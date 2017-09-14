package com.example.xjwhhh.androiddemo3;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class ConversionActivity extends AppCompatActivity {

    Handler handler;

    Button convertButton;
    ImageView firstCurrencyIcon;
    Spinner firstCurrency;
    ImageView secondCurrencyIcon;
    Spinner secondCurrency;
    TextView exchangeRateTextView;
    String firstCurrencyCode="";
    String secondCurrencyCode="";
    ArrayList<String> exchangeRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        // 从Intent获取数据
        // 注意int imageId = getIntent().getIntExtra("currency_code", 0);语句中传入的属性名currency_code一定要写对，和putExtra()方法中的保持一致
        // 通常我们是通过定义常量的方式来定义这样的字符串名字，这里为了简单处理我们用了硬编码的方式。
        int flagId = getIntent().getIntExtra("currency_flag", 0);
        String name = getIntent().getStringExtra("currency_name");

        // 获取特定的视图
        firstCurrencyIcon = (ImageView) findViewById(R.id.firstCurrencyIcon);
         firstCurrency = (Spinner) findViewById(R.id.firstCurrency);

        // 根据数据设置视图展现
        firstCurrencyIcon.setImageResource(flagId);
        ArrayList<String> data_list = new ArrayList<>();
        List<Currency> currencyList=Currency.getAllCurrencies();
        for(int i=0;i<currencyList.size();i++){
            data_list.add(currencyList.get(i).getName());
        }

        ArrayAdapter<String> currency_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);

        firstCurrency.setAdapter(currency_adapter);

        //跳转到汇率转换界面后第一个货币为首页点的那个
        int k = currency_adapter.getCount();
        for (int i = 0; i < k; i++) {
            if (name.equals(currency_adapter.getItem(i))) {
                firstCurrency.setSelection(i, true);
                break;
            }
        }

        secondCurrency = (Spinner) findViewById(R.id.secondCurrency);
        secondCurrency.setAdapter(currency_adapter);
        secondCurrencyIcon=(ImageView)findViewById(R.id.secondCurrencyIcon);
        secondCurrencyIcon.setImageResource(R.drawable.china);

        //TODO 选中代码后设置国旗

        convertButton=(Button)findViewById(R.id.convertButton);
        ButtonListener buttonListener=new ButtonListener();
        convertButton.setOnClickListener(buttonListener);

        handler=new Handler();
        exchangeRateTextView=(TextView)findViewById(R.id.exchangeRateTextView);




    }

    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            List<Currency> currencyList=Currency.getAllCurrencies();
            for(int i=0;i<currencyList.size();i++){
                if(firstCurrency.getSelectedItem().equals(currencyList.get(i).getName())){
                    firstCurrencyCode=currencyList.get(i).getCode();
                    break;
                }
            }
            for(int i=0;i<currencyList.size();i++){
                if(secondCurrency.getSelectedItem().equals(currencyList.get(i).getName())){
                    secondCurrencyCode=currencyList.get(i).getCode();
                    break;
                }
            }

            new Thread(runnable).start();




        }
    }

    Runnable updateExchageRate=new Runnable() {
        @Override
        public void run() {
            exchangeRateTextView.setText(exchangeRates.get(0));
        }
    };

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            exchangeRates=new ArrayList<>();

            JSONObject rate=Data.getRequest3(firstCurrencyCode,secondCurrencyCode);
//            System.out.println(rate);
            try {
                JSONArray rate1 = new JSONArray(rate.get("result").toString());
//                System.out.println(rate1);
                for(int i=0;i<rate1.length();i++){
                    JSONObject jsonObject=rate1.getJSONObject(i);
//                    System.out.println(jsonObject);
//                    System.out.println(jsonObject.get("result").toString());
                    exchangeRates.add(jsonObject.get("result").toString());
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
//            System.out.println("111");
            handler.post(updateExchageRate);



        }
    };

}

package com.example.xjwhhh.androiddemo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ConversionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        // 从Intent获取数据
        // 注意int imageId = getIntent().getIntExtra("currency_code", 0);语句中传入的属性名currency_code一定要写对，和putExtra()方法中的保持一致
        // 通常我们是通过定义常量的方式来定义这样的字符串名字，这里为了简单处理我们用了硬编码的方式。
        int flagId = getIntent().getIntExtra("currency_flag", 0);
        String code = getIntent().getStringExtra("currency_code");

        // 获取特定的视图
        ImageView firstCurrencyIcon = (ImageView) findViewById(R.id.firstCurrencyIcon);
        Spinner firstCurrency = (Spinner) findViewById(R.id.firstCurrency);

        // 根据数据设置视图展现
        firstCurrencyIcon.setImageResource(flagId);
        ArrayList<String> data_list = new ArrayList<>();
        data_list.add("China");
        data_list.add("America");

        ArrayAdapter<String> currency_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);

        firstCurrency.setAdapter(currency_adapter);
        int k = currency_adapter.getCount();
        for (int i = 0; i < k; i++) {
            if (code.equals(currency_adapter.getItem(i))) {
                firstCurrency.setSelection(i, true);
            }
            break;
        }

        Spinner secondCurrency = (Spinner) findViewById(R.id.secondCurrency);
        secondCurrency.setAdapter(currency_adapter);

        //TODO 选中代码后设置国旗

    }

}

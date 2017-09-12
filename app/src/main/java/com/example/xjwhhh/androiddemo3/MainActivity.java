package com.example.xjwhhh.androiddemo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化一个Adapter,第一个参数是this，表示传入的是当前的Activity
        //第二个参数是android.R.layout.simple_list_item_1，这是Android系统自带的一个列表元素（即列表中的每一行）布局，只显一串简单的文字
        //第三个参数是需要显示的所有数据构成的List，即数据源
        CurrencyAdapter teacherAdapter = new CurrencyAdapter(this, R.layout.currency_item, Currency.getAllCurrencies());

        //通过id获取listView
        ListView listView = (ListView) findViewById(R.id.currency_listView);

        //设置listView的Adapter
        listView.setAdapter(teacherAdapter);
    }
}

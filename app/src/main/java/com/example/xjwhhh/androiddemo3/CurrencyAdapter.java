package com.example.xjwhhh.androiddemo3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by xjwhhh on 2017/7/19.
 */

/**
 * 这段代码的主要功能就是，提供了一个getView()方法的重载实现，我们通过重载这个方法就能够让listView根据我们的要求来生成每一个列表元素了。而这个方法做了四件事情：
 * 1.获取老师的数据，getItem(position)会把poistion位置的Teacher对象返回给你，这件事情Adapter会帮你处理好，你只管调用就好了。
 * 2.创建布局，View oneTeacherView = LayoutInflater.from(getContext()).inflate(R.layout.teacher_item, parent, false);这条语句大家照着写就好了，你只需要知道这是根据Layout文件生成一个布局（布局也是一个View的子类）。
 * 3.获取ImageView和TextView
 * 最后根据老师数据设置ImageView和TextView的展现
 */

public class CurrencyAdapter extends ArrayAdapter<Currency> {
    public CurrencyAdapter(Context context, int resource, List<Currency> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 获取老师的数据
        final Currency currency = getItem(position);

        // 创建布局
        View oneTeacherView = LayoutInflater.from(getContext()).inflate(R.layout.currency_item, parent, false);

        // 获取布局中的ImageView和TextView
        ImageView imageView = (ImageView) oneTeacherView.findViewById(R.id.nation_flag);
        TextView textView = (TextView) oneTeacherView.findViewById(R.id.currency_code);

        // 根据货币数据设置ImageView和TextView的展现
        imageView.setImageResource(currency.getFlagId());
        textView.setText(currency.getName());

        oneTeacherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  初始化一个准备跳转到ConversionActivity的Intent
                Intent intent = new Intent(getContext(), ConversionActivity.class);

                // 往Intent中传入Currency相关的数据，供ConversionActivity使用
                intent.putExtra("currency_name", currency.getName());
                intent.putExtra("currency_flag", currency.getFlagId());

                getContext().startActivity(intent);
            }
        });

        return oneTeacherView;
    }
}
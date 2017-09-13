package com.example.xjwhhh.androiddemo3;

import org.json.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xjwhhh on 2017/7/19.
 */
public class Currency {
    private String name;
    private String code;
    private int flagId;
    private String desc;
    private static JSONObject currency;


    //构造函数
    public Currency(String name,String code, int flagId, String desc) {
        this.name=name;
        this.code = code;
        this.flagId = flagId;
        this.desc = desc;

    }

    // 返回一个Currency的列表
    public static List<Currency> getAllCurrencies() {
        List<Currency> currencies=new ArrayList<>();
            Data data = new Data();
            try {
                currency = new JSONObject(data.getRequest2().get("result").toString());
                Iterator it = currency.keys();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    JSONArray array = currency.getJSONArray(key);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonobject = array.getJSONObject(i);
                        //TODO 国家与国旗对应
                        currencies.add(new Currency(jsonobject.get("name").toString(), jsonobject.get("code").toString(), R.drawable.china, jsonobject.get("name").toString()));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return currencies;
    }

    // 以下都是访问内部属性的getter和setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFlagId() {
        return flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}

package com.example.xjwhhh.androiddemo3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjwhhh on 2017/7/19.
 */
public class Currency {
    private String code;
    private int flagId;
    private String desc;

    //构造函数
    public Currency(String code, int flagId, String desc) {
        this.code = code;
        this.flagId = flagId;
        this.desc = desc;
    }

    // 返回一个Currency的列表
    public static List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<Currency>();
        currencies.add(new Currency("China", R.drawable.china, "China"));
        currencies.add(new Currency("America", R.drawable.america, "America"));
        return currencies;
    }

    // 以下都是访问内部属性的getter和setter

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

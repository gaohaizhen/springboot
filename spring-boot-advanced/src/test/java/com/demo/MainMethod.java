package com.demo;

import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;

public class MainMethod {
    public static void main(String[] args) {

        Locale  locale = Locale.getDefault();

        System.out.println(locale);//zh_CN


        System.out.println(Arrays.asList(Locale.getAvailableLocales()));
        System.out.println(Arrays.asList(Locale.SIMPLIFIED_CHINESE));//zh_CN

        System.out.println(locale.getCountry());//CN

        System.out.println(locale.getDisplayCountry());//中国

        System.out.println(locale.getDisplayLanguage());//中文

        TimeZone timeZone=  TimeZone.getDefault();

        System.out.println(timeZone.getDisplayName());//中国标准时间
        System.out.println(timeZone.getID());//Asia/Shanghai





    }
}

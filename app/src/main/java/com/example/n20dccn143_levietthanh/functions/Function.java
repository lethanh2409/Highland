package com.example.n20dccn143_levietthanh.functions;

import java.text.NumberFormat;
import java.util.Locale;

public class Function {
    public static String formatDateTimeToDate(String dateTime){
        String result = "";
        char[] chars = dateTime.toCharArray();
        for (int i = 0; i < 10; i++) {
            result += chars[i];
        }
        return  result;
    }


    public static String formatToVND(Integer amount){
        NumberFormat vnCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return vnCurrencyFormat.format(amount);
    }
}

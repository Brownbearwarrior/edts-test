package com.test.tech.utils;

public class CommonUtils {
    private CommonUtils(){}

    public static String getRandomValue(int n){
        String value = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(value.length() * Math.random());
            sb.append(value.charAt(index));
        }

        return sb.toString();
    }
}

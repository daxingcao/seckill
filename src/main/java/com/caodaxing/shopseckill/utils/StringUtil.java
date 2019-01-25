package com.caodaxing.shopseckill.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isNumber(String str){
        if(StringUtil.isEmpty(str)){
            return false;
        }
        final String patternStr = "^[-\\+]?[\\d]+|[-\\+]?[\\d]+.[\\d]+$";
        Pattern pattern = Pattern.compile(patternStr);
        return pattern.matcher(str).matches();
    }

    public static String humpToUnderline(String str){
        String newStr = str.replaceAll("[^a-zA-Z0-9]+","");
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < newStr.length();i++){
            char character = newStr.charAt(i);
            if(StringUtil.isEmpty(builder.toString())){
                builder.append(Character.toLowerCase(character));
            }else if(Character.isUpperCase(character)){
                builder.append("_").append(Character.toLowerCase(character));
            }else {
                builder.append(character);
            }
        }
        return builder.toString();
    }

    public static String replaceChinese(String str, String replace){
        return Pattern.compile("[\\u4e00-\\u9fa5]+").matcher(str).replaceAll(replace);
    }

    private static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

}

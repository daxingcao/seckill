package com.caodaxing.shopseckill.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isNumber(String str){
        if(StringUtil.isEmpty(str)){
            return false;
        }
        return RegexRoles.NUMBER_PATTERN.matcher(str).matches();
    }

    public static String humpToUnderline(String str){
        Pattern pattern = RegexRoles.NOT_INCLUDE_LETTER_NUMBER_PATTERN;
        String newStr = pattern.matcher(str).replaceAll("");
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
        return RegexRoles.INCLUDE_CHINESE_PATTERN.matcher(str).replaceAll(replace);
    }

    private static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }

}

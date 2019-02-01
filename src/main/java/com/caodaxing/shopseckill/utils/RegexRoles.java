package com.caodaxing.shopseckill.utils;

import java.util.regex.Pattern;

/**
 * @author daxing.cao
 */
class RegexRoles {

    static final Pattern NUMBER_PATTERN = Pattern.compile("[-\\+]?[\\d]+|[-\\+]?[\\d]+.[\\d]+$");

    static final Pattern INCLUDE_CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]+");

    static final Pattern NOT_INCLUDE_LETTER_NUMBER_PATTERN = Pattern.compile("[^a-zA-Z0-9]+");

}

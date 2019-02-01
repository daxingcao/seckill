package com.caodaxing.shopseckill.utils;

/**
 * @author daxing.cao
 */
class RegexRoles {

    static final String NUMBER_PATTERN = "[-\\+]?[\\d]+|[-\\+]?[\\d]+.[\\d]+$";

    static final String INCLUDE_CHINESE_PATTERN = "[\\u4e00-\\u9fa5]+";

    static final String NOT_INCLUDE_LETTER_NUMBER_PATTERN = "[^a-zA-Z0-9]+";

}

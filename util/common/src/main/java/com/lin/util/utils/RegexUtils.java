package com.lin.util.utils;

import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
public class RegexUtils {

    /**
     * 用户名(4-16位汉字,母数,字母的组合)
     */
    public static final String REGEX_USERNAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]{4,16}+$";
    /**
     * 密码(6-16位字母数字的组合)
     */
    public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";


    /**
     * 验证用户名(4-16位汉字,母数,字母的组合).
     *
     * @param userName the user name
     * @return the boolean
     * @author : fangkuaiIt / 2019-02-25
     */
    public static boolean checkUserName(String userName){
        return Pattern.matches(REGEX_USERNAME, userName);
    }

    /**
     * 验证密码(6-16位字母数字的组合).
     *
     * @param password the password
     * @return the boolean
     * @author : fangkuaiIt / 2019-02-25
     */
    public static boolean checkPassword(String password){
        return Pattern.matches(REGEX_PASSWORD, password);
    }


    public static void main(String[] args) {
        String userName = "杨112";
        System.out.println(checkUserName(userName));

        String password = "1234ss";
        System.out.println(checkPassword(password));
    }
}

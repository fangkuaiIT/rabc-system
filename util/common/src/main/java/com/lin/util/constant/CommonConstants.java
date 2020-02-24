package com.lin.util.constant;

/**
 * 通用常量
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
public class CommonConstants {

    /************************************************************
     ************************ 应用环境常量 **********************
     ************************************************************/
    /**
     * 开发环境常量
     */
    public static final String SPRING_PROFILES_PRODUCTION = "pro";

    /**
     * 通用单位字典
     */
    public static enum GeneralUnit {
        LIFETIME("lifetimeUnitCode"),
        MONEY("moneyUnitCode");

        private String field;

        GeneralUnit(String field) {
            this.field = field;
        }

        public String getField() {
            return field;
        }}


    /************************************************************
     *************************** 分隔符 *************************
     ************************************************************/
    /**
     * FILE_SEPARATOR
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    /**
     * LINE_SEPARATOR
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * PATH_SEPARATOR
     */
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");
}

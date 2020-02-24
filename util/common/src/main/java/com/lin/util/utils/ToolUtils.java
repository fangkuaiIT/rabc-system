package com.lin.util.utils;

import com.yyfly.common.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

/**
 * 通用工具类
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
public class ToolUtils {

    /**
     * 首字母小写
     *
     * @param s the s
     * @return the string
     * @author : fangkuaiIt / 2019-01-08
     */
    public static String lowerFirst(String s){
        if (Character.isLowerCase(s.charAt(0))){
            return s;
        }
        else {
            return (new StringBuilder().append(Character.toLowerCase(s.charAt(0))).append(s.substring(1))).toString();
        }
    }

    /**
     * 首字母大写
     *
     * @param s the s
     * @return the string
     * @author : fangkuaiIt / 2019-01-08
     */
    public static String upperFirst(String s){
        if (Character.isUpperCase(s.charAt(0))){
            return s;
        }
        else {
            return (new StringBuilder().append(Character.toUpperCase(s.charAt(0))).append(s.substring(1))).toString();
        }
    }


    /**
     * 获取项目根目录
     * (此方法前提是 MainApplication所在的Module与工具类所在的Module不同)
     *
     * @return the string
     * @author : fangkuaiIt / 2019-01-08
     */
    public static String getProjectPath(){
        String toolUtilClassPath = ToolUtils.class.getResource("").getPath();
        String mainApplicationClassPath = ToolUtils.class.getClassLoader().getResource("").getPath();
        StringBuilder path = new StringBuilder();

        if (!toolUtilClassPath.startsWith("file:/")){
            //开发模式下根路径
            char[] toolUtilClassPathArray = toolUtilClassPath.toCharArray();
            char[] mainApplicationClassPathArray = mainApplicationClassPath.toCharArray();
            for (int i = 0; i < toolUtilClassPathArray.length; i++){
                if (mainApplicationClassPathArray.length > i && toolUtilClassPathArray[i] == mainApplicationClassPathArray[i]){
                    path.append(toolUtilClassPathArray[i]);
                }
                else {
                    break;
                }
            }
        }
        else if (!mainApplicationClassPath.startsWith("file:/")){
            // 部署服务器模式下根路径
            mainApplicationClassPath = mainApplicationClassPath.replace("/WEB-INF/classes/", "");
            mainApplicationClassPath = mainApplicationClassPath.replace("/target/classes/", "");
            try {
                path.append(URLDecoder.decode(mainApplicationClassPath, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                return mainApplicationClassPath;
            }
        }
        else {
            // jar包启动模式下根路径
            String property = System.getProperty("java.class.path");
            int firstIndex = property.lastIndexOf(System.getProperty("path.separator")) + 1;
            int lastIndex = property.lastIndexOf(File.separator) + 1;
            path.append(property, firstIndex, lastIndex);
        }

        File file = new File(path.toString());
        String rootPath = "/";

        try {
            rootPath = URLDecoder.decode(file.getAbsolutePath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rootPath.replaceAll("\\\\", "/");
    }


    /**
     * 获取文件后缀名
     *
     * @param fileName the file name
     * @return the string
     * @author : fangkuaiIt / 2019-01-08
     */
    public static String getFileSuffix(String fileName){
        if (StringUtils.isNotEmpty(fileName)){
            int lastIndexOf = fileName.lastIndexOf(".");
            return fileName.substring(lastIndexOf);
        }
        return "";
    }

    /**
     * 按照时间戳 + 随机5五位数返回字符串
     *
     * @return the string
     * @author : fangkuaiIt / 2019-01-16
     */
    public static String getRandomName(){
        return String.valueOf(System.currentTimeMillis() + (int)Math.random() * 10000);
    }

    /**
     * List to string string.
     *
     * @param strings   the strings
     * @param seperator the seperator
     * @return the string
     * @author : fangkuaiIt / 2019-01-11
     */
    public static String listToString(List<String> strings, String seperator){
        String result = "";
        if (strings != null && strings.size() > 0){
            StringBuilder sb = new StringBuilder();
            for (String s : strings){
                sb.append(s + seperator);
            }
            String str = sb.toString();
            result = str.substring(0, str.length() - 1);
        }

        return result;
    }

    /**
     * List to string string.
     *
     * @param strings the strings
     * @return the string
     * @author : fangkuaiIt / 2019-01-11
     */
    public static String listToString(List<String> strings){
        return listToString(strings, ",");
    }

    /**
     * String to list list.
     *
     * @param string    the string
     * @param seperator the seperator
     * @return the list
     * @author : fangkuaiIt / 2019-01-11
     */
    public static List<String> stringToList(String string, String seperator){
        return Arrays.asList(StringUtils.split(string, seperator));
    }

    /**
     * String to list list.
     *
     * @param string the string
     * @return the list
     * @author : fangkuaiIt / 2019-01-11
     */
    public static List<String> stringToList(String string){
        return stringToList(string, ",");
    }

    /**
     * Enabled to entity e.
     *
     * @param <E>     the type parameter
     * @param entity  the entity
     * @param enabled the enabled
     * @return the e
     * @author : fangkuaiIt / 2019-01-15
     */
    public static <E extends BaseEntity> E enabledToEntity(E entity, boolean enabled){
        if (enabled){
            entity.setStatus(BaseEntity.NORMAL);
        }else {
            entity.setStatus(BaseEntity.DISABLE);
        }
        return entity;
    }

    /**
     * Status to boolean boolean.
     *
     * @param status the status
     * @return the boolean
     * @author : fangkuaiIt / 2019-02-15
     */
    public static boolean statusToBoolean(int status){
        if (BaseEntity.NORMAL == status){
            return true;
        }
        if (BaseEntity.DISABLE == status){
            return false;
        }
        return true;
    }

    /**
     * Boolean to status int.
     *
     * @param status the status
     * @return the int
     * @author : fangkuaiIt / 2019-02-15
     */
    public static int booleanToStatus(boolean status){
        if (status){
            return BaseEntity.NORMAL;
        }else {
            return BaseEntity.DISABLE;
        }
    }
}

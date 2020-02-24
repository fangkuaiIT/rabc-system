package com.lin.util.utils;

import com.google.common.base.Preconditions;

import javax.persistence.Table;

/**
 * 表工具类
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public class TableUtils {

    /**
     * 通过实体类class获取表名.
     *
     * @param clazz the clazz
     * @return the string
     * @author : yangjunqing / 2019-04-10
     */
    public static String getTableName(Class clazz){
        String name = null;

        Preconditions.checkArgument(null != clazz, "clazz不能为空!");
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (null != table){
            name = table.name();
        }

        return name;
    }
}

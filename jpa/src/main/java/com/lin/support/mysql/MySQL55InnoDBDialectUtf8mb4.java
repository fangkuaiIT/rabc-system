package com.lin.support.mysql;

import org.hibernate.dialect.MySQL55Dialect;

/**
 * @author : fangkauiIT / fangkauiIT
 * @version : 1.0
 */
public class MySQL55InnoDBDialectUtf8mb4 extends MySQL55Dialect {

    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_bin";
    }
}

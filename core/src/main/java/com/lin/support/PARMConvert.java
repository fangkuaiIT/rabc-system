package com.lin.support;

import java.util.List;

/**
 * parm与entity互相转换
 *
 * @param <T> the type parameter
 * @param <P> the type parameter
 * @author : fangkauiIT
 * @version : 1.0
 */
public interface PARMConvert<T, P> {

    /**
     * PARM转实体.
     *
     * @param d     the d
     * @param clazz the clazz
     * @return the t
     * @author : fangkauiIT/ 2019-04-13
     */
    T parmToEntity(P d, Class<T> clazz);

    /**
     * 实体转parm.
     *
     * @param t     the t
     * @param clazz the clazz
     * @return the p
     * @author : fangkauiIT/ 2019-04-17
     */
    P entityToPARM(T t, Class<P> clazz);

    /**
     * PARM转实体.
     *
     * @param pList the p list
     * @param clazz the clazz
     * @return the list
     * @author : fangkauiIT/ 2019-04-13
     */
    List<T> parmToEntity(List<P> pList, Class<T> clazz);

    /**
     * 实体转parm.
     *
     * @param tList the t list
     * @param clazz the clazz
     * @return the list
     * @author : fangkauiIT/ 2019-04-17
     */
    List<P> entityToPARM(List<T> tList, Class<P> clazz);
}

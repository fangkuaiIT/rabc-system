package com.lin.support;

import java.util.List;

/**
 * dto与entity互相转换
 *
 * @param <T> the type parameter
 * @param <D> the type parameter
 * @author : fangkauiIT
 * @version : 1.0
 */
public interface DTOConvert<T, D> {

    /**
     * 实体转DTO.
     *
     * @param t     the t
     * @param clazz the clazz
     * @return the d
     * @author : yangjunqing / 2019-04-13
     */
    D entityToDTO(T t, Class<D> clazz);

    /**
     * DTO转实体.
     *
     * @param d     the d
     * @param clazz the clazz
     * @return the t
     * @author : yangjunqing / 2019-04-13
     */
    T dtoToEntity(D d, Class<T> clazz);

    /**
     * 实体转DTO.
     *
     * @param tList the t list
     * @param clazz the clazz
     * @return the list
     * @author : yangjunqing / 2019-04-13
     */
    List<D> entityToDTO(List<T> tList, Class<D> clazz);

    /**
     * DTO转实体.
     *
     * @param dList the d list
     * @param clazz the clazz
     * @return the list
     * @author : yangjunqing / 2019-04-13
     */
    List<T> dtoToEntity(List<D> dList, Class<T> clazz);
}

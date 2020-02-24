package com.lin.service;



import com.lin.search.JpaListData;
import com.lin.search.JpaSearchCriteria;
import com.lin.support.DTOConvert;
import com.lin.support.PARMConvert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * jps 基础 service
 *
 * @param <T> the type parameter
 * @param <P> the type parameter
 * @param <D> the type parameter
 * @author : fangkauiIT / fangkauiIT
 * @version : 1.0
 */
public interface JpaBaseService<T, P, D> extends PARMConvert<T, P>, DTOConvert<T, D> {

    /**
     * 分页查询.
     *
     * @param searchCriteria the search criteria
     * @return the jpa list data
     * @author : fangkauiIT / 2019-03-22
     */
    JpaListData findPageList(JpaSearchCriteria searchCriteria);

    /**
     * 查找全部
     *
     * @return list list
     * @author : yyfly / 2018-08-08
     */
    List<T> findAll();

    /**
     * 根据给定ids查找列表
     *
     * @param ids the ids
     * @return list list
     * @author : yyfly / 2018-08-08
     */
    List<T> findAll(Iterable<String> ids);

    /**
     * Returns all entities sorted by the given options.
     *
     * @param sort the sort
     * @return all entities sorted by the given options
     * @author : yyfly / 2018-08-08
     */
    List<T> findAll(Sort sort);


    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable the pageable
     * @return a page of entities
     * @author : yyfly / 2018-08-08
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Returns all entities matching the given {@link Specification}.
     *
     * @param spec the spec
     * @return list list
     * @author : yyfly / 2018-08-08
     */
    List<T> findAll(Specification<T> spec);

    /**
     * Returns a {@link Page} of entities matching the given {@link Specification}.
     *
     * @param spec     the spec
     * @param pageable the pageable
     * @return page page
     * @author : yyfly / 2018-08-08
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    /**
     * Returns all entities matching the given {@link Specification} and {@link Sort}.
     *
     * @param spec the spec
     * @param sort the sort
     * @return list list
     * @author : yyfly / 2018-08-08
     */
    List<T> findAll(Specification<T> spec, Sort sort);

    /**
     * 保存单个实体
     *
     * @param <S>    the type parameter
     * @param entity the entity
     * @return s s
     * @author : yyfly / 2018-08-08
     */
    <S extends T> S save(S entity);

    /**
     * Saves an entity and flushes changes instantly.
     *
     * @param entity the entity
     * @return the saved entity
     * @author : yyfly / 2018-08-08
     */
    T saveAndFlush(T entity);

    /**
     * 保存给定的所有实体
     *
     * @param <S>      the type parameter
     * @param entities the entities
     * @return list list
     * @author : yyfly / 2018-08-08
     */
    <S extends T> List<S> save(Iterable<S> entities);

    /**
     * 判断给定id是否存在
     *
     * @param id the id
     * @return boolean boolean
     * @author : yyfly / 2018-08-08
     */
    boolean exists(String id);

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}
     * @author : yyfly / 2018-08-08
     */
    T findById(String id);

    /**
     * Returns a single entity matching the given {@link Specification}.
     *
     * @param spec the spec
     * @return t t
     * @author : yyfly / 2018-08-08
     */
    T findBySpecification(Specification<T> spec);

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     * @author : yyfly / 2018-08-08
     */
    void deleteById(String id);

    /**
     * Deletes a given entity.
     *
     * @param entity the entity
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     * @author : yyfly / 2018-08-08
     */
    void delete(T entity);

    /**
     * Deletes the given entities in a batch which means it will create a single. Assume that we will clear
     * the {@link javax.persistence.EntityManager} after the call.
     *
     * @param entities the entities
     * @author : yyfly / 2018-08-08
     */
    void deleteInBatch(Iterable<T> entities);

    /**
     * Deletes the given entities.
     *
     * @param entities the entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     * @author : yyfly / 2018-09-10
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Deletes all entities managed by the repository.
     *
     * @author : yyfly / 2018-09-10
     */
    void deleteAll();

    /**
     * Deletes the entity with the given id.
     *
     * @param id   must not be {@literal null}.
     * @param flag the flag
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     * @author : yyfly / 2018-08-08
     */
    void deleteById(String id, Boolean flag);

    /**
     * Deletes a given entity.
     *
     * @param entity the entity
     * @param flag   the flag
     * @throws IllegalArgumentException in case the given entity is (@literal null}.
     * @author : yyfly / 2018-08-08
     */
    void delete(T entity, Boolean flag);

    /**
     * Deletes the given entities in a batch which means it will create a single. Assume that we will clear
     * the {@link javax.persistence.EntityManager} after the call.
     *
     * @param entities the entities
     * @param flag     the flag
     * @author : yyfly / 2018-08-08
     */
    void deleteInBatch(Iterable<T> entities, Boolean flag);

    /**
     * Deletes the given entities.
     *
     * @param entities the entities
     * @param flag     the flag
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     * @author : yyfly / 2018-09-10
     */
    void deleteAll(Iterable<? extends T> entities, Boolean flag);

    /**
     * Deletes all entities managed by the repository.
     *
     * @param flag the flag
     * @author : yyfly / 2018-09-10
     */
    void deleteAll(Boolean flag);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     * @author : yyfly / 2018-08-08
     */
    long count();

    /**
     * Returns the number of instances that the given {@link Specification} will return.
     *
     * @param spec the {@link Specification} to count instances for
     * @return the number of instances
     * @author : yyfly / 2018-08-08
     */
    long count(Specification<T> spec);

    /**
     * Flushes all pending changes to the database.
     *
     * @author : yyfly / 2018-08-08
     */
    void flush();
}

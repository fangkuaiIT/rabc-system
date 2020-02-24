package com.lin.service.impl;


import com.google.common.collect.Lists;
import com.lin.config.properties.SeerJpaProperties;
import com.lin.constant.Constants;
import com.lin.entity.BaseEntity;
import com.lin.repository.JpaBaseRepository;
import com.lin.search.JpaListData;
import com.lin.search.JpaSearchCriteria;
import com.lin.service.JpaBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * jps 基础 service impl
 *
 * @param <T> the type parameter
 * @author : fangkauiIT / fangkauiIT
 * @version : 1.0
 */
@Slf4j
public abstract class JpaBaseServiceImpl<T extends BaseEntity, P, D> implements JpaBaseService<T, P, D> {

    /**
     * 获取JpaBaseRepository.
     *
     * @return the jpa base repository
     * @author : fangkauiIT / 2019-04-16
     */
    abstract protected JpaBaseRepository<T> getJpaBaseRepository();

    /**
     * 获取SeerJpaProperties.
     *
     * @return the seer jpa properties
     * @author : fangkauiIT / 2019-04-16
     */
    abstract protected SeerJpaProperties getSeerJpaProperties();


    @Override
    public <S extends T> S save(S entity) {
        return getJpaBaseRepository().save(entity);
    }

    @Override
    public T saveAndFlush(T entity) {
        return getJpaBaseRepository().saveAndFlush(entity);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        return getJpaBaseRepository().saveAll(entities);
    }

    @Override
    public boolean exists(String id) {
        return getJpaBaseRepository().existsById(id);
    }

    @Override
    public JpaListData findPageList(JpaSearchCriteria searchCriteria) {
        return JpaListData.of(findAll(searchCriteria.buildQuery(), searchCriteria.buildPage()));
    }

    @Override
    public List<T> findAll() {

        if (!getSeerJpaProperties().getEnableLogicDelete()) {
            return getJpaBaseRepository().findAll();
        } else {
            Specification<T> spec = (root, query, cb) -> {

                //获得查询属性
                Path<String> sPath = root.get("deleteTag");

                //设置属性查询类型，如like ge gt...
                Predicate notEqualPredicate = cb.notEqual(sPath, Constants.DELETED_TAG);

                return cb.and(notEqualPredicate);
            };

            return getJpaBaseRepository().findAll(spec);
        }
    }

    @Override
    public List<T> findAll(Iterable<String> ids) {
        if (!getSeerJpaProperties().getEnableLogicDelete()) {
            return getJpaBaseRepository().findAllById(ids);
        } else {
            return getJpaBaseRepository().findAllById(ids)
                    .stream()
                    .filter(entity -> entity.getDeleteTag().equals(Constants.UNDELETE_TAG))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<T> findAll(Sort sort) {
        if (!getSeerJpaProperties().getEnableLogicDelete()) {
            return getJpaBaseRepository().findAll(sort);
        } else {
            return getJpaBaseRepository().findAll(sort)
                    .stream()
                    .filter(entity -> entity.getDeleteTag().equals(Constants.UNDELETE_TAG))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        if (!getSeerJpaProperties().getEnableLogicDelete()) {
            return getJpaBaseRepository().findAll(spec);
        } else {
            return getJpaBaseRepository().findAll(spec)
                    .stream()
                    .filter(entity -> entity.getDeleteTag().equals(Constants.UNDELETE_TAG))
                    .collect(Collectors.toList());
        }

    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        if (!getSeerJpaProperties().getEnableLogicDelete()) {
            return getJpaBaseRepository().findAll(spec, sort);
        } else {
            return getJpaBaseRepository().findAll(spec, sort)
                    .stream()
                    .filter(entity -> entity.getDeleteTag().equals(Constants.UNDELETE_TAG))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        if (!getSeerJpaProperties().getEnableLogicDelete()) {
            return getJpaBaseRepository().findAll(pageable);
        }
        Specification<T> spec = (root, query, cb) -> {

            //获得查询属性
            Path<String> sPath = root.get("deleteTag");

            //设置属性查询类型，如like ge gt...
            Predicate notEqualPredicate = cb.notEqual(sPath, Constants.DELETED_TAG);

            return cb.and(notEqualPredicate);
        };

        return getJpaBaseRepository().findAll(spec, pageable);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {

        if (!getSeerJpaProperties().getEnableLogicDelete()) {
            return getJpaBaseRepository().findAll(spec, pageable);
        }

        return getJpaBaseRepository().findAll(buildSpecification(spec), pageable);
    }

    /**
     * 构建 Specification 增加删除
     *
     * @param spec the spec
     * @return the specification
     * @author : yyfly / 2018-09-10
     */
    private Specification<T> buildSpecification(Specification<T> spec) {
        Specification<T> specDeleted = (root, query, cb) -> {

            //获得查询属性
            Path<String> sPath = root.get("deleteTag");

            //设置属性查询类型，如like ge gt...
            Predicate notEqualPredicate = cb.notEqual(sPath, Constants.DELETED_TAG);

            return cb.and(notEqualPredicate);
        };

        if (null == spec) {
            return specDeleted;
        } else {
            return spec.and(specDeleted);
        }
    }

    @Override
    public T findById(String id) {
        if (!getSeerJpaProperties().getEnableLogicDelete()) {
            Optional<T> optional = getJpaBaseRepository().findById(id);
            return optional.isPresent() ? optional.get() : null;
        } else {
            Optional<T> optional = getJpaBaseRepository().findById(id).filter(entity -> entity.getDeleteTag().equals(Constants.UNDELETE_TAG));
            return optional.isPresent() ? optional.get() : null;
        }
    }

    @Override
    public T findBySpecification(Specification<T> spec) {
        if (!getSeerJpaProperties().getEnableLogicDelete()) {
            return getJpaBaseRepository().findOne(spec).get();
        } else {
            return getJpaBaseRepository().findOne(buildSpecification(spec)).get();
        }
    }

    @Override
    public void deleteById(String id) {
        deleteById(id, !getSeerJpaProperties().getEnableLogicDelete());
    }

    @Override
    public void delete(T entity) {
        delete(entity, !getSeerJpaProperties().getEnableLogicDelete());
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        deleteInBatch(entities, !getSeerJpaProperties().getEnableLogicDelete());
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        deleteAll(entities, !getSeerJpaProperties().getEnableLogicDelete());
    }

    @Override
    public void deleteAll() {
        deleteAll(!getSeerJpaProperties().getEnableLogicDelete());
    }

    @Override
    public void deleteById(String id, Boolean flag) {
        if (flag) {
            getJpaBaseRepository().deleteById(id);
        } else {
            T entity = findById(id);
            entity.setDeleteTag(Constants.DELETED_TAG);
            getJpaBaseRepository().save(entity);
        }
    }

    @Override
    public void delete(T entity, Boolean flag) {
        if (flag) {
            getJpaBaseRepository().delete(entity);
        } else {
            entity.setDeleteTag(Constants.DELETED_TAG);
            getJpaBaseRepository().save(entity);
        }
    }

    @Override
    public void deleteInBatch(Iterable<T> entities, Boolean flag) {
        if (flag) {
            getJpaBaseRepository().deleteInBatch(entities);
        } else {
            for (T entity : entities) {
                entity.setDeleteTag(Constants.DELETED_TAG);
            }
            getJpaBaseRepository().saveAll(entities);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities, Boolean flag) {
        if (flag) {
            getJpaBaseRepository().deleteAll(entities);
        } else {
            for (T entity : entities) {
                entity.setDeleteTag(Constants.DELETED_TAG);
            }
            getJpaBaseRepository().saveAll(entities);
        }
    }

    @Override
    public void deleteAll(Boolean flag) {
        if (flag) {
            getJpaBaseRepository().deleteAll();
        } else {
            List<T> list = getJpaBaseRepository().findAll();
            for (T entity : list) {
                entity.setDeleteTag(Constants.DELETED_TAG);
            }
            getJpaBaseRepository().saveAll(list);
        }
    }

    @Override
    public void flush() {
        getJpaBaseRepository().flush();
    }

    @Override
    public long count() {
        return getJpaBaseRepository().count();
    }

    @Override
    public long count(Specification<T> spec) {
        return getJpaBaseRepository().count(spec);
    }

    @Override
    public D entityToDTO(T t, Class<D> clazz) {
        D d = null;

        try {
            d = clazz.newInstance();
            BeanUtils.copyProperties(t, d);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return d;
    }

    @Override
    public T dtoToEntity(D d, Class<T> clazz) {
        T t = null;

        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(d, t);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return t;
    }

    @Override
    public List<D> entityToDTO(List<T> tList, Class<D> clazz) {
        List<D> dList = Lists.newLinkedList();

        if (null != tList && tList.size() > 0){
            tList.forEach(t -> {
                D d = entityToDTO(t, clazz);
                dList.add(d);
            });
        }
        return dList;
    }

    @Override
    public List<T> dtoToEntity(List<D> dList, Class<T> clazz) {
        List<T> tList = Lists.newArrayList();

        if (null != dList && dList.size() > 0){
            dList.forEach(d -> {
                T t = dtoToEntity(d, clazz);
                tList.add(t);
            });
        }
        return tList;
    }

    @Override
    public T parmToEntity(P d, Class<T> clazz) {
        T t = null;

        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(d, t);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return t;
    }

    @Override
    public List<T> parmToEntity(List<P> pList, Class<T> clazz) {
        List<T> tList = Lists.newArrayList();

        if (null != pList && pList.size() > 0){
            pList.forEach(p -> {
                T t = parmToEntity(p, clazz);
                tList.add(t);
            });
        }
        return tList;
    }

    @Override
    public P entityToPARM(T t, Class<P> clazz) {
        P p = null;

        try {
            p = clazz.newInstance();
            BeanUtils.copyProperties(t, p);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return p;
    }

    @Override
    public List<P> entityToPARM(List<T> tList, Class<P> clazz) {
        List<P> pList = Lists.newLinkedList();

        if (null != tList && tList.size() > 0){
            tList.forEach(t -> {
                P p = entityToPARM(t, clazz);
                pList.add(p);
            });
        }
        return pList;
    }
}

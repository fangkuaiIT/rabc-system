package com.lin.support;


import com.google.common.base.Strings;
import com.lin.entity.BaseEntity;
import com.lin.utils.IdUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Twitter主键生成策略
 * @author : fangkauiIT / fangkauiIT
 * @version : 1.0
 */
public class TwitterIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        if (null == o){
            throw new IllegalArgumentException("实体对象不能为空!");
        }
        if (!(o instanceof BaseEntity)){
            throw new IllegalArgumentException("实体对象必须是BaseEntity的子类!");
        }

        BaseEntity baseEntity = (BaseEntity) o;
        //新增
        if (Strings.isNullOrEmpty(baseEntity.getId())){
            return IdUtils.nextId();
        }
        //更新
        else {
            return baseEntity.getId();
        }
    }
}

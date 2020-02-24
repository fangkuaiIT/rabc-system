package com.lin.support;


import com.google.common.base.Strings;
import com.lin.constant.Constants;
import com.lin.entity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 实体监听类
 *
 * @author : fangkauiIT / fangkauiIT
 * @version : 1.0
 */
public class EntityListener {

    /**
     * 实体执行save前执行的方法.
     *
     * @param entity the entity
     * @author : fangkauiIT / 2019-04-15
     */
    @PrePersist
    public void prePersist(BaseEntity entity){
        if (Strings.isNullOrEmpty(entity.getId())){
            entity.setDeleteTag(Constants.UNDELETE_TAG);
        }
    }

    /**
     * 实体执行更新前执行的方法.
     *
     * @param entity the entity
     * @author : fangkauiIT / 2019-04-16
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity){
        if (null == entity.getDeleteTag()){
            entity.setDeleteTag(Constants.UNDELETE_TAG);
        }
    }
}

package com.lin.repository;



import com.lin.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * jps 基础 repository
 * @param <T> the type parameter
 * @author :fangkauiIT
 * @version : 1.0
 */
@NoRepositoryBean
public interface JpaBaseRepository<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
}

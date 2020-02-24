package com.lin.entity;



import com.lin.support.EntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * JPA 基础实体
 *
 * @author : fangkauiIT
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, EntityListener.class})
public class JpaBaseEntity extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2353514976340669957L;


    /**
     * 版本
     */
    private Long version;


    @Override
    @Id
    @GenericGenerator(name = "twitter-id", strategy = "com.lin.support.TwitterIdGenerator")
    @GeneratedValue(generator = "twitter-id")
    @Column(length = 32, unique = true)
    public String getId() {
        return super.getId();
    }


    @Override
    @CreatedBy
    @Column(length = 20, updatable = false)
    public String getCreateBy() {
        return super.getCreateBy();
    }


    @Override
    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime getCreateDate() {
        return super.getCreateDate();
    }


    @Override
    @LastModifiedBy
    @Column(length = 20)
    public String getUpdateBy() {
        return super.getUpdateBy();
    }

    @Override
    @UpdateTimestamp
    public LocalDateTime getUpdateDate() {
        return super.getUpdateDate();
    }

    @Override
    @Column(length = 1)
    public Integer getDeleteTag() {
        return super.getDeleteTag();
    }

    /**
     * Get version long.
     *
     * @return the long
     * @author : fangkauiIT / 2019-04-16
     */
    @Version
    public Long getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     * @author : fangkauiIT
     * @Date : 2019-04-16
     */
    public void setVersion(Long version) {
        this.version = version;
    }
}

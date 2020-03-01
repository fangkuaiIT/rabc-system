package com.lin.entity;

import com.yyfly.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "imms_user")
public class User extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8714037745900302751L;

    /**
     * 用户名称
     */
    @Column(updatable = false)
    private String username;

    /**
     * Password
     */
    @Column(updatable = false)
    private String password;

    /**
     * 最后一次密码设置时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date lastPasswordReset;

    /**
     * 员工编号
     */
    private String employeeId;

    /**
     * 角色
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "imms_t_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id ASC")
    private Set<Role> roles;


    /**
     * Get role ids set.
     *
     * @return the set
     * @author : yangjunqing / 2019-01-15
     */
    public Set<String> getRoleIds(){
        Set<String> roleIds = new HashSet<>();
        if (roles != null && roles.size() > 0){
            for (Role role : roles){
                roleIds.add(role.getId());
            }
        }
        return roleIds;
    }

    /**
     * Get role names set.
     *
     * @return the set
     * @author : yangjunqing / 2019-01-19
     */
    public Set<String> getRoleNames(){
        Set<String> roleNames = new HashSet<>();
        if (roles != null && roles.size() > 0){
            for (Role role : roles){
                roleNames.add(role.getRoleName());
            }
        }
        return roleNames;
    }



    /**
     * Get permissions set.
     *
     * @return the set
     * @author : yangjunqing / 2019-01-15
     */
    public Set<String> getPermissions(){
        Set<String> permissions = null;
        if (roles != null && roles.size() > 0){
            for (Role role : roles){
                permissions.addAll(role.getPermissions());
            }
        }
        return permissions;
    }
}

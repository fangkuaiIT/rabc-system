package com.lin.entity;

import com.yyfly.common.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "imms_role")
public class Role extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 710246654760750363L;




    /**
     * 角色名称（Role_Admin样式，这个是传递给springsecurity后端鉴权使用的）
     */
    private String roleName;



    /**
     * 备注
     */
    private String remark;

    /**
    角色名称（“管理员”这个是用来显示用户的中文名称的）
     */

    private String nameZh;

    /**
     * 资源
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "imms_t_role_resource",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")}
    )
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id ASC")
    private Set<Resource> resources;


    /**
     * Get resource ids set.
     *
     * @return the set
     * @author : fangkuaiIt / 2019-01-15
     */
    public Set<String> getResourceIds(){
        Set<String> resourceIds = new HashSet<>();
        if (resources != null && resources.size() > 0){
            for (Resource resource : resources){
                resourceIds.add(resource.getId());
            }
        }
        return resourceIds;
    }

    /**
     * Get resource names set.
     *
     * @return the set
     * @author : fangkuaiIt / 2019-01-19
     */
    public Set<String> getResourceNames(){
        Set<String> resourceNames = new HashSet<>();
        if (resources != null && resources.size() > 0){
            for (Resource resource : resources){
                resourceNames.add(resource.getResourceName());
            }
        }
        return resourceNames;
    }

    /**
     * Get permissions set.
     *
     * @return the set
     * @author : fangkuaiIt / 2019-01-15
     */
    public Set<String> getPermissions(){
        Set<String> permissions = null;
        if (resources != null && resources.size() > 0){
            permissions = new HashSet<>(resources.size());
            for (Resource resource : resources){
                permissions.add(resource.getUrl());
            }
        }
        return permissions;
    }
}

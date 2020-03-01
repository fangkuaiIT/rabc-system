package com.lin.entity;

import com.yyfly.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 资源
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "imms_resource")
public class Resource extends BaseEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3273978566076387246L;

    /**
     * 无
     */
    public static final String TYPE_NONE = "0";
    /**
     * 菜单
     */
    public static final String TYPE_MENU = "1";
    /**
     * 功能
     */
    public static final String TYPE_FUNCTION = "2";

  private String path;

  private String url;

  private String pid;

  private String remark;

  private String resourceName;

    /**
     * 资源类型
     * <ul>
     *      <li> 0 - 无</li>
     *      <li> 1 - 菜单</li>
     *      <li> 2 - 功能</li>
     * </ul>
     */
  private String type;

  private String component;

  private String iconCls;

  private Boolean keepAlive;

  private Boolean requireAuth;

  private Boolean enabled;



}

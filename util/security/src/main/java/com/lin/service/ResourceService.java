package com.lin.service;


import com.lin.dto.AuthorityDTO;
import com.lin.dto.ResourceDTO;
import com.lin.entity.Resource;
import com.lin.util.entity.DTOConvert;
import com.yyfly.common.service.BaseService;

import java.util.Set;

/**
 * 资源 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface ResourceService extends BaseService<Resource>, DTOConvert<Resource, ResourceDTO> {

//      /**
//       * Find page list list data.
//       *
//       * @param searchCriteria the search criteria
//       * @return the list data
//       * @author : yangjunqing / 2019-01-08
//       */
//      ListData<Resource> findPageList(SearchCriteria searchCriteria);
//
//    /**
//     * 通过用户名查询菜单权限.
//     *
//     * @param username the username
//     * @return the list
//     * @author : yangjunqing / 2019-03-18
//     */
//    Set<Resource> findMenuByUsername(@NonNull String username);
//
//    /**
//     * 根据用户和菜单编号查询功能资源.
//     *
//     * @param pid the pid
//     * @return the list
//     * @author : yangjunqing / 2019-03-18
//     */
//    List<Resource> findPermissionByPidAndUsername(String pid, String username);



//    List<ResourceWithRoles>   getAllResourceWithRoles();



//    List<ResourceWithChildren> getAllResources();


    /**
     * 根据用户获取到其资源
     * @return
     */
   Set<AuthorityDTO> getResourcesByUser();

    /**
     * 获取url与角色的对应
     * @return
     */
    Set<Resource> getAllResourceWithRoles();

//    /**
//     * 根据url获取到对应角色
//     * @return
//     */
//   List<Role> getAllRolesWithResources(String url);

  }

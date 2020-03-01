package com.lin.dao;



import com.lin.entity.Resource;
import com.yyfly.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 资源 repository
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
//id默认给我传的就是string了
@Repository
public interface ResourceDao extends BaseRepository<Resource> {

//    /**
//     * 通过用户名查询菜单权限.
//     *
//     * @param username the username
//     * @return the list
//     * @author : yangjunqing / 2019-03-18
//     */
//    @Query(value = "select rs.* " +
//                " from imms_user u " +
//                "  left join imms_t_user_role ur on u.id = ur.user_id " +
//                "  left join imms_role r on r.id = ur.role_id " +
//                "  left join imms_t_role_resource rr on rr.role_id = r.id " +
//                "  left join imms_resource rs on rs.id = rr.resource_id " +
//                "where u.status <> 9999 " +
//                " and r.status <> 9999 " +
//                " and rs.status <> 9999 " +
//                " and rs.type = '1' " +
//                " and u.username = :username", nativeQuery = true)
//    Set<Resource> findMenuByUsername(@Param("username") String username);
//
//    /**
//     * 根据用户和菜单编号查询功能资源.
//     *
//     * @param pid the pid
//     * @return the list
//     * @author : yangjunqing / 2019-03-18
//     */
//    @Query(value = "select rs.* " +
//            " from imms_user u " +
//            "  left join imms_t_user_role ur on u.id = ur.user_id " +
//            "  left join imms_role r on r.id = ur.role_id " +
//            "  left join imms_t_role_resource rr on rr.role_id = r.id " +
//            "  left join imms_resource rs on rs.id = rr.resource_id " +
//            " where u.status <> 9999 " +
//            " and r.status <> 9999 " +
//            " and rs.status <> 9999 " +
//            " and rs.type = '2' " +
//            " and rs.pid = :pid " +
//            " and u.username = :username", nativeQuery = true)
//    List<Resource> findPermissionByPidAndUsername(@Param("pid") String pid, @Param("username") String username);


//    @Query(
//            value = "select m.*,r.id as rid,r.name as rname,r.nameZh as rnameZh " +
//                    "from imms_resource m,imms_t_role_resource mr,imms_role r " +
//                    "where m.id=mr.resource_id and mr.role_id=r.id " +
//                    "order by m.id" ,nativeQuery = true
//    )
//    List<ResourceWithRoles>   getAllResourceWithRoles();


//  @Query(
//    value = "select m1.id as id1,m1.resource_name as name1,m2.id as id2,m2.resource_name as name2,m3.id as id3,m3.resource_name as name3" +
//            " from imms_resource m1,imms_resource m2,imms_resource m3 " +
//            "where m1.id=m2.parent_id and m2.id=m3.parent_id and m3.enabled=true" +
//            " order by m1.id,m2.id,m3.id" ,nativeQuery = true
//  )
//    List<ResourceWithChildren> getAllResources();


//  @Query(
//          value = " select distinct m1.*,m2.id as id2,m2.component as component2,m2.enabled as enabled2,m2.iconCls as iconCls2,m2.keepAlive as keepAlive2,m2.resource_name as name2,m2.parent_id as parent_id2,m2.requireAuth as requireAuth2,m2.path as path2 " +
//                  "from imms_resource m1,imms_resource m2,imms_t_user_role ur,imms_t_role_resource rs " +
//                  "where m1.id=m2.parent_id " +
//                  "and ur.user_id= :userId " +
//                  "and ur.role_id = rs.role_id " +
//                  "and rs.resource_id=m2.id " +
//                  "and m2.enabled=true " +
//                  "order by m1.id,m2.id", nativeQuery = true
//  )
    /**
     * 根据securityContext上下文的用户id查询到里面的资源
     * 获取到子菜单

     * @author : fangkuaiIT
     */
    @Query(value = "select rs.* " +
            " from imms_user u " +
            "  left join imms_t_user_role ur on u.id = ur.user_id " +
            "  left join imms_role r on r.id = ur.role_id " +
            "  left join imms_t_role_resource rr on rr.role_id = r.id " +
            "  left join imms_resource rs on rs.id = rr.resource_id " +
            " where u.status <> 9999 " +
            " and r.status <> 9999 " +
            " and rs.status <> 9999 " +
            " and rs.type = '2' " +
            " and rs.pid = :pid " +
            " and u.id = :userId", nativeQuery = true)
  Set <Resource> getResourcesByPidAndUid(@Param("pid") String pid,@Param("userId")String userId);


    /**
     * 根据用户id查到其父资源
     * @param userId
     * @return
     */
    @Query(value = "select rs.* " +
            " from imms_user u " +
            "  left join imms_t_user_role ur on u.id = ur.user_id " +
            "  left join imms_role r on r.id = ur.role_id " +
            "  left join imms_t_role_resource rr on rr.role_id = r.id " +
            "  left join imms_resource rs on rs.id = rr.resource_id " +
            "where u.status <> 9999 " +
            " and r.status <> 9999 " +
            " and rs.status <> 9999 " +
            " and rs.type = '1' " +
            " and u.id = :userId", nativeQuery = true)
   Set<Resource> getResourcesByUid(@Param("userId") String userId);


  /**
   * 获取到url对应的角色表
   * @return
   */
  @Query(value = "select s.*   from imms_role r , imms_t_role_resource rs , imms_resource s where s.id=rs.resource_id and rs.role_id = r.id ",
    nativeQuery = true)
   Set<Resource> getAllResourceWithRoles();

//    /**
//     *根据url获取需要的权限表
//     * @return
//     */
//    @Query(value = "select  r.* from imms_role r , imms_t_role_resource rs , imms_resource s where s.id=rs.resource_id and rs.role_id = r.id and s.url = :url",
//            nativeQuery = true)
//  List<Role> getAllRolesWithResources(@Param("url") String url);
}

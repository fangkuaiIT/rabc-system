package com.lin.dao;



import com.lin.entity.User;
import com.yyfly.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 用户 repository
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Repository
public interface UserDao extends BaseRepository<User> {

    /**
     * 通过名称查询用户
     *
     * @param username the username
     * @return the user
     * @author : yangjunqing / 2019-01-08
     */
    @Query("select u from User u where u.username = :username and u.status <> 9999")
    User findByUsername(@Param("username") String username);

    /**
     * 用户名是否存在.
     *
     * @param username the username
     * @return the boolean
     * @author : yangjunqing / 2019-02-20
     */
    @Query(value = "select count(1) from imms_user u where u.username = :username and u.status <> 9999", nativeQuery = true)
    int countByUsername(String username);

    /**
     * 用户名是否存在(忽略id).
     *
     * @param username the username
     * @param id       the id
     * @return the boolean
     * @author : yangjunqing / 2019-02-20
     */
    @Query(value = "select count(1) from imms_user u where u.username = :username and u.id <> :id and u.status <> 9999", nativeQuery = true)
    int countByUsernameWithOutId(@Param("username") String username, @Param("id") String id);

    /**
     * 员工编号是否存在.
     *
     * @param employeeId the employee id
     * @return the boolean
     * @author : yangjunqing / 2019-02-21
     */
    @Query(value = "select count(1) from imms_user u where u.employee_id = :employeeId and u.status <> 9999", nativeQuery = true)
    int countByEmployeeId(String employeeId);

    /**
     * 员工编号是否存在(忽略id).
     *
     * @param employeeId the employee id
     * @param id         the id
     * @return the boolean
     * @author : yangjunqing / 2019-02-21
     */
    @Query(value = "select count(1) from imms_user u where u.employee_id = :employeeId and u.id <> :id and u.status <> 9999", nativeQuery = true)
    int countByEmployeeIdWithOutId(@Param("employeeId") String employeeId, @Param("id") String id);

    /**
     * 通过员工编号查询账号名称
     *
     * @param employeeId the employee id
     * @return the string
     * @author : yangjunqing / 2019-01-19
     */
    @Query("select u.username from User u where u.employeeId = :employeeId and u.status <> 9999")
    String getUsername(@Param("employeeId") String employeeId);

    /**
     * 通过名称查询员工名称
     *
     * @param username the username
     * @return the string
     * @author : yangjunqing / 2019-01-18
     */
    @Query(value = "select e.name  " +
                    " from imms_user u " +
                    "  left join imms_employee e on e.id = u.employee_id " +
                    "where u.username = :username " +
                    "  and u.status <> 9999", nativeQuery = true)
    String getEmployeeNameByUsername(@Param("username") String username);

    /**
     * 通过用户名查询员工邮箱.
     *
     * @param username the username
     * @return the string
     * @author : yangjunqing / 2019-03-11
     */
    @Query(value = "select e.email " +
            " from imms_user u " +
            " left join imms_employee e on e.id = u.employee_id " +
            "where u.username = :username", nativeQuery = true)
    String getEmployeeMailByUsername(@Param("username") String username);

    /**
     * 获取所有账号.
     *
     * @return the list
     * @author : yangjunqing / 2019-03-02
     */
    @Query("select u.username from User u where u.status <> 9999")
    List<String> findAllUsernames();


    /**
     * 通过快速查询值查询ids
     * (员工名称)
     *
     * @param fastSearch the fast search
     * @return the list
     * @author : yangjunqing / 2019-01-19
     */
    @Query(value = "select u.id " +
                    " from imms_user u  " +
                    "  left join imms_employee e on e.id = u.employee_id " +
                    "  left join imms_t_user_role ur on ur.user_id = u.id " +
                    "where e.name like CONCAT('%', ?1, '%') " +
                    "   or ur.role_id like CONCAT('%', ?1, '%')", nativeQuery = true)
    List<String> findIdsByFastSearch(String fastSearch);


    /**
     * 根据角色编号查询用户编号.
     *
     * @param roleId the role id
     * @return the list
     * @author : yangjunqing / 2019-03-13
     */
    @Query(value = "select u.id " +
                " from imms_user u " +
                "  left join imms_t_user_role ur on ur.user_id = u.id " +
                " where ur.role_id like CONCAT('%', ?1, '%')", nativeQuery = true)
    List<String> findIdsByRoleId(@Param("roleId") String roleId);

    /**
     * 根据员工名称查询用户编号.
     *
     * @param employeeName the employee name
     * @return the list
     * @author : yangjunqing / 2019-03-13
     */
    @Query(value = "select u.id " +
                    " from imms_user u " +
                    " left join imms_employee e on e.id = u.employee_id " +
                    "where e.name like CONCAT('%', ?1, '%')", nativeQuery = true)
    List<String> findIdsByEmployeeName(String employeeName);

    /**
     * 修改密码
     *
     * @param username the username
     * @param password the password
     * @return the user
     * @author : yangjunqing / 2019-01-09
     */
    @Modifying
    @Query("update User u set u.password = :password where u.username = :username and u.status <> 9999")
    void modifyPassword(@Param("username") String username, @Param("password") String password);


    /**
     * 批量更新状态.
     *
     * @param ids    the ids
     * @param status the status
     * @return the integer
     * @author : yangjunqing / 2019-03-15
     */
    @Modifying
    @Query("update User u set u.status = :status where u.id in :ids")
    Integer updateStatusByIds(@Param("ids") Set<String> ids, @Param("status") int status);
}

package com.lin.service;


import com.lin.dto.AuthorityDTO;
import com.lin.dto.UserDTO;
import com.lin.entity.User;
import com.lin.util.entity.DTOConvert;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.yyfly.common.service.BaseService;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

/**
 * 用户 service
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
public interface UserService extends BaseService<User>, DTOConvert<User, UserDTO> {

    /**
     * Find page list list data.
     *
     * @param searchCriteria the search criteria
     * @return the list data
     * @author : yangjunqing / 2019-01-08
     */
    ListData<User> findPageList(SearchCriteria searchCriteria);

    /**
     * 通过名称查询用户
     *
     * @param username the username
     * @return the user
     * @author : yangjunqing / 2019-01-08
     */
    User findByUsername(String username);

    /**
     * 用户名是否存在.
     *
     * @param username the username
     * @return the boolean
     * @author : yangjunqing / 2019-02-20
     */
    boolean existsByUsername(String username);

    /**
     * 用户名是否存在(忽略id).
     *
     * @param username the username
     * @param id       the id
     * @return the boolean
     * @author : yangjunqing / 2019-02-20
     */
    boolean existsByUsernameWithOutId(String username, String id);

    /**
     * 员工编号是否存在.
     *
     * @param employeeId the employee id
     * @return the boolean
     * @author : yangjunqing / 2019-02-21
     */
    boolean existsByEmployeeId(String employeeId);

    /**
     * 员工编号是否存在(忽略id).
     *
     * @param employeeId the employee id
     * @param id         the id
     * @return the boolean
     * @author : yangjunqing / 2019-02-21
     */
    boolean existsByEmployeeIdWithOutId(String employeeId, String id);

    /**
     * 通过员工编号查询账号名称
     *
     * @param employeeId the employee id
     * @return the string
     * @author : yangjunqing / 2019-01-19
     */
    String getUsername(String employeeId);

    /**
     * 通过名称查询员工名称
     *
     * @param username the username
     * @return the string
     * @author : yangjunqing / 2019-01-18
     */
    String getEmployeeNameByUsername(String username);

    /**
     * 通过用户名查询员工邮箱.
     *
     * @param username the username
     * @return the string
     * @author : yangjunqing / 2019-03-11
     */
    String getEmployeeMailByUsername(String username);

    /**
     * 获取所有账号.
     *
     * @return the list
     * @author : yangjunqing / 2019-03-02
     */
    List<String> findAllUsernames();

    /**
     * 通过快速查询值查询ids
     *
     * @param fastSearch the fast search
     * @return the list
     * @author : yangjunqing / 2019-01-19
     */
    List<String> findIdsByFastSearch(String fastSearch);

    /**
     * 根据角色编号查询用户编号.
     *
     * @param roleId the role id
     * @return the list
     * @author : yangjunqing / 2019-03-13
     */
    List<String> findIdsByRoleId(String roleId);

    /**
     * 根据员工名称查询用户编号.
     *
     * @param employeeName the employee name
     * @return the list
     * @author : yangjunqing / 2019-03-13
     */
    List<String> findIdsByEmployeeName(String employeeName);

    /**
     * 修改密码
     *
     * @param username       the 用户名称
     * @param originPassword the 旧密码
     * @param newPassword    the 新密码
     * @param repeat         the 新密码
     * @author : yangjunqing / 2019-01-09
     */
    void modifyPassword(String username, String originPassword, String newPassword, String repeat);

    /**
     * 用户登录
     *
     * @param username the username
     * @param password the password
     * @return the string
     * @author : yangjunqing / 2019-01-09
     */
    String login(String username, String password);

    /**
     * 刷新令牌
     *
     * @param oldToken the old token
     * @return the string
     * @author : yangjunqing / 2019-01-10
     */
    String refreshToken(String oldToken);

    /**
     * 批量更新状态.
     *
     * @param ids    the ids
     * @param status the status
     * @return the boolean
     * @author : yangjunqing / 2019-03-15
     */
    boolean updateStatusByIds(Set<String> ids, int status);

//    /**
//     * 获取用户权限信息.
//     *
//     * @param username the username
//     * @return the set
//     * @author : yangjunqing / 2019-03-18
//     */
//    Set<AuthorityDTO> getUserAuthority(@NonNull String username);
  }

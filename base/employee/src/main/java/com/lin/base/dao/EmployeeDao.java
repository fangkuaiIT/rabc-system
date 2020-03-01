package com.lin.base.dao;


import com.lin.base.entity.Employee;
import com.yyfly.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 员工 repository
 *
 * @author : fang / fang
 * @version : 1.0
 */
@Repository
public interface EmployeeDao extends BaseRepository<Employee> {

    /**
     * 通过用户名查询员工信息.
     *
     * @param username the username
     * @return the employee
     * @author : fang / 2019-02-16
     */
    @Query(value = "select e.* " +
                   " from imms_employee e " +
                   "  left join imms_user u on e.id = u.employee_id " +
                   "where u.username = :username " +
                    " and u.status <> 9999", nativeQuery = true)
    Employee getByUsername(@Param("username") String username);


    /**
     * 批量更新状态.
     *
     * @param ids    the ids
     * @param status the status
     * @return the integer
     * @author : fang / 2019-03-15
     */
    @Modifying
    @Query("update Employee e set e.status = :status where e.id in :ids")
    Integer updateStatusByIds(@Param("ids") Set<String> ids, @Param("status") int status);

    /**
     * 通过快速查询值查询ids
     * (员工名称)
     *
     * @param fastSearch the fast search
     * @return the list
     * @author : fang / 2019-01-19
     */
    @Query(value = "select e.id " +
            " from imms_employee e " +
            "   left join imms_department d on d.id = e.department_id " +
            "where d.name like CONCAT('%', ?1, '%')", nativeQuery = true)
    List<String> findIdsByFastSearch(String fastSearch);
}

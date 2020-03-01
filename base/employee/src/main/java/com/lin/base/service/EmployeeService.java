package com.lin.base.service;



import com.lin.base.dto.EmployeeDTO;
import com.lin.base.entity.Employee;
import com.lin.util.entity.DTOConvert;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.yyfly.common.service.BaseService;

import java.util.List;
import java.util.Set;



/**
 * 员工 service
 *
 * @author : fang
 * @version : 1.0
 */
public interface EmployeeService extends BaseService<Employee>, DTOConvert<Employee, EmployeeDTO> {

      /**
       * Find page list list data.
       *
       * @param searchCriteria the search criteria
       * @return the list data
       * @author : fang / 2019-02-15
       */
      ListData<Employee> findPageList(SearchCriteria searchCriteria);

    /**
     * 通过用户名查询员工信息.
     *
     * @param username the username
     * @return the employee
     * @author : fang / 2019-02-16
     */
    Employee getByUsername(String username);

    /**
     * 批量更新状态.
     *
     * @param ids    the ids
     * @param status the status
     * @return the integer
     * @author : fang / 2019-03-15
     */
    boolean updateStatusByIds(Set<String> ids, int status);

    /**
     * 通过快速查询值查询ids
     * (员工名称)
     *
     * @param fastSearch the fast search
     * @return the list
     * @author : fang / 2019-01-19
     */
    List<String> findIdsByFastSearch(String fastSearch);
  }

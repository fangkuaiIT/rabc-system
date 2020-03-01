package com.lin.base.service.impl;




import com.lin.base.dao.EmployeeDao;
import com.lin.base.dto.EmployeeDTO;

import com.lin.base.entity.Employee;

import com.lin.base.service.EmployeeService;
import com.lin.entity.Department;
import com.lin.service.DepartmentService;
import com.lin.service.UserService;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;

import com.lin.util.utils.TableUtils;
import com.lin.util.utils.ToolUtils;
import com.yyfly.common.repository.BaseRepository;
import com.yyfly.common.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 员工 service impl
 *
 * @author : fang / fang
 * @version : 1.0
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {


    /**
     * Employee dao
     */
    @Autowired
    private EmployeeDao employeeDao;
    /**
     * Department service
     */
    @Autowired
    private DepartmentService departmentService;
    /**
     * User service
     */
    @Autowired
    private UserService userService;

    /**
     * 表名
     */
    private final String tableName = TableUtils.getTableName(Employee.class);


    @Override
    protected BaseRepository<Employee> getDao() {
        return employeeDao;
    }



    @Override
    public ListData<Employee> findPageList(SearchCriteria searchCriteria) {
        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
    }

    @Override
    public Employee getByUsername(String username) {
        return employeeDao.getByUsername(username);
    }

    @Override
    public boolean updateStatusByIds(Set<String> ids, int status) {
        return employeeDao.updateStatusByIds(ids, status) == 0 ? false : true;
    }

    @Override
    public List<String> findIdsByFastSearch(String fastSearch) {
        return employeeDao.findIdsByFastSearch(fastSearch);
    }

    @Override
    public EmployeeDTO toDTO(Employee employee) {
        if (employee == null){
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        dto.setEnabled(ToolUtils.statusToBoolean(dto.getStatus()));

        Department department = departmentService.findById(dto.getDepartmentId());
        if (department != null){
            dto.setDepartmentName(department.getName());
        }

        dto.setUsername(userService.getUsername(dto.getId()));

       
        return dto;
    }

    /**
     *将实体类拷贝到DTO类上面
     * @param dto
     * @return
     */
    @Override
    public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        employee.setStatus(ToolUtils.booleanToStatus(dto.isEnabled()));
        return employee;
    }

    @Override
    public List<EmployeeDTO> toDTOs(List<Employee> employees) {
        List<EmployeeDTO> dtos = new ArrayList<>();
        if (employees != null && employees.size() > 0){
            employees.forEach(employee -> {
                EmployeeDTO dto = toDTO(employee);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<Employee> toEntitys(List<EmployeeDTO> employeeDTOs) {
        List<Employee> employees = new ArrayList<>();
        if (employeeDTOs != null && employeeDTOs.size() > 0){
            employeeDTOs.forEach(employeeDTO -> {
                Employee employee = toEntity(employeeDTO);
                employees.add(employee);
            });
        }
        return employees;
    }
}

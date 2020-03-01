package com.lin.base.web;



import com.lin.base.dto.EmployeeDTO;
import com.lin.base.entity.Employee;
import com.lin.base.service.EmployeeService;
import com.lin.base.validator.EmployeeDTOValidator;
import com.lin.util.entity.BaseInDTO;
import com.lin.util.entity.FastSearchParam;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.web.BaseController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 员工 controller
 *
 * @author : fang 
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "employee")

public class EmployeeController extends BaseController {

    /**
     * Employee dto validator
     */
    @Autowired
    private EmployeeDTOValidator employeeDTOValidator;
    /**
     * Employee service
     */
    @Autowired
    private EmployeeService employeeService;
  


    /**
     * Validator.
     *
     * @param binder the binder
     * @author : fang / 2019-02-15
     */
    @InitBinder
    public void validator(WebDataBinder binder){
        binder.addValidators(employeeDTOValidator);
    }


    /**
     * 新增
     *
     * @param dto the dto
     * @return the response data
     * @author : fang / 2019-02-15
     */
    @PostMapping
   
   
    public ResponseData add(@RequestBody @Valid @ApiIgnore EmployeeDTO dto){
        Employee employee = employeeService.toEntity(dto);
  
        employee = employeeService.save(employee);
        return ResponseData.success(employeeService.toDTO(employee));
    }

    /**
     * 更新
     *
     * @param dto the dto
     * @return the response data
     * @author : fang / 2019-02-15
     */
    @PutMapping
  
    public ResponseData update(@RequestBody @Valid @ApiIgnore EmployeeDTO dto){
        Employee employee = employeeService.toEntity(dto);
        
        employee = employeeService.save(employee);
        return ResponseData.success(employeeService.toDTO(employee));
    }


 


    /**
     * 批量更新状态.
     *
     * @param dto the dto
     * @return the response data
     * @author : fang / 2019-03-15
     */
 
    @PutMapping("status")
    public ResponseData updateStatusByIds(@RequestBody BaseInDTO dto){
        Set<String> ids = dto.getIds();
        if (null == ids || ids.size() == 0){
            throw new GlobalException("至少选择一个id!");
        }
        if (null == dto.getEnabled()){
            throw new  GlobalException("请选择是否启用!");
        }

        return ResponseData.success(employeeService.updateStatusByIds(ids, dto.getEnabled() == true ? BaseEntity.NORMAL : BaseEntity.DISABLE));
    }


    /**
     * 通过编号查询员工信息
     *
     * @param id the id
     * @return the response data
     * @author : fang / 2019-02-15
     */
    @GetMapping("{id}")
    public ResponseData findById(@PathVariable("id") String id){
        Employee employee = employeeService.findById(id);
        return ResponseData.success(employeeService.toDTO(employee));
    }

    /**
    * 查询所有.
    *
    * @param searchCriteria the search criteria
    * @return the response data
    * @author : fang / 2019-02-15
    */
    @PostMapping("list/all")
    public ResponseData findAll(@RequestBody @Nullable SearchCriteria searchCriteria){
        if (StringUtils.isNotEmpty(searchCriteria.getFastSearch())){
            searchCriteria.setFastSearchParams(initFastSearchParams(searchCriteria.getFastSearch()));
        }

        List<Employee> employees = employeeService.findAll(searchCriteria.buildSearchParams(), searchCriteria.buildSort());
        return ResponseData.success(employeeService.toDTOs(employees));
    }

    /**
     * 分页查询
     *
     * @param searchCriteria the search criteria
     * @return the response data
     * @author : fang / 2019-02-15
     */
    @PostMapping("list")
    public ResponseData findList(@RequestBody @Nullable SearchCriteria searchCriteria){
        if (StringUtils.isNotEmpty(searchCriteria.getFastSearch())){
            searchCriteria.setFastSearchParams(initFastSearchParams(searchCriteria.getFastSearch()));
        }

        ListData<Employee> employees = employeeService.findPageList(searchCriteria);
        employees.setData(employeeService.toDTOs(employees.getOriginData()));
        return ResponseData.success(employees);
    }

    /**
     * 初始化快速查询条件.
     *
     * @param fastSearch the fast search
     * @return the list
     * @author : fang / 2019-02-16
     */
    private List<FastSearchParam> initFastSearchParams(String fastSearch){
        List<FastSearchParam> fastSearchParams = new ArrayList<>(1);
        fastSearchParams.add(new FastSearchParam("name"));
        fastSearchParams.add(new FastSearchParam("id", employeeService.findIdsByFastSearch(fastSearch)));
        return fastSearchParams;
    }
}

package com.lin.web;



import com.lin.dto.DepartmentDTO;
import com.lin.entity.Department;
import com.lin.service.DepartmentService;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.lin.validator.DepartmentDTOValidator;
import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.web.BaseController;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * 部门 controller
 *
 * @author : fang / fang
 */
@RestController
@RequestMapping(value = "department")

public class DepartmentController extends BaseController {

    /**
     * Department dto validator
     */
    @Autowired
    private DepartmentDTOValidator departmentDTOValidator;
    /**
     * Department service
     */
    @Autowired
    private DepartmentService departmentService;



    /**
     * Validator.
     *
     * @param binder the binder
     * @author : fang / 2019-02-15
     */
    @InitBinder
    public void validator(WebDataBinder binder){
        binder.addValidators(departmentDTOValidator);
    }


    /**
     * 新增
     *
     * @param dto the dto
     * @return the response data
     * @author : fang / 2019-02-15
     */
    @PostMapping


    public ResponseData add(@RequestBody @Valid DepartmentDTO dto){
        Department department = departmentService.toEntity(dto);


        return ResponseData.success(departmentService.toDTO(department));

    }

    /**
     * 更新
     *
     * @param dto the dto
     * @return the response data
     * @author : fang / 2019-02-15
     */
    @PutMapping

    public ResponseData update(@RequestBody @Valid @ApiIgnore DepartmentDTO dto){
        Department department = departmentService.toEntity(dto);


        return ResponseData.success(departmentService.toDTO(department));
    }


  

    /**
     * 通过编号查询部门信息
     *
     * @param id the id
     * @return the response data
     * @author : fang / 2019-02-15
     */
    @GetMapping("{id}")

    public ResponseData findById(@PathVariable("id") String id){
        Department department = departmentService.findById(id);
        return ResponseData.success(departmentService.toDTO(department));
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
        List<Department> departments = departmentService.findAll(searchCriteria.buildSearchParams(), searchCriteria.buildSort());
        return ResponseData.success(departmentService.toDTOs(departments));
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
        ListData<Department> departments = departmentService.findPageList(searchCriteria);
        departments.setData(departmentService.toDTOs(departments.getOriginData()));
        return ResponseData.success(departments);
    }
}

package com.lin.web;


import com.lin.dto.RoleDTO;
import com.lin.entity.Role;
import com.lin.service.RoleService;
import com.lin.util.entity.BaseInDTO;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.lin.validator.RoleDTOValidator;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * 角色 controller
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "role")
@Api(value = "角色管理", tags = "角色管理API")
public class RoleController extends BaseController {

    /**
     * Role dto validator
     */
    @Autowired
    private RoleDTOValidator roleDTOValidator;
    /**
     * Role service
     */
    @Autowired
    private RoleService roleService;


    /**
     * Validator.
     *
     * @param binder the binder
     * @author : yangjunqing / 2019-01-08
     */
    @InitBinder
    public void validator(WebDataBinder binder){
        binder.setValidator(roleDTOValidator);
    }


    /**
     * 新增
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @PostMapping
    @ApiOperation(value = "新增角色", notes = "新增角色API", response = RoleDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "groupCode", value = "角色组编码", required = false, dataType = "string"),
            @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "boolean"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, dataType = "string"),
            @ApiImplicitParam(name = "resourceIds[]", value = "资源编号数组", required = false, dataType = "string", allowMultiple = true)
    })
    public ResponseData add(@RequestBody @Valid @ApiIgnore RoleDTO dto){
        if (roleService.existsByRoleName(dto.getRoleName())){
            throw new GlobalException("角色名称已存在，不允许重复！");
        }

        Role role = roleService.toEntity(dto);
        role = roleService.save(role);
        return ResponseData.success(roleService.toDTO(role));
    }

    /**
     * 更新
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @PutMapping
    @ApiOperation(value = "更新角色", notes = "更新角色API", response = RoleDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "id", value = "角色编号", required = true, dataType = "string"),
            @ApiImplicitParam(name = "groupCode", value = "角色组编码", required = false, dataType = "string"),
            @ApiImplicitParam(name = "version", value = "版本号", required = true, dataType = "integer"),
            @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "boolean"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, dataType = "string"),
            @ApiImplicitParam(name = "resourceIds[]", value = "资源编号数组", required = false, dataType = "string", allowMultiple = true)
    })
    public ResponseData update(@RequestBody @Valid @ApiIgnore RoleDTO dto){
        if (roleService.existsByRoleNameWithOutId(dto.getRoleName(), dto.getId())){
            throw new GlobalException("角色名称已存在，不允许重复！");
        }

        Role role = roleService.toEntity(dto);
        role = roleService.save(role);
        return ResponseData.success(roleService.toDTO(role));
    }


    /**
     * 删除
     *
     * @param ids the ids
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @ApiOperation(value = "删除角色", notes = "删除角色API")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "ids", value = "角色编号数组", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public ResponseData deleteByIds(@RequestBody List<String> ids){
        List<Role> roles = roleService.findAll(ids);
        roleService.deleteAll(roles);
        return ResponseData.success();
    }

    /**
     * 批量更新状态.
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-03-15
     */
    @ApiOperation(value = "批量更新状态", notes = "批量更新状态API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    @PutMapping("status")
    public ResponseData updateStatusByIds(@RequestBody BaseInDTO dto){
        Set<String> ids = dto.getIds();
        if (null == ids || ids.size() == 0){
            throw new GlobalException("至少选择一个id!");
        }
        if (null == dto.getEnabled()){
            throw new  GlobalException("请选择是否启用!");
        }

        return ResponseData.success(roleService.updateStatusByIds(ids, dto.getEnabled() == true ? BaseEntity.NORMAL : BaseEntity.DISABLE));
    }

    /**
     * 通过编号查询角色信息
     *
     * @param id the id
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过编号查询角色信息", notes = "通过编号查询角色信息API", response = RoleDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "角色编号", required = true, dataType = "string")
    })
    public ResponseData findById(@PathVariable("id") String id){
        Role role = roleService.findById(id);
        return ResponseData.success(roleService.toDTO(role));
    }

    /**
     * 查询所有角色信息
     *
     * @return the response data
     * @author : yangjunqing / 2019-01-11
     */
    @PostMapping("list/all")
    @ApiOperation(value = "查询所有角色信息", notes = "查询所有角色信息API", response = RoleDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findAll(@RequestBody @Nullable SearchCriteria searchCriteria){
        List<Role> roles = roleService.findAll(searchCriteria.buildSearchParams());
        return ResponseData.success(roleService.toDTOs(roles));
    }

    /**
     * 分页查询
     *
     * @param searchCriteria the search criteria
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @PostMapping("list")
    @ApiOperation(value = "分页查询", notes = "分页查询API", response = ListData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findList(@RequestBody @Nullable SearchCriteria searchCriteria){
        ListData<Role> roles = roleService.findPageList(searchCriteria);
        roles.setData(roleService.toDTOs(roles.getOriginData()));
        return ResponseData.success(roles);
    }
}

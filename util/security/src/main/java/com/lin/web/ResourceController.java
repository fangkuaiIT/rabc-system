package com.lin.web;


import com.lin.dto.AuthorityDTO;
import com.lin.dto.ResourceDTO;
import com.lin.entity.Resource;
import com.lin.service.ResourceService;
import com.lin.util.entity.SearchCriteria;
import com.lin.validator.ResourceDTOValidator;
import com.yyfly.common.entity.ResponseData;
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
 * 资源 controller
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "resource")
@Api(value = "资源管理", tags = "资源管理API")
public class ResourceController extends BaseController {

    /**
     * Resource dto validator
     */
    @Autowired
    private ResourceDTOValidator resourceDTOValidator;
    /**
     * Resource service
     */
    @Autowired
    private ResourceService resourceService;


    /**
     * Validator.
     *
     * @param binder the binder
     * @author : yangjunqing / 2019-01-08
     */
    @InitBinder
    public void validator(WebDataBinder binder){
        binder.setValidator(resourceDTOValidator);
    }


    /**
     * 新增
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @PostMapping
    @ApiOperation(value = "新增资源", notes = "新增资源API", response = ResourceDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "resourceName", value = "资源名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "type", value = "资源类型(0 - 无, 1 - 菜单, 2 - 功能 )", required = true, dataType = "string"),
            @ApiImplicitParam(name = "permission", value = "权限标识", required = true, dataType = "string"),
            @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "boolean"),
            @ApiImplicitParam(name = "path", value = "path", required = false, dataType = "string"),
            @ApiImplicitParam(name = "pid", value = "上级", required = false, dataType = "string"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, dataType = "string")
    })
    public ResponseData add(@RequestBody @Valid @ApiIgnore ResourceDTO dto){
        Resource resource = resourceService.toEntity(dto);
        resource = resourceService.save(resource);
        return ResponseData.success(resourceService.toDTO(resource));
    }

    /**
     * 更新
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @PutMapping
    @ApiOperation(value = "更新资源", notes = "更新资源API", response = ResourceDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "资源编号", required = true, dataType = "string"),
        @ApiImplicitParam(name = "version", value = "版本号", required = true, dataType = "integer"),
        @ApiImplicitParam(name = "resourceName", value = "资源名称", required = true, dataType = "string"),
        @ApiImplicitParam(name = "type", value = "资源类型(0 - 无, 1 - 菜单, 2 - 功能 )", required = true, dataType = "string"),
        @ApiImplicitParam(name = "permission", value = "权限标识", required = true, dataType = "string"),
        @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "boolean"),
        @ApiImplicitParam(name = "path", value = "path", required = false, dataType = "string"),
        @ApiImplicitParam(name = "pid", value = "上级", required = false, dataType = "string"),
        @ApiImplicitParam(name = "remark", value = "备注", required = false, dataType = "string")
    })
    public ResponseData update(@RequestBody @Valid @ApiIgnore ResourceDTO dto){
        Resource resource = resourceService.toEntity(dto);
        resource = resourceService.save(resource);
        return ResponseData.success(resourceService.toDTO(resource));
    }

    /**
     * 删除
     *
     * @param ids the ids
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @ApiOperation(value = "删除资源", notes = "删除资源API")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "ids", value = "资源编号数组", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public ResponseData deleteByIds(@RequestBody List<String> ids){
        List<Resource> resources = resourceService.findAll(ids);
        resourceService.deleteAll(resources);
        return ResponseData.success();
    }

    /**
     * 通过编号查询资源信息
     *
     * @param id the id
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过编号查询资源信息", notes = "通过编号查询资源信息API", response = ResourceDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "资源编号", required = true, dataType = "string")
    })
    public ResponseData findById(@PathVariable("id") String id){
        Resource resource = resourceService.findById(id);
        return ResponseData.success(resourceService.toDTO(resource));
    }

    /**
     * 查询所有资源信息
     *
     * @return the response data
     * @author : yangjunqing / 2019-01-11
     */
    @PostMapping("list/all")
    @ApiOperation(value = "查询所有资源信息", notes = "查询所有资源信息API", response = ResourceDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findAll(@RequestBody @Nullable SearchCriteria searchCriteria){
        List<Resource> resources = resourceService.findAll(searchCriteria.buildSearchParams());
        return ResponseData.success(resourceService.toDTOs(resources));
    }

//    /**
//     * 分页查询
//     *
//     * @param searchCriteria the search criteria
//     * @return the response data
//     * @author : yangjunqing / 2019-01-08
//     */
//    @PostMapping("list")
//    @ApiOperation(value = "分页查询", notes = "分页查询API", response = ListData.class)
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
//    })
//    public ResponseData findList(@RequestBody @Nullable SearchCriteria searchCriteria){
//        ListData<Resource> resources = resourceService.findPageList(searchCriteria);
//        resources.setData(resourceService.toDTOs(resources.getOriginData()));
//        return ResponseData.success(resources);
//    }

    @GetMapping("sys/menu")
    public Set<AuthorityDTO> getResourceByUserId(){
        return resourceService.getResourcesByUser();
    }

}

package com.lin.web;




import com.lin.dto.UserDTO;
import com.lin.dto.UserModifyPasswordDTO;
import com.lin.entity.User;
import com.lin.service.UserService;
import com.lin.util.entity.*;
import com.lin.util.utils.ToolUtils;
import com.lin.validator.UserDTOValidator;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.web.BaseController;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户 controller
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@RestController
@RequestMapping(value = "user")
@Api(value = "用户管理", tags = "用户管理API")
@ApiResponses({
        @ApiResponse(code = -1, message = "不符合条件"),
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "拒绝访问"),
        @ApiResponse(code = 404, message = "请求未找到"),
        @ApiResponse(code = 406, message = "token过期或者token无效"),
        @ApiResponse(code = 500, message = "服务器出错"),
})
public class UserController extends BaseController {

    /**
     * User dto validator
     */
    @Autowired
    private UserDTOValidator userDTOValidator;
    /**
     * User service
     */
    @Autowired
    private UserService userService;

    /**
     * 快速查询集合
     */
    private List<FastSearchParam> fastSearchParams;


    /**
     * Validator.
     *
     * @param binder the binder
     * @author : yangjunqing / 2019-01-08
     */
    @InitBinder
    public void validator(WebDataBinder binder){
        binder.setValidator(userDTOValidator);
    }


    /**
     * 新增
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('/department:show')")
    @ApiOperation(value = "新增用户", notes = "新增用户API", response = UserDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string"),
            @ApiImplicitParam(name = "repeat", value = "确认密码", required = true, dataType = "string"),
            @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "boolean"),
            @ApiImplicitParam(name = "employeeId", value = "员工编号", required = false, dataType = "string"),
            @ApiImplicitParam(name = "roleIds", value = "角色编号数组", required = false, dataType = "string", allowMultiple = true)
    })
    public ResponseData add(@RequestBody @Valid @ApiIgnore UserDTO dto){
        if (userService.existsByUsername(dto.getUsername())){
            throw new GlobalException("用户名已存在，不允许重复!");
        }
        if (StringUtils.isNotEmpty(dto.getEmployeeId())){
            if (userService.existsByEmployeeId(dto.getEmployeeId())){
                throw new GlobalException("该员工已分配账号，不允许重复!");
            }
        }

        User user = userService.toEntity(dto);
        user = userService.save(user);
        return ResponseData.success(userService.toDTO(user));
    }

//    /**
//     * 用户登录
//     *
//     * @param dto the dto
//     * @return the string
//     * @author : yangjunqing / 2019-01-09
//     */
//    @ApiOperation(value = "用户登录", notes = "用户登陆API", responseReference = "token")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string"),
//            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string")
//    })
//    @PostMapping(value = "login", consumes = "application/json")
//    public ResponseData login(@RequestBody @ApiIgnore  UserDTO dto){
//        String token = userService.login(dto.getUsername(), dto.getPassword());
//
//        User user = userService.findByUsername(dto.getUsername());
//        dto = userService.toDTO(user);
//
//        dto.setToken(token);
//        return ResponseData.success(dto);
//    }


    /**
     * 刷新令牌
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-01-10
     */
    @ApiOperation(value = "刷新令牌", notes = "刷新令牌API", responseReference = "newToken")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "token", value = "旧token", required = true, dataType = "string")
    })
    @PostMapping("token/refresh")
    public ResponseData refreshToken(@RequestBody @ApiIgnore UserDTO dto){
        return ResponseData.success(userService.refreshToken(dto.getToken()));
    }


    /**
     * 更新
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @PutMapping
    @ApiOperation(value = "更新用户", notes = "更新用户API", response = UserDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "用户编号", required = true, dataType = "string"),
        @ApiImplicitParam(name = "version", value = "版本号", required = true, dataType = "integer"),
        @ApiImplicitParam(name = "enabled", value = "是否启用", required = true, dataType = "boolean"),
        @ApiImplicitParam(name = "employeeId", value = "员工编号", required = false, dataType = "string"),
        @ApiImplicitParam(name = "roleIds", value = "角色编号数组", required = false, dataType = "string", allowMultiple = true)
    })
    public ResponseData update(@RequestBody @Valid @ApiIgnore UserDTO dto){
        if (userService.existsByUsernameWithOutId(dto.getUsername(), dto.getId())){
            throw new GlobalException("用户名已存在，不允许重复!");
        }
        if (StringUtils.isNotEmpty(dto.getEmployeeId())){
            if (userService.existsByEmployeeIdWithOutId(dto.getEmployeeId(), dto.getId())){
                throw new GlobalException("该员工已分配账号，不允许重复!");
            }
        }

        User user = userService.toEntity(dto);
        user = userService.save(user);
        return ResponseData.success(userService.toDTO(user));
    }


    /**
     * 更新密码
     *
     * @param dto the dto
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @PutMapping("password/modify")
    @ApiOperation(value = "更新密码", notes = "更新密码API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "originPassword", value = "旧密码", required = true, dataType = "string"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "string"),
            @ApiImplicitParam(name = "repeat", value = "重复新密码", required = true, dataType = "string")
    })
    public ResponseData modifyPassword(@RequestBody @ApiIgnore UserModifyPasswordDTO dto){
        userService.modifyPassword(dto.getUsername(), dto.getOriginPassword(), dto.getNewPassword(), dto.getRepeat());
        return ResponseData.success();
    }


    /**
     * 删除
     *
     * @param ids the ids
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @ApiOperation(value = "删除用户", notes = "删除用户API")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "ids", value = "用户编号数组", required = true, dataType = "string", allowMultiple = true)
    })
    @DeleteMapping
    public ResponseData deleteByIds(@RequestBody List<String> ids){
        List<User> users = userService.findAll(ids);
        userService.deleteAll(users);
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

        return ResponseData.success(userService.updateStatusByIds(ids, dto.getEnabled() == true ? BaseEntity.NORMAL : BaseEntity.DISABLE));
    }


    /**
     * 通过编号查询用户信息
     *
     * @param id the id
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @GetMapping("{id}")
    @ApiOperation(value = "通过编号查询用户信息", notes = "通过编号查询用户信息API", response = UserDTO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string"),
        @ApiImplicitParam(name = "id", value = "用户编号", required = true, dataType = "string")
    })
    public ResponseData findById(@PathVariable("id") String id){
        User user = userService.findById(id);
        return ResponseData.success(userService.toDTO(user));
    }


    /**
     * 查询所有用户信息
     *
     * @param searchCriteria the search criteria
     * @return the response data
     * @author : yangjunqing / 2019-01-11
     */
    @PostMapping("list/all")
    @ApiOperation(value = "查询所有用户信息", response = UserDTO.class,
            notes = "分页查询支持说明(时间参数格式 yyyy-MM-dd|yyyy-MM-dd HH:mm:ss):  \n" +
                    "  1.快速查询:  \n" +
                    "    用户名称,员工名称  \n" +
                    "  2.条件查询  \n" +
                    "    status -> 用户状态(0-启用,1-未启用)  \n" +
                    "    roleId -> 角色编号  \n" +
                    "    employeeName -> 员工名称  \n" +
                    "    username -> 用户名称  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findAll(@RequestBody @Nullable SearchCriteria searchCriteria){

        List<SearchParam> searchParams = searchCriteria.getSearchParams();
        if (searchParams != null && searchParams.size() > 0){
            for (int n = 0; n < searchParams.size(); n++){
                SearchParam searchParam = searchParams.get(n);
                String fieldName = searchParam.getFieldName();

                switch (fieldName){
                    case "roleId":
                        String roleId = (String) searchParam.getValue();
                        if (StringUtils.isNotEmpty(roleId)){
                            List<String> ids = userService.findIdsByRoleId(roleId);
                            buildUnusualSearchParams(searchParam, ids);
                        } else{
                            searchParam.setFieldName(null);
                        }
                        break;
                    case "employeeName":
                        String employeeName = (String) searchParam.getValue();
                        if (StringUtils.isNotEmpty(employeeName)){
                            List<String> ids = userService.findIdsByEmployeeName(employeeName);
                            buildUnusualSearchParams(searchParam, ids);
                        } else{
                            searchParam.setFieldName(null);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        //设置快速查询
        if (StringUtils.isNotEmpty(searchCriteria.getFastSearch())){
            searchCriteria.setFastSearchParams(initFastSearchParams(searchCriteria.getFastSearch()));
        }
        List<User> users = userService.findAll(searchCriteria.buildSearchParams());
        return ResponseData.success(userService.toDTOs(users));
    }


    /**
     * 分页查询
     *
     * @param searchCriteria the search criteria
     * @return the response data
     * @author : yangjunqing / 2019-01-08
     */
    @PostMapping("list")
    @ApiOperation(value = "分页查询", response = ListData.class,
            notes = "分页查询支持说明(时间参数格式 yyyy-MM-dd|yyyy-MM-dd HH:mm:ss):  \n" +
                    "  1.快速查询:  \n" +
                    "    用户名称,员工名称  \n" +
                    "  2.条件查询  \n" +
                    "    status -> 用户状态(0-启用,1-未启用)  \n" +
                    "    roleId -> 角色编号  \n" +
                    "    employeeName -> 员工名称  \n" +
                    "    username -> 用户名称  \n"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "认证token", paramType = "header", required = true, dataType = "string")
    })
    public ResponseData findList(@RequestBody @Nullable SearchCriteria searchCriteria){

        List<SearchParam> searchParams = searchCriteria.getSearchParams();
        if (searchParams != null && searchParams.size() > 0){
            for (int n = 0; n < searchParams.size(); n++){
                SearchParam searchParam = searchParams.get(n);
                String fieldName = searchParam.getFieldName();

                switch (fieldName){
                    case "roleId":
                        String roleId = (String) searchParam.getValue();
                        if (StringUtils.isNotEmpty(roleId)){
                            List<String> ids = userService.findIdsByRoleId(roleId);
                            buildUnusualSearchParams(searchParam, ids);
                        } else{
                            searchParam.setFieldName(null);
                        }
                        break;
                    case "employeeName":
                        String employeeName = (String) searchParam.getValue();
                        if (StringUtils.isNotEmpty(employeeName)){
                            List<String> ids = userService.findIdsByEmployeeName(employeeName);
                            buildUnusualSearchParams(searchParam, ids);
                        } else{
                            searchParam.setFieldName(null);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        //设置快速查询
        if (StringUtils.isNotEmpty(searchCriteria.getFastSearch())){
            searchCriteria.setFastSearchParams(initFastSearchParams(searchCriteria.getFastSearch()));
        }

        ListData<User> users = userService.findPageList(searchCriteria);
        users.setData(userService.toDTOs(users.getOriginData()));
        return ResponseData.success(users);
    }

    /**
     * 初始化快速查询条件.
     *
     * @param fastSearch the fast search
     * @return the list
     * @author : yangjunqing / 2019-02-16
     */
    private List<FastSearchParam> initFastSearchParams(String fastSearch){
        fastSearchParams = new ArrayList<>(2);
        fastSearchParams.add(new FastSearchParam("username"));
        fastSearchParams.add(new FastSearchParam("id", userService.findIdsByFastSearch(fastSearch)));
        return fastSearchParams;
    }

    /**
     * Build unusual search params.
     *
     * @param searchParam the search param
     * @param ids         the ids
     * @author : yangjunqing / 2019-03-13
     */
    private void buildUnusualSearchParams(SearchParam searchParam, List<String> ids){
        searchParam.setFieldName("id");
        searchParam.setOperator("in");
        searchParam.setValue(ToolUtils.listToString(ids));
    }
}

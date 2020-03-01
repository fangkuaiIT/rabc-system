package com.lin.service.impl;


import com.lin.dao.UserDao;
import com.lin.dto.AuthorityDTO;
import com.lin.dto.UserDTO;
import com.lin.entity.Resource;
import com.lin.entity.Role;
import com.lin.entity.User;
import com.lin.service.ResourceService;
import com.lin.service.RoleService;
import com.lin.service.UserService;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.lin.util.utils.JwtTokenUtils;
import com.lin.util.utils.ToolUtils;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.http.HTTP;
import com.yyfly.common.repository.BaseRepository;
import com.yyfly.common.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户 service impl
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("jwtUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;


    @Override
    protected BaseRepository<User> getDao() {
        return userDao;
    }


    @Override
    public <S extends User> S save(S entity) {
        if (StringUtils.isEmpty(entity.getId())){
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        return userDao.save(entity);
    }

    @Override
    public ListData<User> findPageList(SearchCriteria searchCriteria) {
        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public String getUsername(String employeeId) {
        return userDao.getUsername(employeeId);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDao.countByUsername(username) == 0 ? false : true;
    }

    @Override
    public boolean existsByUsernameWithOutId(String username, String id) {
        return userDao.countByUsernameWithOutId(username, id) == 0 ? false : true;
    }

    @Override
    public boolean existsByEmployeeId(String employeeId) {
        return userDao.countByEmployeeId(employeeId) == 0 ? false : true;
    }

    @Override
    public boolean existsByEmployeeIdWithOutId(String employeeId, String id) {
        return userDao.countByEmployeeIdWithOutId(employeeId, id) == 0 ? false : true;
    }

    @Override
    public String getEmployeeNameByUsername(String username) {
        return userDao.getEmployeeNameByUsername(username);
    }

    @Override
    public String getEmployeeMailByUsername(String username) {
        return userDao.getEmployeeMailByUsername(username);
    }

    @Override
    public List<String> findAllUsernames() {
        return userDao.findAllUsernames();
    }

    @Override
    public List<String> findIdsByFastSearch(String fastSearch) {
        return userDao.findIdsByFastSearch(fastSearch);
    }

    @Override
    public List<String> findIdsByRoleId(String roleId) {
        return userDao.findIdsByRoleId(roleId);
    }

    @Override
    public List<String> findIdsByEmployeeName(String employeeName) {
        return userDao.findIdsByEmployeeName(employeeName);
    }

    @Override
    public void modifyPassword(String username, String originPassword, String newPassword, String repeat) {
        User user = findByUsername(username);
        if (user == null){
            throw new GlobalException("用户名：" + username + " 不存在!");
        }

        if (!BCrypt.checkpw(originPassword, user.getPassword())){
            throw new GlobalException("用户密码不正确!");
        }

        if (!newPassword.equals(repeat)){
            throw new GlobalException("两次输入的新密码不相同!");
        }

        userDao.modifyPassword(username, passwordEncoder.encode(newPassword));
    }


    @Override
    public String login(String userName, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return jwtTokenUtils.generateToken(userDetails.getUsername());
    }


    @Override
    public String refreshToken(String oldToken) {
        String username = jwtTokenUtils.getUserName(oldToken);
        User user = findByUsername(username);
        if (!jwtTokenUtils.validateToken(oldToken, username, user.getLastPasswordReset())){
            throw new GlobalException(HTTP.Status.FORBIDDEN.value(), HTTP.Status.FORBIDDEN.getReasonPhrase());
        }
        return jwtTokenUtils.refreshToken(oldToken);
    }


    @Override
    public boolean updateStatusByIds(Set<String> ids, int status) {
        return userDao.updateStatusByIds(ids, status) == 0 ? false : true;
    }

//
//    @Override
//    public Set<AuthorityDTO> getUserAuthority(String username) {
//        Set<AuthorityDTO> authorityDTOS = new HashSet<>();
//
//        Set<Resource> menus = resourceService.findMenuByUsername(username);
//        if (null != menus && menus.size() > 0){
//            menus.forEach(menu -> {
//                AuthorityDTO authorityDTO = new AuthorityDTO();
//                Set<String> permissions = new HashSet<>();
//
//                List<Resource> resources = resourceService.findPermissionByPidAndUsername(menu.getId(), username);
//                if (null != resources && resources.size() > 0){
//                    resources.forEach(resource -> {
//                        permissions.add(resource.getUrl());
//                    });
//                }
//                authorityDTO.setMenu(menu.getPath());
//                authorityDTO.setPermission(permissions);
//                authorityDTOS.add(authorityDTO);
//            });
//        }
//
//        return authorityDTOS;
//    }

    @Override
    public UserDTO toDTO(User user) {
        if (user == null){
            return null;
        }

        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);

        dto.setRoleIds(user.getRoleIds());
        dto.setRoleNames(user.getRoleNames());


        if (StringUtils.isNotEmpty(user.getEmployeeId())){
            String employeeName = userDao.getEmployeeNameByUsername(user.getUsername());
            dto.setEmployeeName(employeeName);
        }

//        Set<AuthorityDTO> authorityDTOS = getUserAuthority(dto.getUsername());
//        dto.setAuthorityDTOS(authorityDTOS);

        if (BaseEntity.NORMAL == dto.getStatus()){
            dto.setEnabled(true);
        }
        if (BaseEntity.DISABLE == dto.getStatus()){
            dto.setEnabled(false);
        }

        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user = ToolUtils.enabledToEntity(user, dto.isEnabled());

        Set<String> roleIds = dto.getRoleIds();
        if (roleIds != null && roleIds.size() > 0){
            Set<Role> roles = new HashSet<>(roleIds.size());
            for (String roleId : roleIds){
                Role role = roleService.findById(roleId);
                roles.add(role);
            }
            user.setRoles(roles);
        }

        return user;
    }

    @Override
    public List<UserDTO> toDTOs(List<User> users) {
        List<UserDTO> dtos = new ArrayList<>();
        if (users != null && users.size() > 0){
            users.forEach(user -> {
                UserDTO dto = toDTO(user);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<User> toEntitys(List<UserDTO> userDtos) {
        List<User> users = new ArrayList<>();
        if (userDtos != null && userDtos.size() > 0){
            userDtos.forEach(userDto -> {
                User user = toEntity(userDto);
                users.add(user);
            });
        }
        return users;
    }
}

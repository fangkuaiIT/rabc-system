package com.lin.service.impl;


import com.lin.dao.RoleDao;
import com.lin.dto.RoleDTO;
import com.lin.entity.Resource;
import com.lin.entity.Role;
import com.lin.service.ResourceService;
import com.lin.service.RoleService;
import com.lin.util.entity.ListData;
import com.lin.util.entity.SearchCriteria;
import com.lin.util.utils.ToolUtils;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.repository.BaseRepository;
import com.yyfly.common.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色 service impl
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceService resourceService;



    @Override
    protected BaseRepository<Role> getDao() {
        return roleDao;
    }


    @Override
    public ListData<Role> findPageList(SearchCriteria searchCriteria) {
        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return roleDao.countByRoleName(roleName) == 0 ? false : true;
    }

    @Override
    public boolean existsByRoleNameWithOutId(String roleName, String id) {
        return roleDao.countByRoleNameWithOutId(roleName, id) == 0 ? false : true;
    }

    @Override
    public boolean updateStatusByIds(Set<String> ids, int status) {
        return roleDao.updateStatusByIds(ids, status) == 0 ? false : true;
    }

    @Override
    public List<String> findRoleIdsByUsername(String username) {
        return roleDao.findRoleIdsByUsername(username);
    }


    /**
     * 获取访问当前url需要的角色
     * @param url
     * @return
     */
    @Override
   public List<String> getRolesByUrl(String url){
        return roleDao.getRolesByUrl(url);
    }


    @Override
    public RoleDTO toDTO(Role role) {
        if (role == null){
            return null;
        }

        RoleDTO dto = new RoleDTO();
        BeanUtils.copyProperties(role, dto);

        dto.setResourceIds(role.getResourceIds());
        dto.setResourceNames(role.getResourceNames());

        if (BaseEntity.NORMAL == dto.getStatus()){
            dto.setEnabled(true);
        }
        if (BaseEntity.DISABLE == dto.getStatus()){
            dto.setEnabled(false);
        }


        return dto;
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        role = ToolUtils.enabledToEntity(role, dto.isEnabled());

        Set<String> resourceIds = dto.getResourceIds();
        if (resourceIds != null && resourceIds.size() > 0){
            Set<Resource> resources = new HashSet<>(resourceIds.size());
            for (String resourceId : resourceIds){
                Resource resource = resourceService.findById(resourceId);
                resources.add(resource);
            }
            role.setResources(resources);
        }

        return role;
    }

    @Override
    public List<RoleDTO> toDTOs(List<Role> roles) {
        List<RoleDTO> dtos = new ArrayList<>();
        if (roles != null && roles.size() > 0){
            roles.forEach(role -> {
                RoleDTO dto = toDTO(role);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<Role> toEntitys(List<RoleDTO> roleDtos) {
        List<Role> roles = new ArrayList<>();
        if (roleDtos != null && roleDtos.size() > 0){
            roleDtos.forEach(roleDto -> {
                Role role = toEntity(roleDto);
                roles.add(role);
            });
        }
        return roles;
    }
}

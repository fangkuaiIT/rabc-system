package com.lin.service.impl;


import com.lin.dao.ResourceDao;
import com.lin.dto.AuthorityDTO;
import com.lin.dto.ResourceDTO;
import com.lin.entity.JwtUser;
import com.lin.entity.Resource;
import com.lin.service.ResourceService;
import com.lin.util.utils.ToolUtils;
import com.yyfly.common.entity.BaseEntity;
import com.yyfly.common.repository.BaseRepository;
import com.yyfly.common.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 资源 service impl
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;


    @Override
    protected BaseRepository<Resource> getDao() {
        return resourceDao;
    }


//    @Override
//    public ListData<Resource> findPageList(SearchCriteria searchCriteria) {
//        return ListData.of(findAll(searchCriteria.buildSearchParams(), searchCriteria.buildPageRequest()));
//    }
//
//    @Override
//    public Set<Resource> findMenuByUsername(String username) {
//        if (StringUtils.isEmpty(username)){
//            throw new GlobalException("用户名不能为空!");
//        }
//        return resourceDao.findMenuByUsername(username);
//    }
//
//    @Override
//    public List<Resource> findPermissionByPidAndUsername(String pid, String username) {
//        return resourceDao.findPermissionByPidAndUsername(pid, username);
//    }

//    @Override
//    public List<ResourceWithRoles> getAllResourceWithRoles() {
//        return resourceDao.getAllResourceWithRoles();
//    }

//    @Override
//    public List<ResourceWithChildren> getAllResources() {
//       return resourceDao.getAllResources();
//    }

//    @Override
//    public List<ResourceWithChildren> getResourcesByUserId() {
//        System.out.println("用户的id是"+((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId());
//
//       return null;
//    }

//    查询用户权限
    @Override
   public Set<AuthorityDTO> getResourcesByUser(){

        Set<AuthorityDTO> authorityDTOS = new HashSet<>();

        Set<Resource> menus = resourceDao.getResourcesByUid(((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId());
        if(null != menus && menus.size() > 0){
            menus.forEach(menu -> {
                AuthorityDTO authorityDTO = new AuthorityDTO();
                Set<Resource> children = new HashSet<>();
                Set<Resource> resources = resourceDao.getResourcesByPidAndUid(menu.getPid(),((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId());
                if(null != resources && resources.size() > 0){
                    resources.forEach(resource -> {
                        children.add(resource);
                    });
                }
                authorityDTO.setResource(menu);
                authorityDTO.setChildren(children);
                authorityDTOS.add(authorityDTO);
            });
        }
        return authorityDTOS;
   }

   @Override
   public Set<Resource> getAllResourceWithRoles(){
        return resourceDao.getAllResourceWithRoles();
   }
//    /**
//     * 查找该url的角色
//     * @return
//     */
//    @Override
//    public List<Role> getAllRolesWithResources(String url) {
//        return resourceDao.getAllRolesWithResources(url);
//    }

    @Override
    public ResourceDTO toDTO(Resource resource) {
        if (resource == null){
            return null;
        }

        ResourceDTO dto = new ResourceDTO();
        BeanUtils.copyProperties(resource, dto);

        if (BaseEntity.NORMAL == dto.getStatus()){
            dto.setEnabled(true);
        }
        if (BaseEntity.DISABLE == dto.getStatus()){
            dto.setEnabled(false);
        }

        return dto;
    }

    @Override
    public Resource toEntity(ResourceDTO dto) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(dto, resource);
        resource = ToolUtils.enabledToEntity(resource, dto.isEnabled());
        return resource;
    }

    @Override
    public List<ResourceDTO> toDTOs(List<Resource> resources) {
        List<ResourceDTO> dtos = new ArrayList<>();
        if (resources != null && resources.size() > 0){
            resources.forEach(resource -> {
                ResourceDTO dto = toDTO(resource);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    public List<Resource> toEntitys(List<ResourceDTO> resourceDtos) {
        List<Resource> resources = new ArrayList<>();
        if (resourceDtos != null && resourceDtos.size() > 0){
            resourceDtos.forEach(resourceDto -> {
                Resource resource = toEntity(resourceDto);
                resources.add(resource);
            });
        }
        return resources;
    }
}

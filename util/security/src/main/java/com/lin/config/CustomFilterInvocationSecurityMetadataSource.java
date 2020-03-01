package com.lin.config;

import com.lin.entity.Resource;
import com.lin.entity.Role;
import com.lin.service.ResourceService;

import com.lin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.security.access.SecurityConfig;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**

 *
 * 这个类的作用，主要是根据用户传来的请求地址，分析出请求需要的角色
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    ResourceService resourceService;
    @Autowired
    RoleService roleService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        System.out.println("当前请求的url"+requestUrl);
//        List<ResourceWithRoles> menus = resourceService.getAllResourceWithRoles();
        Set<Resource> resources = resourceService.getAllResourceWithRoles();
//        找到在资源表和角色表与对应的，就两张表都有的
        for (Resource resource : resources) {
            if(antPathMatcher.match(resource.getUrl(),requestUrl)){

                List<String> roles = roleService.getRolesByUrl(resource.getUrl());
                String[] str = new String[roles.size()];
                for(int i = 0; i<roles.size();i++){
                    str[i] = roles.get(i);
                    System.out.println(roles.get(i));
                }

                return SecurityConfig.createList(str);

            }

        }
        return SecurityConfig.createList("ROLE_LOGIN");

    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

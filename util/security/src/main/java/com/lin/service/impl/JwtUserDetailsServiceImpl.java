package com.lin.service.impl;



import com.lin.entity.JwtUser;
import com.lin.entity.User;
import com.lin.service.ResourceService;
import com.lin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * security用户认证实现类
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUsername(userName);

        if (user == null){
            throw new UsernameNotFoundException("未查询到用户名为 : " + userName);
        }

//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        Set<Resource> menus = resourceService.findMenuByUsername(userName);
//        if (null != menus && menus.size() > 0){
//            menus.forEach(menu -> {
//
//                List<Resource> resources = resourceService.findPermissionByPidAndUsername(menu.getId(), userName);
//                if (null != resources && resources.size() > 0){
//                    resources.forEach(resource -> {
//                        authorities.add(new SimpleGrantedAuthority(menu.getPath()+":"+resource.getPermission()));
//                    });
//                }
//            });
//        }

        return new JwtUser(user);
    }
}

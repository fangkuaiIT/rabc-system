package com.lin.entity;

import com.yyfly.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户安全
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
public class JwtUser implements UserDetails {

    /**
     * 用户
     */
    private User user;

    /**
     * Jwt user.
     *
     * @param user the user
     */
    public JwtUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //
        // 这里加载用户权限
        //

        List<SimpleGrantedAuthority> authorities = new ArrayList<>(user.getRoles().size());
        for(Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        System.out.println("用户的权限"+authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (BaseEntity.NORMAL == user.getStatus()){
            return true;
        }
        else {
            return false;
        }
    }
}

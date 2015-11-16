package moe.yuna.springauthdemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * can get user from ldap or jdbc
 * Created by rika on 2015/11/12.
 */
public class User implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set set = new HashSet<GrantedAuthority>();
        set.add(new SimpleAuthority());
        return set;
    }

    @Override
    public String getPassword() {
        //111111{salt}
        return "b9de33ff3d30bb91b85021e7e7235d9a";
    }

    public String getSalt() {
        return "salt";
    }

    @Override
    public String getUsername() {
        return "rika";
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
        return true;
    }
}

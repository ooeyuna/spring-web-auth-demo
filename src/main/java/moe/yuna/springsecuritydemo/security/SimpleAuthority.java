package moe.yuna.springauthdemo.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * can get authority from ldap or jdbc
 * Created by rika on 2015/11/12.
 */
public class SimpleAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ROLE_NORMAL";
    }
}

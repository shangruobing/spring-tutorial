package com.infoweaver.springtutorial.security;

import com.infoweaver.springtutorial.constant.Role;
import com.infoweaver.springtutorial.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Ruobing Shang 2023-10-24 08:33
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class LoginUserDetails implements UserDetails {
    /**
     * 用户角色
     */
    private User user;
    /**
     * 是否启用
     */
    private boolean state;
    /**
     * 授权列表，如角色等
     */
    private List<GrantedAuthority> authorities;
    /**
     * 权限列表，未用，如读写等
     */
    private List<String> permissions;

    public LoginUserDetails(User user) {
        this.user = user;
        this.state = user.getState();
        this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" +
                Role.getDescriptionByValue(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return user.getPhone();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
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
        return state;
    }
}

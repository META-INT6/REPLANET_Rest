package metaint.replanet.rest.auth.jwt;

import metaint.replanet.rest.auth.entity.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String memberCode;
    private String memberName;
    private String email;
    private String password;
    private MemberRole memberRole;  // 추가된 부분
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor, getters, and other methods

    public CustomUserDetails(String memberCode, String memberName, String email, String password,
                             MemberRole memberRole, Collection<? extends GrantedAuthority> authorities) {
        this.memberCode = memberCode;
        this.memberName = memberName;
        this.email = email;
        this.password = password;
        this.memberRole = memberRole;
        this.authorities = authorities;
    }

    // Implement methods from UserDetails interface

    @Override
    public String getUsername() {
        return memberCode;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    // Add getters for other fields

    public String getMemberCode() {
        return memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getEmail() {
        return email;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }
}
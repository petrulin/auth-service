package com.otus.authservice.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.TypeDef;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", schema = "auth_service", catalog = "postgres")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class User extends BaseIdEntity implements UserDetails {

    private String username;

    @Column(name = "password", updatable= false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    private boolean enabled;

    @Column(name = "account_locked")
    private boolean accountNonLocked;

    @Column(name = "account_expired")
    private boolean accountNonExpired;

    @Column(name = "credentials_expired")
    private boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER,  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "role_user", schema = "auth_service", catalog = "postgres", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    })
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
            r.getPermissions().forEach(p ->{
                authorities.add(new SimpleGrantedAuthority(p.getName()));
            });
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	public boolean getEnabled() {
		return enabled;
	}

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

	public boolean getAccountNonLocked() {
		return accountNonLocked;
	}

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

	public boolean getAccountNonExpired() {
		return accountNonExpired;
	}

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

	public boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

    //
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
}

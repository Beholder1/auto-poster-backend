package com.example.autoposterbackend.entity;

import com.example.autoposterbackend.dto.UserRegisterDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "role_id", insertable = false, updatable = false)
    private Long roleId;

    @Column(name = "username")
    private String realUsername;

    private Boolean registered;

    private Boolean banned;

    private Timestamp lastLogin;

    private Timestamp lastLogout;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    public User(UserRegisterDto userDto) {
        this.email = userDto.getEmail().trim().toLowerCase();
        this.realUsername = this.email.split("@")[0];
        this.registered = false;
        this.banned = false;
        this.roleId = 2L;
    }
}

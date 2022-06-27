package fis.com.sprint4.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import fis.com.sprint4.entity.role.UserRole;
import fis.com.sprint4.util.DateFormatter;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Person extends AbstractEntity implements UserDetails {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatter.DATE_FORMAT)
    @DateTimeFormat(pattern = DateFormatter.DATE_FORMAT)
    @Column(nullable = false)
    private LocalDateTime hiringDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "person")
    private Set<UserRole> userRoles = new HashSet<>();

    public Person(String username, String firstName, String lastName, String password, LocalDateTime hiringDate) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.hiringDate = hiringDate;
    }

    public Person() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set = new HashSet<>();
        this.userRoles.forEach(userRole -> {
            set.add(new Authority(userRole.getRole().getRoleName()));
        });
        return set;
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
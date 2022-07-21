package traineer.vanilson.restfullapis_with_spring_boot_2022.persistence.model;


import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name",
            unique = true,
            nullable = false,
            length = 50)
    private String userName;

    @Column(name = "full_name",
            nullable = false,
            length = 50)
    private String fullName;

    @Column(nullable = false,
            length = 50)
    private String password;

    @Column(name = "account_non_expired",
            nullable = false,
            length = 1)
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked ",
            nullable = false,
            length = 1)
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired ",
            nullable = false,
            length = 1)
    private Boolean credentialsNonExpired;

    @Column(name = "enabled",
            nullable = false,
            length = 1)
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_permission")}

    )
    private List<Permission> permissions;


    public User(Integer id, String userName, String fullName, String password, Boolean accountNonExpired,
                Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, List<Permission> permissions) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.permissions = permissions;
    }

    public User(String userName, String fullName, String password, Boolean accountNonExpired,
                Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, List<Permission> permissions) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.permissions = permissions;
    }

    public User(Integer id, String userName, String fullName, String password, Boolean accountNonExpired,
                Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<String> getRoles() {
        return permissions.stream().map(Permission::getDescription)
                .collect(Collectors
                        .toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(fullName, user.fullName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(accountNonExpired, user.accountNonExpired) &&
                Objects.equals(accountNonLocked, user.accountNonLocked) &&
                Objects.equals(credentialsNonExpired, user.credentialsNonExpired) &&
                Objects.equals(enabled, user.enabled) &&
                Objects.equals(permissions, user.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, fullName, password, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, permissions);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", permissions=" + permissions +
                '}';
    }
}

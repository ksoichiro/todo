package com.github.ksoichiro.todo.groovy.springboot.domain

import org.springframework.security.core.GrantedAuthority

import javax.persistence.*

@Entity
public class User extends org.springframework.security.core.userdetails.User {
    @Id
    @GeneratedValue
    Long id

    @Column(nullable = false, unique = true)
    String username

    @Column(nullable = false)
    String password

    boolean enabled

    @Column(nullable = false)
    Long createdAt

    @Column(nullable = false)
    Long updatedAt

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles

    public User() {
        super("INVALID", "INVALID", false, false, false, false, new ArrayList<GrantedAuthority>())
    }

    public User(String username, String password, boolean enabled, boolean accountNonExpired,
                boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities)
        // Super class has these fields, but they're necessary for persistence.
        setUsername(username)
        setPassword(password)
        setEnabled(enabled)
    }
}

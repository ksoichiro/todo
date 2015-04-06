package com.github.ksoichiro.todo.java.springboot.service;

import com.github.ksoichiro.todo.java.springboot.domain.Role;
import com.github.ksoichiro.todo.java.springboot.domain.User;
import com.github.ksoichiro.todo.java.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (null == username || "".equals(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }
        User domainUser = userRepository.findByUsername(username);
        if (domainUser == null) {
            throw new UsernameNotFoundException("User not found for name: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (domainUser.getRoles() != null) {
            for (Role role : domainUser.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        User user = new User(username, domainUser.getPassword(), domainUser.isEnabled(), authorities);
        user.setId(domainUser.getId());
        user.setCreatedAt(domainUser.getCreatedAt());
        user.setUpdatedAt(domainUser.getUpdatedAt());
        user.setRoles(domainUser.getRoles());
        return user;
    }
}

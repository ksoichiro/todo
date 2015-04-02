package com.github.ksoichiro.todo.groovy.springboot.service

import com.github.ksoichiro.todo.groovy.springboot.domain.Role
import com.github.ksoichiro.todo.groovy.springboot.domain.User
import com.github.ksoichiro.todo.groovy.springboot.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user
        if (null == username || "".equals(username)) {
            throw new UsernameNotFoundException("Username is empty")
        } else {
            User domainUser = userRepository.findByUsername(username)
            if (!domainUser) {
                throw new UsernameNotFoundException("User not found for name: " + username)
            } else {
                def authorities = []
                if (domainUser.roles) {
                    for (Role role : domainUser.roles) {
                        authorities.add(new SimpleGrantedAuthority(role.name))
                    }
                }
                user = new User(username, domainUser.password, domainUser.enabled, true, true, true, authorities)
                user.with {
                    id = domainUser.id
                    createdAt = domainUser.createdAt
                    updatedAt = domainUser.updatedAt
                    roles = domainUser.roles
                }
            }
        }
        user
    }
}

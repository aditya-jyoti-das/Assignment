package com.example.ASSIGNMENT_RBAC_BOTMAKERS.services;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.entity.CustomUserDetails;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.entity.User;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
        System.out.println("LOC:-CUSTOM USER DETAILS SERVICE CLASS- LOAD USER BY USERNAME:: "+ user);
        if(user==null){
            throw new UsernameNotFoundException("USER NOT FOUND 404");
        }
        return new CustomUserDetails(user);
    }
}

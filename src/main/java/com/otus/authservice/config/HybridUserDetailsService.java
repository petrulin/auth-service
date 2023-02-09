package com.otus.authservice.config;

import com.otus.authservice.entity.User;
import com.otus.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class HybridUserDetailsService implements UserDetailsService  {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s)  {

        User user = userRepository.findByUsername(s);
        if(user == null)
            throw new UsernameNotFoundException("User not found");
        new AccountStatusUserDetailsChecker().check(user);

        return user;
    }
}

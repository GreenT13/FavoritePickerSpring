package com.firstspring.security;

import com.entity.LogonUserEntity;
import com.firstspring.LogonUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    LogonUserRepository logonUserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        LogonUserEntity user = logonUserRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException(name);
        }
        return new UserDetailsImpl(user);
    }
}

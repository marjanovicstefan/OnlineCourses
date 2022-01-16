package com.example.OnlineCourses.service;

import com.example.OnlineCourses.domain.repository.UserRepository;
import com.example.OnlineCourses.security.OnlineCoursesUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OnlineCoursesUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username: " + username + " not found"));

        return new OnlineCoursesUserDetails(user);
    }
}

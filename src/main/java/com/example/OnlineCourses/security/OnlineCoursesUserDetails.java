//package com.example.OnlineCourses.security;
//
//import com.example.OnlineCourses.domain.model.Course;
//import com.example.OnlineCourses.domain.model.User;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@NoArgsConstructor
//@AllArgsConstructor
//public class OnlineCoursesUserDetails implements UserDetails {
//
//    private String username;
//    private String password;
//    private boolean active;
//    private List<GrantedAuthority> authorities;
//    private Course course;
//
//    public OnlineCoursesUserDetails(User user) {
//        username = user.getUserName();
//        password = user.getPassword();
//        active = user.getActive();
//        authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        course = user.getCourse();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}

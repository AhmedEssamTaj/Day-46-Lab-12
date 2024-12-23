package com.example.day46blogsysterm.Service;

import com.example.day46blogsysterm.Api.ApiException;
import com.example.day46blogsysterm.Model.MyUser;
import com.example.day46blogsysterm.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService  implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = authRepository.findByUsername(username);
        if(user == null){
            throw new ApiException("username or password incorrect");
        }
        return user;
    }
}

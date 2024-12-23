package com.example.day46blogsysterm.Service;

import com.example.day46blogsysterm.Api.ApiException;
import com.example.day46blogsysterm.Model.MyUser;
import com.example.day46blogsysterm.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private  final AuthRepository authRepository;

    public void register(MyUser myUser){
        myUser.setRole("USER");
        String hashPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashPassword);
        authRepository.save(myUser);
    }

    public void update(MyUser myUser,MyUser newUser){
        MyUser oldUser = authRepository.findMyUserById(myUser.getId());
        if (oldUser == null){
            throw  new ApiException("user not found");
        }
        oldUser.setUsername(newUser.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
        oldUser.setPassword(hashPassword);
        authRepository.save(oldUser);
    }

    public List<MyUser> getAllUsers(MyUser myUser){
        MyUser user = authRepository.findMyUserById(myUser.getId());
        if (user == null){
            throw  new ApiException("user not found");
        }
        return authRepository.findAll();
    }

    public void delete(Integer userId){
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null){
            throw  new ApiException("user not found");
        }
        authRepository.delete(user);
    }

}

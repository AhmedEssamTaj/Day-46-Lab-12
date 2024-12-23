package com.example.day46blogsysterm.Controller;

import com.example.day46blogsysterm.Api.ApiResponse;
import com.example.day46blogsysterm.Model.MyUser;
import com.example.day46blogsysterm.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser myUser) {
        authService.register(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("successfully registered"));
    }

    @PutMapping("/update")
    public ResponseEntity update (@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid MyUser newUser) {
        authService.update(myUser, newUser);
        return ResponseEntity.status(200).body(new ApiResponse("successfully updated details"));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllUsers (@AuthenticationPrincipal MyUser myUser) {
        return ResponseEntity.status(200).body(authService.getAllUsers(myUser));
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal MyUser myUser) {
        authService.delete(myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("successfully deleted"));
    }




}

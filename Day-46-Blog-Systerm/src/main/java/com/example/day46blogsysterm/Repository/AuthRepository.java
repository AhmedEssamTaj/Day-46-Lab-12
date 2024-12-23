package com.example.day46blogsysterm.Repository;

import com.example.day46blogsysterm.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser, Integer> {
    MyUser findByUsername(String username);
    MyUser findMyUserById(Integer id);
}

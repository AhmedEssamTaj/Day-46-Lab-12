package com.example.day46blogsysterm.Repository;

import com.example.day46blogsysterm.Model.Blog;
import com.example.day46blogsysterm.Model.MyUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    Blog findBlogById(Integer id);

    List<Blog> findBlogByUser(MyUser user);

    Blog findBlogByTitle(String title);
}

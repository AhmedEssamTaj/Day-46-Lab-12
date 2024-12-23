package com.example.day46blogsysterm.Service;

import com.example.day46blogsysterm.Api.ApiException;
import com.example.day46blogsysterm.Model.Blog;
import com.example.day46blogsysterm.Model.MyUser;
import com.example.day46blogsysterm.Repository.AuthRepository;
import com.example.day46blogsysterm.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public void createBlog(Integer userId, Blog blog){
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null){
            throw new ApiException("No user was found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Integer blogId ,Blog blog){
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null){
            throw new ApiException("No user was found");
        }
        Blog oldBlog = blogRepository.findBlogById(blogId);
        if (oldBlog == null){
            throw new ApiException("No blog was found");
        }
        if (oldBlog.getUser().equals(user)){
            oldBlog.setTitle(blog.getTitle());
            oldBlog.setBody(blog.getBody());
            blogRepository.save(oldBlog);
        }else{
            throw new ApiException("Wrong user");
        }
    }

    public void deleteBlog(Integer userId, Integer blogId){
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null){
            throw new ApiException("No user was found");
        }
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null){
            throw new ApiException("No blog was found");
        }
        if (blog.getUser().equals(user) || user.getRole().equals("ADMIN")){
            blogRepository.delete(blog);
        }else  {
            throw new ApiException("Wrong user");
        }
    }

    public List<Blog> getBlogsByUserId(Integer userId){
        MyUser user = authRepository.findMyUserById(userId);
        if (user == null){
            throw new ApiException("No user was found");
        }
        return blogRepository.findBlogByUser(user);
    }

    public Blog getBlogById(Integer blogId){
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null){
            throw new ApiException("No blog was found");
        }
        return blog;
    }

    public Blog getBlogByTitle(String title){
        return blogRepository.findBlogByTitle(title);
    }



}

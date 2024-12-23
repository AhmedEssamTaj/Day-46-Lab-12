package com.example.day46blogsysterm.Controller;

import com.example.day46blogsysterm.Api.ApiResponse;
import com.example.day46blogsysterm.Model.Blog;
import com.example.day46blogsysterm.Model.MyUser;
import com.example.day46blogsysterm.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get-all")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @PostMapping("/create")
    public ResponseEntity createBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Blog blog){
        blogService.createBlog(myUser.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog created successfully"));
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId, @RequestBody @Valid Blog blog){
        blogService.updateBlog(myUser.getId(), blogId, blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog updated successfully"));
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId){
        blogService.deleteBlog(myUser.getId(), blogId);
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted successfully"));
    }

    @GetMapping("/get-my-blogs")
    public ResponseEntity getAllBlogsByUser(@AuthenticationPrincipal MyUser myUser){

        return ResponseEntity.status(200).body(blogService.getBlogsByUserId(myUser.getId()));

    }

    @GetMapping("/get-blog/{blogId}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId){
        return ResponseEntity.status(200).body(blogService.getBlogById(blogId));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }

}

package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.Blog;
import com.infoweaver.springtutorial.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class BlogController {
    private BlogServiceImpl BlogService;

    @Autowired
    public void setBlogService(BlogServiceImpl BlogService) {
        this.BlogService = BlogService;
    }

    @GetMapping("/blog")
    public List<Blog> selectAllBlog() {
        return BlogService.getBlogList();
    }

    @GetMapping("/blog/{id}")
    public Blog getBlogById(@PathVariable("id") long id) {
        return BlogService.getBlogById(id);
    }


    @PostMapping("/blog")
    public int add(@RequestBody Blog Blog) {
        return BlogService.addBlog(Blog);
    }

    @PutMapping("/blog")
    public int update(@RequestBody Blog Blog) {
        return BlogService.editBlog(Blog);
    }

    @DeleteMapping("/blog/{id}")
    public int delete(@PathVariable("id") long id) {
        return BlogService.removeBlog(id);
    }
}



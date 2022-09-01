package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.Blog;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

public interface IBlogService extends IService<Blog> {
    /**
     * Retrieve All Blog.
     *
     * @return Blog List
     */
    List<Blog> getBlogList();

    /**
     * Retrieve a Blog by id.
     *
     * @param id Blog id
     * @return a Blog instance
     */
    Blog getBlogById(Long id);

    /**
     * Create a Blog instance.
     *
     * @param Blog Blog object
     * @return a status code
     */
    int addBlog(Blog Blog);

    /**
     * Update a Blog instance.
     *
     * @param Blog Blog object
     * @return a status code
     */
    int editBlog(Blog Blog);

    /**
     * Delete a Blog instance.
     *
     * @param id Blog id
     * @return a status code
     */
    int removeBlog(Long id);

}

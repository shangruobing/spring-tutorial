package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.Blog;
import com.infoweaver.springtutorial.mapper.BlogMapper;
import com.infoweaver.springtutorial.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {
    private BlogMapper BlogMapper;

    @Autowired
    public void setBlogMapper(BlogMapper BlogMapper) {
        this.BlogMapper = BlogMapper;
    }

    public List<Blog> getBlogList() {
        return BlogMapper.selectList(null);
    }

    public Blog getBlogById(Long id) {
        return BlogMapper.selectById(id);
    }

    public int addBlog(Blog Blog) {
        return BlogMapper.insert(Blog);
    }

    public int editBlog(Blog Blog) {
        return BlogMapper.updateById(Blog);
    }

    public int removeBlog(Long id) {
        return BlogMapper.deleteById(id);
    }
}

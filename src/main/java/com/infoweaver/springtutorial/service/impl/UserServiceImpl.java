package com.infoweaver.springtutorial.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.infoweaver.springtutorial.entity.Blog;
import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.vo.UserVo;
import com.infoweaver.springtutorial.mapper.BlogMapper;
import com.infoweaver.springtutorial.mapper.UserMapper;
import com.infoweaver.springtutorial.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import static java.util.stream.Collectors.*;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private UserMapper userMapper;
    private BlogMapper blogMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setBlogMapper(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    public List<User> getUserList() {
        return userMapper.selectList(Wrappers.emptyWrapper());
    }

    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    public int addUser(User user) {
        return userMapper.insert(user);
    }

    public int editUser(User user) {
        return userMapper.updateById(user);
    }

    public int removeUser(Long id) {
        return userMapper.deleteById(id);
    }

    public UserVo getUserInfoById(Long id) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).eq(User::getId, id);
        UserVo userVo = Optional.ofNullable(userMapper.selectOne(wrapper)).map(UserVo::new).orElse(null);
        Optional.ofNullable(userVo).ifPresent(this::addUserInfo);
        return userVo;
    }

    private void addUserInfo(UserVo userVo) {
        LambdaQueryWrapper<Blog> wrapper = Wrappers.lambdaQuery(Blog.class).eq(Blog::getUserId, userVo.getId());
        List<Blog> blogs = blogMapper.selectList(wrapper);
        userVo.setBlogs(blogs);
    }

    public List<UserVo> getUserDetailsList() {
        List<User> userList = userMapper.selectList(Wrappers.emptyWrapper());
        List<UserVo> userVos = userList.stream().map(UserVo::new).collect(toList());
        if (userVos.size() > 0) {
            addUserInfoList(userVos);
        }
        return userVos;
    }

    private void addUserInfoList(List<UserVo> userVos) {
        Set<Long> userIds = userVos.stream().map(User::getId).collect(toSet());
        List<Blog> blogs = blogMapper.selectList(Wrappers.lambdaQuery(Blog.class).in(Blog::getUserId, userIds));
        Map<Long, List<Blog>> hashMap = blogs.stream().collect(groupingBy(Blog::getUserId));
        userVos.forEach(e -> e.setBlogs(hashMap.get(e.getId())));
    }

    public List<Blog> getUserDetailBySQL() {
        String sql = "select * from blog";
        return userMapper.dynamicSql(sql);
    }
}

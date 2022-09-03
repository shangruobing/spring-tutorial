package com.infoweaver.springtutorial.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.User;

/**
 * @author Ruobing Shang 2022-09-01
 */

public interface IUserService extends IService<User> {
    /**
     * Retrieve All User.
     *
     * @return User List
     */
    List<User> listUsers();

    /**
     * Retrieve a User by id.
     *
     * @param id user id
     * @return a User instance
     */
    User getUserById(Long id);

    /**
     * Create a User instance.
     *
     * @param user user object
     * @return a status code
     */
    int saveUser(User user);

    /**
     * Update a user instance.
     *
     * @param user user object
     * @return a status code
     */
    int updateUser(User user);

    /**
     * Delete a user instance.
     *
     * @param id user id
     * @return a status code
     */
    int removeUser(Long id);

}

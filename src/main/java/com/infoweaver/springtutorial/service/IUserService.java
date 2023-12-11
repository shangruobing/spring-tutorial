package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.vo.UserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Ruobing Shang 2023-09-20 22:05
 */
public interface IUserService extends IService<User> {
    /**
     * 查询所有用户
     *
     * @param userDto 查询条件
     * @return 分页查询结果
     */
    IPage<UserVo> listUsers(com.infoweaver.springtutorial.dto.UserDto userDto);

    /**
     * 新增用户，注意是系统内新增，不是开放对外注册接口
     *
     * @param userDto 用户信息
     * @return 用户Vo
     */
    @Transactional(rollbackFor = Exception.class)
    UserVo addUser(com.infoweaver.springtutorial.dto.UserDto userDto);

    /**
     * 更新用户信息
     *
     * @param userDto 用户
     * @return 用户Vo
     */
    @Transactional(rollbackFor = Exception.class)
    UserVo updateUser(com.infoweaver.springtutorial.dto.UserDto userDto);

    /**
     * 根据Id列表查询用户Vo
     *
     * @param userIds 用户id列表
     * @return 用户Vo列表
     */
    List<UserVo> getUserVosByIds(Collection<? extends Serializable> userIds);

    /**
     * 根据Id查询用户Vo
     *
     * @param id 用户Id
     * @return 用户Vo
     */
    UserVo getUserVoById(Integer id);

    UserVo getUserVoByPhone(String phone);

    /**
     * 对上传的Excel进行预检
     *
     * @param file excel
     * @return 预检结果
     */
    ResponseEntity<com.infoweaver.springtutorial.po.ExcelPrecheckResult> precheckUploadUser(MultipartFile file);

    /**
     * 批量插入用户
     *
     * @param file excel
     * @return 消息
     */
    @Transactional(rollbackFor = Exception.class)
    String batchInsertUserWithExcel(MultipartFile file);
}

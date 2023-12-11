package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.common.ExcelExporter;
import com.infoweaver.springtutorial.common.ExcelUploader;
import com.infoweaver.springtutorial.dto.UserDto;
import com.infoweaver.springtutorial.entity.Company;
import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.mapper.UserMapper;
import com.infoweaver.springtutorial.po.ExcelMapping;
import com.infoweaver.springtutorial.po.ExcelPrecheckResult;
import com.infoweaver.springtutorial.security.SecurityUtils;
import com.infoweaver.springtutorial.service.IUserService;
import com.infoweaver.springtutorial.util.KeyUtils;
import com.infoweaver.springtutorial.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ruobing Shang 2023-09-20 22:06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final UserMapper userMapper;
    private final CompanyServiceImpl companyService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, CompanyServiceImpl companyService) {
        this.userMapper = userMapper;
        this.companyService = companyService;
    }

    @Override
    public IPage<UserVo> listUsers(UserDto userDto) {
        IPage<User> page = new Page<>(userDto.getPage(), userDto.getPageSize());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>(User.class);
        wrapper.orderByDesc(User::getId);
        Optional.ofNullable(userDto.getState()).ifPresent(state -> wrapper.eq(User::getState, state));
        Optional.ofNullable(userDto.getName()).ifPresent(name -> wrapper.like(User::getName, name));
        Optional.ofNullable(userDto.getCompanyId()).ifPresent(companyId -> wrapper.eq(User::getCompanyId, companyId));
        Optional.ofNullable(userDto.getRole()).ifPresent(role -> wrapper.eq(User::getRole, role));
        Optional.ofNullable(userDto.getPhone()).ifPresent(phone -> wrapper.like(User::getPhone, phone));
        IPage<User> userPage = userMapper.selectPage(page, wrapper);
        IPage<UserVo> userVoPage = userPage.convert(UserVo::new);
        if (userVoPage.getTotal() > 0) {
            addCompanyInfo(userVoPage);
        }
        return userVoPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo addUser(UserDto userDto) {
        checkPhoneExists(userDto.getPhone());
        User user = new User();
        user.setPassword(KeyUtils.encryption(userDto.getPassword()))
                .setUsername(userDto.getUsername())
                .setRole(userDto.getRole())
                .setName(userDto.getName())
                .setGender(userDto.getGender())
                .setPhone(userDto.getPhone())
                .setEmail(userDto.getEmail())
                .setJoinTime(LocalDateTime.now())
                .setCompanyId(userDto.getCompanyId())
                .setUnionId(userDto.getUnionId())
                .setOpenId(userDto.getOpenId())
                .setState(true)
                .setCreateUserId(SecurityUtils.getCurrentUserId());
        userMapper.insert(user);
        return convertUserEntityToUserVo(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo updateUser(UserDto userDto) {
        /**
         * 先判断是否传入Id
         */
        Integer id = Optional.ofNullable(userDto.getId()).orElseThrow(() -> new RuntimeException("ID不能为空"));
        /**
         * 根据Id查找记录
         */
        User existUser = Optional.ofNullable(userMapper.selectById(id)).orElseThrow(() -> new RuntimeException("用户不存在"));
        /**
         * 判断是否出现重复手机号
         */
        boolean isNeedUpdatePhone = !existUser.getPhone().equals(userDto.getPhone());
        if (isNeedUpdatePhone) {
            checkPhoneExists(userDto.getPhone());
            existUser.setPhone(userDto.getPhone());
        }
        Optional.ofNullable(userDto.getPassword()).ifPresent(password -> existUser.setPassword(KeyUtils.encryption(password)));
        Optional.ofNullable(userDto.getState()).ifPresent(existUser::setState);
        Optional.ofNullable(userDto.getRole()).ifPresent(existUser::setRole);
        Optional.ofNullable(userDto.getUsername()).ifPresent(existUser::setUsername);
        Optional.ofNullable(userDto.getName()).ifPresent(existUser::setName);
        Optional.ofNullable(userDto.getGender()).ifPresent(existUser::setGender);
        Optional.ofNullable(userDto.getPhone()).ifPresent(existUser::setPhone);
        Optional.ofNullable(userDto.getCompanyId()).ifPresent(existUser::setCompanyId);
        Optional.ofNullable(userDto.getEmail()).ifPresent(existUser::setEmail);
        Optional.ofNullable(userDto.getUnionId()).ifPresent(existUser::setUnionId);
        Optional.ofNullable(userDto.getOpenId()).ifPresent(existUser::setOpenId);
        /**
         * 条件构造器添加完成，开始更新
         */
        existUser.setUpdateUserId(SecurityUtils.getCurrentUserId());
        userMapper.updateById(existUser);
        /**
         * 更新完把该条记录查询并返回为Vo
         */
        return convertUserEntityToUserVo(userMapper.selectById(id));
    }

    @Override
    public List<UserVo> getUserVosByIds(Collection<? extends Serializable> userIds) {
        /**
         * 没有传入id列表则直接返回空
         */
        if (userIds.isEmpty()) {
            return new ArrayList<>();
        }
        List<User> users = userMapper.selectBatchIds(userIds);
        Set<Integer> companyIds = users.stream()
                .map(User::getCompanyId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        /**
         * 如果都没有公司就直接返回
         */
        if (companyIds.isEmpty()) {
            return users.stream().map(UserVo::new).collect(Collectors.toList());
        }
        /**
         * 否则构造公司id列表查询公司
         */
        Map<Integer, Company> companyMap = companyService.listByIds(companyIds)
                .stream()
                .collect(Collectors.toMap(Company::getId, company -> company));
        return users.stream()
                .map(user -> new UserVo(user).setCompany(companyMap.get(user.getCompanyId())))
                .collect(Collectors.toList());
    }

    @Override
    public UserVo getUserVoById(Integer id) {
        return Optional.ofNullable(id).map(userMapper::selectById).map(this::convertUserEntityToUserVo).orElse(null);
    }

    @Override
    public UserVo getUserVoByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>(User.class);
        wrapper.eq(User::getPhone, phone);
        return Optional.of(wrapper).map(userMapper::selectOne).map(this::convertUserEntityToUserVo).orElse(null);
    }

    private void checkPhoneExists(String phone) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).eq(User::getPhone, phone);
        if (Optional.ofNullable(userMapper.selectOne(wrapper)).isPresent()) {
            throw new RuntimeException(phone + "已经存在");
        }
    }

    private UserVo convertUserEntityToUserVo(User user) {
        UserVo userVo = new UserVo(user);
        addCompanyInfo(userVo);
        return userVo;
    }

    private void addCompanyInfo(UserVo userVo) {
        if (Optional.ofNullable(userVo.getCompanyId()).isPresent()) {
            userVo.setCompany(companyService.getById(userVo.getCompanyId()));
        }
    }

    private void addCompanyInfo(IPage<UserVo> userVoPage) {
        Set<Integer> companyIds = userVoPage.getRecords()
                .stream()
                .map(UserVo::getCompanyId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if (!companyIds.isEmpty()) {
            Map<Integer, Company> companyMap = companyService.listByIds(companyIds)
                    .stream()
                    .collect(Collectors.toMap(Company::getId, company -> company));
            userVoPage.convert(userVo -> userVo.setCompany(companyMap.get(userVo.getCompanyId())));
        }
    }

    @Override
    public ResponseEntity<ExcelPrecheckResult> precheckUploadUser(MultipartFile file) {
        ExcelUploader<User> excelUploader = new ExcelUploader<>(User.class, file, getUploadRequiredColumns());
        return excelUploader.precheckUpload();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String batchInsertUserWithExcel(MultipartFile file) {
        ExcelUploader<User> excelUploader = new ExcelUploader<>(User.class, file, getUploadRequiredColumns());
        List<User> userList = excelUploader.getEntityList()
                .stream()
                .peek(user -> user.setCreateUserId(SecurityUtils.getCurrentUserId()))
                .collect(Collectors.toList());
        saveBatch(userList);
        return "ok";
    }

    public ResponseEntity<?> exportUser() {
        ExcelExporter<User> excelExporter = new ExcelExporter<>(getRequiredColumns(), "用户信息");
        return excelExporter.exportToExcel(userMapper.selectList(Wrappers.emptyWrapper()));
    }

    /**
     * 导入所需的列
     */
    private List<ExcelMapping> getUploadRequiredColumns() {
        return List.of(
                new ExcelMapping("姓名", "name"),
                new ExcelMapping("用户名称", "username"),
                new ExcelMapping("公司编号", "companyId"),
                new ExcelMapping("角色", "role"),
                new ExcelMapping("性别", "gender"),
                new ExcelMapping("手机", "phone"),
                new ExcelMapping("邮箱", "email")
        );
    }

    /**
     * 导出所需要的列
     */
    private List<ExcelMapping> getRequiredColumns() {
        return List.of(
                new ExcelMapping("用户编号", "id"),
                new ExcelMapping("姓名", "name"),
                new ExcelMapping("用户名称", "username"),
                new ExcelMapping("是否启用", "state"),
                new ExcelMapping("公司编号", "companyId"),
                new ExcelMapping("角色", "role"),
                new ExcelMapping("性别", "gender"),
                new ExcelMapping("手机", "phone"),
                new ExcelMapping("邮箱", "email"),
                new ExcelMapping("注册时间", "joinTime"),
                new ExcelMapping("上次登录时间", "lastLoginTime")
        );
    }
}
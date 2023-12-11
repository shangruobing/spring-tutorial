package com.infoweaver.springtutorial.vo;

import com.infoweaver.springtutorial.entity.Company;
import com.infoweaver.springtutorial.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Ruobing Shang 2023-10-13 14:42
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserVo extends User {
    /**
     * 外键公司
     */
    private Company company;

    public UserVo(User user) {
        /**
         * 密码字段在Vo处需要加密
         */
        this.password = "********";
        this.id = user.getId();
        this.state = user.getState();
        this.unionId = user.getUnionId();
        this.openId = user.getOpenId();
        this.role = user.getRole();
        this.name = user.getName();
        this.username = user.getUsername();
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.joinTime = user.getJoinTime();
        this.lastLoginTime = user.getLastLoginTime();
        this.companyId = user.getCompanyId();
        this.createTime = user.getCreateTime();
        this.createUserId = user.getCreateUserId();
        this.updateTime = user.getUpdateTime();
        this.updateUserId = user.getUpdateUserId();
    }
}

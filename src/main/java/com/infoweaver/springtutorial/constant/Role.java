package com.infoweaver.springtutorial.constant;

import lombok.Getter;

/**
 * @author Ruobing Shang 2023-10-26 11:00
 */
@Getter
public enum Role {
    /**
     * 管理员
     */
    ADMIN("管理员", "ADMIN"),
    /**
     * 用户
     */
    SUPPLIER("用户", "USER"),
    /**
     * 访客
     */
    EMPLOYEE("访客", "GUEST");
    private final String value;
    private final String description;

    Role(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static String getDescriptionByValue(String value) {
        for (Role item : Role.values()) {
            if (item.getValue().equals(value)) {
                return item.getDescription();
            }
        }
        throw new RuntimeException("can't find description by value");
    }
}

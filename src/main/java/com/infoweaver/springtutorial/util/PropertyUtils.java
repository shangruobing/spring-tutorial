package com.infoweaver.springtutorial.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ruobing Shang 2023-10-13 17:53
 */
public class PropertyUtils {
    /**
     * 此方法用于将Entity对象进行转换，生成Vo对象，用于Vo的构造函数赋值
     *
     * @param entityName         实体名称
     * @param definePropertyText 属性定义字符串
     */
    private static void generateVoByEntity(String entityName, String definePropertyText) {
        Pattern pattern = Pattern.compile("(?<=\\s)(\\w+)(?=\\s*;)");
        Matcher matcher = pattern.matcher(definePropertyText);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group(1));
        }
        for (String match : matches) {
            String capitalizedMatch = match.substring(0, 1).toUpperCase() + match.substring(1);
            System.out.println("this." + match + " = " + entityName + ".get" + capitalizedMatch + "();");
        }
    }

    public static void main(String[] args) {
        String entityName = "distributionMethod";
        String definePropertyText =
                """
    protected Integer id;
    protected Integer userId;
    protected BigDecimal disbursedAmount;
    protected BigDecimal unpaidAmount;
    protected LocalDateTime distributeTime;
    protected String remark;
                                    """;
        PropertyUtils.generateVoByEntity(entityName, definePropertyText);
    }
}

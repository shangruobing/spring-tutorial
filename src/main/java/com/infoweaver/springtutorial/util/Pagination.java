package com.infoweaver.springtutorial.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruobing Shang 2022-09-05 23:31
 */
public class Pagination {

    public static <T> Page<T> getPages(Integer pageNo, Integer pageSize, List<T> list) {
        Page<T> page = new Page<>();
        int size = list.size();
        if (pageSize > size) {
            pageSize = size;
        }
        // 求出最大页数，防止currentPage越界
        int maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
        if (pageNo > maxPage) {
            pageNo = maxPage;
        }
        // 当前页第一条数据下标
        int currentIndex = pageNo > 1 ? (pageNo - 1) * pageSize : 0;
        List<T> pageList = new ArrayList<>();
        // 将当前页的数据放进pageList
        for (int i = 0; i < pageSize && currentIndex + i < size; i++) {
            pageList.add(list.get(currentIndex + i));
        }
        page.setCurrent(pageNo).setSize(pageSize).setTotal(list.size()).setRecords(pageList);
        return page;
    }
}

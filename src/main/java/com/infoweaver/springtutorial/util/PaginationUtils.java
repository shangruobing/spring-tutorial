package com.infoweaver.springtutorial.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * This class seems to have no effect.
 * Although it is possible to override the response body,
 * but the request does not seem to be processed.
 *
 * @author Ruobing Shang 2022-09-05 23:31
 */

public class PaginationUtils {

    public static <T> Page<T> getPages(Integer current, Integer size, List<T> records) {
        Page<T> page = new Page<>();
        int total = records.size();
        if (size > total) {
            size = total;
        }
        int maxPage = total % size == 0 ? total / size : total / size + 1;
        if (current > maxPage) {
            current = maxPage;
        }
        int currentIndex = current > 1 ? (current - 1) * size : 0;
        List<T> pageList = new ArrayList<>();

        for (int i = 0; i < size && currentIndex + i < total; i++) {
            pageList.add(records.get(currentIndex + i));
        }
        page.setCurrent(current).setSize(size).setTotal(records.size()).setRecords(pageList);
        return page;
    }

    public static void main(String[] args) {
        List<String> list = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
        Page<String> page = getPages(2, 3, list);
        System.out.println("pages: " + page.getPages());
        System.out.println("current: " + page.getCurrent());
        System.out.println("size: " + page.getSize());
        System.out.println("records: " + page.getRecords());
        System.out.println("total: " + page.getTotal());
    }
}

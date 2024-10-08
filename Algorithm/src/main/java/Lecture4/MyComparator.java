package Lecture4;

import java.util.Comparator;

/**
 * @Title: Comparator
 * @Author R7CKB
 * @Package Lecture4
 * @Date 2024/10/4 17:16
 * @description: 比较器
 */
public class MyComparator {
    // 前面的减去后面的代表升序，后面的减去前面的代表降序
    public static class ageComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}
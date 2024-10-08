package Lecture1;

// 一个数组中只有一种数出现了奇数次, 其他数都出现了偶数次, 怎么找到并打印这种数 (出现奇数次).
// leetcode:https://leetcode.cn/problems/single-number/
public class EvenTimesOddTimes {

    // 1. 0异或所有的数,最后的数即为奇数次的数
    // 2. 数组中所有的数分别异或,最后的数即为奇数次的数
    public static void printOddTimesNum1(int[] nums) {
        int eor = 0;
        for (int j : nums) {
            eor ^= j;
        }
        System.out.println(eor);
    }
}

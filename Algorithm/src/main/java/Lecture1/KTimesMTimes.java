package Lecture1;

import java.util.HashMap;

// 输入一定能够保证，数组中所有的数都出现了M次，只有一种数出现了K次
// 1 <= K < M
// 返回这种数
// leetcode:https://leetcode.cn/problems/single-number-ii/
public class KTimesMTimes {
    // 最简单的写法
    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int ans = 0;
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    // 更简洁的写法
    public static int km(int[] arr, int k, int m) {
        int[] help = new int[32];
        // 统计每一位上1的个数,来统计每种数出现的次数
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        // 把这个数还原出来
        for (int i = 0; i < 32; i++) {
            help[i] %= m;
            if (help[i] != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 9, 9, 9, 3, 3, 3};
        int k = 3;
        int m = 4;
        System.out.println(km(arr, k, m)); // 9
    }
}

package Lecture3;

/**
 * @Title: CountOfRangeSum
 * @Author R7CKB
 * @Package Lecture3
 * @Date 2024/10/3 16:48
 * @description: leetcode 327 hard 区间和的个数
 */
// leetcode : https://leetcode.com/problems/count-of-range-sum/
// 这道题和之前的求逆序对的题目差不多,都是在归并排序的过程中进行处理.
public class CountOfRangeSum {
    // 这道题的整体思路应该是前缀和+归并排序
    // 为啥要用前缀和。因为对于频繁求数组某个范围内数的和时，使用前缀和代替这样在求原数组 i - j 范围内数的和，
    // 则等于前缀和数组中 Sum[ j ] - Sum[ i - 1]。这样就不用每次求和时都遍历数组了。
    public int countRangeSum(int[] nums, int lower, int upper) {
        // 先判空
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // sum数组即是前缀和数组,它存储的是从0到i的元素的和
        // 注意这里的long类型,防止溢出
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        // 然后使用归并排序来完成问题
        return process(sum, 0, sum.length - 1, lower, upper);
    }


    public int process(long[] sum, int L, int R, int lower, int upper) {
        // 如果排序的时候只有一个元素,则直接判断是否在区间内,如果在,则返回1,否则返回0
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        // 接下来就是归并排序的过程
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
                + merge(sum, L, M, R, lower, upper);
    }

    public int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        // 下面是解决问题的过程
        // 核心思路如下:
        // 1.使用前缀和代替原数组；
        // 2.在归并排序合并方法内，统计满足条件的累加和个数 和 合并操作分开。
        // 3.每次合并操作，对于右组（前缀和数组）中的每一个数X[i]，
        // 求左组（前缀和数组）所有数中有多少个范围在 [X[i] - upper, X[i] - lower]上，
        // 将满足条件的个数累加起来即为最后的结果

        // 累加和个数
        int ans = 0;
        // 左组寻找的左侧位置（肯定是从当前的L位置开始）
        int windowL = L;
        // 左组寻找的右侧位置（肯定也是从当前的L位置开始）
        int windowR = L;
        // [windowL, windowR)
        // 对于右组的每一个数X，在左组中寻找值在[X-upper, X-lower]之间的个数
        for (int i = M + 1; i <= R; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            // 因为是在左组中寻找，所以下标不能超过mid
            // 寻找当前值比max大的第一个位置（因为等于max的时候右移了一位，所以不包含此位置）
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            // 寻找当前值大于等于min的第一个位置（因为等于min的时候没有右移，所以包含此位置）
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            // 最后满足要求的累加和个数为 [windowL, windowR)，即 windowR - windowL，windowR是开区间，所以不 +1
            ans += windowR - windowL;
        }

        // 以下是归并排序的过程
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }
}

package Lecture3;

/**
 * @Title: ReversePairs
 * @Author R7CKB
 * @Package Lecture3
 * @Date 2024/10/2 19:37
 * @description: Leetcode 493 hard
 */
// leetcode : https://leetcode.com/problems/reverse-pairs/
public class ReversePairs {
    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        // l < r
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int M, int R) {

        // 只有下面这一小段是正常计算该数组的逆序对的步骤.
        // 其余全是正常的归并排序的步骤.
        // 这里也用到了滑动窗口的思想
        // [L....M] [M+1....R]
        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = M + 1;
        // 刚开始L是左序列的开头,windowsR是右序列的开头
        // 直接扫描所有左序列的数, 对于每个数,看他是否满足条件,如果满足条件,就把他和右序列的数进行比较,
        // 并把满足条件的数的个数加到ans中.
        for (int i = L; i <= M; i++) {
            // long类型防止溢出
            // 对于任意的i来说,如果不满足条件的话,ans+=0, 所以不需要判断.
            // 对于任意的i来说,如果满足条件的话,我们就接着移动windowR,直到windowR大于等于R,或者arr[i]小于arr[windowR]*2.
            while (windowR <= R && (long) arr[i] > (long) arr[windowR] * 2) {
                windowR++;
            }
            ans += windowR - (M + 1);
        }


        // 下面就是正常的归并排序的步骤,以上才是解题步骤.
        int[] help = new int[R - L + 1];
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

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 3, 1};
        int res = reversePairs(nums);
        System.out.println(res);
    }
}

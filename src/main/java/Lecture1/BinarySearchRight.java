package Lecture1;

public class BinarySearchRight {
    // 在arr上，找满足<=value的最右位置
    // 有序数组
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int L = 0;
        int R = nums.length - 1;
        int res = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] <= target) {
                res = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return res;
    }
}

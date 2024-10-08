package Lecture1;

public class BinarySearchLeft {
    // 在arr上，找满足>=value的最左位置
    // 有序数组
    public int SearchLeft(int[] nums, int target) {
        // 总是要考虑边界条件
        if (nums == null || nums.length == 0){
            return -1;
        }
        int L = 0;
        int R = nums.length - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] >= target) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }
}

package Lecture1;

public class BinarySearch {
    // 二分查找(在有序数组中查找一个数)
    // arr: 要搜索的数组
    // x: 要搜索的数
    // 找得到返回下标，找不到返回-1
    public static int binarySearch(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return -1;
    }
}

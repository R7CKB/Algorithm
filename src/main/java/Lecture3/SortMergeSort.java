package Lecture3;

public class SortMergeSort {
    // 递归方法实现
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) >> 1;
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int index = 0;
        int front = left;
        int rear = mid + 1;
        while (front <= mid && rear <= right) {
            help[index++] = arr[front] < arr[rear] ? arr[front++] : arr[rear++];
        }
        // 结束的时候肯定有一个越界了，把剩下的元素都放进help数组
        while (front <= mid) {
            help[index++] = arr[front++];
        }
        while (rear <= right) {
            help[index++] = arr[rear++];
        }
        // 把help数组中的元素放回原数组
        // public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        // src:源数组;
        // srcPos:源数组要复制的起始位置;
        // dest:目的数组;
        // destPos:目的数组放置的起始位置;
        // length:复制的长度.
        System.arraycopy(help, 0, arr, left, help.length);
    }

    // 非递归方法实现
    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 步长
        int mergeSize = 1; // 当前有序的左组长度
        while (mergeSize < N) { // log N
            // 当前左组的，第一个位置
            int L = 0;
            while (L < N) {
                if (mergeSize >= N - L) {
                    break;
                }
                // L....M 左组(mergeSize)
                int M = L + mergeSize - 1;
                // L...M M+1...R 右组(N-M-1)
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        mergeSort2(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}

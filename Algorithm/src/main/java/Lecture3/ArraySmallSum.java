package Lecture3;

/**
 * @Title: ArraySmallSum
 * @Author R7CKB
 * @Package Lecture3
 * @Date 2024/9/25 21:12
 * @description: 数组小和
 * 在一个数组中, 一个数左边比它小的数的总和, 叫数的小和, 所有数的小和累加起来, 叫数组小和. 求数组小和.
 * 例子: `[1,3,4,2,5]`
 * 1 左边比 1 小的数: 没有
 * 3 左边比 3 小的数: 1
 * 4 左边比 4 小的数: 1,3
 * 2 左边比 2 小的数: 1
 * 5 左边比 5 小的数: 1,3,4,2
 * 所以数组的小和为 1+1+3+1+1+3+4+2=16
 */
public class ArraySmallSum {
    // 主函数
    public static int arraySmallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        // L < R
        int M = L + ((R - L) >> 1);
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int index = 0;
        int front = L;
        int rear = M + 1;
        int result = 0;
        while (front <= M && rear <= R) {
            // R - rear + 1 代表右边比arr[front]大的数的个数
            // 首先对1和3进行merge，在此过程中产生一个小和1；然后将1、3和4进行merge，
            // 在此过程中产生小和1、3；然后2和5进行merge，
            // 产生小和2；最后将1、3、4和2、5进行一次merge，1比2小，
            // 所以一共产生n个1的小和，这个n就是当前右边的数的个数，
            // 因为右边有两个数2和5，所以产生2个1的小和，然
            // 后将1填入辅助数组，继续比较3和2，2比3小，
            // 但是2是右边的数，所以不算小和，然后比较3和5，3比5小，
            // 所以产生n个3的小和，因为右侧只有一个数，所以就只产生1个3的小和，同样的，产生1个4的小和
            result += arr[front] < arr[rear] ? (R - rear + 1) * arr[front] : 0;
            help[index++] = arr[front] < arr[rear] ? arr[front++] : arr[rear++];
        }
        while (front <= M) {
            help[index++] = arr[front++];
        }
        while (rear <= R) {
            help[index++] = arr[rear++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(arraySmallSum(arr));
    }
}

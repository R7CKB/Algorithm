package Lecture3;

/**
 * @Title: ReverseOrderPairs
 * @Author R7CKB
 * @Package Lecture3
 * @Date 2024/9/27 20:11
 * @description: 求逆序对
 */
// 本职业是利用归并的思想进行计算的
public class ReverseOrderPairs {
    public int mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process(arr, L, M) +
                process(arr, M + 1, R) +
                merge(arr, L, M, R);
    }

    public int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int index = 0;
        int front = L;
        int rear = M + 1;
        int result = 0;
        while (front <= M && rear <= R) {
            result += arr[front] > arr[rear] ? (M - front + 1) : 0;
            help[index++] = arr[front] < arr[rear] ? arr[front++] : arr[rear++];
        }
        while (front <= M) {
            help[index++] = arr[front++];
        }
        while (rear <= R) {
            help[index++] = arr[rear++];
        }
        // copy数组
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4, 5, 7, 6};
        ReverseOrderPairs rop = new ReverseOrderPairs();
        int result = rop.mergeSort(arr);
        System.out.println(result);
    }
}


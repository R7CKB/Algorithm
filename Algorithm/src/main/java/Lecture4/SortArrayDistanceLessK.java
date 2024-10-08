package Lecture4;

import java.util.PriorityQueue;

/**
 * @Title: SortArrayDistanceLessK
 * @Author R7CKB
 * @Package Lecture4
 * @Date 2024/10/5 20:56
 * @description:
 */
// 已知一个几乎有序的数组. 几乎有序是指, 如果把数组排好顺序的话,
// 每个元素移动的距离一定不超过 k, 并且 k 相对于数组长度来说是比较小的.
// 请选择一个合适的排序策略, 对这个数组进行排序.

// 思路:
// 先把数组中 k+1 的数放到小根堆里面去,
// 然后从小根堆中弹出最小值放到 0 位置,
// 然后将 k+2 位置的数放到小根堆里面去,
// 就这样周而复始, 直到整个数组变得有序.
public class SortArrayDistanceLessK {
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        // 特殊情况
        // 如果 k等于0的情况下,即数组已经有序,则直接返回
        if (k == 0) {
            return;
        }
        // 优先级队列默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 0...K-1 一共k个数放入小根堆
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        // 从这一步开始,最小堆中每加入一个数,都要与前面的数进行比较,
        // 最先被最小堆弹出的数就是最小的数,将最小的数放到数组的第i个位置,
        // 然后一直重复这个过程,直到数组的最后一个数被弹出.
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        // 最后将小根堆中剩余的数全部弹出,放到数组的后面
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}

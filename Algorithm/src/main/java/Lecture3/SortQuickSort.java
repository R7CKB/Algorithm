package Lecture3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SortQuickSort {

    // 先是通过荷兰国旗问题引出快速排序
    // 荷兰国旗问题指的是给定一个数组和一个数(pivot),请把这个数组分成3个部分
    // 左边为小于pivot的元素，右边为大于pivot的元素，中间为等于pivot的元素
    // 从头遍历这个数组
    public static int[] netherlandsFlag(int[] arr, int L, int R, int pivot) {
        // 如果L>R, 则说明数组中没有等于pivot的元素, 直接返回[-1, -1]
        if (L > R) {
            return new int[]{-1, -1};
        }
        // 如果L==R, 则说明数组中只有一个等于pivot的元素, 直接返回[L, R]
        if (L == R) {
            return new int[]{L, R};
        }
        int lessEqual = L - 1;
        // 这里的R和下面的也不一样,这里直接传入arr.length作为R,而不是arr.length-1
        int greatEqual = R;
        int index = L;
        while (index < greatEqual) {
            // 如果数组的 index等于选中的这个数, 则跳过这个数, 索引++
            if (arr[index] == pivot) {
                index++;
                // 如果数组的 index小于选中的这个数,
                // 将小于区域的下一个数与这个数进行交换,
                // 同时将小于区域的边界向右移动一位, 索引++
            } else if (arr[index] < pivot) {
                swap(arr, index++, ++lessEqual);
            } else {
                // 如果数组的 index大于选中的这个数,
                // 将大于区域的上一个数与这个数进行交换,
                // 同时将大于区域的边界向左移动一位, 索引--
                swap(arr, index, --greatEqual);
            }
        }
        // 最后返回小于区域的边界和大于区域的边界
        // 因为lessEqual是小于边界的最后一个位置, greatEqual是大于边界的第一个位置
        // 所以返回值应该是[lessEqual+1, greatEqual-1]
        // 即为等于pivot的元素的左右边界
        return new int[]{lessEqual + 1, greatEqual - 1};
    }

    // 改编的荷兰国旗问题,减少一个参数,直接将arr[R]作为pivot,
    // 因为之前已经把数组中随机的一个数与arr[R]进行了交换
    // 所以可以减少一个参数, 直接传入数组
    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] >arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;  // < 区 右边界
        int more = R;  // > 区 左边界
        int index = L;
        while (index < more) {  // 当前位置，不能和 >区的左边界撞上
            // 等于的逻辑分支
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
//				swap(arr, less + 1, index);
//				less++;
//				index++;
                // 一行代码解决了小于的逻辑分支
                // 将小于区域的下一个值进行交换
                // 同时将小于区域的右边界向右移一位
                // 同时index++
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        // 最后交换pivot和more位置, 使得pivot在数组的中间位置
        // 所以在完成一次排序前，划分值要保持在最后一位，然后最后再将划分值交换到大于区域的左边界
        // L....less   less+1...more-1   more...R-1   R
        //  小于区域         等于区域         大于区域
        swap(arr, more, R);
        // 返回一个数组,数组的长度为2,且为等于区域arr[R]的范围
        // L....less   less+1...more   more+1...R
        //  小于区域        等于区域      大于区域
        return new int[]{less + 1, more};
    }

    // 交换函数
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机快排的关键步骤,随机挑选一个数作为pivot
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    // 非递归方法不管是使用栈还是队列, 都需要保存待排序的范围, 所以都需要一个辅助类来保存范围
    // 而且栈和队列其实都是模拟调用递归过程,栈是先将左边的范围压入栈,然后继续将左边都排好序,之后再处理右边的范围,
    // 而队列则是先将左边的范围压入队列,再处理右边的范围,再处理左边的范围,再处理右边的范围,一直到队列为空


    // 快排非递归版本需要的辅助类
    // 要处理的是什么范围上的排序
    public static class Op {
        public int l;
        public int r;

        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }

    // 快排3.0 非递归版本 用栈来执行
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 同递归一样,先谁将挑选一个数作为pivot,然后进行荷兰国旗问题
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] equalArea = netherlandsFlag(arr, 0, N - 1);
        // 得出荷兰国旗问题的左边界和右边界
        int el = equalArea[0];
        int er = equalArea[1];
        // 建立一个栈, 保存待排序的范围
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, el - 1));
        stack.push(new Op(er + 1, N - 1));
        // 栈不为空时, 依次弹出栈顶的范围, 并对其进行排序
        // 其实就是手动实现了递归调用
        while (!stack.isEmpty()) {
            Op op = stack.pop(); // op.l ... op.r
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = netherlandsFlag(arr, op.l, op.r);
                el = equalArea[0];
                er = equalArea[1];
                stack.push(new Op(op.l, el - 1));
                stack.push(new Op(er + 1, op.r));
            }
        }
    }

    // 快排3.0 非递归版本 用队列来执行
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] equalArea = netherlandsFlag(arr, 0, N - 1);
        int el = equalArea[0];
        int er = equalArea[1];
        // 建立一个队列, 保存待排序的范围
        Queue<Op> queue = new LinkedList<>();
        queue.offer(new Op(0, el - 1));
        queue.offer(new Op(er + 1, N - 1));
        while (!queue.isEmpty()) {
            Op op = queue.poll();
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = netherlandsFlag(arr, op.l, op.r);
                el = equalArea[0];
                er = equalArea[1];
                queue.offer(new Op(op.l, el - 1));
                queue.offer(new Op(er + 1, op.r));
            }
        }
    }

    public static void quickSort4(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process4(arr, 0, arr.length - 1);
    }

    public static void process4(int[] arr, int L, int R) {
        if (L > R) {
            return;
        }
        int partition = partition(arr, L, R);
        process4(arr, L, partition - 1);
        process4(arr, partition + 1, R);
    }

    // 只是用小于等于区域和大于区域,不单独区分等于区域
    private static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        while (L < R) {
            // 如果小于的话,将其与小于区域的后一个数进行交换.
            if (arr[L] <= arr[R]) {
                swap(arr, L, ++lessEqual);
            }
            // 不管怎样,L一直加1
            L++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{14, 13, 12, 11, 10, 9, 8, 5, 5, 5, 4, 3, 2, 1, 0};
        quickSort4(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

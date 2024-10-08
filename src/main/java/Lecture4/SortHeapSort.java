package Lecture4;

/**
 * @Title: SortHeapSort
 * @Author R7CKB
 * @Package Lecture4
 * @Date 2024/10/5 19:16
 * @description: 堆排序
 */
// 一一般来说都是建议从左孩子开始,因为堆是按照数组来排列的,
// 如果一个父节点又能没有右孩子,但是会有左孩子,如果只从右孩子开始看的话,可能就会左孩子忽略掉
public class SortHeapSort {
    // 堆排序的额外空间复杂度为O(1)
    // 无论是大根堆还是小根堆，都可以用堆排序
    // 大根堆应该就是升序排序，小根堆应该就是降序排序
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // O(N*logN)
        // 从上到下建堆,时间复杂度为O(N*logN)
//        for (int i = 0; i < arr.length; i++) { // O(N)
//            heapInsert(arr, i); // O(logN)
//        }
        // 从下往上建堆,时间复杂度为O(N)
        // arr.length/2-1是最后一个非叶子节点的索引
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            // 这一步建立的是大根堆
//            bigHeapify(arr, i, arr.length); // O(logN)
            // 这一步建立的是小根堆
            smallHeapify(arr, i, arr.length); // O(logN)
        }
        int size = arr.length;
        // 交换堆顶元素和最后一个元素,时间复杂度为O(1)
        swap(arr, 0, --size);
        // O(N*logN)
        while (size > 0) {
            // 这一步将堆顶元素放到最后一个元素的位置
            // 然后对最后一个元素重新建堆,时间复杂度为O(logN)
//            bigHeapify(arr, 0, size);
            smallHeapify(arr, 0, size);
            // 交换堆顶元素和最后一个元素,时间复杂度为O(1)
            // 对数组中的每一个数都重新建堆
            swap(arr, 0, --size);
        }

    }


    // 实现降序排序的小根堆
    private static void smallHeapify(int[] heap, int index, int size) {
        // left为当前节点的左子节点
        int left = index * 2 + 1;
        // 右孩子不越界
        while (left < size) {
            // 左孩子不越界,且左孩子大于右孩子,先在左右孩子之间比出一个最大值
            int smallest = (left + 1) < size && heap[left + 1] < heap[left] ? left + 1 : left;
            // 再在当前节点和最大值之间比出一个最大值
            smallest = heap[smallest] < heap[index] ? smallest : index;
            // 如果最大值是当前节点,说明已经是大根堆,结束,不用再下沉了
            if (smallest == index) {
                break;
            }
            swap(heap, smallest, index);
            index = smallest;
            left = index * 2 + 1;
        }
    }

    // 实现升序排序的大根堆
    private static void bigHeapify(int[] heap, int index, int size) {
        // left为当前节点的左子节点
        int left = index * 2 + 1;
        // 右孩子不越界
        while (left < size) {
            // 左孩子不越界,且左孩子大于右孩子,先在左右孩子之间比出一个最大值
            int biggest = (left + 1) < size && heap[left + 1] > heap[left] ? left + 1 : left;
            // 再在当前节点和最大值之间比出一个最大值
            biggest = heap[biggest] > heap[index] ? biggest : index;
            // 如果最大值是当前节点,说明已经是大根堆,结束,不用再下沉了
            if (biggest == index) {
                break;
            }
            swap(heap, biggest, index);
            index = biggest;
            left = index * 2 + 1;
        }
    }

    // arr[index]刚来的数，往上
    public static void heapInsert(int[] arr, int index) {
        // 大根堆
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1, 5, 4, 7, 6, 8, 10,9};
        heapSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}

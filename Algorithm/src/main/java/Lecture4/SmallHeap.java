package Lecture4;

/**
 * @Title: Heap
 * @Author R7CKB
 * @Package Lecture4
 * @Date 2024/10/4 19:29
 * @description: 小根堆的实现
 */
public class SmallHeap {
    private int[] heap;
    private final int limit;
    private int size;

    public SmallHeap(int limit) {
        heap = new int[limit];
        this.limit = limit;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean isFull() {
        return size == limit;
    }

    public void insert(int value) {
        if (isFull()) {
            System.out.println("Heap is full");
            return;
        }
        heap[size] = value;
        // 然后对堆进行调整
        heapInsert(heap, size++);
    }

    // 用户此时，让你返回最大值(或是最小值)，并且在大(小)根堆中，把最大(小)值删掉
    // 剩下的数，依然保持大(小)根堆组织
    public int pop() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return -1;
        }
        int result = heap[0];
        // 只要弹出一个数,我们就都将他和数组的最开始位置进行交换
        // 然后对堆进行调整
        swap(heap, 0, --size);
        // 然后对堆进行调整
        heapify(heap, 0, size);
        return result;
    }

    public void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 新加进来的数，现在停在了index位置，请依次往上移动，
    // 等到移动到0位置或者干不掉自己的父亲了，停！
    public void heapInsert(int[] heap, int index) {
        // 因为数组的下标是从0位置开始的,所以对于父节点的下标应该是(index-1)/2
        // 如果是小根堆的话,我们只需要这样
        // 其实也不需要index>0,因为index=0的话,(0-1)/2 = 0
        while (heap[index] < heap[(index - 1) / 2]) {
            swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
        // 大根堆的操作则相反
//        while (heap[(index - 1) / 2] < heap[index]) {
//            swap(heap, index, (index - 1) / 2);
//            index = (index - 1) / 2;
//        }
    }

    // 然后就是heapify方法了,这个方法比heapInsert更复杂一点
    // 我们默认index位置都从0开始,size是堆的大小
    public void heapify(int[] heap, int index, int size) {
        // 因为数组从0位置开始,所以对于任意一个节点来说,
        // 它的左孩子的下标应该是2*index+1,右孩子的下标应该是2*index+2
        int left = index * 2 + 1;
        // while指的是有左孩子的情况下,进行heapify
        while (left < size) {
            // 标准的有没有右孩子:
            // 1. 如果有右孩子,并且右孩子大于左孩子,那么index指向右孩子
            // 2. 如果没有右孩子,或者右孩子小于左孩子,那么index指向左孩子
            // 同时得保证右孩子不越界,且右孩子比左孩子小的话
            // 这时候的smallest为小的数的数组下标
            int smallest = left + 1 < size && heap[left + 1] < heap[left] ? left + 1 : left;
            smallest = heap[smallest] < heap[index] ? smallest : index;
            // 如果当前节点已经是最小值了,就不用再下沉了
            if (smallest == index) {
                break;
            }
            // 交换index和smallest
            swap(heap, index, smallest);
            // smallest指的是父子三人中最小的下标
            index = smallest;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        SmallHeap heap = new SmallHeap(20);
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);
        heap.insert(9);
        heap.insert(4);
        heap.insert(8);
        heap.insert(2);
        heap.insert(6);
        heap.insert(0);
        heap.insert(5);
        heap.insert(7);
        heap.insert(1);
        while (!heap.isEmpty()) {
            System.out.println(heap.pop());
        }
    }
}

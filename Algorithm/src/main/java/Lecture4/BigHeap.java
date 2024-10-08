package Lecture4;

/**
 * @Title: BigHeap
 * @Author R7CKB
 * @Package Lecture4
 * @Date 2024/10/5 18:43
 * @description: 大根堆
 */
public class BigHeap {
    private int[] heap;
    private final int limit;
    private int size;

    public BigHeap(int limit) {
        this.limit = limit;
        this.heap = new int[limit];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == limit;
    }

    public void insert(int value) {
        if (isFull()) {
            System.out.println("Heap is full");
            return;
        }
        heap[size] = value;
        heapInsert(heap, size++);
    }

    private void heapInsert(int[] heap, int index) {
        // 当size为0时,也成立
        // 如果当前节点比父节点大,往上交换
        while (heap[index] > heap[(index - 1) / 2]) {
            swap(heap, (index - 1) / 2, index);
            index = (index - 1) / 2;
        }
        // 小根堆操作相反
//        while (heap[index] < heap[(index - 1) / 2]) {
//            swap(heap, index, (index - 1) / 2);
//            index = (index - 1) / 2;
//        }
    }

    private void swap(int[] heap, int i, int j) {
        heap[i] = heap[i] ^ heap[j];
        heap[j] = heap[i] ^ heap[j];
        heap[i] = heap[i] ^ heap[j];
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return -1;
        }
        int result = heap[0];
        // 因为数组从0开始,所以交换size--
        swap(heap, 0, --size);
        heapify(heap, 0, size);
        return result;
    }

    private void heapify(int[] heap, int index, int size) {
        // right为当前节点的右子节点
        int right = index * 2 + 2;
        // 右孩子不越界
        while (right < size) {
            // 左孩子不越界,且左孩子大于右孩子,先在左右孩子之间比出一个最大值
            int biggest = right - 1 < size && heap[right - 1] > heap[right] ? right - 1 : right;
            // 再在当前节点和最大值之间比出一个最大值
            biggest = heap[biggest] > heap[index] ? biggest : index;
            // 如果最大值是当前节点,说明已经是大根堆,结束,不用再下沉了
            if (biggest == index) {
                break;
            }
            swap(heap, biggest, index);
            index = biggest;
            right = index * 2 + 2;
        }
    }

    public static void main(String[] args) {
        BigHeap bigHeap = new BigHeap(10);
        bigHeap.insert(1);
        bigHeap.insert(2);
        bigHeap.insert(3);
        bigHeap.insert(1);
        bigHeap.insert(9);
        bigHeap.insert(4);
        bigHeap.insert(8);
        bigHeap.insert(2);
        bigHeap.insert(6);
        bigHeap.insert(10);
        while (!bigHeap.isEmpty()) {
            System.out.println(bigHeap.pop());
        }
    }
}




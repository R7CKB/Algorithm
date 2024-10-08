package Lecture2;

/**
 * @Title: RingArray
 * @Author R7CKB
 * @Package Lecture2
 * @Date 2024/10/2 18:39
 * @description: 循环队列
 */
public class RingArray {
    // capacity为循环队列的初始值
    // front和rear分别指向队列的头和尾
    public static class MyQueue {
        private int[] arr;
        private int front;// end
        private int rear;// begin
        private int size;
        private final int capacity;

        public MyQueue(int limit) {
            arr = new int[limit];
            front = 0;
            rear = 0;
            size = 0;
            this.capacity = limit;
        }

        public void push(int value) {
            if (size == capacity) {
                System.out.println("Queue is full");
            }
            size++;
            arr[rear] = value;
            rear = nextIndex(rear);
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
            }
            size--;
            int result = arr[front];
            front = nextIndex(front);
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return (i + 1) % capacity;
        }

    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(5);
        myQueue.push(1);
        myQueue.pop();
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);
        myQueue.push(6);
        myQueue.pop();
    }
}

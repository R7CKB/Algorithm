package Lecture2;

public class DoubleNode {
    public int value;
    public DoubleNode next;
    public DoubleNode prev;


    public DoubleNode(int value) {
        this.value = value;
    }

    public DoubleNode noRecursionReverse(DoubleNode head) {
        DoubleNode prev = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            head.prev = next;
            prev = head;
            head = next;
        }
        return prev;
    }

    public DoubleNode recursionReverse(DoubleNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleNode newHead = recursionReverse(head.next);
        DoubleNode tail = head.next.next;
        head.next.next = head;
        head.next.prev = tail;
        // 处理头节点的特殊情况
        if (head.prev == null) {
            head.prev = head.next;
            head.next = null;
        }
        return newHead;
    }

    public static void main(String[] args) {
        DoubleNode head = new DoubleNode(1);
        head.next = new DoubleNode(2);
        head.next.next = new DoubleNode(3);
        head.next.prev = head; // 2->1
        head.next.next.prev = head.next; // 3->2
        head.next.next.next = new DoubleNode(4);
        head.next.next.next.prev = head.next.next; // 4->3
        head.next.next.next.next = new DoubleNode(5);
        head.next.next.next.next.prev = head.next.next.next; // 5->4


        DoubleNode newHead = head.recursionReverse(head);
        while (newHead != null) {
            System.out.print(newHead.value + " ");
            newHead = newHead.next;
        }
    }
}

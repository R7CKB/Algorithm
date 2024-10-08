package Lecture2;

public class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    // 迭代方式反转链表
    //  https://leetcode-cn.com/problems/reverse-linked-list/
    public Node noRecursionReverse(Node head) {
        Node prev = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            // 确定前一个节点
            prev = head;
            head = next;
        }
        return prev;
    }

    // 递归方式反转链表
    //  https://leetcode-cn.com/problems/reverse-linked-list/
    public Node recursionReverse(Node head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        // recursive case
        Node newHead = recursionReverse(head.next);
        head.next.next = head;  // 翻转指针;
        head.next = null;
        return newHead;
    }

    // 删除链表中指定值节点
    // https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/description/
    public void deleteNode(Node head, int value) {
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }
        Node prev = head;
        Node curr = head;
        while (curr != null) {
            if (curr.value == value) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        Node n = new Node(1);
        n.next = new Node(2);
        n.next.next = new Node(3);
        n.next.next.next = new Node(4);
        n.next.next.next.next = new Node(5);
        Node head = n.noRecursionReverse(n);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        Node head2 = n1.recursionReverse(n1);
        while (head2 != null) {
            System.out.print(head2.value + " ");
            head2 = head2.next;
        }
    }
}

package Lecture2;

import java.util.Deque;
import java.util.LinkedList;

public class DequeImplementStack2 {
    Deque<Integer> deque;

    //  方法二: 一个队列
    public DequeImplementStack2() {
        deque = new LinkedList<>();
    }

    public void push(int x) {
        deque.offer(x);
        for (int i = 0; i < deque.size() - 1; i++) {
            deque.offer(deque.poll());
        }
    }

    public int pop() {
        return deque.poll();
    }

    public int top() {
        return deque.peek();
    }

    public boolean empty() {
        return deque.isEmpty();
    }
}

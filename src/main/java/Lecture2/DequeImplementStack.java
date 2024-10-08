package Lecture2;

import java.util.Deque;
import java.util.LinkedList;

public class DequeImplementStack {
    // 方法一: 两个队列
    // 让一个队列一直保持空状态
    Deque<Integer> deque1;
    Deque<Integer> deque2;

    public DequeImplementStack() {
        deque1 = new LinkedList<>();
        deque2 = new LinkedList<>();
    }

    public void push(int x) {
        deque1.offer(x);
    }

    public int pop() {
        while(deque1.size() > 1){
            deque2.offer(deque1.poll());
        }
        int result = deque1.poll();
        Deque<Integer> temp = deque1;
        deque1 = deque2;
        deque2 = temp;
        return result;
    }

    public int top() {
        while(deque1.size() > 1){
            deque2.offer(deque1.poll());
        }
        int result = deque1.poll();
        deque2.offer(result);
        Deque<Integer> temp = deque1;
        deque1 = deque2;
        deque2 = temp;
        return result;
    }

    public boolean empty() {
        return deque1.isEmpty();
    }

}


package Lecture2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// https://leetcode.cn/problems/min-stack-lcci/description/
public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        if (minStack.isEmpty() || x <= this.getMin()) {
            minStack.push(x);
        }
        stack.push(x);
    }

    public void pop() {
        int value = stack.pop();
        if (value == this.getMin()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

// 或者如下(LeetCode解法)
class MinStack2 {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack2() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

// 或者再如下
class MinStack3 {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack3() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        if (minStack.isEmpty() || x < getMin()) {
            minStack.push(x);
        } else {
            minStack.push(minStack.peek());
        }
        stack.push(x);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

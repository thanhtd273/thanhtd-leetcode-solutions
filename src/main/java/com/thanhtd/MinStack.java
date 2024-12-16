package com.thanhtd;

import java.util.Stack;

public class MinStack {
    private final Stack<Integer> stack;

    private int minEle;

    public MinStack() {
        stack = new Stack<>();
        minEle = 0;
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            minEle = val;
            stack.push(val);
        } else if (val < minEle) {
            stack.push(2 * val - minEle);
            minEle = val;
        } else {
            stack.push(val);
        }
    }

    public void pop() {
        int val = stack.pop();
        if (val < minEle) {
            minEle = 2 * minEle - val;
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minEle;
    }
}

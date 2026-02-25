package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:09
 * @Description: 包含min函数的栈
 */
public class includeMinStack {

    Stack<Integer> stack = new Stack<Integer>();

    public void push(int node) {
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        int min = stack.peek();
        int tmp = 0;
        for (Integer integer : stack) {
            tmp = integer;
            if (min > tmp) {
                min = tmp;
            }
        }
        return min;
    }
}

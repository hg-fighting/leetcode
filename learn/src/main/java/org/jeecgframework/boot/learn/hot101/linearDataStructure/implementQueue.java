package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:05
 * @Description: 用两个栈实现队列
 */
public class implementQueue {

    Stack<Integer> stack1 = new Stack<Integer>();

    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}

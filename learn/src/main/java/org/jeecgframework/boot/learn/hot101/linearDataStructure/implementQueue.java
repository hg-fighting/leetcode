package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:05
 * @Description: 用两个栈实现队列
 * 描述
 * 用两个栈来实现一个队列，分别完成在队列尾部插入整数(push)和在队列头部删除整数(pop)的功能。 队列中的元素为int类型。保证操作合法，即保证pop操作时队列内已有元素。
 * 数据范围： n≤1000
 * 要求：存储n个元素的空间复杂度为 O(n) ，插入与删除的时间复杂度都是 O(1)
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

package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:09
 * @Description: 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * 数据范围：操作数量满足 0≤n≤300  ，输入的元素满足 ∣val∣≤10000
 * 进阶：栈的各个操作的时间复杂度是 O(1) ，空间复杂度是 O(n)
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

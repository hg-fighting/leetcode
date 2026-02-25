package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:12
 * @Description: 有效的括号
 * 描述
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 * 数据范围：字符串长度 0≤n≤10000
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class isValid {

    public boolean isValid(String s) {
        //空字符串为有效
        if (s.isEmpty()) {
            return true;
        }
        //栈用于存储左括号
        Stack<Character> stack = new Stack<>();
        //遍历字符串
        for (char c : s.toCharArray()) {
            //左括号入栈
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                //右括号出栈
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                //判断是否匹配
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        //栈为空则所有左括号都有匹配的右括号
        return stack.isEmpty();
    }
}

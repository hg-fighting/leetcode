package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:12
 * @Description: 有效的括号
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

package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:47
 * @Description: 表达式求值
 * 描述
 * 请写一个整数计算器，支持加减乘三种运算和括号。
 * 数据范围：0≤∣s∣≤100  ，保证计算结果始终在整型范围内
 * 要求：空间复杂度： O(n) ，时间复杂度 O(n)
 * 示例1
 * 输入：
 * "1+2"
 * 复制
 * 返回值：
 * 3
 * 复制
 * 示例2
 * 输入：
 * "(2*(3-4))*5"
 * 复制
 * 返回值：
 * -10
 * 复制
 * 示例3
 * 输入：
 * "3+2*3*4-1"
 * 复制
 * 返回值：
 * 26
 * 复制
 * 备注：
 * 0≤∣s∣≤100
 */
public class solve {

    public ArrayList<Integer> function(String s, int index) {
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char op = '+';
        int i;
        for (i = index; i < s.length(); i++) {
            //数字转换成int数字
            //判断是否为数字
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + s.charAt(i) - '0';
                if (i != s.length() - 1) {
                    continue;
                }
            }
            //碰到'('时，把整个括号内的当成一个数字处理
            if (s.charAt(i) == '(') {
                //递归处理括号
                ArrayList<Integer> res = function(s, i + 1);
                num = res.get(0);
                i = res.get(1);
                if (i != s.length() - 1) {
                    continue;
                }
            }
            switch (op) {
                //加减号先入栈
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    //相反数
                    stack.push(-num);
                    break;
                //优先计算乘号
                case '*':
                    int temp = stack.pop();
                    stack.push(temp * num);
                    break;
            }
            num = 0;
            //右括号结束递归
            if (s.charAt(i) == ')') {
                break;
            } else {
                op = s.charAt(i);
            }
        }
        int sum = 0;
        //栈中元素相加
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(sum);
        temp.add(i);
        return temp;
    }

    public int solve(String s) {
        ArrayList<Integer> res = function(s, 0);
        return res.get(0);
    }

}

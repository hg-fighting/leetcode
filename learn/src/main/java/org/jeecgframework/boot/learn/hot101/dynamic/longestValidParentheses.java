package org.jeecgframework.boot.learn.hot101.dynamic;

import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  16:07
 * @Description: 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class longestValidParentheses {

    /**
     * 栈
     */
    public int longestValidParentheses(String s) {
        int res = 0;
        //记录上一次连续括号结束的位置
        int start = -1;
        Stack<Integer> st = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
            //左括号入栈
            if (s.charAt(i) == '(')
                st.push(i);
                //右括号
            else {
                //如果右括号时栈为空，不合法，设置为结束位置
                if (st.isEmpty())
                    start = i;
                else {
                    //弹出左括号
                    st.pop();
                    //栈中还有左括号，说明右括号不够，减去栈顶位置就是长度
                    if (!st.empty())
                        res = Math.max(res, i - st.peek());
                        //栈中没有括号，说明左右括号行号，减去上一次结束的位置就是长度
                    else
                        res = Math.max(res, i - start);
                }
            }
        }
        return res;
    }

    /**
     * 动态规划
     */
    public int longestValidParentheses2(String s) {
        int res = 0;
        //长度为0的串或者空串，返回0
        if (s.isEmpty())
            return res;
        //dp[i]表示以下标为i的字符为结束点的最长合法括号长度
        int[] dp = new int[s.length()];
        //第一位不管是左括号还是右括号都是0，因此不用管
        for (int i = 1; i < s.length(); i++) {
            //取到左括号记为0，有右括号才合法
            if (s.charAt(i) == ')') {
                //如果该右括号前一位就是左括号
                if (s.charAt(i - 1) == '(')
                    //计数+
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                    //找到这一段连续合法括号序列前第一个左括号做匹配
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(')
                    dp[i] = (i - dp[i - 1] > 1 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
            }
            //维护最大值
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

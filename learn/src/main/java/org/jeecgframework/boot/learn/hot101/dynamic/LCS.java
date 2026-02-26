package org.jeecgframework.boot.learn.hot101.dynamic;

import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 最长公共子序列
 * 描述
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class LCS {

    public String LCS(String s1, String s2) {
        //只要有一个空字符串便不会有子序列
        if (s1.isEmpty() || s2.isEmpty())
            return "-1";
        int len1 = s1.length();
        int len2 = s2.length();
        //dp[i][j]表示第一个字符串到第i位，第二个字符串到第j位为止的最长公共子序列长度
        int[][] dp = new int[len1 + 1][len2 + 1];
        //遍历两个字符串每个位置求的最长长度
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                //遇到两个字符相等
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    //来自于左上方
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    //遇到的两个字符不同
                else
                    //来自左边或者上方的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        //从动态规划数组末尾开始
        int i = len1, j = len2;
        Stack<Character> s = new Stack<Character>();
        while (dp[i][j] != 0) {
            //来自于左方向
            if (dp[i][j] == dp[i - 1][j])
                i--;
                //来自于上方向
            else if (dp[i][j] == dp[i][j - 1])
                j--;
                //来自于左上方向
            else if (dp[i][j] > dp[i - 1][j - 1]) {
                i--;
                j--;
                //只有左上方向才是字符相等的情况，入栈，逆序使用
                s.push(s1.charAt(i));
            }
        }
        StringBuilder res = new StringBuilder();
        //拼接子序列
        while (!s.isEmpty())
            res.append(s.pop());
        //如果两个完全不同，返回字符串为空，则要改成-1
        return (!res.isEmpty()) ? res.toString() : "-1";
    }
}

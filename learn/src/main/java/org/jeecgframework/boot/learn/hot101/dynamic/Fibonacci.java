package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 斐波那契数列
 * 描述
 * 大家都知道斐波那契数列，现在要求输入一个正整数 n ，请你输出斐波那契数列的第 n 项。
 * fib(x) = fib(x-1) + fib(x-2)
 */
public class Fibonacci {

    // 动态规划
    public int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 递归
    public int Fibonacci2(int n) {
        if (n <= 2) {
            return n;
        }
        return Fibonacci2(n - 1) + Fibonacci2(n - 2);
    }
}

package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 跳台阶
 * 描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class jumpFloor {

    /**
     * 动态规划
     */
    public int jumpFloor(int number) {
        if (number <= 1) {
            return number;
        }
        int[] dp = new int[number + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= number; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[number];
    }

    /**
     * 递归
     */
    public int jumpFloor2(int number) {
        if (number <= 2) {
            return number;
        }
        return jumpFloor2(number - 1) + jumpFloor2(number - 2);
    }
}

package org.jeecgframework.boot.learn.hot101.dynamic;

import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 换钱的最少货币数
 * 描述
 * 给定数组 arr，arr 中所有的值都为正整数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数 aim 代表要找的钱数，求组成 aim 的最少货币数。
 * 如果无解，请返回-1.
 */
public class minMoney {

    public int minMoney(int[] arr, int aim) {
        //小于1的都返回0
        if (aim < 1)
            return 0;
        int[] dp = new int[aim + 1];
        //dp[i]表示凑齐i元最少需要多少货币数
        Arrays.fill(dp, aim + 1);
        dp[0] = 0;
        //遍历1-aim元
        for (int i = 1; i <= aim; i++) {
            //每种面值的货币都要枚举
            for (int k : arr) {
                //如果面值不超过要凑的钱才能用
                if (k <= i)
                    //维护最小值
                    dp[i] = Math.min(dp[i], dp[i - k] + 1);
            }
        }
        //如果最终答案大于aim代表无解
        return dp[aim] > aim ? -1 : dp[aim];
    }
}

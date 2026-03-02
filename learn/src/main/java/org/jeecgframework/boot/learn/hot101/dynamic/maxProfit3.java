package org.jeecgframework.boot.learn.hot101.dynamic;

import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  16:34
 * @Description: 买卖股票的最佳时机 III
 * 假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
 * 1. 你最多可以对该股票有两笔交易操作，一笔交易代表着一次买入与一次卖出，但是再次购买前必须卖出之前的股票
 * 2. 如果不能获取收益，请返回0
 * 3. 假设买入卖出均无手续费
 */
public class maxProfit3 {

    /**
     * dp[i][0]表示到第i天为止没有买过股票的最大收益
     * dp[i][1]表示到第i天为止买过一次股票还没有卖出的最大收益
     * dp[i][2]表示到第i天为止买过一次也卖出过一次股票的最大收益
     * dp[i][3]表示到第i天为止买过两次只卖出过一次股票的最大收益
     * dp[i][4]表示到第i天为止买过两次同时也买出过两次股票的最大收益
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][5];
        //初始化dp为最小
        Arrays.fill(dp[0], -10000);
        //第0天不持有状态
        dp[0][0] = 0;
        //第0天持有股票
        dp[0][1] = -prices[0];
        //状态转移
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        //选取最大值，可以只操作一次
        return Math.max(dp[n - 1][2], Math.max(0, dp[n - 1][4]));
    }

}

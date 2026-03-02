package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  16:29
 * @Description: 买卖股票的最佳时机 II
 * 假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
 * 1. 你可以多次买卖该只股票，但是再次购买前必须卖出之前的股票
 * 2. 如果不能获取收益，请返回0
 * 3. 假设买入卖出均无手续费
 */
public class maxProfit2 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        //dp[i][0]表示某一天不持股到该天为止的最大收益，dp[i][1]表示某天持股，到该天为止的最大收益
        int[][] dp = new int[n][2];
        //第一天不持股，总收益为0
        dp[0][0] = 0;
        //第一天持股，总收益为减去该天的股价
        dp[0][1] = -prices[0];
        //遍历后续每天，状态转移
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        //最后一天不持股，到该天为止的最大收益
        return dp[n - 1][0];
    }

    /**
     * 贪心算法
     */
    public int maxProfit2(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            //只要某段在递增就有收益
            if (prices[i - 1] < prices[i])
                //收益累加
                res += prices[i] - prices[i - 1];
        }
        return res;
    }

}

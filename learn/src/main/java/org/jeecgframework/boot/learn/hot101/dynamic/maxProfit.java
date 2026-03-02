package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  16:25
 * @Description: 买卖股票的最佳时机
 * 假设你有一个数组prices，长度为n，其中prices[i]是股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
 * 1.你可以买入一次股票和卖出一次股票，并非每天都可以买入或卖出一次，总共只能买入和卖出一次，且买入必须在卖出的前面的某一天
 * 2.如果不能获取到任何利润，请返回0
 * 3.假设买入卖出均无手续费
 * 示例 1:
 * 输入: [8,9,2,5,4,7,1]
 * 输出: 5
 * 解释: 在第 3 天(股票价格 = 2)的时候买入，在第 5 天(股票价格 = 7)的时候卖出, 这笔交易所能获得利润 = 7-2 = 5
 * 注意: 你不能在第 1 天(股票价格 = 8)的时候买入, 然后在第 2 天(股票价格 = 9)的时候卖出, 因为这样属于同时参与了多笔交易, 你必须在再次购买前出售掉之前的股票
 */
public class maxProfit {


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
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        //最后一天不持股，到该天为止的最大收益
        return dp[n - 1][0];
    }

    /**
     * 贪心算法
     * 遍历数组，记录当前的最小值，以及当前的最大收益
     */
    public int maxProfit2(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }

}

package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 不同路径
 * 描述
 * 一个机器人在m×n大小的地图的左上角（起点）。
 * 机器人每次可以向下或向右移动。机器人要到达地图的右下角（终点）。
 * 可以有多少种不同的路径从起点走到终点？
 */
public class uniquePaths {

    /**
     * 递归
     */
    public int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    /**
     * 动态规划
     */
    public int uniquePaths2(int m, int n) {
        //dp[i][j]表示大小为i*j的矩阵的路径数量
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //只有1行的时候，只有一种路径
                if (i == 1) {
                    dp[i][j] = 1;
                    continue;
                }
                //只有1列的时候，只有一种路径
                if (j == 1) {
                    dp[i][j] = 1;
                    continue;
                }
                //路径数等于左方格子的路径数加上上方格子的路径数
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

}

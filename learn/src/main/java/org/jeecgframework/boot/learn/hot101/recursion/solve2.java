package org.jeecgframework.boot.learn.hot101.recursion;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  15:11
 * @Description: 矩阵最长递增路径
 * 描述
 * 给定一个 n 行 m 列矩阵 matrix ，矩阵内所有数均为非负整数。 你需要在矩阵中找到一条最长路径，使这条路径上的元素是递增的。并输出这条最长路径的长度。
 * 这个路径必须满足以下条件：
 * 1. 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外。
 * 2. 你不能走重复的单元格。即每个格子最多只能走一次。
 * 数据范围：1≤n,m≤1000，0≤matrix[i][j]≤1000
 * 进阶：空间复杂度 O(nm) ，时间复杂度 O(nm)
 */
public class solve2 {

    //记录四个方向
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int n, m;

    //深度优先搜索，返回最大单元格数
    public int dfs(int[][] matrix, int[][] dp, int i, int j) {
        if (dp[i][j] != 0)
            return dp[i][j];
        dp[i][j]++;
        for (int k = 0; k < 4; k++) {
            int nexti = i + dirs[k][0];
            int nextj = j + dirs[k][1];
            //判断条件
            if (nexti >= 0 && nexti < n && nextj >= 0 && nextj < m &&
                    matrix[nexti][nextj] > matrix[i][j])
                dp[i][j] = Math.max(dp[i][j], dfs(matrix, dp, nexti, nextj) + 1);
        }
        return dp[i][j];
    }

    public int solve(int[][] matrix) {
        //矩阵不为空
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int res = 0;
        n = matrix.length;
        m = matrix[0].length;
        //i，j处的单元格拥有的最长递增路径
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                //更新最大值
                res = Math.max(res, dfs(matrix, dp, i, j));
        return res;
    }
}

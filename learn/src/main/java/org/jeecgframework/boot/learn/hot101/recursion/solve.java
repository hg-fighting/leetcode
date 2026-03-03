package org.jeecgframework.boot.learn.hot101.recursion;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  9:47
 * @Description: 岛屿数量
 * 描述
 * 给一个01矩阵，1代表是陆地，0代表海洋， 如果两个1相邻，那么这两个1属于同一个岛。我们只考虑上下左右为相邻。
 * 岛屿: 相邻陆地可以组成一个岛屿（相邻:上下左右） 判断岛屿个数。
 * 例如：
 * 输入
 * [[1,1,0,0,0],[0,1,0,1,1],[0,0,0,1,1],[0,0,0,0,0],[0,0,1,1,1]]
 * 对应的输出为3
 * (注：存储的01数据其实是字符'0','1')
 */
public class solve {

    //深度优先遍历与i，j相邻的所有1
    public void dfs(char[][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;
        // 置为0
        grid[i][j] = '0';
        //后续四个方向遍历
        if (i - 1 >= 0 && grid[i - 1][j] == '1')
            dfs(grid, i - 1, j);
        if (i + 1 < n && grid[i + 1][j] == '1')
            dfs(grid, i + 1, j);
        if (j - 1 >= 0 && grid[i][j - 1] == '1')
            dfs(grid, i, j - 1);
        if (j + 1 < m && grid[i][j + 1] == '1')
            dfs(grid, i, j + 1);
    }

    public int solve(char[][] grid) {
        int n = grid.length;
        //空矩阵的情况
        if (n == 0)
            return 0;
        int m = grid[0].length;
        //记录岛屿数
        int count = 0;
        //遍历矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //遍历到1的情况
                if (grid[i][j] == '1') {
                    //计数
                    count++;
                    //将与这个1相邻的所有1置为0
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

}

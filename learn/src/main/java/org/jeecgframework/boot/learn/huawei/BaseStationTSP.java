package org.jeecgframework.boot.learn.huawei;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/4  16:38
 * @Description: 基站维修工程师
 * 题目描述
 * 小王是一名基站维护工程师，负责某区域的基站维护。
 * 某地方有 n 个基站（1<n<10），已知各基站之间的距离 s（0<s<500），并且基站 x 到基站 y 的距离，与基站 y 到基站 x 的距离并不一定会相同。
 * 小王从基站 1 出发，途径每个基站 1 次，然后返回基站 1，需要请你为他选择一条距离最短的路线。
 * 输入描述
 * 站点数 n 和各站点之间的距离（均为整数）。如：
 * 3 {站点数}
 * 0 2 1 {站点 1 到各站点的路程}
 * 1 0 2 {站点 2 到各站点的路程}
 * 2 1 0 {站点 3 到各站点的路程}
 * 输出描述
 * 最短路程的数值
 * 输入
 * 3
 * 0 2 1
 * 1 0 2
 * 2 1 0
 * 所有可能的路径：
 * 1→2→3→1：距离 = 2+2+2=6；
 * 1→3→2→1：距离 = 1+1+1=3。
 * 输出
 * 3
 */
public class BaseStationTSP {
    // 全局变量：最短路径总距离（初始化为极大值）
    private static int minDistance = Integer.MAX_VALUE;
    // 基站数量
    private static int n;
    // 基站间距离矩阵
    private static int[][] dist;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 步骤1：读取输入
        n = scanner.nextInt();
        dist = new int[n][n];
        // 读取n行距离数据
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = scanner.nextInt();
            }
        }

        // 步骤2：回溯遍历所有路径（起点为0，已访问标记数组，当前距离，已访问基站数）
        boolean[] visited = new boolean[n];
        visited[0] = true; // 起点（基站1）已访问
        backtrack(0, visited, 0, 1);

        // 步骤3：输出最短距离
        System.out.println(minDistance);
        scanner.close();
    }

    /**
     * 回溯函数
     * @param curr 当前所在基站下标
     * @param visited 基站访问标记（true=已访问）
     * @param currDist 当前累计距离
     * @param visitedCount 已访问的基站数量
     */
    private static void backtrack(int curr, boolean[] visited, int currDist, int visitedCount) {
        // 终止条件：遍历完所有基站，返回起点
        if (visitedCount == n) {
            // 加上返回起点的距离，更新最短距离
            int totalDist = currDist + dist[curr][0];
            if (totalDist < minDistance) {
                minDistance = totalDist;
            }
            return;
        }

        // 遍历所有未访问的基站
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // 剪枝：如果当前累计距离+到下一站的距离 >= 已知最短距离，无需继续递归
                if (currDist + dist[curr][i] >= minDistance) {
                    continue;
                }
                // 标记为已访问
                visited[i] = true;
                // 递归访问下一个基站
                backtrack(i, visited, currDist + dist[curr][i], visitedCount + 1);
                // 回溯：取消标记
                visited[i] = false;
            }
        }
    }
}

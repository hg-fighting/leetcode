package org.jeecgframework.boot.learn.huawei;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  8:45
 * @Description: 开心消消乐
 * 题目描述
 * 给定一个 N 行 M 列的二维矩阵，矩阵中每个位置的数字取值为 0 或 1。矩阵示例如：
 * 1 1 0 0
 * 0 0 0 1
 * 0 0 1 1
 * 1 1 1 1
 * 现需要将矩阵中所有的 1 进行反转为 0，规则如下：
 * 1）当点击一个 1 时，该 1 变被反转为 0，同时相邻的上、下、左、右，以及左上、左下、右
 * 上、右下 8 个方向的 1（如果存在 1）均会自动反转为 0；
 * 2）进一步地，一个位置上的 1 被反转为 0 时，与其相邻的 8 个方向的 1（如果存在 1）均会自动反转为 0；
 * 按照上述规则示例中的矩阵只最少需要点击 2 次后，所有值均为 0。请问，给定一个矩阵，
 * 最少需要点击几次后，所有数字均为 0？
 * 输入描述
 * 第一行为两个整数，分别表示矩阵的行数 N 和列数 M，取值范围均为[1, 100]
 * 接下来 N 行表示矩阵的初始值，每行均为 M 个数，取值范围[0,1]
 * 输出描述
 * 输出一个整数，表示最少需要点击的次数
 */
public class HappyElimination {

    // 8个方向的偏移量（上、下、左、右、左上、左下、右上、右下）
    private static final int[][] DIRS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 行数
        int M = scanner.nextInt(); // 列数
        scanner.nextLine(); // 处理换行

        // 初始化矩阵和访问标记数组
        int[][] matrix = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        // 读取矩阵数据
        for (int i = 0; i < N; i++) {
            String[] parts = scanner.nextLine().trim().split(" ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(parts[j]);
            }
        }
        scanner.close();

        int clickCount = 0; // 最少点击次数

        // 遍历矩阵所有位置
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 找到未访问的1，触发BFS
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    clickCount++;
                    bfs(matrix, visited, i, j, N, M);
                }
            }
        }

        System.out.println(clickCount);
    }

    /**
     * BFS遍历8连通的1区域，标记为已访问
     */
    private static void bfs(int[][] matrix, boolean[][] visited, int x, int y, int N, int M) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true; // 标记为已访问

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];

            // 遍历8个方向
            for (int[] dir : DIRS) {
                int newX = currX + dir[0];
                int newY = currY + dir[1];
                // 检查边界、是否为1、是否未访问
                if (newX >= 0 && newX < N && newY >= 0 && newY < M
                        && matrix[newX][newY] == 1 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
    }
}

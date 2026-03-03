package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/3
 * @Description: 查找单入口空闲区域
 * 题目描述
 * 给定一个 m x n 的矩阵，由若干字符 'X' 和
 * 'O'构成，'X'表示该处已被占据，'O'表示该处空闲，请找到最大的单入口空闲区域。
 * 解释：
 * 空闲区域是由连通的'O'组成的区域，位于边界的'O'可以构成入口，单入口空闲区域即有且
 * 只有一个位于边界的'O'作为入口的由连通的'O'组成的区域。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“连通”的。 输入描述
 * 第一行输入为两个数字，第一个数字为行数 m，第二个数字列数 n，两个数字以空格分隔
 * ，1 <= m, n <= 200，
 * 剩余各行为矩阵各行元素，元素为'X' 或 'O'，各元素间以空格分隔。 输出描述
 * 若有唯一符合要求的最大单入口空闲区域，输出三个数字，第一个数字为入口行坐标（范
 * 围为 0~行数-1），第二个数字为入口列坐标（范围为 0~列数- 1），第三个数字为区域大小，三个数字以空格分隔；
 * 若有多个符合要求的最大单入口空闲区域，输出一个数字，代表区域的大小；
 * 若没有，输出 NUL。
 * 示例
 * 输入
 * 4 4
 * X O X X
 * X O O X
 * X X O X
 * X X X O
 * 分析：
 * 区域 1：(0,1)、(1,1)、(1,2)、(2,2) → 边界入口仅 (0,1)，大小 4；
 * 区域 2：(3,3) → 边界入口仅 (3,3)，大小 1；
 * 最大区域是区域 1（大小 4），且唯一；
 * 输出：0 1 4
 */
public class SingleEntranceArea {

    // 方向数组：上下左右
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int m, n;

    private static char[][] matrix;

    private static boolean[][] visited;

    // 存储区域信息：入口坐标、区域大小
    static class AreaInfo {

        int entranceRow;

        int entranceCol;

        int size;

        public AreaInfo(int r, int c, int s) {
            entranceRow = r;
            entranceCol = c;
            size = s;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取行列数
        m = scanner.nextInt();
        n = scanner.nextInt();
        scanner.nextLine(); // 换行符

        // 初始化矩阵
        matrix = new char[m][n];
        for (int i = 0; i < m; i++) {
            String[] row = scanner.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = row[j].charAt(0);
            }
        }

        visited = new boolean[m][n];
        List<AreaInfo> validAreas = new ArrayList<>();

        // 遍历矩阵，寻找所有连通区域
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 未访问且是'O'，开始遍历连通区域
                if (!visited[i][j] && matrix[i][j] == 'O') {
                    // 存储该区域的边界入口数和入口坐标
                    int[] borderCount = new int[1]; // 用数组存，方便传参修改
                    int[] entrance = new int[2]; // [行, 列]，初始为-1
                    int size = bfs(i, j, borderCount, entrance);

                    // 单入口区域：边界入口数=1
                    if (borderCount[0] == 1) {
                        validAreas.add(new AreaInfo(entrance[0], entrance[1], size));
                    }
                }
            }
        }

        // 处理结果
        if (validAreas.isEmpty()) {
            System.out.println("NUL");
        } else {
            // 找到最大区域大小
            int maxSize = 0;
            for (AreaInfo area : validAreas) {
                if (area.size > maxSize) {
                    maxSize = area.size;
                }
            }

            // 收集所有最大区域
            List<AreaInfo> maxAreas = new ArrayList<>();
            for (AreaInfo area : validAreas) {
                if (area.size == maxSize) {
                    maxAreas.add(area);
                }
            }

            // 按规则输出
            if (maxAreas.size() == 1) {
                AreaInfo res = maxAreas.get(0);
                System.out.println(res.entranceRow + " " + res.entranceCol + " " + res.size);
            } else {
                System.out.println(maxSize);
            }
        }

        scanner.close();
    }

    // BFS遍历连通区域，返回区域大小；borderCount[0]存储边界入口数，entrance存储入口坐标
    private static int bfs(int startR, int startC, int[] borderCount, int[] entrance) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        int size = 0;
        borderCount[0] = 0;
        entrance[0] = -1;
        entrance[1] = -1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            size++;

            // 判断当前位置是否是边界'O'（入口候选）
            if (isBorder(r, c)) {
                borderCount[0]++;
                // 记录入口坐标（仅第一个边界'O'，因为单入口区域只有一个）
                if (entrance[0] == -1) {
                    entrance[0] = r;
                    entrance[1] = c;
                }
            }

            // 遍历四个方向
            for (int[] dir : DIRS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                // 坐标合法、未访问、是'O'
                if (nr >= 0 && nr < m && nc >= 0 && nc < n
                        && !visited[nr][nc] && matrix[nr][nc] == 'O') {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return size;
    }

    // 判断是否是边界位置
    private static boolean isBorder(int r, int c) {
        return r == 0 || r == m - 1 || c == 0 || c == n - 1;
    }
}

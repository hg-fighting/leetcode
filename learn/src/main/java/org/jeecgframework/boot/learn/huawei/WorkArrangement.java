package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/3
 * @Description: 工作安排
 * 题目描述
 * 小明每周上班都会拿到自己的工作清单，工作清单内包含 n 项工作，每项工作都有对应的
 * 耗时时长（单位 h）和报酬，工作的总报酬为所有已完成工作的报酬之和。那么请你帮小
 * 明安排一下工作，保证小明在指定的工作时间内工作收入最大化。
 * 输入描述
 * 输入的第一行为两个正整数 T，n。T 代表工作时长（单位 h，0 < T <100000），n 代表工作数量(1 < n ≤ 3000)。
 * 接下来是 n 行，每行包含两个整数 t，w。t 代表该项工作消耗的时长（单位 h，t > 0），w 代表该项工作的报酬。
 * 输出描述
 * 输出小明指定工作时长内工作可获得的最大报酬。
 * 10 3
 * 4 10
 * 5 12
 * 6 15
 * 计算过程：
 * 初始化dp = [0,0,0,0,0,0,0,0,0,0,0]；
 * 处理第 1 项工作（4h，10 报酬）：
 * j 从 10→4，dp[4]=10、dp[5]=10、…、dp[10]=10；
 * 处理第 2 项工作（5h，12 报酬）：
 * j=10：max(10, dp[5]+12)=max(10,10+12)=22 → dp[10]=22；
 * j=9：max(10, dp[4]+12)=22 → dp[9]=22；
 * j=5：dp[5]=12；
 * 处理第 3 项工作（6h，15 报酬）：
 * j=10：max(22, dp[4]+15)=max(22,10+15)=25 → dp[10]=25；
 * 输出：25
 */
public class WorkArrangement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取总时长T和工作数量n
        int T = scanner.nextInt();
        int n = scanner.nextInt();

        // 存储每项工作的耗时t和报酬w
        int[] t = new int[n];
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }

        // 一维DP数组：dp[j]表示时长j内的最大报酬
        long[] dp = new long[T + 1]; // 用long避免报酬溢出（n=3000，w最大假设为1e4，总和3e7，int也够，但long更安全）

        // 遍历每项工作
        for (int i = 0; i < n; i++) {
            int time = t[i];
            int wage = w[i];
            // 从后往前遍历时长，避免重复选择同一工作
            for (int j = T; j >= time; j--) {
                dp[j] = Math.max(dp[j], dp[j - time] + wage);
            }
        }

        // 输出总时长T内的最大报酬
        System.out.println(dp[T]);
        scanner.close();
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取总时长T和工作数量n
        int T = scanner.nextInt();
        int n = scanner.nextInt();

        // 存储每项工作的耗时t和报酬w（下标从1开始，方便和DP数组对应）
        int[] t = new int[n + 1];
        int[] w = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            t[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
        }

        // 二维DP数组：dp[i][j]表示前i项工作、耗时j小时内的最大报酬
        // 行：前i项工作（0~n），列：耗时j小时（0~T）
        long[][] dp = new long[n + 1][T + 1];

        // 遍历每一项工作（从1开始，对应前i项工作）
        for (int i = 1; i <= n; i++) {
            // 遍历所有可能的耗时（从1到T）
            for (int j = 1; j <= T; j++) {
                // 情况1：不选第i项工作，报酬等于前i-1项工作、耗时j的最大报酬
                dp[i][j] = dp[i - 1][j];

                // 情况2：选第i项工作（需耗时j ≥ 当前工作耗时t[i]）
                if (j >= t[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - t[i]] + w[i]);
                }
            }
        }

        // 输出前n项工作、耗时T小时内的最大报酬
        System.out.println(dp[n][T]);
        scanner.close();
    }

}


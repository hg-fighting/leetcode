package org.jeecgframework.boot.learn.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/5
 * @Description: 微服务的集成测试
 * 题目描述
 * 现在有 n 个容器服务，服务的启动可能有一定的依赖性（有些服务启动没有依赖），其次
 * 服务自身启动加载会消耗一些时间 。
 * 给你一个 nxn 的二维矩阵 useTime，其中 useTime[i][i]=10 表示服务 i
 * 自身启动加载需要消耗 10s，useTime[i][j]=1 表示服务 i 启动依赖服务 j
 * 启动完成，useTime[i][k]=0，表示服务 i
 * 启动不依赖服务 k
 * 其实 0<= i，j，k <
 * n。服务之间启动没有循环依赖（不会出现环），若想对任意一个服务 i 进行集成测试（服务 i 自身也需要加载），求最少需要等待多少时间。 .
 * 输入描述
 * 第一行输入服务总量 n，之后的 n 行表示服务启动的依赖关系以及自身启动加载耗时
 * 最后输入 k 表示计算需要等待多少时间后可以对服务 k 进行集成测试
 * 其中 1 <= k <=n，1<=n<=100
 * 输出描述
 * 最少需要等待多少时间(s)后可以对服务 k 进行集成测试
 */
public class MicroserviceTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // 处理换行

        // 存储每个服务的自身启动耗时（对角线值）
        int[] selfTime = new int[n];
        // 存储每个服务的依赖列表：dependents[i] = 服务i需要依赖的所有服务j
        List<List<Integer>> dependents = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dependents.add(new ArrayList<>());
        }

        // 解析n行依赖/耗时数据
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split(" ");
            for (int j = 0; j < n; j++) {
                int val = Integer.parseInt(parts[j]);
                if (i == j) {
                    // 对角线值 = 服务i自身启动耗时
                    selfTime[i] = val;
                } else if (val == 1) {
                    // useTime[i][j]=1 → 服务i依赖服务j
                    dependents.get(i).add(j);
                }
            }
        }

        // 读取目标服务k（1-based → 转0-based）
        int target = scanner.nextInt() - 1;
        scanner.close();

        // 记忆化缓存：dp[i] = 服务i完全启动的最少耗时（避免重复计算）
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int result = calcMaxTime(target, selfTime, dependents, dp);

        System.out.println(result);
    }

    /**
     * 递归计算服务i的最长启动耗时（关键路径）
     *
     * @param i          服务索引（0-based）
     * @param selfTime   自身耗时数组
     * @param dependents 依赖列表
     * @param dp         记忆化缓存
     * @return 服务i完全启动的耗时
     */
    private static int calcMaxTime(int i, int[] selfTime, List<List<Integer>> dependents, int[] dp) {
        // 已计算过，直接返回缓存值
        if (dp[i] != -1) {
            return dp[i];
        }

        // 计算所有依赖服务的最长耗时（并行启动，取最大值）
        int maxDepTime = 0;
        for (int depJ : dependents.get(i)) {
            // 递归计算依赖服务j的耗时
            int depTime = calcMaxTime(depJ, selfTime, dependents, dp);
            maxDepTime = Math.max(maxDepTime, depTime);
        }

        // 服务i的耗时 = 自身耗时 + 依赖的最长耗时
        dp[i] = selfTime[i] + maxDepTime;
        return dp[i];
    }
}
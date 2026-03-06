package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  10:07
 * @Description: 查找充电设备组合
 * 题目描述
 * 某个充电站，可提供 n 个充电设备，每个充电设备均有对应的输出功率。任意个充电设备
 * 组合的输出功率总和，均构成功率集合 P 的 1 个元素。功率集合 P 的最优元素，表示最接近
 * 充电站最大输出功率 p_max 的元素。
 * 输入描述
 * 输入为 3 行：
 * 第 1 行为充电设备个数 n。
 * 第 2 行为每个充电设备的输出功率。
 * 第 3 行为充电站最大输出功率 p_max。
 * 输出描述
 * 功率集合 P 的最优元素
 */
public class FindOptimalPower {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 步骤1：读取输入
        int n = scanner.nextInt(); // 充电设备个数
        int[] powers = new int[n];
        for (int i = 0; i < n; i++) {
            powers[i] = scanner.nextInt();
        }
        int pMax = scanner.nextInt(); // 充电站最大输出功率
        scanner.close();

        // 步骤2：动态规划求解0-1背包问题
        boolean[] dp = new boolean[pMax + 1];
        dp[0] = true; // 初始状态：功率0可达

        for (int power : powers) {
            // 从后往前遍历，避免重复选择同一设备
            for (int j = pMax; j >= power; j--) {
                if (dp[j - power]) {
                    dp[j] = true;
                }
            }
        }

        // 步骤3：找最接近pMax的可达功率
        int optimal = 0;
        for (int j = pMax; j >= 0; j--) {
            if (dp[j]) {
                optimal = j;
                break;
            }
        }

        // 步骤4：输出结果
        System.out.println(optimal);
    }
}

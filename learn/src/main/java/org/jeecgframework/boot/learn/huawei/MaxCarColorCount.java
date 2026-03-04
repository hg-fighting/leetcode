package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/4  16:01
 * @Description: 找出通过车辆最多颜色
 * 题目描述
 * 在一个狭小的路口，每秒只能通过一辆车，假如车辆的颜色只有 3 种，找出 N 秒内经过的最
 * 多颜色的车辆数量
 * 三种颜色编号为 0,1,2
 * 输入描述
 * 第一行输入的是通过的车辆颜色信息
 * [0,1,1,2] 代表 4 秒钟通过的车辆颜色分别是 0,1,1,2
 * 第二行输入的是统计时间窗，整型，单位为秒
 * 输出描述
 * 输出指定时间窗内经过的最多颜色的车辆数量
 * 输入
 * [0,1,1,2]
 * 2
 * 执行过程：
 * 初始窗口（前 2 个）：[0,1] → 计数 [1,1,0] → 最大值 1；
 * 滑动 1 次（窗口 [1,1]）：计数 [0,2,0] → 最大值 2；
 * 滑动 2 次（窗口 [1,2]）：计数 [0,1,1] → 最大值 1；
 * 全局最大值：2；
 * 输出：2
 */
public class MaxCarColorCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 步骤1：读取车辆颜色序列（处理输入格式，如[0,1,1,2]）
        String colorStr = scanner.nextLine().trim();
        // 去除括号和空格，按逗号分割
        colorStr = colorStr.replace("[", "").replace("]", "").replace(" ", "");
        String[] colorArr = colorStr.split(",");
        int[] colors = new int[colorArr.length];
        for (int i = 0; i < colorArr.length; i++) {
            colors[i] = Integer.parseInt(colorArr[i]);
        }

        // 步骤2：读取时间窗长度
        int window = scanner.nextInt();

        // 边界处理：时间窗大于车辆总数时，直接统计所有车辆
        if (window >= colors.length) {
            window = colors.length;
        }

        // 步骤3：初始化滑动窗口的颜色计数（仅3种颜色，0/1/2）
        int[] count = new int[3];
        int maxCount = 0;

        // 初始化前window个车辆的计数
        for (int i = 0; i < window; i++) {
            int color = colors[i];
            count[color]++;
            // 更新初始窗口的最大值
            if (count[color] > maxCount) {
                maxCount = count[color];
            }
        }

        // 步骤4：滑动窗口遍历剩余车辆
        int currentMax = maxCount;
        for (int i = window; i < colors.length; i++) {
            // 移除窗口左侧离开的车辆（i-window位置）
            int leftColor = colors[i - window];
            count[leftColor]--;

            // 添加窗口右侧进入的车辆（i位置）
            int rightColor = colors[i];
            count[rightColor]++;

            // 计算当前窗口的最大计数
            currentMax = Math.max(count[0], Math.max(count[1], count[2]));
            // 更新全局最大值
            if (currentMax > maxCount) {
                maxCount = currentMax;
            }
        }

        // 输出结果
        System.out.println(maxCount);
        scanner.close();
    }
}

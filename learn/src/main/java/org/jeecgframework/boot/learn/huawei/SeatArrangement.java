package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/4  8:54
 * @Description: 新员工座位安排系统
 * 题目描述
 * 工位由序列 F1,F2...Fn 组成，Fi 值为 0、1 或 2。其中 0 代表空置，1 代表有人，2 代表障碍物。
 * 1、某一空位的友好度为左右连续老员工数之和
 * 2、为方便新员工学习求助，优先安排友好度高的空位
 * 给出工位序列，求所有空位中友好度的最大值。 输入描述
 * 第一行为工位序列：F1,F2...Fn 组成，1<=n<=100000，Fi 值为 0、1 或 2。其中 0 代表空置，1 代表有人，2 代表障碍物
 * 其中 0 代表空置，1 代表有人，2 代表障碍物。
 * 输出描述
 * 所有空位中友好度的最大值。如果没有空位，返回 0。
 * 输入：1,0,1,1,2,0,1,1,1
 * left 数组：[0,1,1,2,3,0,0,1,2]
 * right 数组：[2,2,1,0,3,3,2,1,0]
 * 空位友好度：
 * 位置 1：1+2=3；
 * 位置 5：0+3=3；
 * 输出：3
 */
public class SeatArrangement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",");
        int n = input.length;
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = Integer.parseInt(input[i]);
        }

        // 1. 预处理left数组：left[i]表示i左侧（左边）连续的1的数量（遇到2重置）
        int[] left = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            left[i] = cnt; // 先记录当前左侧的连续数
            if (seats[i] == 1) {
                cnt++;
            } else if (seats[i] == 2) {
                cnt = 0; // 障碍物阻断，重置计数
            }
            // 空位不影响计数，仅1累加、2重置
        }

        // 2. 预处理right数组：right[i]表示i右侧（右边）连续的1的数量（遇到2重置）
        int[] right = new int[n];
        cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            right[i] = cnt; // 先记录当前右侧的连续数
            if (seats[i] == 1) {
                cnt++;
            } else if (seats[i] == 2) {
                cnt = 0; // 障碍物阻断，重置计数
            }
        }

        // 3. 遍历所有空位，计算最大友好度
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 0) {
                int cur = left[i] + right[i];
                if (cur > max) {
                    max = cur;
                }
            }
        }

        System.out.println(max);
        scanner.close();
    }
}

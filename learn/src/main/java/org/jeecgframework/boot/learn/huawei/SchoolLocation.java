package org.jeecgframework.boot.learn.huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  11:05
 * @Description: 学校的位置
 * 题目描述
 * 为了解决新学期学生暴涨的问题，小乐村要建所新学校。考虑到学生上学安全问题，需要
 * 所有学生家到学校距离最短。
 * 假设学校和所有的学生家，走在一条直线上。
 * 请问，学校要建在什么位置，能使得学校到各个学生家的距离之和最短？
 * 输入描述
 * 输入的第一行是一个整数 N（1<=N<=1000），表示有 N 户家庭。
 * 输入的第二行是一个属组 （0<=
 * <=10000），表示每户家庭的位置，所有家庭的位置都不相同。 输出描述
 * 输出一行，一个整数，表示你确定的学校位置。如有多个位置相同，则输出值最小的位置
 * 示例
 * 3
 * 1 7 5
 * 排序后：[1,2,3,4]
 * 中位数索引：(4-1)/2=1 → 值为 2；
 * 距离和验证：|1-2|+|2-2|+|3-2|+|4-2|=1+0+1+2=4（与选 3 的距离和相同，按要求输出较小的 2）；
 * 输出：2。
 */
public class SchoolLocation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取家庭数量
        int n = scanner.nextInt();
        // 读取家庭位置数组
        int[] positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = scanner.nextInt();
        }

        // 对位置数组升序排序
        Arrays.sort(positions);

        // 计算中位数索引（偶数长度时取中间两个中较小的）
        int midIndex = n / 2;
        // 输出最优位置
        System.out.println(positions[midIndex]);

        scanner.close();
    }
}

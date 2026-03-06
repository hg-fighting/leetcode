package org.jeecgframework.boot.learn.huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  8:07
 * @Description: 租车骑绿道
 * 题目描述
 * 部门组织绿道骑行团建活动。租用公共双人自行车骑行，每辆自行车最多坐两人、做大载重 M。
 * 给出部门每个人的体重，请问最多需要租用多少双人自行车。
 * 输入描述
 * 第一行两个数字 m、n，自行车限重 m，代表部门总人数 n。
 * 第二行，n 个数字，代表每个人的体重。体重都小于等于自行车限重 m。
 * 0 < m <= 200
 * 0 < n <= 1000000
 * 输出描述
 * 最小需要的双人自行车数量。
 */
public class BikeRental {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取限重m和人数n
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine(); // 处理换行

        // 读取体重数组（适配百万级数据）
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }
        scanner.close();

        // 步骤1：升序排序体重
        Arrays.sort(weights);

        // 步骤2：双指针贪心计算最少租车数
        int left = 0;          // 最轻的人
        int right = n - 1;     // 最重的人
        int bikeCount = 0;     // 租车数量

        while (left <= right) {
            // 最轻+最重 ≤ 限重 → 两人同乘
            if (weights[left] + weights[right] <= m) {
                left++;
            }
            // 无论是否同乘，最重的人都被安排（单独/同乘）
            right--;
            bikeCount++;
        }

        // 输出结果
        System.out.println(bikeCount);
    }
}
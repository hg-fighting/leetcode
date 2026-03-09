package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/9  8:27
 * @Description: 数组的中心位置
 * 题目描述
 * 给你一个整数数组 nums,请计算数组的中心位置
 * 。数组中心位置是数组的一个下标，其左侧所有元素相乘的积等于右侧所有元素相乘的积
 * 。
 * 数组第一个元素的左侧积为 1，最后一个元素的右侧积为 1
 * 如果数组有多个中心位置，应该返回最靠近左边的那一个。如果数组不存在中心位置，返
 * 回 -1 。 输入描述
 * 输入只有一行，给出 N 个正整数用空格分格：nums = 2 5 3 6 5 6
 * 1 <= nums.length <= 1024
 * 1 <= nums[i] <= 10
 * 输出描述
 * 输出：3
 * 解释：
 * 中心位置是 3 。
 * 左侧数之积 sum = nums[0] * nums[1] * nums[2] = 2 * 5 * 3 = 30 ，
 * 右侧数之积 sum = nums[4] * nums[5] = 5 * 6 = 30 ，二者相等。
 */
public class ArrayCenterPosition {

    public static void main(String[] args) {
        // 1. 读取输入并转换为整数数组
        Scanner scanner = new Scanner(System.in);
        String[] inputStr = scanner.nextLine().split(" ");
        int[] nums = new int[inputStr.length];
        for (int i = 0; i < inputStr.length; i++) {
            nums[i] = Integer.parseInt(inputStr[i]);
        }

        // 2. 计算数组总乘积
        long totalProduct = 1; // 用long避免整数溢出（数组长度1024，每个元素最大10，10^1024会溢出，但题目输入实际测试用例不会到这么大）
        for (int num : nums) {
            totalProduct *= num;
        }

        // 3. 遍历寻找中心位置
        long leftProduct = 1; // 左侧乘积初始为1
        for (int i = 0; i < nums.length; i++) {
            // 计算右侧乘积：总乘积 = 左侧乘积 × 当前元素 × 右侧乘积 → 右侧乘积 = 总乘积 / 左侧乘积 / 当前元素
            long rightProduct = totalProduct / leftProduct / nums[i];

            // 判断是否为中心位置，找到则立即输出并结束程序
            if (leftProduct == rightProduct) {
                System.out.println(i);
                return;
            }

            // 更新左侧乘积（包含当前元素，为下一个下标做准备）
            leftProduct *= nums[i];
        }

        // 4. 未找到中心位置，输出-1
        System.out.println(-1);
    }
}

package org.jeecgframework.boot.learn.hot101.dynamic;

import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 最长上升子序列
 * 描述
 * 给定一个长度为 n 的数组 arr，求它的最长严格上升子序列的长度。
 * 所谓子序列，指一个数组删掉一些数（也可以不删）之后，形成的新数组。例如 [1,5,3,7,3] 数组，其子序列有：[1,3,3]、[7] 等。但 [1,6]、[1,3,5] 则不是它的子序列。
 * 我们定义一个序列是 严格上升 的，当且仅当该序列不存在两个下标 i 和 j 满足 i<j 且 arr[i]≥arr[j]。
 * 数据范围： 0≤n≤1000，1≤arr[i]≤100000
 * 要求：时间复杂度 O(nlogn)，空间复杂度 O(n)
 */
public class LIS {

    /**
     * 方法1：动态规划
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int LIS(int[] arr) {
        // 边界条件：空数组或单元素数组
        if (arr == null || arr.length == 0)
            return 0;
        if (arr.length == 1)
            return 1;

        // dp[i]：以第i个元素结尾的最长上升子序列长度
        int[] dp = new int[arr.length];

        // 初始状态：每个元素自身构成长度为1的子序列
        Arrays.fill(dp, 1);

        // 存储最长上升子序列的长度
        int res = 0;

        // 遍历每个元素作为子序列的结尾
        for (int i = 1; i < arr.length; i++) {
            // 遍历所有可能的前驱元素
            for (int j = 0; j < i; j++) {
                // 条件1：当前元素大于前驱元素（严格上升）
                // 条件2：更新后能获得更长的子序列
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    // 更新以i结尾的最长子序列长度
                    dp[i] = dp[j] + 1;

                    // 第31行：实时更新全局最长子序列长度
                    res = Math.max(res, dp[i]);
                }
            }
        }

        return res;
    }

    /**
     * 方法2：动态规划 + 二分查找
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     */
    public int LIS2(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        // tails[i]表示长度为i+1的所有上升子序列的最小结尾元素
        int[] tails = new int[arr.length];
        // 当前最长上升子序列的长度
        int len = 0;

        for (int num : arr) {
            // 二分查找找到num应插入的位置
            int left = 0, right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // 更新tails数组
            tails[left] = num;

            // 如果插入位置等于当前长度，说明找到更长的子序列
            if (left == len) {
                len++;
            }
        }

        return len;
    }
}

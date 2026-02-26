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

    public int LIS(int[] arr) {
        int[] dp = new int[arr.length];
        //设置数组长度大小的动态规划辅助数组
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                //可能j不是所需要的最大的，因此需要dp[i] < dp[j] + 1
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    //i点比j点大，理论上dp要加1
                    dp[i] = dp[j] + 1;
                    //找到最大长度
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }
}

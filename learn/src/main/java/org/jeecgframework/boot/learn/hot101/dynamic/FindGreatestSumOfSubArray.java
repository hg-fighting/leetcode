package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/2/27  15:04
 * @Description: 连续子数组的最大和
 * 描述
 * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
 * 数据范围:
 * 1 <= n <= 2*10^5
 * -100 <= a[i] <= 100
 * 要求:时间复杂度为 O(n)，空间复杂度为 O(n)
 * 示例1
 * 输入：
 * [1,-2,3,10,-4,7,2,-5]
 * 复制
 * 返回值：
 * 18
 * 复制
 * 说明：
 * 经分析可知，输入数组的子数组[3,10,-4,7,2]可以求得最大和为18
 */
public class FindGreatestSumOfSubArray {

    public int FindGreatestSumOfSubArray(int[] array) {
        //记录到下标i为止的最大连续子数组和
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int maxsum = dp[0];
        for (int i = 1; i < array.length; i++) {
            //状态转移：连续子数组和最大值
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            //维护最大值
            maxsum = Math.max(maxsum, dp[i]);
        }
        return maxsum;
    }

    public int findGreatestSumOfSubArray2(int[] array) {
        int maxSum = array[0];
        int currentSum = array[0];
        for (int i = 1; i < array.length; i++) {
            currentSum = Math.max(currentSum + array[i], array[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        FindGreatestSumOfSubArray solution = new FindGreatestSumOfSubArray();
        int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
        int result = solution.findGreatestSumOfSubArray2(array);
        System.out.println(result);
    }

}

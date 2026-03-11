package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  16:19
 * @Description: 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class rob {

    public int rob(int[] nums) {
        //dp[i]表示长度为i的数组，最多能偷取多少钱
        int[] dp = new int[nums.length + 1];
        //长度为1只能偷第一家
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++)
            //对于每家可以选择偷或者不偷
            //dp[i-1]表示不偷这家，dp[i-2]+nums[i-1]表示偷这家
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        return dp[nums.length];
    }
}

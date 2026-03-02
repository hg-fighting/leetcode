package org.jeecgframework.boot.learn.hot101.dynamic;

import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  16:22
 * @Description: 打家劫舍2
 * 你是一个经验丰富的小偷，准备偷沿湖的一排房间，每个房间都存有一定的现金，为了防止被发现，你不能偷相邻的两家，即，如果偷了第一家，就不能再偷第二家，如果偷了第二家，那么就不能偷第一家和第三家。沿湖的房间组成一个闭合的圆形，即第一个房间和最后一个房间视为相邻。
 * 给定一个长度为n的整数数组nums，数组中的元素表示每个房间存有的现金数额，请你计算在不被发现的前提下最多的偷窃金额
 */
public class rob2 {

    public int rob(int[] nums) {
        //dp[i]表示长度为i的数组，最多能偷取多少钱
        int[] dp = new int[nums.length + 1];
        //选择偷了第一家
        dp[1] = nums[0];
        //最后一家不能偷
        for (int i = 2; i < nums.length; i++)
            //对于每家可以选择偷或者不偷
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        int res = dp[nums.length - 1];
        //清除dp数组，第二次循环
        Arrays.fill(dp, 0);
        //不偷第一家
        dp[1] = 0;
        //可以偷最后一家
        for (int i = 2; i <= nums.length; i++)
            //对于每家可以选择偷或者不偷
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        //选择最大值
        return Math.max(res, dp[nums.length]);
    }
}

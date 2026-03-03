package org.jeecgframework.boot.learn.hot101.greedy;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:51
 * @Description: 分发糖果
 * 有 n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */
public class candy {

    public int candy(int[] arr) {
        int n = arr.length;
        if (n <= 1)
            return n;
        int[] nums = new int[n];
        //初始化
        for (int i = 0; i < n; i++)
            nums[i] = 1;
        //从左到右遍历
        for (int i = 1; i < arr.length; i++) {
            //如果右边在递增，每次增加一个
            if (arr[i] > arr[i - 1])
                nums[i] = nums[i - 1] + 1;
        }
        //记录总糖果数
        int res = nums[arr.length - 1];
        //从右到左遍历
        for (int i = arr.length - 2; i >= 0; i--) {
            //如果左边更大但是糖果数更小
            if (arr[i] > arr[i + 1] && nums[i] <= nums[i + 1])
                nums[i] = nums[i + 1] + 1;
            //累加和
            res += nums[i];
        }
        return res;
    }
}

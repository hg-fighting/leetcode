package org.jeecgframework.boot.learn.hot101.array;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:43
 * @Description: 接雨水问题
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class maxWater {

    public long maxWater(int[] arr) {
        //排除空数组
        if (arr.length == 0)
            return 0;
        long res = 0;
        //左右双指针
        int left = 0;
        int right = arr.length - 1;
        //中间区域的边界高度
        int maxL = 0;
        int maxR = 0;
        //直到左右指针相遇
        while (left < right) {
            //每次维护往中间的最大边界
            maxL = Math.max(maxL, arr[left]);
            maxR = Math.max(maxR, arr[right]);
            //较短的边界确定该格子的水量
            if (maxR > maxL)
                res += maxL - arr[left++];
            else
                res += maxR - arr[right--];
        }
        return res;
    }
}

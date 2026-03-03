package org.jeecgframework.boot.learn.hot101.array;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:41
 * @Description: 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 */
public class maxArea {

    /**
     * @param height: 高度数组
     * @return: 最大面积
     */
    public int maxArea(int[] height) {
        //左右双指针
        int left = 0;
        int right = height.length - 1;
        //最大面积
        int maxArea = 0;
        //两指针往中间靠
        while (left < right) {
            //当前面积
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            //更新最大面积
            maxArea = Math.max(maxArea, currentArea);
            //移动较短的指针
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}

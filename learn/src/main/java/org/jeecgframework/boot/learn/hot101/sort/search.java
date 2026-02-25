package org.jeecgframework.boot.learn.hot101.sort;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  13:22
 * @Description: 二分查找-I
 * 描述
 * 请实现有重复数字的有序数组的二分查找。
 * 输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
 * 数据范围：数组长度满足 0≤n≤10^5，数组中每个元素都满足 ∣val∣≤10^9
 * 进阶：时间复杂度 O(logn)，空间复杂度 O(1)
 */
public class search {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

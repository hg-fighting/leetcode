package org.jeecgframework.boot.learn.hot101.sort;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  13:22
 * @Description: 二分查找-I
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

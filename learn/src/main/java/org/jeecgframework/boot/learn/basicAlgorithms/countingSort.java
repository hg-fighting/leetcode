package org.jeecgframework.boot.learn.basicAlgorithms;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:46
 * @Description: 计数排序
 * 时间复杂度：O(n + k)
 * 空间复杂度：O(k)
 * 是否稳定：是
 */
public class countingSort {

    // 计数排序（适用于非负整数，且数值范围不大）
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        // 1. 找数组最大值（确定计数数组长度）
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        // 2. 统计每个元素出现次数
        int[] countArr = new int[max + 1];
        for (int num : arr) {
            countArr[num]++;
        }
        // 3. 重构有序数组
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (countArr[i] > 0) {
                arr[index++] = i;
                countArr[i]--;
            }
        }
    }
}

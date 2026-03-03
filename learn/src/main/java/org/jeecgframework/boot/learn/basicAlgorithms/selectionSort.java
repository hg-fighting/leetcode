package org.jeecgframework.boot.learn.basicAlgorithms;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:37
 * @Description: 选择排序
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 是否稳定：否
 */
public class selectionSort {

    // 选择排序
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // 最小元素索引
            // 找未排序区间的最小元素
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换到未排序区间首位
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}

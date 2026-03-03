package org.jeecgframework.boot.learn.basicAlgorithms;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:39
 * @Description: 插入排序
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 是否稳定：是
 */
public class insertionSort {

    // 插入排序
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // 待插入元素
            int j = i - 1;
            // 已排序区间后移，找到插入位置
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // 插入元素
        }
    }
}

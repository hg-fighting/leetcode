package org.jeecgframework.boot.learn.basicAlgorithms;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:41
 * @Description: 希尔排序
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 是否稳定：否
 */
public class shellSort {

    // 希尔排序
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        // 步长逐步缩小（通常取 n/2、n/4...1）
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 对每组进行插入排序
            for (int i = gap; i < n; i++) {
                int key = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > key) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = key;
            }
        }
    }
}

package org.jeecgframework.boot.learn.basicAlgorithms;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:39
 * @Description: 冒泡排序
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 是否稳定：是
 */
public class bubbleSort {

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 空数组或单元素数组无需排序
        }
        int n = arr.length;
        boolean swapped; // 标记是否发生交换，优化性能
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // 每轮遍历后，最大的元素已"冒泡"到末尾，无需再比较
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换相邻元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // 若本轮无交换，说明数组已有序，直接退出
            if (!swapped) {
                break;
            }
        }
    }

}
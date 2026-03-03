package org.jeecgframework.boot.learn.basicAlgorithms;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:35
 * @Description: 快速排序
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(logn)
 * 是否稳定：否
 */
public class quickSort {

    // 快速排序入口
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    // 递归排序
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotIndex = partition(arr, left, right); // 分区
        quickSort(arr, left, pivotIndex - 1); // 左半区
        quickSort(arr, pivotIndex + 1, right); // 右半区
    }

    // 分区：返回基准值最终位置
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // 选右边界为基准
        int i = left - 1; // 小于基准的区域边界
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                // 交换元素，扩大小于基准的区域
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 基准值放到最终位置
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        return i + 1;
    }
}

package org.jeecgframework.boot.learn.basicAlgorithms;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:42
 * @Description: 归并排序
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 * 是否稳定：是
 */
public class mergeSort {

    // 归并排序入口
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    // 递归拆分
    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2; // 避免整数溢出
        mergeSort(arr, left, mid); // 左半区排序
        mergeSort(arr, mid + 1, right); // 右半区排序
        merge(arr, left, mid, right); // 合并有序子数组
    }

    // 合并两个有序子数组
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 临时数组
        int i = left, j = mid + 1, k = 0;
        // 按序合并
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        // 处理剩余元素
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        // 复制回原数组
        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}

package org.jeecgframework.boot.learn.basicAlgorithms;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:43
 * @Description: 堆排序
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 * 是否稳定：否
 */
public class heapSort {

    // 堆排序
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        // 构建大顶堆（从最后一个非叶子节点开始）
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // 逐个取出堆顶元素
        for (int i = n - 1; i > 0; i--) {
            // 堆顶（最大值）交换到末尾
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 调整剩余堆
            heapify(arr, i, 0);
        }
    }

    // 调整堆（保证以i为根的子树是大顶堆）
    private static void heapify(int[] arr, int heapSize, int i) {
        int largest = i; // 最大值索引
        int left = 2 * i + 1; // 左子节点
        int right = 2 * i + 2; // 右子节点
        // 找最大值
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        // 若最大值不是根节点，交换并递归调整
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, heapSize, largest);
        }
    }
}

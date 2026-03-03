package org.jeecgframework.boot.learn.basicAlgorithms;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:45
 * @Description: 测试所有排序算法
 */
public class AllSortAlgorithms {

    // 打印数组
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 复制数组（避免排序后原数组被修改）
    public static int[] copyArray(int[] arr) {
        int[] newArr = new int[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    public static void main(String[] args) {
        int[] original = {5, 2, 9, 3, 7, 6, 1, 8, 4};
        System.out.println("原数组：");
        printArray(original);

        // 测试冒泡排序
        int[] bubbleArr = copyArray(original);
        bubbleSort.bubbleSort(bubbleArr);
        System.out.println("冒泡排序后：");
        printArray(bubbleArr);

        // 测试选择排序
        int[] selectionArr = copyArray(original);
        selectionSort.selectionSort(selectionArr);
        System.out.println("选择排序后：");
        printArray(selectionArr);

        // 测试插入排序
        int[] insertionArr = copyArray(original);
        insertionSort.insertionSort(insertionArr);
        System.out.println("插入排序后：");
        printArray(insertionArr);

        // 测试希尔排序
        int[] shellArr = copyArray(original);
        shellSort.shellSort(shellArr);
        System.out.println("希尔排序后：");
        printArray(shellArr);

        // 测试归并排序
        int[] mergeArr = copyArray(original);
        mergeSort.mergeSort(mergeArr);
        System.out.println("归并排序后：");
        printArray(mergeArr);

        // 测试快速排序
        int[] quickArr = copyArray(original);
        quickSort.quickSort(quickArr);
        System.out.println("快速排序后：");
        printArray(quickArr);

        // 测试堆排序
        int[] heapArr = copyArray(original);
        heapSort.heapSort(heapArr);
        System.out.println("堆排序后：");
        printArray(heapArr);

        // 测试计数排序
        int[] countingArr = copyArray(original);
        countingSort.countingSort(countingArr);
        System.out.println("计数排序后：");
        printArray(countingArr);
    }
}

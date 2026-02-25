package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.Random;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:31
 * @Description: 查找第K大的数
 */
public class findKth {

    //交换函数
    Random r = new Random();

    public static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public int partition(int[] a, int low, int high, int k) {
        //随机快排划分
        int x = Math.abs(r.nextInt()) % (high - low + 1) + low;
        swap(a, low, x);
        int v = a[low];
        int i = low + 1;
        int j = high;
        while (true) {
            //小于标杆的在右
            while (j >= low + 1 && a[j] < v) {
                j--;
            }
            //大于标杆的在左
            while (i <= high && a[i] > v) {
                i++;
            }
            if (i > j) {
                break;
            }
            swap(a, i, j);
            i++;
            j--;
        }
        swap(a, low, j);
        //从0开始，所以为第j+1大
        if (j + 1 == k) {
            return a[j];
        }
        //继续划分
        else if (j + 1 < k) {
            return partition(a, j + 1, high, k);
        } else {
            return partition(a, low, j - 1, k);
        }
    }

    public int findKth(int[] a, int n, int K) {
        return partition(a, 0, n - 1, K);
    }

}

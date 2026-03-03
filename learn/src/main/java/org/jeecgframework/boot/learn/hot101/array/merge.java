package org.jeecgframework.boot.learn.hot101.array;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:07
 * @Description: 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 */
public class merge {

    public void merge(int A[], int m, int B[], int n) {
        //指向数组A的结尾
        int i = m - 1;
        //指向数组B的结尾
        int j = n - 1;
        //指向数组A空间的结尾处
        int k = m + n - 1;
        //从两个数组最大的元素开始，直到某一个数组遍历完
        while (i >= 0 && j >= 0) {
            //将较大的元素放到最后
            if (A[i] > B[j])
                A[k--] = A[i--];
            else
                A[k--] = B[j--];
        }
        //数组A遍历完了，数组B还有，则还需要添加到数组A前面
        if (i < 0) {
            while (j >= 0)
                A[k--] = B[j--];
        }
        //数组B遍历完了，数组A前面正好有，不用再添加
    }

}

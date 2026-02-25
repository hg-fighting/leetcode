package org.jeecgframework.boot.learn.hot101.sort;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  13:56
 * @Description: 数组中的逆序对
 * 描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 数据范围：  对于 50%50% 的数据, size≤10^4
 * 对于 100%100% 的数据, size≤10^5
 * 数组中所有数字的值满足 0≤val≤1000000
 * 要求：空间复杂度 O(n)，时间复杂度 O(nlogn)
 */
public class InversePairs {

    public int mod = 1000000007;

    public int mergeSort(int left, int right, int[] data, int[] temp) {
        //停止划分
        if (left >= right) {
            return 0;
        }
        //取中间
        int mid = (left + right) / 2;
        //左右划分合并
        int res = mergeSort(left, mid, data, temp) + mergeSort(mid + 1, right, data, temp);
        //防止溢出
        res %= mod;
        int i = left, j = mid + 1;
        if (right + 1 - left >= 0) System.arraycopy(data, left, temp, left, right + 1 - left);
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                data[k] = temp[j++];
            } else if (j == right + 1 || temp[i] <= temp[j]) {
                data[k] = temp[i++];
            }
            //左边比右边大，答案增加
            else {
                data[k] = temp[j++];
                // 统计逆序对
                res += mid - i + 1;
            }
        }
        return res % mod;
    }

    public int InversePairs(int[] array) {
        int n = array.length;
        int[] res = new int[n];
        return mergeSort(0, n - 1, array, res);
    }
}

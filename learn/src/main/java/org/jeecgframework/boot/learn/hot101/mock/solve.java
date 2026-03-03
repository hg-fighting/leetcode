package org.jeecgframework.boot.learn.hot101.mock;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:03
 * @Description: 旋转数组
 * 一个数组A中存有 n 个整数，在不允许使用另外数组的前提下，将每个整数循环向右移 M（ M >=0）个位置，
 * 即将A中的数据由（A0 A1 ……AN-1 ）变换为（AN-M …… AN-1 A0 A1 ……AN-M-1 ）（最后 M 个数循环移至最前面的 M 个位置）。
 * 如果需要考虑程序移动数据的次数尽量少，要如何设计移动的方法？
 */
public class solve {

    public int[] solve(int n, int m, int[] a) {
        //取余，因为每次长度为n的旋转数组相当于没有变化
        m = m % n;
        //第一次逆转全部数组元素
        reverse(a, 0, n - 1);
        //第二次只逆转开头m个
        reverse(a, 0, m - 1);
        //第三次只逆转结尾m个
        reverse(a, m, n - 1);
        return a;
    }

    //反转函数
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    //交换函数
    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

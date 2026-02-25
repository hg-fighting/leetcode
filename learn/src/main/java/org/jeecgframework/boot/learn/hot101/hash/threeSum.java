package org.jeecgframework.boot.learn.hot101.hash;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:12
 * @Description: 三数之和
 * 描述
 * 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
 * 注意：
 * 三元组（a、b、c）中的元素必须按非降序排列。（即a≤b≤c）
 * 解集中不能包含重复的三元组。
 * 例如，给定的数组 S = {-10 0 10 20 -10 -40},解集为(-10, -10, 20),(-10, 0, 10)
 * 数据范围：0≤n≤1000，数组中各个元素值满足 ∣val∣≤100
 * 要求：空间复杂度 O(n^2)，时间复杂度 O(n^2)
 */
public class threeSum {

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int n = num.length;
        //不够三元组
        if (n < 3)
            return res;
        //排序
        Arrays.sort(num);
        for (int i = 0; i < n - 2; i++) {
            if (i != 0 && num[i] == num[i - 1])
                continue;
            //后续的收尾双指针
            int left = i + 1;
            int right = n - 1;
            //设置当前数的负值为目标
            int target = -num[i];
            while (left < right) {
                //双指针指向的二值相加为目标，则可以与num[i]组成0
                if (num[left] + num[right] == target) {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(num[i]);
                    temp.add(num[left]);
                    temp.add(num[right]);
                    res.add(temp);
                    while (left + 1 < right && num[left] == num[left + 1])
                        //去重
                        left++;
                    while (right - 1 > left && num[right] == num[right - 1])
                        //去重
                        right--;
                    //双指针向中间收缩
                    left++;
                    right--;
                }
                //双指针指向的二值相加大于目标，右指针向左
                else if (num[left] + num[right] > target)
                    right--;
                    //双指针指向的二值相加小于目标，左指针向右
                else
                    left++;
            }
        }
        return res;
    }

}

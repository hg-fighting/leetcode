package org.jeecgframework.boot.learn.hot101.hash;

import java.util.HashMap;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:12
 * @Description: 最小的缺失正数
 * 描述
 * 给定一个无序数组arr，找到数组中没有出现的最小正整数
 * 例如arr = [-1, 2, 3, 4]。返回1
 * arr = [1, 2, 3, 4]。返回5
 * [要求]: 时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)
 */
public class minNumberDisappeared {

    public int minNumberDisappeared(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
        //哈希表记录数组中出现的每个数字
        for (int i = 0; i < n; i++)
            mp.put(nums[i], 1);
        int res = 1;
        //从1开始找到哈希表中第一个没有出现的正整数
        while (mp.containsKey(res))
            res++;
        return res;
    }
}

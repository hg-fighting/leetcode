package org.jeecgframework.boot.learn.hot101.array;

import java.util.HashMap;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:37
 * @Description: 最长无重复子数组
 * 给定一个数组 nums ，请你找出其中不含有重复元素的 最长子数组 的长度。
 */
public class maxLength {

    public int maxLength(int[] arr) {
        //哈希表记录窗口内非重复的数字
        HashMap<Integer, Integer> mp = new HashMap<>();
        int res = 0;
        //设置窗口左右边界
        for (int left = 0, right = 0; right < arr.length; right++) {
            //窗口右移进入哈希表统计出现次数
            if (mp.containsKey(arr[right]))
                mp.put(arr[right], mp.get(arr[right]) + 1);
            else
                mp.put(arr[right], 1);
            //出现次数大于1，则窗口内有重复
            while (mp.get(arr[right]) > 1)
                //窗口左移，同时减去该数字的出现次数
                mp.put(arr[left], mp.get(arr[left++]) - 1);
            //维护子数组长度最大值
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

}

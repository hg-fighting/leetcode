package org.jeecgframework.boot.learn.hot101.hash;

import java.util.HashMap;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 数组中出现次数超过一半的数字
 * 题目描述
 * 给一个长度为 n 的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * 数据范围：n≤50000，数组中元素的值 0≤val≤10000
 * 要求：空间复杂度：O(1)，时间复杂度 O(n)
 */
public class MoreThanHalfNum_Solution {

    public int MoreThanHalfNum_Solution(int[] array) {
        //哈希表统计每个数字出现的次数
        HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
        //遍历数组
        for (int i = 0; i < array.length; i++) {
            //统计频率
            if (!mp.containsKey(array[i]))
                mp.put(array[i], 1);
            else
                mp.put(array[i], mp.get(array[i]) + 1);
            //一旦有个数大于长度一半的情况即可返回
            if (mp.get(array[i]) > array.length / 2)
                return array[i];
        }
        return 0;
    }

}

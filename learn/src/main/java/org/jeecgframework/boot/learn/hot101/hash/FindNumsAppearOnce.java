package org.jeecgframework.boot.learn.hot101.hash;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 数组中只出现一次的两个数字
 * 描述
 * 一个整型数组里除了两个数字只出现一次，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 数据范围：数组长度 2≤n≤1000，数组中每个数的大小 0<val≤1000000
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 * 提示：输出时按非降序排列。
 */
public class FindNumsAppearOnce {

    public int[] FindNumsAppearOnce(int[] array) {
        HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        //遍历数组
        //统计每个数出现的频率
        for (int j : array)
            if (!mp.containsKey(j))
                mp.put(j, 1);
            else
                mp.put(j, mp.get(j) + 1);
        //再次遍历数组
        //找到频率为1的两个数
        for (int j : array)
            if (mp.get(j) == 1)
                res.add(j);
        //整理次序
        if (res.get(0) < res.get(1))
            return new int[]{res.get(0), res.get(1)};
        else
            return new int[]{res.get(1), res.get(0)};
    }
}

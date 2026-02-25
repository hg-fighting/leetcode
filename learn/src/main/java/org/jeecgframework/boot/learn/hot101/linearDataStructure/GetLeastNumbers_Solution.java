package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:30
 * @Description: 获取最小的k个数
 */
public class GetLeastNumbers_Solution {

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (k > input.length || k == 0) {
            return res;
        }
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }
}

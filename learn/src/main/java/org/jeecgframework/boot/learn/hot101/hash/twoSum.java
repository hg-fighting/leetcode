package org.jeecgframework.boot.learn.hot101.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 两数之和
 * 描述
 * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
 * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
 * 假设给出的数组中只存在唯一解
 */
public class twoSum {

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[]{map.get(target - numbers[i]) + 1, i + 1};
            } else {
                map.put(numbers[i], i);
            }
        }
        return new int[0];
    }
}

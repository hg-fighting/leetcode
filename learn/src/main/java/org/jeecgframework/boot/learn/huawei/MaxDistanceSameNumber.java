package org.jeecgframework.boot.learn.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  9:34
 * @Description: 相同数字的积木游戏 1
 * 题目描述
 * 小华和小薇一起通过玩积木游戏学习数学。
 * 他们有很多积木，每个积木块上都有一个数字，积木块上的数字可能相同。
 * 小华随机拿一些积木挨着排成一排，请小薇找到这排积木中数字相同且所处位置最远的 2块积木块，计算他们的距离。
 * 小薇请你帮忙替解决这个问题。
 * 输入描述
 * 第一行输入为 N，表示小华排成一排的积木总数。
 * 接下来 N 行每行一个数字，表示小华排成一排的积木上数字。
 * 输出描述
 * 相同数字的积木的位置最远距离；
 * 如果所有积木数字都不相同，请返回-1.
 */
public class MaxDistanceSameNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine().trim()); // 积木总数

        // 读取积木数字数组（位置从0开始）
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(scanner.nextLine().trim());
        }
        scanner.close();

        // 哈希表：key=数字，value=[首次出现位置, 末次出现位置]
        Map<Integer, int[]> posMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int num = nums[i];
            if (!posMap.containsKey(num)) {
                // 首次出现，记录[首次, 末次]均为当前位置
                posMap.put(num, new int[]{i, i});
            } else {
                // 非首次出现，更新末次位置
                posMap.get(num)[1] = i;
            }
        }

        int maxDistance = -1;
        // 遍历所有数字，计算最大距离
        for (int[] pos : posMap.values()) {
            int distance = pos[1] - pos[0];
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }

        // 无重复数字则返回-1，否则返回最大距离
        System.out.println(maxDistance > 0 ? maxDistance : -1);
    }
}

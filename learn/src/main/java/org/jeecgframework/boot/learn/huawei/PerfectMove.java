package org.jeecgframework.boot.learn.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  10:20
 * @Description: 完美走位
 * 题目描述
 * 在第一人称射击游戏中，玩家通过键盘的 A、S、D、W 四个按键控制游戏人物分别向左、
 * 向后、向右、向前进行移动，从而完成走位。假设玩家每按动一次键盘，游戏人物会向某
 * 个方向移动一步，如果玩家在操作一定次数的键盘并且各个方向的步数相同时，此时游戏
 * 人物必定会回到原点，则称此次走位为完美走位。
 * 现给定玩家的走位（例如：ASDA）,请通过更换其中 一段连续走位的方式
 * 使得原走位能够变成一个完美走位。其中待更换的连续走位可以是相同长度的任何走位。
 * 请返回待更换的连续走位的最小可能长度。
 * 若果原走位本身是一个完美走位，则返回 0。
 * 输入描述
 * 输入为由键盘字母表示的走位 s，例如：ASDA
 * 输出描述
 * 输出为待更换的连续走位的最小可能长度
 */
public class PerfectMove {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int result = minReplaceLength(s);
        System.out.println(result);
        scanner.close();
    }

    private static int minReplaceLength(String s) {
        int n = s.length();
        // 总步数不能被4整除，无法构成完美走位
        if (n % 4 != 0) {
            return -1;
        }
        // 每个方向的目标步数
        int target = n / 4;

        // 统计初始各方向步数
        Map<Character, Integer> count = new HashMap<>();
        count.put('A', 0);
        count.put('S', 0);
        count.put('D', 0);
        count.put('W', 0);
        for (char c : s.toCharArray()) {
            count.put(c, count.get(c) + 1);
        }

        // 检查是否已经是完美走位
        boolean isPerfect = true;
        for (int cnt : count.values()) {
            if (cnt != target) {
                isPerfect = false;
                break;
            }
        }
        if (isPerfect) {
            return 0;
        }

        // 滑动窗口找最短替换子串
        int minLen = n;
        // 初始化为最大可能长度
        int left = 0;
        for (int right = 0; right < n; right++) {
            // 右指针移动：窗口内包含当前字符，窗口外计数减1
            char c = s.charAt(right);
            count.put(c, count.get(c) - 1);

            // 检查当前窗口是否满足条件：窗口外所有方向步数≤target
            while (checkValid(count, target)) {
                // 更新最小窗口长度
                minLen = Math.min(minLen, right - left + 1);
                // 左指针移动：缩小窗口，窗口外计数加1
                char leftChar = s.charAt(left);
                count.put(leftChar, count.get(leftChar) + 1);
                left++;
            }
        }

        return minLen == n ? -1 : minLen;
    }

    // 检查窗口外的各方向步数是否都≤target（满足则可通过替换窗口内字符补足）
    private static boolean checkValid(Map<Character, Integer> count, int target) {
        for (int cnt : count.values()) {
            if (cnt > target) {
                return false;
            }
        }
        return true;
    }
}
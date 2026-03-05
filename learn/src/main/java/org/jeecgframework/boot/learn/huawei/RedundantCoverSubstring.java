package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/5  11:11
 * @Description: 最左侧冗余覆盖子串
 * 题目描述
 * 给定 2 个字符串 s1 和 s2 和正整数 k，其中 s1 长度为 n1，s2 长度为 n2，在 s2 中选一个子串，满
 * 足：
 * * 该子串长度为 n1+k * 该子串包含 s1 中全部字母
 * * 该子串每个字母的出现次数不小于 s1 中对应的字母
 * 我们称 s2 以长度 k 冗余覆盖 s1。给定 s1、s2 和 k，求最左侧的 s2 以长度 k 冗余覆盖 s1 的
 * 子串的首个元素的下标，如果没有返回-1
 * 举例：
 * s1=ab
 * s2=aabcd
 * k=1
 * 则子串 aab 和 abc 均满足此条件，由于 aab 在 abc 的左侧，aab 的第一个元素下标为 0，因此输
 * 出 0
 * 输入描述
 * 输入三行，第一行为 s1，第二行为 s2，第三行为 k
 * s1 和 s2 只包含小写字母
 * 输出描述
 * 最左侧的 s2 以长度 k 冗余覆盖 s1 的子串首个元素的下标，如果没有返回-1
 */
public class RedundantCoverSubstring {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine().trim();
        String s2 = scanner.nextLine().trim();
        int k = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();

        int n1 = s1.length();
        int n2 = s2.length();
        int targetLen = n1 + k;

        // 边界条件：目标子串长度超过s2，直接返回-1
        if (targetLen > n2) {
            System.out.println(-1);
            return;
        }

        // 1. 统计s1的字符频次（a-z对应数组0-25）
        int[] s1Count = new int[26];
        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }

        // 2. 初始化滑动窗口的频次统计（第一个窗口）
        int[] windowCount = new int[26];
        for (int i = 0; i < targetLen; i++) {
            windowCount[s2.charAt(i) - 'a']++;
        }

        // 3. 检查第一个窗口是否满足条件
        if (isCover(s1Count, windowCount)) {
            System.out.println(0);
            return;
        }

        // 4. 滑动窗口遍历剩余位置
        for (int start = 1; start <= n2 - targetLen; start++) {
            // 移出左边界字符（上一个窗口的第一个字符）
            char leftChar = s2.charAt(start - 1);
            windowCount[leftChar - 'a']--;
            // 移入右边界字符（当前窗口的最后一个字符）
            char rightChar = s2.charAt(start + targetLen - 1);
            windowCount[rightChar - 'a']++;

            // 检查当前窗口是否满足条件
            if (isCover(s1Count, windowCount)) {
                System.out.println(start);
                return;
            }
        }

        // 无满足条件的子串
        System.out.println(-1);
    }

    /**
     * 检查窗口频次是否覆盖s1频次（所有字符次数≥s1）
     */
    private static boolean isCover(int[] s1Count, int[] windowCount) {
        for (int i = 0; i < 26; i++) {
            // 若s1中有该字符，但窗口内次数不足，直接不满足
            if (s1Count[i] > windowCount[i]) {
                return false;
            }
        }
        return true;
    }
}

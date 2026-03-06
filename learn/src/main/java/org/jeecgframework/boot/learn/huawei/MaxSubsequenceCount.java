package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  8:59
 * @Description: 最多提取子串数目
 * 题目描述
 * 给定由 [a-z]
 * 26 个英文小写字母组成的字符串 A 和 B，其中 A 中可能存在重复字母，B 中不会存在重复字母
 * 现从字符串 A 中按规则挑选一些字母，可以组成字符串 B。
 * 挑选规则如下：
 * 1） 同一个位置的字母只能被挑选一次
 * 2） 被挑选字母的相对先后顺序不能改变
 * 求最多可以同时从 A 中挑选多少组能组成 B 的字符串
 * 输入描述
 * 输入为 2 行，第 1 行输入字符串 A，第 2 行输入字符串 B，行首行尾无多余空格
 * 其中 A、B 均由[a-z] 26 个英文小写字母组成
 * 0<A.length<100，A 中可能包含重复字母
 * 0<B.length<10，B 中不会出现重复字母
 * 输出描述
 * 输出 1 行，包含 1 个数字，表示最多可以同时从 A 中挑选多少组能组成 B 的字符串
 * 行末无多余空格
 */
public class MaxSubsequenceCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String A = scanner.nextLine().trim();
        String B = scanner.nextLine().trim();
        scanner.close();

        // 边界条件：B为空或A比B短（无法组成1组）
        if (B.isEmpty() || A.length() < B.length()) {
            System.out.println(0);
            return;
        }

        int count = 0; // 最大组数
        int lenA = A.length();
        int lenB = B.length();
        boolean[] used = new boolean[lenA]; // 标记A中字符是否被使用

        // 循环匹配，直到无法组成新的B子序列
        while (true) {
            int bIndex = 0; // 当前匹配B的第几个字符
            // 遍历A，尝试匹配当前轮的B
            for (int aIndex = 0; aIndex < lenA && bIndex < lenB; aIndex++) {
                // 字符未被使用，且匹配B的当前字符
                if (!used[aIndex] && A.charAt(aIndex) == B.charAt(bIndex)) {
                    bIndex++; // 匹配下一个B的字符
                    used[aIndex] = true; // 标记为已使用
                }
            }
            // 若完整匹配了B，组数+1；否则终止循环
            if (bIndex == lenB) {
                count++;
            } else {
                break;
            }
        }

        System.out.println(count);
    }
}

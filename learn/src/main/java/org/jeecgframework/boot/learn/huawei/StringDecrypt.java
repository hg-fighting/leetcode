package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/5  16:32
 * @Description: 字符串解密
 * 题目描述
 * 给定两个字符串 string1 和 string2。
 * string1 是一个被加扰的字符串。string1 由小写英文字母（'a'~'z'）和数字字符（'0'~'9'）组成
 * ，而加扰字符串由'0'~'9'、'a'~'f'组成。string1 里面可能包含 0 个或多个加扰子串，剩下可能
 * 有 0 个或多个有效子串，这些有效子串被加扰子串隔开。
 * string2 是一个参考字符串，仅由小写英文字母（'a'~'z'）组成。
 * 你需要在 string1 字符串里找到一个有效子串，这个有效子串要同时满足下面两个条件：
 * （
 * 1）这个有效子串里不同字母的数量不超过且最接近于 string2 里不同字母的数量，即小
 * 于或等于 string2 里不同字母的数量的同时且最大。
 * （
 * 2）这个有效子串是满足条件（
 * 1）里的所有子串（如果有多个的话）里字典序最大的一
 * 个。
 * 如果没有找到合适条件的子串的话，请输出"Not Found" 示例：
 * 输入字符串 string1 为"thisisanewday111forme"，输入字符串 string2 为"good"。string1 里有效
 * 子串和加扰子串分割后可表示为："thisis"+"a"+"n"+"e"+"w"+"da"+"y"+"111f"+"orm"+"e"，
 * 去除加扰子串（"a"、"e"、"da"、"111f"、"e"）后的有效子串候选为("thisis", "n", "w", "y", "orm")。输入字符串 string2 里不同字母的数量为 3（'g'、'o'、'd'），从有效子串候选里可以
 * 找出"orm"满足要求，其不同字母的数量为 3，最接近于 string2 不同字母的数量。 输入描述
 * input_string1
 * input_string2
 * 说明：输入为两个字符串，第 1 行是题目里的 string1（被加扰的字符串），第 2 行是题目里
 * 的 string2（参考字符串）
 * 输出描述
 * output_string
 * 说明：输出为一个字符串（有效字符串）
 */
public class StringDecrypt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string1 = scanner.nextLine().trim();
        String string2 = scanner.nextLine().trim();
        scanner.close();

        // 步骤1：计算string2的不同字母数量
        int targetCount = getDistinctCharCount(string2);

        // 步骤2：分割string1，提取所有有效子串
        List<String> validSubstrings = extractValidSubstrings(string1);

        // 步骤3：筛选符合条件的有效子串
        String result = findBestSubstring(validSubstrings, targetCount);

        // 步骤4：输出结果
        System.out.println(result == null ? "Not Found" : result);
    }

    /**
     * 计算字符串中不同字母的数量（仅小写字母）
     */
    private static int getDistinctCharCount(String s) {
        Set<Character> distinctChars = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                distinctChars.add(c);
            }
        }
        return distinctChars.size();
    }

    /**
     * 分割string1，提取所有有效子串（非加扰子串）
     * 加扰子串定义：包含0-9/a-f的子串；有效子串：仅含g-z的子串
     */
    private static List<String> extractValidSubstrings(String s) {
        List<String> validList = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (char c : s.toCharArray()) {
            // 判断当前字符是否是加扰字符（0-9 或 a-f）
            boolean isScrambleChar = (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f');

            if (isScrambleChar) {
                // 遇到加扰字符：若当前有有效字符，加入列表并清空
                if (!current.isEmpty()) {
                    validList.add(current.toString());
                    current.setLength(0);
                }
            } else {
                // 有效字符（g-z），加入当前缓存
                current.append(c);
            }
        }

        // 处理最后一段有效子串
        if (!current.isEmpty()) {
            validList.add(current.toString());
        }

        return validList;
    }

    /**
     * 找到符合条件的最优子串
     * 条件1：不同字母数≤targetCount且最大；条件2：字典序最大
     */
    private static String findBestSubstring(List<String> validList, int targetCount) {
        if (validList.isEmpty()) {
            return null;
        }

        int maxValidCount = -1; // 满足条件1的最大不同字母数
        List<String> candidates = new ArrayList<>(); // 候选子串列表

        // 第一步：找到maxValidCount
        for (String sub : validList) {
            int count = getDistinctCharCount(sub);
            if (count <= targetCount && count > maxValidCount) {
                maxValidCount = count;
            }
        }

        // 无符合条件1的子串
        if (maxValidCount == -1) {
            return null;
        }

        // 第二步：筛选出所有不同字母数=maxValidCount的子串
        for (String sub : validList) {
            if (getDistinctCharCount(sub) == maxValidCount) {
                candidates.add(sub);
            }
        }

        // 第三步：选字典序最大的子串
        candidates.sort(Collections.reverseOrder());
        return candidates.get(0);
    }
}
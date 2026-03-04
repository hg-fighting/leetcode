package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/4  16:21
 * @Description: 字符串重新排序
 * 题目描述
 * 给定一个字符串 s，s 包含以空格分隔的若干个单词，请对 s 进行如下处理后输出：
 * 1、单词内部调整：对每个单词字母重新按字典序排序；
 * 2、单词间顺序调整：
 * 1）统计每个单词出现的次数，并按次数降序排列；
 * 2）次数相同时，按单词长度升序排列；
 * 3）次数和单词长度均相同时，按字典序升序排列。
 * 请输出处理后的字符串，每个单词以一个空格分隔。 输入描述
 * 一行字符串，每个字符取值范围：[a-zA-Z0-9]以及空格，字符串长度范围：[1, 1000]
 * 输出描述
 * 重新排序后的字符串，每个单词间隔 1 个空格，且首尾无空格
 * 示例 1：基础场景
 * 输入：bac abc 321 123 abc
 * 步骤 1（内部排序）：abc abc 123 123 abc
 * 步骤 2（次数统计）：abc(3 次)、123(2 次)
 * 步骤 3（单词排序）：abc（次数更高）→ 123
 * 输出：abc abc abc 123 123
 */
public class StringReorder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入字符串（包含空格分隔的单词）
        String s = scanner.nextLine();
        // 分割成单词数组（按空格分割，处理多个连续空格的情况）
        String[] originalWords = s.split("\\s+");

        // 步骤1：处理每个单词内部字母排序
        List<String> processedWords = new ArrayList<>();
        for (String word : originalWords) {
            // 拆分单词为字符数组，按字典序排序
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            // 拼接成排序后的新单词
            String sortedWord = new String(chars);
            processedWords.add(sortedWord);
        }

        // 步骤2：统计每个处理后单词的出现次数
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : processedWords) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        // 步骤3：对单词进行自定义排序
        List<String> uniqueWords = new ArrayList<>(countMap.keySet());
        uniqueWords.sort((w1, w2) -> {
            // 规则1：次数降序
            int count1 = countMap.get(w1);
            int count2 = countMap.get(w2);
            if (count1 != count2) {
                return Integer.compare(count2, count1);
            }
            // 规则2：次数相同时，长度升序
            int len1 = w1.length();
            int len2 = w2.length();
            if (len1 != len2) {
                return Integer.compare(len1, len2);
            }
            // 规则3：次数和长度都相同时，字典序升序
            return w1.compareTo(w2);
        });

        // 步骤4：拼接最终结果（按排序后的单词，重复对应次数）
        List<String> resultList = new ArrayList<>();
        for (String word : uniqueWords) {
            int count = countMap.get(word);
            for (int i = 0; i < count; i++) {
                resultList.add(word);
            }
        }

        // 拼接成字符串，首尾无空格，单词间一个空格分隔
        String result = String.join(" ", resultList);
        System.out.println(result);

        scanner.close();
    }
}

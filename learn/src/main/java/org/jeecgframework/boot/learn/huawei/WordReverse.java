package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  10:05
 * @Description: 单词倒序
 * 题目描述
 * 输入单行英文句子，里面包含英文字母，空格以及,.?
 * 三种标点符号，请将句子内每个单词进行倒序，并输出倒序后的语句
 * 输入描述
 * 输入字符串 S，S 的长度 1≤N≤100
 * 输出描述
 * 输出逆序后的字符串
 * 输入	输出	说明
 * Hello world!	olleH dlrow!	普通单词 + 感叹号
 * I love Java, and you?	I evol avaJ, dna uoy?	多单词 + 逗号 + 问号
 * Hi, how are you?	iH, woh era uoy?	单词后紧跟标点
 * A simple test.	A elpmis tset.	单个字母单词 + 句点
 */

public class WordReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取整行输入（包含空格）
        String input = scanner.nextLine();
        StringBuilder result = new StringBuilder();
        // 缓存当前正在处理的单词（仅字母）
        StringBuilder currentWord = new StringBuilder();

        // 遍历每个字符
        for (char c : input.toCharArray()) {
            // 判断是否为字母（仅处理字母组成的单词）
            if (Character.isLetter(c)) {
                currentWord.append(c);
            } else {
                // 遇到非字母（空格/标点）：先倒序输出当前单词，再输出分隔符
                if (!currentWord.isEmpty()) {
                    result.append(currentWord.reverse());
                    // 清空缓存
                    currentWord.setLength(0);
                }
                result.append(c);
            }
        }

        // 处理最后一个单词（句子末尾无分隔符的情况）
        if (!currentWord.isEmpty()) {
            result.append(currentWord.reverse());
        }

        // 输出结果
        System.out.println(result);
        scanner.close();
    }
}

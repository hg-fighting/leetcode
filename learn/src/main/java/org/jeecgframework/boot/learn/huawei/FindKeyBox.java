package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  9:47
 * @Description: 寻找关键钥匙
 * 题目描述
 * 小强正在参加《密室逃生》游戏，当前关卡要求找到符合给定
 * 密码 K（升序的不重复小写字母组成） 的箱子，并给出箱子编号，箱子编号为 1~N。
 * 每个箱子中都有一个 字符串 s ，字符串由大写字母，小写字母，数字，标点符号，空格组成，需要在这些字符串中找出
 * 所有的字母，忽略大小写后排列出对应的密码串，并返回匹配密码的箱子序号
 * 提示：
 * 满足条件的箱子不超过 1 个
 * 输入描述
 * 第一行为 key 的字符串，第二行为箱子 boxes，为数组样式，以空格分隔
 * 箱子 N 数量满足 1<=N<=10000, s 长度满足 0<=s.length<=50, 密码为仅包含小写字母的升序字符串，且不存在重复字母, 密码 K 长度 K.length，1<=K.length<=26
 * 输出描述
 * 返回对应箱子编号
 * 如不存在符合要求的密码箱，则返回-1
 */
public class FindKeyBox {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 步骤1：读取密码K（升序不重复小写字母）
        String key = scanner.nextLine().trim();
        // 步骤2：读取箱子数组（注意输入是数组样式，需处理格式）
        String boxesInput = scanner.nextLine().trim();
        scanner.close();

        // 解析箱子数组：去除首尾的[]，按空格分割
        String[] boxes = parseBoxesInput(boxesInput);
        int boxCount = boxes.length;

        // 步骤3：遍历每个箱子，验证是否匹配密码
        int result = -1;
        for (int i = 0; i < boxCount; i++) {
            String boxStr = boxes[i];
            // 处理当前箱子字符串，生成标准化密码串
            String normalized = normalizeBoxString(boxStr);
            // 匹配则记录编号（i+1，因为箱子编号从1开始）
            if (normalized.equals(key)) {
                result = i + 1;
                break; // 题目说明满足条件的箱子不超过1个，直接退出
            }
        }

        // 步骤4：输出结果
        System.out.println(result);
    }

    /**
     * 解析箱子输入字符串（数组样式，如 "[abc 123 def]" → ["abc", "123", "def"]）
     */
    private static String[] parseBoxesInput(String input) {
        // 去除首尾的[]，处理空输入
        if (input == null || input.isEmpty()) {
            return new String[0];
        }
        String trimmed = input.replaceAll("^\\[|]$", "").trim();
        // 空数组（如"[]"）
        if (trimmed.isEmpty()) {
            return new String[0];
        }
        // 按空格分割（兼容多个空格的情况）
        return trimmed.split("\\s+");
    }

    /**
     * 标准化箱子字符串：提取字母→转小写→去重→升序排序→拼接
     */
    private static String normalizeBoxString(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        // TreeSet自动去重+升序排序
        TreeSet<Character> letters = new TreeSet<>();
        for (char c : s.toCharArray()) {
            // 仅提取字母（忽略大小写）
            if (Character.isLetter(c)) {
                letters.add(Character.toLowerCase(c));
            }
        }
        // 拼接为字符串
        StringBuilder sb = new StringBuilder();
        for (char c : letters) {
            sb.append(c);
        }
        return sb.toString();
    }
}
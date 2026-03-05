package org.jeecgframework.boot.learn.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  15:53
 * @Description: 箱子之形摆放
 * 题目描述
 * 有一批箱子（形式为字符串，设为 str），要求将这批箱子按从上到下以之形的顺序摆放在
 * 宽度为 n 的空地，请输出箱子的摆放结果。
 * 例如：箱子为 ABCDEFG，空地宽度为 3，摆放结果如图：
 * 则输出结果为：
 * AFG
 * BE
 * CD
 * 输入描述
 * 输入一行字符串，通过空格分割，前面部分为字母或数字组成的字符串 str，表示箱子；后
 * 面部分为一个数字 n，表示空地的宽度。例如：
 * ABCDEFG 3
 * 输出描述
 * 箱子摆放结果，如题目示例所示
 * AFG
 * BE
 * CD
 */
public class ZigzagBox {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入：分割为字符串和宽度n
        String[] input = scanner.nextLine().trim().split(" ");
        String str = input[0];
        int n = Integer.parseInt(input[1]);
        scanner.close();

        // 特殊情况：宽度n=1，直接输出原字符串
        if (n == 1) {
            System.out.println(str);
            return;
        }

        // 1. 初始化n行的字符列表
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rows.add(new StringBuilder());
        }

        int index = 0; // 当前字符索引
        int strLen = str.length();
        int col = 0;   // 当前列索引

        // 2. 按列填充字符
        while (index < strLen) {
            boolean isOddCol = (col % 2 == 0); // 奇数列（从0开始计数，0是第一列）

            if (isOddCol) {
                // 奇数列：垂直向下填充
                for (int i = 0; i < n && index < strLen; i++) {
                    rows.get(i).append(str.charAt(index++));
                }
            } else {
                // 偶数列：垂直向上填充
                for (int i = n - 1; i >= 0 && index < strLen; i--) {
                    rows.get(i).append(str.charAt(index++));
                }
            }

            col++; // 移动到下一列
        }

        // 3. 按行输出结果
        for (StringBuilder row : rows) {
            System.out.println(row.toString());
        }
    }
}

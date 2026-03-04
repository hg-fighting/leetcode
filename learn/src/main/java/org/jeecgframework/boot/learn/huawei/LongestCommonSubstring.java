package org.jeecgframework.boot.learn.huawei;

/**
 * @Author: hao gang
 * @Date: 2026/3/3
 * @Description: 查找重复代码
 * 题目描述
 * 小明负责维护项目下的代码，需要查找出重复代码，用以支撑后续的代码优化，请你帮助
 * 小明找出重复的代码，。
 * 重复代码查找方法：以字符串形式给定两行代码（字符串长度 1 < length <= 100，由英文字母、数字和空格组成），找出两行代码中的最长公共子串。
 * 注： 如果不存在公共子串，返回空字符串
 * 输入描述
 * 输入的参数 text1, text2 分别表示两行代码
 * 输出描述
 * 输出任一最长公共子串
 * abc123 def
 * xyz123 ghi
 * text1的1（下标 3）和text2的1（下标 3）相等，dp[4][4]=1；
 * 后续2（下标 4）和2（下标 4）相等，dp[5][5]=2；
 * 后续3（下标 5）和3（下标 5）相等，dp[6][6]=3；
 * 最长子串：123，输出：123
 */
public class LongestCommonSubstring {

    public static String findLongestCommonSubstring(String text1, String text2) {
        // 处理空字符串边界
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return "";
        }

        int m = text1.length();
        int n = text2.length();
        // DP数组：dp[i][j]表示text1[0..i-1]和text2[0..j-1]以i-1/j-1结尾的最长公共子串长度
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0; // 最长公共子串长度
        int endIndex = 0; // 最长公共子串在text1中的结束下标

        // 遍历两个字符串，填充DP数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 字符相等时，延续前一个位置的长度+1
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    // 更新最长长度和结束位置
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        endIndex = i - 1; // text1的下标从0开始
                    }
                } else {
                    // 字符不相等，连续子串中断，长度重置为0
                    dp[i][j] = 0;
                }
            }
        }

        // 无公共子串，返回空
        if (maxLen == 0) {
            return "";
        }

        // 从text1中截取最长公共子串（结束位置-长度+1 到 结束位置）
        int startIndex = endIndex - maxLen + 1;
        return text1.substring(startIndex, endIndex + 1);
    }

    public static void main(String[] args) {
        // 测试用例（可替换为输入读取逻辑）
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String text1 = scanner.nextLine();
        String text2 = scanner.nextLine();
        scanner.close();

        String result = findLongestCommonSubstring(text1, text2);
        System.out.println(result);
    }
}

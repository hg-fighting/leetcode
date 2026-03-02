package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  15:53
 * @Description: 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
public class match {

    public boolean match(String str, String pattern) {
        int n1 = str.length();
        int n2 = pattern.length();
        //dp[i][j]表示str前i个字符和pattern前j个字符是否匹配
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        //遍历str每个长度
        for (int i = 0; i <= n1; i++) {
            //遍历pattern每个长度
            for (int j = 0; j <= n2; j++) {
                //空正则的情况
                if (j == 0) {
                    dp[i][j] = (i == 0);
                    //非空的情况下 星号、点号、字符
                } else {
                    if (pattern.charAt(j - 1) != '*') {
                        //当前字符不为*，用.去匹配或者字符直接相同
                        if (i > 0 && (str.charAt(i - 1) == pattern.charAt(j - 1) ||
                                pattern.charAt(j - 1) == '.')) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else {
                        //碰到*
                        if (j >= 2) {
                            dp[i][j] |= dp[i][j - 2];
                        }
                        //若是前一位为.或者前一位可以与这个数字匹配
                        if (i >= 1 && j >= 2 && (str.charAt(i - 1) == pattern.charAt(j - 2) ||
                                pattern.charAt(j - 2) == '.')) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }
        }
        return dp[n1][n2];
    }

}

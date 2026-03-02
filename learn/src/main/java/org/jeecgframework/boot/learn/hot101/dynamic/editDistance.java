package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  15:47
 * @Description: 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * @Description: 编辑距离
 */
public class editDistance {

    public int editDistance(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        //dp[i][j]表示到str1[i]和str2[j]为止的子串需要的编辑距离
        int[][] dp = new int[n1 + 1][n2 + 1];
        //初始化边界
        for (int i = 1; i <= n1; i++)
            dp[i][0] = dp[i - 1][0] + 1;
        for (int i = 1; i <= n2; i++)
            dp[0][i] = dp[0][i - 1] + 1;
        //遍历第一个字符串的每个位置
        for (int i = 1; i <= n1; i++)
            //对应第二个字符串每个位置
            for (int j = 1; j <= n2; j++) {
                //若是字符相同，此处不用编辑
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    //直接等于二者前一个的距离
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    //选取最小的距离加上此处编辑距离1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
            }
        return dp[n1][n2];
    }

}

package org.jeecgframework.boot.learn.hot101.dynamic;

/**
 * @Author: hao gang
 * @Date: 2026/2/27  15:10
 * @Description: 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class getLongestPalindrome {

    public int fun(String s, int begin, int end) {
        //每个中心点开始扩展
        while (begin >= 0 && end < s.length() && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        //返回长度
        return end - begin - 1;
    }

    public int getLongestPalindrome(String A) {
        int maxlen = 1;
        //以每个点为中心
        for (int i = 0; i < A.length() - 1; i++)
            //分奇数长度和偶数长度向两边扩展
            maxlen = Math.max(maxlen, Math.max(fun(A, i, i), fun(A, i, i + 1)));
        return maxlen;
    }

}

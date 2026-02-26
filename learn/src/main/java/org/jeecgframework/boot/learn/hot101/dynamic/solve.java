package org.jeecgframework.boot.learn.hot101.dynamic;

import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:51
 * @Description: 解码
 * 描述
 * 有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
 * 我们把一个字符串编码成一串数字，再考虑逆向编译成字符串。
 * 由于没有分隔符，数字编码成字母可能有多种编译结果，例如 11 既可以看做是两个 'a' 也可以看做是一个 'k'。
 * 现给定数字串，返回可能的解码结果数。
 */
public class solve {

    public int solve (String nums) {
        //排除0
        if (nums.equals("0"))
            return 0;
        //排除只有一种可能的10 和 20
        if (nums.equals("10") || nums.equals("20"))
            return 1;
        //当0的前面不是1或2时，无法译码，0种
        for (int i = 1; i < nums.length(); i++) {
            if (nums.charAt(i) == '0')
                if (nums.charAt(i - 1) != '1' && nums.charAt(i - 1) != '2')
                    return 0;
        }
        int[] dp = new int[nums.length() + 1];
        //辅助数组初始化为1
        Arrays.fill(dp, 1);
        for (int i = 2; i <= nums.length(); i++) {
            //在11-19，21-26之间的情况
            if ((nums.charAt(i - 2) == '1' && nums.charAt(i - 1) != '0') ||
                    (nums.charAt(i - 2) == '2' && nums.charAt(i - 1) > '0' &&
                            nums.charAt(i - 1) < '7'))
                dp[i] = dp[i - 1] + dp[i - 2];
            else
                dp[i] = dp[i - 1];
        }
        return dp[nums.length()];
    }
}

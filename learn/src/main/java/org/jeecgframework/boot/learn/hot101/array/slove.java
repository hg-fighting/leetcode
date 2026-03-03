package org.jeecgframework.boot.learn.hot101.array;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:34
 * @Description: 反转字符串
 */
public class slove {

    public String solve(String str) {
        //左右双指针
        char[] s = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;
        //两指针往中间靠
        while (left < right) {
            char c = s[left];
            //交换位置
            s[left] = s[right];
            s[right] = c;
            left++;
            right--;
        }
        return new String(s);
    }

    public String solve2(String str) {
        //从一个空串开始
        StringBuilder output = new StringBuilder();
        //逆序遍历字符串
        for (int i = str.length() - 1; i >= 0; i--)
            //将字符加到新串后面
            output.append(str.charAt(i));
        return output.toString();
    }
}

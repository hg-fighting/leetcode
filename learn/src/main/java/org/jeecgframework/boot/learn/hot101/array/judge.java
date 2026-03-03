package org.jeecgframework.boot.learn.hot101.array;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:14
 * @Description: 判断是否为回文字符串
 * 回文字符串：正着读和反着读都一样的字符串
 */
public class judge {

    /**
     * 双指针法
     */
    public boolean judge(String str) {
        //首指针
        int left = 0;
        //尾指针
        int right = str.length() - 1;
        //首尾往中间靠
        while (left < right) {
            //比较前后是否相同
            if (str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    /**
     * 反转字符串法
     */
    public boolean judge2(String str) {
        StringBuffer temp = new StringBuffer(str);
        //反转字符串
        String s = temp.reverse().toString();
        //比较字符串是否相等
        if (s.equals(str))
            return true;
        return false;
    }
}

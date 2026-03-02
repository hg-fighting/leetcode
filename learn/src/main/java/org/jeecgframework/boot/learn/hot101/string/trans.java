package org.jeecgframework.boot.learn.hot101.string;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  16:40
 * @Description: 字符串变形
 * 描述：
 * 对于一个长度为 n 字符串，我们需要对它做一些变形。
 * 首先这个字符串中包含着一些空格，就像"Hello World"一样，然后我们要做的是把这个字符串中由空格隔开的单词反序，同时反转每个字符的大小写。
 * 比如"Hello World"变形后就变成了"wORLD hELLO"。
 */
public class trans {

    public String trans(String s, int n) {
        if (n == 0)
            return s;
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < n; i++) {
            //大小写转换
            if (s.charAt(i) <= 'Z' && s.charAt(i) >= 'A')
                res.append((char) (s.charAt(i) - 'A' + 'a'));
            else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                res.append((char) (s.charAt(i) - 'a' + 'A'));
            else
                //空格直接复制
                res.append(s.charAt(i));
        }
        //翻转整个字符串
        res = res.reverse();
        for (int i = 0; i < n; i++) {
            int j = i;
            //以空格为界，二次翻转
            while (j < n && res.charAt(j) != ' ')
                j++;
            String temp = res.substring(i, j);
            StringBuffer buffer = new StringBuffer(temp);
            temp = buffer.reverse().toString();
            res.replace(i, j, temp);
            i = j;
        }
        return res.toString();
    }

}

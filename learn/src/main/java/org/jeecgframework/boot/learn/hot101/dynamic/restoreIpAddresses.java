package org.jeecgframework.boot.learn.hot101.dynamic;

import java.util.ArrayList;

/**
 * @Author: hao gang
 * @Date: 2026/3/2  15:40
 * @Description: 数字字符串转化成IP地址
 * 描述：给定一个只包含数字的字符串，复原所有可能的IP地址。
 * 一个有效的IP地址正好包含4个整数，每个整数之间用点号分隔。0到255之间的整数为一个有效的整数。
 * 例如，给定字符串"25525511135"，那么有效的IP地址有"255.255.11.135"，"255.255.111.35"。
 * 但是"255.255.111.35.1"，"255.255.1111.35"，"255.255.11.1351"等都不是有效的IP地址。
 * 输入：字符串s="25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 */
public class restoreIpAddresses {

    /**
     * 枚举
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<String>();
        int n = s.length();
        //遍历IP的点可能的位置（第一个点）
        for (int i = 1; i < 4 && i < n - 2; i++) {
            //第二个点的位置
            for (int j = i + 1; j < i + 4 && j < n - 1; j++) {
                //第三个点的位置
                for (int k = j + 1; k < j + 4 && k < n; k++) {
                    //最后一段剩余数字不能超过3
                    if (n - k >= 4)
                        continue;
                    //从点的位置分段截取
                    String a = s.substring(0, i);
                    String b = s.substring(i, j);
                    String c = s.substring(j, k);
                    String d = s.substring(k);
                    //IP每个数字不大于255
                    if (Integer.parseInt(a) > 255 || Integer.parseInt(b) > 255 ||
                            Integer.parseInt(c) > 255 || Integer.parseInt(d) > 255)
                        continue;
                    //排除前导0的情况
                    if ((a.length() != 1 && a.charAt(0) == '0') || (b.length() != 1 &&
                            b.charAt(0) == '0') || (c.length() != 1 && c.charAt(0) == '0') ||
                            (d.length() != 1 && d.charAt(0) == '0'))
                        continue;
                    //组装IP地址
                    String temp = a + "." + b + "." + c + "." + d;
                    res.add(temp);
                }
            }
        }
        return res;
    }

    /**
     * 递归+回溯
     */
    //记录分段IP数字字符串
    private String nums = "";

    //step表示第几个数字，index表示字符串下标
    public void dfs(String s, ArrayList<String> res, int step, int index) {
        //当前分割出的字符串
        String cur = "";
        //分割出了四个数字
        if (step == 4) {
            //下标必须走到末尾
            if (index != s.length())
                return;
            res.add(nums);
        } else {
            //最长遍历3位
            for (int i = index; i < index + 3 && i < s.length(); i++) {
                cur += s.charAt(i);
                //转数字比较
                int num = Integer.parseInt(cur);
                String temp = nums;
                //不能超过255且不能有前导0
                if (num <= 255 && (cur.length() == 1 || cur.charAt(0) != '0')) {
                    //添加点
                    if (step - 3 != 0)
                        nums += cur + ".";
                    else
                        nums += cur;
                    //递归查找下一个数字
                    dfs(s, res, step + 1, i + 1);
                    //回溯
                    nums = temp;
                }
            }
        }
    }

    public ArrayList<String> restoreIpAddresses2(String s) {
        ArrayList<String> res = new ArrayList<String>();
        dfs(s, res, 0, 0);
        return res;
    }

}

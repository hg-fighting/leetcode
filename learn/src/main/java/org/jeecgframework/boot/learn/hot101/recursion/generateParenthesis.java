package org.jeecgframework.boot.learn.hot101.recursion;

import java.util.ArrayList;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  13:22
 * @Description: 括号生成
 * 描述
 * 给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
 * 例如，给出n=3，解集为：
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * 数据范围：0≤n≤10
 * 要求：空间复杂度 O(n)，时间复杂度 O(2^n)
 */
public class generateParenthesis {

    public void recursion(int left, int right, String temp, ArrayList<String> res,
                          int n) {
        //左右括号都用完了，就加入结果
        if (left == n && right == n) {
            res.add(temp);
            return;
        }
        //使用一次左括号
        if (left < n) {
            recursion(left + 1, right, temp + "(", res, n);
        }
        //使用右括号个数必须少于左括号
        if (right < n && left > right) {
            recursion(left, right + 1, temp + ")", res, n);
        }
    }

    public ArrayList<String> generateParenthesis(int n) {
        //记录结果
        ArrayList<String> res = new ArrayList<String>();
        //递归
        recursion(0, 0, "", res, n);
        return res;
    }
}

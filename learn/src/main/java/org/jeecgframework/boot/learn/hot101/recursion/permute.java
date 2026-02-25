package org.jeecgframework.boot.learn.hot101.recursion;

import java.util.ArrayList;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  11:59
 * @Description: 没有重复项数字的全排列
 * 给定一个数字列表，返回其所有可能的排列。
 * 你可以假设没有重复数字。
 * 例如：
 * 给出一个列表[1,2,3]，其全排列为：
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * 给出一个列表[1,2]，其全排列为：
 * [
 * [1,2],
 * [2,1]
 * ]
 * 挑战
 * 使用递归和非递归分别解决。
 * 数据范围：数字个数 0<n≤6
 * 要求：空间复杂度 O(n!)，时间复杂度 O(n!)
 */
public class permute {

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        dfs(num, res, new ArrayList<>(), 0);
        return res;

    }

    void dfs(int[] num, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> t, int index) {
        if (t.size() == num.length) {
            res.add(new ArrayList<>(t));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (t.contains(num[i])) {
                continue;
            }
            t.add(num[i]);
            dfs(num, res, t, i + 1);
            t.remove(t.size() - 1);
        }
    }
}

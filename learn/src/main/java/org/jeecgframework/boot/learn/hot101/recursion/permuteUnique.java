package org.jeecgframework.boot.learn.hot101.recursion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  9:47
 * @Description: 全排列 II
 * 描述
 * 给出一组可能包含重复项的数字，返回该组数字的所有排列。结果以字典序升序排列。
 * 数据范围： 0<n≤8 ，数组中的值满足 −1≤val≤5
 * 要求：空间复杂度 O(n!)O(n!)，时间复杂度 O(n!)O(n!)
 * 例如：
 * 输入：[1,1,2]
 * 返回值：[[1,1,2],[1,2,1],[2,1,1]]
 */
public class permuteUnique {

    public void recursion(ArrayList<ArrayList<Integer>> res, int[] num,
                          ArrayList<Integer> temp, Boolean[] vis) {
        //临时数组满了加入输出
        if (temp.size() == num.length) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        //遍历所有元素选取一个加入
        for (int i = 0; i < num.length; i++) {
            //如果该元素已经被加入了则不需要再加入了
            if (vis[i])
                continue;
            if (i > 0 && num[i - 1] == num[i] && !vis[i - 1])
                //当前的元素num[i]与同一层的前一个元素num[i-1]相同且num[i-1]已经用过了
                continue;
            //标记为使用过
            vis[i] = true;
            //加入数组
            temp.add(num[i]);
            recursion(res, num, temp, vis);
            //回溯
            vis[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        //先按字典序排序
        Arrays.sort(num);
        Boolean[] vis = new Boolean[num.length];
        Arrays.fill(vis, false);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        recursion(res, num, temp, vis);
        return res;
    }
}

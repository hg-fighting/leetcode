package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  15:02
 * @Description: 按之字形顺序打印二叉树
 * 描述
 * 给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
 * 例如：
 * 给定的二叉树是{3,9,20,#,#,15,7},
 * 该二叉树之字形层序遍历的结果是
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * 提示:
 * 0≤n≤1500
 * 数据范围：0≤val≤1500
 * 要求：空间复杂度：O(n)，时间复杂度：O(n)
 */
public class Print {

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        TreeNode head = pRoot;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (head == null)
        //如果是空，则直接返回空list
        {
            return res;
        }
        //队列存储，进行层次遍历
        Queue<TreeNode> temp = new LinkedList<TreeNode>();
        temp.offer(head);
        TreeNode p;
        boolean flag = true;
        while (!temp.isEmpty()) {
            //记录二叉树的某一行
            ArrayList<Integer> row = new ArrayList<Integer>();
            int n = temp.size();
            //奇数行反转，偶数行不反转
            flag = !flag;
            //因先进入的是根节点，故每层节点多少，队列大小就是多少
            for (int i = 0; i < n; i++) {
                p = temp.poll();
                row.add(p.val);
                //若是左右孩子存在，则存入左右孩子作为下一个层次
                if (p.left != null) {
                    temp.offer(p.left);
                }
                if (p.right != null) {
                    temp.offer(p.right);
                }
            }
            //奇数行反转，偶数行不反转
            if (flag) {
                Collections.reverse(row);
            }
            res.add(row);
        }
        return res;

    }
}

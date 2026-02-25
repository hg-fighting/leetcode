package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  15:56
 * @Description: 完全二叉树判断
 * 描述
 * 给定一个二叉树，确定他是否是一个完全二叉树。
 * 完全二叉树的定义：若二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（第 h 层可能包含 1~ 2h 个节点）
 * 数据范围：节点数满足 1≤n≤100
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class isCompleteTree {

    /**
     * 完全二叉树判断：层序遍历，遇到空节点后，队列中剩余的节点都应该是叶子节点
     */
    public boolean isCompleteTree(TreeNode root) {
        //空树一定是完全二叉树
        if (root == null) {
            return true;
        }
        //辅助队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode cur;
        //定义一个首次出现的标记位
        boolean notComplete = false;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            //标记第一次遇到空节点
            if (cur == null) {
                notComplete = true;
                continue;
            }
            //后续访问已经遇到空节点了，说明经过了叶子
            if (notComplete) {
                return false;
            }
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return true;
    }

}

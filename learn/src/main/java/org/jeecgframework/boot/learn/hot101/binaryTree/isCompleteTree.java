package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  15:56
 * @Description: 完全二叉树判断
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

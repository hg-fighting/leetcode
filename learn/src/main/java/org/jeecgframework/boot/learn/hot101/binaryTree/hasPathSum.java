package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  15:11
 * @Description: 路径总和
 */
public class hasPathSum {

    /**
     * 递归
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 迭代
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queue.offer(root);
        queueSum.offer(root.val);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int sum = queueSum.poll();
            if (node.left == null && node.right == null) {
                if (sum == targetSum) {
                    return true;
                }
            }
            if (node.left != null) {
                queue.offer(node.left);
                queueSum.offer(sum + node.left.val);
            }
            if (node.right != null) {
                queue.offer(node.right);
                queueSum.offer(sum + node.right.val);
            }
        }
        return false;
    }
}

package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  15:11
 * @Description: 路径总和
 * 描述
 * 给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 * 例如：
 * 给出如下的二叉树， sum=22，
 * 返回true，因为存在一条路径 5→4→11→2的节点值之和为 22
 * 数据范围：
 * 1≤n≤10000
 * −109≤节点值≤1000
 * −1000≤sum≤1000
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

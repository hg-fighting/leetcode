package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  15:08
 * @Description: 二叉树的最大深度
 * 描述
 * 求给定二叉树的最大深度，
 * 深度是指树的根节点到任一叶子节点路径上节点的数量。
 * 最大深度是所有叶子节点的深度的最大值。
 * （注：叶子节点是指没有子节点的节点。）
 * 数据范围：0≤n≤100000，树上每个节点的val满足 |val|≤100
 * 要求： 空间复杂度 O(1)O(1),时间复杂度 O(n)O(n)
 */
public class maxDepth {

    /**
     * 递归
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 迭代
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

}

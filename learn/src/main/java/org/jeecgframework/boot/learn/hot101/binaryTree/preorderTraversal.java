package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  14:28
 * @Description: 二叉树前序遍历
 * 描述
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * 数据范围：二叉树的节点数满足 0≤n≤1000 ，二叉树节点的值满足 1≤val≤100 ，树的各节点的值各不相同
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class preorderTraversal {

    /**
     * 前序遍历：中左右
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }

    /**
     * 递归
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorderTraversal2(root, res);
        return res;
    }

    private void preorderTraversal2(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorderTraversal2(root.left, res);
        preorderTraversal2(root.right, res);
    }
}

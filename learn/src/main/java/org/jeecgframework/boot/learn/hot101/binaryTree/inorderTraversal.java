package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  14:35
 * @Description: 二叉树中序遍历
 */
public class inorderTraversal {

    /**
     * 中序遍历：左中右
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

    /**
     * 递归
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversal2(root, res);
        return res;
    }

    private void inorderTraversal2(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorderTraversal2(root.left, res);
        res.add(root.val);
        inorderTraversal2(root.right, res);
    }
}

package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  14:39
 * @Description: 后序遍历
 */
public class postorderTraversal {

    /**
     * 后序遍历-左-右-中
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        //添加遍历结果的数组
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode pre = null;
        while (root != null || !s.isEmpty()) {
            //每次先找到最左边的节点
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            //弹出栈顶
            TreeNode node = s.pop();
            //如果该元素的右边没有或是已经访问过
            if (node.right == null || node.right == pre) {
                //访问中间的节点
                res.add(node.val);
                //且记录为访问过了
                pre = node;
            } else {
                //该节点入栈
                s.push(node);
                //先访问右边
                root = node.right;
            }
        }
        return res;
    }

    /**
     * 递归
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderTraversal2(root, res);
        return res;
    }

    private void postorderTraversal2(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorderTraversal2(root.left, res);
        postorderTraversal2(root.right, res);
        res.add(root.val);
    }
}

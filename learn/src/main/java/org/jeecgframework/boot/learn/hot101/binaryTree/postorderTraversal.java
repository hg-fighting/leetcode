package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  14:39
 * @Description: 后序遍历
 * 描述
 * 给定一个二叉树，返回它的 后序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * 数据范围：二叉树的节点数满足 0≤n≤1000，二叉树每个节点的值满足 0≤val≤1000
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class postorderTraversal {

    /**
     * 后序遍历-迭代算法（左-右-中）
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // 存储遍历结果的列表
        List<Integer> res = new ArrayList<>();
        // 辅助栈，用于模拟递归过程
        Stack<TreeNode> s = new Stack<>();
        // 记录上一个访问的节点，用于判断右子树是否已访问
        TreeNode pre = null;

        // 循环条件：当前节点不为空或栈不为空
        while (root != null || !s.isEmpty()) {
            // 第一步：找到当前节点的最左后代，依次入栈
            while (root != null) {
                s.push(root);
                root = root.left;
            }

            // 第二步：弹出栈顶节点
            TreeNode node = s.pop();

            // 第三步：检查是否可以访问当前节点
            // 条件：右子树为空，或右子树已经被访问过
            if (node.right == null || node.right == pre) {
                // 访问中间节点（后序遍历的"中"）
                res.add(node.val);
                // 记录当前节点为已访问节点
                // 标记当前节点为已访问
                pre = node;
            } else {
                // 第四步：不能访问当前节点，重新压回栈中
                s.push(node);
                // 转向处理右子树（后序遍历的"右"）
                root = node.right;
            }
        }

        return res;
    }

    /**
     * 后序遍历-递归算法（作为对比）
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
        postorderTraversal2(root.left, res);  // 左
        postorderTraversal2(root.right, res); // 右
        res.add(root.val);                    // 中
    }
}

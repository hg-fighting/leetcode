package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  15:53
 * @Description: 验证二叉搜索树
 * 描述
 * 给定一个二叉树根节点，请你判断这棵树是不是二叉搜索树。
 * 二叉搜索树满足每个节点的左子树上的所有节点均小于当前节点且右子树上的所有节点均大于当前节点。
 * 数据范围：节点数满足 0≤n≤100000，节点上的值满足 ∣val∣≤100000
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class isValidBST {

    /**
     * 递归：每个节点的左子树所有节点的值都小于它，右子树所有节点的值都大于它
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}

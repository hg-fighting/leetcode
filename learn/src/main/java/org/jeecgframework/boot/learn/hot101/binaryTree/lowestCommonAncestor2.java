package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.ArrayList;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  16:07
 * @Description: 在二叉树中找到两个节点的最近公共祖先
 * 描述
 * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 * 数据范围：1≤n≤1000, 0<val≤1000
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 * 注：本题保证二叉树中每个节点的val值均不相同。
 */
public class lowestCommonAncestor2 {

    //记录是否找到到o的路径
    public boolean flag = false;

    //求得根节点到目标节点的路径
    public void dfs(TreeNode root, ArrayList<Integer> path, int o) {
        if (flag || root == null) {
            return;
        }
        path.add(root.val);
        //节点值都不同，可以直接用值比较
        if (root.val == o) {
            flag = true;
            return;
        }
        //dfs遍历查找
        dfs(root.left, path, o);
        dfs(root.right, path, o);
        //找到
        if (flag) {
            return;
        }
        //回溯
        path.remove(path.size() - 1);
    }

    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        ArrayList<Integer> path1 = new ArrayList<Integer>();
        ArrayList<Integer> path2 = new ArrayList<Integer>();
        //求根节点到两个节点的路径
        dfs(root, path1, o1);
        //重置flag，查找下一个
        flag = false;
        dfs(root, path2, o2);
        int res = 0;
        //比较两个路径，找到第一个不同的点
        for (int i = 0; i < path1.size() && i < path2.size(); i++) {
            int x = path1.get(i);
            int y = path2.get(i);
            if (x == y)
            //最后一个相同的节点就是最近公共祖先
            {
                res = x;
            } else {
                break;
            }
        }
        return res;
    }

    /**
     * 递归
     */
    public int lowestCommonAncestor2(TreeNode root, int o1, int o2) {
        //该子树没找到，返回-1
        if (root == null) {
            return -1;
        }
        //该节点是其中某一个节点
        if (root.val == o1 || root.val == o2) {
            return root.val;
        }
        //左子树寻找公共祖先
        int left = lowestCommonAncestor2(root.left, o1, o2);
        //右子树寻找公共祖先
        int right = lowestCommonAncestor2(root.right, o1, o2);
        //左子树为没找到，则在右子树中
        if (left == -1) {
            return right;
        }
        //右子树没找到，则在左子树中
        if (right == -1) {
            return left;
        }
        //否则是当前节点
        return root.val;
    }
}

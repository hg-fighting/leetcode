package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.ArrayList;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  16:01
 * @Description: 二叉树的最近公共祖先
 * 描述
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 1.对于该题的最近的公共祖先定义:对于有根树T的两个节点p、q，最近公共祖先LCA(T,p,q)表示一个节点x，满足x是p和q的祖先且x的深度尽可能大。在这里，一个节点也可以是它自己的祖先.
 * 2.二叉搜索树是若它的左子树不空，则左子树上所有节点的值均小于它的根节点的值； 若它的右子树不空，则右子树上所有节点的值均大于它的根节点的值
 * 3.所有节点的值都是唯一的。
 * 4.p、q 为不同节点且均存在于给定的二叉搜索树中。
 * 数据范围:
 * 3<=节点总数<=10000
 * 0<=节点值<=10000
 */
public class lowestCommonAncestor {

    /**
     * 最近公共祖先：给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     */
    public int lowestCommonAncestor(TreeNode root, int p, int q) {
        //空树找不到公共祖先
        if (root == null) {
            return -1;
        }
        //pq在该节点两边说明这就是最近公共祖先
        if ((p >= root.val && q <= root.val) || (p <= root.val && q >= root.val)) {
            return root.val;
        }
        //pq都在该节点的左边
        else if (p <= root.val && q <= root.val)
        //进入左子树
        {
            return lowestCommonAncestor(root.left, p, q);
        }
        //pq都在该节点的右边
        else
        //进入右子树
        {
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    //求得根节点到目标节点的路径
    public ArrayList<Integer> getPath(TreeNode root, int target) {
        ArrayList<Integer> path = new ArrayList<Integer>();
        TreeNode node = root;
        //节点值都不同，可以直接用值比较
        while (node.val != target) {
            path.add(node.val);
            //小的在左子树
            if (target < node.val) {
                node = node.left;
            }
            //大的在右子树
            else {
                node = node.right;
            }
        }
        path.add(node.val);
        return path;
    }

    public int lowestCommonAncestor2(TreeNode root, int p, int q) {
        //求根节点到两个节点的路径
        ArrayList<Integer> path_p = getPath(root, p);
        ArrayList<Integer> path_q = getPath(root, q);
        int res = 0;
        //比较两个路径，找到第一个不同的点
        for (int i = 0; i < path_p.size() && i < path_q.size(); i++) {
            int x = path_p.get(i);
            int y = path_q.get(i);
            //最后一个相同的节点就是最近公共祖先
            if (x == y) {
                res = path_p.get(i);
            } else {
                break;
            }
        }
        return res;
    }
}

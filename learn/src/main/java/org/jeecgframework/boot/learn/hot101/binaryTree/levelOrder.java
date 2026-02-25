package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  14:47
 * @Description: 层序遍历二叉树
 * 描述
 * 给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
 * 例如：
 * 给定的二叉树是{3,9,20,#,#,15,7},
 * 该二叉树层序遍历的结果是
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * 提示:
 * 0≤n≤1500
 * −1000≤node.val≤1000
 */
public class levelOrder {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tem = queue.poll();
                list.add(tem.val);
                if (tem.left != null) {
                    queue.offer(tem.left);
                }
                if (tem.right != null) {
                    queue.offer(tem.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }
}
package org.jeecgframework.boot.learn.hot101.binaryTree;

import org.jeecgframework.boot.learn.hot101.basic.TreeNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  16:10
 * @Description: 序列化二叉树
 * 描述
 * 请实现两个函数，分别用来序列化和反序列化二叉树，不对序列化之后的字符串进行约束，但要求能够根据序列化之后的字符串重新构造出一棵与原二叉树相同的树。
 */
public class Serialize {

    //序列的下标
    public int index = 0;

    //处理序列化的功能函数（递归）
    private void SerializeFunction(TreeNode root, StringBuilder str) {
        //如果节点为空，表示左子节点或右子节点为空，用#表示
        if (root == null) {
            str.append('#');
            return;
        }
        //根节点
        str.append(root.val).append('!');
        //左子树
        SerializeFunction(root.left, str);
        //右子树
        SerializeFunction(root.right, str);
    }

    public String Serialize(TreeNode root) {
        //处理空树
        if (root == null) {
            return "#";
        }
        StringBuilder res = new StringBuilder();
        SerializeFunction(root, res);
        //把str转换成char
        return res.toString();
    }

    //处理反序列化的功能函数（递归）
    private TreeNode DeserializeFunction(String str) {
        //到达叶节点时，构建完毕，返回继续构建父节点
        //空节点
        if (str.charAt(index) == '#') {
            index++;
            return null;
        }
        //数字转换
        int val = 0;
        //遇到分隔符或者结尾
        while (str.charAt(index) != '!' && index != str.length()) {
            val = val * 10 + ((str.charAt(index)) - '0');
            index++;
        }
        TreeNode root = new TreeNode(val);
        //序列到底了，构建完成
        if (index == str.length()) {
            return root;
        } else {
            index++;
        }
        //反序列化与序列化一致，都是前序
        root.left = DeserializeFunction(str);
        root.right = DeserializeFunction(str);
        return root;
    }

    public TreeNode Deserialize(String str) {
        //空序列对应空树
        if (str == "#") {
            return null;
        }
        TreeNode res = DeserializeFunction(str);
        return res;
    }
}

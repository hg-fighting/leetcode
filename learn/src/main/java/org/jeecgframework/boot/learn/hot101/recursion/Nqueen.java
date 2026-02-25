package org.jeecgframework.boot.learn.hot101.recursion;

import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:12
 * @Description: N皇后
 * 描述
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 数据范围：0 < n ≤ 9
 * 要求：空间复杂度 O(n^2) ，时间复杂度 O(n!)
 * 例如当输入4时，对应的返回为
 * <p>
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */
public class Nqueen {

    private int res;

    //判断皇后是否符合条件
    public boolean isValid(int[] pos, int row, int col) {
        //遍历所有已经记录的行
        for (int i = 0; i < row; i++) {
            //不能同行同列同一斜线
            if (row == i || col == pos[i] || Math.abs(row - i) == Math.abs(col - pos[i]))
                return false;
        }
        return true;
    }

    //递归查找皇后种类
    public void recursion(int n, int row, int[] pos) {
        //完成全部行都选择了位置
        if (row == n) {
            res++;
            return;
        }
        //遍历所有列
        for (int i = 0; i < n; i++) {
            //检查该位置是否符合条件
            if (isValid(pos, row, i)) {
                //加入位置
                pos[row] = i;
                //递归继续查找
                recursion(n, row + 1, pos);
            }
        }
    }

    public int Nqueen(int n) {
        res = 0;
        //下标为行号，元素为列号，记录皇后位置
        int[] pos = new int[n];
        Arrays.fill(pos, 0);
        //递归
        recursion(n, 0, pos);
        return res;
    }
}

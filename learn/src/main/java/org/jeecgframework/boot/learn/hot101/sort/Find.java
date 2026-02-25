package org.jeecgframework.boot.learn.hot101.sort;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  13:29
 * @Description: 二维数组中的查找
 * 描述
 * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 * 给定 target = 3，返回 false。
 * 数据范围：矩阵的长宽满足 0≤n,m≤500， 矩阵中的值满足 0≤val≤10^9
 * 进阶：空间复杂度 O(1)，时间复杂度 O(n+m)
 * ]
 */
public class Find {

    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int row = 0;
        int col = array[0].length - 1;
        while (row < array.length && col >= 0) {
            if (array[row][col] == target) {
                return true;
            } else if (array[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }
}

package org.jeecgframework.boot.learn.hot101.mock;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:10
 * @Description: 顺时针旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间能否做到？
 */
public class rotateMatrix {

    public int[][] rotateMatrix(int[][] mat, int n) {
        int length = mat.length;
        //矩阵转置
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < i; ++j) {
                //交换上三角与下三角对应的元素
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
        //每行翻转
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length / 2; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[i][length - j - 1];
                mat[i][length - j - 1] = temp;
            }
        }
        return mat;
    }

}

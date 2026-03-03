package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/3
 * @Description: 简单的自动曝光
 * 题目描述
 * 一个图像有 n 个像素点，存储在一个长度为 n 的数组 img 里，每个像素点的取值范围[0,255]
 * 的正整数。
 * 请你给图像每个像素点值加上一个整数 k（可以是负数），得到新图 newImg，使得新图 ne
 * wImg 的所有像素平均值最接近中位值 128。
 * 请输出这个整数 k。 输入描述
 * n 个整数，中间用空格分开
 * 例如：
 * 0 0 0 0
 * 4 个数值，中间用空格分开
 * 输出描述
 * 一个整数 k
 * 示例 2：kOpt 为小数（需比较候选值）
 * 输入：100 100 100
 * 计算过程：
 * n=3，sum=300；
 * target=128×3=384；
 * kOpt=(384-300)/3=28.0 → 直接取 k=28；
 * 验证：加 28 后数组为 [128,128,128]，平均值 = 128，完全匹配；
 * 输出：28。
 */
public class AutoExposure {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入的像素数组
        String[] input = scanner.nextLine().split(" ");
        int n = input.length;
        long sum = 0; // 用long避免sum溢出（n≤1e5时255*1e5=2.55e7，int也够，但long更安全）

        // 计算像素总和
        for (String s : input) {
            sum += Integer.parseInt(s);
        }

        // 计算理论最优k（浮点数）
        double target = 128.0 * n;
        double kOpt = (target - sum) / n;

        // 取整数候选值：k1为向下取整，k2为向上取整
        int k1 = (int) Math.floor(kOpt);
        int k2 = k1 + 1;

        // 计算两个候选值对应的平均值与128的差值绝对值
        double avg1 = (sum + (long) n * k1) / (double) n;
        double diff1 = Math.abs(avg1 - 128);

        double avg2 = (sum + (long) n * k2) / (double) n;
        double diff2 = Math.abs(avg2 - 128);

        // 选择差值更小的k；若相等，取较小的k（因kOpt向下取整更接近）
        int k;
        if (diff1 < diff2) {
            k = k1;
        } else if (diff2 < diff1) {
            k = k2;
        } else {
            k = k1; // 差值相等时取较小的k
        }

        System.out.println(k);
        scanner.close();
    }
}
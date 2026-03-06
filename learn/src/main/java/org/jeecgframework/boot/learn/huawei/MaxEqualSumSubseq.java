package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  9:17
 * @Description: 最多等和不相交连续子序列
 * 题目描述
 * 给定一个数组，我们称其中连续的元素为连续子序列，称这些元素的和为连续子序列的和
 * 。数组中可能存在几组连续子序列，组内的连续子序列互不相交且有相同的和。求一组连
 * 续子序列，组内子序列的数目最多。输出这个数目。 输入描述
 * 第一行输入为数组长度 N，1 <= N <= 10^3。
 * 第二行为 N 个用空格分开的整数 Ci，-10^5 <= Ci <= 10^5。 输出描述
 * 第一行是一个整数 M，表示满足要求的最多的组内子序列的数目。
 */

public class MaxEqualSumSubseq {

    // 定义子序列实体：和、起始索引、结束索引
    static class Subseq {

        int sum;

        int start;

        int end;

        public Subseq(int sum, int start, int end) {
            this.sum = sum;
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        // 步骤1：枚举所有连续子序列，按和分组
        Map<Integer, List<Subseq>> sumToSubseqs = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int currentSum = 0;
            for (int j = i; j < N; j++) {
                currentSum += arr[j];
                Subseq subseq = new Subseq(currentSum, i, j);
                // 按和分组存储
                sumToSubseqs.computeIfAbsent(currentSum, k -> new ArrayList<>()).add(subseq);
            }
        }

        int maxCount = 0; // 最终结果：最多的子序列数目

        // 步骤2：遍历每个和的分组，计算该组最多不相交子序列数目
        for (List<Subseq> subseqs : sumToSubseqs.values()) {
            // 按结束索引升序排序（贪心关键：优先选结束早的）
            subseqs.sort(Comparator.comparingInt(s -> s.end));

            int count = 0;
            int lastEnd = -1; // 上一个选中子序列的结束索引
            for (Subseq s : subseqs) {
                // 当前子序列起始索引 > 上一个结束索引 → 不相交，可选
                if (s.start > lastEnd) {
                    count++;
                    lastEnd = s.end;
                }
            }

            // 更新最大数目
            if (count > maxCount) {
                maxCount = count;
            }
        }

        // 输出结果（注意：最少是1，因为单个子序列也算一组）
        System.out.println(maxCount);
    }
}
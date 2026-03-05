package org.jeecgframework.boot.learn.huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/5  10:23
 * @Description: MVP 争夺战
 * 题目描述
 * 在星球争霸篮球赛对抗赛中，强大的宇宙战队，希望每个人都能拿到 MVP。
 * MVP 的条件是，单场最高分得分获得者，可以并列，所以宇宙战队决定在比赛中，尽可能
 * 让更多的队员上场，且让所有有得分的队员得分都相同。
 * 然而比赛过程中的每一分钟的得分都只能由某一个人包揽。 输入描述
 * 输入第一行为一个数字 t，表示有得分的分钟数（ 1 <= t <= 50），第二行为 t 个数字，代表每一分钟的得分 p（1 <= p <= 50）
 * 输出描述
 * 输出有得分的队员都是 MVP 时最少的 MVP 得分。
 */
public class MVPPartition {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入
        int t = scanner.nextInt();
        int[] scores = new int[t];
        int total = 0;
        for (int i = 0; i < t; i++) {
            scores[i] = scanner.nextInt();
            total += scores[i];
        }
        scanner.close();

        // 排序（降序），优化回溯效率
        Arrays.sort(scores);
        reverse(scores);

        // 遍历可能的队员数（从最大到最小）
        int minMvpScore = total; // 初始值为总和（最少1个队员）
        // 队员数最大可能值：Math.min(total, t)（每人至少1分，且至少1分钟得分）
        for (int k = Math.min(total, t); k >= 1; k--) {
            // 总和必须能被队员数整除，否则跳过
            if (total % k != 0) {
                continue;
            }
            int target = total / k; // 候选MVP得分
            // 验证是否能分成k组，每组和为target
            boolean[] used = new boolean[t];
            if (canPartition(scores, used, 0, k, 0, target)) {
                minMvpScore = target;
                break; // 找到最小的MVP得分，直接退出
            }
        }

        // 输出结果
        System.out.println(minMvpScore);
    }

    // 回溯验证是否能分成k组，每组和为target
    private static boolean canPartition(int[] scores, boolean[] used, int start, int remainGroups, int currentSum, int target) {
        // 所有组都分完，返回true
        if (remainGroups == 0) {
            return true;
        }
        // 当前组凑够target，开始分下一组
        if (currentSum == target) {
            return canPartition(scores, used, 0, remainGroups - 1, 0, target);
        }
        // 遍历未使用的得分，尝试加入当前组
        for (int i = start; i < scores.length; i++) {
            // 剪枝：已使用 / 加入后超过target / 重复值跳过
            if (used[i] || currentSum + scores[i] > target) {
                continue;
            }
            // 跳过重复得分（优化效率）
            if (i > start && scores[i] == scores[i - 1] && !used[i - 1]) {
                continue;
            }
            // 标记为已使用
            used[i] = true;
            // 递归尝试
            if (canPartition(scores, used, i + 1, remainGroups, currentSum + scores[i], target)) {
                return true;
            }
            // 回溯：取消标记
            used[i] = false;
            // 剪枝：当前组第一个得分加入后无法凑成，直接返回
            if (currentSum == 0) {
                return false;
            }
        }
        return false;
    }

    // 数组反转（实现降序）
    private static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}

package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:46
 * @Description: 日志采集系统 - 计算首次上报能获得的最多积分数
 * 题目描述
 * 日志采集是运维系统的的核心组件。日志是按行生成，每行记做一条，由采集系统分批上
 * 报。
 * 如果上报太频繁，会对服务端造成压力；如果上报太晚，会降低用户的体验；如果一次上
 * 报的条数太多，会导致超时失败。
 * 为此，项目组设计了如下的上报策略：
 * 1、每成功上报一条日志，奖励 1 分
 * 2、每条日志每延迟上报 1 秒，扣 1 分
 * 3、积累日志达到 100 条，必须立即上报
 * 给出日志序列，根据该规则，计算首次上报能获得的最多积分数
 * 输入描述
 * 按时序产生的日志条数 T1,T2...Tn, 其中 1<=n<=1000, 0<=Ti<=100
 * 输出描述
 * 首次上报最多能获得的积分数
 */
public class LogCollection {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入的日志序列（以空格分隔）
        String[] input = scanner.nextLine().split(" ");
        int[] logs = new int[input.length];
        // 将字符串数组转换为整数数组，存储每一秒产生的日志条数
        for (int i = 0; i < input.length; i++) {
            logs[i] = Integer.parseInt(input[i]);
        }

        // 初始化最大积分为极小值，确保能被有效更新
        int maxScore = Integer.MIN_VALUE;
        // 累计日志总条数（用于判断是否达到100条强制上报条件）
        int totalLogs = 0;
        // 累计所有日志的延迟扣分总和（第k秒产生的每条日志扣k分）
        int totalDelay = 0;
        // 当前时间秒数（从1开始递增，对应日志产生的时序）
        int time = 0;

        // 遍历每一秒的日志产生情况
        for (int log : logs) {
            // 时间递增1秒
            time++;
            // 累加当前秒产生的日志条数
            totalLogs += log;
            // 累加当前秒日志的延迟扣分（时间*条数）
            totalDelay += time * log;

            // 计算当前时刻上报的日志条数（最多100条）
            int reportCount = Math.min(totalLogs, 100);
            // 计算当前时刻上报的积分：奖励分（上报条数） - 延迟扣分（总延迟）
            int currentScore = reportCount - totalDelay;

            // 更新首次上报的最大积分
            if (currentScore > maxScore) {
                maxScore = currentScore;
            }

            // 累计日志达到100条，必须立即上报，终止循环
            if (totalLogs >= 100) {
                break;
            }
        }

        // 输出首次上报能获得的最大积分
        System.out.println(maxScore);
        scanner.close();
    }
}
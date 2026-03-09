package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/9  8:45
 * @Description: 静态代码扫描服务
 * 题目描述
 * 静态扫描快速快速识别源代码的缺陷，静态扫描的结果以扫描报告作为输出：
 * 1、文件扫描的成本和文件大小相关，如果文件大小为 N，则扫描成本为 N 个金币
 * 2、扫描报告的缓存成本和文件大小无关，每缓存一个报告需要 M 个金币
 * 3、扫描报告缓存后，后继再碰到该文件则不需要扫描成本，直接获取缓存结果
 * 给出源代码文件标识序列和文件大小序列，求解采用合理的缓存策略，最少需要的金币数
 * 。输入描述
 * 第一行为缓存一个报告金币数 M，1<=M<=100
 * 第二行为文件标识序列：F1,F2,F3...Fn, 其中 1<=N<=10000， 1<=Fi<=1000
 * 第三行为文件大小序列：S1,S2,S3...Sn, 其中 1<=N<=10000， 1<=Si<=10
 * 输出描述
 * 采用合理的缓存策略，需要的最少金币数a
 */
public class StaticScanCost {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 读取输入
        int M = Integer.parseInt(scanner.nextLine()); // 缓存一个报告的成本
        String[] fileIds = scanner.nextLine().split(","); // 文件标识序列
        String[] fileSizes = scanner.nextLine().split(","); // 文件大小序列

        // 2. 预处理：统计每个文件出现的位置和大小（确保标识和大小序列长度一致）
        int n = fileIds.length;
        if (n != fileSizes.length) {
            System.out.println(0); // 输入异常，按题目约束实际不会出现
            return;
        }

        // key: 文件标识, value: 该文件所有出现的大小列表（按顺序）
        Map<String, List<Integer>> fileSizeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String fid = fileIds[i];
            int size = Integer.parseInt(fileSizes[i]);
            fileSizeMap.computeIfAbsent(fid, k -> new ArrayList<>()).add(size);
        }

        // 3. 计算最小总成本
        int totalCost = 0;
        for (Map.Entry<String, List<Integer>> entry : fileSizeMap.entrySet()) {
            List<Integer> sizes = entry.getValue();
            int firstScanCost = sizes.get(0); // 首次必须扫描
            totalCost += firstScanCost;

            // 计算该文件后续所有出现的扫描成本总和
            int subsequentScanTotal = 0;
            for (int i = 1; i < sizes.size(); i++) {
                subsequentScanTotal += sizes.get(i);
            }

            // 决策：缓存成本 M ≤ 后续扫描总和 → 选缓存（支付M），否则选每次扫描（支付后续总和）
            totalCost += Math.min(M, subsequentScanTotal);
        }

        // 4. 输出结果
        System.out.println(totalCost);
    }
}

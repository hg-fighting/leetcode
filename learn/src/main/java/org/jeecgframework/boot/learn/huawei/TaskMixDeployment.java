package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  9:54
 * @Description: 任务混部
 * 题目描述
 * 公司创新实验室正在研究如何最小化资源成本，最大化资源利用率，请你设计算法帮他们
 * 解决一个任务混部问题：有 taskNum 项任务，每个任务有开始时间（startTime
 * ），结束时间（endTime），并行度(parallelism)三个属性，并行度是指这个任务运行时将
 * 会占用的服务器数量，一个服务器在每个时刻可以被任意任务使用但最多被一个任务占用
 * ，任务运行完会立即释放（结束时刻不占用）。任务混部问题是指给定一批任务，让这批
 * 任务由同一批服务器承载运行，请你计算完成这批任务混部最少需要多少服务器，从而最
 * 大化控制资源成本。 输入描述
 * 第一行输入为 taskNum，表示有 taskNum 项任务
 * 接下来 taskNum 行，每行三个整数，表示每个任务的开始时间（startTime
 * ），结束时间（endTime），并行度(parallelism)
 * 输出描述
 * 一个整数，表示最少需要的服务器数量
 * 测试用例
 * 3
 * 1 4 2
 * 2 5 3
 * 3 6 1
 */
public class TaskMixDeployment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取任务数量
        int taskNum = scanner.nextInt();
        // 存储事件：每个事件是一个数组，[时间, 并行度变化量]
        List<int[]> events = new ArrayList<>();

        for (int i = 0; i < taskNum; i++) {
            int startTime = scanner.nextInt();
            int endTime = scanner.nextInt();
            int parallelism = scanner.nextInt();
            // 任务开始：增加并行度
            events.add(new int[]{startTime, parallelism});
            // 任务结束：减少并行度
            events.add(new int[]{endTime, -parallelism});
        }

        // 排序事件：1. 按时间升序；2. 时间相同时，减少事件（负数）先处理
        events.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]); // 负数在前：结束先处理
        });

        int currentServers = 0;
        int maxServers = 0;
        // 遍历所有事件，计算最大并行服务器数
        for (int[] event : events) {
            currentServers += event[1];
            // 更新最大服务器数
            if (currentServers > maxServers) {
                maxServers = currentServers;
            }
        }

        System.out.println(maxServers);
        scanner.close();
    }
}

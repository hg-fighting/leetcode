package org.jeecgframework.boot.learn.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @Author: hao gang
 * @Date: 2026/3/5  10:58
 * @Description: 任务总执行时长
 * 题目描述
 * 任务编排服务负责对任务进行组合调度。参与编排的任务有两种类型，其中一种执行时长
 * 为 taskA，另一种执行时长为 taskB。任务一旦开始执行不能被打断，且任务可连续执行。
 * 服务每次可以编排 num 个任务。请编写一个方法，生成每次编排后的任务所有可能的总执
 * 行时长。 输入描述
 * 第 1 行输入分别为第 1 种任务执行时长 taskA，第 2 种任务执行时长 taskB，这次要编排的任务
 * 个数 num，以逗号分隔。 输出描述
 * 数组形式返回所有总执行时时长，需要按从小到大排列。
 */
public class TaskDuration {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入并分割（按逗号分隔）
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        // 解析参数（确保转为整数）
        int taskA = Integer.parseInt(parts[0].trim());
        int taskB = Integer.parseInt(parts[1].trim());
        int num = Integer.parseInt(parts[2].trim());
        scanner.close();

        // 存储所有可能的总时长（TreeSet自动去重+排序）
        TreeSet<Integer> durations = new TreeSet<>();

        // 遍历所有可能的taskA数量（0~num）
        for (int x = 0; x <= num; x++) {
            int countB = num - x; // taskB的数量
            int total = x * taskA + countB * taskB;
            durations.add(total);
        }

        // 转换为数组并按格式输出
        List<Integer> resultList = new ArrayList<>(durations);
        // 按题目要求的数组形式输出（如 [10,20,30]）
        System.out.print("[");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.print(resultList.get(i));
            if (i != resultList.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }
}

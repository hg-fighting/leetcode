package org.jeecgframework.boot.learn.huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  11:16
 * @Description: 核酸检测人员安排
 * 题目描述
 * 在系统、网络均正常的情况下组织核酸采样员和志愿者对人群进行核酸检测筛查。每名采
 * 样员的效率不同，采样效率为 N 人/小时。由于外界变化，采样员的效率会以 M 人/小时为
 * 粒度发生变化，M 为采样效率浮动粒度，M=N10%，输入保证 N10%的结果为整数。采样员
 * 效率浮动规则：采样员需要一名志愿者协助组织才能发挥正常效率，在此基础上，每增加
 * 一名志愿者，效率提升 1M，最多提升 3M；如果没有志愿者协助组织，效率下降 2M。
 * 怎么安排速度最快？求总最快检测效率（总检查效率为各采样人员效率值相加）。 输入描述
 * 第一行：第一个值，采样员人数，取值范围[1,100]；第二个值，志愿者人数，取值范围[1, 500]；
 * 第二行：各采样员基准效率值(单位人/小时)，取值范围[60,600]，保证序列中每项值计算 10%为整数
 * 输出描述
 * 第一行：总最快检测效率(单位人/小时)
 * 示例1
 * 2 3
 * 100 200
 * 采样员 1：N=100，M=10 → 0 志愿者效率 = 80，增量列表 [20,10,10,10]；
 * 采样员 2：N=200，M=20 → 0 志愿者效率 = 160，增量列表 [40,20,20,20]；
 * 总基础效率 = 80+160=240；
 * 所有增量合并排序：[40,20,20,20,20,10,10,10]；
 * 分配 3 名志愿者，取前 3 个增量：40+20+20=80；
 * 总效率 = 240+80=320；
 */
public class NucleicAcidArrangement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取采样员人数、志愿者人数
        int samplerNum = scanner.nextInt();
        int volunteerNum = scanner.nextInt();
        scanner.nextLine(); // 处理换行

        // 读取采样员基准效率数组
        int[] baseEfficiency = new int[samplerNum];
        for (int i = 0; i < samplerNum; i++) {
            baseEfficiency[i] = scanner.nextInt();
        }

        // 步骤1：计算所有可能的志愿者增量，按从大到小排序
        List<Integer> increments = new ArrayList<>();
        int totalBase = 0; // 所有采样员0志愿者时的总效率

        for (int n : baseEfficiency) {
            int m = n / 10; // M = N×10%（输入保证为整数）
            totalBase += (n - 2 * m); // 0志愿者时的效率

            // 第1名志愿者：增量2M（优先级最高）
            increments.add(2 * m);
            // 第2名志愿者：增量M
            increments.add(m);
            // 第3名志愿者：增量M
            increments.add(m);
            // 第4名及以上：增量M（但最多到3M上限，仅需加1次）
            increments.add(m);
        }

        // 步骤2：增量从大到小排序
        increments.sort(Collections.reverseOrder());

        // 步骤3：分配志愿者，累加增量（最多分配volunteerNum个）
        int totalIncrement = 0;
        int assignCount = Math.min(volunteerNum, increments.size());
        for (int i = 0; i < assignCount; i++) {
            totalIncrement += increments.get(i);
        }

        // 总最快效率 = 0志愿者总效率 + 分配志愿者的增量
        int maxTotal = totalBase + totalIncrement;
        System.out.println(maxTotal);

        scanner.close();
    }
}

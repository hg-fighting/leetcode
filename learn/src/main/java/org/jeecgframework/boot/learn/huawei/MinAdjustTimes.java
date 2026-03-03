package org.jeecgframework.boot.learn.huawei;

import java.io.*;
import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  13:20
 * @Description: 最小的调整次数
 * 题目描述
 * 有一个特异性的双端队列，该队列可以从头部或尾部添加数据，但是只能从头部移出数据。
 * 小 A 依次执行 2n 个指令往队列中添加数据和移出数据。其中 n 个指令是添加数据（
 * 可能从头部添加、也可能从尾部添加
 * ），依次添加 1 到 n；n 个指令是移出数据。现在要求移除数据的顺序为 1 到 n。为了满足最
 * 后输出的要求，小 A 可以在任何时候调整队列中数据的顺序。
 * 请问 小 A 最少需要调整几次才能够满足移除数据的顺序正好是 1 到 n；
 * 输入描述
 * 第一行一个整数 n，表示数据范围。
 * 接下来有 2n 行，其中有 n 行为添加数据：指令“ head add x”表示从头部添加数据 x，“ tail
 * add x”表示从尾部添加数据 x；另外 n
 * 行为移出数据指令，指令为 “remove” 的形式，表示移出 1 个数据；1 ≤ n ≤ 3 * 10^5。
 * 所有的数据均合法。 输出描述
 * 一个整数，表示 小 A 要调整的最小次数。
 * 输入
 * 2
 * head add 1
 * tail add 2
 * remove
 * remove
 * 执行过程：
 * 添加 1（头部）→ 队列：[1]；
 * 添加 2（尾部）→ 队列：[1,2]；
 * 移除：头部是 1（currentNeed=1），直接移除→队列 [2]，currentNeed=2；
 * 移除：头部是 2（currentNeed=2），直接移除→队列空；
 * 输出：0。
 */
public class MinAdjustTimes {

    public static void main(String[] args) throws IOException {
        // 用BufferedReader处理大规模输入（避免Scanner超时）
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 存储每个数的添加指令（1~n）
        // 下标从1开始
        String[] addOps = new String[n + 1];
        // 模拟队列
        Deque<Integer> deque = new ArrayDeque<>();
        // 当前需要移除的数
        int currentNeed = 1;
        // 调整次数
        int adjustCount = 0;

        // 处理2n条指令
        for (int i = 0; i < 2 * n; i++) {
            String line = br.readLine().trim();
            if (line.startsWith("head add") || line.startsWith("tail add")) {
                // 解析添加指令
                String[] parts = line.split(" ");
                int x = Integer.parseInt(parts[2]);
                // 记录是head还是tail添加
                addOps[x] = parts[0];
                // 加入队列
                if ("head".equals(parts[0])) {
                    deque.addFirst(x);
                } else {
                    deque.addLast(x);
                }
            } else if ("remove".equals(line)) {
                // 移除指令
                if (deque.isEmpty()) {
                    continue;
                }
                // 检查队列头部是否是当前需要移除的数
                if (deque.peekFirst() == currentNeed) {
                    // 无需调整，直接移除
                    deque.pollFirst();
                    currentNeed++;
                } else {
                    // 必须调整一次
                    adjustCount++;
                    // 调整队列：收集所有≥currentNeed的数，按升序排列后重新入队
                    List<Integer> list = new ArrayList<>();
                    while (!deque.isEmpty()) {
                        int num = deque.pollFirst();
                        if (num >= currentNeed) {
                            list.add(num);
                        }
                    }
                    // 升序排序（调整操作）
                    Collections.sort(list);
                    // 重新入队
                    for (int num : list) {
                        deque.addLast(num);
                    }
                    // 移除当前需要的数
                    deque.pollFirst();
                    currentNeed++;
                }
            }
        }

        System.out.println(adjustCount);
        br.close();
    }
}

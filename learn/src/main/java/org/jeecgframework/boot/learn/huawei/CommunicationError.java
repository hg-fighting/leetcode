package org.jeecgframework.boot.learn.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/9  9:05
 * @Description: 通信误码
 * 题目描述
 * 信号传播过程中会出现一些误码，不同的数字表示不同的误码 ID，取值范围为 1~65535，
 * 用一个数组记录误码出现的情况。每个误码出现的次数代表误码频度，请找出记录中包含
 * 频度最高误码的最小子数组长度。 输入描述
 * 误码总数目：取值范围为 0~255，取值为 0 表示没有误码的情况。
 * 误码出现频率数组：误码 ID 范围为 1~65535，数组长度为 1~1000。 输出描述
 * 包含频率最高的误码最小子数组长度
 */
public class CommunicationError {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 读取输入
        int errorCount = Integer.parseInt(scanner.nextLine()); // 误码总数目（实际可忽略，以数组长度为准）
        if (errorCount == 0) { // 无误码情况
            System.out.println(0);
            return;
        }
        String[] errorStr = scanner.nextLine().split(",");
        int[] errors = new int[errorStr.length];
        for (int i = 0; i < errorStr.length; i++) {
            errors[i] = Integer.parseInt(errorStr[i]);
        }

        // 2. 统计每个误码的频度、首次出现位置、末次出现位置
        // key: 误码ID, value: [频度, 首次出现索引, 末次出现索引]
        Map<Integer, int[]> errorMap = new HashMap<>();
        for (int i = 0; i < errors.length; i++) {
            int id = errors[i];
            if (!errorMap.containsKey(id)) {
                // 初始化：频度1，首次/末次索引都是当前i
                errorMap.put(id, new int[]{1, i, i});
            } else {
                int[] info = errorMap.get(id);
                info[0]++; // 频度+1
                info[2] = i; // 更新末次出现索引
            }
        }

        // 3. 找到最高频度值
        int maxFreq = 0;
        for (int[] info : errorMap.values()) {
            maxFreq = Math.max(maxFreq, info[0]);
        }

        // 4. 对所有最高频度的误码，计算其最小子数组长度，取最小值
        int minLength = Integer.MAX_VALUE;
        for (Map.Entry<Integer, int[]> entry : errorMap.entrySet()) {
            int[] info = entry.getValue();
            if (info[0] == maxFreq) {
                // 子数组长度 = 末次索引 - 首次索引 + 1
                int length = info[2] - info[1] + 1;
                if (length < minLength) {
                    minLength = length;
                }
            }
        }

        // 5. 输出结果
        System.out.println(minLength);
    }
}

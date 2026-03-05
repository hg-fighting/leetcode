package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/5
 * @Description: 异常的打卡记录
 * 题目描述
 * 考勤记录是分析和考核职工工作时间利用情况的原始依据，也是计算职工工资的原始依据
 * ，为了正确地计算职工工资和监督工资基金使用情况，公司决定对员工的手机打卡记录进
 * 行异常排查。
 * 如果出现以下两种情况，则认为打卡异常：
 * 1、实际设备号与注册设备号不一样
 * 2、 或者， 同一个员工的两个打卡记录的时间小于 60 分钟并且打卡距离超过 5km。
 * 给定打卡记录的字符串数组 clockRecords（每个打卡记录组成为：工号;时间（分钟）;打卡
 * 距离（km）;实际设备号;注册设备号），返回其中异常的打卡记录（按输入顺序输出）。
 * 输入描述
 * 第一行输入为 N，表示打卡记录数；
 * 之后的 N 行为打卡记录，每一行为一条打卡记录。
 * 例如：
 * 2
 * 100000,10,1,ABCD,ABCD
 * 100000,50,10,ABCD,ABCD
 * 输出描述
 * 输出为异常的打卡记录，例如：100000,10,1,ABCD,ABCD;100000,50,10,ABCD,ABCD
 */
public class AbnormalClockRecord {

    // 打卡记录结构化实体
    static class Record {

        String empId;       // 工号

        int time;           // 时间（分钟）

        int distance;       // 打卡距离（km）

        String actualDev;   // 实际设备号

        String regDev;      // 注册设备号

        int index;          // 输入顺序索引

        boolean isAbnormal; // 是否异常

        Record(String line, int index) {
            this.index = index;
            String[] parts = line.split(",");
            this.empId = parts[0];
            this.time = Integer.parseInt(parts[1]);
            this.distance = Integer.parseInt(parts[2]);
            this.actualDev = parts[3];
            this.regDev = parts[4];
            // 先判断规则1：设备号不一致 → 直接异常
            this.isAbnormal = !this.actualDev.equals(this.regDev);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        // 存储所有记录（按输入顺序）
        List<Record> allRecords = new ArrayList<>();
        // 按工号分组存储记录
        Map<String, List<Record>> empRecordMap = new HashMap<>();

        // 步骤1：解析所有记录，初始化规则1的异常标记
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            Record record = new Record(line, i);
            allRecords.add(record);
            // 按工号分组
            empRecordMap.computeIfAbsent(record.empId, k -> new ArrayList<>()).add(record);
        }
        scanner.close();

        // 步骤2：处理规则2：同员工两条记录时间差<60且距离差>5 → 标记为异常
        for (List<Record> empRecords : empRecordMap.values()) {
            int size = empRecords.size();
            // 两两对比同员工的记录
            for (int i = 0; i < size; i++) {
                Record r1 = empRecords.get(i);
                for (int j = i + 1; j < size; j++) {
                    Record r2 = empRecords.get(j);
                    // 计算时间差和距离差
                    int timeDiff = Math.abs(r1.time - r2.time);
                    int distanceDiff = Math.abs(r1.distance - r2.distance);
                    // 满足规则2 → 两条都标记为异常
                    if (timeDiff < 60 && distanceDiff > 5) {
                        r1.isAbnormal = true;
                        r2.isAbnormal = true;
                    }
                }
            }
        }

        // 步骤3：按输入顺序收集异常记录，拼接输出
        List<String> abnormalLines = new ArrayList<>();
        for (Record record : allRecords) {
            if (record.isAbnormal) {
                // 还原原始字符串（按输入顺序）
                abnormalLines.add(String.format("%s,%d,%d,%s,%s",
                        record.empId, record.time, record.distance, record.actualDev, record.regDev));
            }
        }

        // 输出：用分号分隔
        System.out.println(String.join(";", abnormalLines));
    }
}
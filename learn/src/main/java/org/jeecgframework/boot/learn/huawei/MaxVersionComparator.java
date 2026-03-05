package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/5  11:19
 * @Description: 获取最大软件版本号
 * 题目描述
 * Maven 版本号定义，<主版本>.<次版本>.<增量版本>-<里程碑版本>，举例 3.1.4-beta
 * 其中，主版本和次版本都是必须的，主版本，次版本，增量版本由多位数字组成，可能包
 * 含前导零，里程碑版本由字符串组成。
 * <主版本>.<次版本>.<增量版本>：基于数字比较
 * 里程碑版本：基于字符串比较,采用字典序
 * 比较版本号时，按从左到右的顺序依次比较。基于数字比较，
 * 只需比较忽略任何前导零后的整数值 。
 * 输入 2 个版本号，输出最大版本号
 * 输入描述
 * 输入 2 个版本号，换行分割，每个版本的最大长度小于 50
 * 输出描述
 * 版本号相同时输出第一个输入版本号
 */
public class MaxVersionComparator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取两个版本号（注意处理换行和空格）
        String v1 = scanner.nextLine().trim();
        String v2 = scanner.nextLine().trim();
        scanner.close();

        // 比较版本号，返回最大的（相同返回v1）
        String maxVersion = compareVersions(v1, v2) >= 0 ? v1 : v2;
        System.out.println(maxVersion);
    }

    /**
     * 比较两个版本号
     *
     * @return 1: v1>v2; 0: v1=v2; -1: v1<v2
     */
    private static int compareVersions(String v1, String v2) {
        // 拆分版本号：数字段 + 里程碑段
        VersionInfo info1 = parseVersion(v1);
        VersionInfo info2 = parseVersion(v2);

        // 1. 比较主版本
        int cmpMain = Integer.compare(info1.main, info2.main);
        if (cmpMain != 0) {
            return cmpMain;
        }

        // 2. 比较次版本
        int cmpSub = Integer.compare(info1.sub, info2.sub);
        if (cmpSub != 0) {
            return cmpSub;
        }

        // 3. 比较增量版本（缺省为0）
        int cmpIncrement = Integer.compare(info1.increment, info2.increment);
        if (cmpIncrement != 0) {
            return cmpIncrement;
        }

        // 4. 比较里程碑版本
        return compareMilestone(info1.milestone, info2.milestone);
    }

    /**
     * 解析版本号为结构化信息
     */
    private static VersionInfo parseVersion(String version) {
        VersionInfo info = new VersionInfo();
        // 分割数字段和里程碑段
        String[] parts = version.split("-", 2);
        String numPart = parts[0];
        info.milestone = parts.length > 1 ? parts[1] : "";

        // 分割数字段为 主.次.增量
        String[] numSegments = numPart.split("\\.");
        // 主版本（必须）
        info.main = parseNum(numSegments[0]);
        // 次版本（必须）
        info.sub = numSegments.length >= 2 ? parseNum(numSegments[1]) : 0;
        // 增量版本（可选，缺省为0）
        info.increment = numSegments.length >= 3 ? parseNum(numSegments[2]) : 0;

        return info;
    }

    /**
     * 解析数字字符串（忽略前导零），如 "003" → 3, "0" → 0
     */
    private static int parseNum(String s) {
        // 去除前导零（保留最后一个0，避免空字符串）
        String trimmed = s.replaceFirst("^0+(?!$)", "");
        return Integer.parseInt(trimmed);
    }

    /**
     * 比较里程碑版本
     * 规则：无里程碑 > 有里程碑；都有则按字典序；都无则相等
     */
    private static int compareMilestone(String m1, String m2) {
        // 都无里程碑
        if (m1.isEmpty() && m2.isEmpty()) {
            return 0;
        }
        // v1无里程碑，v2有 → v1大
        if (m1.isEmpty()) {
            return 1;
        }
        // v2无里程碑，v1有 → v2大
        if (m2.isEmpty()) {
            return -1;
        }
        // 都有里程碑，按字典序比较
        return m1.compareTo(m2);
    }

    /**
     * 版本号结构化信息
     */
    static class VersionInfo {

        int main;         // 主版本

        int sub;          // 次版本

        int increment;    // 增量版本

        String milestone; // 里程碑版本（空表示无）
    }
}

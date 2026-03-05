package org.jeecgframework.boot.learn.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/5
 * @Description: 服务中心的最佳位置
 * 题目描述
 * 一家快递公司希望在一条街道建立新的服务中心。公司统计了该街道中所有区域在地图上
 * 的位置，并希望能够以此为依据为新的服务中心选址：使服务中心
 * 到所有区域的距离的总和最小。
 * 给你一个数组 positions ，其中 positions[i] = [left, right] 表示第 i
 * 个区域在街道上的位置，其中 left
 * 代表区域的左侧的起点， right 表示区域的右侧终点，设择服务中心的位置为 location。
 * * 如果第 i 个区域的右侧起点 right 满足 right < location ，则第 i
 * 个区域到服务中心的距离为 location - right；
 * * 如果第 i 个区域的左侧起点 left 满足 left > location ，则第 i
 * 个区域到服务中心的距离为 left - location；
 * * 如果第 i 个区域的两侧 left, right 满足 left <= location <= right ，则第 i
 * 个区域到服务中心的距离为 0；
 * 选择最佳的服务中心的位置为 location ，请返回最佳的服务中心位置到所有区域的距离总和的最小值。 输入描述
 * 先输入区域数组 positions 的长度 n（1 <= n <= 10^5）
 * 接下来 n 行每行输入成对的 left 和 right 值，以空格隔开
 * -10^9 <left <= 10^9
 * -10^9 <right<= 10^9
 * 输出描述
 * 输出为 location
 */
public class BestServiceLocation {

    // 存储所有区域的left和right
    static List<long[]> positions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        long low = Long.MAX_VALUE;
        long high = Long.MIN_VALUE;

        // 读取所有区域，确定初始查找区间
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().trim().split(" ");
            long left = Long.parseLong(parts[0]);
            long right = Long.parseLong(parts[1]);
            positions.add(new long[]{left, right});
            low = Math.min(low, left);
            high = Math.max(high, right);
        }
        scanner.close();

        // 三分查找最小总距离（处理大数，用long避免溢出）
        long minTotalDistance = ternarySearch(low, high);
        System.out.println(minTotalDistance);
    }

    /**
     * 三分查找：找到使总距离最小的位置，并返回最小总距离
     */
    private static long ternarySearch(long low, long high) {
        // 三分查找迭代次数（足够多，确保精度）
        for (int i = 0; i < 100; i++) {
            if (high - low < 3) break; // 区间足够小，直接遍历

            long mid1 = low + (high - low) / 3;
            long mid2 = high - (high - low) / 3;
            long cost1 = calculateTotalDistance(mid1);
            long cost2 = calculateTotalDistance(mid2);

            if (cost1 < cost2) {
                high = mid2; // 最小值在左区间
            } else {
                low = mid1;  // 最小值在右区间
            }
        }

        // 遍历最终小区间，找到最小总距离
        long minCost = Long.MAX_VALUE;
        for (long loc = low; loc <= high; loc++) {
            minCost = Math.min(minCost, calculateTotalDistance(loc));
        }
        return minCost;
    }

    /**
     * 计算某个location到所有区域的总距离
     */
    private static long calculateTotalDistance(long location) {
        long total = 0;
        for (long[] pos : positions) {
            long left = pos[0];
            long right = pos[1];
            if (location > right) {
                total += location - right;
            } else if (location < left) {
                total += left - location;
            }
            // 落在区间内，距离为0，无需计算
        }
        return total;
    }
}
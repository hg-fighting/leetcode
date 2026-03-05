package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/5
 * @Description: 不爱施肥的小布
 * 题目描述
 * 某农场主管理了一大片果园，fields[i]
 * 表示不同果林的面积，单位:(m^2)，现在要为所有的果林施肥且必须在 n
 * 天之内完成，否则影响收成。小布是果林的工作人员，他每次选择一片果林进行施肥，且
 * 一片果林施肥完后当天不再进行施肥作业。假设施肥机的能效为 k，单位:(m^2/day)，请问
 * 至少租赁能效
 * k 为多少的施肥机才能确保不影响收成？如果无法完成施肥任务，则返回 -1。 输入描述
 * 第一行输入为 m 和 n，m 表示 fields 中的元素个数，n 表示施肥任务必须在 n 天内（含 n
 * 天）完成；
 * 第二行输入为 fields，fields[i] 表示果林 i 的面积，单位:(m^2)
 * 输出描述
 * 对于每组数据，输出最小施肥机的能效 k，无多余空格。
 */
public class FertilizerK {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取m（果林数）和n（天数）
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine(); // 换行符

        // 读取果林面积数组
        int[] fields = new int[m];
        String[] fieldStr = scanner.nextLine().trim().split(" ");
        for (int i = 0; i < m; i++) {
            fields[i] = Integer.parseInt(fieldStr[i]);
        }
        scanner.close();

        // 步骤1：判断是否无法完成（果林数>天数）
        if (m > n) {
            System.out.println(-1);
            return;
        }

        // 步骤2：二分查找最小k
        int left = 1;
        int right = 0;
        // 找最大面积作为右边界
        for (int area : fields) {
            right = Math.max(right, area);
        }

        int minK = right; // 初始化为最大可能值
        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免溢出
            // 验证当前k是否可行
            if (isFeasible(fields, n, mid)) {
                minK = mid; // 记录可行的k，尝试更小值
                right = mid - 1;
            } else {
                left = mid + 1; // 不可行，需要更大的k
            }
        }

        // 输出最小k
        System.out.println(minK);
    }

    /**
     * 验证：用能效k是否能在n天内完成施肥
     */
    private static boolean isFeasible(int[] fields, int n, int k) {
        int totalDays = 0;
        for (int area : fields) {
            // 计算单块果林需要的天数：ceil(area/k) = (area + k - 1) / k
            int days = (area + k - 1) / k;
            totalDays += days;
            // 提前终止：总天数已超n，无需继续计算
            if (totalDays > n) {
                return false;
            }
        }
        return totalDays <= n;
    }
}
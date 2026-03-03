package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/3
 * @Description: 优秀学员统计
 * 题目描述
 * 公司某部门软件教导团正在组织新员工每日打卡学习活动，他们开展这项学习活动已经一
 * 个月了，所以想统计下这个月优秀的打卡员工。每个员工会对应一个 id，每天的打卡记录
 * 记录当天打卡员工的 id 集合，一共 30 天。
 * 请你实现代码帮助统计出打卡次数 top5 的员工。假如打卡次数相同，将较早参与打卡的员
 * 工排在前面，如果开始参与打卡的时间还是一样，将 id 较小的员工排在前面。
 * 注：不考虑并列的情况，按规则返回前 5 名员工的 id 即可，如果当月打卡的员工少于 5 个，
 * 按规则排序返回所有有打卡记录的员工 id。 输入描述
 * 第一行输入为新员工数量 N，表示新员工编号 id 为 0 到 N-1，N 的范围为[1,100]
 * 第二行输入为 30 个整数，表示每天打卡的员工数量，每天至少有 1 名员工打卡
 * 之后 30 行为每天打卡的员工 id 集合，id 不会重复
 * 输出描述
 * 按顺序输出打卡 top5 员工的 id，用空格隔开
 * 8
 * 3 3 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 0 1 2
 * 0 1 2
 * 0 1 2
 * 统计结果：
 * 0、1、2 打卡次数 3 次（首次 Day1）；3~7 打卡次数 0；
 * 排序后：0→1→2；
 * 输出：0 1 2。
 */
public class ExcellentStudentStatistics {
    // 存储员工打卡信息的实体类
    static class Employee {
        int id;          // 员工id
        int count;       // 打卡次数
        int firstDay;    // 首次打卡日期（1~30）

        public Employee(int id) {
            this.id = id;
            this.count = 0;
            this.firstDay = Integer.MAX_VALUE; // 初始化为最大值，标记未打卡
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. 读取新员工数量N（id 0~N-1）
        int N = scanner.nextInt();
        // 初始化所有员工信息
        Employee[] employees = new Employee[N];
        for (int i = 0; i < N; i++) {
            employees[i] = new Employee(i);
        }

        // 2. 读取30天每天的打卡人数（仅用于读取后续数据，无实际计算作用）
        int[] dailyCount = new int[30];
        for (int i = 0; i < 30; i++) {
            dailyCount[i] = scanner.nextInt();
        }
        scanner.nextLine(); // 处理换行符

        // 3. 读取30天的打卡记录，统计次数和首次打卡日期
        for (int day = 1; day <= 30; day++) {
            String[] ids = scanner.nextLine().split(" ");
            for (String idStr : ids) {
                int id = Integer.parseInt(idStr);
                Employee emp = employees[id];
                emp.count++; // 打卡次数+1
                // 更新首次打卡日期（仅记录最早的）
                if (day < emp.firstDay) {
                    emp.firstDay = day;
                }
            }
        }

        // 4. 筛选有打卡记录的员工（count>0）
        List<Employee> validEmps = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.count > 0) {
                validEmps.add(emp);
            }
        }

        // 5. 按规则排序
        validEmps.sort((e1, e2) -> {
            // 优先级1：打卡次数降序
            if (e1.count != e2.count) {
                return Integer.compare(e2.count, e1.count);
            }
            // 优先级2：首次打卡日期升序
            if (e1.firstDay != e2.firstDay) {
                return Integer.compare(e1.firstDay, e2.firstDay);
            }
            // 优先级3：id升序
            return Integer.compare(e1.id, e2.id);
        });

        // 6. 输出结果（取前5个或全部）
        StringBuilder sb = new StringBuilder();
        int outputCount = Math.min(validEmps.size(), 5);
        for (int i = 0; i < outputCount; i++) {
            sb.append(validEmps.get(i).id);
            if (i < outputCount - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb);

        scanner.close();
    }
}
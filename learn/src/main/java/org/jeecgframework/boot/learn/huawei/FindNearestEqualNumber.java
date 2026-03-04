package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/4  16:12
 * @Description: 找数字
 * 题目描述
 * 给一个二维数组 nums，对于每一个元素 num[i]，找出距离最近的且值相等的元素，输出横
 * 纵坐标差值的绝对值之和，如果没有等值元素，则输出-1。
 * 例如：
 * 输入数组 nums 为
 * 0 3 5 4 2
 * 2 5 7 8 3
 * 2 5 4 2 4
 * 对于 num[0][0] = 0，不存在相等的值。
 * 对于 num[0][1] = 3，存在一个相等的值，最近的坐标为 num[1][4]，最小距离为 4。
 * 对于 num[0][2] = 5，存在两个相等的值，最近的坐标为 num[1][1]，故最小距离为 2。
 * ... 对于 num[1][1] = 5，存在两个相等的值，最近的坐标为 num[2][1]，故最小距离为 1。
 * ... 故输出为
 * -1 4 2 3 3
 * 1 1 -1 -1 4
 * 1 1 2 3 2
 * 输入描述
 * 输入第一行为二维数组的行
 * 输入第二行为二维数组的列
 * 输入的数字以空格隔开。
 * 输出描述
 * 数组形式返回所有坐标值。
 */
public class FindNearestEqualNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 步骤1：读取输入（行、列、数组元素）
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine(); // 处理换行符

        int[][] nums = new int[rows][cols];
        // 哈希表：key=数值，value=该数值的所有坐标（存储为List<int[]>，int[0]=行，int[1]=列）
        Map<Integer, List<int[]>> valueToCoords = new HashMap<>();

        // 读取数组并填充哈希表
        for (int i = 0; i < rows; i++) {
            String[] parts = scanner.nextLine().trim().split(" ");
            for (int j = 0; j < cols; j++) {
                nums[i][j] = Integer.parseInt(parts[j]);
                // 将当前坐标加入对应数值的列表
                valueToCoords.computeIfAbsent(nums[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        // 步骤2：遍历每个元素，计算最小距离
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int currentVal = nums[i][j];
                List<int[]> coords = valueToCoords.get(currentVal);

                // 无其他等值元素，输出-1
                if (coords.size() <= 1) {
                    result[i][j] = -1;
                    continue;
                }

                // 遍历所有等值坐标，计算最小距离
                int minDistance = Integer.MAX_VALUE;
                for (int[] coord : coords) {
                    int x = coord[0];
                    int y = coord[1];
                    // 跳过自身坐标
                    if (x == i && y == j) {
                        continue;
                    }
                    // 计算曼哈顿距离：|x-i| + |y-j|
                    int distance = Math.abs(x - i) + Math.abs(y - j);
                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                }

                result[i][j] = minDistance;
            }
        }

        // 步骤3：输出结果（按数组形式打印）
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(result[i][j]);
                if (j < cols - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}

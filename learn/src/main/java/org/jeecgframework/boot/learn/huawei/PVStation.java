package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  13:50
 * @Description: 光伏场地建设规划
 * 题目描述
 * 祖国西北部有一片大片荒地，其中零星的分布着一些湖泊，保护区，矿区；整体上常年光
 * 照良好，但是也有一些地区光照不太好。某电力公司希望在这里建设多个光伏电站，生产
 * 清洁能源。对每平方公里的土地进行了发电评估，其中不能建设的区域发电量为 0kw，可
 * 以发电的区域根据光照，地形等给出了每平方公里年发电量 x 千瓦。我们希望能够找到其
 * 中集中的矩形区域建设电站，能够获得良好的收益。 输入描述
 * 第一行输入为调研的地区长，宽，以及准备建设的电站【长宽相等，为正方形】的边长，
 * 最低要求的发电量
 * 之后每行为调研区域每平方公里的发电量
 * 例如，输入为：
 * 2 5 2 6
 * 1 3 4 5 8
 * 2 3 6 7 1
 * 表示调研的区域大小为长 2 宽 5 的矩形，我们要建设的电站的边长为 2，建设电站最低发电量为 6
 * 输出描述
 * 输出为这样的区域有多少个
 * 上述输入长宽为 2 的正方形满足发电量大于等于 6 的区域有 4 个。
 * 则输出为：
 * 4
 */
public class PVStation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入：长、宽、正方形边长、最低发电量
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int k = scanner.nextInt();
        int minPower = scanner.nextInt();

        // 边界检查
        if (k <= 0 || k > row || k > col) {
            System.out.println(0);
            return;
        }

        // 读取发电量矩阵
        int[][] power = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                power[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        // 步骤1：构建前缀和矩阵（使用long避免整数溢出）
        long[][] prefix = new long[row + 1][col + 1];
        for (int i = 0; i < row; i++) {
            long rowSum = 0;
            for (int j = 0; j < col; j++) {
                rowSum += power[i][j];
                prefix[i + 1][j + 1] = prefix[i][j + 1] + rowSum;
            }
        }

        // 步骤2：统计符合条件的正方形数量
        int count = 0;
        // 遍历所有可能的正方形左上角（i,j）
        for (int i = 0; i <= row - k; i++) {
            for (int j = 0; j <= col - k; j++) {
                // 计算正方形区域的总发电量
                int x1 = i, y1 = j;
                int x2 = i + k - 1, y2 = j + k - 1;
                // 使用long避免溢出
                long total = prefix[x2 + 1][y2 + 1] - prefix[x1][y2 + 1] - prefix[x2 + 1][y1] + prefix[x1][y1];
                if (total >= minPower) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}

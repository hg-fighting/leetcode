package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/5  10:44
 * @Description: 对称字符串
 * 题目描述
 * 对称就是最大的美学，现有一道关于对称字符串的美学。已知：
 * 第 1 个字符串：R
 * 第 2 个字符串：BR
 * 第 3 个字符串：RBBR
 * 第 4 个字符串：BRRBRBBR
 * 第 5 个字符串：RBBRBRRBBRRBRBBR
 * 相信你已经发现规律了，没错！就是第 i 个字符串 = 第 i - 1 号字符串的取反 + 第 i - 1
 * 号字符串;取反(R->B, B->R);
 * 现在告诉你 n 和 k，让你求得第 n 个字符串的第 k 个字符是多少。(k 的编号从 0 开始)
 * 输入描述
 * 第一行输入一个 T，表示有 T 组用例；
 * 接下里输入 T 行，每行输入两个数字，表示 n, k
 * 1 <= T <= 100;
 * 1 <= n <= 64;
 * 0 <= k < 2^(n-1);
 * 输出描述
 * 输出 T 行表示答案；
 * 输出 "blue" 表示字符是 B；
 * 输出 "red" 表示字符是 R;
 */
public class SymmetricString {

    // 定义2^63的边界（因为n最大64时，2^(64-1)=2^63，k必须<2^63）
    private static final long MAX_K_BOUND = 1L << 63; // 2^63，注意：long的最大值是2^63-1

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            long n = scanner.nextLong();
            long k = scanner.nextLong();

            // 第一步：严格校验输入范围（题目要求）
            if (!isValidInput(n, k)) {
                System.err.println("输入超出范围！");
                continue; // 按题目要求，输入合法，此处仅防御性校验
            }

            // 第二步：递归求解字符
            char res = getChar(n, k);
            System.out.println(res == 'R' ? "red" : "blue");
        }
        scanner.close();
    }

    /**
     * 校验n和k是否符合题目范围要求
     * 1 <= n <= 64
     * 0 <= k < 2^(n-1)
     */
    private static boolean isValidInput(long n, long k) {
        // 1. 校验n的范围
        if (n < 1 || n > 64) {
            return false;
        }
        // 2. 校验k的下界
        if (k < 0) {
            return false;
        }
        // 3. 校验k的上界：k < 2^(n-1)
        // 分情况处理，避免溢出：
        if (n == 64) {
            // n=64时，2^(64-1)=2^63，k必须<2^63（而long的k最大是2^63-1，因此只要k>=0就合法）
            return true;
        } else {
            // n<64时，2^(n-1)可以安全计算（2^63-1是long最大值）
            long upperBound = 1L << (n - 1); // 2^(n-1)
            return k < upperBound;
        }
    }

    /**
     * 递归获取第n个字符串的第k位字符（R/B）
     * 核心逻辑：分治+取反，避免生成完整字符串
     */
    private static char getChar(long n, long k) {
        // 基础情况：第1个字符串只有R
        if (n == 1) {
            return 'R';
        }

        // 计算中点mid=2^(n-2)，分情况避免溢出：
        long mid;
        if (n == 2) {
            mid = 1; // 2^(2-2)=1，特殊处理避免n=2时1<<0=1
        } else if (n == 64) {
            mid = 1L << 62; // 2^(64-2)=2^62，不会溢出
        } else {
            mid = 1L << (n - 2); // 2^(n-2)
        }

        if (k < mid) {
            // 前半段：取反第n-1个字符串的第k位
            char c = getChar(n - 1, k);
            return c == 'R' ? 'B' : 'R';
        } else {
            // 后半段：等于第n-1个字符串的第k-mid位
            return getChar(n - 1, k - mid);
        }
    }
}

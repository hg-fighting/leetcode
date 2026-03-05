package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  10:55
 * @Description: 寻找链表的中间结点
 * 题目描述
 * 给定一个单链表 L，请编写程序输出 L
 * 中间结点保存的数据。如果有两个中间结点，则输出第二个中间结点保存的数据。
 * 例如：给定 L 为 1→7→5，则输出应该为 7；给定 L 为 1→2→3→4，则输出应该为 3。 输入描述
 * 每个输入包含 1 个测试用例。每个测试用例第 1
 * 行给出链表首结点的地址、结点总个数正整数 N (≤105)。结点的地址是 5
 * 位非负整数，NULL 地址用
 * −1 表示。
 * 接下来有 N 行，每行格式为：
 * Address Data Next
 * 其中 Address 是结点地址，Data 是该结点保存的整数数据(0 ≤ Data ≤ 108)，Next
 * 是下一结点的地址。
 * 输出描述
 * 对每个测试用例，在一行中输出 L
 * 中间结点保存的数据。如果有两个中间结点，则输出第二个中间结点保存的数据。
 * 示例
 * 00100 3
 * 00100 1 00001
 * 00001 7 12309
 * 12309 5 -1
 * 输入含义：
 * 首地址 00100，总 3 个结点；
 * 00100 结点：数据 1，下一个 00001；
 * 00001 结点：数据 7，下一个 12309；
 * 12309 结点：数据 5，下一个 - 1；
 * 链表顺序：1→7→5，长度 3；
 * 中间索引：3/2=1，对应数据 7；
 * 输出：7。
 */
public class LinkedListMiddleNode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取首结点地址和结点总数
        String headAddr = scanner.next();
        int n = scanner.nextInt();

        // 存储所有结点：key=结点地址，value=[数据, 下一个地址]
        Map<String, String[]> nodeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String addr = scanner.next();
            String data = scanner.next();
            String next = scanner.next();
            nodeMap.put(addr, new String[]{data, next});
        }

        // 还原链表：按顺序存储结点数据（增加空指针判断）
        List<String> list = new ArrayList<>();
        String currentAddr = headAddr;
        // 遍历直到Next为-1（NULL），且当前地址存在于结点表中
        while (!"-1".equals(currentAddr) && nodeMap.containsKey(currentAddr)) {
            String[] node = nodeMap.get(currentAddr);
            // 双重保险：避免node为null的极端情况
            if (node == null) {
                break;
            }
            list.add(node[0]); // 存储当前结点数据
            currentAddr = node[1]; // 移动到下一个结点
        }

        // 找中间结点：索引为len/2（整数除法，自动向下取整）
        int midIndex = list.size() / 2;
        // 边界保护：链表为空的情况（题目中N≥1，实际可省略）
        if (list.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(midIndex));
        }

        scanner.close();
    }
}

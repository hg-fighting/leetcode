package org.jeecgframework.boot.learn.huawei;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  8:25
 * @Description: 打印文件
 * 题目描述
 * 有 5 台打印机打印文件，每台打印机有自己的待打印队列。因为打印的文件内容有轻重缓
 * 急之分，所以队列中的文件有 1~10 不同的优先级，其中数字越大优先级越高。打印机会从
 * 自己的待打印队列中选择优先级最高的文件来打印。如果存在两个优先级一样的文件，则
 * 选择最早进入队列的那个文件。
 * 现在请你来模拟这 5 台打印机的打印过程。
 * 输入描述
 * 每个输入包含 1 个测试用例，每个测试用例第 1 行给出发生事件的数量 N (0 < N < 1000)。
 * 接下来有 N 行，分别表示发生的事件。
 * 共有如下两种事件：
 * 1\. "IN P NUM"，表示有一个拥有优先级 NUM 的文件放到了打印机 P 的待打印队列中。(0
 * < P <= 5, 0 < NUM <= 10)；
 * 2\. "OUT P"，表示打印机 P 进行了一次文件打印，同时该文件从待打印队列中取出。(0 < P
 * <= 5)。 输出描述
 * 对于每个测试用例，每次"OUT
 * P"事件，请在一行中输出文件的编号。如果此时没有文件可以打印，请输出"NULL"。
 * 文件的编号定义为："IN P NUM"事件发生第 X 次，此处待打印文件的编号为
 * X。编号从 1 开始。
 */
public class PrinterSimulation {

    // 定义文件实体：编号、优先级、入队顺序（用于同优先级排序）
    static class File implements Comparable<File> {

        int id;         // 文件编号（IN事件的第X次）

        int priority;   // 优先级（1-10）

        int order;      // 入队顺序（用于同优先级排序）

        public File(int id, int priority, int order) {
            this.id = id;
            this.priority = priority;
            this.order = order;
        }

        // 自定义排序规则：优先级降序 → 入队顺序升序
        @Override
        public int compareTo(File o) {
            if (this.priority != o.priority) {
                // 优先级高的排前面（降序）
                return Integer.compare(o.priority, this.priority);
            } else {
                // 优先级相同，入队早的排前面（升序）
                return Integer.compare(this.order, o.order);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine().trim());

        // 初始化5台打印机的优先队列（索引1-5对应打印机1-5）
        PriorityQueue<File>[] printers = new PriorityQueue[6];
        for (int i = 1; i <= 5; i++) {
            printers[i] = new PriorityQueue<>();
        }

        int fileId = 0;    // 文件编号（IN事件计数）
        int enqueueOrder = 0; // 入队顺序（用于同优先级排序）

        // 处理N个事件
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            String eventType = parts[0];
            int P = Integer.parseInt(parts[1]); // 打印机编号（1-5）

            if ("IN".equals(eventType)) {
                // IN事件：添加文件到打印机P的队列
                fileId++;
                enqueueOrder++;
                int NUM = Integer.parseInt(parts[2]);
                printers[P].offer(new File(fileId, NUM, enqueueOrder));
            } else if ("OUT".equals(eventType)) {
                // OUT事件：打印并输出文件编号
                PriorityQueue<File> queue = printers[P];
                if (queue.isEmpty()) {
                    System.out.println("NULL");
                } else {
                    File printFile = queue.poll();
                    System.out.println(printFile.id);
                }
            }
        }
        scanner.close();
    }
}

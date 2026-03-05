package org.jeecgframework.boot.learn.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/4 16:38
 * @Description: AI 处理器组合
 * 题目描述：
 * 某公司研发了一款高性能 AI 处理器。每台物理设备具备 8 颗 AI 处理器，编号分别为 0、1、2 、3、4、5、6、7。
 * 编号 0-3 的处理器处于同一个链路中，编号 4-7 的处理器处于另外一个链路中，不通链路中的处理器不能通信，如下图所示。
 * 现给定服务器可用的处理器编号数组 array，以及任务申请的处理器数量 num，找出符合下列亲和性调度原则的芯片组合。
 * 如果不存在符合要求的组合，则返回空列表。
 * 亲和性调度原则：
 * - 如果申请处理器个数为 1，则选择同一链路，剩余可用的处理器数量为 1 个的最佳，其次是剩余 3 个的为次佳，然后是剩余 2 个，最后是剩余 4 个。
 * - 如果申请处理器个数为 2，则选择同一链路剩余可用的处理器数量 2 个的为最佳，其次是剩余 4 个，最后是剩余 3 个。
 * - 如果申请处理器个数为 4，则必须选择同一链路剩余可用的处理器数量为 4 个。
 * - 如果申请处理器个数为 8，则申请节点所有 8 个处理器。
 * 提示：
 * 1. 任务申请的处理器数量只能是 1、2、4、8
 * 2. 编号 0-3 的处理器处于一个链路，编号 4-7 的处理器处于另外一个链路。
 * 3. 处理器编号唯一，且不存在相同编号处理器。
 * 输入描述：
 * 输入包含可用的处理器编号数组 array，以及任务申请的处理器数量 num 两个部分。
 * 第一行为 array，第二行为 num。例如：
 * [0, 1, 4, 5, 6, 7] 1
 * 表示当前编号为 0、1、4、5、6、7 的处理器可用。任务申请 1 个处理器。
 * 0<= array.length <= 8
 * 0<= array[i] <= 7
 * num in [1, 2, 4, 8]
 * 输出描述：
 * 输出为组合列表，当 array=[0, 1, 4, 5, 6, 7] ，num=1 时，输出为[[0], [1]]。
 */
public class AIProcessorCombination {

    /**
     * 主函数
     *
     * @param args 输入参数
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 步骤1：读取输入
        // 可用处理器编号
        String arrayInput = scanner.nextLine();
        // 任务申请的处理器数量
        int num = scanner.nextInt();

        // 将输入的处理器编号字符串转换为数组
        String[] arrayStr = arrayInput.replaceAll("[\\[\\] ]", "").split(",");
        List<Integer> availableProcessors = new ArrayList<>();
        for (String s : arrayStr) {
            availableProcessors.add(Integer.parseInt(s));
        }

        // 步骤2：根据调度原则找出符合要求的组合
        List<List<Integer>> result = findCombinations(availableProcessors, num);

        // 步骤3：输出结果
        System.out.println(result);

        scanner.close();
    }

    /**
     * 查找符合亲和性调度原则的处理器组合
     *
     * @param availableProcessors 可用的处理器列表
     * @param num                 任务申请的处理器数量
     * @return 返回符合条件的处理器组合
     */
    private static List<List<Integer>> findCombinations(List<Integer> availableProcessors, int num) {
        // 基站处理器分为两个链路：链路1: [0, 1, 2, 3], 链路2: [4, 5, 6, 7]
        List<Integer> link1 = Arrays.asList(0, 1, 2, 3);
        List<Integer> link2 = Arrays.asList(4, 5, 6, 7);

        // 按链路划分处理器
        List<Integer> availableLink1 = new ArrayList<>();
        List<Integer> availableLink2 = new ArrayList<>();

        for (int processor : availableProcessors) {
            if (link1.contains(processor)) {
                availableLink1.add(processor);
            } else if (link2.contains(processor)) {
                availableLink2.add(processor);
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        // 根据申请数量选择合适的处理器组合
        switch (num) {
            case 1:
                // 选择一个处理器
                if (!availableLink1.isEmpty()) {
                    result.addAll(generateCombinations(availableLink1, 1));
                }
                if (!availableLink2.isEmpty()) {
                    result.addAll(generateCombinations(availableLink2, 1));
                }
                break;
            case 2:
                // 选择两个处理器
                if (availableLink1.size() >= 2) {
                    result.addAll(generateCombinations(availableLink1, 2));
                }
                if (availableLink2.size() >= 2) {
                    result.addAll(generateCombinations(availableLink2, 2));
                }
                break;
            case 4:
                // 选择四个处理器
                if (availableLink1.size() >= 4) {
                    result.addAll(generateCombinations(availableLink1, 4));
                }
                if (availableLink2.size() >= 4) {
                    result.addAll(generateCombinations(availableLink2, 4));
                }
                break;
            case 8:
                // 选择所有8个处理器
                if (availableProcessors.size() == 8) {
                    result.add(new ArrayList<>(availableProcessors));
                }
                break;
        }

        return result;
    }

    /**
     * 生成处理器的组合
     *
     * @param availableProcessors 可用处理器列表
     * @param k                   需要的组合大小
     * @return 返回所有的组合
     */
    private static List<List<Integer>> generateCombinations(List<Integer> availableProcessors, int k) {
        List<List<Integer>> combinations = new ArrayList<>();

        // 当需要生成组合大小为1时，直接返回每个处理器的单独组合
        if (k == 1) {
            for (Integer processor : availableProcessors) {
                List<Integer> combination = new ArrayList<>();
                combination.add(processor);
                combinations.add(combination);
            }
        } else {
            // 当需要生成大于1的组合时，递归生成所有可能的组合
            for (int i = 0; i < availableProcessors.size(); i++) {
                // 获取当前元素之后的子列表
                List<Integer> subList = availableProcessors.subList(i + 1, availableProcessors.size());
                // 递归生成组合
                for (List<Integer> subCombination : generateCombinations(subList, k - 1)) {
                    List<Integer> combination = new ArrayList<>();
                    combination.add(availableProcessors.get(i));
                    combination.addAll(subCombination);
                    combinations.add(combination);
                }
            }
        }

        return combinations;
    }
}
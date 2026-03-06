package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  10:57
 * @Description:
 */
public class GuessRiddle {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入：谜面列表（逗号分隔）、谜底库列表（逗号分隔）
        String puzzleInput = scanner.nextLine().trim();
        String answerInput = scanner.nextLine().trim();
        scanner.close();

        // 拆分谜面和谜底为数组
        String[] puzzles = puzzleInput.split(",");
        String[] answers = answerInput.split(",");

        // 步骤1：预处理谜底库，建立特征到谜底的映射（优先保留先出现的谜底）
        Map<String, String> featureToAnswer = new HashMap<>();
        for (String ans : answers) {
            if (ans.isEmpty()) {
                continue; // 跳过空字符串
            }
            // 生成两个特征：排序特征（条件1）、去重排序特征（条件2）
            String sortFeature = getSortFeature(ans);
            String dedupSortFeature = getDedupSortFeature(ans);
            // 仅当特征未映射时添加（保证谜底库中先出现的优先）
            featureToAnswer.putIfAbsent(sortFeature, ans);
            featureToAnswer.putIfAbsent(dedupSortFeature, ans);
        }

        // 步骤2：遍历每个谜面，匹配谜底
        List<String> resultList = new ArrayList<>();
        for (String puzzle : puzzles) {
            if (puzzle.isEmpty()) {
                resultList.add("not found");
                continue;
            }
            // 生成谜面的两个特征
            String puzzleSort = getSortFeature(puzzle);
            String puzzleDedupSort = getDedupSortFeature(puzzle);
            // 优先匹配条件1，再匹配条件2，无则返回not found
            String match = featureToAnswer.getOrDefault(puzzleSort,
                    featureToAnswer.getOrDefault(puzzleDedupSort, "not found"));
            resultList.add(match);
        }

        // 步骤3：输出结果（逗号分隔）
        String result = String.join(",", resultList);
        // 特殊处理：所有都是not found时，直接输出not found（可选，按题目要求）
        if ("not found".equals(result.replace("not found,", ""))) {
            System.out.println("not found");
        } else {
            System.out.println(result);
        }
    }

    /**
     * 生成单词的排序特征（条件1）：字母排序后拼接
     */
    private static String getSortFeature(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * 生成单词的去重排序特征（条件2）：去重后字母排序拼接
     */
    private static String getDedupSortFeature(String word) {
        Set<Character> dedupSet = new TreeSet<>(); // TreeSet自动去重+排序
        for (char c : word.toCharArray()) {
            dedupSet.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : dedupSet) {
            sb.append(c);
        }
        return sb.toString();
    }
}

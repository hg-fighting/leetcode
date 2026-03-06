package org.jeecgframework.boot.learn.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/6  11:23
 * @Description: 分界线
 * 题目描述
 * 电视剧《分界线》里面有一个片段，男主为了向警察透露案件细节，且不暴露自己，于是
 * 将报刊上的字剪切下来，剪拼成匿名信。
 * 现在有一名举报人，希望借鉴这种手段，使用英文报刊完成举报操作。
 * 但为了增加文章的混淆度，只需满足每个单词中字母数量一致即可，不关注每个字母的顺序。
 * 解释：单词'on'允许通过单词'no'进行替代
 * 报纸代表 newspaper, 匿名信代表 anonymousLetter, 求报纸内容是否可以拼成匿名信。
 * 输入描述
 * 第一行输入 newspaper 内容，包括 1-N 个字符串，用空格分开
 * 第二行输入 anonymousLetter 内容，包括 1-N 个字符串，用空格分开
 * 1、newspaper 和 anonymousLetter 的字符串由小写英文字母组成且每个字母只能使用一次
 * 2、newspaper 内容中的每个字符串字母顺序可以任意调整,但必须保证字符串的完整性(每个字符串不能有多余字母)
 * 3、1<N<100 , 1<= newspaper.length, anonymousLetter.length <= 104
 * 输出描述
 * 如果报纸可以拼成匿名信返回 true，否则返回 false
 */
public class DividingLine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取报纸和匿名信内容
        String[] newspaper = scanner.nextLine().trim().split(" ");
        String[] letter = scanner.nextLine().trim().split(" ");
        scanner.close();

        // 步骤1：统计报纸单词的特征频率
        Map<String, Integer> newsFeatureCount = new HashMap<>();
        for (String word : newspaper) {
            String feature = getCharCountFeature(word);
            newsFeatureCount.put(feature, newsFeatureCount.getOrDefault(feature, 0) + 1);
        }

        // 步骤2：匹配匿名信单词
        boolean canCompose = true;
        for (String word : letter) {
            String feature = getCharCountFeature(word);
            if (newsFeatureCount.getOrDefault(feature, 0) == 0) {
                canCompose = false;
                break;
            }
            // 扣减特征数量
            newsFeatureCount.put(feature, newsFeatureCount.get(feature) - 1);
        }

        System.out.println(canCompose);
    }

    /**
     * 生成单词的字母计数特征（如 "on" → "n:1,o:1"）
     */
    private static String getCharCountFeature(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                sb.append((char) ('a' + i)).append(":").append(count[i]).append(",");
            }
        }
        return sb.toString().isEmpty() ? "" : sb.substring(0, sb.length() - 1);
    }
}

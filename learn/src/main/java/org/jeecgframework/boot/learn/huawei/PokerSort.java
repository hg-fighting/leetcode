package org.jeecgframework.boot.learn.huawei;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: hao gang
 * @Date: 2026/3/4 16:38
 * @Description: 整理扑克牌
 * <p>
 * 题目描述：
 * 给定一组数字，表示扑克牌的牌面数字，忽略扑克牌的花色，请按如下规则对这一组扑克牌进行整理：
 * 步骤 1、对扑克牌进行分组，形成组合牌，规则如下：
 * 1. 当牌面数字相同张数 大于等于 4 时 ，组合牌为“炸弹”；
 * 2. 3 张相同牌面数字 + 2 张相同牌面数字，且 3 张牌与 2 张牌不相同时，组合牌为“葫芦”；
 * 3. 3 张相同牌面数字，组合牌为“三张”；
 * 4. 2 张相同牌面数字，组合牌为“对子”；
 * 5. 剩余没有相同的牌，则为“单张”；
 * 步骤 2、对上述组合牌进行由大到小排列，规则如下：
 * 1. 不同类型组合牌之间由大到小排列规则：“炸弹” > "葫芦" > "三张" > "对子" > “单张”；
 * 2. 相同类型组合牌之间，除“葫芦”外，按组合牌全部牌面数字加总由大到小排列；
 * 3. “葫芦”则先按 3 张相同牌面数字加总由大到小排列，3 张相同牌面数字加总相同时，再按另外 2 张牌面数字加总由大到小排列；
 * 4. 由于“葫芦” > “三张”，因此如果能形成更大的组合牌，也可以将“三张”拆分为 2 张和 1 张，其中的 2 张可以和其它“三张”重新组合成“葫芦”，剩下的 1 张为“单张”；
 * 步骤 3、当存在多个可能组合方案时，按如下规则排序取最大的一个组合方案：
 * 1. 依次对组合方案中的组合牌进行大小比较，规则同上；
 * 2. 当组合方案 A 中的第 n 个组合牌大于组合方案 B 中的第 n 个组合牌时，组合方案 A 大于组合方案 B；
 * <p>
 * 输入描述：
 * 第一行为空格分隔的 N 个正整数，每个整数取值范围[1,13]，N 的取值范围[1,1000]。
 * <p>
 * 输出描述：
 * 经重新排列后的扑克牌数字列表，每个数字以空格分隔。
 */
enum ComboType {
    BOMB(5, "炸弹"),    // 4张相同
    HULU(4, "葫芦"),    // 3张+2张（3张在前）
    THREE(3, "三张"),   // 3张相同
    PAIR(2, "对子"),    // 2张相同
    SINGLE(1, "单张");  // 单张

    int priority;

    String name;

    ComboType(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }
}

// 组合牌实体类
class Combo implements Comparable<Combo> {

    ComboType type;

    List<Integer> originalCards; // 原始组合（可修改的列表）

    int sum3;             // 葫芦的3张总和

    int sum2;             // 葫芦的2张总和

    int totalSum;         // 所有牌的总和（除葫芦外）

    int mainNum;          // 核心数字

    // 重构构造函数：避免不可修改列表
    public Combo(ComboType type, List<Integer> threeCards, List<Integer> twoCards) {
        this.type = type;
        this.originalCards = new ArrayList<>(); // 用ArrayList，可修改

        if (type == ComboType.HULU) {
            // 葫芦：3张在前，2张在后（转为ArrayList保证可排序）
            List<Integer> threeList = new ArrayList<>(threeCards);
            List<Integer> twoList = new ArrayList<>(twoCards);
            // 各自降序（即使数字相同，排序也不会报错）
            Collections.sort(threeList, Collections.reverseOrder());
            Collections.sort(twoList, Collections.reverseOrder());
            this.originalCards.addAll(threeList);
            this.originalCards.addAll(twoList);
            // 计算葫芦的排序字段
            this.sum3 = threeList.stream().mapToInt(Integer::intValue).sum();
            this.sum2 = twoList.stream().mapToInt(Integer::intValue).sum();
            this.mainNum = threeList.isEmpty() ? 0 : threeList.get(0);
            this.totalSum = this.sum3 + this.sum2;
        } else {
            // 非葫芦：直接用可修改的ArrayList
            List<Integer> normalList = new ArrayList<>(threeCards);
            Collections.sort(normalList, Collections.reverseOrder());
            this.originalCards = normalList;
            this.totalSum = normalList.stream().mapToInt(Integer::intValue).sum();
            this.mainNum = normalList.isEmpty() ? 0 : Collections.max(normalList);
        }
    }

    // 组合牌间的比较规则
    @Override
    public int compareTo(Combo o) {
        if (this.type.priority != o.type.priority) {
            return Integer.compare(o.type.priority, this.type.priority);
        }
        if (this.type == ComboType.HULU) {
            if (this.sum3 != o.sum3) {
                return Integer.compare(o.sum3, this.sum3);
            }
            return Integer.compare(o.sum2, this.sum2);
        }
        if (this.mainNum != o.mainNum) {
            return Integer.compare(o.mainNum, this.mainNum);
        }
        return Integer.compare(o.totalSum, this.totalSum);
    }

    // 获取输出牌列表
    public List<Integer> getOutputCards() {
        return new ArrayList<>(this.originalCards);
    }
}

// 组合方案
class ComboPlan {

    List<Combo> combos;

    int totalPriority;

    public ComboPlan(List<Combo> combos) {
        Collections.sort(combos);
        this.combos = combos;
        // 计算总优先级
        this.totalPriority = 0;
        for (Combo c : combos) {
            this.totalPriority += c.type.priority * 1000 + c.mainNum;
        }
    }

    // 转换为最终输出列表
    public List<Integer> toCardList() {
        List<Integer> result = new ArrayList<>();
        for (Combo combo : combos) {
            result.addAll(combo.getOutputCards());
        }
        return result;
    }
}

public class PokerSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入
        List<Integer> inputCards = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .toList();
        scanner.close();

        // 统计数字频次（降序排列）
        Map<Integer, Integer> cardCount = new TreeMap<>(Collections.reverseOrder());
        for (int card : inputCards) {
            cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
        }

        // 生成所有组合方案
        List<ComboPlan> allPlans = new ArrayList<>();
        generatePlans(new HashMap<>(cardCount), new ArrayList<>(), allPlans);

        // 选最优方案
        ComboPlan bestPlan = null;
        int maxPriority = -1;
        for (ComboPlan plan : allPlans) {
            if (plan.totalPriority > maxPriority) {
                maxPriority = plan.totalPriority;
                bestPlan = plan;
            }
        }

        // 输出结果
        List<Integer> result = bestPlan.toCardList();
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    // 递归生成组合方案（核心修复：所有列表用ArrayList，避免不可修改）
    private static void generatePlans(Map<Integer, Integer> cardCount, List<Combo> currentCombos, List<ComboPlan> allPlans) {
        Map<Integer, Integer> countCopy = new HashMap<>(cardCount);
        boolean hasRemaining = countCopy.values().stream().anyMatch(c -> c > 0);

        // 终止条件：无剩余牌，保存方案
        if (!hasRemaining) {
            allPlans.add(new ComboPlan(new ArrayList<>(currentCombos)));
            return;
        }

        List<Integer> sortedNums = new ArrayList<>(countCopy.keySet());
        sortedNums.sort(Collections.reverseOrder());

        // 1. 生成炸弹（≥4张）
        for (int num : sortedNums) {
            int count = countCopy.get(num);
            if (count >= 4) {
                countCopy.put(num, count - 4);
                // 修复：用ArrayList替代Collections.nCopies()
                List<Integer> bombCards = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    bombCards.add(num);
                }
                currentCombos.add(new Combo(ComboType.BOMB, bombCards, null));
                generatePlans(countCopy, currentCombos, allPlans);
                currentCombos.remove(currentCombos.size() - 1);
                countCopy.put(num, count);
                return;
            }
        }

        // 2. 生成葫芦（3+2）
        boolean huluGenerated = false;
        for (int threeNum : sortedNums) {
            int threeCount = countCopy.get(threeNum);
            if (threeCount >= 3) {
                countCopy.put(threeNum, threeCount - 3);
                // 找2张的数字
                for (int twoNum : sortedNums) {
                    if (twoNum == threeNum || countCopy.get(twoNum) < 2) continue;
                    countCopy.put(twoNum, countCopy.get(twoNum) - 2);
                    // 修复：用ArrayList生成3张和2张列表
                    List<Integer> threeCards = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        threeCards.add(threeNum);
                    }
                    List<Integer> twoCards = new ArrayList<>();
                    for (int i = 0; i < 2; i++) {
                        twoCards.add(twoNum);
                    }
                    currentCombos.add(new Combo(ComboType.HULU, threeCards, twoCards));
                    generatePlans(countCopy, currentCombos, allPlans);
                    currentCombos.remove(currentCombos.size() - 1);
                    countCopy.put(twoNum, countCopy.get(twoNum) + 2);
                    huluGenerated = true;
                    break;
                }
                countCopy.put(threeNum, threeCount);
                if (huluGenerated) return;
            }
        }

        // 3. 生成三张（≥3张）
        for (int num : sortedNums) {
            int count = countCopy.get(num);
            if (count >= 3) {
                countCopy.put(num, count - 3);
                // 修复：用ArrayList
                List<Integer> threeCards = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    threeCards.add(num);
                }
                currentCombos.add(new Combo(ComboType.THREE, threeCards, null));
                generatePlans(countCopy, currentCombos, allPlans);
                currentCombos.remove(currentCombos.size() - 1);
                countCopy.put(num, count);
                return;
            }
        }

        // 4. 生成对子（≥2张）
        for (int num : sortedNums) {
            int count = countCopy.get(num);
            if (count >= 2) {
                countCopy.put(num, count - 2);
                // 修复：用ArrayList
                List<Integer> pairCards = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    pairCards.add(num);
                }
                currentCombos.add(new Combo(ComboType.PAIR, pairCards, null));
                generatePlans(countCopy, currentCombos, allPlans);
                currentCombos.remove(currentCombos.size() - 1);
                countCopy.put(num, count);
                return;
            }
        }

        // 5. 生成单张
        for (int num : sortedNums) {
            int count = countCopy.get(num);
            if (count >= 1) {
                countCopy.put(num, count - 1);
                // 修复：用ArrayList
                List<Integer> singleCards = new ArrayList<>();
                singleCards.add(num);
                currentCombos.add(new Combo(ComboType.SINGLE, singleCards, null));
                generatePlans(countCopy, currentCombos, allPlans);
                currentCombos.remove(currentCombos.size() - 1);
                countCopy.put(num, count);
                return;
            }
        }
    }
}
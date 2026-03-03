package org.jeecgframework.boot.learn.huawei;

import java.util.*;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  14:03
 * @Description: 货币单位换算
 * 题目描述
 * 记账本上记录了若干条多国货币金额，需要转换成人民币分（fen），汇总后输出。
 * 每行记录一条金额，金额带有货币单位，格式为数字+单位，可能是单独元，或者单独分，或者元与分的组合。
 * 要求将这些货币全部换算成人民币分（fen）后进行汇总，汇总结果仅保留整数，小数部分舍弃。
 * 元和分的换算关系都是 1:100，如下：
 * 1CNY=100fen（
 * 1 元=100 分）
 * 1HKD=100cents（
 * 1 港元=100 港分）
 * 1JPY=100sen（
 * 1 日元=100 仙）
 * 1EUR=100eurocents（
 * 1 欧元=100 欧分）
 * 1GBP=100pence（
 * 1 英镑=100 便士）
 * 汇率如下表
 * CNY
 * |
 * JPY
 * |
 * HKD
 * |
 * EUR
 * |
 * GBP
 * ---|---|---|---|---
 * 100
 * |
 * 1825
 * |
 * 123
 * |
 * 14
 * |
 * 12
 * 即 100CNY=1825JPY=123HKD=14EUR=12GBP
 * 输入描述
 * 第一行输入为 N，N 表示记录数。0<N<100
 * 之后 N 行，每行表示一条货币记录，且该行只会是一种货币。 输出描述
 * 将每行货币转换成人民币分（fen）后汇总求和，只保留整数部分。
 * 输出格式只有整数数字，不带小数，不带单位。
 * 输入
 * 2
 * 1CNY
 * 50fen
 * 计算过程：
 * 1CNY = 1×100 = 100fen；
 * 50fen = 50×1 = 50fen；
 * 汇总：100+50=150；
 * 输出：150。
 */
public class CurrencyConversion {

    // 存储各货币元级单位到CNY分的换算系数（1元级货币 = X fen）
    private static final Map<String, Double> RATE_MAP = new HashMap<>();

    static {
        // 初始化汇率系数：基于 100CNY = 1825JPY = 123HKD = 14EUR = 12GBP
        RATE_MAP.put("CNY", 100.0);          // 1CNY = 100fen
        RATE_MAP.put("JPY", (100 * 100) / 1825.0); // 1JPY = (100CNY/1825JPY)*100fen
        RATE_MAP.put("HKD", (100 * 100) / 123.0);
        RATE_MAP.put("EUR", (100 * 100) / 14.0);
        RATE_MAP.put("GBP", (100 * 100) / 12.0);

        // 分級单位映射到对应元级单位 + 系数（÷100）
        RATE_MAP.put("fen", 1.0);            // 1fen = 1fen
        RATE_MAP.put("cents", RATE_MAP.get("HKD") / 100); // 1cent = 1HKD/100 → 转fen
        RATE_MAP.put("sen", RATE_MAP.get("JPY") / 100);
        RATE_MAP.put("eurocents", RATE_MAP.get("EUR") / 100);
        RATE_MAP.put("pence", RATE_MAP.get("GBP") / 100);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        long totalFen = 0; // 汇总的CNY分（用long避免溢出）

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            // 拆分数字和单位（找到第一个非数字/小数点的位置）
            int splitIndex = 0;
            while (splitIndex < line.length()) {
                char c = line.charAt(splitIndex);
                if (!Character.isDigit(c) && c != '.') {
                    break;
                }
                splitIndex++;
            }
            // 提取数字和单位
            double amount = Double.parseDouble(line.substring(0, splitIndex));
            String unit = line.substring(splitIndex);

            // 计算该金额对应的CNY分（舍弃小数部分）
            double fen = amount * RATE_MAP.get(unit);
            totalFen += (long) fen; // 强制转换舍弃小数
        }

        System.out.println(totalFen);
        scanner.close();
    }
}

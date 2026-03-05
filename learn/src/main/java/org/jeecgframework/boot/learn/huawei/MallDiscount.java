package org.jeecgframework.boot.learn.huawei;

import java.util.Scanner;

/**
 * @Author: hao gang
 * @Date: 2026/3/5
 * @Description: 【背景】
 * 某网上商城举办优惠活动，发布了满减、打折、无门槛 3 种优惠券，分别为：
 * 1.每满 100 元优惠 10 元，无使用数限制，如 100~199 元可以使用 1 张减 10 元，200~299 可使用
 * 2 张减 20 元，以此类推；
 * 2.92 折券，1 次限使用 1 张，如 100 元，则优惠后为 92 元；
 * 3.无门槛 5 元优惠券，无使用数限制，直接减 5 元。
 * 【优惠券使用限制】
 * 每次最多使用 2 种优惠券，2 种优惠可以叠加（优惠叠加时以优惠后的价格计算），以购物
 * 200 元为例，可以先用 92 折券优惠到 184 元，再用 1 张满减券优惠 10 元，最终价格是 174 元，
 * 也可以用满减券 2 张优惠 20 元为 180 元，再使用 92 折券优惠到 165（165.6 向下取整）元，不
 * 同使用顺序的优惠价格不同，以最优惠价格为准。在一次购物中，同一类型优惠券使用多
 * 张时必须一次性使用，不能分多次拆开穿插使用（不允许先使用 1 张满减券，再用打折券
 * ，再使用一张满减券）。
 * 【问题】
 * 请设计实现一种解决方法，帮助购物者以最少的优惠券获得最优的优惠价格。优惠后价格
 * 越低越好，同等优惠价格，使用的优惠券越少越好，可以允许某次购物不使用优惠券。
 * 【约定】
 * 优惠活动每人只能参加一次，每个人的优惠券种类和数量是一样的。 输入描述
 * 第一行：每个人拥有的优惠券数量（数量取值范围为[0, 10]），按满减、打折、无门槛的顺序输入。
 * 第二行：表示购物的人数 n（1 <= n <= 10000）。
 * 最后 n 行：每一行表示某个人优惠前的购物总价格（价格取值范围(0, 1000]，都为整数）。
 * 约定：输入都是符合题目设定的要求的。 输出描述
 * 每行输出每个人每次购物优惠后的最低价格以及使用的优惠券总数量，每行的输出顺序和
 * 输入的顺序保持一致。
 */
public class MallDiscount {

    // 优惠券类型枚举
    private static final int TYPE_FULL_REDUCE = 0; // 满减券

    private static final int TYPE_DISCOUNT = 1;    // 92折券

    private static final int TYPE_NO_THRESHOLD = 2;// 无门槛券

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. 读取优惠券数量：满减、打折、无门槛
        int[] couponNums = new int[3];
        String[] couponParts = scanner.nextLine().trim().split(" ");
        for (int i = 0; i < 3; i++) {
            couponNums[i] = Integer.parseInt(couponParts[i]);
        }
        // 2. 读取用户数
        int n = Integer.parseInt(scanner.nextLine().trim());
        // 3. 处理每个用户的价格
        for (int i = 0; i < n; i++) {
            int price = Integer.parseInt(scanner.nextLine().trim());
            // 计算最优优惠结果
            Result bestResult = calculateBestDiscount(price, couponNums);
            // 输出：最低价格 优惠券数量
            System.out.println(bestResult.finalPrice + " " + bestResult.couponCount);
        }
        scanner.close();
    }

    /**
     * 计算单个价格的最优优惠结果
     */
    private static Result calculateBestDiscount(int originalPrice, int[] couponNums) {
        // 初始化最优结果：不使用任何优惠券
        Result bestResult = new Result(originalPrice, 0);

        // 枚举所有合法的优惠券组合（最多2种）
        // 组合1：仅使用满减券
        if (couponNums[TYPE_FULL_REDUCE] > 0) {
            Result r = calculateOnlyFullReduce(originalPrice, couponNums[TYPE_FULL_REDUCE]);
            bestResult = compareResult(bestResult, r);
        }

        // 组合2：仅使用打折券
        if (couponNums[TYPE_DISCOUNT] > 0) {
            Result r = calculateOnlyDiscount(originalPrice);
            bestResult = compareResult(bestResult, r);
        }

        // 组合3：仅使用无门槛券
        if (couponNums[TYPE_NO_THRESHOLD] > 0) {
            Result r = calculateOnlyNoThreshold(originalPrice, couponNums[TYPE_NO_THRESHOLD]);
            bestResult = compareResult(bestResult, r);
        }

        // 组合4：满减 + 打折（两种顺序）
        if (couponNums[TYPE_FULL_REDUCE] > 0 && couponNums[TYPE_DISCOUNT] > 0) {
            // 顺序1：先满减，再打折
            Result r1 = calculateFullReduceThenDiscount(originalPrice, couponNums[TYPE_FULL_REDUCE]);
            // 顺序2：先打折，再满减
            Result r2 = calculateDiscountThenFullReduce(originalPrice, couponNums[TYPE_FULL_REDUCE]);
            Result r = compareResult(r1, r2);
            bestResult = compareResult(bestResult, r);
        }

        // 组合5：满减 + 无门槛（两种顺序）
        if (couponNums[TYPE_FULL_REDUCE] > 0 && couponNums[TYPE_NO_THRESHOLD] > 0) {
            // 顺序1：先满减，再无门槛
            Result r1 = calculateFullReduceThenNoThreshold(originalPrice, couponNums[TYPE_FULL_REDUCE], couponNums[TYPE_NO_THRESHOLD]);
            // 顺序2：先无门槛，再满减
            Result r2 = calculateNoThresholdThenFullReduce(originalPrice, couponNums[TYPE_FULL_REDUCE], couponNums[TYPE_NO_THRESHOLD]);
            Result r = compareResult(r1, r2);
            bestResult = compareResult(bestResult, r);
        }

        // 组合6：打折 + 无门槛（两种顺序）
        if (couponNums[TYPE_DISCOUNT] > 0 && couponNums[TYPE_NO_THRESHOLD] > 0) {
            // 顺序1：先打折，再无门槛
            Result r1 = calculateDiscountThenNoThreshold(originalPrice, couponNums[TYPE_NO_THRESHOLD]);
            // 顺序2：先无门槛，再打折
            Result r2 = calculateNoThresholdThenDiscount(originalPrice, couponNums[TYPE_NO_THRESHOLD]);
            Result r = compareResult(r1, r2);
            bestResult = compareResult(bestResult, r);
        }

        return bestResult;
    }

    // ------------------------ 单个/组合优惠券计算方法 ------------------------

    /**
     * 仅使用满减券
     */
    private static Result calculateOnlyFullReduce(int price, int fullReduceNum) {
        int maxUse = Math.min(fullReduceNum, price / 100); // 最多可用数量
        int reduce = maxUse * 10;
        int finalPrice = price - reduce;
        return new Result(finalPrice, maxUse);
    }

    /**
     * 仅使用打折券
     */
    private static Result calculateOnlyDiscount(int price) {
        int finalPrice = (int) (price * 0.92); // 向下取整
        return new Result(finalPrice, 1);
    }

    /**
     * 仅使用无门槛券
     */
    private static Result calculateOnlyNoThreshold(int price, int noThresholdNum) {
        int maxUse = Math.min(noThresholdNum, price / 5); // 最多可用数量（避免负数）
        int reduce = maxUse * 5;
        int finalPrice = price - reduce;
        return new Result(finalPrice, maxUse);
    }

    /**
     * 先满减，再打折
     */
    private static Result calculateFullReduceThenDiscount(int price, int fullReduceNum) {
        Result r1 = calculateOnlyFullReduce(price, fullReduceNum);
        Result r2 = calculateOnlyDiscount(r1.finalPrice);
        int totalCoupon = r1.couponCount + r2.couponCount;
        return new Result(r2.finalPrice, totalCoupon);
    }

    /**
     * 先打折，再满减
     */
    private static Result calculateDiscountThenFullReduce(int price, int fullReduceNum) {
        Result r1 = calculateOnlyDiscount(price);
        Result r2 = calculateOnlyFullReduce(r1.finalPrice, fullReduceNum);
        int totalCoupon = r1.couponCount + r2.couponCount;
        return new Result(r2.finalPrice, totalCoupon);
    }

    /**
     * 先满减，再无门槛
     */
    private static Result calculateFullReduceThenNoThreshold(int price, int fullReduceNum, int noThresholdNum) {
        Result r1 = calculateOnlyFullReduce(price, fullReduceNum);
        Result r2 = calculateOnlyNoThreshold(r1.finalPrice, noThresholdNum);
        int totalCoupon = r1.couponCount + r2.couponCount;
        return new Result(r2.finalPrice, totalCoupon);
    }

    /**
     * 先无门槛，再满减
     */
    private static Result calculateNoThresholdThenFullReduce(int price, int fullReduceNum, int noThresholdNum) {
        Result r1 = calculateOnlyNoThreshold(price, noThresholdNum);
        Result r2 = calculateOnlyFullReduce(r1.finalPrice, fullReduceNum);
        int totalCoupon = r1.couponCount + r2.couponCount;
        return new Result(r2.finalPrice, totalCoupon);
    }

    /**
     * 先打折，再无门槛
     */
    private static Result calculateDiscountThenNoThreshold(int price, int noThresholdNum) {
        Result r1 = calculateOnlyDiscount(price);
        Result r2 = calculateOnlyNoThreshold(r1.finalPrice, noThresholdNum);
        int totalCoupon = r1.couponCount + r2.couponCount;
        return new Result(r2.finalPrice, totalCoupon);
    }

    /**
     * 先无门槛，再打折
     */
    private static Result calculateNoThresholdThenDiscount(int price, int noThresholdNum) {
        Result r1 = calculateOnlyNoThreshold(price, noThresholdNum);
        Result r2 = calculateOnlyDiscount(r1.finalPrice);
        int totalCoupon = r1.couponCount + r2.couponCount;
        return new Result(r2.finalPrice, totalCoupon);
    }

    // ------------------------ 辅助方法 ------------------------

    /**
     * 比较两个结果，返回更优的（价格低优先，价格相同选券少的）
     */
    private static Result compareResult(Result r1, Result r2) {
        if (r1.finalPrice < r2.finalPrice) {
            return r1;
        } else if (r1.finalPrice > r2.finalPrice) {
            return r2;
        } else {
            // 价格相同，选优惠券数量少的
            return r1.couponCount <= r2.couponCount ? r1 : r2;
        }
    }

    /**
     * 结果实体：最终价格 + 优惠券使用数量
     */
    static class Result {

        int finalPrice;    // 优惠后价格

        int couponCount;   // 使用的优惠券数量

        public Result(int finalPrice, int couponCount) {
            this.finalPrice = finalPrice;
            this.couponCount = couponCount;
        }
    }
}
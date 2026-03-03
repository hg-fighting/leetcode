package org.jeecgframework.boot.learn.hot101.greedy;

import java.util.Arrays;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:57
 * @Description: 主持人调度二
 * 有 n 个活动即将举办，每个活动都有开始时间与活动的结束时间，第 i 个活动的开始时间是 starti ,第 i 个活动的结束时间是 endi ,举办某个活动意味着在该时间点开始举办，
 * 并在该时间点结束，活动的持续时间为 endi-starti 。
 * 给你一个整数数组 start 和一个整数数组 end ，请你返回 所需的最小主持人数量 ，以便所有活动都可以被举办。
 */
public class minmumNumberOfHost {

    public int minmumNumberOfHost(int n, int[][] startEnd) {
        int[] start = new int[n];
        int[] end = new int[n];
        //分别得到活动起始时间
        for (int i = 0; i < n; i++) {
            start[i] = startEnd[i][0];
            end[i] = startEnd[i][1];
        }
        //单独排序
        Arrays.sort(start, 0, start.length);
        Arrays.sort(end, 0, end.length);
        int res = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            //新开始的节目大于上一轮结束的时间，主持人不变
            if (start[i] >= end[j])
                j++;
            else
                //主持人增加
                res++;
        }
        return res;
    }
}

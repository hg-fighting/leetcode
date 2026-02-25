package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:15
 * @Description: 滑动窗口最大值
 */
public class maxInWindows {

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        //窗口大于数组长度的时候，返回空
        if (size <= num.length && size != 0) {
            //双向队列
            ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
            //先遍历一个窗口
            for (int i = 0; i < size; i++) {
                //去掉比自己先进队列的小于自己的值
                while (!dq.isEmpty() && num[dq.peekLast()] < num[i]) {
                    dq.pollLast();
                }
                dq.add(i);
            }
            //遍历后续数组元素
            for (int i = size; i < num.length; i++) {
                //取窗口内的最大值
                res.add(num[dq.peekFirst()]);
                while (!dq.isEmpty() && dq.peekFirst() < (i - size + 1))
                //弹出窗口移走后的值
                {
                    dq.pollFirst();
                }
                //加入新的值前，去掉比自己先进队列的小于自己的值
                while (!dq.isEmpty() && num[dq.peekLast()] < num[i]) {
                    dq.pollLast();
                }
                dq.add(i);
            }
            res.add(num[dq.pollFirst()]);
        }
        return res;
    }

    /**
     * 暴力解法
     */
    public ArrayList<Integer> maxInWindows2(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (size > num.length || size == 0) {
            return res;
        }
        for (int i = 0; i <= num.length - size; i++) {
            int max = num[i];
            for (int j = i; j < i + size; j++) {
                if (num[j] > max) {
                    max = num[j];
                }
            }
            res.add(max);
        }
        return res;
    }

}

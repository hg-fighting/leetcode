package org.jeecgframework.boot.learn.hot101.array;

import org.jeecgframework.boot.learn.hot101.basic.Interval;

import java.util.ArrayList;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:16
 * @Description: 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class merge2 {

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        //去除特殊情况
        if (intervals.isEmpty())
            return res;
        //重载比较，按照区间首排序
        intervals.sort((o1, o2) -> {
            if (o1.start != o2.start)
                return o1.start - o2.start;
            else
                return o1.end - o2.end;
        });
        //放入第一个区间
        res.add(intervals.get(0));
        int count = 0;
        //遍历后续区间，查看是否与末尾有重叠
        for (int i = 1; i < intervals.size(); i++) {
            Interval o1 = intervals.get(i);
            Interval origin = res.get(count);
            if (o1.start > origin.end) {
                res.add(o1);
                count++;
                //区间有重叠，更新结尾
            } else {
                res.remove(count);
                Interval s = new Interval(origin.start, o1.end);
                if (o1.end < origin.end)
                    s.end = origin.end;
                res.add(s);
            }
        }
        return res;
    }

}

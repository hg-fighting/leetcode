package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:36
 * @Description: 数据流中的中位数
 */
public class GetMedian {

    List<Integer> list = new ArrayList<Integer>();

    public void Insert(Integer num) {
        list.add(num);
        Collections.sort(list);
    }

    public Double GetMedian() {
        int size = list.size();
        if (size % 2 == 0) {
            return (list.get(size / 2 - 1) + list.get(size / 2)) / 2.0;
        } else {
            return list.get(size / 2) * 1.0;
        }
    }

    //小顶堆，元素数值都比大顶堆大
    private PriorityQueue<Integer> max = new PriorityQueue<>();

    //大顶堆，元素数值较小
    private PriorityQueue<Integer> min = new PriorityQueue<>((o1,
                                                              o2) -> o2.compareTo(o1));

    //维护两个堆，取两个堆顶部即与中位数相关
    public void Insert2(Integer num) {
        //先加入较小部分
        min.offer(num);
        //将较小部分的最大值取出，送入到较大部分
        max.offer(min.poll());
        //平衡两个堆的数量
        if (min.size() < max.size()) {
            min.offer(max.poll());
        }
    }

    public Double GetMedian2() {
        //奇数个
        if (min.size() > max.size()) {
            return (double) min.peek();
        } else
        //偶数个
        {
            return (double) (min.peek() + max.peek()) / 2;
        }
    }


}

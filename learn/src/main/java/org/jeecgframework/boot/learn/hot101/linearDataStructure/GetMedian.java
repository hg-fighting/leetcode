package org.jeecgframework.boot.learn.hot101.linearDataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: hao gang
 * @Date: 2026/2/25  8:36
 * @Description: 数据流中的中位数
 * 描述
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 * 示例1
 * 输入：
 * [5,2,3,4,1,6,7,0,8]
 * 复制
 * 返回值：
 * "5.00 3.50 3.00 3.50 3.00 3.50 4.00 3.50 4.00 "
 * 复制
 * 说明：
 * 数据流里面不断吐出的是5,2,3...,则得到的平均数分别为5,(5+2)/2,3...
 * 示例2
 * 输入：
 * [1,1,1]
 * 复制
 * 返回值：
 * "1.00 1.00 1.00 "
 * 备注：
 * 数据范围：数据流中数个数满足 1≤n≤1000  ，大小满足 1≤val≤1000
 */
public class GetMedian {

    //小顶堆，元素数值都比大顶堆大
    private final PriorityQueue<Integer> max = new PriorityQueue<>();

    //大顶堆，元素数值较小
    private final PriorityQueue<Integer> min = new PriorityQueue<>((o1,
                                                                    o2) -> o2.compareTo(o1));

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

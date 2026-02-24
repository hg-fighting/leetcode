package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:24
 * @Description: 合并K个有序链表
 */
public class mergeKLists {

    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        //小顶堆
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        //遍历所有链表第一个元素
        for (ListNode list : lists) {
            //不为空则加入小顶堆
            if (list != null) {
                pq.add(list);
            }
        }
        //加一个表头
        ListNode res = new ListNode(-1);
        ListNode head = res;
        //直到小顶堆为空
        while (!pq.isEmpty()) {
            //取出最小的元素
            ListNode temp = pq.poll();
            //连接
            head.next = temp;
            head = head.next;
            //每次取出链表的后一个元素加入小顶堆
            if (temp.next != null) {
                pq.add(temp.next);
            }
        }
        //去掉表头
        return res.next;
    }
}

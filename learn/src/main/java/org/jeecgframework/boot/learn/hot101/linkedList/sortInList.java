package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  11:06
 * @Description: 单链表的排序
 * 描述
 * 给定一个节点数为n的无序单链表，对其按升序排序。
 * 数据范围：0≤n≤100000
 * 要求：空间复杂度 O(n)，时间复杂度 O(nlogn)
 */
public class sortInList {

    public ListNode sortInList(ListNode head) {
        ArrayList<Integer> nums = new ArrayList<>();
        ListNode p = head;
        //遍历链表，将节点值加入数组
        while (p != null) {
            nums.add(p.val);
            p = p.next;
        }
        p = head;
        //对数组元素排序
        Collections.sort(nums);
        //遍历数组
        for (Integer num : nums) {
            //将数组元素依次加入链表
            p.val = num;
            p = p.next;
        }
        return head;
    }
}


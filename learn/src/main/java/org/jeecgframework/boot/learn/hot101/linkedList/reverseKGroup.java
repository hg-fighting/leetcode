package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:12
 * @Description: k个一组翻转链表
 * 描述
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 数据范围： 0≤n≤2000 ， 1≤k≤2000 ，链表中每个元素都满足 0≤val≤1000
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 */
public class reverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = prev.next;
            ListNode next = end.next;
            end.next = null;
            prev.next = new reverseList().reverseList(start);
            start.next = next;
            prev = start;
            end = prev;
        }
        return dummy.next;
    }
}

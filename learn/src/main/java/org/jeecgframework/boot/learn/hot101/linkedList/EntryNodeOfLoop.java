package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:40
 * @Description: 链表中环的入口节点
 * 描述
 * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 * 数据范围： n≤10000，1<=结点值<=10000
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 */
public class EntryNodeOfLoop {

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) {
            return null;
        }
        ListNode entry = pHead;
        while (entry != slow) {
            entry = entry.next;
            slow = slow.next;
        }
        return entry;
    }

}

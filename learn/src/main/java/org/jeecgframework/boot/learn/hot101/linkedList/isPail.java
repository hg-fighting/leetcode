package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  11:11
 * @Description: 判断链表是否为回文链表
 * 描述
 * 给定一个链表，请判断该链表是否为回文结构。
 * 回文是指该字符串正序逆序完全一致。
 * 数据范围： 链表节点数 0≤n≤105，链表中每个节点的值满足∣val∣≤107
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class isPail {

    /**
     * 双指针法
     */
    public boolean isPail(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = dummyNode;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHalf = slow.next;
        slow.next = null;
        secondHalf = new reverseList().reverseList(secondHalf);
        while (head != null && secondHalf != null) {
            if (head.val != secondHalf.val) {
                return false;
            }
            head = head.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }
}

package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:51
 * @Description: 删除链表的倒数第 N 个节点
 * 描述
 * 给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针
 * 例如，
 * 给出的链表为: 1→2→3→4→5, n= 2.
 * 删除了链表的倒数第 n 个节点之后,链表变为1→2→3→5.
 * 数据范围： 链表长度 0≤n≤1000，链表中任意节点的值满足 0≤val≤100
 * 要求：空间复杂度 O(1)，时间复杂度 O(n)
 * 备注：
 * 题目保证 n 一定是有效的
 */
public class removeNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = dummyNode;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyNode.next;
    }

}

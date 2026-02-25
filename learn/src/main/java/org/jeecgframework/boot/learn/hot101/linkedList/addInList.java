package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:59
 * @Description: 两个链表相加
 * 描述
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 数据范围：0≤n,m≤1000000，链表任意值 0≤val≤9
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class addInList {

    public ListNode addInList(ListNode head1, ListNode head2) {
        // write code here
        head1 = new reverseList().reverseList(head1);
        head2 = new reverseList().reverseList(head2);
        int carry = 0;
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while (head1 != null || head2 != null) {
            int a1 = head1 == null ? 0 : head1.val;
            int a2 = head2 == null ? 0 : head2.val;
            int sum = a1 + a2 + carry;
            head.next = new ListNode(sum % 10);
            head = head.next;
            if (head1 != null) {
                head1 = head1.next;
            }
            if (head2 != null) {
                head2 = head2.next;
            }
            carry = sum / 10;
        }
        if (carry > 0) {
            head.next = new ListNode(carry);
        }
        return new reverseList().reverseList(temp.next);
    }
}

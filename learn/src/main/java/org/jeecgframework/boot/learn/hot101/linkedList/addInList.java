package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:59
 * @Description: 两个链表相加
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

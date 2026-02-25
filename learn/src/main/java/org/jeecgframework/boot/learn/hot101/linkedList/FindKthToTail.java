package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:49
 * @Description: 链表中倒数第k个节点
 * 描述
 * 输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
 * 如果该链表长度小于k，请返回一个长度为 0 的链表。
 * 数据范围：0≤n≤105，0≤ai≤109，0≤k≤109
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class FindKthToTail {

    /**
     * 双指针法
     */
    public ListNode findKthToTail(ListNode head, int k) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        ListNode fast = dummyNode;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
            if (fast == null) {
                return null;
            }
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.next;
    }
}

package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  13:20
 * @Description: 删除排序链表中的重复元素-II
 * 描述
 * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 * 例如：
 * 给出的链表为1→2→3→3→4→4→5, 返回1→2→5.
 * 给出的链表为1→1→1→2→3, 返回2→3.
 * 数据范围：链表长度 0≤n≤1000，链表中任意节点的值满足 ∣val∣≤1000
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class deleteDuplicates2 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                ListNode temp = cur.next.next;
                while (temp != null && temp.val == cur.next.val) {
                    temp = temp.next;
                }
                cur.next = temp;
            } else {
                cur = cur.next;
            }
        }
        return dummyNode.next;
    }

}

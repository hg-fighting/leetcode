package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  13:18
 * @Description: 删除排序链表中的重复元素-I
 * 描述
 * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 * 例如：
 * 给出的链表为1→2→3→3→4→4→5, 返回1→2→5.
 * 给出的链表为1→1→1→2→3, 返回2→3.
 * 数据范围：链表长度 0≤n≤1000，链表中每个节点的值满足 ∣val∣≤1000
 * 要求：空间复杂度 O(n)，时间复杂度 O(n)
 */
public class deleteDuplicates1 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}

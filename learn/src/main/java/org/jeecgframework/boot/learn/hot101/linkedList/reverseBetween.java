package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  9:54
 * @Description: 链表内指定区间反转
 * 描述
 * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)，空间复杂度 O(1)。
 * 例如：
 * 给出的链表为 1→2→3→4→5→NULL, m=2,n=4,
 * 返回 1→4→3→2→5→NULL.
 * 数据范围： 链表长度 0<size≤1000，0<m≤n≤size，链表中每个节点的值满足 ∣val∣≤1000
 * 进阶：时间复杂度 O(n)，空间复杂度 O(1)
 */
public class reverseBetween {

    public static void main(String[] args) {
        // 测试用例
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reverseBetween reverseBetween = new reverseBetween();
        ListNode result = reverseBetween.reverseBetween1(head, 2, 4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    /**
     * 双指针法
     */
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode slow = dummyNode;
        for (int i = 0; i < m - 1; i++) {
            slow = slow.next;
        }
        ListNode fast = slow;
        for (int i = 0; i < n - m + 1; i++) {
            fast = fast.next;
        }
        ListNode pre = slow.next;
        ListNode remain = fast.next;
        slow.next = null;
        fast.next = null;
        new reverseList().reverseList(pre);
        slow.next = fast;
        pre.next = remain;
        return dummyNode.next;
    }

    /**
     * 一次遍历法
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for (int i = 0; i < n - m; i++) {
            ListNode nextTemp = curr.next;
            curr.next = nextTemp.next;
            nextTemp.next = prev.next;
            prev.next = nextTemp;
        }
        return dummy.next;
    }

}

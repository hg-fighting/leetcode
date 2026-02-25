package org.jeecgframework.boot.learn.hot101.linkedList;

import org.jeecgframework.boot.learn.hot101.basic.ListNode;

/**
 * @Author: hao gang
 * @Date: 2026/2/24  10:53
 * @Description: 两个链表的第一个公共节点
 * 描述
 * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 * 例如，输入{1,2,3},{4,5},{6,7}时，两个无环的单向链表的结构如下图所示：
 * 可以看到它们的第一个公共结点的结点值为6，所以返回结点值为6的结点。
 * 输入描述：
 * 输入分为是3段，第一段是第一个链表的非公共部分，第二段是第二个链表的非公共部分，第三段是第一个链表和第二个链表的公共部分。 后台会将这3个参数组装为两个链表，并将这两个链表对应的头节点传入到函数FindFirstCommonNode里面，用户得到的输入只有pHead1和pHead2。
 * 返回值描述：
 * 返回传入的pHead1和pHead2的第一个公共结点，后台会打印以该节点为头节点的链表。
 */
public class FindFirstCommonNode {

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1;
        ListNode l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null ? pHead2 : l1.next);
            l2 = (l2 == null ? pHead1 : l2.next);
        }
        return l1;
    }

}

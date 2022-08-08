package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/9 16:09<br/>
 *
 * @author xkunchen<br />
 */



import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *    	[剑指 Offer II 021]删除链表的倒数第 n 个结点
 */
public class II021removeNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }
    //快慢指针
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    public static void main(String[] args) {
        ListNode root=new ListNode();
        root.val=1;
        ListNode node1=root;

        for (int i = 2; i < 10; i++) {
            ListNode node=new ListNode();
            node.val=i;
            node1.next=node;
            node1=node;
        }
        II021removeNthNodeFromEndOfList i=new II021removeNthNodeFromEndOfList();
        i.removeNthFromEnd3(root,3);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

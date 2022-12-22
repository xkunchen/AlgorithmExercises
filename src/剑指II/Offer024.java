package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/22 18:24<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Stack;

/**
 *    	反转链表
 *    https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class Offer024 {
    public ListNode reverseList(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        Stack<ListNode> stack=new Stack();
        ListNode node=head;
        stack.add(node);
        while (node.next!=null){
            node=node.next;
            stack.add(node);
        }
        ListNode root = stack.pop();
        ListNode indexNode=root;
        while (!stack.isEmpty()){
            indexNode.next=stack.peek();
            indexNode=stack.pop();
        }
        indexNode.next=null;
        return root;
    }

    public ListNode reverseList2(ListNode head) {
        if (head==null||head.next==null){
            return head;
        }
        ListNode node=head;
        ListNode pre=null;
        ListNode current=head;
        while (node.next!=null){
            //目的是为了node改变后不改变当前值
            current=node;
            //遍历后
            node=node.next;
            current.next=pre;
            pre=current;
        }
        node.next=current;
        return node;
    }

    public static void main(String[] args) {
        ListNode root =new ListNode(1);
        root.next=new ListNode(2);
        root.next.next=new ListNode(3);
        root.next.next.next=new ListNode(4);
        root.next.next.next.next=new ListNode(5);
        Offer024 offer022 = new Offer024();
        offer022.reverseList2(root);
    }
}

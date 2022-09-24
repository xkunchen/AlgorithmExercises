package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/8/18 10:12<br/>
 *
 * @author xkunchen<br />
 */

import 剑指offer突击.II021removeNthNodeFromEndOfList;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 *    	[剑指 Offer 06]从尾到头打印链表	74.9%	Easy	0.0%
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Offer006 {
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }

    public int[] reversePrint2(ListNode head) {
        if (head==null){
            return new int[0];
        }
        ListNode nextNode = head.next;
        head.next=null;
        ListNode nextNextNode = null;
        int size = 1;
        while (nextNode != null) {
            nextNextNode=nextNode.next;
            nextNode.next=head;
            head=nextNode;
            nextNode=nextNextNode;
            size++;
        }

        int[] print = new int[size];
        int i=0;
        while (head != null) {
            print[i++] = head.val;
            head = head.next;
        }
        return print;
    }
    public static void main(String[] args) {
        ListNode root=new ListNode(1);
        Offer006 i=new Offer006();
        i.reversePrint2(root);
    }

}

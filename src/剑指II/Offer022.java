package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/22 17:52<br/>
 *
 * @author xkunchen<br />
 */

/**
 * 链表中倒数第k个节点
 *
 */
//给定一个链表: 1->2->3->4->5, 和 k = 2.

//给定一个链表: 1->2, 和 k = 1.
//给定一个链表: 1, 和 k = 1.

public class Offer022 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        //快慢指针
        ListNode indexNode=head;
        int index=1;
        while (indexNode.next!=null){
            if (index>=k){
                head=head.next;
            }
            index++;
            indexNode=indexNode.next;

        }
        return head;
    }

    public static void main(String[] args) {
        ListNode root =new ListNode(1);
        root.next=new ListNode(2);
        Offer022 offer022 = new Offer022();
        offer022.getKthFromEnd(root,1);
    }
}

package 剑指II;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *    [剑指 Offer 25]合并两个排序的链表
 */
public class Offer025 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null){
            return l2;
        }
        if (l2==null){
            return l1;
        }
        ListNode listNode=null;
        ListNode root=null;
        if (l1.val>l2.val){
            listNode=l2;
            root=l2;
            l2=l2.next;
        }else {
            listNode=l1;
            root=l1;
            l1=l1.next;
        }

        while (true){
            if (l1==null){
                listNode.next=l2;
                break;
            }
            if (l2==null){
                listNode.next=l1;
                break;
            }
            if (l1.val>l2.val){
                listNode.next=l2;
                l2=l2.next;
            }else {
                listNode.next=l1;
                l1=l1.next;
            }
            listNode=listNode.next;
        }
        return root;
    }

    public static void main(String[] args) {
        Offer025 o=new Offer025();
        ListNode root =new ListNode(1);
        root.next=new ListNode(2);
        root.next.next=new ListNode(4);
        ListNode root2 =new ListNode(1);
        root2.next=new ListNode(3);
        root2.next.next=new ListNode(4);
        o.mergeTwoLists(root,root2);
    }
}

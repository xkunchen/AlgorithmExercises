package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/20 15:15<br/>
 *
 * @author xkunchen<br />
 */

/**
 *
 */
public class Offer018 {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode next=head;
        ListNode pre=null;
        while (next!=null){
            if (next.val==val){
                if (pre==null){
                    head=head.next;
                }else {
                    pre.next=next.next;
                }
                break;
            }
            pre=next;
            next=next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Offer018 offer018 = new Offer018();
        ListNode root = new ListNode(-3);
        root.next=new ListNode(5);
        root.next.next=new ListNode(-99);
        offer018.deleteNode(root,-3);
    }
}

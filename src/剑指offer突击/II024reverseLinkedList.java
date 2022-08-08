package 剑指offer突击;


/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/10 16:13<br/>
 *
 * @author xkunchen<br />
 */
public class II024reverseLinkedList {
    //迭代
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public ListNode reverseList3(ListNode head) {
        //使用头插法
        ListNode dummy = new ListNode(0);
        ListNode p = dummy, cur = head;
        while(cur != null){
            //从head摘下一个头
            ListNode t = cur;
            cur = cur.next;     //cur移到下一个
            t.next = p.next;    //头插法插入
            p.next = t;
        }
        return dummy.next;
    }
    public ListNode reverseList2(ListNode head) {
        ListNode ans = null;
        for (ListNode x = head; x != null; x = x.next) {
            ans = new ListNode(x.val,ans);
        }
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
        II024reverseLinkedList i=new II024reverseLinkedList();
        i.reverseList3(root);
    }
}

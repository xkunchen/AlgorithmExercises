package 精选top;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/8/8 18:21<br/>
 *
 * @author xkunchen<br />
 */

import java.util.List;

/**
 *    	[2]两数相加
 *    	https://leetcode.cn/problems/add-two-numbers/
 */

/**
 * 总结：本题两个难点在于最后一个相加值的处理，还有进位处理
 */
class ListNode {
    int val;
    ListNode next;ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public static ListNode buildListNode(int[] arr){
        ListNode listNode=new ListNode();
        ListNode nextNode=listNode;
        for (int i:arr){
            ListNode node = new ListNode(i);
            nextNode.next=node;
            nextNode=node;
        }
        return listNode.next;
    }
}
public class Top002addTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int addOne=0;
        int isEnd=0;
        ListNode listNode = new ListNode();
        ListNode nextNode=listNode;
        while (l1!=null||l2!=null||isEnd==1){
            int sum = (l1!=null?l1.val:0) + (l2!=null?l2.val:0) + addOne;
            if (sum>=10){
                addOne=1;
                sum-=10;
            }else {
                addOne=0;
            }
            ListNode node=new ListNode(sum);
            nextNode.next=node;
            nextNode=node;
            l1 = l1!=null ? l1.next:l1;
            l2 = l2!=null ? l2.next:l2;
            if (l1==null&&l2==null){
                break;
            }
            if (l1==null&&l2==null){
                isEnd++;
            }
        }
        if (addOne>0){
            nextNode.next=new ListNode(addOne);
        }
        return listNode.next;
    }

    public static void main(String[] args) {
        Top002addTwoNumbers t=new Top002addTwoNumbers();
        t.addTwoNumbers(ListNode.buildListNode(new int[]{9,9,9,9,9,9,9}),ListNode.buildListNode(new int[]{9,9,9,9}));
    }
}

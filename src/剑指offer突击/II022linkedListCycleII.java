package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/10 16:08<br/>
 *
 * @author xkunchen<br />
 */



import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *   	[剑指 Offer II 022]链表中环的入口节点
 */

/**
 * 设 环的长度为A,慢指针在入环的时候快指针在环中的位置B(取值范围0到A-1),
 *     当快慢指针相遇时 [慢指针在环中走了C] ,有
 *          C % A = ( B + 2C) % A,等价于
 *          An + C = B + 2C,合并得
 *          C = An - B
 *          当 n=1 时 , 0 <= C < A
 *      故 慢指针在第一圈必定能和快指针相遇
 */

/**
 * 解释：为何慢指针第一圈走不完一定会和快指针相遇：
 *      首先，第一步，快指针先进入环
 *      第二步：当慢指针刚到达环的入口时，快指针此时在环中的某个位置(也可能此时相遇)
 *      第三步：设此时快指针和慢指针距离为x，若在第二步相遇，则x = 0；
 *      第四步：设环的周长为n，那么看成快指针追赶慢指针，需要追赶n-x；
 *      第五步：快指针每次都追赶慢指针1个单位，设慢指针速度1/s，快指针2/s，那么追赶需要(n-x)s
 *      第六步：在n-x秒内，慢指针走了n-x单位，因为x>=0，则慢指针走的路程小于等于n，即走不完一圈就和快指针相遇
 */

/**
 * 2x-(l+1)=x
 * x=l+1
 */
public class II022linkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<ListNode>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }
    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(5);
        ListNode root=new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,listNode))));
        listNode.next=root;
        II022linkedListCycleII i=new II022linkedListCycleII();
        i.detectCycle2(root);
    }

}

package 剑指offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/19 19:14<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/reorder-list/
 * ✔	[剑指 Offer II 026]重排链表
 */
public class II026ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}

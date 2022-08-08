package 剑指offer突击;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/29
 **/


import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 剑指 Offer 09. 用两个栈实现队列
 */
public class YongLiangGeZhanShiXianDuiLieLcof {
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    public YongLiangGeZhanShiXianDuiLieLcof() {
        stack1 = new LinkedList<Integer>();
        stack2 = new LinkedList<Integer>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        // 如果第二个栈为空
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) {
            return -1;
        } else {
            int deleteItem = stack2.pop();
            return deleteItem;
        }
    }
}

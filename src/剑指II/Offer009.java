package 剑指II;

import java.util.Stack;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/8/18 18:35<br/>
 *
 * @author xkunchen<br />
 */
public class Offer009 {
}
class CQueue {

    Stack<Integer> stack1 = null;
    Stack<Integer> stack2 = null;
    public CQueue() {
        stack1= new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()){
            if (stack1.isEmpty()){
                return -1;
            }
            while (!stack1.isEmpty()){
                stack2.add(stack1.pop());
            }
        }
        return stack2.pop();
    }
}

package 栈;

import java.util.Stack;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
// 有效字符串需满足：
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
// 注意空字符串可被认为是有效字符串。
// 示例 1:
// 输入: "()"
//输出: true
// 示例 2:
// 输入: "()[]{}"
//输出: true
// 示例 3:
// 输入: "(]"
//输出: false
// 示例 4:
// 输入: "([)]"
//输出: false
// 示例 5:
// 输入: "{[]}"
//输出: true
// Related Topics 栈 字符串
// 👍 1773 👎 0


/**
 * 8月21第一次练习
 */
public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses v=new ValidParentheses();
        System.out.println(v.isValid("{}{}[{()}](())"));
    }
    public boolean isValid(String s) {
        //创建个栈
        Stack<Character> stack=new Stack<Character>();
        char[] chars = s.toCharArray();
        if (chars.length==1){
            return false;
        }
        for (int i=0;i<chars.length;i++){
            //如果是符号右边符对比，并进行出栈，否则入栈
            if(stack.size()>0){
                if (chars[i]=='}'){
                    if (stack.peek()!='{'){
                        return false;
                    }
                    stack.pop();
                }else if (chars[i]==')'){
                    if (stack.peek()!='('){
                        return false;
                    }
                    stack.pop();
                }else if (chars[i]==']'){
                    if (stack.peek()!='['){
                        return false;
                    }
                    stack.pop();
                }else{
                    stack.push(chars[i]);
                }
            }else{
                if(chars[i]=='}'||chars[i]==')'||chars[i]==']'){
                    return false;
                }
                stack.push(chars[i]);
            }

        }
        if (stack.size()!=0){
            return false;
        }
        return true;
    }
}

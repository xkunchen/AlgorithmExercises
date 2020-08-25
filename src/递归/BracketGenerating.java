package 递归;
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 示例：
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
//
// Related Topics 字符串 回溯算法
// 👍 1255 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 * 8月24日第一次练习，
 */
public class BracketGenerating {
    public List<String> generateParenthesis(int n) {
        List<String> allResult=new ArrayList<String>();
        //解法思路，加括号，进行递归
        addBracket("", 0, 0,n,allResult);
        return allResult;
    }

    private void addBracket(String s, int left, int right, int n,List<String> allResult) {
        //两个结果，当左括号和右括号都相等时，且右括号等于n返回
        //当左括号大于n时，结束
        //第一个值应该为（
        //结束条件
        if (left<right||left>n){
            return;
        }
        if (n==right){
            System.out.println(s);
            allResult.add(s);
            return;
        }
        //处理逻辑
        //继续递归
        addBracket(s+"(",left+1,right,n,allResult);
        addBracket(s+")",left,right+1,n,allResult);
    }

    public static void main(String[] args) {
        BracketGenerating b=new BracketGenerating();
        System.out.println(b.generateParenthesis(3));
    }
}

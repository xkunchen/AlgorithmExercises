package 高级搜索;
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
// 示例 1：
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 示例 2：
//输入：n = 1
//输出：["()"]
// 提示：
// 1 <= n <= 8
// Related Topics 字符串 回溯算法
// 👍 1645 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result=new ArrayList<>();
        dealData(result,n,"",0,0);
        return result;

    }
    //条件左括号和右括号一半一半,并且左括号不可以小于右括号
    public void dealData(List<String> result,int n,String currentStr,int leftNum,int rightNum){
        //递归终止条件
        if (leftNum>n||rightNum>n||leftNum<rightNum){
            return;
        }
        if (currentStr.length()==2*n){
            result.add(currentStr);
            return;
        }
        dealData(result,n,currentStr+"(",leftNum+1,rightNum);
        dealData(result,n,currentStr+")",leftNum,rightNum+1);
    }

    public static void main(String[] args) {
        GenerateParentheses g=new GenerateParentheses();
        g.generateParenthesis(3);
    }
}

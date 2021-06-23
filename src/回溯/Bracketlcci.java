package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/21
 **/

import 高级搜索.GenerateParentheses;

import java.util.ArrayList;
import java.util.List;
//括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
// 说明：解集不能包含重复的子集。
// 例如，给出 n = 3，生成结果为：
//[
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
// Related Topics 字符串 回溯算法
// 👍 71 👎 0
/**
 * https://leetcode-cn.com/problems/bracket-lcci/
 * [面试题 08.09]括号
 * 回溯算法
 */
public class Bracketlcci {
    public List<String> generateParenthesis(int n) {
        List<String> returnList =new ArrayList<>();
        generateParentheses(n,returnList,"",0,0);
        return returnList;
    }
    void generateParentheses(int n,List<String> returnList,String currentStr,int left,int right){
        //剪枝
        if (left<right||left>n){
            return;
        }
        if (right==n){
            returnList.add(currentStr);
        }
        generateParentheses(n,returnList,currentStr+"(",left+1,right);
        generateParentheses(n,returnList,currentStr+")",left,right+1);
    }

    public static void main(String[] args) {
        Bracketlcci b=new Bracketlcci();
        b.generateParenthesis(3);
    }

}

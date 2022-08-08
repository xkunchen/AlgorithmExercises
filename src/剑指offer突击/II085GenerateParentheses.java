package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/22 10:38<br/>
 *
 * @author xkunchen<br />
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 *    [剑指 Offer II 085]生成匹配的括号
 */
public class II085GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> returnList=new ArrayList<>();
        dealData(returnList,"",n,n);
        return returnList;
    }

    private void dealData(List<String> returnList, String s, int leftNum,int rightNum) {
        if (leftNum<0||rightNum<0||rightNum<leftNum){
            return;
        }
        if (leftNum==0&&rightNum==0){
            returnList.add(s);
        }
        dealData(returnList,s+"(",leftNum-1,rightNum);
        dealData(returnList,s+")",leftNum,rightNum-1);
    }

    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public static void main(String[] args) {
        II085GenerateParentheses i=new II085GenerateParentheses();
        i.generateParenthesis(3);
    }
}

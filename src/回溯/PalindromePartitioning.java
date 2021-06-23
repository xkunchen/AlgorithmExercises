package 回溯;
//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
// 返回 s 所有可能的分割方案。
// 示例:
// 输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//]
// Related Topics 深度优先搜索 动态规划 回溯算法
// 👍 448 👎 0

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 分割回文串
 *            https://leetcode-cn.com/problems/palindrome-partitioning/
 *             回溯
 */

/**
 * 分析：判断是否为回文,在哪里回溯，一个List<String> current，例子，当s为“aab”时，当current
 * 的赋值应该是这样的：
 * ['a']->判断'a'->['a','a']->判断b->['a','a','b']->回溯到['a']->判断'ab',不是，回溯到[]->判断'aa'-> ["aa"]->判断'b'->['aa','b']
 */
public class PalindromePartitioning {

    public List<List<String>> partition3(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        char[] charArray = s.toCharArray();
        // 预处理
        // 状态：dp[i][j] 表示 s[i][j] 是否是回文
        boolean[][] dp = new boolean[len][len];
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (charArray[left] == charArray[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }

        Deque<String> stack = new ArrayDeque<>();
        dfs(s, 0, len, dp, stack, res);
        return res;
    }

    private void dfs(String s, int index, int len, boolean[][] dp, Deque<String> path, List<List<String>> res) {
        if (index == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < len; i++) {
            if (dp[index][i]) {
                path.addLast(s.substring(index, i + 1));
                dfs(s, i + 1, len, dp, path, res);
                path.removeLast();
            }
        }
    }

    public List<List<String>> partition2(String s) {
        List<List<String>> result=new ArrayList<>();
        ArrayList<String> current=new ArrayList<>();
        char[] charArray= s.toCharArray();
        deal2(result,charArray,current,0);
        return  result;
    }

    private void deal2(List<List<String>> result, char[] charArray , List<String> current  , int currentIndex) {
        String preStr="";
        if (currentIndex==charArray.length){
            result.add(current);
        }
        for (int i = currentIndex; i < charArray.length; i++) {
            preStr+=charArray[i];
            if (judge(preStr)){
                List<String> newList =new ArrayList<>();
                newList.addAll(current);
                newList.add(preStr);
                deal2(result,charArray,newList,i+1);
            }

        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result=new ArrayList<>();
        ArrayList<String> current=new ArrayList<>();
        deal(result,s,current,s.charAt(0)+"",0);
        return  result;
    }
    public void deal(List<List<String>> result, String s, ArrayList<String> current, String currentString, int index){
        if (index>s.length()-1){
            return;
        }
        if (index==s.length()-1){
            if(judge(currentString)){
                current.add(currentString);
                ArrayList<String> clone = (ArrayList<String>)current.clone();
                result.add(clone);
                current.remove(currentString);
            }
        }else{
            String nextString=currentString;
            for (int i = index; i < s.length(); i++) {
                //如果是回文串，那么就
                if(judge(nextString)){
                    current.add(nextString);
                    if (i<s.length()-1){
                        deal(result,s,current,""+s.charAt(i+1),i+1);
                    }else{
                        ArrayList<String> clone = (ArrayList<String>)current.clone();
                        result.add(clone);
                    }
                    current.remove(nextString);
                }
                if (i<s.length()-1) nextString=nextString+s.charAt(i+1);
            }
        }
    }
    //判断是否是回文串
    boolean judge(String s){
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning p=new PalindromePartitioning();
        p.partition3("aab");
    }
}

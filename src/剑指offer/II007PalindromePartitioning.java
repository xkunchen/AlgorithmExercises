package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/4/22 16:40<br/>
 *
 * @author xkunchen<br />
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 *    [剑指 Offer II 086]分割回文子字符串
 */
public class II007PalindromePartitioning {
    public  List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> result = new ArrayList<>();
        if (len == 0) {
            //return (String[][])result.toArray();
        }
        char[] charArray = s.toCharArray();
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
        for (int i = 0; i < len; i++) {
            List<String> list = new ArrayList<>();
            boolean isAdd=false;
            for (int j = 0; j < len; j++) {
                if (j+i<len&&dp[j][j+i]){
                    list.add(s.substring(j,j+i+1));
                    j=j+i;
                    isAdd=true;
                }else{
                    list.add(s.substring(j,j+1));
                }
            }
            if (isAdd)  result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        II007PalindromePartitioning i=new II007PalindromePartitioning();
        i.partition("google");
    }
}

package 动态规划;
//给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
// 若这两个字符串没有公共子序列，则返回 0。
// 示例 1:
// 输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace"，它的长度为 3。
// 示例 2:
// 输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc"，它的长度为 3。
// 示例 3:
// 输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0。
// 提示:
// 1 <= text1.length <= 1000
// 1 <= text2.length <= 1000
// 输入的字符串只含有小写英文字符。
// Related Topics 动态规划
// 👍 226 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 最长公共子序列
 *      https://leetcode-cn.com/problems/longest-common-subsequence/
 *  教训:一开始就弄错了动态方程，还有二维数组优化成一维数组不能是f（n-1）（m-1）+opera=f(n)(m)只能上下，不能对斜
 */
public class LongestCommonSubsequence {
    //错误解法
    public int longestCommonSubsequence(String text1, String text2) {
        //规则f(n)(n)=f(n-1)(n)+单前是不是相同
        int maxValue=0;
        char[] text1Char = text1.toCharArray();
        char[] text2Char = text2.toCharArray();
        int arr[]=new int[text2.length()];
        arr[0]=text1Char[0]==text2Char[0]?1:0;
        if (text1.equals("")||text2.equals("")){
            return 0;
        }
        for (int j = 0; j < text2Char.length; j++) {
            if(text2Char[j]==text1Char[0]){
                arr[j]=1;
                while ( j < text2Char.length-1){
                    j++;
                    arr[j]=arr[j-1];
                }
            }
        }
        for (int i=1;i<text1Char.length;i++){
            if (text1Char[i]==text2Char[0]) arr[0]=1;
            for (int j = 1; j < text2Char.length; j++) {
                if(text2Char[j]==text1Char[i]){
                    arr[text2.length()-1-j]=arr[j-1]+1;
                }else{
                    arr[j]=Math.max(arr[j-1],arr[j]);
                }
            }
        }
        return arr[text2.length()-1];
    }
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 获取两个串字符
                char c1 = text1.charAt(i), c2 = text2.charAt(j);
                if (c1 == c2) {
                    // 去找它们前面各退一格的值加1即可
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    //要么是text1往前退一格，要么是text2往前退一格，两个的最大值
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        LongestCommonSubsequence l=new LongestCommonSubsequence();
        System.out.println(l.longestCommonSubsequence2("bsbininm","jmjkbkjkv"));
    }
}

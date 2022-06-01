package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/24 11:39<br/>
 *
 * @author xkunchen<br />
 */

/**
 *  https://leetcode-cn.com/problems/longest-common-subsequence/
 *     	[剑指 Offer II 095]最长公共子序列
 */
public class II095LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] text1Chars = text1.toCharArray();
        char[] text2Chars = text2.toCharArray();
        int[][] arrs=new int[text1Chars.length+1][text2Chars.length+1];
        for (int i = 0; i < text1Chars.length; i++) {
            for (int j = 0; j < text2Chars.length; j++) {
                if (text1Chars[i]==text2Chars[j]){
                    arrs[i+1][j+1]=arrs[i][j]+1;
                }else{
                    arrs[i+1][j+1]=Math.max(arrs[i+1][j],arrs[i][j+1]);
                }
            }
        }
        return  arrs[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        II095LongestCommonSubsequence i=new II095LongestCommonSubsequence();
        i.longestCommonSubsequence("abcde","ace");
    }
}

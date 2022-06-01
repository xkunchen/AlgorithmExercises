package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/19 19:38<br/>
 *
 * @author xkunchen<br />
 */

/**
 * 	[剑指 Offer II 097]子序列的数目
 * https://leetcode-cn.com/problems/distinct-subsequences/
 */
public class II097distinctSubsequences {
    public int numDistinct(String s, String t) {
        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();
        return dealData(schars,tchars,0,0,"");
    }

    private int dealData(char[] schars, char[] tchars, int tIndex,int index, String currentStr) {
        if (tIndex==tchars.length){
            return 1;
        }
        int sum=0;
        for (int i = index; i < schars.length; i++) {
            if (schars[i]==tchars[tIndex]){
                sum+=dealData(schars,tchars,tIndex+1,i+1,currentStr+schars[i]);
            }
        }
        return sum;
    }

    public int numDistinct2(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        II097distinctSubsequences i=new II097distinctSubsequences();
        System.out.println( i.numDistinct2("babgbag","bag"));
    }
}

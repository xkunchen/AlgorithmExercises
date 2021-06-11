package 动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/11
 **/
public class OnesAndZeroes {
    /**
     * 1. https://leetcode-cn.com/problems/ones-and-zeroes/
     *         一和零
     *         动态规划,三维数组
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][]arr=new int[2][strs.length];
        for (int i = 0; i < strs.length; i++) {
            char[] charArray = strs[i].toCharArray();
            int zeroLength=0;
            int oneLength=0;
            for (char c:charArray){
                if (c=='0'){
                    zeroLength++;
                }else {
                    oneLength++;
                }
            }
            arr[0][i]=zeroLength;
            arr[1][i]=oneLength;
        }
        int[][] arr1=new int[m+1][n+1];
        return 0;
    }
    public int findMaxForm2(String[] strs, int m, int n) {
        int length = strs.length;
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i - 1]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[length][m][n];
    }

    public int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

    public static void main(String[] args) {
        OnesAndZeroes o=new OnesAndZeroes();
        String[] stringArr=new String[]{"10", "0001", "111001", "1", "0"};
        o.findMaxForm2(stringArr,5,3);
    }
}

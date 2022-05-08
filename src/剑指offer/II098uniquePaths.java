package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/8 9:46<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/unique-paths/
 *    [剑指 Offer II 098]路径的数目
 */
public class II098uniquePaths {
    public int uniquePaths(int m, int n) {
        if (m<=1||n<=1){
            return 1;
        }
        int arr[][]=new int[m+1][n+1];
        for (int i = 1; i <=n; i++) {
            arr[1][i]=1;
        }
        for (int i = 1; i <=m; i++) {
            arr[i][1]=1;
        }
        for (int i = 2; i <=m; i++) {
            for (int j = 2; j <=n; j++) {
                arr[i][j]=arr[i-1][j]+arr[i][j-1];
            }
        }
        return arr[m][n];
    }

    public static void main(String[] args) {
        II098uniquePaths i=new II098uniquePaths();
        i.uniquePaths(7,3);
    }
}

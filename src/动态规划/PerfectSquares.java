package 动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/11
 **/

/**
 * https://leetcode-cn.com/problems/perfect-squares/
 *            完全平方数
 *           动态规划
 */
public class PerfectSquares {
    public int numSquares(int n) {
        if (n==0) return 0;
        boolean[][] arr=new boolean[n+1][n+1];
        int[] arr1=new int[n+1];
        int end=1;
        for ( ; end <= n; end++) {
            if (end*end<n){
                arr1[end]=end*end;
                arr[1][end*end]=true;
            }else if (end*end==n){
                return 1;
            }else break;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 1; k < end; k++) {
                    if (arr1[k]>j) continue;
                    if (arr[i][j]||(arr[i][j-arr1[k]]==true)){
                        arr[i+1][j]=true;
                        if (j==n)
                            return i+1;
                        continue;
                    }
                }
            }
        }
        return n;
    }

    public int numSquares2(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }
    //数学
    public int numSquares3(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }
        if (checkAnswer4(n)) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;
    }

    // 判断是否为完全平方数
    public boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }

    // 判断是否能表示为 4^k*(8m+7)
    public boolean checkAnswer4(int x) {
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }
    public static void main(String[] args) {
        PerfectSquares p=new PerfectSquares();
        p.numSquares2(12);
    }
}


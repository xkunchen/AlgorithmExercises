package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/31 17:49<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/paint-house/
 *   	[剑指 Offer II 091]粉刷房子
 */
public class II091paintHouse {
    public int minCost(int[][] costs) {
        int n=costs.length;
        int[][] arr = new int[n][3];
        arr[0][0] = costs[0][0];
        arr[0][1] = costs[0][1];
        arr[0][2] = costs[0][2];
        for (int i = 1; i < n; i++) {
            arr[i][0]=Math.min(arr[i-1][1],arr[i-1][2])+costs[i][0];
            arr[i][1]=Math.min(arr[i-1][0],arr[i-1][2])+costs[i][1];
            arr[i][2]=Math.min(arr[i-1][0],arr[i-1][1])+costs[i][2];
        }
        return Math.min(Math.min(arr[n-1][0],arr[n-1][1]),arr[n-1][2]);
    }

    public static void main(String[] args) {
        II091paintHouse i=new II091paintHouse();
        i.minCost(new int[][]{{17,2,17},{16,16,5},{14,3,19}});

    }
}

package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/26 11:44<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *    	[剑指 Offer II 099]最小路径之和
 */
public class II099minimumPathSum {
    public int minPathSum(int[][] grid) {
        int xlength = grid.length;
        int ylength = grid[0].length;
        int[][] arr=new int[xlength+1][ylength+1];
        for (int i = 0; i < xlength; i++) {
            arr[i+1][1]=arr[i][1]+grid[i][0];
        }
        for (int i = 0; i < ylength; i++) {
            arr[1][i+1]=arr[1][i]+grid[0][i];
        }
        for (int i = 2; i < xlength+1; i++) {
            for (int j = 2; j < ylength+1; j++) {
                arr[i][j]=grid[i-1][j-1]+Math.min(arr[i][j-1],arr[i-1][j]);
            }
        }
        return arr[xlength][ylength];
    }

    public static void main(String[] args) {
        int[][] arr=new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        II099minimumPathSum i=new II099minimumPathSum();
        i.minPathSum(arr);
    }
}

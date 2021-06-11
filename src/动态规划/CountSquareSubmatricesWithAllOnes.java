package 动态规划;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/5
 **/

/**
 * https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/
 *         统计全为 1 的正方形子矩阵
 *         动态规划
 *         第一遍 根据 最大正方形 关联解答
 */
public class CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        int arr[][]=new int[matrix.length][matrix[0].length];
        int count=0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]==0) continue;
                if (i==0||j==0){
                    arr[i][j]=1;
                }else  {
                    arr[i][j]+=Math.min(arr[i-1][j-1],Math.min(arr[i-1][j],arr[i][j-1]))+1;
                }
                count+=arr[i][j];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountSquareSubmatricesWithAllOnes c=new CountSquareSubmatricesWithAllOnes();
        int[][] arr=new int[][]{
                {0,1,1,1 },
                {1,1,1,1 },
                {0,1,1,1 }
            };
        c.countSquares(arr);
    }
}

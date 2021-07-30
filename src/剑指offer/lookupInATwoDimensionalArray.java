package 剑指offer;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/7/29
 **/

/**
 * 剑指 Offer 04. 二维数组中的查找
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 */

/**
 * 总结：因为多个比较，所以先定死一个值，在一行中最大或最小
 */
public class lookupInATwoDimensionalArray {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            System.out.println(num);
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[][]=new int[][]{
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        lookupInATwoDimensionalArray l=new lookupInATwoDimensionalArray();
        l.findNumberIn2DArray(arr,17);
    }
}

package 二分查找;
//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
// 每行中的整数从左到右按升序排列。
// 每行的第一个整数大于前一行的最后一个整数。
// 示例 1:
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//输出: true
// 示例 2:
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//输出: false
// Related Topics 数组 二分查找
// 👍 254 👎 0
/**
 * 搜索二维矩阵
 *         https://leetcode-cn.com/problems/search-a-2d-matrix/
 *         二分查找
 */
public class SearchA2dMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length==0||matrix[0].length==0) return false;
        int leftArrLength=0;
        int leftFirstLength=0;
        int rightArrLength =matrix.length;
        int rightFirstLength=matrix[0].length;
        while (leftArrLength<matrix.length&&leftArrLength<=rightArrLength&&leftFirstLength<=rightFirstLength){
            int minData=matrix[(leftArrLength+rightArrLength)/2][(leftFirstLength+rightFirstLength)/2];
            if (minData==target){
                return true;
            }else if (minData>target){
                if (matrix[(leftArrLength+rightArrLength)/2][0]==target) return true;
                if (matrix[(leftArrLength+rightArrLength)/2][0]<target){
                    leftArrLength=(leftArrLength+rightArrLength)/2;
                    rightArrLength=(leftArrLength+rightArrLength)/2;
                    rightFirstLength=(leftFirstLength+rightFirstLength)/2-1;
                }else{
                    rightArrLength=(leftArrLength+rightArrLength)/2-1;
                }
            }else{
                if (matrix[(leftArrLength+rightArrLength)/2][matrix[0].length-1]==target) return true;
                if (matrix[(leftArrLength+rightArrLength)/2][matrix[0].length-1]>target){
                    leftArrLength=(leftArrLength+rightArrLength)/2;
                    rightArrLength=(leftArrLength+rightArrLength)/2;
                    leftFirstLength=(leftFirstLength+rightFirstLength)/2+1;
                }else{
                    leftArrLength=(leftArrLength+rightArrLength)/2+1;
                }
            }
        }
        return  false;
    }

    public static void main(String[] args) {
        SearchA2dMatrix s=new SearchA2dMatrix();
        //int[][] arr=new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50},{51,54,78,89}};
        int[][] arr=new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        s.searchMatrix(arr,3);
    }
}

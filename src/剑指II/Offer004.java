package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/8/10 11:42<br/>
 *
 * @author xkunchen<br />
 */

/**
 *    	[剑指 Offer 04]二维数组中的查找	40.0%	Medium	0.0%
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 */
public class Offer004 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int yLength=matrix.length;
        if (yLength==0){
            return false;
        }
        int xLength=matrix[0].length;
        if (xLength==0){
            return false;
        }
        //行最大，列最小
        int startX=xLength-1;
        int startY=0;
        while (startX>=0&&startY<yLength){
            if (matrix[startY][startX]>target){
                startX--;
            }else if (matrix[startY][startX]<target){
                startY++;
            }else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Offer004 f=new Offer004();
        f.findNumberIn2DArray(new int[][]{
                {1,4,7,11,15},
                {2,6,8,12,19},
                {3,7,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}},5);
    }
}

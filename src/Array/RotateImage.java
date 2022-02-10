package Array;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/2/8 10:45<br/>
 *
 * @author xkunchen<br />
 */

/**
 * 旋转图像
 * https://leetcode-cn.com/problems/rotate-image/
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }
}

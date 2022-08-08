package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/6/16 11:41<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/max-area-of-island/
 *    	[剑指 Offer II 105]岛屿的最大面积
 */

/**
 * 总结：一开始想用动态规划去做，一开始就走错方向，本题没有子结果，需要遍历然后进行标记
 */
public class II105maxAreaOfIsland {
    //广度优先遍历
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                int cur = 0;
                Queue<Integer> queuei = new LinkedList<Integer>();
                Queue<Integer> queuej = new LinkedList<Integer>();
                queuei.offer(i);
                queuej.offer(j);
                while (!queuei.isEmpty()) {
                    int cur_i = queuei.poll(), cur_j = queuej.poll();
                    if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                        continue;
                    }
                    ++cur;
                    grid[cur_i][cur_j] = 0;
                    int[] di = {0, 0, 1, -1};
                    int[] dj = {1, -1, 0, 0};
                    for (int index = 0; index != 4; ++index) {
                        int next_i = cur_i + di[index], next_j = cur_j + dj[index];
                        queuei.offer(next_i);
                        queuej.offer(next_j);
                    }
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

    //深度优先搜索 + 栈
    public int maxAreaOfIsland2(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                int cur = 0;
                Deque<Integer> stacki = new LinkedList<Integer>();
                Deque<Integer> stackj = new LinkedList<Integer>();
                stacki.push(i);
                stackj.push(j);
                while (!stacki.isEmpty()) {
                    int cur_i = stacki.pop(), cur_j = stackj.pop();
                    if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
                        continue;
                    }
                    ++cur;
                    grid[cur_i][cur_j] = 0;
                    int[] di = {0, 0, 1, -1};
                    int[] dj = {1, -1, 0, 0};
                    for (int index = 0; index != 4; ++index) {
                        int next_i = cur_i + di[index], next_j = cur_j + dj[index];
                        stacki.push(next_i);
                        stackj.push(next_j);
                    }
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        II105maxAreaOfIsland i=new II105maxAreaOfIsland();
        int[][] arr= new int[][]{{0,1,1,0,0,0,0,1}
                                ,{0,1,0,0,0,0,0,1}
                                ,{0,1,1,0,1,0,0,0}
                                ,{0,1,0,0,1,1,0,0}};
        i.maxAreaOfIsland2(arr);
    }
}

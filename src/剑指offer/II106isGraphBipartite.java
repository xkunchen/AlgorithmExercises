package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/6/16 16:29<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/is-graph-bipartite/
 *    	[剑指 Offer II 106]二分图
 */
public class II106isGraphBipartite {
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color;
    private boolean valid;

    //深度优先遍历
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        valid = true;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);
        for (int i = 0; i < n && valid; ++i) {
            if (color[i] == UNCOLORED) {
                dfs(i, RED, graph);
            }
        }
        return valid;
    }

    public void dfs(int node, int c, int[][] graph) {
        color[node] = c;
        int cNei = c == RED ? GREEN : RED;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == UNCOLORED) {
                dfs(neighbor, cNei, graph);
                if (!valid) {
                    return;
                }
            } else if (color[neighbor] != cNei) {
                valid = false;
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[][] aa=new int[][]{{1,3},{0,2},{1,3},{0,2}};
        II106isGraphBipartite i=new II106isGraphBipartite();
        i.isBipartite(aa);
    }
}

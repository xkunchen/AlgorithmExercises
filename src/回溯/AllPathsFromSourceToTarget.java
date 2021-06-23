package 回溯;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/6/18
 **/

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/all-paths-from-source-to-target/
 *         所有可能的路径
 *         回溯算法
 */
public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return solve(graph, 0);
    }

    public List<List<Integer>> solve(int[][] graph, int node) {
        int N = graph.length;
        List<List<Integer>> ans = new ArrayList();
        if (node == N - 1) {
            List<Integer> path = new ArrayList();
            path.add(N-1);
            ans.add(path);
            return ans;
        }

        for (int nei: graph[node]) {
            for (List<Integer> path: solve(graph, nei)) {
                path.add(0, node);
                ans.add(path);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] arrs=new int[][]{{4,3,1},{3,2,4},{3},{4},{}};
        AllPathsFromSourceToTarget a=new AllPathsFromSourceToTarget();
        a.allPathsSourceTarget(arrs);
    }
}

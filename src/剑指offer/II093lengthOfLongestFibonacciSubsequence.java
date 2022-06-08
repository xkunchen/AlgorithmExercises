package 剑指offer;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/6/2 10:58<br/>
 *
 * @author xkunchen<br />
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://leetcode-cn.com/problems/length-of-longest-fibonacci-subsequence/
 *    	[剑指 Offer II 093]最长斐波那契数列	58.6%
 */
// 示例 1：
//输入: arr = [1,2,3,4,5,6,7,8]
//输出: 5
//解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
// 示例 2：
//输入: arr = [1,3,7,11,12,14,18]
//输出: 3
//解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。

/**
 * 这道题败在动态方程的存储，需要的信息有前两个，所以需要j * N + k 来确定唯一值
 */
public class II093lengthOfLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Set<Integer> S = new HashSet();
        for (int x: A) {
            S.add(x);
        }

        int ans = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                int x = A[j], y = A[i] + A[j];
                int length = 2;
                while (S.contains(y)) {
                    // x, y -> y, x+y
                    int tmp = y;
                    y += x;
                    x = tmp;
                    ans = Math.max(ans, ++length);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }
    public int lenLongestFibSubseq2(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap();
        for (int i = 0; i < N; ++i){
            index.put(A[i], i);
        }
        Map<Integer, Integer> longest = new HashMap();
        int ans = 0;

        for (int k = 0; k < N; ++k){
            for (int j = 0; j < k; ++j) {
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j) {
                    // Encoding tuple (i, j) as integer (i * N + j)
                    int cand = longest.getOrDefault(i * N + j, 2) + 1;
                    longest.put(j * N + k, cand);
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }

    public int lenLongestFibSubseq3(int[] arr) {
        int max = 0, n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) { // 得到值与下标的映射
            map.put(arr[i], i);
        }
        int[][] dp = new int[n - 1][n]; // i一定小于j，所以第一维大小可以设为n-1
        for(int i = 0; i < n; i++){
            for(int k = i + 2; k < n; k++){ // k至少比i大2，因为中间要放下j
                int j = map.getOrDefault(arr[k] - arr[i], -1); // 获取满足arr[i]+arr[j]=arr[k]的j，不满足时j=-1
                if(i < j && j < k) { // j在i和k之间
                    dp[j][k] = dp[i][j] + 1;
                    max = Math.max(max, dp[j][k]);
                }
            }
        }
        return max == 0 ? 0 : max + 2;
    }
    public int lenLongestFibSubseq4(int[] arr) {
        int max = 0, n = arr.length;
        int[][] dp = new int[n - 1][n]; // i一定小于j，所以第一维大小可以设为n-1
        for(int k = 2; k < n; k++){
            int i = 0, j = k - 1;
            while(i < j){ // 确定arr[k]，控制双指针i和j寻找斐波那契关系
                int sum = arr[i] + arr[j];
                if(sum == arr[k]) {
                    dp[j][k] = dp[i][j] + 1;
                    max = Math.max(max, dp[j][k]);
                    i++;
                    j--;
                }
                if(sum < arr[k]) i++;
                if(sum > arr[k]) j--;
            }
        }
        return max == 0 ? 0 : max + 2;
    }


    public static void main(String[] args) {
        II093lengthOfLongestFibonacciSubsequence i=new II093lengthOfLongestFibonacciSubsequence();
        i.lenLongestFibSubseq2(new int[]{1,2,3,4,5,6,7,8});
    }
}

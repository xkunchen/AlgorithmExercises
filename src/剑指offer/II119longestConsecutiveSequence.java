package 剑指offer;


import java.util.*;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/8 11:27<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 * ✔[剑指 Offer II 119]最长连续序列
 */
public class II119longestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }
        int longestStreak = 0;
        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    // 哈希表 + 并查集
    public int longestConsecutive2(int[] nums) {
        if(nums.length < 2) {
            return nums.length;
        }
        UnionFind uf = new UnionFind(nums);
        for(int num : nums) {
            uf.union(num, num + 1);
        }
        int max = 1;
        for(int num : nums){
            max = Math.max(max, uf.find(num) - num + 1);
        }
        return max;
    }

    private class UnionFind{
        Map<Integer, Integer> parents;
        public UnionFind(int[] nums){
            this.parents = new HashMap<>();
            for(int num : nums) {
                this.parents.put(num, num); // 初始单元素集合，parent指向自己
            }
        }
        // 带路径压缩的查找
        public int find(int x){
            if(parents.get(x) == x) {
                return x;
            }
            parents.put(x, find(parents.get(x)));
            return parents.get(x);
        }
        // 直接求并。本题只在最初将相邻数字组为两元素集合时用到union，因此无需更高级的按秩/大小求并。
        public void union(int x, int y){
            if(parents.containsKey(y)) {
                parents.put(x, y); // 以y为x的parent
            }
        }
    }

    public static void main(String[] args) {
        II119longestConsecutiveSequence i=new II119longestConsecutiveSequence();
        i.longestConsecutive2(new int[]{0,3,7,2,5,8,4,6,0,1});
    }

}

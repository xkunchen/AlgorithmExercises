package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/5 17:32<br/>
 *
 * @author xkunchen<br />
 */


import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/contiguous-array/
 *    [剑指 Offer II 011]0 和 1 个数相同的子数组
 */

/**
 * 前缀和+哈希表 屡试不爽 总结一下最近用到这个思想的的题目：
 * 连续数组
 * 连续的子数组和
 * 元素和为目标值的子矩阵数量
 * 和为k的子数组
 * 每个元音包含最长的子字符串
 * 统计「优美子数组」 LCP 19：秋叶收藏集
 * 矩形区域不超过 K 的最大数值和
 */
/**
 * 解题思路:
 * 本题的意思是找到具有相同数量 0，1 的最长连续子数组，也就是子数组中要同时具有 0 和 1，并且 0 和 1 的数量是相同的, 并且是最长的子数组(好像是废话...）。
 * 给出一组测试用例：[0,0,0,1,1,1,0,0,1], 用指针 i 扫描一遍数组, 来观察每个位置上的可能情况
 * [0,0,0,1,1,1,0,0,1]
 * -i                 不符合条件, 0 1 数量不同
 * [0,0,0,1,1,1,0,0,1]
 * -  i               不符合条件, 0 1 数量不同
 * [0,0,0,1,1,1,0,0,1]
 * -    i             不符合条件, 0 1 数量不同
 * [0,0,0,1,1,1,0,0,1]
 * -      i           此时与前一个 0 构成 [0,1] 满足条件, 此时的子数组长度为 2
 * [0,0,0,1,1,1,0,0,1]
 * -        i         此时 [1,4] 区间满足条件, 子数组长度为 4
 * [0,0,0,1,1,1,0,0,1]
 * -          i       此时 [0,5] 区间满足条件, 子数组长度为 5
 * [0,0,0,1,1,1,0,0,1]
 * -            i     不符合条件
 * [0,0,0,1,1,1,0,0,1]
 * -              i   此时 [0,7] 区间满足条件, 子数组长度为 8
 * [0,0,0,1,1,1,0,0,1]
 * -                ^ 不符合条件
 * <p>
 * 当遍历完整个数组后, 我们可以知到 [0, 7] 区间是符合条件的最长连续子数组。肉眼很容易辨别哪个区间为最长连续子数组, 但是计算机如
 * 何能知道？答案是计算区间和, 如果让 0 变为 -1, 那么当区间内 -1 和 1 的数量相同时, 这区间和就是 0 。如此, 似乎可以使用前缀和
 * 来解决这个问题, 当计算的前缀和为 0 时, 就说明[0,i] 区间是满足题目要求的一个子数组。不过这样肯定会出现错误, 因为最终的结果不
 * 一定是从 0 下标开始子数组。例如这个用例 [0,0,1,0,0,0,1,1], 答案应该是 nums[2,7]区间长度为6的数组, 可以用上面的方式进行计算
 * 前缀和：
 * -[0,0,1,0,0,0,1,1]
 * - i               preSum = -1, (用 -1 替换 0);
 * -[0,0,1,0,0,0,1,1]
 * -   i   i       i  preSum = -2
 * -[0,0,1,0,0,0,1,1]
 * -     i           preSum = -1
 * -[0,0,1,0,0,0,1,1]
 * -       i         preSum = -2
 * -[0,0,1,0,0,0,1,1]
 * -         i       preSum = -3
 * -[0,0,1,0,0,0,1,1]
 * -           i     preSum = -4
 * -[0,0,1,0,0,0,1,1]
 * -             i   preSum = -3
 * -[0,0,1,0,0,0,1,1]
 * -               i preSum = -2
 * 观察可以发现, 当前缀和相同时, 前一个 i1 后面一个位置开始一直到 i2 的区间是满足题目要求的子数组, 即 nums[i1+1...i2] 满足题
 * 目要求, 并且 i2 - i1 = 子数组长度, 所以我们只需要计算出 nums[0...n-1] 每一个位置的前缀和, 一旦发现当前的计算出的前缀和在
 * 之前已经出现过, 就用当前的索引 i2 - 之前的索引 i1 即可求出本次得到的子数组长度,。因为需要求得的是最长连续子数组，所以应用一
 * 个变量 maxLength 来保存每一次计算出的子数组长度, 取较大值。也因为, 我们需要保存每一个位置的前缀和, 并且还需要通过前缀和找到
 * 相应位置的索引, 所以，使用 HashMap 来存放 {前缀和:索引}, 在上面例子中我们通过观察得到了 i2 - i1 = 数组长度, 但是有一个很隐
 * 蔽的缺陷, 即当整个数组即为答案时, i2 = nums.length - 1, i1 = 0 此时得到的数组长度为 nums.length - 1 这显然是错误的。因此
 * , 为了避免这个错误, 我们初始将 Map 中添加一对 {前缀和:索引}, 即 put(0,-1), 0代表前一个不存在的元素前缀和为 0, -1 代表不存
 * 在元素的索引。
 * 当定义了这些条件后, 我们开始用指针 i 遍历数组nums[0...nums.length - 1] 位置上的每一个元素。
 * 一、用变量 sum 来纪录[0...i]区间的和:
 * -   1.当 nums[i] == 0 时, sum += -1
 * -   2.当 nums[i] == 1 时, sum += 1
 * 二、接着判断 sum 是否已经存在于 HashMap 中:
 * -   1. 如果存在, 则取出 sum 所对应的索引 j, 那么 nums[j+1,i] 就是一个满足答案的子区间, 用
 * -      maxLength = Math.max(maxLengnth, i - j); 来纪录最长子数组。
 * -   2. 如果不存在, 则将 {sum:i} 存放在 HashMap 中作为纪录。
 * 当数组遍历完毕时, maxLength 中保存的即为答案数组的长度。
 * <p>
 */
public class II011ContiguousArray {
    public int findMaxLength(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }
    public int findMaxLength2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : nums[i];
            if (map.containsKey(sum)) {
                int j = map.get(sum);
                maxLength = Math.max(maxLength, i - j);
            } else {
                map.put(sum, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        II011ContiguousArray i=new II011ContiguousArray();
        i.findMaxLength2(new int[]{0,0,1,0,0,0,1,1});
    }
}

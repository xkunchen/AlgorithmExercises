package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/5 11:32<br/>
 *
 * @author xkunchen<br />
 */

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 *    [剑指 Offer II 010]和为 k 的子数组
 */
public class II010SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int len=nums.length;
        int left=0; int right=0;
        int result=0;
        int count=0;
        while(right<len){
            result+=nums[right];
            while (result > k) {
                result -= nums[left];
                left++;
            }
            if(result==k&&right>=left){
                count++;
            }
            right++;
        }
        return  count;
    }

    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum3(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap< Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


    public static void main(String[] args) {
        II010SubarraySumEqualsK k=new II010SubarraySumEqualsK();
        k.subarraySum2(new int[]{2,3,4,3,6,9},9);
    }
}

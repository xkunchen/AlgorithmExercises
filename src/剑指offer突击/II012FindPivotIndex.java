package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/5 17:35<br/>
 *
 * @author xkunchen<br />
 */


import java.util.Arrays;

/**
 *   [剑指 Offer II 012]左右两边子数组的和相等
 * https://leetcode-cn.com/problems/find-pivot-index/
 */
public class II012FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        if(total-nums[0]==0) return 0;
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }

    public int pivotIndex2(int[] nums) {
        int sum=0,presum=0;
        for(int i=0;i<nums.length;i++)
            sum+=nums[i];

        if(sum-nums[0]==0) return 0;

        for(int i=0;i<nums.length-1;i++){
            presum+=nums[i];
            if(presum == sum-presum-nums[i+1])
                return i+1;
        }

        return -1;
    }

    public static void main(String[] args) {
        II012FindPivotIndex i=new II012FindPivotIndex();
        i.pivotIndex2(new int[]{1,7,3,6,5,6});
    }
}

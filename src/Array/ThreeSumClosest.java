package Array;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/2/7 15:41<br/>
 *
 * @author xkunchen<br />
 */

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/3sum-closest/
 * 最接近的三数之和
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 排序
        int MinSum=nums[0]+nums[1]+nums[2];
        if( MinSum >= target){
            return MinSum;
        }
        for (int i = 0; i < nums.length ; i++) {
            int L = i+1;
            int R = nums.length-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum-target == 0){
                    return sum;
                }
                else if (sum-target < 0) L++;
                else if (sum-target > 0) R--;
                MinSum=Math.abs(MinSum-target)>Math.abs(sum-target)?sum:MinSum;
            }
        }
        return MinSum;
    }

    public static void main(String[] args) {
        ThreeSumClosest t=new ThreeSumClosest();
        System.out.println(t.threeSumClosest(new int[]{-1,2,1,-4},1));
    }
}

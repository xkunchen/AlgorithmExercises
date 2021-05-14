package 分治;
//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
// 示例 1:
// 输入: [3,2,3]
//输出: 3
// 示例 2:
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// Related Topics 位运算 数组 分治算法
// 👍 748 👎 0

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    //hash法
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        int MaxData=nums[0];
        int MaxValue=1;
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i])==null){
                map.put(nums[i],1);
            }else {
                Integer newData=map.get(nums[i]).intValue()+1;
                map.put(nums[i],newData);
                if(MaxValue<newData){
                    MaxValue=newData;
                    MaxData=nums[i];
                }
            }
        }
        return MaxData;
    }
    //投票法
    public int majorityElement2(int[] nums) {
        //注意条件大于n/2，所以可以用这个
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        MajorityElement m=new MajorityElement();
        int nums[]={6,5,5,5,2,5,5,7,5,2};
        m.majorityElement2(nums);
    }
}

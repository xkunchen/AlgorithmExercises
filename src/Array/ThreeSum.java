package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 注意：答案中不可以包含重复的三元组。
// 示例：
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// Related Topics 数组 双指针
// 👍 2518 👎 0
public class ThreeSum {
    //排序加双指针
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> returnList=new ArrayList<List<Integer>>();
        //进行排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            if (i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left=i+1;
            int right= nums.length-1;
            while (left<right){
                if (nums[left]+nums[right]+nums[i]<0){
                    left++;
                }else if (nums[left]+nums[right]+nums[i]>0){
                    right--;
                }else{
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    returnList.add(list);
                    while (left<right){
                        if (nums[left]==nums[left+1]){
                            left++;
                        }else {
                            left++;
                            break;
                        }
                    }

                }
            }
        }
        return  returnList;
    }
    //简洁代码
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr={0,0,0,0};
        ThreeSum t=new ThreeSum();
        t.threeSum(arr);
    }
}

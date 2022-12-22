package 剑指II;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/12/22 16:39<br/>
 *
 * @author xkunchen<br />
 */

/**
 *    	[剑指 Offer 21]调整数组顺序使奇数位于偶数前面	65.0%	Easy	0.0%
 *      https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 */
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4]
//注：[3,1,2,4] 也是正确的答案之一。
public class Offer021 {
    public int[] exchange(int[] nums) {
        //可以借鉴于快排
        int start=0;
        int end= nums.length-1;
        int temp=0;
        //如果
        while(start<end){
            if (nums[start]%2==0){
                while(start<end){
                    if (nums[end]%2==1){
                        temp=nums[start];
                        nums[start]=nums[end];
                        nums[end]=temp;
                        break;
                    }else {
                        end--;
                    }
                }
            }
            start++;
        }
        return nums;
    }

    public static void main(String[] args) {
        Offer021 o=new Offer021();
        o.exchange(new int[]{1,2,3,4});
    }
}

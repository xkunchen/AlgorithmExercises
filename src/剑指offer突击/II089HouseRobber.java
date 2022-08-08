package 剑指offer突击;

/**
 * ClassName: xkunchen <br/>
 * Description: <br/>
 * date: 2022/5/5 16:23<br/>
 *
 * @author xkunchen<br />
 */

/**
 * https://leetcode-cn.com/problems/house-robber/
 *    [剑指 Offer II 089]房屋偷盗
 */
public class II089HouseRobber {
    public int rob(int[] nums) {
        int[] arr=new int[nums.length+1];
        arr[1]=nums[0];
        for (int i = 2; i <= nums.length; i++) {
            arr[i]=Math.max(arr[i-1],arr[i-2]+nums[i-1]);
        }
        return arr[nums.length];
    }

    //优化
    public int rob2(int[] nums) {
        //上一个
        int lastNum=nums[0];
        //前一个
        int priorNum=0;
        int changeNum=0;
        for (int i = 2; i <= nums.length; i++) {
            changeNum=lastNum;
            lastNum=Math.max(lastNum,priorNum+nums[i-1]);
            priorNum=changeNum;
        }
        return lastNum;
    }

    public static void main(String[] args) {
        II089HouseRobber i=new II089HouseRobber();
        i.rob2(new int[]{1,2,3,1});
    }
}

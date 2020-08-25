package Array;
//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
// 示例:
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
// 说明:
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
// Related Topics 数组 双指针
// 👍 705 👎 0
public class MoveZeroes {
    //8月25日第一次提交
    public void moveZeroes(int[] nums) {
        int low=0;//定义位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=0){
                nums[low++]=nums[i];
            }
        }
        for (int i = low; i < nums.length; i++) {
            nums[i]=0;
        }
    }

    public static void main(String[] args) {
        MoveZeroes m=new MoveZeroes();
        int nums[]={0,1,0,3,12};
        m.moveZeroes(nums);
        System.out.println(nums);
    }
}

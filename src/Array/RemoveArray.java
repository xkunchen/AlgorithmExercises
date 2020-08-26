package Array;

public class RemoveArray {
    //8月26日第二次提交
    public int removeDuplicates(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int i=0; //快指针
        int j=0;//慢指针
        while (i<nums.length){
            if (nums[i]!=nums[j]){
                nums[++j]=nums[i];
            }
            i++;
        }
        return j+1;
    }

    public static void main(String[] args) {
        RemoveArray r=new RemoveArray();
        int[] arr={0,0,1,1,1,2,2,3,3,4};
        r.removeDuplicates(arr);
    }
}

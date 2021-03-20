package Array;
//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
// 说明：你不能倾斜容器，且 n 的值至少为 2。
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
// 示例：
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49
// Related Topics 数组 双指针
// 👍 1759 👎 0
    /*双指针代表的是 可以作为容器边界的所有位置的范围。在一开始，双指针指向数组的左右边界，
    表示 数组中所有的位置都可以作为容器的边界，因为我们还没有进行过任何尝试。在这之后，
        我们每次将 对应的数字较小的那个指针 往 另一个指针 的方向移动一个位置，就表
        示我们认为 这个指针不可能再作为容器的边界了。*/

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left=0;//左边开始
        int right=height.length-1;//右边开始
        //首先定义一个最大值结果返回
        int maxArea=Math.min(height[right],height[left])*(right-left);
        while (left<right){
            //如果左边大于右边，右边应该向左移动，因为这时如果以右边为基准，左边移动的话的区域都是单前区域的子区域
            if(height[left]<height[right]){
                left++;
            }else {
                right--;
            }
            int curentMaxArea=Math.min(height[right],height[left])*(right-left);
            if (curentMaxArea>maxArea){
                maxArea=curentMaxArea;
            }
        }
        return maxArea;
    }
    //第二遍
    public int maxArea2(int[] height) {
        int maxArea=0;
        //定义左指针
        int left=0;
        //定义右指针
        int right=height.length-1;
        while (left<right){
            //如果左指针对应数组的值，小于右边的值，那么就往
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
            int curentMaxArea=Math.min(height[right],height[left])*(right-left);
            if (curentMaxArea>maxArea){
                maxArea=curentMaxArea;
            }
        }
       return maxArea;
    }
    public static void main(String[] args) {
        ContainerWithMostWater c=new ContainerWithMostWater();
        int height[]={1,8,6,2,5,4,8,3,7};
        System.out.println(c.maxArea(height));
    }
}

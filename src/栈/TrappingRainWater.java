package 栈;
//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。
// 示例:
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6
// Related Topics 栈 数组 双指针
// 👍 1599 👎 0

import java.util.Stack;

/**
 * 接雨水
 */
/*
    8月31日第一次提交
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int maxArex=0;
        Stack<Integer> stack=new Stack<>();
        for (int i = 0; i < height.length; i++) {
            int pre=0;
            if (!stack.isEmpty()&&stack.peek()<height[i]){
                pre = stack.pop();
                if (!stack.isEmpty()){

                }
            }
            stack.push(height[i]);
        }
        return maxArex;
    }
    //动态规划，思想就是引用以前的结果进行下一步预测，
    // 这题用一个数组存储结果，首先就是right数组，表明在单前从右往左是最大的
    // left数组表明从左往右是最大的。

    public int trap6(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }
    public static void main(String[] args) {
        TrappingRainWater tr=new TrappingRainWater();
        int[] arr={0,1,0,3,2,0,0,1,3};
        tr.trap6(arr);
    }
}

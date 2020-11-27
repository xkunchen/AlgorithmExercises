package 广度与深度优先遍历;
//您需要在二叉树的每一行中找到最大的值。
// 示例：
//输入:
//          1
//         / \
//        3   2
//       / \   \
//      5   3   9
//输出: [1, 3, 9]
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 106 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 在每个树行中找最大值
 *          https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/#/description
 *          树
 *          第一遍
 */
public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        if (root==null){
            return new ArrayList<Integer>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result=new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            int MaxData=Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                MaxData=poll.val>MaxData?poll.val:MaxData;
                if (poll.left!=null)queue.add(poll.left);
                if (poll.right!=null)queue.add(poll.right);
            }
            result.add(MaxData);
        }
        return result;
    }
}

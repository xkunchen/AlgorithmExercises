package 字典树和并查集;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/3/20
 **/
//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
// 示例：
//二叉树：[3,9,20,null,null,15,7],
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 返回其层次遍历结果：
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// Related Topics 树 广度优先搜索
// 👍 698 👎 0
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
public class BinaryTreeLevelOrderTraversal {
    /**
     * 1  二叉树的层序遍历
     *            https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     *             字典树和并查集
     *             第一遍
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        //广度优先遍历
        //返回总结果
        List<List<Integer>> resultAll=new ArrayList<>();
        if (root == null) {
            return resultAll;
        }
        //用一个队列进行遍历
        Queue<TreeNode> treeNodeQueue=new LinkedList<>();
        treeNodeQueue.add(root);
        while (!treeNodeQueue.isEmpty()){
            //遍历每一层
            List<Integer> oneResult=new ArrayList<>();
            int size = treeNodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = treeNodeQueue.poll();
                oneResult.add(poll.val);
                if (poll.left!=null){
                    treeNodeQueue.add(poll.left);
                }
                if (poll.right!=null){
                    treeNodeQueue.add(poll.right);
                }
            }
            resultAll.add(oneResult);
        }
        return  resultAll;
    }

}

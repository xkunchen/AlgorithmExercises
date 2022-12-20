package 字典树和并查集;
//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。
// 示例 1：
//输入：board = [["o","a","a","n"],
//              ["e","t","a","e"],
//              ["i","h","k","r"],
//              ["i","f","l","v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 示例 2：
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 提示：
// m == board.length
// n == board[i].length
// 1 <= m, n <= 12
// board[i][j] 是一个小写英文字母
// 1 <= words.length <= 3 * 104
// 1 <= words[i].length <= 10
// words[i] 由小写英文字母组成
// words 中的所有字符串互不相同
// Related Topics 字典树 回溯算法
// 👍 355 👎 0
/**
 * @Author: xkunchen
 * @Description:
 * @Data: 2021/3/26
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *https://leetcode-cn.com/problems/word-search-ii/
 * 单词搜索 II
 */
class TrieNode1 {
    HashMap<Character, TrieNode1> children = new HashMap<Character, TrieNode1>();
    String word = null;
    public TrieNode1() {}
}

class WordSearchII {
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode1 root = new TrieNode1();
        for (String word : words) {
            TrieNode1 node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode1 newNode = new TrieNode1();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, TrieNode1 parent) {
        Character letter = this._board[row][col];
        TrieNode1 currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
    public static void main(String[] args) {
        char[][] chars=new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words=new String[]{"oath","pea","eat","rain"};
        WordSearchII w=new WordSearchII();
        w.findWords(chars,words);
    }
}

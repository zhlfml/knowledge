package me.thomas.knowledge.algorithm.leetcode.design;

/**
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 * @author xinsheng2.zhao
 * @version Id: Trie.java, v 0.1 2022/10/13 15:07 xinsheng2.zhao Exp $
 */
public class Trie {

    private boolean word;

    private final Trie[] children; /* 理解为26叉数，使用数组表示 */

    public Trie() {
        children = new Trie[26];
    }

    public void insert(String word) {
        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (trie.children[index] == null) {
                trie.children[index] = new Trie();
            }
            trie = trie.children[index];
        }
        trie.word = true;
    }

    public boolean search(String word) {
        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (trie.children[index] == null) {
                return false;
            }
            trie = trie.children[index];
        }
        return trie.word;
    }

    public boolean startsWith(String prefix) {
        Trie trie = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';
            if (trie.children[index] == null) {
                return false;
            }
            trie = trie.children[index];
        }
        return true;
    }
}

package com.java.lib.tree.element;

import com.java.lib.tree.node.ITreeNode;
import com.java.lib.tree.node.TreeNode;

public class Tree implements ITree {
    private ITreeNode root;
    private int hashCode;

    public Tree() {
        this.root = new TreeNode();
        this.hashCode = 0;
    }

    @Override
    public void add(String word) {
        ITreeNode current = this.root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.getChild()[index] == null) {
                current.getChild()[index] = new TreeNode();
            }
            current = current.getChild()[index];
        }
        this.hashCode += word.hashCode();
        current.incrementVal();
        current.setIsEnd(true);
    }

    @Override
    public ITreeNode find(String word) {
        ITreeNode current = this.root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.getChild().length <= index || current.getChild()[index] == null) {
                return null;
            }
            current = current.getChild()[index];
        }
        if (current != null && current.getIsEnd()) {
            return current;
        }
        return null;
    }

    @Override
    public int wordCount() {
        return this.calcWordCount(this.root);
    }

    @Override
    public int nodeCount() {
        return this.calcTreeSize(this.root);
    }

    private int calcTreeSize(ITreeNode node) {
        if (node == null) {
            return 0;
        }
        int size = 0;
        for (int i = 0; i < 26; i++) {
            ITreeNode n = node.getChild()[i];
            if (n != null)
                size += this.calcTreeSize(n);
        }
        return (1 + size);
    }

    private int calcWordCount(ITreeNode root) {
        int result = 0;
        if (root.getIsEnd())
            result++;
        for (int i = 0; i < 26; i++) {
            ITreeNode n = root.getChild()[i];
            if (n != null)
                result += this.calcWordCount(n);
        }
        return result;
    }

    public String toString() {
        char[] words = new char[50];
        StringBuilder stringBuilder = new StringBuilder();
        this.appendWords(root, words, 0, stringBuilder);
        if (stringBuilder.toString().length() == 0)
            return "";
        return stringBuilder.toString().substring(1);
    }

    private void appendWords(ITreeNode root, char[] words, int pos, StringBuilder stringBuilder) {
        if (root == null) return;
        if (root.getIsEnd()) {
            stringBuilder.append("\n");

            for (int i = 0; i < pos; i++) {
                stringBuilder.append(words[i]);
            }
        }
        for (int i = 0; i < 26; i++) {
            ITreeNode n = root.getChild()[i];
            if (n != null) {
                words[pos] = (char) (i + 'a');
                this.appendWords(n, words, pos + 1, stringBuilder);
            }
        }
    }

    public int hashCode() {
        return this.hashCode;
    }

    public boolean equals(Object o) {
        if (o instanceof Tree) {
            Tree s = (Tree) o;
            if (this.nodeCount() != s.nodeCount())
                return false;
            if (this.wordCount() != s.wordCount())
                return false;
            return this.compareTree(this, s);
        } else {
            return false;
        }
    }

    private boolean compareTree(Tree p, Tree q) {
        String s1 = p.toString();
        String s2 = q.toString();
        if (s1.equals("") && s2.equals(""))
            return true;
        String[] strs1 = s1.split("\n");
        String[] strs2 = s2.split("\n");
        if (strs1.length != strs2.length)
            return false;
        for (String s : strs1) {
            ITreeNode node1 = p.find(s);
            ITreeNode node2 = q.find(s);
            if (node1.getVal() != node2.getVal())
                return false;
        }
        return true;
    }
}

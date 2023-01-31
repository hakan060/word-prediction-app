package com.java.lib.tree.node;

public class TreeNode implements ITreeNode {
    private int count;
    private boolean isEnd;
    private ITreeNode[] children;

    public TreeNode() {
        this.count = 0;
        this.isEnd = false;
        this.children = new ITreeNode[26];
    }

    public int getVal() {
        return this.count;
    }

    public void incrementVal() {
        this.count++;
    }

    @Override
    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
    public boolean getIsEnd() {
        return this.isEnd;
    }

    public ITreeNode[] getChild() {
        return this.children;
    }
}

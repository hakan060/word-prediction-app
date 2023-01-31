package com.java.lib.tree.node;

public interface ITreeNode {
    int getVal();
    void incrementVal();
    void setIsEnd(boolean isEnd);
    boolean getIsEnd();
    ITreeNode[] getChild();
}

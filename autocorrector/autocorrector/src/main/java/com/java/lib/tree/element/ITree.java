package com.java.lib.tree.element;

import com.java.lib.dictionary.IDictionaryCallback;
import com.java.lib.tree.node.ITreeNode;

public interface ITree extends IDictionaryCallback {
    ITreeNode find(String word);
    int wordCount();
    int nodeCount();

    String toString();
    int hashCode();
    boolean equals(Object obj);

}

package com.java.lib.tree.node;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

    @Test
    void getVal() {
        TreeNode node = new TreeNode();
        assertEquals(0, node.getVal());
    }

    @Test
    void incrementVal() {
        TreeNode node = new TreeNode();
        node.incrementVal();
        assertEquals(1, node.getVal());
    }

    @Test
    void getChild() {
        TreeNode node = new TreeNode();
        assertNotNull(node.getChild());
    }
}
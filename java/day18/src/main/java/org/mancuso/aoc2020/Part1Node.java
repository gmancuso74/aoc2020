package org.mancuso.aoc2020;

public class Part1Node implements TreeNode {
    Operation operation;
    TreeNode left;
    TreeNode right;
    Integer value;

    public Part1Node() {
    }

    public Part1Node(Operation op) {
        this.operation = op;
    }

    public Part1Node(Integer value) {
        this.value = value;
    }

    @Override
    public TreeNode getLeft() {
        return left;
    }

    @Override
    public void setLeft(TreeNode node) {
        this.left = node;
    }

    @Override
    public TreeNode getRight() {
        return right;
    }

    @Override
    public void setRight(TreeNode node) {
        this.right = node;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
}

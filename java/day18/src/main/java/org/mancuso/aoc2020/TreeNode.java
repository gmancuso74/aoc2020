package org.mancuso.aoc2020;

public interface TreeNode {

    public TreeNode getLeft();

    public void setLeft(TreeNode node);

    public TreeNode getRight();

    public void setRight(TreeNode node);

    public Operation getOperation();

    public void setOperation(Operation operation);

    public Integer getValue();

    public void setValue(Integer value);
}

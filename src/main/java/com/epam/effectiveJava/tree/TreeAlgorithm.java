package com.epam.effectiveJava.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeAlgorithm {
    public List<Integer> traverse(Tree tree) {
        List<Integer> result = new ArrayList<>();

        Tree leftTree = tree.getLeft();
        Tree rightTree = tree.getRight();

        if (Objects.nonNull(leftTree)) {
            result.addAll(traverse(leftTree));
        }
        if (Objects.nonNull(rightTree)) {
            result.addAll(traverse(rightTree));
        }

        result.add(tree.getValue());

        return result;
    }
}

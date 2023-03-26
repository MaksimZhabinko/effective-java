package com.epam.effectiveJava.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Tree {

    private int value;
    private Tree left;
    private Tree right;

    public Tree(int value) {
        this.value = value;
    }

}

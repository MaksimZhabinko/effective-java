package com.epam.effectiveJava.tree;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class TreeAlgorithmTest {
    @InjectMocks
    private TreeAlgorithm sut;

    @Test
    void shouldTraverseTree() {
        Tree root = new Tree(2,
                new Tree(1,
                        new Tree(10),
                        new Tree(50)),
                new Tree(18));

        List<Integer> expected = ImmutableList.of(10, 50, 1, 18, 2);

        List<Integer> actual = sut.traverse(root);

        assertThat(actual, is(expected));
    }
}
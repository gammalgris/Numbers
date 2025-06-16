/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * e-mail: kristian.kutin@arcor.de
 */

/*
 * This section contains meta informations.
 *
 * $Id$
 */

package jmul.math.vectors;


import java.util.HashMap;
import java.util.Map;

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.signs.Signs;
import jmul.math.vectors.nodes.IndexNode;
import jmul.math.vectors.nodes.NodesHelper;


/**
 * A helper class for managing index numbers.
 *
 * @author Kristian Kutin
 */
public class IndexSingletons {

    /**
     * A singleton for managing index numbers.
     */
    private static IndexRepository SINGLETON;

    /*
     * The static initializer.
     */
    static {

        SINGLETON = new IndexRepositoryImpl();
    }

    /**
     * The default constructor.
     */
    private IndexSingletons() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the next index to the specified index.
     *
     * @param index
     *        an index (i.e. a positive integer which is not zero)
     *
     * @return the next index
     */
    public static Number nextIndex(Number index) {

        return SINGLETON.nextIndex(index);
    }

    /**
     * Returns the first index.
     *
     * @return the first index
     */
    public static Number firstIndex() {

        return SINGLETON.firstIndex();
    }

    /**
     * Returns the default number base for index values.
     *
     * @return a default number base
     */
    public static int defaultNumberBase() {

        return SINGLETON.defaultNumberBase();
    }

}


/**
 * This itnerface describes a repository which manages index numbers.<br>
 * <br>
 * <i>Note:</i><br>
 * <i>This repository aims at conserving memory by reusing used index numbers.</>
 *
 * @author Kristian Kutin
 */
interface IndexRepository {

    /**
     * Returns the next index to the specified index.
     *
     * @param index
     *        an index (i.e. a positive integer which is not zero)
     *
     * @return the next index
     */
    Number nextIndex(Number index);

    /**
     * Returns the first index.
     *
     * @return the first index
     */
    Number firstIndex();

    /**
     * Returns the default number base for index values.
     *
     * @return a default number base
     */
    int defaultNumberBase();

}


/**
 * An implementation of an index repository.
 *
 * @author Kristian Kutin
 */
class IndexRepositoryImpl implements IndexRepository {

    /**
     * The constant contains the default number base for index numbers.
     */
    private final static int DEFAULT_NUMBER_BASE;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_NUMBER_BASE = 10;
    }

    /**
     * A reference to the first node.
     */
    private IndexNode firstNode;

    /**
     * A reference to the last node.
     */
    private IndexNode lastNode;

    /**
     * A map with shortcuts to existing index numbers.
     */
    private Map<Number, IndexNode> shortcuts;

    /**
     * The default constructor.
     */
    public IndexRepositoryImpl() {

        super();

        Number startIndex = createNumber(Signs.POSITIVE, DEFAULT_NUMBER_BASE, 1);

        this.firstNode = NodesHelper.createNode(startIndex);

        this.lastNode = firstNode;

        this.shortcuts = new HashMap<>();
        shortcuts.put(startIndex, firstNode);
    }

    /**
     * Returns the next index to the specified index.
     *
     * @param index
     *        an index (i.e. a positive integer which is not zero)
     *
     * @return the next index
     */
    @Override
    public Number nextIndex(Number index) {

        Number firstIndex = firstNode.index();

        if (firstIndex.isGreater(index)) {

            String message = String.format("An illegal index (%s) was specified!", index);
            throw new IllegalArgumentException(message);
        }

        IndexNode indexNode = findIndexOrEnlargeList(index);
        return indexNode.index();
    }

    /**
     * Enlarge the linked list up to the specified index.
     *
     * @param index
     *        an index number
     *
     * @return returns the index node with the next index
     */
    private IndexNode findIndexOrEnlargeList(Number index) {

        IndexNode indexNode = shortcuts.get(index);

        if (indexNode == null) {

            Number nextIndex = index.inc();
            return enlargeList(nextIndex);

        } else {

            return findNextIndexToTheRight(indexNode, index);
        }
    }

    /**
     * looks for the next index to the right or creates a new node to the right if necessary.
     *
     * @param indexNode
     *        the current index node
     * @param index
     *        the current index
     *
     * @return the next index node
     */
    private IndexNode findNextIndexToTheRight(IndexNode indexNode, Number index) {

        IndexNode rightNode = indexNode.rightNode();

        if (rightNode == null) {

            Number nextIndex = index.inc();
            rightNode = addNodeToTheRight(indexNode, nextIndex);
        }

        return rightNode;
    }

    /**
     * Add a node to the right of the specified node with the specified next index. The new node is linked with the
     * specified linked list. Updates the last node and the shortcuts accordingly.
     *
     * @param currentNode
     *        a node
     * @param nextIndex
     *        an index number
     *
     * @return the new node
     */
    private IndexNode addNodeToTheRight(IndexNode currentNode, Number nextIndex) {

        IndexNode rightNode = currentNode.rightNode();

        if (rightNode != null) {

            throw new IllegalArgumentException("Trying to append a node to the right although a right node exists!");
        }

        rightNode = NodesHelper.createNode(nextIndex);
        NodesHelper.linkNodes(currentNode, rightNode);

        shortcuts.put(nextIndex, rightNode);
        lastNode = rightNode;

        return rightNode;
    }

    /**
     * Enlarges the linked list and fills the gaps up to and including the specified index.
     *
     * @param index
     *        an index
     *
     * @return the node correspoding to the specified index
     */
    private IndexNode enlargeList(Number index) {

        IndexNode currentNode = lastNode;
        Number currentIndex = currentNode.index();

        while (currentIndex.isLesser(index)) {

            Number nextIndex = currentIndex.inc();
            IndexNode rightNode = addNodeToTheRight(currentNode, nextIndex);

            currentNode = rightNode;
            currentIndex = nextIndex;
        }

        return currentNode;
    }

    /**
     * Returns the first index.
     *
     * @return the first index
     */
    @Override
    public Number firstIndex() {

        return firstNode.index();
    }

    /**
     * Returns the default number base for index values.
     *
     * @return a default number base
     */
    @Override
    public int defaultNumberBase() {

        return DEFAULT_NUMBER_BASE;
    }

}

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

package jmul.math.vectors.nodes;


import jmul.math.numbers.Number;


/**
 * An implementation of an index node.
 *
 * @author Kristian Kutin
 */
class IndexNodeImpl implements IndexNode {

    /**
     * The index number stored in this node.
     */
    private final Number index;

    /**
     * A reference to the left node.
     */
    private IndexNode leftNode;

    /**
     * A reference to the right node.
     */
    private IndexNode rightNode;

    /**
     * Creates a new node with the specified parameter.
     *
     * @param index
     *        an index number
     */
    public IndexNodeImpl(Number index) {

        this(index, null, null);
    }

    /**
     * Creates a new node with the specified parameters.
     *
     * @param index
     *        an index number
     * @param leftNode
     *        a reference to the left node
     * @param rightNode
     *        a reference to the right node
     */
    public IndexNodeImpl(Number index, IndexNode leftNode, IndexNode rightNode) {

        super();

        this.index = index;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * Returns the index stored in this node.
     *
     * @return a digit
     */
    @Override
    public Number index() {

        return index;
    }

    /**
     * Returns the node to the left of this node or <code>null</code> if there is no node.
     *
     * @return a node or <code>null</code> if there is no node to the left
     */
    @Override
    public IndexNode leftNode() {

        return leftNode;
    }

    /**
     * Returns the node to the right of this node or <code>null</code> if there is no node.
     *
     * @return a node or <code>null</code> if there is no node to the right
     */
    @Override
    public IndexNode rightNode() {

        return rightNode;
    }

    /**
     * Returns a string representation for this node.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.valueOf(index);
    }

    /**
     * Sets the specified node as the reference to the left node.
     *
     * @param node
     *        an index node
     */
    public void setLeftNode(IndexNode node) {

        leftNode = node;
    }

    /**
     * Sets the specified node as the reference to the right node.
     *
     * @param node
     *        an index node
     */
    public void setRightNode(IndexNode node) {

        rightNode = node;
    }

}

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
 * A helper class which contains various utility functions.
 *
 * @author Kristian Kutin
 */
public class NodesHelper {

    /**
     * The default constructor.
     */
    private NodesHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new node.
     *
     * @param index
     *        an index number
     *
     * @return an index node
     */
    public static IndexNode createNode(Number index) {

        return new IndexNodeImpl(index);
    }

    /**
     * Links the two nodes.
     *
     * @param left
     *        the left node
     * @param right
     *        the right node
     */
    public static void linkNodes(IndexNode left, IndexNode right) {

        IndexNodeImpl leftNode = (IndexNodeImpl) left;
        IndexNodeImpl rightNode = (IndexNodeImpl) right;

        if (leftNode != null) {

            leftNode.setRightNode(rightNode);
        }

        if (rightNode != null) {

            rightNode.setLeftNode(leftNode);
        }
    }

}

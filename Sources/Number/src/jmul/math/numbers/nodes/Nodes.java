/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

package jmul.math.numbers.nodes;


import jmul.math.numbers.digits.Digit;


/**
 * A helper class which contains various utility functions.<br>
 * <br>
 * <i>Note:<br>
 * The <code>equals</code> and <code>hashCode</code> methods are not overridden on purpose.</i>
 *
 * @author Kristian Kutin
 */
public final class Nodes {

    /**
     * The default constructor.
     */
    private Nodes() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new node.
     *
     * @param digit
     *        a digit
     *
     * @return a digit node
     */
    public static DigitNode createNode(Digit digit) {

        return new DigitNodeImpl(digit);
    }

    /**
     * Clones the list to the left of this node.
     *
     * @param node
     *
     * @return a clone
     */
    public static DigitNode cloneLeftTail(DigitNode node) {

        if (node == null) {

            return null;
        }

        DigitNodeImpl clone = (DigitNodeImpl) createNode(node.digit());
        DigitNodeImpl leftClone = (DigitNodeImpl) cloneLeftTail(node.leftNode());

        if (leftClone != null) {

            clone.setLeftNode(leftClone);
            leftClone.setRightNode(clone);
        }

        return clone;
    }

    /**
     * Severes the linked list at the specified node and the node to the left.
     *
     * @param node
     *        a node in a linked list
     */
    public static void removeLeftTail(DigitNode node) {

        DigitNodeImpl right = (DigitNodeImpl) node;

        if (right != null) {

            DigitNodeImpl left = (DigitNodeImpl) node.leftNode();

            if (left != null) {

                left.setRightNode(null);
            }

            right.setLeftNode(null);
        }
    }

    /**
     * Severes the linked list at the specified node and the node to the right.
     *
     * @param node
     *        a node in a linked list
     */
    public static void removeRightTail(DigitNode node) {

        DigitNodeImpl left = (DigitNodeImpl) node;

        if (left != null) {

            DigitNodeImpl right = (DigitNodeImpl) node.rightNode();

            if (right != null) {

                right.setLeftNode(null);
            }

            left.setRightNode(null);
        }
    }

    /**
     * Clones the list to the right of this node.
     *
     * @param node
     *
     * @return a clone
     */
    public static DigitNode cloneRightTail(DigitNode node) {

        if (node == null) {

            return null;
        }

        DigitNodeImpl clone = (DigitNodeImpl) createNode(node.digit());
        DigitNodeImpl rightClone = (DigitNodeImpl) cloneRightTail(node.rightNode());

        if (rightClone != null) {

            clone.setRightNode(rightClone);
            rightClone.setLeftNode(clone);
        }

        return clone;
    }

    /**
     * Clones the specified linked list starting with the center node and then the left and right tails.
     *
     * @param centerNode
     *        the center node of a linked list
     *
     * @return a clone (i.e. a reference to the cloned center node)
     */
    public static DigitNode cloneLinkedList(DigitNode centerNode) {

        if (centerNode == null) {

            return null;
        }

        DigitNodeImpl clonedCenter = (DigitNodeImpl) createNode(centerNode.digit());
        DigitNodeImpl clonedLeft = (DigitNodeImpl) cloneLeftTail(centerNode.leftNode());
        DigitNodeImpl clonedRight = (DigitNodeImpl) cloneRightTail(centerNode.rightNode());

        clonedCenter.setLeftNode(clonedLeft);
        clonedCenter.setRightNode(clonedRight);

        return clonedCenter;
    }

    /**
     * Links the two nodes.
     *
     * @param left
     *        the left node
     * @param right
     *        the right node
     */
    public static void linkNodes(DigitNode left, DigitNode right) {

        DigitNodeImpl leftNode = (DigitNodeImpl) left;
        DigitNodeImpl rightNode = (DigitNodeImpl) right;

        if (leftNode != null) {

            leftNode.setRightNode(rightNode);
        }

        if (rightNode != null) {

            rightNode.setLeftNode(leftNode);
        }
    }

}

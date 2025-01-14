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


import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;

import jmul.metainfo.annotations.Modified;


/**
 * A helper class which contains various utility functions.
 *
 * @author Kristian Kutin
 */
public final class NodesHelper {

    /**
     * A constant representing a zero digit (i.e. it's ordinal value).
     */
    private static final int ZERO;

    /*
     * The static initializer.
     */
    static {

        ZERO = 0;
    }

    /**
     * The default constructor.
     */
    private NodesHelper() {

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
     * Creates a new node.
     *
     * @param base
     *        the digit base
     * @param ordinal
     *        the ordinal value of the digit
     *
     * @return a digit node
     */
    public static DigitNode createNode(int base, int ordinal) {

        Digit digit = PositionalNumeralSystems.ordinalToDigit(base, ordinal);
        DigitNode digitNode = createNode(digit);

        return digitNode;
    }

    /**
     * Returns the base of the underlying digit.
     *
     * @param node
     *        a node within a linked list
     *
     * @return the digit base
     */
    public static int getBase(DigitNode node) {

        Digit digit = node.digit();
        int base = digit.base();

        return base;
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

        linkNodes(clonedLeft, clonedCenter);
        linkNodes(clonedCenter, clonedRight);

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

    /**
     * Moves the references to the most a common node to the right.
     *
     * @param firstNode
     *        a reference to a node in the first linked list
     * @param secondNode
     *        a reference to a node in the second linked list
     */
    public static NodesResult moveRightSynchronously(DigitNode firstNode, DigitNode secondNode) {

        DigitNode node1 = firstNode;
        DigitNode node2 = secondNode;

        while (true) {

            if ((node1.rightNode() != null) && (node2.rightNode() != null)) {

                node1 = node1.rightNode();
                node2 = node2.rightNode();

            } else {

                break;
            }
        }

        return new NodesResult(node1, node2);
    }

    /**
     * Moves the references to the most common node to the left.
     *
     * @param firstNode
     *        a reference to a node in the first linked list
     * @param secondNode
     *        a reference to a node in the second linked list
     */
    public static NodesResult moveLeftSynchronously(DigitNode firstNode, DigitNode secondNode) {

        DigitNode node1 = firstNode;
        DigitNode node2 = secondNode;

        while (true) {

            if ((node1.leftNode() != null) && (node2.leftNode() != null)) {

                node1 = node1.leftNode();
                node2 = node2.leftNode();

            } else {

                break;
            }
        }

        return new NodesResult(node1, node2);
    }

    /**
     * Moves the reference to the last node to the left.
     *
     * @param node
     *        a reference to a node
     *
     * @return a reference to a node
     */
    public static DigitNode moveLeft(DigitNode node) {

        DigitNode currentNode = node;

        if (currentNode == null) {

            return currentNode;
        }

        while (true) {

            if (currentNode.leftNode() == null) {

                break;
            }

            currentNode = currentNode.leftNode();
        }

        return currentNode;
    }

    /**
     * Moves the reference to the last node to the right.
     *
     * @param node
     *        a reference to a node
     *
     * @return a reference to a node
     */
    public static DigitNode moveRight(DigitNode node) {

        DigitNode currentNode = node;

        if (currentNode == null) {

            return currentNode;
        }

        while (true) {

            if (currentNode.rightNode() == null) {

                break;
            }

            currentNode = currentNode.rightNode();
        }

        return currentNode;
    }

    /**
     * Removes leading zeros in the linked list.
     *
     * @param centerNode
     *        the center node of a linked list
     */
    public static void trimLeft(DigitNode centerNode) {

        DigitNode left = centerNode;

        while (left.leftNode() != null) {

            left = left.leftNode();
        }

        DigitNode right;
        do {

            if (left == centerNode) {

                break;
            }

            right = left.rightNode();
            if (left.digit().isZero()) {

                removeLeftTail(right);
                left = right;

            } else {

                break;
            }

        } while (right != null);
    }

    /**
     * Removes trailing zeros in the linked list.
     *
     * @param centerNode
     *        the center node of a linked list
     */
    public static void trimRight(DigitNode centerNode) {

        DigitNode right = centerNode;

        while (right.rightNode() != null) {

            right = right.rightNode();
        }

        DigitNode left;
        do {

            if (right == centerNode) {

                break;
            }

            left = right.leftNode();
            if (right.digit().isZero()) {

                removeRightTail(left);
                right = left;

            } else {

                break;
            }

        } while (left != null);
    }

    /**
     * Compares the two linked lists and fills up missing leading and trailing digits in the first linked
     * list with zeroes.
     *
     * @param firstNode
     *        the center node of a linked list. This linked list is modified.
     * @param secondNode
     *        the center node of a linked list
     */
    public static void fillUpWithZeroes(@Modified DigitNode firstNode, DigitNode secondNode) {

        NodesResult nodesResult;
        DigitNode node1;
        DigitNode node2;

        int base = getBase(firstNode);

        nodesResult = moveLeftSynchronously(firstNode, secondNode);
        node1 = nodesResult.firstNode;
        node2 = nodesResult.secondNode;

        while (true) {

            if (node2.leftNode() == null) {

                break;

            } else if (node1.leftNode() == null) {

                DigitNode zeroNode = createNode(base, ZERO);
                linkNodes(zeroNode, node1);
            }

            node1 = node1.leftNode();
            node2 = node2.leftNode();
        }

        nodesResult = moveRightSynchronously(firstNode, secondNode);
        node1 = nodesResult.firstNode;
        node2 = nodesResult.secondNode;

        while (true) {

            if (node2.rightNode() == null) {

                break;

            } else if (node1.rightNode() == null) {

                DigitNode zeroNode = createNode(base, ZERO);
                linkNodes(node1, zeroNode);
            }

            node1 = node1.rightNode();
            node2 = node2.rightNode();
        }
    }

}

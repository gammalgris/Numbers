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


import java.util.List;

import jmul.math.bool.BooleanHelper;
import jmul.math.digits.Digit;
import jmul.math.hash.HashHelper;


/**
 * An implementation of a digit node.<br>
 * <br>
 * <i>Note:<br>
 * In this implementation the {@link #equals} and {@link #hashCode} methods are
 * overriden to ensure that only the digits with the underlying linked lists are processed.
 * Thus different instances of a linked list which represent the same number should return
 * the same results.</i>
 *
 * @author Kristian Kutin
 */
class DigitNodeImpl implements DigitNode {

    /**
     * The digit stored in this node.
     */
    private final Digit digit;

    /**
     * A reference to the left node.
     */
    private DigitNode leftNode;

    /**
     * A reference to the right node.
     */
    private DigitNode rightNode;

    /**
     * Creates a new node with the specified parameter.
     *
     * @param digit
     *        a digit
     */
    public DigitNodeImpl(Digit digit) {

        this(digit, null, null);
    }

    /**
     * Creates a new node with the specified parameters.
     *
     * @param digit
     *        a digit
     * @param leftNode
     *        a reference to the left node
     * @param rightNode
     *        a reference to the right node
     */
    public DigitNodeImpl(Digit digit, DigitNode leftNode, DigitNode rightNode) {

        super();

        this.digit = digit;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * Returns the digit stored in this node.
     *
     * @return a digit
     */
    @Override
    public Digit digit() {

        return digit;
    }

    /**
     * Returns the node to the left of this node or <code>null</code> if there is no node.
     *
     * @return a node or <code>null</code> if there is no node to the left
     */
    @Override
    public DigitNode leftNode() {

        return leftNode;
    }

    /**
     * Sets the specified node as the reference to the left node.
     *
     * @param node
     *        a digit node
     */
    public void setLeftNode(DigitNode node) {

        leftNode = node;
    }

    /**
     * Returns the node to the right of this node or <code>null</code> if there is no node.
     *
     * @return a node or <code>null</code> if there is no node to the right
     */
    @Override
    public DigitNode rightNode() {

        return rightNode;
    }

    /**
     * Sets the specified node as the reference to the right node.
     *
     * @param node
     *        a digit node
     */
    public void setRightNode(DigitNode node) {

        rightNode = node;
    }

    /**
     * Returns a string representation for this node.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.valueOf(digit);
    }

    /**
     * Checks the equality of this node with the speicified object.
     *
     * @param o
     *        another object
     *
     * @return <code>true</code> if this node and the specified object are the same or represent the same number,
     *         else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (!(o instanceof DigitNode)) {

            return false;
        }

        DigitNode other = (DigitNode) o;

        if (this.digit() != other.digit()) {

            return false;
        }

        DigitNode thisNode = this.leftNode();
        DigitNode otherNode = this.leftNode();

        while (true) {

            if (BooleanHelper.xor(thisNode != null, otherNode != null)) {

                return false;
            }

            if ((thisNode == null) && (otherNode == null)) {

                break;
            }

            if (this.digit() != other.digit()) {

                return false;
            }

            thisNode = thisNode.leftNode();
            otherNode = otherNode.leftNode();
        }

        thisNode = this.rightNode();
        otherNode = this.rightNode();

        while (true) {

            if (BooleanHelper.xor(thisNode != null, otherNode != null)) {

                return false;
            }

            if ((thisNode == null) && (otherNode == null)) {

                break;
            }

            if (this.digit() != other.digit()) {

                return false;
            }

            thisNode = thisNode.rightNode();
            otherNode = otherNode.rightNode();
        }

        return true;
    }

    /**
     * Calculates a hash code for this node (i.e. and the whole linked list).
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        List<Integer> primeNumbers = HashHelper.getTwoPrimeNumbers(DigitNode.class);

        int firstPrime = primeNumbers.get(0);
        int secondPrime = primeNumbers.get(1);


        int hash;
        int n;

        hash = firstPrime;


        n = digit().hashCode();
        hash = secondPrime * hash + n;


        DigitNode node = leftNode();

        while (node != null) {

            n = digit().hashCode();
            hash = secondPrime * hash + n;

            node = node.leftNode();
        }

        node = rightNode();

        while (node != null) {

            n = digit().hashCode();
            hash = secondPrime * hash + n;

            node = node.rightNode();
        }

        return hash;
    }

}

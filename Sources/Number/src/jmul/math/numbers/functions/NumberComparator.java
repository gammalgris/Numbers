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

package jmul.math.numbers.functions;


import java.util.Comparator;

import jmul.math.functions.Function;
import jmul.math.numbers.Number;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.exceptions.DigitBaseMismatchException;
import jmul.math.numbers.nodes.DigitNode;
import jmul.math.numbers.nodes.NodesHelper;
import jmul.math.numbers.nodes.NodesResult;


/**
 * An implementation of an natural ordering comparator for numbers.
 *
 * @author Kristian Kutin
 */
public class NumberComparator extends ComparatorBase implements Function, Comparator<Number> {

    /**
     * The default constructor.
     */
    public NumberComparator() {

        super();
    }

    /**
     * Compares the two specified numbers regarding their natural order.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     *
     * @return <code>1</code>, <code>0</code> or <code>-1</code> if the first number is greater than,
     *         equals or lesser than the second number.
     */
    @Override
    public int compare(Number n1, Number n2) {

        // Check the references
        if (n1 == null) {

            throw new NullPointerException();
        }

        if (n2 == null) {

            throw new NullPointerException();
        }

        if (n1 == n2) {

            return EQUALS;
        }

        // Check the number bases
        if (n1.base() != n2.base()) {

            throw new DigitBaseMismatchException(n1, n2);
        }

        // Check for zero (-0, 0, +0) and ignore the signs
        if (n1.isZero() && n2.isZero()) { // may be superfluous

            return EQUALS;
        }

        // Check the signs
        if (n1.sign() != n2.sign()) {

            if (n1.isPositive()) {

                return GREATER_THAN;

            } else {

                return LESSER_THAN;
            }
        }

        // Check for infinity
        if (n1.isInfinity() && n2.isInfinity()) {

            return EQUALS;
        }

        if (n1.isInfinity()) {

            return GREATER_THAN;
        }

        if (n2.isInfinity()) {

            return LESSER_THAN;
        }

        // Check the digits left of the decimal separator
        Sign commonSign = n1.sign();

        NodesResult nodesResult = NodesHelper.moveLeftSynchronously(n1.centerNode(), n2.centerNode());
        DigitNode node1 = nodesResult.firstNode;
        DigitNode node2 = nodesResult.secondNode;

        // Check which number has more digits to the left
        if (node1.leftNode() != null) {

            if (Signs.isPositive(commonSign)) {

                return GREATER_THAN;

            } else {

                return LESSER_THAN;
            }

        } else if (node2.leftNode() != null) {

            if (Signs.isPositive(commonSign)) {

                return LESSER_THAN;

            } else {

                return GREATER_THAN;
            }
        }

        // Compare the digits from left to right
        while (true) {

            if ((node1 == null) && (node2 == null)) {

                break;
            }

            if (node1 == null) {

                if (Signs.isPositive(commonSign)) {

                    return LESSER_THAN;

                } else {

                    return GREATER_THAN;
                }

            } else if (node2 == null) {

                if (Signs.isPositive(commonSign)) {

                    return GREATER_THAN;

                } else {

                    return LESSER_THAN;
                }
            }

            Digit d1 = node1.digit();
            Digit d2 = node2.digit();

            int result = d1.compareTo(d2);

            if (result != 0) {

                if (Signs.isPositive(commonSign)) {

                    return result;

                } else {

                    return result * -1;
                }
            }

            node1 = node1.rightNode();
            node2 = node2.rightNode();
        }

        // return a default value
        return EQUALS;
    }

}

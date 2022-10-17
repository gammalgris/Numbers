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

package jmul.math.numbers.operations;


import jmul.math.numbers.Number;
import jmul.math.numbers.nodes.DigitNode;

import jmul.singletons.Function;


/**
 * An implementation of an equality comparator for numbers.
 *
 * @author Kristian Kutin
 */
public class NumberEqualityFunctionImpl implements Function, EqualityFunction<Number> {

    /**
     * The default constructor.
     */
    public NumberEqualityFunctionImpl() {

        super();
    }

    /**
     * Compares the two specified numbers regarding equality.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     *
     * @return <code>true</code> if both numbers are considered equal, else <code>false</code>
     */
    public boolean equals(Number n1, Number n2) {

        if ((n1 == null) && (n2 == null)) {

            return true;
        }

        if ((n1 == null) || (n2 == null)) {

            return false;
        }

        if (n1 == n2) {

            return true;
        }

        if (n1.sign() != n2.sign()) {

            return false;
        }

        if (n1.base() != n2.base()) {

            return false;
        }

        if ((n1.centerNode() == null) && (n2.centerNode() == null)) {

            return true;
        }

        if ((n1.centerNode() == null) || (n2.centerNode() == null)) {

            return false;
        }

        if (n1.centerNode().digit() != n2.centerNode().digit()) {

            return false;
        }

        DigitNode thisLeft = n1.centerNode().leftNode();
        DigitNode otherLeft = n2.centerNode().leftNode();

        while (true) {

            if ((thisLeft == null) && (otherLeft == null)) {

                break;
            }

            if ((thisLeft == null) || (otherLeft == null)) {

                return false;
            }

            if (thisLeft.digit() != otherLeft.digit()) {

                return false;
            }

            thisLeft = thisLeft.leftNode();
            otherLeft = otherLeft.leftNode();
        }

        DigitNode thisRight = n1.centerNode().rightNode();
        DigitNode otherRight = n2.centerNode().rightNode();

        while (true) {

            if ((thisRight == null) && (otherRight == null)) {

                break;
            }

            if ((thisRight == null) || (otherRight == null)) {

                return false;
            }

            if (thisRight.digit() != otherRight.digit()) {

                return false;
            }

            thisRight = thisRight.rightNode();
            otherRight = otherRight.rightNode();
        }

        return true;
    }

}

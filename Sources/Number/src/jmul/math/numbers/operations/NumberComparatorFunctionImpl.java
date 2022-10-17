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


import java.util.Comparator;

import jmul.math.numbers.Number;

import jmul.singletons.Function;


/**
 * An implementation of an natural ordering comparator for numbers.
 *
 * @author Kristian Kutin
 */
public class NumberComparatorFunctionImpl implements Function, Comparator<Number> {

    /**
     * A constant value indicating that a number is greater than another number.
     */
    private static final int GREATER_THAN;

    /**
     * A constanjt indicating that a number is equal to another number.
     */
    private static final int EQUALS;

    /**
     * A constant indicating that a number is lesser than another number.
     */
    private static final int LESSER_THAN;

    /*
     * The static initializer.
     */
    static {

        GREATER_THAN = 1;
        EQUALS = 0;
        LESSER_THAN = -1;
    }

    /**
     * The default constructor.
     */
    public NumberComparatorFunctionImpl() {

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

        if ((n1 == null) && (n2 == null)) {

            return EQUALS;
        }

        if (n1 == null) {

            return LESSER_THAN;
        }

        if (n2 == null) {

            return GREATER_THAN;
        }

        if (n1 == n2) {

            return EQUALS;
        }

        if (n1.isZero() && n2.isZero()) {

            return EQUALS;
        }

        if (n1.sign() != n2.sign()) {

            if (n1.isPositive()) {

                return GREATER_THAN;

            } else {

                return LESSER_THAN;
            }
        }

        if (n1.isInfinity() && n2.isInfinity()) {

            return EQUALS;
        }

        if (n1.isInfinity()) {

            return GREATER_THAN;
        }

        if (n2.isInfinity()) {

            return LESSER_THAN;
        }

        if (n1.isZero()) {

            if (n2.isPositive()) {

                return LESSER_THAN;

            } else {

                return GREATER_THAN;
            }
        }

        if (n2.isZero()) {

            if (n1.isPositive()) {

                return GREATER_THAN;

            } else {

                return LESSER_THAN;
            }
        }


        // TODO Implement this method

        return 0;
    }

}

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

package jmul.math.numbers.comparators;


import java.util.Comparator;

import jmul.math.numbers.DigitSequence;
import jmul.math.numbers.digits.Digit;


/**
 * A generic implementation of a comparator for numbers.<br>
 * <br>
 * <i>Note:<br>
 * This implementation should work with any numbers who share the same digit base.</i>
 *
 * @param <T>
 *        The type of objects to compare
 */
public class NaturalOrderingComparator<T extends DigitSequence<? extends Digit>> implements Comparator<T> {

    /**
     * The default constructor.
     */
    public NaturalOrderingComparator() {

        super();
    }

    /**
     * Compares the two specified numbers regarding the natural odering of their respective digits.
     *
     * @param o1
     *        a number
     * @param o2
     *        a number
     *
     * @return <code>-1</code> if <code>o1</code> is lesser than <code>o2</code>, <code>0</code> if both numbers are
     *         equal and <code>1</code> if <code>o1</code> is greater than <code>o2</code>
     */
    @Override
    public int compare(T o1, T o2) {

        if (o1.isNegative() && o2.isPositive()) {

            return -1;

        } else if (o1.isPositive() && o2.isNegative()) {

            return 1;
        }

        // o1 and o2 have the same sign

        if (o1.isInfinity() && o2.isInfinity()) {

            return 0;

        } else if (o1.isInfinity() && !o2.isInfinity()) {

            if (o1.isPositive()) {

                return 1;

            } else {

                return -1;
            }

        } else if (!o1.isInfinity() && o2.isInfinity()) {

            if (o1.isPositive()) {

                return -1;

            } else {

                return 1;
            }
        }

        // neither o1 and o2 are infinity

        checkBase(o1, o2);

        // now check the two numbers by their digits
        int maxLeft = Math.max(o1.leftDigits(), o2.leftDigits());
        int maxRight = Math.max(o1.rightDigits(), o2.rightDigits());

        for (int a = maxLeft; a >= -maxRight; a--) {

            if (a == 0) {

                continue;
            }

            Digit d1 = o1.digitAt(a);
            Digit d2 = o2.digitAt(a);

            int b = normalize(d1.ordinal() - d2.ordinal());

            if (b != 0) {

                return b;
            }
        }

        return 0;
    }

    /**
     * Checks if the two numbers have a common digit base.
     *
     * @param o1
     *        a number
     * @param o2
     *        a number
     */
    private void checkBase(T o1, T o2) {

        Digit d1 = o1.digitAt(1);
        Digit d2 = o2.digitAt(1);

        if (d1.base() != d2.base()) {

            String message =
                String.format("The specified numbers (#1=%s; #2=%s) don't have the same digit base (#1=%d; #2=%d)",
                              o1.toString(), o2.toString(), d1.base(), d2.base());
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Normalize the specified integer.
     *
     * @param i
     *        an integer
     *
     * @return <code>-1</code> if the specified integer is negative, <code>0</code> if the specified inteer is zero
     *         and <code>1</code> if the specified itneger is greater than zero
     */
    private static int normalize(int i) {

        if (i < 0) {

            return -1;

        } else if (i > 0) {

            return 1;

        } else {

            return 0;
        }
    }

}

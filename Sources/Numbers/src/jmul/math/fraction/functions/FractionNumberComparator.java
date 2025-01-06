/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.math.fraction.functions;


import jmul.math.fraction.Fraction;
import static jmul.math.fraction.FractionHelper.DONT_CLONE;
import static jmul.math.fraction.FractionHelper.createFraction;
import jmul.math.functions.Function;
import jmul.math.numbers.Number;
import jmul.math.numbers.functions.ComparatorBase;
import jmul.math.operations.MixedComparator;


/**
 * A custom implementation of a comparator that compares a fraction with a number.
 *
 * @author Kristian Kutin
 */
public class FractionNumberComparator extends ComparatorBase implements Function, MixedComparator<Fraction, Number> {

    /**
     * The default constructor.
     */
    public FractionNumberComparator() {

        super();
    }

    /**
     * Compares the specified objects.
     *
     * @param f fraction
     *        a fraction
     * @param n number
     *        a number
     *
     * @return <code>1</code> if the specified fraction is greater than the specified number, <code>0</code> if the
     *         specified fraction and the specified number are considered equal or <code>-1</code> if the specified
     *         fraction is lesser than the specified number
     */
    @Override
    public int compare(Fraction f, Number n) {

        checkParameters(f, n);

        Number numerator = f.denominator().multiply(n);
        Number denominator = f.denominator();
        Fraction normalizedNumber = createFraction(DONT_CLONE, numerator, denominator);

        return f.compareTo(normalizedNumber);
    }

    /**
     * Checks the specified parameters.
     *
     * @param f
     *        a fraction
     * @param n
     *        a number
     */
    private static void checkParameters(Fraction f, Number n) {

        if (f == null) {

            throw new IllegalArgumentException("No fraction (null) was specified!");
        }

        if (n == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        if (f.base() != n.base()) {

            throw new IllegalArgumentException("The fraction and the number don't have the same number base!");
        }
    }

}

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

package jmul.math.functions.implementations.comparisons;


import jmul.math.fractions.Fraction;
import jmul.math.functions.Function;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.implementations.ComparatorBase;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.MixedComparator;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * A custom implementation of a comparator that compares a number with a fraction.
 *
 * @author Kristian Kutin
 */
public class NumberFractionComparator extends ComparatorBase implements Function, MixedComparator<Number, Fraction> {

    /**
     * The default constructor.
     */
    public NumberFractionComparator() {

        super();
    }

    /**
     * Compares the specified objects.
     *
     * @param n number
     *        a number
     * @param f fraction
     *        a fraction
     *
     * @return <code>1</code> if the specified number is greater than the specified fraction, <code>0</code> if the
     *         specified number and the specified fraction are considered equal or <code>-1</code> if the specified
     *         number is lesser than the specified fraction
     */
    @Override
    public int compare(Number n, Fraction f) {

        checkParameters(n, f);

        UnaryOperation<Number, Result<Fraction>> function =
            (UnaryOperation<Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_TO_FRACTION_FUNCTION);
        Result<Fraction> wrappedResult = function.calculate(n);
        Fraction normalizedNumber = wrappedResult.result();

        return normalizedNumber.compareTo(f);
    }

    /**
     * Checks the specified parameters.
     *
     * @param n
     *        a number
     * @param f
     *        a fraction
     */
    private static void checkParameters(Number n, Fraction f) {

        if (n == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        if (f == null) {

            throw new IllegalArgumentException("No fraction (null) was specified!");
        }

        if (f.base() != n.base()) {

            throw new IllegalArgumentException("The fraction and the number don't have the same number base!");
        }
    }

}

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

package jmul.math.functions.implementations.equality;


import static jmul.math.fractions.FractionHelper.DONT_CLONE;
import jmul.math.fractions.Fraction;
import jmul.math.fractions.FractionHelper;
import jmul.math.functions.Function;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.operations.EqualityFunction;
import jmul.math.operations.MixedEqualityFunction;


/**
 * An implementation of a mixed equality comparator for fractions and numbers.
 *
 * @author Kristian Kutin
 */
public class NumberFractionEquality implements Function, MixedEqualityFunction<Number, Fraction> {

    /**
     * The default constructor.
     */
    public NumberFractionEquality() {

        super();
    }

    /**
     * Compares the specified number with the specified fraction.
     *
     * @param t1
     *        a number
     * @param t2
     *        a fraction
     *
     * @return <code>true</code> if both numbers are considered equal, else <code>false</code>
     */
    @Override
    public boolean equals(Number t1, Fraction t2) {

        // Check the references

        if ((t1 == null) && (t2 == null)) {

            return true;
        }

        if ((t1 == null) || (t2 == null)) {

            return false;
        }

        Fraction f1 = FractionHelper.createFraction(DONT_CLONE, t1);

        EqualityFunction<Fraction> function =
            (EqualityFunction<Fraction>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_EQUALITY_FUNCTION);
        boolean result = function.equals(f1, t2);

        return result;
    }

}

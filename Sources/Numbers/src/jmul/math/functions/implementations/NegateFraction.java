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

package jmul.math.functions.implementations;


import jmul.math.fractions.Fraction;
import jmul.math.fractions.FractionHelper;
import static jmul.math.fractions.FractionHelper.DONT_CLONE;
import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * This function implementation negates a fraction.
 *
 * @author Kristian Kutin
 */
public class NegateFraction implements UnaryOperation<Fraction, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public NegateFraction() {

        super();
    }

    /**
     * Negates the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Result<Fraction> calculate(Fraction fraction) {

        if (fraction == null) {

            throw new IllegalArgumentException("The specified fraction is null!");
        }

        Number newIntegerPart = fraction.integerPart();
        Number newNumerator = fraction.numerator();
        Number newDenominator = fraction.denominator();

        Fraction newFraction;
        if (fraction.hasIntegerPart()) {

            newIntegerPart = newIntegerPart.negate();
            newFraction = FractionHelper.createFraction(DONT_CLONE, newIntegerPart, newNumerator, newDenominator);

        } else {

            if (newNumerator.isZero()) {

                newIntegerPart = newIntegerPart.negate();
                newFraction = FractionHelper.createFraction(DONT_CLONE, newIntegerPart);

            } else {

                newNumerator = newNumerator.negate();
                newFraction = FractionHelper.createFraction(DONT_CLONE, newNumerator, newDenominator);
            }
        }

        return new Result<Fraction>(newFraction);
    }

}

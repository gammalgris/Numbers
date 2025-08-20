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

package jmul.math.functions.implementations;


import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import static jmul.math.functions.implementations.ParameterCheckHelper.checkParameter;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import static jmul.math.numbers.creation.CreationParameters.DONT_CLONE;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * Translates a number into a fraction.
 *
 * @author Kristian Kutin
 */
public class NumberToFraction implements UnaryOperation<Number, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public NumberToFraction() {

        super();
    }

    /**
     * Translates the specified number into a fraction.
     *
     * @param number
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Result<Fraction> calculate(Number number) {

        checkParameter(number);

        Fraction fraction;
        if (number.isInfinity() || number.isInteger()) {

            fraction = integerToFraction(number);

        } else {

            fraction = numberToFraction(number);
        }

        return new Result<Fraction>(fraction);
    }

    /**
     * Translates the specified integer (i.e. number with out fraction) into a fraction.
     *
     * @param number
     *        a number
     *
     * @return a fraction
     */
    private static Fraction integerToFraction(Number number) {

        return createFraction(CLONE, number);
    }

    /**
     * Translates the specified number with fraction into a fraction.
     *
     * @param number
     *        a number
     *
     * @return a fraction
     */
    private static Fraction numberToFraction(Number number) {

        //TODO Consider where this function is needed
        //TODO Consider adding a corresponding function to the number interface

        ParameterCheckHelper.checkParameter(number);

        int base = number.base();

        String symbol = PositionalNumeralSystems.toString(base, 1);

        Number newNumerator = NumberHelper.createNumber(CLONE, number);
        Number newDenominator = createNumber(base, symbol);

        while (newNumerator.isFraction()) {

            newNumerator = newNumerator.shiftRight();
            newDenominator = newDenominator.shiftRight();
        }

        return createFraction(DONT_CLONE, newNumerator, newDenominator);
    }

}

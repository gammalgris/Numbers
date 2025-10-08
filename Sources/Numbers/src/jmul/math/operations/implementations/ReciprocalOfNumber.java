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

package jmul.math.operations.implementations;


import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.DONT_CLONE;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Signs;


/**
 * Calculates the reciprocal of a number.
 *
 * @author Kristian Kutin
 */
public class ReciprocalOfNumber implements UnaryOperation<Number, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public ReciprocalOfNumber() {

        super();
    }

    /**
     * Calculates the reciprocal of the specified number and returns a fraction.
     *
     * @param number
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Result<Fraction> calculate(Number number) {

        ParameterCheckHelper.checkParameter(number);

        Fraction result;
        if (number.isFraction()) {

            result = reciprocalOfFraction(number);

        } else {

            result = reciprocalOfInteger(number);
        }

        return new Result<Fraction>(result);
    }

    /**
     * Calculates the reciprocal of the specified integer.
     *
     * @param integer
     *        an integer
     *
     * @return the reciprocal of the specified integer
     */
    private Fraction reciprocalOfInteger(Number integer) {

        int base = integer.base();

        Number newNumerator = createNumber(base, Signs.POSITIVE, 1);
        if (integer.isNegative()) {

            newNumerator = newNumerator.negate();
        }

        Number newDenominator = integer.absoluteValue();

        return createFraction(DONT_CLONE, newNumerator, newDenominator);
    }

    /**
     * Calculates the reciprocal of a number with a fractional part.
     *
     * @param number
     *        a number with a fractional part
     *
     * @return
     */
    private Fraction reciprocalOfFraction(Number number) {

        int base = number.base();

        Number newNumerator = createNumber(base, Signs.POSITIVE, 1);
        Number newDenominator = number;

        while (newDenominator.isFraction()) {

            newNumerator = newNumerator.shiftRight();
            newDenominator = newDenominator.shiftRight();
        }

        return createFraction(DONT_CLONE, newNumerator, newDenominator);
    }

}

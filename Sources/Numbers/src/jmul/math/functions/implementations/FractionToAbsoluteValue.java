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


import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.DONT_CLONE;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * A function to compute the absolute value of a fraction.
 *
 * @author Kristian Kutin
 */
public class FractionToAbsoluteValue implements UnaryOperation<Fraction, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public FractionToAbsoluteValue() {

        super();
    }

    /**
     * Calculates and returns the absolute value of the specified fraction.
     *
     * @param operand
     *        a number
     *
     * @return the absolute value of the fraction
     */
    @Override
    public Result<Fraction> calculate(Fraction operand) {

        ParameterCheckHelper.checkParameter(operand);

        Number absoluteIntegerPart = operand.integerPart().absoluteValue();
        Number absoluteNumerator = operand.numerator().absoluteValue();
        Number absoluteDenominator = operand.denominator().absoluteValue();
        Fraction absoluteValue =
            createFraction(DONT_CLONE, absoluteIntegerPart, absoluteNumerator, absoluteDenominator);

        return new Result<Fraction>(absoluteValue);
    }

}

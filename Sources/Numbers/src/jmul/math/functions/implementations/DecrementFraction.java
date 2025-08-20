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
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import static jmul.math.numbers.creation.CreationParameters.DONT_CLONE;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Signs;


/**
 * This function implements the increment function.
 *
 * @author Kristian Kutin
 */
public class DecrementFraction implements UnaryOperation<Fraction, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public DecrementFraction() {

        super();
    }

    /**
     * Decrements the specified operand by one.
     *
     * @param operand
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Result<Fraction> calculate(Fraction operand) {

        ParameterCheckHelper.checkParameter(operand);

        Fraction newFraction;
        /*if (operand.hasIntegerPart()) {

            Number newIntegerPart = operand.integerPart().dec();
            newFraction =
                createFraction(CLONE, newIntegerPart, operand.numerator(), operand.denominator());

        } else {

            int base = operand.base();
            String symbol = PositionalNumeralSystems.toString(base, 1);
            final Number ONE = createNumber(base, symbol);

            if (operand.isPositive()) {

                Number normalizedOne = ONE.multiply(operand.denominator());
                Number newNumerator = operand.numerator().subtract(normalizedOne);
                newFraction = createFraction(CLONE, newNumerator, operand.denominator());

            } else {

                Number absoluteNumerator = operand.numerator().absoluteValue();
                Number newIntegerPart = ONE.negate();
                newFraction =
                    createFraction(CLONE, newIntegerPart, absoluteNumerator, operand.denominator());
            }
        }*/
        if (operand.hasIntegerPart()) {

            Number newIntegerPart = operand.integerPart().dec();
            Number newNumerator = operand.numerator();
            Number newDenominator = NumberHelper.createNumber(CLONE, operand.denominator());
            if (newIntegerPart.isZero() && !newNumerator.isZero()) {

                newNumerator = newNumerator.negate();
            }
            newFraction = createFraction(CLONE, newIntegerPart, newNumerator, newDenominator);

        } else {

            int base = operand.base();
            final Number ONE = createNumber(base, Signs.NEGATIVE, 1);

            Number normalizedOne = ONE.multiply(operand.denominator());
            if (operand.numerator().isZero()) {

                newFraction = createFraction(DONT_CLONE, ONE);

            } else {

                Number newNumerator = operand.numerator().add(normalizedOne);
                Number newDenominator = NumberHelper.createNumber(CLONE, operand.denominator());
                newFraction = createFraction(DONT_CLONE, newNumerator, newDenominator);
            }
        }

        return new Result<Fraction>(newFraction);
    }

}

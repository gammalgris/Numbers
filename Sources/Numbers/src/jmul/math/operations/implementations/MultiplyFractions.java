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


import jmul.math.Math;
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.cloneFraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.creation.CreationParameters.DONT_CLONE;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;


/**
 * An implementation of multiplying fractions.
 *
 * @author Kristian Kutin
 */
public class MultiplyFractions implements BinaryOperation<Fraction, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public MultiplyFractions() {

        super();
    }

    /**
     * Mutiplies the specified fractions.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Result<Fraction> calculate(Fraction operand1, Fraction operand2) {

        ParameterCheckHelper.checkParameters(operand1, operand2);

        int base = operand1.base();

        final Number ONE = Math.ONE.value(base);
        final Number MINUS_ONE = Math.MINUS_ONE.value(base);

        Fraction result;
        if (ONE.equals(operand1)) {

            result = cloneFraction(operand2);
            return new Result<Fraction>(result);

        } else if (MINUS_ONE.equals(operand1)) {

            result = cloneFraction(operand2);
            result = result.negate();
            return new Result<Fraction>(result);


        } else if (ONE.equals(operand2)) {

            result = cloneFraction(operand1);
            return new Result<Fraction>(result);

        } else if (MINUS_ONE.equals(operand2)) {

            result = cloneFraction(operand1);
            result = result.negate();
            return new Result<Fraction>(result);
        }

        Fraction normalizedFraction1 = operand1.normalizedFraction();
        Fraction normalizedFraction2 = operand2.normalizedFraction();

        Number newNumerator = normalizedFraction1.numerator().multiply(normalizedFraction2.numerator());
        Number newDenominator = normalizedFraction1.denominator().multiply(normalizedFraction2.denominator());

        if (newNumerator.isInfinity() && newDenominator.isInfinity()) {

            result = createFraction(DONT_CLONE, newNumerator, newDenominator);

        } else if (newNumerator.isInfinity()) {

            result = createFraction(DONT_CLONE, newNumerator);

        } else if (newNumerator.isZero()) {

            result = createFraction(DONT_CLONE, newNumerator);

        } else if (newDenominator.isOne()) {

            result = createFraction(DONT_CLONE, newNumerator);

        } else {

            result = createFraction(DONT_CLONE, newNumerator, newDenominator);
        }

        return new Result<Fraction>(result);
    }

}

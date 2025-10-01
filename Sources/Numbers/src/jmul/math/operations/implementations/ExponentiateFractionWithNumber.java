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
import static jmul.math.fractions.FractionHelper.cloneFraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.DONT_CLONE;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;


/**
 * Implements the exponentiation func tion with exponents that are integers.
 *
 * @author Kristian Kutin
 */
public class ExponentiateFractionWithNumber implements MixedBinaryOperation<Fraction, Number, Result<Fraction>> {

    /**
     * The default constructor.
     */
    public ExponentiateFractionWithNumber() {

        super();
    }

    /**
     * Exponentiates the specified number by the specified exponent.
     *
     * @param number
     *        a fraction
     * @param exponent
     *        an exponent (i.e. an integer)
     *
     * @return the result
     */
    @Override
    public Result<Fraction> calculate(Fraction number, Number exponent) {

        ParameterCheckHelper.checkParameters(number, exponent);
        ParameterCheckHelper.checkIntegerIgnoreNull(exponent);

        int base = number.base();
        final Number ZERO = createNumber(base, "0");
        final Number ONE = ZERO.inc();

        if (exponent.isZero()) {

            Fraction clone = createFraction(DONT_CLONE, ONE);
            return new Result<Fraction>(clone);
        }

        if (isInfinity(number)) {

            if (exponent.isNegative()) {

                Fraction clone = createFraction(DONT_CLONE, ZERO);
                return new Result<Fraction>(clone);
            }

            Fraction clone = cloneFraction(number);
            return new Result<Fraction>(clone);
        }

        Fraction result;
        if (exponent.isNegative()) {

            Number absoluteExponent = exponent.absoluteValue();
            Fraction reciprocal = number.reciprocal();
            result = exponentiate(reciprocal, absoluteExponent);

        } else {

            result = exponentiate(number, exponent);
        }

        return new Result<Fraction>(result);
    }

    /**
     * Checks if the specified fraction represents infinity.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if the specified fraction represents infinity, else <code>false</code>
     */
    private static boolean isInfinity(Fraction fraction) {

        if (fraction.hasIntegerPart() && fraction.integerPart().isInfinity()) {

            return true;
        }

        if (fraction.hasNumerator() && fraction.numerator().isInfinity()) {

            return true;
        }

        return false;
    }

    /**
     * Exponentiates the specified number (i.e. reciprocal of a number) by the specified exponent.
     *
     * @param fraction
     *        a fraction
     * @param exponent
     *        an exponent (i.e. a positive integer greater than or equal to one)
     *
     * @return the result
     */
    private Fraction exponentiate(Fraction fraction, Number exponent) {

        int base = fraction.base();
        final Fraction ONE = createFraction(base, "1");

        Number counter = exponent;
        Fraction result = ONE;

        while (!counter.isZero()) {

            result = result.multiply(fraction);
            counter = counter.dec();
        }

        //result = result.reduce();

        return result;
    }

}

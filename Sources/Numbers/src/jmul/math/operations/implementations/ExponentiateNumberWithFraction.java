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


import java.util.ArrayList;
import java.util.List;

import jmul.math.Math;
import jmul.math.collections.Sequence;
import jmul.math.collections.SequenceImpl;
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.operations.MixedQuaternaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.signs.Signs;


/**
 * Implements the exponentiation function with exponents that are fractions.
 *
 * @author Kristian Kutin
 */
public class ExponentiateNumberWithFraction implements MixedQuaternaryOperation<Number, Fraction, Result<Number>> {

    /**
     * The default constructor.
     */
    public ExponentiateNumberWithFraction() {

        super();
    }

    /**
     * Exponentiates the specified number by the specified exponent (i.e. exponentiating and calculating
     * roots).
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent (i.e. an integer)
     * @param iterations
     *        the iteration depth
     * @param decimalPlaces
     *        the precision
     *
     * @return the result
     */
    @Override
    public Result<Number> calculate(Number number, Fraction exponent, Number iterations, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(number, exponent, iterations, decimalPlaces);
        ParameterCheckHelper.checkPositiveInteger(iterations);
        ParameterCheckHelper.checkPositiveInteger(decimalPlaces);

        if (exponent.hasIntegerPart() && !exponent.hasNumerator() && !exponent.hasDenominator()) {

            ProcessingDetails processingDetails = ProcessingDetails.setAlgorithm(ProcessingDetails.DEFAULT_ALGORITHM);
            Number result = number.exponentiate(processingDetails, exponent.integerPart());
            return new Result<Number>(result);
        }

        Fraction normalizedExponent = exponent;
        if (exponent.hasIntegerPart()) {

            normalizedExponent = exponent.normalizedFraction();
        }

        int base = number.base();
        final Number ZERO = Math.ZERO.value(base);
        final Number ONE = Math.ONE.value(base);

        if (normalizedExponent.numerator().isZero()) {

            Number clone = createNumber(CLONE, ONE);
            return new Result<Number>(clone);
        }

        if (number.isInfinity()) {

            if (exponent.isNegative()) {

                Number clone = createNumber(CLONE, ZERO);
                return new Result<Number>(clone);
            }

            Number clone = createNumber(CLONE, number);
            return new Result<Number>(clone);
        }

        Number result;
        if (exponent.isNegative()) {

            Fraction absoluteExponent = exponent.absoluteValue();
            Fraction reciprocal = number.reciprocal();
            result = exponentiate(reciprocal, absoluteExponent, iterations, decimalPlaces);

        } else {

            result = exponentiate(number, exponent, iterations, decimalPlaces);
        }

        return new Result<Number>(result);
    }

    /**
     * Exponentiates the specified number by the specified exponent.
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent (i.e. a positive fraction)
     * @param iterations
     *        the iteration depth
     * @param decimalPlaces
     *        the precision
     *
     * @return the result
     */
    private Number exponentiate(Number number, Fraction exponent, Number iterations, Number decimalPlaces) {

        if (exponent.numerator().equals(exponent.denominator())) {

            return number;
        }


        int base = number.base();
        final Number ZERO = createNumber(base, Signs.POSITIVE, 0);
        final Number ONE = createNumber(base, Signs.POSITIVE, 1);

        Sequence<Fraction> exponents = splitExponent(exponent);
        Number lastElementIndex = exponents.elements().dec();

        Number product1;
        if (exponents.elements().isOne()) {

            product1 = null;

        } else {

            product1 = ONE;
        }

        for (Number index = ZERO; index.isLesser(lastElementIndex); index = index.inc()) {

            Fraction f = exponents.ordinal(index);
            Number factorN = exponentiate(number, f, iterations, decimalPlaces);
            product1 = product1.multiply(factorN);
        }


        Fraction lastExponent = exponents.ordinal(lastElementIndex);
        lastExponent = lastExponent.reduce();


        ProcessingDetails processingDetails;
        Number product2;

        processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces, iterations);
        product2 = number.root(processingDetails, lastExponent.denominator());

        processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);
        product2 = product2.exponentiate(processingDetails, lastExponent.numerator());

        if (product1 == null) {

            return product2;

        } else {

            processingDetails = ProcessingDetails.setAlgorithm(ProcessingDetails.DEFAULT_ALGORITHM);
            product1 = product1.multiply(processingDetails, product2);

            processingDetails = ProcessingDetails.setPrecision(decimalPlaces);
            product1 = product1.round(processingDetails);

            return product1;
        }
    }

    /**
     * Exponentiates the specified number by the specified exponent.
     *
     * @param number
     *        a fraction
     * @param exponent
     *        an exponent (i.e. a positive fractioninteger greater than or equal to one)
     * @param iterations
     *        the iteration depth
     * @param decimalPlaces
     *        the precision
     *
     * @return the result
     */
    private Number exponentiate(Fraction number, Fraction exponent, Number iterations, Number decimalPlaces) {

        Number normalizedNumber = number.evaluate(decimalPlaces);

        return exponentiate(normalizedNumber, exponent, iterations, decimalPlaces);
    }

    /**
     * Split up an exponent. Instead of reducing the exponent it is split up into summands. Example:<br>
     * <br>
     * a^(11/3)<br>
     * -&gt; a^(3/3 + 3/3 + 3/3 + 2/3)<br>
     * -&gt; a^(3/3) * a(3/3) * a^(3/3) * a^(2/3)<br>
     * -&gt; a * a * a * a^(2/3)<br>
     * <br>
     * Afterwards the individual exponents can be further reduced if possible.
     *
     * @param exponent
     *        an unsigned exponent (i.e. positive)
     *
     * @return a reduced exponent
     */
    private Sequence<Fraction> splitExponent(Fraction exponent) {

        int base = exponent.base();

        List<Fraction> splitExponent = new ArrayList<>();

        Number numerator = exponent.numerator();
        Number denominator = exponent.denominator();

        while (numerator.isGreaterOrEqual(denominator)) {

            numerator = numerator.subtract(denominator);

            Fraction part = createFraction(CLONE, denominator, denominator);
            splitExponent.add(part);
        }

        if (!numerator.isZero()) {

            Fraction part = createFraction(CLONE, numerator, denominator);
            splitExponent.add(part);
        }

        Sequence<Fraction> sequence = new SequenceImpl<>(base, splitExponent);

        return sequence;
    }

}

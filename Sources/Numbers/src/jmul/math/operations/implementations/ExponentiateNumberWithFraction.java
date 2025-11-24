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
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.operations.MixedQuaternaryOperation;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.QuaternaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifiers;


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

            TernaryOperation<Number, Result<Number>> function =
                (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION);
            Result<Number> result = function.calculate(number, exponent.integerPart(), decimalPlaces);

            return result;
        }

        Fraction normalizedExponent = exponent;
        if (exponent.hasIntegerPart()) {

            normalizedExponent = exponent.normalizedFraction();
        }
        normalizedExponent = normalizedExponent.reduce();

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

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces, iterations);

        Result<Number> result;
        Number n = number;

        QuaternaryOperation<Number, Result<Number>> rootFunction =
            (QuaternaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NTH_ROOT_FUNCTION);
        result = rootFunction.calculate(n, exponent.denominator(), iterations, decimalPlaces);

        n = result.result();
        if (n.isFraction()) {

            n = n.round(processingDetails);
        }

        TernaryOperation<Number, Result<Number>> exponentiateFunction =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION);
        result = exponentiateFunction.calculate(n, exponent.numerator(), decimalPlaces);

        n = result.result();
        if (n.isFraction()) {

            n = n.round(processingDetails);
        }

        return n;
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

        if (exponent.numerator().equals(exponent.denominator())) {

            return number.evaluate(decimalPlaces);
        }

        Result<Number> result;
        Number n = number.evaluate(decimalPlaces);

        QuaternaryOperation<Number, Result<Number>> rootFunction =
            (QuaternaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NTH_ROOT_FUNCTION);
        result = rootFunction.calculate(n, exponent.denominator(), iterations, decimalPlaces);

        n = result.result();

        TernaryOperation<Number, Result<Number>> exponentiateFunction =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION);
        result = exponentiateFunction.calculate(n, exponent.numerator(), decimalPlaces);

        n = result.result();

        return n;
    }

}

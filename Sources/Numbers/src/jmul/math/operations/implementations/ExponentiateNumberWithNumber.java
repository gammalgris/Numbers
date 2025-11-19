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
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifiers;


/**
 * Implements the exponentiation function with exponents that are integers.
 *
 * @author Kristian Kutin
 */
public class ExponentiateNumberWithNumber implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public ExponentiateNumberWithNumber() {

        super();
    }

    /**
     * Exponentiates the specified number by the specified exponent.
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent (i.e. an integer)
     * @param decimalPlaces
     *        the precision
     *
     * @return the result
     */
    @Override
    public Result<Number> calculate(Number number, Number exponent, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(number, exponent, decimalPlaces);
        ParameterCheckHelper.checkIntegerIgnoreNull(exponent);
        ParameterCheckHelper.checkPositiveInteger(decimalPlaces);

        int base = number.base();
        final Number ZERO = Math.ZERO.value(base);
        final Number ONE = Math.ONE.value(base);

        if (exponent.isZero()) {

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

            Number absoluteExponent = exponent.absoluteValue();
            Fraction reciprocal = number.reciprocal();
            result = exponentiate(reciprocal, absoluteExponent, decimalPlaces);

        } else {

            result = exponentiate(number, exponent, decimalPlaces);
        }

        return new Result<Number>(result);
    }

    /**
     * Exponentiates the specified number by the specified exponent.
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent (i.e. a positive integer greater than or equal to one)
     * @param decimalPlaces
     *        the precision
     *
     * @return the result
     */
    private Number exponentiate(Number number, Number exponent, Number decimalPlaces) {

        int base = number.base();
        final Number ONE = Math.ONE.value(base);

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.ROUND_NUMBER_TO_ODD_FUNCTION, decimalPlaces,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        Number counter = exponent;
        Number result = ONE;

        while (!counter.isZero()) {

            result = result.multiply(number);
            result = result.round(processingDetails);

            if (result.isZero()) {

                break;
            }

            counter = counter.dec();
        }

        return result;
    }

    /**
     * Exponentiates the specified number (i.e. reciprocal of a number) by the specified exponent.
     *
     * @param reciprocal
     *        the reciprocal of a number
     * @param exponent
     *        an exponent (i.e. a positive integer greater than or equal to one)
     * @param decimalPlaces
     *        the precision
     *
     * @return the result
     */
    private Number exponentiate(Fraction reciprocal, Number exponent, Number decimalPlaces) {

        Number n = reciprocal.evaluate(decimalPlaces);

        return exponentiate(n, exponent, decimalPlaces);
    }

}

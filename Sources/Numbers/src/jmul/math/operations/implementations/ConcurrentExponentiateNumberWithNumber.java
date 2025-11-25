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
import jmul.math.concurrent.CalculationPool;
import jmul.math.concurrent.ConcurrencyConfiguration;
import jmul.math.concurrent.ConcurrentCalculation;
import jmul.math.concurrent.NextConstantFactorQueue;
import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.creation.CreationParameters.CLONE;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.processing.ProcessingDetails;


/**
 * Implements the exponentiation function with exponents that are integers.
 *
 * @author Kristian Kutin
 */
public class ConcurrentExponentiateNumberWithNumber implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public ConcurrentExponentiateNumberWithNumber() {

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

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        CalculationPool<Number, Number> threadPool = new ConcurrentMultiplicationPool();

        Number[] results = threadPool.calculateResultsAndWaitForThreads(number, exponent, decimalPlaces);

        Number factor1 = results[0];
        Number factor2 = results[1];

        Number product = factor1.multiply(processingDetails, factor2);

        return product;
    }

}


/**
 * An iplementation for concurrent ultiplication.
 *
 * @author Kristian Kutin
 */
class ConcurrentMultiplication extends ConcurrentCalculation<Number, Number> {

    /**
     * A factor queue.
     */
    private final NextConstantFactorQueue queue;

    /**
     * A precision.
     */
    private final Number decimalPlaces;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param queue
     *        a factor queue
     * @param initialValue
     *        the initial value for the calculation
     * @param decimalPlaces
     *        a precision
     */
    ConcurrentMultiplication(NextConstantFactorQueue queue, Number initialValue, Number decimalPlaces) {

        super(initialValue);

        this.queue = queue;
        this.decimalPlaces = decimalPlaces;
    }

    /**
     * The actual multiplication.
     *
     * @param initialValue
     *        an initial value
     *
     * @return the product
     */
    @Override
    protected Number calculate(Number initialValue) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        Number result = initialValue;

        while (queue.hasNext()) {

            Number factor = queue.next();

            result = result.multiply(processingDetails, factor);

            if (result.isZero() || result.isOne()) {

                break;
            }
        }

        return result;
    }

}


/**
 * An implementation for a calculation pool for concurrent multiplications.
 *
 * @author Kristian Kutin
 */
class ConcurrentMultiplicationPool extends CalculationPool<Number, Number> {

    /**
     * Creates a new result array according to the specified size.
     *
     * @param length
     *        an array size
     *
     * @return an empty result array
     */
    @Override
    protected Number[] newArray(int length) {

        return new Number[length];
    }

    /**
     * Creates all concurrent calculations (i.e. runnables).
     *
     * @param inputs
     *        numbers
     *
     * @return all concurrent calculations (i.e. runnables)
     */
    @Override
    protected ConcurrentCalculation<Number, Number>[] createConcurrentCalculations(Number... inputs) {

        Number factor = inputs[0];
        Number counter = inputs[1];
        Number decimalPlaces = inputs[2];

        int base = factor.base();

        Number initialValue = createNumber(base, "1");

        NextConstantFactorQueue queue = new NextConstantFactorQueue(factor, counter);

        int threads = ConcurrencyConfiguration.getConcurrentMultiplications();
        ConcurrentCalculation<Number, Number>[] calculations = new ConcurrentMultiplication[threads];

        for (int index = 0; index < threads; index++) {

            calculations[index] = new ConcurrentMultiplication(queue, initialValue, decimalPlaces);
        }

        return calculations;
    }

}

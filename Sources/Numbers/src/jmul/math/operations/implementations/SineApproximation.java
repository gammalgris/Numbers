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
import jmul.math.numbers.Number;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.processing.ProcessingDetails;


/**
 * An implementation of an algorithm that approximates the sine function
 * (see <a href="https://en.wikipedia.org/wiki/Sine_and_cosine">sine and cosine</a>).<br>
 * <br>
 * sin(x) = &Sigma; (-1)<sup>n</sup> / ( 2 * n + 1)! * x<sup>(2 * n + 1)</sup><br>
 * <br>
 * with n ranging from 0 to infinity.
 *
 * TODO not working good enough
 * 
 * @author Kristian Kutin
 */
public class SineApproximation implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public SineApproximation() {

        super();
    }

    /**
     * Calculates an approximation for the sine function.
     *
     * @param x
     *        an input value
     * @param iterations
     *        an iteration depth
     * @param decimalPlaces
     *        a precision
     *
     * @return the sine for x (i.e. sin(x) )
     */
    @Override
    public Result<Number> calculate(Number x, Number iterations, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(x, iterations, decimalPlaces);
        ParameterCheckHelper.checkPositiveInteger(iterations);
        ParameterCheckHelper.checkPositiveInteger(decimalPlaces);

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, decimalPlaces,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        int base = x.base();

        final Number ZERO = Math.ZERO.value(base);

        Number n = ZERO;
        Number interationCounter = iterations;
        Number sum = ZERO;

        while (!interationCounter.isZero()) {

            Number next = next(processingDetails, n, x);
            sum = sum.add(next);

            n = n.inc();
            interationCounter = interationCounter.dec();
        }

        return new Result<Number>(sum);
    }

    /**
     * Calclates the next number in the approximation series.<br>
     * <br>
     * sin(x) = &Sigma; (-1)<sup>n</sup> / ( 2 * n + 1)! * x<sup>(2 * n + 1)</sup><br>
     * <br>
     * with n ranging from 0 to infinity
     *
     * @param processingDetails
     *        processing details
     * @param n
     *        a number
     * @param x
     *        a number
     * @return
     */
    private Number next(ProcessingDetails processingDetails, Number n, Number x) {

        int base = n.base();

        final Number ONE = Math.ONE.value(base);
        final Number TWO = Math.TWO.value(base);
        final Number MINUS_ONE = Math.MINUS_ONE.value(base);

        Number oddNumber = ONE.add(TWO.multiply(n));

        Number term1 = MINUS_ONE.exponentiate(processingDetails, n);
        Number term2 = oddNumber.factorial();
        Number term3 = x.exponentiate(processingDetails, oddNumber);

        Number result = (term1.divide(processingDetails, term2)).multiply(processingDetails, term3);

        return result;
    }

}

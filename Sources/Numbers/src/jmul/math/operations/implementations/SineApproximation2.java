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
 * An implementation of an algorithm that approximates the sine function in radian measure
 * (see <a href="https://en.wikipedia.org/wiki/Sine_and_cosine">sine and cosine</a> and
 * <a href="https://en.wikipedia.org/wiki/Bh%C4%81skara_I%27s_sine_approximation_formula">Bhaskara</a>).<br>
 * <br>
 * sin(x) = ( 16 * x * ( Pi - x) ) / ( 5 * Pi<sup>2</sup> - 4 * x * ( Pi - x ) )<br>
 * <br>
 *
 * @author Kristian Kutin
 */
public class SineApproximation2 implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public SineApproximation2() {

        super();
    }

    /**
     * Calculates an approximation for the sine function in radian measure.
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

        // sin(x) = ( 16 * x * ( Pi - x) ) / ( 5 * Pi^2 - 4 * x * ( Pi - x ) )

        final Number FOUR;
        final Number FIVE;
        final Number SIXTEEN;
        final Number PI = Math.PI.value(base);
        {
            final Number TWO = Math.TWO.value(base);
            FOUR = TWO.doubling();
            FIVE = FOUR.inc();
            SIXTEEN = FOUR.multiply(FOUR);
        }

        Number term1 = PI.subtract(x);

        Number term2 = (SIXTEEN.multiply(x)).multiply(term1);

        Number term3 = (FOUR.multiply(x)).multiply(term1);
        Number term4 = ((PI.square()).multiply(FIVE)).subtract(term3);

        Number result = term2.divide(processingDetails, term4);

        return new Result<Number>(result);
    }

}

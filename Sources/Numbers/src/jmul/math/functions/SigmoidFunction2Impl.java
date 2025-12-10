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

package jmul.math.functions;


import jmul.math.Math;
import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;
import jmul.math.operations.processing.ProcessingDetails;


/**
 * This class implements a sigmoid function.<br>
 * <br>
 * f(x) = e<sup>x</sup> / ( 1 + e<sup>x</sup> )<br>
 *
 * @author Kristian Kutin
 */
public class SigmoidFunction2Impl extends FunctionBaseImpl {

    /**
     * Euler's number.<br>
     * <br>
     * <i>Note:<br>
     * Euler's number is cached in order to improve performance.</i>
     */
    private final Number e;

    /**
     * Creates a new sigmoid function for the specified number base.
     *
     * @param base
     *        a number base
     */
    protected SigmoidFunction2Impl(int base) {

        super(base);

        this.e = Math.e(base);
    }

    /**
     * Calculate the function value for x.
     *
     * @param x
     *        the input value
     *
     * @return f(x)
     */
    @Override
    public Number calculate(Number x) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return calculate(processingDetails, x);
    }

    /**
     * Calculate the function value for x.
     *
     * @param processingDetails
     *        additonal processing details
     * @param x
     *        the input value
     *
     * @return f(x)
     */
    @Override
    public Number calculate(ProcessingDetails processingDetails, Number x) {

        final Number ONE = Math.ONE.value(base());

        Number result;
        {
            Fraction exponent = x.toFraction();

            Number term1 = e.round(processingDetails);
            term1 = term1.exponentiate(processingDetails, exponent);

            Number term2 = ONE.add(term1);

            result = term1.divide(processingDetails, term2);
        }

        return result;
    }

    /**
     * Returns the derivative function for this function.
     *
     * @return a derivative function
     */
    @Override
    public Function derivativeFunction() {

        return new SigmoidFunctionFirstDerivative(base());
    }

    /**
     * Returns a string representation for this function.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return "e^x / ( 1 + e^x )";
    }

}

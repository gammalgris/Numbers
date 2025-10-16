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
 * This class implements a hyperbolic tangent function.<br>
 * <br>
 * f(x) = ( 1 - e<sup>-x</sup> ) / ( 1 + e<sup>-x</sup> )<br>
 *
 * @author Kristian Kutin
 */
public class HyperbolicTangentFunctionImpl extends FunctionBaseImpl {

    /**
     * Creates a new hyperbolic tangent for the specified number base.
     *
     * @param base
     */
    protected HyperbolicTangentFunctionImpl(int base) {

        super(base);
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
        final Number e = Math.e(base());
        Fraction exponent = x.toFraction().reduce().negate();

        return (ONE.subtract(e.exponentiate(processingDetails, exponent))).divide(processingDetails,
                                                                                  ONE.add(e.exponentiate(processingDetails,
                                                                                                         exponent)));
    }

    /**
     * Returns the derivative function for this function.
     *
     * @return a derivative function
     */
    @Override
    public Function derivativeFunction() {

        return new HyperbolicTangentFunctionFirstDerivative(base());
    }

    /**
     * Returns a string representation for this function.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return "( 1 - e^-x ) / ( 1 + e^-x )";
    }

}


/**
 * An implementation of the first derivative of a hyperbolic tangent function.<br>
 * <br>
 * f'(x) = 2 * e<sup>x</sup> / ( e<sup>x</sup> + 1 )<sup>2</sup><br>
 *
 * @author Kristian Kutin
 */
class HyperbolicTangentFunctionFirstDerivative extends FunctionBaseImpl {

    /**
     * Creates a new function for the specified number base.
     *
     * @param base
     *        a number base
     */
    protected HyperbolicTangentFunctionFirstDerivative(int base) {

        super(base);
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
        final Number TWO = ONE.inc();
        final Number e = Math.e(base());
        Fraction exponent1 = x.toFraction().reduce();
        Fraction exponent2 = TWO.toFraction();

        return (TWO.multiply(e.exponentiate(processingDetails, exponent1))).divide(processingDetails,
                                                                                   ((e.exponentiate(processingDetails,
                                                                                                    exponent1)).add(ONE)).exponentiate(processingDetails,
                                                                                                                                       exponent2));
    }

    /**
     * Returns the derivative function for this function.
     *
     * @return a derivative function
     */
    @Override
    public Function derivativeFunction() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns a string representation for this function.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return "( 2 * e^x ) / ( e^x + 1 )^2";
    }

}

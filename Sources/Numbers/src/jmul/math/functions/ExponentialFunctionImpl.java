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


import jmul.math.numbers.Number;
import jmul.math.operations.implementations.ParameterCheckHelper;
import jmul.math.operations.processing.ProcessingDetails;


/**
 * An implementation of an exponential  nth root function f(x) = c<sub>1</sub><sup>x</sup> + c<sub>0</sub>.<br>
 * <br>
 * TODO need logarithms for the derivative function -&gt; f'(x) = c<sub>1</sub><sup>x</sup> * log(c<sub>1</sub>)
 *
 * @author Kristian Kutin
 */
public class ExponentialFunctionImpl extends FunctionBaseImpl {

    /**
     * A coefficient.
     */
    private final Number coefficient1;

    /**
     * A coefficient.
     */
    private final Number coefficient0;


    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param coefficient1
     *        a coefficient
     * @param coefficient0
     *        a coefficient
     */
    protected ExponentialFunctionImpl(Number coefficient1, Number coefficient0) {

        super(extractBase(coefficient1, coefficient0));

        if (coefficient1.isZero()) {

            throw new IllegalArgumentException("The first coefficient cannot be zero!");
        }

        if (coefficient1.isOne()) {

            throw new IllegalArgumentException("The first coefficient cannot be one!");
        }

        this.coefficient1 = coefficient1;
        this.coefficient0 = coefficient0;
    }

    /**
     * Tries to extract a number base from the specified parameters.
     *
     * @param coefficient
     *        a coefficient
     * @param exponent
     *        an exponent
     *
     * @return a number base
     */
    private static int extractBase(Number coefficient, Number exponent) {

        ParameterCheckHelper.checkParameters(coefficient, exponent);
        ParameterCheckHelper.checkPositiveInteger(exponent);

        return coefficient.base();
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

        return (coefficient1.exponentiate(x)).add(coefficient0);
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

        return ((coefficient1.exponentiate(processingDetails, x)).add(coefficient0)).round(processingDetails);
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

        return String.format("%s^x + %s", coefficient1, coefficient0);
    }

}

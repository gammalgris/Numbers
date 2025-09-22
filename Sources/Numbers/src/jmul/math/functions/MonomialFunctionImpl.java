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


/**
 * An implementation of a monomial function f(x) = c * x<sup>n</sup>.
 *
 * @author Kristian Kutin
 */
public class MonomialFunctionImpl extends FunctionBaseImpl {

    /**
     * A coefficient.
     */
    private final Number coefficient;

    /**
     * An exponent.
     */
    private final Number exponent;

    /**
     * Creates a new instance according to the specified parameters.
     *
     * @param coefficient
     *        a coefficient
     * @param exponent
     *        an exponent
     */
    MonomialFunctionImpl(Number coefficient, Number exponent) {

        super(extractBase(coefficient, exponent));

        this.coefficient = coefficient;
        this.exponent = exponent;
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
     * Calculates the output value according to the specified input value.
     *
     * @param number
     *        a value
     *
     * @return an output value
     */
    @Override
    public Number calculate(Number number) {

        Number result = coefficient;

        if (exponent.isZero()) {

            return result;
        }

        Number counter = exponent;

        while (!counter.isZero()) {

            result = result.multiply(number);
            counter = counter.dec();
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

        Number newCoefficient = coefficient.multiply(exponent);
        Number newExponent = exponent.dec();

        if (exponent.isZero()) {

            return new MonomialFunctionImpl(newCoefficient, exponent);
        }

        return new MonomialFunctionImpl(newCoefficient, newExponent);
    }

    /**
     * Returns a string representation for this function.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        if (coefficient.isZero() || exponent.isZero()) {

            return coefficient.toString();
        }

        if (exponent.isOne()) {

            return String.format("%s * x", coefficient);
        }

        return String.format("%s * x^%s", coefficient, exponent);
    }

}

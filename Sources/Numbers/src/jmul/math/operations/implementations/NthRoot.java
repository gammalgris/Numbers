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


import jmul.math.functions.Function;
import jmul.math.functions.FunctionHelper;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.creation.CreationParameters;
import jmul.math.operations.QuaternaryOperation;
import jmul.math.operations.Result;


/**
 * An implementation to calculate the nth root of a number.<br>
 * See <a href="https://en.wikipedia.org/wiki/Nth_root">Nth Root</a>.
 * <br>
 * x<sub>k+1</sub> = x<sub>k</sub> - ( x<sub>k</sub><sup>n</sup> - A ) / ( n * k<sub>k</sub><sup>n - 1</sup> )<br>
 * <br>
 * The recursive equation calculates the nth root of a number A.
 *
 * @author Kristian Kutin
 */
public class NthRoot implements QuaternaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public NthRoot() {

        super();
    }

    /**
     * Calculates the square root of the specified number according to the specified precision.
     *
     * @param number
     *        a number
     * @param n
     *        the root
     * @param iterations
     *        the number of iterations
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return the square root
     */
    @Override
    public Result<Number> calculate(Number number, Number n, Number iterations, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(number, n, iterations, decimalPlaces);
        ParameterCheckHelper.checkIntegerIgnoreNull(n);
        ParameterCheckHelper.checkIntegerIgnoreNull(iterations);
        ParameterCheckHelper.checkIntegerIgnoreNull(decimalPlaces);

        if (number.isNegative()) {

            throw new UnsupportedOperationException("The specified number is negative. Imaginary numbers are not supported!");
        }

        if (n.isNegative() || n.isZero()) {

            throw new IllegalArgumentException("The specified root must be a positive integer!");
        }

        if (iterations.isNegative() || iterations.isZero()) {

            throw new IllegalArgumentException("The specified iterations must be a positive integer!");
        }

        if (decimalPlaces.isNegative() || decimalPlaces.isZero()) {

            throw new IllegalArgumentException("The specified precision must be a positive integer!");
        }

        if (number.isInfinity()) {

            Number clone = createNumber(CreationParameters.CLONE, number);
            return new Result<Number>(clone);
        }

        if (n.isOne()) {

            Number clone = createNumber(CreationParameters.CLONE, number);
            return new Result<Number>(clone);
        }


        Number i = iterations;
        Number s = number;
        Number x = x0(s);

        while (!i.isZero()) {

            x = f(x, number, n, decimalPlaces);
            i = i.dec();
        }

        x = x.round(decimalPlaces);

        return new Result<Number>(x);
    }

    /**
     * Calculates a starting value for s.
     *
     * @param s
     *        the number for which the square root should be calculated
     *
     * @return a starting calue for Heron's method
     */
    private static Number x0(Number s) {

        //return s.shiftLeft();
        return s.halving();
    }

    /**
     * Calculates the next value for x (i.e.
     * x<sub>k+1</sub> = x<sub>k</sub> - ( x<sub>k</sub><sup>n</sup> - A ) / ( n * k<sub>k</sub><sup>n - 1</sup> ) ).
     *
     * @param x
     *        the current value of x<sub>n</sub>
     * @param number
     *        the number for which to calculate the nth root
     * @param n
     *        the root
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return an approximation for the nth root
     */
    private static Number f(Number x, Number number, Number n, Number decimalPlaces) {

        int base = x.base();

        Number term1;
        {
            final Number ONE = createNumber(base, "1");

            Function monomial = FunctionHelper.createMonomialFunction(ONE, n);

            term1 = monomial.calculate(x);
            term1 = term1.subtract(number);
        }

        Number term2;
        {
            Number exponent = n.dec();
            Function monomial = FunctionHelper.createMonomialFunction(n, exponent);
            term2 = monomial.calculate(x);
        }

        Number result = term1;
        result = result.divide(term2, decimalPlaces);
        result = result.negate();
        result = result.add(x);

        return result;
    }

}

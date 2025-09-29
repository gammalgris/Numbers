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


import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.creation.CreationParameters;
import jmul.math.operations.ProcessingDetails;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.repository.OperationIdentifiers;


/**
 * An implementation to calculate the square root of a number according to Heron's method.<br>
 * See <a href="https://en.wikipedia.org/wiki/Square_root_algorithms#Heron's_method">Heron's method</a>.
 * <br>
 * x<sub>n+1</sub> = 1/2 * ( x<sub>n</sub> + s * 1/x<sub>n</sub> )
 *
 * @author Kristian Kutin
 */
public class SquareRoot implements TernaryOperation<Number, Result<Number>> {

    /**
     * The default constructor.
     */
    public SquareRoot() {

        super();
    }

    /**
     * Calculates the square root of the specified number according to the specified precision.
     *
     * @param number
     *        a number
     * @param iterations
     *        the number of iterations
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return the square root
     */
    @Override
    public Result<Number> calculate(Number number, Number iterations, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(number, iterations, decimalPlaces);
        ParameterCheckHelper.checkIntegerIgnoreNull(iterations);
        ParameterCheckHelper.checkIntegerIgnoreNull(decimalPlaces);

        if (number.isNegative()) {

            throw new UnsupportedOperationException("The specified number is negative. Imaginary numbers are not supported!");
        }

        if (iterations.isNegative() || iterations.isZero()) {

            throw new IllegalArgumentException("The specified iterations must be a positive integer!");
        }

        if (decimalPlaces.isNegative() || decimalPlaces.isZero()) {

            throw new IllegalArgumentException("The specified precision must be a positive integer!");
        }

        if (number.isInfinity() || number.isZero() || number.isOne()) {

            Number clone = createNumber(CreationParameters.CLONE, number);
            return new Result<Number>(clone);
        }

        Number i = iterations;
        Number s = number;
        Number x = x0(s);

        while (!i.isZero()) {

            x = f(x, s, decimalPlaces);
            i = i.dec();
        }

        ProcessingDetails processingDetails =
            new ProcessingDetails(OperationIdentifiers.ROUND_NUMBER_TO_ODD_FUNCTION, decimalPlaces);
        x = x.round(processingDetails);

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

        return s.shiftLeft();
    }

    /**
     * Calculates the next value for x (i.e. f(x<sub>n+1</sub>) = 1/2 * ( x<sub>n</sub> + s * 1/x<sub>n</sub> ).
     *
     * @param x
     *        the current value of x<sub>n</sub>
     * @param s
     *        the number for which the square root is to be calculated
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return an approximation for the square root
     */
    private static Number f(Number x, Number s, Number decimalPlaces) {

        int base = x.base();

        final Number ONE = createNumber(base, "1");
        final Number TWO = ONE.inc();

        ProcessingDetails processingDetails =
            new ProcessingDetails(OperationIdentifiers.RUSSIAN_DIVISION_FUNCTION, decimalPlaces);

        Number result = s;
        result = result.divide(processingDetails, x);
        result = result.add(x);
        result = result.divide(processingDetails, TWO);

        return result;
    }

}

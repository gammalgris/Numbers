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
import jmul.math.fractions.Fraction;
import jmul.math.operations.implementations.ParameterCheckHelper;
import jmul.math.operations.processing.ProcessingDetails;


/**
 * An implementation of a nth root function f(x) = c * x<sup>m/n</sup>.
 *
 * @author Kristian Kutin
 */
public class RootFunctionImpl extends FunctionBaseImpl {

    /**
     * A coefficient.
     */
    private final Number coefficient;

    /**
     * An exponent.
     */
    private final Fraction exponent;

    /**
     * Creates a new root function according to the specified parameters.
     *
     * @param coefficient
     *        a coefficient
     * @param exponent
     *        an exponent
     */
    RootFunctionImpl(Number coefficient, Fraction exponent) {

        super(extractBase(coefficient, exponent));

        this.coefficient = coefficient;
        this.exponent = exponent.reduce();
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
    private static int extractBase(Number coefficient, Fraction exponent) {

        ParameterCheckHelper.checkParameters(coefficient, exponent);

        if (exponent.hasIntegerPart()) {

            throw new IllegalArgumentException("Normalize the fraction part of the exponent!");
        }

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

        return coefficient.multiply(x.exponentiate(exponent));
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

        return coefficient.multiply(x.exponentiate(processingDetails, exponent));
    }

    /**
     * Returns the derivative function for this function.
     *
     * @return a derivative function
     */
    @Override
    public Function derivativeFunction() {

        //TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a string representation for this function.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        if (coefficient.isZero()) {

            return coefficient.toString();
        }

        if (exponent.numerator().isZero() && !exponent.denominator().isZero()) {

            return exponent.numerator().toString();
        }

        return String.format("%s * x^(%s)", coefficient, exponent);
    }

}

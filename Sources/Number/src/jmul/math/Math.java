/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package jmul.math;


import jmul.math.functions.FunctionSingletons;
import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * A helper class for using the custom number class.
 *
 * @author Kristian Kutin
 */
public final class Math {

    /**
     * The default constructor.
     */
    private Math() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the absolute value of the specified number.
     *
     * @param n
     *        a number
     *
     * @return the absolute value
     */
    public static Number absoluteValue(Number n) {

        if (n == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        return n.absoluteValue();
    }

    /**
     * Adds the specified summands and returns the sum.
     *
     * @param firstSummand
     *        a number
     * @param secondSummand
     *        a number
     *
     * @return a number
     */
    public static Number add(Number firstSummand, Number secondSummand) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADDITION_FUNCTION);
        Result<Number> result = function.calculate(firstSummand, secondSummand);

        return result.result();
    }

    /**
     * Negates the specified number (i.e. changes the sign).
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    public static Number negate(Number n) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.NEGATE_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(n);

        return result.result();
    }

    /**
     * Parses the specified string and returns a number. The number base is 10.
     *
     * @param s
     *        a string representing a number
     *
     * @return a number
     */
    public static Number parseNumber(String s) {

        return parseNumber(10, s);
    }

    /**
     * Parses the specified string and returns a number.
     *
     * @param base
     *        the number base
     * @param s
     *        a string representing a number
     *
     * @return a number
     */
    public static Number parseNumber(int base, String s) {

        return new NumberImpl(base, s);
    }

    /**
     * Substracts the specified subtrahend from the specified minuend and returns the difference.
     *
     * @param minuend
     *        a number
     * @param subtrahend
     *        a number
     *
     * @return a number
     */
    public static Number subtract(Number minuend, Number subtrahend) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACTION_FUNCTION);
        Result<Number> result = function.calculate(minuend, subtrahend);

        return result.result();
    }

    /**
     * Halves the specified number.
     *
     * @return a number
     */
    public static Number halving(Number n) {

        //TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Doubles the specified number.
     *
     * @return a number
     */
    public static Number doubling(Number n) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.DOUBLING_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(n);

        return result.result();
    }

}

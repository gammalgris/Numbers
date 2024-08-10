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


import java.util.Comparator;

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
     * @param n
     *        a number
     *
     * @return a number
     */
    public static Number halving(Number n) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.HALVING_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(n);

        return result.result();
    }

    /**
     * Doubles the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    public static Number doubling(Number n) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.DOUBLING_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(n);

        return result.result();
    }

    /**
     * Returns a truncated number (i.e. where the fractional part is removed).
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    public static Number truncate(Number n) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.TRUNCATE_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(n);

        return result.result();
    }

    /**
     * Returns a number where the decimal point is shifted to the left by one digit.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    public static Number shiftLeft(Number n) {

        if (n == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        final Number ONE = Math.parseNumber(n.base(), "1");

        return shiftLeft(n, ONE);
    }

    /**
     * Returns a number where the decimal point is shifted to the left by the number of specified
     * shifts.
     *
     * @param n
     *        a number
     * @param shifts
     *        the nuber of shifts to be performed. Zero will perform no shift. A positive number
     *        will perform shifts to the left. A negative number will perform shifts to the right.
     *
     * @return a number
     */
    public static Number shiftLeft(Number n, Number shifts) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.SHIFT_LEFT_FUNCTION);
        Result<Number> result = function.calculate(n, shifts);

        return result.result();
    }

    /**
     * Returns a number where the decimal point is shifted to the right by one digit.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    public static Number shiftRight(Number n) {

        if (n == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        final Number ONE = Math.parseNumber(n.base(), "1");

        return shiftRight(n, ONE);
    }

    /**
     * Returns a number where the decimal point is shifted to the right by the number of specified
     * shifts.
     *
     * @param n
     *        a number
     * @param shifts
     *        the nuber of shifts to be performed. Zero will perform no shift. A positive number
     *        will perform shifts to the right. A negative number will perform shifts to the left.
     *
     * @return a number
     */
    public static Number shiftRight(Number n, Number shifts) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.SHIFT_RIGHT_FUNCTION);
        Result<Number> result = function.calculate(n, shifts);

        return result.result();
    }

    /**
     * Returns the complement of the specified number.
     *
     * @param n
     *        a number
     *
     * @return a number
     */
    public static Number complement(Number n) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_COMPLEMENT_FUNCTION);
        Result<Number> result = function.calculate(n);

        return result.result();
    }

    /**
     * Compares the specified numbers and returns <code>1</code> if this number is greater then the specified
     * number, <code>0</code> if this number is equal to the specified number and <code>-1</code> if this number
     * is smaller than the specified number.
     *
     * @param n1
     *        a nuber
     * @param n2
     *        a number
     *
     * @return <code>1</code> if this number is greater then the specified number, <code>0</code> if this number
     *         is equal to the specified number and <code>-1</code> if this number is smaller than the specified
     *         number
     */
    public static int compare(Number n1, Number n2) {

        Comparator<Number> function =
            (Comparator<Number>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_COMPARATOR_FUNCTION);
        int result = function.compare(n1, n2);

        return result;
    }

    /**
     * Compares the specified numbers and returns the bigger number.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     *
     * @return a number
     */
    public static Number max(Number n1, Number n2) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MAX_FUNCTION);
        Result<Number> result = function.calculate(n1, n2);

        return result.result();
    }

    /**
     * Compares the specified numbers and returns the lesser number.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     *
     * @return a number
     */
    public static Number min(Number n1, Number n2) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MIN_FUNCTION);
        Result<Number> result = function.calculate(n1, n2);

        return result.result();
    }

    /**
     * Increments the specified number by one.
     *
     * @param number
     *        a number
     *
     * @return a number
     */
    public static Number inc(Number number) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.INCREMENT_FUNCTION);
        Result<Number> result = function.calculate(number);

        return result.result();
    }

    /**
     * Decrements the specified number by one.
     *
     * @param number
     *        a number
     *
     * @return a number
     */
    public static Number dec(Number number) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.DECREMENT_FUNCTION);
        Result<Number> result = function.calculate(number);

        return result.result();
    }

    /**
     * Checks if the specified number is an even integer.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if the specified number is an even integer, else <code>false</code>
     */
    public static boolean isEven(Number number) {

        UnaryOperation<Number, Result<Boolean>> function =
            (UnaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.EVEN_NUMBER_FUNCTION);
        Result<Boolean> result = function.calculate(number);

        return result.result();
    }

    /**
     * Checks if the specified number is an odd integer.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if the specified number is an odd integer, else <code>false</code>
     */
    public static boolean isOdd(Number number) {

        UnaryOperation<Number, Result<Boolean>> function =
            (UnaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.ODD_NUMBER_FUNCTION);
        Result<Boolean> result = function.calculate(number);

        return result.result();
    }

    /**
     * Multiplies the specified numbers.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     *
     * @return a number
     */
    public static Number multiply(Number n1, Number n2) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLICATION_FUNCTION);
        Result<Number> result = function.calculate(n1, n2);

        return result.result();
    }

    /**
     * Checks if this number is greater than the specified number.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return <code>true</code> if this number is greater than the specified number, else <code>false</code>
     */
    public static boolean isGreater(Number number1, Number number2) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.GREATER_COMPARISON);
        Result<Boolean> result = function.calculate(number1, number2);

        return result.result();
    }

    /**
     * Checks if this number is greater than or equal to the specified number.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return <code>true</code> if this number is greater than or equal to the specified number, else <code>false</code>
     */
    public static boolean isGreaterOrEqual(Number number1, Number number2) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.GREATER_OR_EQUAL_COMPARISON);
        Result<Boolean> result = function.calculate(number1, number2);

        return result.result();
    }

    /**
     * Checks if this number is lesser than the specified number.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return <code>true</code> if this number is lesser than the specified number, else <code>false</code>
     */
    public static boolean isLesser(Number number1, Number number2) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.LESSER_COMPARISON);
        Result<Boolean> result = function.calculate(number1, number2);

        return result.result();
    }

    /**
     * Checks if this number is lesser than or equal to the specified number.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return <code>true</code> if this number is lesser than or equal to the specified number, else <code>false</code>
     */
    public static boolean isLesserOrEqual(Number number1, Number number2) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.LESSER_OR_EQUAL_COMPARISON);
        Result<Boolean> result = function.calculate(number1, number2);

        return result.result();
    }

}

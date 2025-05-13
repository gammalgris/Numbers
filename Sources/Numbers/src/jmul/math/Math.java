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

import jmul.math.fractions.Fraction;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.NumberImpl;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;


/**
 * A helper class for using the custom number class.
 *
 * @author Kristian Kutin
 */
public final class Math {

    /**
     * A default length for the fractional part.<br>
     * <br>
     * <i>Note:<br>
     * In several cases it is useful to cut the fractional part instead of running through end endless loop.</i>
     */
    public static Number DEFAULT_MAXIMUM_FRACTION_LENGTH;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_MAXIMUM_FRACTION_LENGTH = createNumber(10, "10");
    }

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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_NUMBERS_TRIM_RESULT_FUNCTION);
        Result<Number> result = function.calculate(firstSummand, secondSummand);

        return result.result();
    }

    /**
     * Adds the specified summands and returns the sum.
     *
     * @param firstSummand
     *        a fraction
     * @param secondSummand
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction add(Fraction firstSummand, Fraction secondSummand) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_FRACTIONS_FUNCTION);
        Result<Fraction> result = function.calculate(firstSummand, secondSummand);

        return result.result();
    }

    /**
     * Adds the specified summands and returns the sum.
     *
     * @param firstSummand
     *        a number
     * @param secondSummand
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction add(Number firstSummand, Fraction secondSummand) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_NUMBER_AND_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(firstSummand, secondSummand);

        return result.result();
    }

    /**
     * Adds the specified summands and returns the sum.
     *
     * @param firstSummand
     *        a fraction
     * @param secondSummand
     *        a number
     *
     * @return a fraction
     */
    public static Fraction add(Fraction firstSummand, Number secondSummand) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_FRACTION_AND_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(firstSummand, secondSummand);

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
     * Negates the specified fraction (i.e. changes the sign).
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction negate(Fraction f) {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.NEGATE_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(f);

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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_NUMBERS_FUNCTION);
        Result<Number> result = function.calculate(minuend, subtrahend);

        return result.result();
    }

    /**
     * Substracts the specified subtrahend from the specified minuend and returns the difference.
     *
     * @param minuend
     *        a fraction
     * @param subtrahend
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction subtract(Fraction minuend, Fraction subtrahend) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_FRACTIONS_FUNCTION);
        Result<Fraction> result = function.calculate(minuend, subtrahend);

        return result.result();
    }

    /**
     * Substracts the specified subtrahend from the specified minuend and returns the difference.
     *
     * @param minuend
     *        a fraction
     * @param subtrahend
     *        a number
     *
     * @return a fraction
     */
    public static Fraction subtract(Fraction minuend, Number subtrahend) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_FRACTION_AND_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(minuend, subtrahend);

        return result.result();
    }

    /**
     * Substracts the specified subtrahend from the specified minuend and returns the difference.
     *
     * @param minuend
     *        a number
     * @param subtrahend
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction subtract(Number minuend, Fraction subtrahend) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_NUMBER_AND_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(minuend, subtrahend);

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
     * @return a truncated number
     */
    public static Number removeFractionPart(Number n) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.REMOVE_FRACTION_PART_FUNCTION);
        Result<Number> result = function.calculate(n);

        return result.result();
    }

    /**
     * Returns a truncated number (i.e. where the integer part is removed).
     *
     * @param n
     *        a number
     *
     * @return a truncated number
     */
    public static Number removeIntegerPart(Number n) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.REMOVE_INTEGER_PART_FUNCTION);
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
     * Compares the specified numbers and returns <code>1</code> if the first number is greater than the second
     * number, <code>0</code> if the first number is equal to the second number and <code>-1</code> if the first number
     * is smaller than the second number.
     *
     * @param n1
     *        a nuber
     * @param n2
     *        a number
     *
     * @return <code>1</code> if the first number is greater than the second number, <code>0</code> if the first
     *         number is equal to the second number and <code>-1</code> if the first number is smaller than the second
     *         number
     */
    public static int compare(Number n1, Number n2) {

        Comparator<Number> function =
            (Comparator<Number>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_COMPARATOR_FUNCTION);
        int result = function.compare(n1, n2);

        return result;
    }

    /**
     * Compares the specified fractions and returns <code>1</code> if the first fraction is greater than the second
     * fraction, <code>0</code> if the first fraction is equal to the second fraction and <code>-1</code> if the first
     * fraction is smaller than the second fraction.
     *
     * @param n1
     *        a fraction
     * @param n2
     *        a fraction
     *
     * @return <code>1</code> if the first fraction is greater than the second fraction, <code>0</code> if the first
     *         fraction is equal to the second fraction and <code>-1</code> if the first fraction is smaller than the
     *         second fraction
     */
    public static int compare(Fraction n1, Fraction n2) {

        Comparator<Fraction> function =
            (Comparator<Fraction>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_COMPARATOR_FUNCTION);
        int result = function.compare(n1, n2);

        return result;
    }

    /**
     * Compares the specified numbers and returns the greater number.
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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MAX_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(n1, n2);

        return result.result();
    }

    /**
     * Compares the specified fractions and returns the greater fraction.
     *
     * @param f1
     *        a fraction
     * @param f2
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction max(Fraction f1, Fraction f2) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MAX_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(f1, f2);

        return result.result();
    }

    /**
     * Compares this fraction and the specified number and returns the greater number (as fraction).
     *
     * @param f
     *        a fraction
     * @param n
     *        a number
     *
     * @return a fraction
     */
    public static Fraction max(Fraction f, Number n) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MAX_FRACTION_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(f, n);

        return result.result();
    }

    /**
     * Compares this number and the specified fraction and returns the greater number (as fraction).
     *
     * @param n
     *        a number
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction max(Number n, Fraction f) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MAX_NUMBER_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(n, f);

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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MIN_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(n1, n2);

        return result.result();
    }

    /**
     * Compares the specified fractions and returns the lesser fraction.
     *
     * @param f1
     *        a fraction
     * @param f2
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction min(Fraction f1, Fraction f2) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MIN_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(f1, f2);

        return result.result();
    }

    /**
     * Compares this fraction and the specified number and returns the smaller number (as fraction).
     *
     * @param f
     *        a fraction
     * @param n
     *        a number
     *
     * @return an expression
     */
    public static Fraction min(Fraction f, Number n) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MIN_FRACTION_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(f, n);

        return result.result();
    }

    /**
     * Compares this number and the specified fraction and returns the smaller number (as fraction).
     *
     * @param n
     *        a number
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction min(Number n, Fraction f) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MIN_NUMBER_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(n, f);

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
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_INCREMENT_FUNCTION);
        Result<Number> result = function.calculate(number);

        return result.result();
    }

    /**
     * Increments the specified fraction by one.
     *
     * @param fraction
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction inc(Fraction fraction) {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_INCREMENT_FUNCTION);
        Result<Fraction> result = function.calculate(fraction);

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
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_DECREMENT_FUNCTION);
        Result<Number> result = function.calculate(number);

        return result.result();
    }

    /**
     * Decrements the specified fraction by one.
     *
     * @param fraction
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction dec(Fraction fraction) {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_DECREMENT_FUNCTION);
        Result<Fraction> result = function.calculate(fraction);

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
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_NUMBERS_FUNCTION);
        Result<Number> result = function.calculate(n1, n2);

        return result.result();
    }

    /**
     * Divides the first specified number by the second specified number and returns the remainder of the division.
     *
     * @param n1
     *        an integer
     * @param n2
     *        an integer
     *
     * @return the remainder of the division
     */
    public static Number modulo(Number n1, Number n2) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.MODULO_FUNCTION);
        Result<Number> result = function.calculate(n1, n2);

        return result.result();
    }

    /**
     * Divides the first specified number by the second specified number and returns the result of the division.
     *
     * @param n1
     *        an integer
     * @param n2
     *        an integer
     *
     * @return the result of the division
     */
    public static Number diviso(Number n1, Number n2) {

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIVISO_FUNCTION);
        Result<Number> result = function.calculate(n1, n2);

        return result.result();
    }

    /**
     * Multiplies the specified fractions.
     *
     * @param f1
     *        a fraction
     * @param f2
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction multiply(Fraction f1, Fraction f2) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_FRACTIONS_FUNCTION);
        Result<Fraction> result = function.calculate(f1, f2);

        return result.result();
    }

    /**
     * Multiplies the specified operands.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction multiply(Number operand1, Fraction operand2) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_NUMBER_AND_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Multiplies the specified operands.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a number
     *
     * @return a fraction
     */
    public static Fraction multiply(Fraction operand1, Number operand2) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_FRACTION_AND_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Divides the specified first operand by the specified second operand.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     *
     * @return a fraction
     */
    public static Fraction divide(Number operand1, Number operand2) {

        BinaryOperation<Number, Result<Fraction>> function =
            (BinaryOperation<Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIVIDE_NUMBERS_RETURN_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Divides the specified first operand by the specified second operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction divide(Fraction operand1, Fraction operand2) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIVIDE_FRACTIONS_FUNCTION);
        Result<Fraction> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Divides the specified first operand by the specified second operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a number
     *
     * @return a fraction
     */
    public static Fraction divide(Fraction operand1, Number operand2) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIVIDE_FRACTION_BY_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Divides the specified first operand by the specified second operand.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction divide(Number operand1, Fraction operand2) {

        MixedBinaryOperation<Number, Fraction, Result<Fraction>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIVIDE_NUMBER_BY_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first number is greater than the second number.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if the first number is greater than the second number, else <code>false</code>
     */
    public static boolean isGreater(Number operand1, Number operand2) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first number is greater than or equal to the second number.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if the first number is greater than or equal to the second number, else <code>false</code>
     */
    public static boolean isGreaterOrEqual(Number operand1, Number operand2) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first number is lesser than the second number.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if the first number is lesser than the second number, else <code>false</code>
     */
    public static boolean isLesser(Number operand1, Number operand2) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first number is lesser than or equal to the second number.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if the first number is lesser than or equal to the second number number, else <code>false</code>
     */
    public static boolean isLesserOrEqual(Number operand1, Number operand2) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first operand is greater than the second operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a fraction
     *
     * @return <code>true</code> if the first operand is greater than the second operand, else <code>false</code>
     */
    public static boolean isGreater(Fraction operand1, Fraction operand2) {

        BinaryOperation<Fraction, Result<Boolean>> function =
            (BinaryOperation<Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first operand is greater than or equal to the second operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a fraction
     *
     * @return <code>true</code> if the first operand is greater than or equal to the second operand, else <code>false</code>
     */
    public static boolean isGreaterOrEqual(Fraction operand1, Fraction operand2) {

        BinaryOperation<Fraction, Result<Boolean>> function =
            (BinaryOperation<Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first operand is lesser than the second operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a fraction
     *
     * @return <code>true</code> if the first operand is lesser than the second operand, else <code>false</code>
     */
    public static boolean isLesser(Fraction operand1, Fraction operand2) {

        BinaryOperation<Fraction, Result<Boolean>> function =
            (BinaryOperation<Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if this operand is lesser than or equal to the specified operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a fraction
     *
     * @return <code>true</code> if this operand is lesser than or equal to the specified operand, else <code>false</code>
     */
    public static boolean isLesserOrEqual(Fraction operand1, Fraction operand2) {

        BinaryOperation<Fraction, Result<Boolean>> function =
            (BinaryOperation<Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first operand is greater than the second operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if the first operand is greater than the second operand, else <code>false</code>
     */
    public static boolean isGreater(Fraction operand1, Number operand2) {

        MixedBinaryOperation<Fraction, Number, Result<Boolean>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_NUMBER_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first operand is greater than or equal to the second operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if the first operand is greater than or equal to the second operand, else <code>false</code>
     */
    public static boolean isGreaterOrEqual(Fraction operand1, Number operand2) {

        MixedBinaryOperation<Fraction, Number, Result<Boolean>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first operand is lesser than the second operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if the first operand is lesser than the second operand, else <code>false</code>
     */
    public static boolean isLesser(Fraction operand1, Number operand2) {

        MixedBinaryOperation<Fraction, Number, Result<Boolean>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_NUMBER_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if this operand is lesser than or equal to the specified operand.
     *
     * @param operand1
     *        a fraction
     * @param operand2
     *        a number
     *
     * @return <code>true</code> if this operand is lesser than or equal to the specified operand, else <code>false</code>
     */
    public static boolean isLesserOrEqual(Fraction operand1, Number operand2) {

        MixedBinaryOperation<Fraction, Number, Result<Boolean>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first operand is greater than the second operand.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a fraction
     *
     * @return <code>true</code> if the first operand is greater than the second operand, else <code>false</code>
     */
    public static boolean isGreater(Number operand1, Fraction operand2) {

        MixedBinaryOperation<Number, Fraction, Result<Boolean>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_FRACTION_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first operand is greater than or equal to the second operand.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a fraction
     *
     * @return <code>true</code> if the first operand is greater than or equal to the second operand, else <code>false</code>
     */
    public static boolean isGreaterOrEqual(Number operand1, Fraction operand2) {

        MixedBinaryOperation<Number, Fraction, Result<Boolean>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if the first operand is lesser than the second operand.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a fraction
     *
     * @return <code>true</code> if the first operand is lesser than the second operand, else <code>false</code>
     */
    public static boolean isLesser(Number operand1, Fraction operand2) {

        MixedBinaryOperation<Number, Fraction, Result<Boolean>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_FRACTION_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Checks if this operand is lesser than or equal to the specified operand.
     *
     * @param operand1
     *        a number
     * @param operand2
     *        a fraction
     *
     * @return <code>true</code> if this operand is lesser than or equal to the specified operand, else <code>false</code>
     */
    public static boolean isLesserOrEqual(Number operand1, Fraction operand2) {

        MixedBinaryOperation<Number, Fraction, Result<Boolean>> function =
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(operand1, operand2);

        return result.result();
    }

    /**
     * Returns the reciprocal of this fraction.
     *
     * @param operand
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction reciprocal(Fraction operand) {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.RECIPROCAL_OF_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(operand);

        return result.result();
    }

    /**
     * Returns the reciprocal of this number.
     *
     * @param operand
     *        a number
     *
     * @return a fraction
     */
    public static Fraction reciprocal(Number operand) {

        UnaryOperation<Number, Result<Fraction>> function =
            (UnaryOperation<Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.RECIPROCAL_OF_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(operand);

        return result.result();
    }

    /**
     * Translates this number into a number of the specified base.
     *
     * @param number
     *        a number
     * @param base
     *        the new base
     *
     * @return a number
     */
    public static Number rebase(Number number, int base) {

        MixedBinaryOperation<Number, Integer, Result<Number>> function =
            (MixedBinaryOperation<Number, Integer, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.REBASE_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(number, base);

        return result.result();
    }

    /**
     * Translates this fraction into a number of the specified base.
     *
     * @param fraction
     *        a fraction
     * @param base
     *        the new base
     *
     * @return a fraction
     */
    public static Fraction rebase(Fraction fraction, int base) {

        MixedBinaryOperation<Fraction, Integer, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Integer, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.REBASE_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(fraction, base);

        return result.result();
    }

    /**
     * Calculates the factorial for the specified number.
     *
     * @param number
     *        a positive integer
     *
     * @return the factorial for the specified number
     */
    public static Number factorial(Number number) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.FACTORIAL_FUNCTION);
        Result<Number> result = function.calculate(number);

        return result.result();
    }

}

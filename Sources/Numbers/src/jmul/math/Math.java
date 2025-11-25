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

import jmul.math.collections.Sequence;
import jmul.math.collections.Set;
import jmul.math.constants.Constant;
import jmul.math.constants.ConstantHelper;
import jmul.math.fractions.Fraction;
import jmul.math.matrices.Matrix;
import jmul.math.numbers.Number;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.MixedQuaternaryOperation;
import jmul.math.operations.OperationSingletons;
import jmul.math.operations.QuaternaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.UnaryOperation;
import jmul.math.operations.implementations.ParameterCheckHelper;
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifier;
import jmul.math.operations.repository.OperationIdentifiers;
import jmul.math.signs.Signs;
import jmul.math.vectors.Vector;


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
    public static final Constant DEFAULT_MAXIMUM_FRACTION_LENGTH;

    /**
     * The default number of iterations for Heron's method of calculating the suqare root.
     */
    public static final Constant DEFAULT_HERON_METHOD_ITERATIONS;

    /**
     * The default number of iterations for calculating the nth root.
     */
    public static final Constant DEFAULT_NTH_ROOT_ITERATIONS;

    /**
     * The default number of iterations for approximating Euler's number.
     */
    public static final Constant DEFAULT_EULERS_NUMBER_ITERATIONS;

    /**
     * The default number of iterations for approximating Pi.
     */
    public static final Constant DEFAULT_LEIBNITZ_PI_APPROXIMATION_ITERATIONS;

    /**
     * The default number of iterations for approximating the sine.
     */
    public static final Constant DEFAULT_SINE_APPROXIMATION_ITERATIONS;

    /**
     * The default number of iterations for approximating the cosine.
     */
    public static final Constant DEFAULT_COSINE_APPROXIMATION_ITERATIONS;

    /**
     * A constant representing the number minus one.
     */
    public static final Constant MINUS_ONE;

    /**
     * A constant representing the number zero.
     */
    public static final Constant ZERO;

    /**
     * A constant representing the number one.
     */
    public static final Constant ONE;

    /**
     * A constant representing the number two.
     */
    public static final Constant TWO;

    /**
     * A constant representing Euler's number.
     */
    public static final Constant E;

    /**
     * A constant representing the number Pi.
     */
    public static final Constant PI;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_MAXIMUM_FRACTION_LENGTH = ConstantHelper.createConstantNumber(10, "10");
        DEFAULT_HERON_METHOD_ITERATIONS = ConstantHelper.createConstantNumber(10, "8");
        DEFAULT_NTH_ROOT_ITERATIONS = ConstantHelper.createConstantNumber(10, "7");
        DEFAULT_EULERS_NUMBER_ITERATIONS = ConstantHelper.createConstantNumber(10, "12");
        DEFAULT_LEIBNITZ_PI_APPROXIMATION_ITERATIONS = ConstantHelper.createConstantNumber(10, "100");
        DEFAULT_SINE_APPROXIMATION_ITERATIONS = ConstantHelper.createConstantNumber(10, "25");
        DEFAULT_COSINE_APPROXIMATION_ITERATIONS = ConstantHelper.createConstantNumber(10, "25");

        MINUS_ONE = ConstantHelper.createConstantNumber(10, Signs.NEGATIVE, 1);
        ZERO = ConstantHelper.createConstantNumber(10, Signs.POSITIVE, 0);
        ONE = ConstantHelper.createConstantNumber(10, Signs.POSITIVE, 1);
        TWO = ConstantHelper.createConstantNumber(10, "2");

        // e = 2.71828182845904523536028747135266249775724709369995957496696762772407663035
        E = ConstantHelper.createConstantNumber(10, "2.7182818284");

        // pi = 3.14159265358979323846264338327950288419716939937510582097494459230781640628
        PI = ConstantHelper.createConstantNumber(10, "3.1415926535");
    }

    /**
     * The default constructor.
     */
    private Math() {

        throw new UnsupportedOperationException();
    }

    /**
     * Returns the constant value for the specified base.
     *
     * @param base
     *        a number base
     *
     * @return a constant value
     */
    public static Number getDefaultMaximumFractionLength(int base) {

        return DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base);
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

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_TO_ABSOLUTE_VALUE_FUNCTION);
        Result<Number> result = function.calculate(n);

        return result.result();
    }

    /**
     * Returns the absolute value of this fraction.
     *
     * @param f
     *        a fraction
     *
     * @return the absolute value
     */
    public static Fraction absoluteValue(Fraction f) {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_TO_ABSOLUTE_VALUE_FUNCTION);
        Result<Fraction> result = function.calculate(f);

        return result.result();
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
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.ADD_NUMBERS_TRIM_RESULT_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.ADD_FRACTIONS_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.ADD_NUMBER_AND_FRACTION_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.ADD_FRACTION_AND_NUMBER_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NEGATE_NUMBER_FUNCTION);
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
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.NEGATE_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(f);

        return result.result();
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
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_NUMBERS_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_FRACTIONS_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_FRACTION_AND_NUMBER_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_NUMBER_AND_FRACTION_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.HALVING_NUMBER_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DOUBLING_NUMBER_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.REMOVE_FRACTION_PART_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.REMOVE_INTEGER_PART_FUNCTION);
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

        return shiftLeft(n, ONE.value(n.base()));
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
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SHIFT_LEFT_FUNCTION);
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

        return shiftRight(n, ONE.value(n.base()));
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
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SHIFT_RIGHT_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_COMPLEMENT_FUNCTION);
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
            (Comparator<Number>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_COMPARATOR_FUNCTION);
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
            (Comparator<Fraction>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_COMPARATOR_FUNCTION);
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
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.MAX_NUMBER_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MAX_FRACTION_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MAX_FRACTION_NUMBER_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MAX_NUMBER_FRACTION_FUNCTION);
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
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.MIN_NUMBER_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MIN_FRACTION_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MIN_FRACTION_NUMBER_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MIN_NUMBER_FRACTION_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_INCREMENT_FUNCTION);
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
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_INCREMENT_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_DECREMENT_FUNCTION);
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
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_DECREMENT_FUNCTION);
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
            (UnaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.EVEN_NUMBER_FUNCTION);
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
            (UnaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.ODD_NUMBER_FUNCTION);
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
     * @return the product of the specified numbers
     */
    public static Number multiply(Number n1, Number n2) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return multiply(processingDetails, n1, n2);
    }

    /**
     * Multiplies this number with the specified number.
     *
     * @param processingDetails
     *        additonal processing details
     * @param n1
     *        a number
     * @param n2
     *        a number
     *
     * @return the product of this number and the specified number
     */
    public static Number multiply(ProcessingDetails processingDetails, Number n1, Number n2) {

        ParameterCheckHelper.checkParameter(processingDetails);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.LONG_MULTIPLICATION_FUNCTION,
            OperationIdentifiers.MULTIPLY_NUMBERS_BY_ADDITION_FUNCTION,
            OperationIdentifiers.RUSSIAN_PEASANT_MULTIPLICATION_FUNCTION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
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
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.MODULO_FUNCTION);
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
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIVISO_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MULTIPLY_FRACTIONS_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MULTIPLY_NUMBER_AND_FRACTION_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.MULTIPLY_FRACTION_AND_NUMBER_FUNCTION);
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
    public static Fraction divideReturnFraction(Number operand1, Number operand2) {

        BinaryOperation<Number, Result<Fraction>> function =
            (BinaryOperation<Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_NUMBERS_RETURN_FRACTION_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_FRACTIONS_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_FRACTION_BY_NUMBER_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.DIVIDE_NUMBER_BY_FRACTION_FUNCTION);
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
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_GREATER_THAN_COMPARISON_FUNCTION);
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
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
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
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_LESSER_THAN_COMPARISON_FUNCTION);
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
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_GREATER_THAN_COMPARISON_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_LESSER_THAN_COMPARISON_FUNCTION);
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
            (BinaryOperation<Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_GREATER_THAN_NUMBER_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_LESSER_THAN_NUMBER_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_GREATER_THAN_FRACTION_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_GREATER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_LESSER_THAN_FRACTION_COMPARISON_FUNCTION);
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
            (MixedBinaryOperation<Number, Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_LESSER_THAN_OR_EQUAL_FRACTION_COMPARISON_FUNCTION);
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
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.RECIPROCAL_OF_FRACTION_FUNCTION);
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
            (UnaryOperation<Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.RECIPROCAL_OF_NUMBER_FUNCTION);
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
            (MixedBinaryOperation<Number, Integer, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.REBASE_NUMBER_FUNCTION);
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
            (MixedBinaryOperation<Fraction, Integer, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.REBASE_FRACTION_FUNCTION);
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
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.FACTORIAL_FUNCTION);
        Result<Number> result = function.calculate(number);

        return result.result();
    }

    /**
     * Halves the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction halving(Fraction fraction) {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.HALVING_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(fraction);

        return result.result();
    }

    /**
     * Doubles the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction doubling(Fraction fraction) {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.DOUBLING_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(fraction);

        return result.result();
    }

    /**
     * Round this number down to the nearest integer that doesn't exceed this number.
     *
     * @param number
     *        a number
     *
     * @return the nearest integer that doesn't exceed this number
     */
    public static Number roundDown(Number number) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.ROUND_DOWN_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(number);

        return result.result();
    }

    /**
     * Round this number up to the nearest integer that is not less than this number.
     *
     * @param number
     *        a number
     *
     * @return the nearest integer that is not less than this number
     */
    public static Number roundUp(Number number) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.ROUND_UP_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(number);

        return result.result();
    }

    /**
     * Shorten the precision of this number according to the specified decimal places.
     *
     * @param number
     *        a number
     *
     * @return a rounded number
     */
    public static Number round(Number number) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return round(processingDetails, number);
    }

    /**
     * Shorten the precision of this number according to the specified decimal places.
     *
     * @param number
     *        a number
     * @param processingDetails
     *        additonal processing details
     *
     * @return a shortened number according to the specified precision
     */
    public static Number round(ProcessingDetails processingDetails, Number number) {

        ParameterCheckHelper.checkParameter(processingDetails);
        ParameterCheckHelper.checkParameter(number);

        int base = number.base();

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.ROUND_NUMBER_TO_ODD_FUNCTION, OperationIdentifiers.ROUND_NUMBER_TO_EVEN_FUNCTION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(number, decimalPlaces);

        return result.result();
    }

    /**
     * Checks if the specified number is a common divisor of the specified fraction (i.e. numerator and denominator).
     *
     * @param fraction
     *        a fraction
     * @param number
     *        a number
     *
     * @return <code>true</code> if the specified number is a common divisor of the specified fraction (i.e.
     *         numerator and denominator), else <code>false</code>
     */
    public static boolean isCommonDivisor(Fraction fraction, Number number) {

        //TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if this number is a multiple of the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this number is a multiple of the specified number, else <code>false</code>
     */
    public static boolean isMultipleOf(Number number) {

        //TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Divides the first specified number by the second specified number.
     *
     * @param n1
     *        a number
     * @param n2
     *        a number
     *
     * @return the quotient of both numbers
     */
    public static Number divide(Number n1, Number n2) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return divide(processingDetails, n1, n2);
    }

    /**
     * Divides the first specified number by the second specified number.
     *
     * @param processingDetails
     *        additonal processing details
     * @param n1
     *        a number
     * @param n2
     *        a number
     *
     * @return the quotient of both numbers
     */
    public static Number divide(ProcessingDetails processingDetails, Number n1, Number n2) {

        ParameterCheckHelper.checkParameter(processingDetails);
        ParameterCheckHelper.checkParameter(n1);

        int base = n1.base();

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.RUSSIAN_DIVISION_FUNCTION, OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(n1, n2, decimalPlaces);

        return result.result();
    }

    /**
     * Evaluates this mathematical expression and returns a calculation result.
     *
     * @param fraction
     *        a fraction
     *
     * @return a number
     */
    public static Number evaluate(Fraction fraction) {

        ParameterCheckHelper.checkParameter(fraction);

        return evaluate(fraction, Math.getDefaultMaximumFractionLength(fraction.base()));
    }

    /**
     * Evaluates this fraction and returns a number which is equivalent to the fraction.
     *
     * @param fraction
     *        a fraction
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     *
     * @return a number
     */
    public static Number evaluate(Fraction fraction, Number decimalPlaces) {

        MixedBinaryOperation<Fraction, Number, Result<Number>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.EVALUATE_FRACTION_FUNCTION);
        Result<Number> result = function.calculate(fraction, decimalPlaces);

        return result.result();
    }

    /**
     * Exponentiates this number by the specified exponent.
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent (must be an integer)
     *
     * @return the result
     */
    public static Number exponentiate(Number number, Number exponent) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return exponentiate(processingDetails, number, exponent);
    }

    /**
     * Exponentiates this number by the specified exponent.
     *
     * @param processingDetails
     *        additonal processing details
     * @param number
     *        a number
     * @param exponent
     *        an exponent (must be an integer)
     *
     * @return the result
     */
    public static Number exponentiate(ProcessingDetails processingDetails, Number number, Number exponent) {

        ParameterCheckHelper.checkParameter(processingDetails);
        ParameterCheckHelper.checkParameter(number);

        int base = number.base();

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION,
            OperationIdentifiers.CONCURRENT_EXPONENTIATE_NUMBER_WITH_NUMBER_FUNCTION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(number, exponent, decimalPlaces);

        return result.result();
    }

    /**
     * Exponentiates this number by the specified exponent.
     *
     * @param number
     *        a number
     * @param exponent
     *        an exponent
     *
     * @return the result
     */
    public static Number exponentiate(Number number, Fraction exponent) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return exponentiate(processingDetails, number, exponent);
    }

    /**
     * Exponentiates this number by the specified exponent.
     *
     * @param processingDetails
     *        additonal processing details
     * @param number
     *        a number
     * @param exponent
     *        an exponent
     *
     * @return the result
     */
    public static Number exponentiate(ProcessingDetails processingDetails, Number number, Fraction exponent) {

        ParameterCheckHelper.checkParameter(processingDetails);
        ParameterCheckHelper.checkParameter(number);

        int base = number.base();

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.EXPONENTIATE_NUMBER_WITH_FRACTION_FUNCTION };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));
        Number iterations =
            processingDetails.checkAndReturnIterationDepth(Math.DEFAULT_NTH_ROOT_ITERATIONS.value(base));

        MixedQuaternaryOperation<Number, Fraction, Result<Number>> function =
            (MixedQuaternaryOperation<Number, Fraction, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(number, exponent, iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Performs an exponentiation with the specified fraction as the base and the specified number as the exponent.
     *
     * @param fraction
     *        a fraction
     * @param exponent
     *        a number
     *
     * @return a fraction
     */
    public static Fraction exponentiate(Fraction fraction, Number exponent) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.EXPONENTIATE_FRACTION_WITH_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(fraction, exponent);

        return result.result();
    }

    /**
     * Checks if this number is a multiple of the specified number.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return <code>true</code> if this number is a multiple of the specified number, else <code>false</code>
     */
    public static boolean isMultipleOf(Number number1, Number number2) {

        BinaryOperation<Number, Result<Boolean>> function =
            (BinaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.IS_MULTIPLE_FUNCTION);
        Result<Boolean> result = function.calculate(number1, number2);

        return result.result();
    }

    /**
     * Calculates the square for the specified number.
     *
     * @param number
     *        a number
     *
     * @return a number
     */
    public static Number square(Number number) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SQUARE_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(number);

        return result.result();
    }

    /**
     * Calculates the square root for the specified number.
     *
     * @param number
     *        a number
     *
     * @return the square root for the specified number
     */
    public static Number squareRoot(Number number) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return squareRoot(processingDetails, number);
    }

    /**
     * Calculates the square root for the specified number.
     *
     * @param processingDetails
     *        additonal processing details
     * @param number
     *        a number
     *
     * @return a square root for the specified number
     */
    public static Number squareRoot(ProcessingDetails processingDetails, Number number) {

        ParameterCheckHelper.checkParameter(processingDetails);
        ParameterCheckHelper.checkParameter(number);

        int base = number.base();

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.SQUARE_ROOT_FUNCTION };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));
        Number iterations =
            processingDetails.checkAndReturnIterationDepth(Math.DEFAULT_HERON_METHOD_ITERATIONS.value(base));

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(number, iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Calculates the nth root for the specified number.
     *
     * @param number
     *        a number
     * @param n
     *        a root
     *
     * @return a number
     */
    public static Number root(Number number, Number n) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return root(processingDetails, number, n);
    }

    /**
     * Calculates the nth root for this number.
     *
     * @param processingDetails
     *        additonal processing details
     * @param number
     *        a number
     * @param n
     *        the root
     *
     * @return the nth root for this number
     */
    public static Number root(ProcessingDetails processingDetails, Number number, Number n) {

        ParameterCheckHelper.checkParameter(processingDetails);
        ParameterCheckHelper.checkParameter(number);
        ParameterCheckHelper.checkParameter(n);

        int base = number.base();

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.NTH_ROOT_FUNCTION };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));
        Number iterations =
            processingDetails.checkAndReturnIterationDepth(Math.DEFAULT_NTH_ROOT_ITERATIONS.value(base));

        QuaternaryOperation<Number, Result<Number>> function =
            (QuaternaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(number, n, iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Calculates the square for the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction square(Fraction fraction) {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.SQUARE_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(fraction);

        return result.result();
    }

    /**
     * Determines all divisors of this number.
     *
     * @param number
     *        a number
     *
     * @return a set containing all divisors or an empty set if there are no divisors
     */
    public static Set<Number> divisors(Number number) {

        UnaryOperation<Number, Result<Set<Number>>> function =
            (UnaryOperation<Number, Result<Set<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_DIVISORS_FUNCTION);
        Result<Set<Number>> result = function.calculate(number);

        return result.result();
    }

    /**
     * Determines all common divisors of this number and the specified number.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return a set containing all common divisors or an empty set if there are no common divisors
     */
    public static Set<Number> commonDivisors(Number number1, Number number2) {

        BinaryOperation<Number, Result<Set<Number>>> function =
            (BinaryOperation<Number, Result<Set<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_COMMON_DIVISORS_OF_NUMBERS);
        Result<Set<Number>> result = function.calculate(number1, number2);

        return result.result();
    }

    /**
     * Determines the prime factors for the specified number. The result sequence contains all prime factors.
     *
     * @param number
     *        a number
     *
     * @return a sequnce of prime factors or an empty sequence if there are no prime factors
     */
    public static Sequence<Number> primeFactors(Number number) {

        UnaryOperation<Number, Result<Sequence<Number>>> function =
            (UnaryOperation<Number, Result<Sequence<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_PRIME_FACTORS_OF_NUMBER);
        Result<Sequence<Number>> result = function.calculate(number);

        return result.result();
    }

    /**
     * Checks if this number is a prime number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this number is a prime number, else <code>false</code>
     */
    public static boolean isPrime(Number number) {

        UnaryOperation<Number, Result<Boolean>> function =
            (UnaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.IS_PRIME_FUNCTION);
        Result<Boolean> result = function.calculate(number);

        return result.result();
    }

    /**
     * Reduces the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction reduce(Fraction fraction) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return reduce(processingDetails, fraction);
    }

    /**
     * Reduces the specified fraction.
     *
     * @param processingDetails
     *        some processing details
     * @param fraction
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction reduce(ProcessingDetails processingDetails, Fraction fraction) {

        ParameterCheckHelper.checkParameter(processingDetails);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.OPTIMIZED_REDUCE_FRACTION, OperationIdentifiers.REDUCE_FRACTION_BY_COMMON_PRIME_FACTORS
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) OperationSingletons.getFunction(algorithm);
        Result<Fraction> result = function.calculate(fraction);

        return result.result();
    }

    /**
     * Determines the common divisors for this fraction (i.e. numerator and denominator).
     *
     * @param fraction
     *        a fraction
     *
     * @return a set of common divisors or an empty set if there are no common divisors
     */
    public static Set<Number> commonDivisors(Fraction fraction) {

        UnaryOperation<Fraction, Result<Set<Number>>> function =
            (UnaryOperation<Fraction, Result<Set<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_COMMON_DIVISORS_IN_FRACTION);
        Result<Set<Number>> result = function.calculate(fraction);

        return result.result();
    }

    /**
     * Determines the common prime factors for the specified fraction (i.e. numerator and denominator).
     *
     * @param fraction
     *        a fraction
     *
     * @return a set of prime factors or an empty set if there are no common prime factors
     */
    public static Sequence<Number> commonPrimeFactors(Fraction fraction) {

        UnaryOperation<Fraction, Result<Sequence<Number>>> function =
            (UnaryOperation<Fraction, Result<Sequence<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_COMMON_PRIME_FACTORS_IN_FRACTION);
        Result<Sequence<Number>> result = function.calculate(fraction);

        return result.result();
    }

    /**
     * Determines the common prime factors of this number and the specified number.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     *
     * @return a sequence of common prime factors or an empty sequence if there are no common prime factors
     */
    public static Sequence<Number> commonPrimeFactors(Number number1, Number number2) {

        BinaryOperation<Number, Result<Sequence<Number>>> function =
            (BinaryOperation<Number, Result<Sequence<Number>>>) OperationSingletons.getFunction(OperationIdentifiers.DETERMINE_COMMON_PRIME_FACTORS_IN_NUMBERS);
        Result<Sequence<Number>> result = function.calculate(number1, number2);

        return result.result();
    }

    /**
     * Adds the specified vectors.
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     *
     * @return the result of the addition
     */
    public static Vector add(Vector vector1, Vector vector2) {

        BinaryOperation<Vector, Result<Vector>> function =
            (BinaryOperation<Vector, Result<Vector>>) OperationSingletons.getFunction(OperationIdentifiers.ADD_VECTORS_FUNCTION);
        Result<Vector> result = function.calculate(vector1, vector2);

        return result.result();
    }

    /**
     * Subtracts the specified vectors.
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     *
     * @return the result of the subtraction
     */
    public static Vector subtract(Vector vector1, Vector vector2) {

        BinaryOperation<Vector, Result<Vector>> function =
            (BinaryOperation<Vector, Result<Vector>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_VECTORS_FUNCTION);
        Result<Vector> result = function.calculate(vector1, vector2);

        return result.result();
    }

    /**
     * Multiplies the specified vector with the specified number (i.e. perform a scalar multiplication).
     *
     * @param vector
     *        a vector
     * @param number
     *        a number
     *
     * @return the result of the multiplication
     */
    public static Vector multiply(Vector vector, Number number) {

        MixedBinaryOperation<Vector, Number, Result<Vector>> function =
            (MixedBinaryOperation<Vector, Number, Result<Vector>>) OperationSingletons.getFunction(OperationIdentifiers.MULTIPLY_VECTOR_WITH_NUMBER_FUNCTION);
        Result<Vector> result = function.calculate(vector, number);

        return result.result();
    }

    /**
     * Multiplies the specified vectors (i.e. calculates the inner product of two vectors).
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     *
     * @return the result of the multiplication
     */
    public static Number scalarProduct(Vector vector1, Vector vector2) {

        BinaryOperation<Vector, Result<Number>> function =
            (BinaryOperation<Vector, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.SCALAR_PRODUCT_FUNCTION);
        Result<Number> result = function.calculate(vector1, vector2);

        return result.result();
    }

    /**
     * Calculates the cross product of the specified vectors.
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     *
     * @return the cross product
     */
    public static Vector crossProduct(Vector vector1, Vector vector2) {

        BinaryOperation<Vector, Result<Vector>> function =
            (BinaryOperation<Vector, Result<Vector>>) OperationSingletons.getFunction(OperationIdentifiers.CROSS_PRODUCT_FUNCTION);
        Result<Vector> result = function.calculate(vector1, vector2);

        return result.result();
    }

    /**
     * Calculates the triple product of this vector and the two specified vectors (i.e. <code>(vector1 x vector2) * vector3</code>).
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     * @param vector3
     *        a vector
     *
     * @return the triple product
     */
    public static Number tripleProduct(Vector vector1, Vector vector2, Vector vector3) {

        TernaryOperation<Vector, Result<Number>> function =
            (TernaryOperation<Vector, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.TRIPLE_PRODUCT_FUNCTION);
        Result<Number> result = function.calculate(vector1, vector2, vector3);

        return result.result();
    }

    /**
     * Performs a matrix addition with the specified matrices.
     *
     * @param matrix1
     *        a matrix
     * @param matrix2
     *        a matrix
     *
     * @return the result
     */
    public static Matrix add(Matrix matrix1, Matrix matrix2) {

        BinaryOperation<Matrix, Result<Matrix>> function =
            (BinaryOperation<Matrix, Result<Matrix>>) OperationSingletons.getFunction(OperationIdentifiers.ADD_MATRICES_FUNCTION);
        Result<Matrix> result = function.calculate(matrix1, matrix2);

        return result.result();
    }

    /**
     * Translates this number into a fraction.
     *
     * @param number
     *        a number
     *
     * @return a fraction
     */
    public static Fraction toFraction(Number number) {

        UnaryOperation<Number, Result<Fraction>> function =
            (UnaryOperation<Number, Result<Fraction>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_TO_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(number);

        return result.result();
    }

    /**
     * Performs a matrix subtraction with the specified matrices.
     *
     * @param matrix1
     *        a matrix
     * @param matrix2
     *        a matrix
     *
     * @return the result
     */
    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {

        BinaryOperation<Matrix, Result<Matrix>> function =
            (BinaryOperation<Matrix, Result<Matrix>>) OperationSingletons.getFunction(OperationIdentifiers.SUBTRACT_MATRICES_FUNCTION);
        Result<Matrix> result = function.calculate(matrix1, matrix2);

        return result.result();
    }

    /**
     * Checks if the specified number is within the specified bounds (including bounds).
     *
     * @param min
     *        a number (i.e. lower bound of an interval)
     * @param number
     *        a number
     * @param max
     *        a number (i.e. upper bound of an interval)
     *
     * @return <code>true</code> if the specified number is within the specified bounds, else <code>false</code>
     */
    public static boolean isWithinInterval(Number min, Number number, Number max) {

        TernaryOperation<Number, Result<Boolean>> function =
            (TernaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_IS_WITHIN_INTERVAL);
        Result<Boolean> result = function.calculate(min, number, max);

        return result.result();
    }

    /**
     * Checks if the specified fraction is within the specified bounds (including bounds).
     *
     * @param min
     *        a fraction (i.e. lower bound of an interval)
     * @param fraction
     *        a fraction
     * @param max
     *        a fraction (i.e. upper bound of an interval)
     *
     * @return <code>true</code> if the specified fraction is within the specified bounds, else <code>false</code>
     */
    public static boolean isWithinInterval(Fraction min, Fraction fraction, Fraction max) {

        TernaryOperation<Fraction, Result<Boolean>> function =
            (TernaryOperation<Fraction, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.FRACTION_IS_WITHIN_INTERVAL);
        Result<Boolean> result = function.calculate(min, fraction, max);

        return result.result();
    }

    /**
     * Calculates the dyadic product of the specified vectors The underlying assumption ist that the specified
     * first vector is interpreted as a column vector and the specified vector is interpreted as a row vector.
     *
     * @param vector1
     *        a vector (i.e. column vector)
     * @param vector2
     *        a vector (i.e. a row vector)
     *
     * @return the dyadic product
     */
    public static Matrix dyadicProduct(Vector vector1, Vector vector2) {

        BinaryOperation<Vector, Result<Matrix>> function =
            (BinaryOperation<Vector, Result<Matrix>>) OperationSingletons.getFunction(OperationIdentifiers.DYADIC_PRODUCT_FUNCTION);
        Result<Matrix> result = function.calculate(vector1, vector2);

        return result.result();
    }

    /**
     * Performs a vetorization of the specified matrix (i.e. transforms the matrix to a vector).
     *
     * @param matrix
     *        a matrix
     *
     * @return a vector
     */
    public static Vector toVector(Matrix matrix) {

        UnaryOperation<Matrix, Result<Vector>> function =
            (UnaryOperation<Matrix, Result<Vector>>) OperationSingletons.getFunction(OperationIdentifiers.VECTORIZATION_FUNCTION);
        Result<Vector> result = function.calculate(matrix);

        return result.result();
    }

    /**
     * Transposes the specified matrix.
     *
     * @param matrix
     *        a matrix
     *
     * @return a transposed matrix
     */
    public static Matrix transpose(Matrix matrix) {

        UnaryOperation<Matrix, Result<Matrix>> function =
            (UnaryOperation<Matrix, Result<Matrix>>) OperationSingletons.getFunction(OperationIdentifiers.TRANSPOSE_MATRIX_FUNCTION);
        Result<Matrix> result = function.calculate(matrix);

        return result.result();
    }

    /**
     * Performs a matrix multiplication with the specified matrices.
     *
     * @param matrix1
     *        a matrix
     * @param matrix2
     *        a matrix
     *
     * @return the result
     */
    public static Matrix multiply(Matrix matrix1, Matrix matrix2) {

        BinaryOperation<Matrix, Result<Matrix>> function =
            (BinaryOperation<Matrix, Result<Matrix>>) OperationSingletons.getFunction(OperationIdentifiers.MATRIX_MULTIPLCIATION_FUNCTION);
        Result<Matrix> result = function.calculate(matrix1, matrix2);

        return result.result();
    }

    /**
     * Generates a random number between zero and one (0 =&lt; n &lt; 1).
     *
     * @param base
     *        a number base
     *
     * @return a (pseudo) random number
     */
    public static Number random(int base) {

        return random(base, DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));
    }

    /**
     * Generates a random number between zero and one with the specified number of digits to the right of the decimal
     * separator (0 =&lt; n &lt; 1).
     *
     * @param base
     *        a number base
     * @param digits
     *        a number of digits to randomly generate
     *
     * @return a (pseudo) random number
     */
    public static Number random(int base, Number digits) {

        MixedBinaryOperation<Integer, Number, Result<Number>> function =
            (MixedBinaryOperation<Integer, Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.RANDOM_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(base, digits);

        return result.result();
    }

    /**
     * Returns a random value between the specified boundaries (including  the boundaries). Generates a random number
     * between zero and one. Determines and returns a corresponding number within the specified interval
     * (min =&lt; n =&lt; max).
     *
     * @param minimumValue
     *        a minimum value
     * @param maximumValue
     *        a maximum value
     *
     * @return a (pseudo) random number
     */
    public static Number random(Number minimumValue, Number maximumValue) {

        if (minimumValue == null) {

            throw new IllegalArgumentException("No minimum value was specified!");
        }

        int base = minimumValue.base();

        return random(minimumValue, maximumValue, DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));
    }

    /**
     * Returns a random value between the specified boundaries (including  the boundaries). Generates a random number
     * between zero and one with the specified number of digits to the right of the decimal separator. Determines and
     * returns a corresponding number within the specified interval (min =&lt; n =&lt; max).
     *
     * @param minimumValue
     *        a minimum value
     * @param maximumValue
     *        a maximum value
     * @param digits
     *        the number of digits of the generated random number to the right of the decimal separator
     *
     * @return a (pseudo) random number
     */
    public static Number random(Number minimumValue, Number maximumValue, Number digits) {

        TernaryOperation<Number, Result<Number>> function =
            (TernaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.RANDOM_NUMBER_WITHIN_INTERVAL_FUNCTION);
        Result<Number> result = function.calculate(minimumValue, maximumValue, digits);

        return result.result();
    }

    /**
     * Calculates the digit sum for this number.
     *
     * @param n
     *        a number (i.e. integer)
     *
     * @return the digit sum
     */
    public static Number digitSum(Number n) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.DIGIT_SUM_FUNCTION);
        Result<Number> result = function.calculate(n);

        return result.result();
    }

    /**
     * Checks if this number consists of a single digit.
     *
     * @param n
     *        a number
     *
     * @return <code>true</code> if this number consists of a single digit, else <code>false</code>
     */
    public static boolean isSingleDigit(Number n) {

        UnaryOperation<Number, Result<Boolean>> function =
            (UnaryOperation<Number, Result<Boolean>>) OperationSingletons.getFunction(OperationIdentifiers.IS_SINGLE_DIGIT_FUNCTION);
        Result<Boolean> result = function.calculate(n);

        return result.result();
    }

    /**
     * Returns an approximation for Euler's number.
     *
     * @param base
     *        a number base
     *
     * @return an approximation for Euler's number
     */
    public static Number e(int base) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return e(processingDetails, base);
    }

    /**
     * Returns an approximation for Euler's number.
     *
     * @param processingDetails
     *        additonal processing details
     * @param base
     *        a number base
     *
     * @return an approximation for Euler's number
     */
    public static Number e(ProcessingDetails processingDetails, int base) {

        ParameterCheckHelper.checkParameter(processingDetails);
        ParameterCheckHelper.checkNumberBase(base);

        Number iterations =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_EULERS_NUMBER_ITERATIONS.value(base));
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.EULERS_NUMBER_FUNCTION);
        Result<Number> result = function.calculate(iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Returns an approximation for Pi.
     *
     * @param base
     *        a number base
     *
     * @return an approximation for Pi
     */
    public static Number pi(int base) {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM,
                                                   ProcessingDetails.DEFAULT_PRECISION,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);

        return pi(processingDetails, base);
    }

    /**
     * Returns an approximation for Pi.
     *
     * @param processingDetails
     *        additonal processing details
     * @param base
     *        a number base
     *
     * @return an approximation for Pi
     */
    public static Number pi(ProcessingDetails processingDetails, int base) {

        ParameterCheckHelper.checkParameter(processingDetails);
        ParameterCheckHelper.checkNumberBase(base);

        final OperationIdentifier[] ALLOWED_ALGORITHMS = new OperationIdentifier[] {
            OperationIdentifiers.ARCHIMEDES_PI_APPROXIMATION_FUNCTION,
            OperationIdentifiers.LEIBNIZ_PI_APPROXIMATION_FUNCTION
        };

        OperationIdentifier algorithm = processingDetails.checkAndReturnAlgorithm(ALLOWED_ALGORITHMS);
        Number iterations =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_LEIBNITZ_PI_APPROXIMATION_ITERATIONS.value(base));
        Number decimalPlaces =
            processingDetails.checkAndReturnPrecision(Math.DEFAULT_MAXIMUM_FRACTION_LENGTH.value(base));

        BinaryOperation<Number, Result<Number>> function =
            (BinaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(algorithm);
        Result<Number> result = function.calculate(iterations, decimalPlaces);

        return result.result();
    }

    /**
     * Returns the next prime number (e.g. 0 -&gt; 2, 1 -&gt; 3, etc.).
     *
     * @param ordinal
     *        the nth prime number
     *
     * @return a prime number
     */
    public static Number nextPrimeNumber(Number ordinal) {

        UnaryOperation<Number, Result<Number>> function =
            (UnaryOperation<Number, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.NEXT_PRIME_NUMBER);
        Result<Number> result = function.calculate(ordinal);

        return result.result();
    }

}

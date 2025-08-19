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

package jmul.math.fractions;


import java.util.Comparator;
import java.util.SortedSet;

import jmul.math.Math;
import jmul.math.digits.PositionalNumeralSystems;
import static jmul.math.fractions.FractionHelper.CLONE;
import static jmul.math.fractions.FractionHelper.DONT_CLONE;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.hash.HashHelper;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.EqualityFunction;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.MixedComparator;
import jmul.math.operations.MixedEqualityFunction;
import jmul.math.operations.Result;
import jmul.math.operations.TernaryOperation;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;


/**
 * This class implements a mixed quotient.
 *
 * @author Kristian Kutin
 */
class MixedFraction implements Fraction {

    /**
     * The integer part of this fraction.
     */
    private final Number integerPart;

    /**
     * The numerator of this fraction.
     */
    private final Number numerator;

    /**
     * The denominator of this fraction.
     */
    private final Number denominator;

    /**
     * Creates a new fraction according to the specified parameters.
     *
     * @param integerPart
     *        the integer part of this fraction
     * @param numerator
     *        the numerator of this fraction
     * @param denominator
     *        the denominator of this fraction
     */
    public MixedFraction(Number integerPart, Number numerator, Number denominator) {

        super();

        checkParameters(integerPart, numerator, denominator);

        this.integerPart = integerPart;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Checks the specifiecd parameters.
     *
     * @param integerPart
     *        a number representing the integer part of a fraction
     * @param numerator
     *        a number representing the numerator of a fraction
     * @param denominator
     *        a number representing the denominator of a fraction
     */
    private static void checkParameters(Number integerPart, Number numerator, Number denominator) {

        // Check the references now. This will avoid null checking later.

        if (integerPart == null) {

            String message = "No integer part (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (numerator == null) {

            String message = "No numerator (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        if (denominator == null) {

            String message = "No denominator (null) was specified!";
            throw new IllegalArgumentException(message);
        }

        // Check the bases. All specified numbers have to be of the same base.

        if (integerPart.base() != numerator.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }

        if (numerator.base() != denominator.base()) {

            String message = "The specified numbers are of different bases!";
            throw new IllegalArgumentException(message);
        }

        // Check if the specified numbers are integers as only integer values are allowed.

        if (integerPart.isFraction()) {

            String message = ("The specified integer part is no integer!");
            throw new IllegalArgumentException(message);
        }

        if (numerator.isFraction()) {

            String message = "The specified numerator is no integer!";
            throw new IllegalArgumentException(message);
        }

        if (denominator.isFraction()) {

            String message = "The specified denominator is no integer!";
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Returns the base of the underlying numeral system for this number.
     *
     * @return a base
     */
    @Override
    public int base() {

        return integerPart.base();
    }

    /**
     * Creates a string representation of this mixed number.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        if (hasIntegerPart()) {

            buffer.append(integerPart);

            if (hasNumerator()) {

                buffer.append(" ");
            }

        } else {

            if (!hasNumerator()) {

                buffer.append(integerPart);
            }
        }

        if (hasNumerator()) {

            String fractioNString = String.format("%s/%s", numerator, denominator);
            buffer.append(fractioNString);
        }

        return String.valueOf(buffer);
    }

    /**
     * Evaluates this mathematical expression and returns a calculation result.
     *
     * @return a number
     */
    @Override
    public Number evaluate() {

        return evaluate(Math.getDefaultMaximumFractionLength(base()));
    }

    /**
     * Evaluates this fraction and returns a number which is equivalent to the fraction.
     *
     * @param decimalPlaces
     *        the number of decimal places retained after cutting fraction part
     *
     * @return a number
     */
    @Override
    public Number evaluate(Number decimalPlaces) {

        MixedBinaryOperation<Fraction, Number, Result<Number>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Number>>) FunctionSingletons.getFunction(FunctionIdentifiers.EVALUATE_FRACTION_FUNCTION);
        Result<Number> result = function.calculate(this, decimalPlaces);

        return result.result();
    }

    /**
     * Returns the sign of this number.
     *
     * @return a sign
     */
    @Override
    public Sign sign() {

        if (integerPart.isZero()) {

            if (numerator.isZero()) {

                return integerPart.sign();

            } else {

                return numerator.sign();
            }

        } else {

            return integerPart.sign();
        }
    }

    /**
     * Checks if this fraction has an integer part.
     *
     * @return <code>true</code> if this fraction has an integer part, else <code>false</code>
     */
    @Override
    public boolean hasIntegerPart() {

        // If the integer part is zero then there is no integer part.

        return !integerPart.isZero();
    }

    /**
     * Returns the integer part of this fraction.
     *
     * @return a number
     */
    @Override
    public Number integerPart() {

        return integerPart;
    }

    /**
     * Checks if this fraction has a numerator.
     *
     * @return <code>true</code> if this fraction has a numerator, else <code>false</code>
     */
    @Override
    public boolean hasNumerator() {

        // If the numerator is zero then the fraction is zero thus there is no numerator.

        return !numerator.isZero();
    }

    /**
     * Returns the numerator of this fraction.
     *
     * @return a number
     */
    @Override
    public Number numerator() {

        return numerator;
    }

    /**
     * Checks if this fraction has a denominator.
     *
     * @return <code>true</code> if this fraction has a denominator, else <code>false</code>
     */
    @Override
    public boolean hasDenominator() {

        // If the numerator is zero then the fraction is zero and thus there is no denominator.

        return !numerator.isZero();
    }

    /**
     * Returns the denominator of this fraction.
     *
     * @return a number
     */
    @Override
    public Number denominator() {

        return denominator;
    }

    /**
     * Normalizes this fraction (i.e. integrates an integer part into the numerator).
     *
     * @return a fraction
     */
    @Override
    public Fraction normalizedFraction() {

        if (hasIntegerPart() && hasNumerator()) {

            Number newNumerator = integerPart().absoluteValue();
            newNumerator = newNumerator.multiply(denominator());
            newNumerator = newNumerator.add(numerator());
            Number newDenominator = denominator();
            if (Signs.isNegative(sign())) {

                newNumerator = newNumerator.negate();
            }
            return createFraction(DONT_CLONE, newNumerator, newDenominator);

        } else if (hasIntegerPart() && !hasNumerator()) {

            Number newNumerator = integerPart().multiply(denominator());
            Number newDenominator = createNumber(base(), Signs.POSITIVE, 1);
            return createFraction(DONT_CLONE, newNumerator, newDenominator);

        } else if (!hasIntegerPart() && hasNumerator()) {

            return createFraction(CLONE, numerator(), denominator());

        } else {

            String symbol = PositionalNumeralSystems.toString(base(), 0);
            return createFraction(base(), symbol);
        }
    }

    /**
     * Normalizes this fraction (i.e. extracts the integer part from the numerator).
     *
     * @return a fraction
     */
    @Override
    public Fraction normalizedMixedFraction() {

        String zeroString = PositionalNumeralSystems.toString(base(), 0);
        Number newIntegerPart = NumberHelper.createNumber(base(), zeroString);

        Number newNumerator = numerator().absoluteValue();
        Number newDenominator = createNumber(denominator());

        while (newNumerator.isGreaterOrEqual(denominator())) {

            newIntegerPart = newIntegerPart.inc();
            newNumerator = newNumerator.subtract(denominator());
        }

        if (newNumerator.isZero()) {

            String oneString = PositionalNumeralSystems.toString(base(), 1);
            newDenominator = NumberHelper.createNumber(base(), oneString);
        }

        if (this.isNegative()) {

            newIntegerPart = integerPart().subtract(newIntegerPart);

        } else {

            newIntegerPart = integerPart().add(newIntegerPart);
        }

        Fraction newFraction = FractionHelper.createFraction(DONT_CLONE, newIntegerPart, newNumerator, newDenominator);

        return newFraction;
    }

    /**
     * Checks if this fraction is positive.
     *
     * @return <code>true</code> if this fraction is positive, else <code>false</code>
     */
    @Override
    public boolean isPositive() {

        return Signs.isPositive(sign());
    }

    /**
     * Checks if this fraction is negative.
     *
     * @return <code>true</code> if this fraction is negative, else <code>false</code>
     */
    @Override
    public boolean isNegative() {

        return Signs.isNegative(sign());
    }

    /**
     * Checks if this fraction is greater than the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this fraction is greater than the specified fraction, else <code>false</code>
     */
    @Override
    public boolean isGreater(Fraction fraction) {

        BinaryOperation<Fraction, Result<Boolean>> function =
            (BinaryOperation<Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, fraction);

        return result.result();
    }

    /**
     * Checks if this fraction is greater than the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this fraction is greater than the specified number, else <code>false</code>
     */
    @Override
    public boolean isGreater(Number number) {

        MixedBinaryOperation<Fraction, Number, Result<Boolean>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_NUMBER_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Checks if this fraction is greater than or equal to the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this fraction is greater than or equal to the specified fraction,
     *         else <code>false</code>
     */
    @Override
    public boolean isGreaterOrEqual(Fraction fraction) {

        BinaryOperation<Fraction, Result<Boolean>> function =
            (BinaryOperation<Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, fraction);

        return result.result();
    }

    /**
     * Checks if this fraction is greater than or equal to the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this fraction is greater than or equal to the specified number,
     *         else <code>false</code>
     */
    @Override
    public boolean isGreaterOrEqual(Number number) {

        MixedBinaryOperation<Fraction, Number, Result<Boolean>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_GREATER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Checks if this fraction is lesser than the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this fraction is lesser than the specified fraction, else <code>false</code>
     */
    @Override
    public boolean isLesser(Fraction fraction) {

        BinaryOperation<Fraction, Result<Boolean>> function =
            (BinaryOperation<Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, fraction);

        return result.result();
    }

    /**
     * Checks if this fraction is lesser than the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this fraction is lesser than the specified number, else <code>false</code>
     */
    @Override
    public boolean isLesser(Number number) {

        MixedBinaryOperation<Fraction, Number, Result<Boolean>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_NUMBER_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Checks if this fraction is lesser than or equal to the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this fraction is lesser than or equal to the specified fraction, else <code>false</code>
     */
    @Override
    public boolean isLesserOrEqual(Fraction fraction) {

        BinaryOperation<Fraction, Result<Boolean>> function =
            (BinaryOperation<Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, fraction);

        return result.result();
    }

    /**
     * Checks if this fraction is lesser than or equal to the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this fraction is lesser than or equal to the specified number, else <code>false</code>
     */
    @Override
    public boolean isLesserOrEqual(Number number) {

        MixedBinaryOperation<Fraction, Number, Result<Boolean>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_LESSER_THAN_OR_EQUAL_NUMBER_COMPARISON_FUNCTION);
        Result<Boolean> result = function.calculate(this, number);

        return result.result();
    }

    /**
     * Calculates a hash value for this fraction.
     *
     * @return a hash value
     */
    @Override
    public int hashCode() {

        final Number ZERO = createNumber(base(), "0");
        final Number ONE = createNumber(base(), "1");

        Number integerPart;
        if (hasIntegerPart()) {

            integerPart = integerPart();

        } else {

            integerPart = ZERO;
        }

        Number denominator;
        if (hasDenominator()) {

            denominator = denominator();

        } else {

            denominator = ZERO;
        }

        Number numerator;
        if (hasNumerator()) {

            numerator = numerator();

        } else {

            numerator = ONE;
        }

        return HashHelper.calculateHashCode(Fraction.class, integerPart, numerator, denominator);
    }

    /**
     * Compares this fraction with the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if this fraction is considered equal to the specified object, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o instanceof Fraction) {

            Fraction other = (Fraction) o;

            EqualityFunction<Fraction> function =
                (EqualityFunction<Fraction>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_EQUALITY_FUNCTION);
            boolean result = function.equals(this, other);

            return result;

        } else if (o instanceof Number) {

            Number other = (Number) o;

            MixedEqualityFunction<Fraction, Number> function =
                (MixedEqualityFunction<Fraction, Number>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_NUMBER_EQUALITY_FUNCTION);
            boolean result = function.equals(this, other);

            return result;
        }

        return false;
    }

    /**
     * Compares this fraction to the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>1</code> if this fraction is greater than the specified object, <code>0</code> if this
     *         fraction is equal to the specified object or <code>-1</code> if this fraction is lesser than the
     *         specified object
     *
     * @throws NullPointerException
     *         if the specified object is null
     * @throws ClassCastException
     *         if the specified object's type prevents it from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {

        if (o == null) {

            throw new NullPointerException();
        }

        if (o instanceof Number) {

            Number other = (Number) o;

            MixedComparator<Fraction, Number> comparator =
                (MixedComparator<Fraction, Number>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_NUMBER_COMPARATOR_FUNCTION);
            return comparator.compare(this, other);

        } else if (o instanceof Fraction) {

            Fraction other = (Fraction) o;

            Comparator<Fraction> comparator =
                (Comparator<Fraction>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_COMPARATOR_FUNCTION);
            return comparator.compare(this, other);
        }

        throw new ClassCastException();
    }

    /**
     * Negates this fraction (i.e. changes the sign).
     *
     * @return a fraction
     */
    @Override
    public Fraction negate() {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.NEGATE_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Calculates the square root for this fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction squareRoot() {

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Halves this fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction halving() {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.HALVING_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Doubles this fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction doubling() {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.DOUBLING_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Increments this fraction by one.
     *
     * @return a fraction
     */
    @Override
    public Fraction inc() {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_INCREMENT_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Decrements this fraction by one.
     *
     * @return a fraction
     */
    @Override
    public Fraction dec() {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_DECREMENT_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Adds this fraction and the specified number.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Fraction add(Number n) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_FRACTION_AND_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Adds this fraction and the specified fraction.
     *
     * @param f
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Fraction add(Fraction f) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.ADD_FRACTIONS_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Substracts the specified number from this fraction.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Fraction subtract(Number n) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_FRACTION_AND_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Subtracts the specified fractionr from this fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction subtract(Fraction f) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.SUBTRACT_FRACTIONS_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Multiplies this fraction with the specified number.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Fraction multiply(Number n) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_FRACTION_AND_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Multiplies this fraction with the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction multiply(Fraction f) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MULTIPLY_FRACTIONS_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Divides this fraction by the specified number.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Fraction divide(Number n) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIVIDE_FRACTION_BY_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Divides this fraction by the specified fraction.
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction divide(Fraction f) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.DIVIDE_FRACTIONS_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Compares this fraction and the specified number and returns the greater number (as fraction).
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Fraction max(Number n) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MAX_FRACTION_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Compares this fraction and the specified fraction and returns the greater number (as fraction).
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction max(Fraction f) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MAX_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Compares this fraction and the specified number and returns the smaller number (as fraction).
     *
     * @param n
     *        a number
     *
     * @return an expression
     */
    @Override
    public Fraction min(Number n) {

        MixedBinaryOperation<Fraction, Number, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Number, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MIN_FRACTION_NUMBER_FUNCTION);
        Result<Fraction> result = function.calculate(this, n);

        return result.result();
    }

    /**
     * Compares this fraction and the specified fraction and returns the smaller number (as fraction).
     *
     * @param f
     *        a fraction
     *
     * @return a fraction
     */
    @Override
    public Fraction min(Fraction f) {

        BinaryOperation<Fraction, Result<Fraction>> function =
            (BinaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.MIN_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, f);

        return result.result();
    }

    /**
     * Performs an exponentiation with this fraction as the base and the specified number as the exponent.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Fraction exponentiate(Number n) {

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Takes the specified number as base and calculates the logarithm for this number.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Fraction log(Number n) {

        // TODO Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Reduce fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction reduce() {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.REDUCE_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Returns the reciprocal of this fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction reciprocal() {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.RECIPROCAL_OF_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Translates this fraction into a number of the specified base.
     *
     * @param base
     *        the new base
     *
     * @return a fraction
     */
    @Override
    public Fraction rebase(int base) {

        MixedBinaryOperation<Fraction, Integer, Result<Fraction>> function =
            (MixedBinaryOperation<Fraction, Integer, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.REBASE_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this, base);

        return result.result();
    }

    /**
     * Returns the absolute value of this fraction.
     *
     * @return the absolute value
     */
    @Override
    public Fraction absoluteValue() {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_TO_ABSOLUTE_VALUE_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Calculates the square for this fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction square() {

        UnaryOperation<Fraction, Result<Fraction>> function =
            (UnaryOperation<Fraction, Result<Fraction>>) FunctionSingletons.getFunction(FunctionIdentifiers.SQUARE_FRACTION_FUNCTION);
        Result<Fraction> result = function.calculate(this);

        return result.result();
    }

    /**
     * Determines the common divisors for this fraction (i.e. numerator and denominator). The result set contains
     * divisors greater than one.
     *
     * @return a set of divisors
     */
    @Override
    public SortedSet<Number> commonDivisorSet() {

        UnaryOperation<Fraction, Result<SortedSet<Number>>> function =
            (UnaryOperation<Fraction, Result<SortedSet<Number>>>) FunctionSingletons.getFunction(FunctionIdentifiers.DETERMINE_COMMON_DIVISORS_FUNCTION);
        Result<SortedSet<Number>> result = function.calculate(this);

        return result.result();
    }

    /**
     * Determines the common prime factors for this fraction (i.e. numerator and denominator). The result set contains
     * the prime factors.
     *
     * @return a set of prime factors
     */
    @Override
    public SortedSet<Number> commonPrimeFactors() {

        UnaryOperation<Fraction, Result<SortedSet<Number>>> function =
            (UnaryOperation<Fraction, Result<SortedSet<Number>>>) FunctionSingletons.getFunction(FunctionIdentifiers.DETERMINE_COMMON_PRIME_FACTORS_FUNCTION);
        Result<SortedSet<Number>> result = function.calculate(this);

        return result.result();
    }

    /**
     * Checks if this fraction is within the specified bounds (including bounds).
     *
     * @param min
     *        a fraction (i.e. lower bound of an interval)
     * @param max
     *        a fraction (i.e. upper bound of an interval)
     *
     * @return <code>true</code> if this fraction is within the specified bounds, else <code>false</code>
     */
    @Override
    public boolean isWithinInterval(Fraction min, Fraction max) {

        TernaryOperation<Fraction, Result<Boolean>> function =
            (TernaryOperation<Fraction, Result<Boolean>>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_IS_WITHIN_INTERVAL);
        Result<Boolean> result = function.calculate(min, this, max);

        return result.result();
    }

}

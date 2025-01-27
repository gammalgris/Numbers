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

import static jmul.math.fractions.FractionHelper.DONT_CLONE;
import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.hash.HashHelper;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Sign;
import jmul.math.numbers.Signs;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.EqualityFunction;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.MixedComparator;
import jmul.math.operations.MixedEqualityFunction;
import jmul.math.operations.Result;


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

        // Check the signs. Certain sign combinations are not allowed.

        if (integerPart.isZero()) {

            if (integerPart.isNegative()) {

                String message = "The integer part must not be negative when zero!";
                throw new IllegalArgumentException(message);
            }

        } else {

            if (numerator.isNegative()) {

                String message = "The numerator must not be negative!";
                throw new IllegalArgumentException(message);
            }
        }

        if (numerator.isZero()) {

            if (numerator.isNegative()) {

                String message = "The numerator must not be negative when zero!";
                throw new IllegalArgumentException(message);
            }
        }

        if (denominator.isNegative()) {

            String message = "The denominator must not be negative!";
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

        // Add the integer part and the fraction.

        // TODO Implement this method
        throw new UnsupportedOperationException();
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

        Number newNumerator = integerPart().multiply(denominator()).add(numerator());
        Number newDenominator = new NumberImpl(denominator());

        Fraction newFraction = FractionHelper.createFraction(DONT_CLONE, newNumerator, newDenominator);

        return newFraction;
    }

    /**
     * Normalizes this fraction (i.e. extracts the integer part from the numerator).
     *
     * @return a fraction
     */
    @Override
    public Fraction normalizedMixedFraction() {

        Number extractedIntegerPart = new NumberImpl(base(), "0");
        Number newNumerator = new NumberImpl(numerator());
        Number newDenominator = new NumberImpl(denominator());

        while (newNumerator.isGreater(denominator())) {

            extractedIntegerPart = extractedIntegerPart.inc();
            newNumerator = newNumerator.subtract(denominator());
        }

        Number newIntegerPart = integerPart().add(extractedIntegerPart);

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

        final Number ZERO = new NumberImpl(base(), "0");
        final Number ONE = new NumberImpl(base(), "1");

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

        throw new UnsupportedOperationException();
    }

    /**
     * Calculates the square root for this fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction squareRoot() {

        throw new UnsupportedOperationException();
    }

    /**
     * Halves this fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction halving() {

        throw new UnsupportedOperationException();
    }

    /**
     * Doubles this fraction.
     *
     * @return a fraction
     */
    @Override
    public Fraction doubling() {

        throw new UnsupportedOperationException();
    }

    /**
     * Increments this fraction by one.
     *
     * @return a fraction
     */
    @Override
    public Fraction inc() {

        throw new UnsupportedOperationException();
    }

    /**
     * Decrements this fraction by one.
     *
     * @return a fraction
     */
    @Override
    public Fraction dec() {

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
    }

    /**
     * Divides this fraction by the specified number and returns the remainder of the division.
     *
     * @param n
     *        a number
     *
     * @return a fraction
     */
    @Override
    public Fraction modulo(Number n) {

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
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

        throw new UnsupportedOperationException();
    }

}

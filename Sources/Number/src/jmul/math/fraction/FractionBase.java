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

package jmul.math.fraction;


import java.util.Comparator;

import jmul.math.functions.FunctionSingletons;
import jmul.math.hash.HashHelper;
import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Signs;
import jmul.math.numbers.functions.EqualityFunction;


/**
 * A common base impementation for the existing implementations of fractions.
 *
 * @author Kristian Kutin
 */
abstract class FractionBase implements Fraction {

    /**
     * The default constructor.
     */
    FractionBase() {

        super();
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

        return compareTo(fraction) > 0;
    }

    /**
     * Checks if this fraction is greater than or equal to the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return <code>true</code> if this fraction is greater than or equal to the specified fraction, else <code>false</code>
     */
    @Override
    public boolean isGreaterOrEqual(Fraction fraction) {

        return compareTo(fraction) >= 0;
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

        return compareTo(fraction) < 0;
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

        return compareTo(fraction) <= 0;
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

        if (o == null) {

            return false;
        }

        if (o instanceof Fraction) {

            Fraction other = (Fraction) o;

            EqualityFunction<Fraction> function =
                (EqualityFunction<Fraction>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_EQUALITY_FUNCTION);
            boolean result = function.equals(this, other);

            return result;
        }

        return false;
    }

    /**
     * Compares this fraction to the specified fraction.
     *
     * @param other
     *        an expression
     *
     * @return <code>1</code> if this expression is greater than the specified expression, <code>0</code> if this
     *         expression is equal to the specified expression or <code>-1</code> if this expression is lesser than the
     *         specified expression
     */
    @Override
    public int compareTo(Fraction other) {

        Comparator<Fraction> comparator =
            (Comparator<Fraction>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_COMPARATOR_FUNCTION);
        int result = comparator.compare(this, other);

        return result;
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

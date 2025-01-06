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


import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;


/**
 * A helper class for creating expressions.
 *
 * @author Kristian Kutin
 */
public final class FractionHelper {

    /**
     * A constant value indicating to clone the specified parameters.
     */
    public static final boolean CLONE;

    /**
     * A constant value indicating to not clone the specified parameters.
     */
    public static final boolean DONT_CLONE;

    /*
     * The static initializer.
     */
    static {

        CLONE = true;
        DONT_CLONE = !CLONE;
    }

    /**
     * The default constructor.
     */
    private FractionHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a number according to the specified parameter.
     *
     * @param base
     *        the number base
     * @param s
     *        a number string or <code>null</code> for infinity
     *
     * @return a number
     */
    private static Number createNumber(int base, String s) {

        Number n;
        if (s == null) {

            n = new NumberImpl(base);

        } else {

            n = new NumberImpl(base, s);
        }

        return n;
    }

    /**
     * Creates a fraction according to the specified parameters.
     *
     * @param base
     *        the number base
     * @param s
     *        a string representing the integer part of a fraction
     *
     * @return a fraction (i.e. a fraction with only the integer part)
     */
    public static Fraction createFraction(int base, String s) {

        Number n = createNumber(base, s);

        final Number NUMERATOR = new NumberImpl(n.base(), "0");
        final Number DENOMINATOR = new NumberImpl(n.base(), "1");

        return new MixedFraction(n, NUMERATOR, DENOMINATOR);
    }

    /**
     * Creates a fraction according to the specified number.
     *
     * @param cloneFlag
     *        <code>true</code> indicates the specified number is to be cloned, else <code>false</code>
     * @param number
     *        a number representing the integer part of a fraction
     *
     * @return a fraction (i.e. a fraction with only the integer part)
     */
    public static Fraction createFraction(boolean cloneFlag, Number number) {

        if (number == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        final Number NUMERATOR = new NumberImpl(number.base(), "0");
        final Number DENOMINATOR = new NumberImpl(number.base(), "1");

        if (cloneFlag) {

            Number clone = new NumberImpl(number);
            return new MixedFraction(clone, NUMERATOR, DENOMINATOR);

        } else {

            return new MixedFraction(number, NUMERATOR, DENOMINATOR);
        }
    }

    /**
     * Creates a fraction according to the specified parameters.
     *
     * @param base
     *        the number base
     * @param numeratorString
     *        a string representing the numerator of a fraction
     * @param denominatorString
     *        a string representing the denominator of a fraction
     *
     * @return a fraction
     */
    public static Fraction createFraction(int base, String numeratorString, String denominatorString) {

        Number numerator = createNumber(base, numeratorString);
        Number denominator = createNumber(base, denominatorString);

        final Number integer = new NumberImpl(numerator.base(), "0");

        return new MixedFraction(integer, numerator, denominator);
    }

    /**
     * Creates a fraction according to the specified parameters.
     *
     * @param cloneFlag
     *        <code>true</code> indicates the specified numbers are to be cloned, else <code>false</code>
     * @param numerator
     *        a number representing the numerator of a fraction
     * @param denominator
     *        a number the denominator of a fraction
     *
     * @return a fraction
     */
    public static Fraction createFraction(boolean cloneFlag, Number numerator, Number denominator) {

        if (numerator == null) {

            throw new IllegalArgumentException("No numerator (null) was specified!");
        }

        if (denominator == null) {

            throw new IllegalArgumentException("No denominator (null) was specified!");
        }

        final Number integer = new NumberImpl(numerator.base(), "0");

        if (cloneFlag) {

            Number clonedNumerator = new NumberImpl(numerator);
            Number clonedDenominator = new NumberImpl(denominator);
            return new MixedFraction(integer, clonedNumerator, clonedDenominator);

        } else {

            return new MixedFraction(integer, numerator, denominator);
        }
    }

    /**
     * Creates a fraction according to the specified parameters.
     *
     * @param base
     *        the number base
     * @param integerString
     *        a string representing the integer part of a fraction
     * @param numeratorString
     *        a string representing the numerator of a fraction
     * @param denominatorString
     *        a string representing the denominator of a fraction
     *
     * @return a fraction (i.e. a mixed fraction with an integer part)
     */
    public static Fraction createFraction(int base, String integerString, String numeratorString,
                                          String denominatorString) {

        Number integer = createNumber(base, integerString);
        Number numerator = createNumber(base, numeratorString);
        Number denominator = createNumber(base, denominatorString);
        return new MixedFraction(integer, numerator, denominator);
    }

    /**
     * Creates an expression according to the specified numbers.
     *
     * @param cloneFlag
     *        <code>true</code> indicates the specified numbers are to be cloned, else <code>false</code>
     * @param integer
     *        a number
     * @param numerator
     *        a number
     * @param denominator
     *        a number
     *
     * @return a fraction (i.e. a mixed fraction with an integer part)
     */
    public static Fraction createFraction(boolean cloneFlag, Number integer, Number numerator, Number denominator) {

        if (integer == null) {

            throw new IllegalArgumentException("No integer (null) was specified!");
        }

        if (numerator == null) {

            throw new IllegalArgumentException("No numerator (null) was specified!");
        }

        if (denominator == null) {

            throw new IllegalArgumentException("No denominator (null) was specified!");
        }

        if (cloneFlag) {

            Number clonedInteger = new NumberImpl(integer);
            Number clonedNumerator = new NumberImpl(numerator);
            Number clonedDenominator = new NumberImpl(denominator);
            return new MixedFraction(clonedInteger, clonedNumerator, clonedDenominator);

        } else {

            return new MixedFraction(integer, numerator, denominator);
        }
    }

}

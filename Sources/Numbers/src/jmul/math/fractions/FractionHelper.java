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


import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.signs.Sign;


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
     * Creates an integer part with a default value.
     *
     * @param base
     *        the number base
     *
     * @return a number
     */
    private static Number createDefaultIntegerPart(int base) {

        String symbol = PositionalNumeralSystems.toString(base, 0);

        return createNumber(base, symbol);
    }

    /**
     * Creates a numerator with a default value.
     *
     * @param base
     *        the number base
     *
     * @return a number
     */
    private static Number createDefaultNumerator(int base) {

        String symbol = PositionalNumeralSystems.toString(base, 0);

        return createNumber(base, symbol);
    }

    /**
     * Creates a denominator with a default value.
     *
     * @param base
     *        a number base
     *
     * @return a number
     */
    private static Number createDefaultDenominator(int base) {

        String symbol = PositionalNumeralSystems.toString(base, 1);

        return createNumber(base, symbol);
    }

    /**
     * Clones the specified number depending on the specified flag.
     *
     * @param cloneFlag
     *        <code>true</code> clones thespecified number, <code>false</code> doesn't clone the specified number
     * @param number
     *        a number
     *
     * @return the specified number or a cloned number
     */
    private static Number cloneNumber(boolean cloneFlag, Number number) {

        if (number == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        if (cloneFlag) {

            return createNumber(number);
        }

        return number;
    }

    /**
     * Checks the specified parameter.
     *
     * @param integerPart
     *        the integer part of a mixed fraction
     *
     * @return the specified parameter
     */
    private static Number checkIntgerPart(Number integerPart) {

        if (integerPart == null) {

            throw new IllegalArgumentException("No integer part (null) was specified!");
        }

        return integerPart;
    }

    /**
     * Checks the specified parameter.
     *
     * @param numerator
     *        the numerator of a mixed fraction.
     *
     * @return the specified parameter
     */
    private static Number checkNumerator(Number numerator) {

        if (numerator == null) {

            throw new IllegalArgumentException("No numerator (null) was specified!");
        }

        return numerator;
    }

    /**
     * Checks the specified parameter.
     *
     * @param denominator
     *        the denominator of a mixed fraction
     *
     * @return the specified parameter
     */
    private static Number checkDenominator(Number denominator) {

        if (denominator == null) {

            throw new IllegalArgumentException("No denominator (null) was specified!");
        }

        return denominator;
    }

    /**
     * Creates a fraction according to the specified parameters.
     *
     * @param base
     *        the number base
     *
     * @return a fraction (i.e. a fraction with only the integer part)
     */
    public static Fraction createFraction(int base) {

        Number newIntegerPart = createNumber(base);
        Number defaultNumerator = createDefaultNumerator(base);
        Number defaultDenominator = createDefaultDenominator(base);

        return createFractionAndDontCheckOrModifyParameters(newIntegerPart, defaultNumerator, defaultDenominator);
    }

    /**
     * Creates a fraction according to the specified parameters.
     *
     * @param sign
     *        the sign of the number
     * @param base
     *        the number base
     *
     * @return a fraction (i.e. a fraction with only the integer part)
     */
    public static Fraction createFraction(Sign sign, int base) {

        Number newIntegerPart = createNumber(sign, base);
        Number defaultNumerator = createDefaultNumerator(base);
        Number defaultDenominator = createDefaultDenominator(base);

        return createFractionAndDontCheckOrModifyParameters(newIntegerPart, defaultNumerator, defaultDenominator);
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

        Number newIntegerPart = createNumber(base, s);
        Number defaultNumerator = createDefaultNumerator(base);
        Number defaultDenominator = createDefaultDenominator(base);

        return createFractionAndDontCheckOrModifyParameters(newIntegerPart, defaultNumerator, defaultDenominator);
    }

    /**
     * Creates a fraction according to the specified number.
     *
     * @param cloneFlag
     *        <code>true</code> indicates the specified number is to be cloned, else <code>false</code>
     * @param integerPart
     *        a number representing the integer part of a fraction
     *
     * @return a fraction (i.e. a fraction with only the integer part)
     */
    public static Fraction createFraction(boolean cloneFlag, Number integerPart) {

        checkIntgerPart(integerPart);

        int base = integerPart.base();

        Number newIntegerPart = cloneNumber(cloneFlag, integerPart);
        Number defaultNumerator = createDefaultNumerator(base);
        Number defaultDenominator = createDefaultDenominator(base);

        return createFractionAndDontCheckOrModifyParameters(newIntegerPart, defaultNumerator, defaultDenominator);
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

        Number defaultIntegerPart = createDefaultIntegerPart(base);
        Number newNumerator = createNumber(base, numeratorString);
        Number newDenominator = createNumber(base, denominatorString);

        if (newNumerator.isNegative() && newDenominator.isNegative()) {

            newNumerator = newNumerator.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newNumerator.isPositive() && newDenominator.isNegative()) {

            newNumerator = newNumerator.negate();
            newDenominator = newDenominator.absoluteValue();
        }

        return createFractionAndDontCheckOrModifyParameters(defaultIntegerPart, newNumerator, newDenominator);
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

        checkNumerator(numerator);
        checkDenominator(denominator);

        int base = numerator.base();

        Number defaultIntegerPart = createDefaultIntegerPart(base);
        Number newNumerator = numerator;
        Number newDenominator = denominator;

        if (newNumerator.isNegative() && newDenominator.isNegative()) {

            newNumerator = newNumerator.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newNumerator.isPositive() && newDenominator.isNegative()) {

            newNumerator = newNumerator.negate();
            newDenominator = newDenominator.absoluteValue();

        } else {

            newNumerator = cloneNumber(cloneFlag, newNumerator);
            newDenominator = cloneNumber(cloneFlag, denominator);
        }

        return createFractionAndDontCheckOrModifyParameters(defaultIntegerPart, newNumerator, newDenominator);
    }

    /**
     * The method checks for illegal combinations of numbers and signs.
     *
     * @param componentName
     *        the component of a mixed fraction
     * @param number
     *        the actual component of the mixed fraction
     */
    private static void checkForNegativeZero(String componentName, Number number) {

        if (componentName == null) {

            throw new IllegalArgumentException("No component name (null) was specified!");
        }

        if (number == null) {

            throw new IllegalArgumentException("No number (null) was specified!");
        }

        if (number.isZero() && number.isNegative()) {

            String message = String.format("The %s should not be zero and negative!", componentName);
            throw new IllegalArgumentException(message);
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

        Number newIntegerPart = createNumber(base, integerString);
        Number newNumerator = createNumber(base, numeratorString);
        Number newDenominator = createNumber(base, denominatorString);

        checkForNegativeZero("integer part", newIntegerPart);
        checkForNegativeZero("numerator", newNumerator);
        checkForNegativeZero("denominator", newDenominator);

        if (newIntegerPart.isNegative() && newNumerator.isNegative() && newDenominator.isNegative()) {

            newNumerator = newNumerator.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newIntegerPart.isNegative() && newNumerator.isNegative() && newDenominator.isPositive()) {

            newIntegerPart = newIntegerPart.absoluteValue();
            newNumerator = newNumerator.absoluteValue();

        } else if (newIntegerPart.isNegative() && newNumerator.isPositive() && newDenominator.isNegative()) {

            newIntegerPart = newIntegerPart.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newIntegerPart.isPositive() && newNumerator.isNegative() && newDenominator.isNegative()) {

            newNumerator = newNumerator.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newIntegerPart.isPositive() && newNumerator.isNegative() && newDenominator.isPositive()) {

            if (!newIntegerPart.isZero()) {

                newIntegerPart = newIntegerPart.negate();
            }
            newNumerator = newNumerator.absoluteValue();

        } else if (newIntegerPart.isPositive() && newNumerator.isPositive() && newDenominator.isNegative()) {

            if (!newIntegerPart.isZero()) {

                newIntegerPart = newIntegerPart.negate();

            } else {

                newNumerator = newNumerator.negate();
            }
            newDenominator = newDenominator.absoluteValue();
        }

        return createFractionAndDontCheckOrModifyParameters(newIntegerPart, newNumerator, newDenominator);
    }

    /**
     * Creates an expression according to the specified numbers.
     *
     * @param cloneFlag
     *        <code>true</code> indicates the specified numbers are to be cloned, else <code>false</code>
     * @param integerPart
     *        the integer part of a fraction
     * @param numerator
     *        the numerator of a fraction
     * @param denominator
     *        the denominator of a fraction
     *
     * @return a fraction (i.e. a mixed fraction with an integer part)
     */
    public static Fraction createFraction(boolean cloneFlag, Number integerPart, Number numerator, Number denominator) {

        checkIntgerPart(integerPart);
        checkNumerator(numerator);
        checkDenominator(denominator);

        Number newIntegerPart = integerPart;
        Number newNumerator = numerator;
        Number newDenominator = denominator;

        checkForNegativeZero("integer part", newIntegerPart);
        checkForNegativeZero("numerator", newNumerator);
        checkForNegativeZero("denominator", newDenominator);

        if (newIntegerPart.isNegative() && newNumerator.isNegative() && newDenominator.isNegative()) {

            newIntegerPart = cloneNumber(cloneFlag, newIntegerPart);
            newNumerator = newNumerator.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newIntegerPart.isNegative() && newNumerator.isNegative() && newDenominator.isPositive()) {

            newIntegerPart = newIntegerPart.absoluteValue();
            newNumerator = newNumerator.absoluteValue();
            newDenominator = cloneNumber(cloneFlag, newDenominator);

        } else if (newIntegerPart.isNegative() && newNumerator.isPositive() && newDenominator.isNegative()) {

            newIntegerPart = newIntegerPart.absoluteValue();
            newNumerator = cloneNumber(cloneFlag, newNumerator);
            newDenominator = newDenominator.absoluteValue();

        } else if (newIntegerPart.isPositive() && newNumerator.isNegative() && newDenominator.isNegative()) {

            newIntegerPart = cloneNumber(cloneFlag, newIntegerPart);
            newNumerator = newNumerator.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newIntegerPart.isPositive() && newNumerator.isNegative() && newDenominator.isPositive()) {

            if (!newIntegerPart.isZero()) {

                newIntegerPart = newIntegerPart.negate();
                newNumerator = newNumerator.absoluteValue();

            } else {

                newIntegerPart = cloneNumber(cloneFlag, newIntegerPart);
                newNumerator = cloneNumber(cloneFlag, newNumerator);
            }
            newDenominator = cloneNumber(cloneFlag, newDenominator);

        } else if (newIntegerPart.isPositive() && newNumerator.isPositive() && newDenominator.isNegative()) {

            if (!newIntegerPart.isZero()) {

                newIntegerPart = newIntegerPart.negate();
                newNumerator = cloneNumber(cloneFlag, newNumerator);

            } else {

                newIntegerPart = cloneNumber(cloneFlag, newIntegerPart);
                newNumerator = newNumerator.negate();
            }
            newDenominator = newDenominator.absoluteValue();

        } else {

            newIntegerPart = cloneNumber(cloneFlag, newIntegerPart);
            newNumerator = cloneNumber(cloneFlag, newNumerator);
            newDenominator = cloneNumber(cloneFlag, newDenominator);
        }

        return createFractionAndDontCheckOrModifyParameters(newIntegerPart, newNumerator, newDenominator);
    }

    /**
     * Creates an expression according to the specified numbers.
     *
     * @param integerPart
     *        the integer part of a fraction
     * @param numerator
     *        the numerator of a fraction
     * @param denominator
     *        the denominator of a fraction
     *
     * @return a fraction (i.e. a mixed fraction with an integer part)
     */
    private static Fraction createFractionAndDontCheckOrModifyParameters(Number integerPart, Number numerator,
                                                                         Number denominator) {

        return new MixedFraction(integerPart, numerator, denominator);
    }

}

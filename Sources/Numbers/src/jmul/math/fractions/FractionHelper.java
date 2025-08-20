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


import jmul.math.constants.Constant;
import static jmul.math.constants.ConstantHelper.createConstant;
import jmul.math.numbers.Constants;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import jmul.math.numbers.creation.CreationParameter;
import jmul.math.numbers.creation.CreationParameters;
import jmul.math.signs.Sign;


/**
 * A helper class for creating expressions.
 *
 * @author Kristian Kutin
 */
public final class FractionHelper {

    /**
     * A default value for the integer part.
     */
    private static final Constant DEFAULT_INTEGER_PART;

    /**
     * A default value for the numerator.
     */
    private static final Constant DEFAULT_NUMERATOR;

    /**
     * A default value for the denominator.
     */
    private static final Constant DEFAULT_DENOMINATOR;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_INTEGER_PART = createConstant(Constants.DEFAULT_NUMBER_BASE, "0");
        DEFAULT_NUMERATOR = createConstant(Constants.DEFAULT_NUMBER_BASE, "0");
        DEFAULT_DENOMINATOR = createConstant(Constants.DEFAULT_NUMBER_BASE, "1");
    }

    /**
     * The default constructor.
     */
    private FractionHelper() {

        throw new UnsupportedOperationException();
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
    public static Fraction createInfinity(int base) {

        Number newIntegerPart = NumberHelper.createInfinity(base);
        Number defaultNumerator = DEFAULT_NUMERATOR.value(base);
        Number defaultDenominator = DEFAULT_DENOMINATOR.value(base);

        return createFractionAndDontCheckOrModifyParameters(newIntegerPart, defaultNumerator, defaultDenominator);
    }

    /**
     * Creates a fraction according to the specified parameters.
     *
     * @param base
     *        the number base
     * @param sign
     *        the sign of the number
     *
     * @return a fraction (i.e. a fraction with only the integer part)
     */
    public static Fraction createInfinity(int base, Sign sign) {

        Number newIntegerPart = NumberHelper.createInfinity(base, sign);
        Number defaultNumerator = DEFAULT_NUMERATOR.value(base);
        Number defaultDenominator = DEFAULT_DENOMINATOR.value(base);

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

        Number newIntegerPart = NumberHelper.createNumber(base, s);
        Number defaultNumerator = DEFAULT_NUMERATOR.value(base);
        Number defaultDenominator = DEFAULT_DENOMINATOR.value(base);

        return createFractionAndDontCheckOrModifyParameters(newIntegerPart, defaultNumerator, defaultDenominator);
    }

    /**
     * Creates a fraction according to the specified number.
     *
     * @param creationParameter
     *        indicates if the specified number is to be cloned or not
     * @param integerPart
     *        a number representing the integer part of a fraction
     *
     * @return a fraction (i.e. a fraction with only the integer part)
     */
    public static Fraction createFraction(CreationParameter creationParameter, Number integerPart) {

        Number newIntegerPart = NumberHelper.createNumber(creationParameter, integerPart);

        int base = integerPart.base();

        Number defaultNumerator = DEFAULT_NUMERATOR.value(base);
        Number defaultDenominator = DEFAULT_DENOMINATOR.value(base);

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

        Number defaultIntegerPart = DEFAULT_INTEGER_PART.value(base);
        Number newNumerator = NumberHelper.createNumber(base, numeratorString);
        Number newDenominator = NumberHelper.createNumber(base, denominatorString);

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
     * @param creationParameter
     *        indicates if the specified numbers are to be cloned or not
     * @param numerator
     *        a number representing the numerator of a fraction
     * @param denominator
     *        a number the denominator of a fraction
     *
     * @return a fraction
     */
    public static Fraction createFraction(CreationParameter creationParameter, Number numerator, Number denominator) {

        checkNumerator(numerator);
        checkDenominator(denominator);

        int base = numerator.base();

        Number defaultIntegerPart = DEFAULT_INTEGER_PART.value(base);
        Number newNumerator = numerator;
        Number newDenominator = denominator;

        if (newNumerator.isNegative() && newDenominator.isNegative()) {

            newNumerator = newNumerator.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newNumerator.isPositive() && newDenominator.isNegative()) {

            newNumerator = newNumerator.negate();
            newDenominator = newDenominator.absoluteValue();

        } else {

            newNumerator = NumberHelper.createNumber(creationParameter, newNumerator);
            newDenominator = NumberHelper.createNumber(creationParameter, denominator);
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

        Number newIntegerPart = NumberHelper.createNumber(base, integerString);
        Number newNumerator = NumberHelper.createNumber(base, numeratorString);
        Number newDenominator = NumberHelper.createNumber(base, denominatorString);

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
     * Creates a fraction according to the specified numbers.
     *
     * @param creationParameter
     *        indicates if the specified numbers are to be cloned or not
     * @param integerPart
     *        the integer part of a fraction
     * @param numerator
     *        the numerator of a fraction
     * @param denominator
     *        the denominator of a fraction
     *
     * @return a fraction (i.e. a mixed fraction with an integer part)
     */
    public static Fraction createFraction(CreationParameter creationParameter, Number integerPart, Number numerator,
                                          Number denominator) {

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

            newIntegerPart = NumberHelper.createNumber(creationParameter, newIntegerPart);
            newNumerator = newNumerator.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newIntegerPart.isNegative() && newNumerator.isNegative() && newDenominator.isPositive()) {

            newIntegerPart = newIntegerPart.absoluteValue();
            newNumerator = newNumerator.absoluteValue();
            newDenominator = NumberHelper.createNumber(creationParameter, newDenominator);

        } else if (newIntegerPart.isNegative() && newNumerator.isPositive() && newDenominator.isNegative()) {

            newIntegerPart = newIntegerPart.absoluteValue();
            newNumerator = NumberHelper.createNumber(creationParameter, newNumerator);
            newDenominator = newDenominator.absoluteValue();

        } else if (newIntegerPart.isPositive() && newNumerator.isNegative() && newDenominator.isNegative()) {

            newIntegerPart = NumberHelper.createNumber(creationParameter, newIntegerPart);
            newNumerator = newNumerator.absoluteValue();
            newDenominator = newDenominator.absoluteValue();

        } else if (newIntegerPart.isPositive() && newNumerator.isNegative() && newDenominator.isPositive()) {

            if (!newIntegerPart.isZero()) {

                newIntegerPart = newIntegerPart.negate();
                newNumerator = newNumerator.absoluteValue();

            } else {

                newIntegerPart = NumberHelper.createNumber(creationParameter, newIntegerPart);
                newNumerator = NumberHelper.createNumber(creationParameter, newNumerator);
            }

            newDenominator = NumberHelper.createNumber(creationParameter, newDenominator);

        } else if (newIntegerPart.isPositive() && newNumerator.isPositive() && newDenominator.isNegative()) {

            if (!newIntegerPart.isZero()) {

                newIntegerPart = newIntegerPart.negate();
                newNumerator = NumberHelper.createNumber(creationParameter, newNumerator);

            } else {

                newIntegerPart = NumberHelper.createNumber(creationParameter, newIntegerPart);
                newNumerator = newNumerator.negate();
            }
            newDenominator = newDenominator.absoluteValue();

        } else {

            newIntegerPart = NumberHelper.createNumber(creationParameter, newIntegerPart);
            newNumerator = NumberHelper.createNumber(creationParameter, newNumerator);
            newDenominator = NumberHelper.createNumber(creationParameter, newDenominator);
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

    /**
     * Creates a clone of the specified fraction.
     *
     * @param fraction
     *        a fraction
     *
     * @return a fraction
     */
    public static Fraction cloneFraction(Fraction fraction) {

        if (fraction.hasIntegerPart() && !fraction.hasNumerator()) {

            return createFraction(CreationParameters.CLONE, fraction.integerPart());

        } else if (!fraction.hasIntegerPart() && fraction.hasNumerator()) {

            return createFraction(CreationParameters.CLONE, fraction.numerator(), fraction.denominator());

        } else {

            return createFraction(CreationParameters.CLONE, fraction.integerPart(), fraction.numerator(),
                                  fraction.denominator());
        }
    }

}

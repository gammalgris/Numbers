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


import static jmul.math.fraction.FractionHelper.DONT_CLONE;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Sign;


/**
 * This class implements a mixed quotient.
 *
 * @author Kristian Kutin
 */
class MixedFraction extends FractionBase implements Fraction {

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

}

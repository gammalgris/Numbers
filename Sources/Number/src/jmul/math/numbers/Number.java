/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

package jmul.math.numbers;


import jmul.math.numbers.nodes.LinkedDigitList;


/**
 * The interface represents a number (i.e. natural numbers, integers and real numbers, but no irrational
 * numbers) and describes operations to access its digits and properties (e.g. sign).<br>
 * <br>
 * <i>Note:<br>
 * <ul>
 * <li>The linked list data model is exposed as it is required for accessing digits and is a
 * requirement for performing arithmetic operations.</li>
 * <li>Numbers should not be modifiable.</li>
 * <li>Operations should return a modified copy of a number</li>
 * <li>Periodic decimal number can only be approximated (i.e. the decimal places must be truncated).</li>
 * </ul>
 * </i>
 *
 * @author Kristian Kutin
 */
public interface Number extends LinkedDigitList, ArithmeticOperations, NumberComparisons, Comparable<Number> {

    /**
     * Returns the sign of this number.
     *
     * @return a sign
     */
    Sign sign();

    /**
     * Returns the base of the underlying numeral system for this number.
     *
     * @return a base
     */
    int base();

    /**
     * Returns a scientific notation for this number. The default decimal separator is used.
     *
     * @return a scientific notation for this number
     */
    String toScientificNotation();

    /**
     * Returns a scientific notation for this number. The specified decimal separator is used.
     *
     * @param decimalSeparator
     *        a decimal separator
     *
     * @return a scientific notation for this number
     */
    String toScientificNotation(char decimalSeparator);

    /**
     * Returns a standard notation for this number. The default decimal separator is used.
     *
     * @return a standard notation for this number
     */
    String toStandardNotation();

    /**
     * Returns a standard notation for this number. The specified decimal separator is used.
     *
     * @param decimalSeparator
     *        a decimal separator
     *
     * @return a standard notation for this number
     */
    String toStandardNotation(char decimalSeparator);

    /**
     * Returns a string representation (i.e. standard notation) for this number. The default decimal separator is used.
     *
     * @return a string representation
     */
    String toString();

    /**
     * Returns a string representation (i.e. standard notation) for this number. The specified decimal separator is used.
     *
     * @param decimalSeparator
     *        a decimal separator
     *
     * @return a string representation
     */
    String toString(char decimalSeparator);

    /**
     * Checks if this number represents infinity.
     *
     * @return <code>true</code> if this number represents infinity, else <code>false</code>
     */
    boolean isInfinity();

    /**
     * Checks if this number represents zero.
     *
     * @return <code>true</code> if this number represents zero, else <code>false</code>
     */
    boolean isZero();

    /**
     * Checks if this number is a fraction.
     *
     * @return <code>true</code> if this number is a fraction, else <code>false</code>
     */
    boolean isFraction();

    /**
     * Checks if this number is an integer.
     *
     * @return <code>true</code> if this number is an integer, else <code>false</code>
     */
    boolean isInteger();

    /**
     * Checks if this number is an even integer.
     *
     * @return <code>true</code> if this number is an even integer, else <code>false</code>
     */
    boolean isEven();

    /**
     * Checks if this number is an odd integer.
     *
     * @return <code>true</code> if this number is an odd integer, else <code>false</code>
     */
    boolean isOdd();

    /**
     * Checks if this number is a natural number, including zero.
     *
     * @return <code>true</code> if this number is a natural number, including zero, else <code>false</code>
     */
    boolean isNaturalNumberIncludingZero();

    /**
     * Checks if this number is a natural number.
     *
     * @return <code>true</code> if this number is a natural number, else <code>false</code>
     */
    boolean isNaturalNumber();

    /**
     * Checks if this number is positive.
     *
     * @return <code>true</code> if this number is positive, else <code>false</code>
     */
    boolean isPositive();

    /**
     * Checks if this number is negative.
     *
     * @return <code>true</code> if this number is negative, else <code>false</code>
     */
    boolean isNegative();

    /**
     * Returns the absolute value of this number.
     *
     * @return the absolute value
     */
    Number absoluteValue();

    /**
     * Returns a truncated number (i.e. where the fractional part is removed).
     *
     * @return a number
     */
    Number truncate();

    /**
     * Returns a number where the decimal point is shifted to the left by one digit.
     *
     * @return a number
     */
    Number shiftLeft();

    /**
     * Returns a number where the decimal point is shifted to the left by the number of specified
     * shifts.
     *
     * @param shifts
     *        the nuber of shifts to be performed. Zero will perform no shift. A positive number
     *        will perform shifts to the left. A negative number will perform shifts to the right.
     *
     * @return a number
     */
    Number shiftLeft(Number shifts);

    /**
     * Returns a number where the decimal point is shifted to the right by one digit.
     *
     * @return a number
     */
    Number shiftRight();

    /**
     * Returns a number where the decimal point is shifted to the right by the number of specified
     * shifts.
     *
     * @param shifts
     *        the nuber of shifts to be performed. Zero will perform no shift. A positive number
     *        will perform shifts to the right. A negative number will perform shifts to the left.
     *
     * @return a number
     */
    Number shiftRight(Number shifts);

    /**
     * Translates this number into a number of the specified base.
     *
     * @param base
     *        the new base
     *
     * @return a number
     */
    Number rebase(int base);

}

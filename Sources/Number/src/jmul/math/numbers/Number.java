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
import jmul.math.numbers.operations.ArithmeticOperations;


/**
 * The interface represents a number (i.e. natural numbers, integers and real numbers, but no irrational
 * numbers) and describes operations to access its digits and properties (e.g. sign).<br>
 * <br>
 * <i>Note:<br>
 * The linked list data model is exposed as it is required for accessing digits and is a
 * requirement for performing arithmetic operations.</i><br>
 * <br>
 * <i>Note:<br>
 * Numbers should not be modifiable.</i><br>
 * <br>
 * <i>Note:<br>
 * Periodic decimal number can only be approximated (i.e. the decimal places must be truncated).</i>
 *
 * @author Kristian Kutin
 */
public interface Number extends LinkedDigitList, ArithmeticOperations, Comparable<Number> {

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
     * Returns a scientific notation for this number.
     *
     * @return a scientific notation for this number
     */
    String toScientificNotation();

    /**
     * Returns a standard notation for this number.
     *
     * @return a standard notation for this number
     */
    String toStandardNotation();

    /**
     * Returns a string representation for this number.
     *
     * @return a standard notation for this number
     */
    String toString();

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

}

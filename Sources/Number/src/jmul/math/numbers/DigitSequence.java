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

import jmul.math.numbers.digits.Digit;


/**
 * This interface represents a digit sequence (i.e. a number).<br>
 * <br>
 * <i>Note:<br>
 * This is an alternative way to represent various numbers which combines integer and floating point numbers.
 * Implementations are likely to require more memory and be less efficient than the built-in primitive types and
 * their wrappers.</i>
 *
 * @param <T>
 *        the set of digits which comprises the digit sequence
 */
public interface DigitSequence<T extends Digit> extends Comparable<DigitSequence<T>> {

    /**
     * Returns the count of digits left of the comma.
     *
     * @return a partial digit count
     */
    int leftDigits();

    /**
     * Returns the count of digits right of the comma.
     *
     * @return a partial digit count
     */
    int rightDigits();

    /**
     * Returns the total count of digits (i.e. left and right of the comma).
     *
     * @return a digit count
     */
    int digits();

    /**
     * Returns the digit at the specified index positions.
     *
     * @param anIndex
     *        a positive or negative number specifiying the position of a digit. 0 represents the position of the
     *        comma, a positive number a digit left of the comma and a negative number a digit right of the comma.
     *        The higher the absolute value the farther away it is from the comma (i.e. index 0).
     *
     * @return a digit
     */
    T digitAt(int anIndex);

    /**
     * Returns the sign of this number (i.e. digit sequence).
     *
     * @return a sign
     */
    Sign sign();

    /**
     * Checks if this number is negative.
     *
     * @return <code>true</code> if this number is negative, else <code>false</code>
     */
    boolean isNegative();

    /**
     * Checks if this number is positive.
     *
     * @return <code>true</code> if this number is positive, else <code>false</code>
     */
    boolean isPositive();

    /**
     * Returns the scientific notation for this number (i.e. digit sequence).
     *
     * @return a string containing a number with a specific notation
     */
    String toScientificNotation();

    /**
     * Returns the standard notation for this number (i.e. digit sequence).
     *
     * @return a string containing a number with a specific notation
     */
    String toStandardNotation();

    /**
     * Checks if this number represents infinity.
     *
     * @return <code>true</code> if this number represents infinity, else <code>false</code>
     */
    boolean isInfinity();

    /**
     * Returns the base for this number.
     *
     * @return a base
     */
    int base();

    /**
     * Checks if this number is zero.
     *
     * @return <code>true</code> if this number is zero, else <code>false</code>
     */
    boolean isZero();

}

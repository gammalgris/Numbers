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

package jmul.math.digits;


/**
 * This interface represents a digit which is used within a positional numeral system
 * of a specific base.<br>
 * A digit has a symbol and an ordinal value, although a symbol can be used in positional
 * numeral systems of different bases.<br>
 * <br>
 * A digit contains informations about the base of a positional numeral system in order
 * to distinguish digits from positional numeral systems with different bases.<br>
 * <br>
 * The ordinal number is required to determine the natural ordering of digits. Digits from
 * different positional numeral systems but with the same ordinal number are equal.
 *
 * @author Kristian Kutin
 */
public interface Digit extends Comparable<Digit> {

    /**
     * Returns the symbol associated with this digit.
     *
     * @return a symbol, i.e. a single character
     */
    char symbol();

    /**
     * Returns the base of the positional numeral system.
     *
     * @return a base
     */
    int base();

    /**
     * Returns the ordinal number for this digit.
     *
     * @return an ordinal number
     */
    int ordinal();

    /**
     * Checks if this digit is zero.
     *
     * @return <code>true</code> if this digit is zero, else <code>false</code>
     */
    boolean isZero();

    /**
     * Checks if this digit matches the specified ordinal value.
     *
     * @param ordinal
     *        an ordinal value
     *
     * @return <code>true</code> if this digit matches the ordinal value, else <code>false</code>
     */
    boolean isOrdinal(int ordinal);

    /**
     * Checks if this digit is odd.
     *
     * @return <code>true</code> if this digit is odd, else <code>even</code>
     */
    boolean isOdd();

    /**
     * Checks if this digit is even.
     *
     * @return <code>true</code> if this digit is even, else <code>even</code>
     */
    boolean isEven();

}

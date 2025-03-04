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

package jmul.math.operations;


import jmul.math.numbers.Number;


/**
 * This interface describes various number comparisons.
 *
 * @author Kristian Kutin
 */
public interface NumberComparisons {

    /**
     * Checks if this object (i.e. operand) is greater than the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this object (i.e. operand) is greater than the specified number,
     *         else <code>false</code>
     */
    boolean isGreater(Number number);

    /**
     * Checks if this object (i.e. operand) is greater than or equal to the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this object (i.e. operand) is greater than or equal to the specified number,
     *         else <code>false</code>
     */
    boolean isGreaterOrEqual(Number number);

    /**
     * Checks if this object (i.e. operand) is lesser than the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this object (i.e. operand) is lesser than the specified number,
     *         else <code>false</code>
     */
    boolean isLesser(Number number);

    /**
     * Checks if this object (i.e. operand) is lesser than or equal to the specified number.
     *
     * @param number
     *        a number
     *
     * @return <code>true</code> if this object (i.e. operand) is lesser than or equal to the specified number, else <code>false</code>
     */
    boolean isLesserOrEqual(Number number);

}

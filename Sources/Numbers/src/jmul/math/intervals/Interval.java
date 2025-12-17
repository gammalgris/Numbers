/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
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

package jmul.math.intervals;


import jmul.math.fractions.Fraction;
import jmul.math.numbers.Number;


/**
 * This interface defines an interval.
 *
 * @author Kristian Kutin
 */
public interface Interval {

    /**
     * The number base.
     * 
     * @return a number base
     */
    int base();

    /**
     * Checks if the specified number is within the bounds of this interval.
     *
     * @param n
     *        a number
     *
     * @return <code>true</code> if the specified number is within the bounds of this interval, else <code>false</code>
     */
    boolean isWithinInterval(Number n);

    /**
     * Checks if the specified fraction is within the bounds of this interval.
     *
     * @param f
     *        a fraction
     *
     * @return <code>true</code> if the specified fraction is within the bounds of this interval, else <code>false</code>
     */
    boolean isWithinInterval(Fraction f);

    /**
     * Checks if the specified number is outside the bounds of this interval.
     *
     * @param n
     *        a number
     *
     * @return <code>true</code> if the specified number is outside the bounds of this interval, else <code>false</code>
     */
    boolean isOutsideInterval(Number n);

    /**
     * Checks if the specified fraction is outside the bounds of this interval.
     *
     * @param f
     *        a fraction
     *
     * @return <code>true</code> if the specified fraction is outside the bounds of this interval, else <code>false</code>
     */
    boolean isOutsideInterval(Fraction f);

    /**
     * Returns the length of this interval. The fraction component of the boundaries is not considered when  calculating
     * the length.
     *
     * @return a length
     */
    Number length();

    /**
     * Returns the midpoint for this interval.
     *
     * @return a midpoint
     */
    Number midpoint();

    /**
     * Returns the upper bounday of this interval.
     *
     * @return a number
     */
    Number upperBoundary();

    /**
     * Returns the lower boundary of this interval.
     *
     * @return a number
     */
    Number lowerBoundary();

}

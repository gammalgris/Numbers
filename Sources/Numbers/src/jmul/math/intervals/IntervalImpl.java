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
import jmul.math.operations.implementations.ParameterCheckHelper;


/**
 * Implements an inteval which can be open or closed.
 *
 * @author Kristian Kutin
 */
public class IntervalImpl implements Interval {

    /**
     * The lower boundary value.
     */
    private final Number lowerBoundaryValue;

    /**
     * The lower boundary type.
     */
    private final BoundaryType lowerBoundaryType;

    /**
     * The upper boundary value.
     */
    private final Number upperBoundaryValue;

    /**
     * The upper boundary type.
     */
    private final BoundaryType upperBoundaryType;

    /**
     * Creates a new interval according to the specified parameters.
     *
     * @param lowerBoundaryValue
     *        a value for the lower bound
     * @param lowerBoundaryType
     *        the type of the lower bound
     * @param upperBoundaryValue
     *        a value for the upper bound
     * @param upperBoundaryType
     *        the type of the upper bound
     */
    IntervalImpl(Number lowerBoundaryValue, BoundaryType lowerBoundaryType, Number upperBoundaryValue,
                 BoundaryType upperBoundaryType) {

        super();

        ParameterCheckHelper.checkParameters(lowerBoundaryValue, upperBoundaryValue);
        checkBoundaries(lowerBoundaryType, upperBoundaryType);

        this.lowerBoundaryValue = lowerBoundaryValue;
        this.lowerBoundaryType = lowerBoundaryType;

        this.upperBoundaryValue = upperBoundaryValue;
        this.upperBoundaryType = upperBoundaryType;
    }

    /**
     * Checks the specified boundary types.
     *
     * @param lowerBoundaryType
     *        a lower boundary type
     * @param upperBoundaryType
     *        an upper boudnary type
     */
    private static void checkBoundaries(BoundaryType lowerBoundaryType, BoundaryType upperBoundaryType) {

        if (lowerBoundaryType == null) {

            throw new IllegalArgumentException("No lower boundary type (null) was specified!");
        }

        if (upperBoundaryType == null) {

            throw new IllegalArgumentException("No upper boundary type (null) was specified!");
        }
    }

    /**
     * Checks if the specified number is within the bounds of this interval.
     *
     * @param n
     *        a number
     *
     * @return <code>true</code> if the specified number is within the bounds of this interval, else <code>false</code>
     */
    @Override
    public boolean isWithinInterval(Number n) {

        if (lowerBoundaryType.includesBoundary() && upperBoundaryType.includesBoundary()) {

            return lowerBoundaryValue.isLesserOrEqual(n) && upperBoundaryValue.isGreaterOrEqual(n);

        } else if (lowerBoundaryType.includesBoundary() && !upperBoundaryType.includesBoundary()) {

            return lowerBoundaryValue.isLesserOrEqual(n) && upperBoundaryValue.isGreater(n);

        } else if (!lowerBoundaryType.includesBoundary() && upperBoundaryType.includesBoundary()) {

            return lowerBoundaryValue.isLesser(n) && upperBoundaryValue.isGreaterOrEqual(n);

        } else {

            return lowerBoundaryValue.isLesser(n) && upperBoundaryValue.isGreater(n);
        }
    }

    /**
     * Checks if the specified number is outside the bounds of this interval.
     *
     * @param n
     *        a number
     *
     * @return <code>true</code> if the specified number is outside the bounds of this interval, else <code>false</code>
     */
    @Override
    public boolean isOutsideInterval(Number n) {

        return !isWithinInterval(n);
    }

    /**
     * Checks if the specified fraction is within the bounds of this interval.
     *
     * @param f
     *        a fraction
     *
     * @return <code>true</code> if the specified fraction is within the bounds of this interval, else <code>false</code>
     */
    @Override
    public boolean isWithinInterval(Fraction f) {

        if (lowerBoundaryType.includesBoundary() && upperBoundaryType.includesBoundary()) {

            return lowerBoundaryValue.isLesserOrEqual(f) && upperBoundaryValue.isGreaterOrEqual(f);

        } else if (lowerBoundaryType.includesBoundary() && !upperBoundaryType.includesBoundary()) {

            return lowerBoundaryValue.isLesserOrEqual(f) && upperBoundaryValue.isGreater(f);

        } else if (!lowerBoundaryType.includesBoundary() && upperBoundaryType.includesBoundary()) {

            return lowerBoundaryValue.isLesser(f) && upperBoundaryValue.isGreaterOrEqual(f);

        } else {

            return lowerBoundaryValue.isLesser(f) && upperBoundaryValue.isGreater(f);
        }
    }

    /**
     * Checks if the specified fraction is outside the bounds of this interval.
     *
     * @param f
     *        a fraction
     *
     * @return <code>true</code> if the specified fraction is outside the bounds of this interval, else <code>false</code>
     */
    @Override
    public boolean isOutsideInterval(Fraction f) {

        return !isWithinInterval(f);
    }

    /**
     * Returns a string representation for this interval.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s%s,%s%s", lowerBoundaryType.symbol(BoundaryPositions.LOWER_BOUNDARY),
                             lowerBoundaryValue, upperBoundaryValue,
                             upperBoundaryType.symbol(BoundaryPositions.UPPER_BOUNDARY));
    }

}

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


import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.implementations.ParameterCheckHelper;
import jmul.math.signs.Signs;


/**
 * A helper class for intervals.
 *
 * @author Kristian Kutin
 */
public final class IntervalHelper {

    /**
     * The default constructor.
     */
    private IntervalHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new interval according to the specified parameters.
     *
     * @param lowerBoundary
     *        a value for the lower bound
     * @param lowerBoundaryType
     *        the type of the lower bound
     * @param upperBoundary
     *        a value for the upper bound
     * @param upperBoundaryType
     *        the type of the upper bound
     *
     * @return an interval
     */
    public static Interval createInterval(Number lowerBoundary, BoundaryType lowerBoundaryType, Number upperBoundary,
                                          BoundaryType upperBoundaryType) {

        return new IntervalImpl(lowerBoundary, lowerBoundaryType, upperBoundary, upperBoundaryType);
    }

    /**
     * Creates a new interval according to the specified parameters.
     *
     * @param n
     *        a number
     * @param decimalPlaces
     *        the significant decimal places
     *
     * @return an Interval
     */
    public static Interval createInterval(Number n, Number decimalPlaces) {

        ParameterCheckHelper.checkParameters(n, decimalPlaces);
        ParameterCheckHelper.checkPositiveIntegerGreaterZero(decimalPlaces);

        int base = n.base();

        final Number ONE = createNumber(base, Signs.POSITIVE, 1);

        Number epsilon = ONE;
        Number counter = decimalPlaces;

        while (!counter.isZero()) {

            epsilon = epsilon.shiftLeft();
            counter = counter.dec();
        }

        Number lowerBoundary = n.subtract(epsilon);
        Number upperBoundary = n.add(epsilon);

        return createInterval(lowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, upperBoundary,
                              BoundaryTypes.CLOSED_BOUNDARY);
    }

    /**
     * Creates a new translator according to the specified parameters.
     *
     * @param originLowerBoundary
     *        an interval boundary
     * @param originUpperBoundary
     *        an interval boundary
     * @param destinationLowerBoundary
     *        an interval boundary
     * @param destinationUpperBoundary
     *        an interval boundary
     *
     * @return a translator
     */
    public static Translator createTranslator(Number originLowerBoundary, Number originUpperBoundary,
                                              Number destinationLowerBoundary, Number destinationUpperBoundary) {

        Interval originInterval =
            createInterval(originLowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, originUpperBoundary,
                           BoundaryTypes.CLOSED_BOUNDARY);
        Interval destinationInterval =
            createInterval(destinationLowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, destinationUpperBoundary,
                           BoundaryTypes.CLOSED_BOUNDARY);

        return createTranslator(originInterval, destinationInterval);
    }

    /**
     * Creates a new translator according to the specified parameters.
     *
     * @param originInterval
     *        an interval
     * @param destinationInterval
     *        an interval
     *
     * @return a translator
     */
    public static Translator createTranslator(Interval originInterval, Interval destinationInterval) {

        return new TranslatorImpl(originInterval, destinationInterval);
    }

}

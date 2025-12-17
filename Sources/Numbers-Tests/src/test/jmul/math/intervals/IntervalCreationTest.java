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

package test.jmul.math.intervals;


import jmul.math.intervals.BoundaryTypes;
import jmul.math.intervals.Interval;
import static jmul.math.intervals.IntervalHelper.createInterval;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suit tests creating intervals.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class IntervalCreationTest {

    /**
     * Tests creating an interval with valid parameters.
     */
    @Test
    public void createIntervalWithValidParameters() {

        int base = 10;
        Number lowerBoundary = createNumber(base, "10");
        Number upperBoundary = createNumber(base, "20");

        Number expectedLength = createNumber(base, "10");
        Number expectedMidpoint = createNumber(base, "15");

        Interval interval =
            createInterval(lowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, upperBoundary, BoundaryTypes.CLOSED_BOUNDARY);

        assertEquals("#base", base, interval.base());

        assertEquals("#lower boundary", lowerBoundary, interval.lowerBoundary());
        assertEquals("#upper boundary", upperBoundary, interval.upperBoundary());

        assertEquals("#length", expectedLength, interval.length());
        assertEquals("#midpint", expectedMidpoint, interval.midpoint());
    }

    /**
     * Tests creating an interval with zero length.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIntervalWithZeroLength() {

        int base = 10;
        Number lowerBoundary = createNumber(base, "10");
        Number upperBoundary = createNumber(base, "10");

        createInterval(lowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, upperBoundary, BoundaryTypes.CLOSED_BOUNDARY);
    }

    /**
     * Tests creating an interval with invalid boundaries.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIntervalWithInvalidBoundaries() {

        int base = 10;
        Number lowerBoundary = createNumber(base, "20");
        Number upperBoundary = createNumber(base, "10");

        createInterval(lowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, upperBoundary, BoundaryTypes.CLOSED_BOUNDARY);
    }

    /**
     * Tests creating an interval with valid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIntervalWithInfinityAsLowerBoundary() {

        int base = 10;
        Number lowerBoundary = createInfinity(base);
        Number upperBoundary = createNumber(base, "20");

        createInterval(lowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, upperBoundary, BoundaryTypes.CLOSED_BOUNDARY);
    }

    /**
     * Tests creating an interval with valid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIntervalWithInfinityAsUpperBoundary() {

        int base = 10;
        Number lowerBoundary = createNumber(base, "20");
        Number upperBoundary = createInfinity(base);

        createInterval(lowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, upperBoundary, BoundaryTypes.CLOSED_BOUNDARY);
    }

    /**
     * Tests creating a null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIntervalWithNullParameter() {

        int base = 10;
        Number lowerBoundary = null;
        Number upperBoundary = createNumber(base, "20");

        createInterval(lowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, upperBoundary, BoundaryTypes.CLOSED_BOUNDARY);
    }

    /**
     * Tests creating a null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIntervalWithNullParameterVariant2() {

        int base = 10;
        Number lowerBoundary = createNumber(base, "20");
        Number upperBoundary = null;

        createInterval(lowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, upperBoundary, BoundaryTypes.CLOSED_BOUNDARY);
    }

    /**
     * Tests creating a null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIntervalWithNullParameterVariant3() {

        int base = 10;
        Number lowerBoundary = createNumber(base, "20");
        Number upperBoundary = createNumber(base, "21");

        createInterval(lowerBoundary, null, upperBoundary, BoundaryTypes.CLOSED_BOUNDARY);
    }

    /**
     * Tests creating a null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createIntervalWithNullParameterVariant4() {

        int base = 10;
        Number lowerBoundary = createNumber(base, "20");
        Number upperBoundary = createNumber(base, "21");

        createInterval(lowerBoundary, BoundaryTypes.CLOSED_BOUNDARY, upperBoundary, null);
    }

}

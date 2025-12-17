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


import java.util.ArrayList;
import java.util.Collection;

import static jmul.math.intervals.BoundaryTypes.CLOSED_BOUNDARY;
import static jmul.math.intervals.BoundaryTypes.OPEN_BOUNDARY;
import jmul.math.intervals.Interval;
import static jmul.math.intervals.IntervalHelper.createInterval;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;

import jmul.test.classification.UnitTest;

import jmul.math.numbers.Number;

import static jmul.math.numbers.NumberHelper.createNumber;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests if numbers are within a small interval.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class EpsilonTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * An interval.
     */
    private final Interval interval;

    /**
     * The expected result.
     */
    private final boolean expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param interval
     *        an interval
     * @param expectedResult
     *        the expected result
     */
    public EpsilonTest(Number number, Interval interval, boolean expectedResult) {

        super();

        this.number = number;
        this.interval = interval;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s -> %s -> %s", number.base(), number, interval, expectedResult);
    }

    /**
     * Tests if a number is within an interval.
     */
    @Test
    public void testIsWithinInterval() {

        boolean actualResult = interval.isWithinInterval(number);
        boolean actualResult2 = interval.isOutsideInterval(number);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), !expectedResult, actualResult2);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createNumber(base, "1.00001"),
                                          createInterval(createNumber(base, "1.00001"), createNumber(base, "101")),
                                          true });
        }

        return parameters;
    }

}

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

package test.jmul.math.numbers;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.Math;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suit tests rounding up numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RoundUpTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param expectedResult
     *        the expected result
     */
    public RoundUpTest(Number number, Number expectedResult) {

        super();

        this.number = number;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("(%d) %s -> (%d) %s", number.base(), number, expectedResult.base(), expectedResult);
    }

    /**
     * Rounds up a number and checks the result.
     */
    @Test
    public void testRoundingUp() {

        Number actualResult = number.roundUp();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Rounds up a number and checks the result.
     */
    @Test
    public void testRoundingUpVariant2() {

        Number actualResult = Math.roundUp(number);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
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

            parameters.add(new Object[] { createInfinity(base), createInfinity(base) });
            parameters.add(new Object[] { createNegativeInfinity(base), createNegativeInfinity(base) });

            parameters.add(new Object[] { createNumber(base, "0"), createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "-0"), createNumber(base, "0") });

            parameters.add(new Object[] { createNumber(base, "0.1"), createNumber(base, "1") });
            parameters.add(new Object[] { createNumber(base, "-0.1"), createNumber(base, "0") });
        }

        parameters.add(new Object[] { createNumber(2, "1"), createNumber(2, "1") });
        parameters.add(new Object[] { createNumber(2, "-1"), createNumber(2, "-1") });

        parameters.add(new Object[] { createNumber(2, "1.1111"), createNumber(2, "10") });
        parameters.add(new Object[] { createNumber(2, "-1.1111"), createNumber(2, "-1") });

        parameters.add(new Object[] { createNumber(2, "1.0001"), createNumber(2, "10") });
        parameters.add(new Object[] { createNumber(2, "-1.01"), createNumber(2, "-1") });

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createNumber(base, "1"), createNumber(base, "1") });
            parameters.add(new Object[] { createNumber(base, "-1"), createNumber(base, "-1") });

            parameters.add(new Object[] { createNumber(base, "1.1111"), createNumber(base, "2") });
            parameters.add(new Object[] { createNumber(base, "-1.1111"), createNumber(base, "-1") });

            parameters.add(new Object[] { createNumber(base, "1.0001"), createNumber(base, "2") });
            parameters.add(new Object[] { createNumber(base, "-1.0001"), createNumber(base, "-1") });
        }

        parameters.add(new Object[] { createNumber(10, "9.1"), createNumber(10, "10") });
        parameters.add(new Object[] { createNumber(10, "-9.1"), createNumber(10, "-9") });

        return parameters;
    }

}

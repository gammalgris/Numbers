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
 * This test suite tests rebasing a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RebaseNumberTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * A destination number base.
     */
    private final int destinationBase;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param destinationBase
     *        a destination number base
     * @param expectedResult
     *        the expected result
     */
    public RebaseNumberTest(Number number, int destinationBase, Number expectedResult) {

        super();

        this.number = number;
        this.destinationBase = destinationBase;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a string representation of this test case.
     *
     * @return string representation
     */
    @Override
    public String toString() {

        return String.format("(%d) %s rebased to (%d)%s", number.base(), number, destinationBase, expectedResult);
    }

    /**
     * Tests translating the number base.
     */
    @Test
    public void testRebase() {

        Number actualResult = number.rebase(destinationBase);

        assertEquals(toString(), expectedResult.base(), actualResult.base());
        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests translating the number base.
     */
    @Test
    public void testRebaseVariant2() {

        Number actualResult = Math.rebase(number, destinationBase);

        assertEquals(toString(), expectedResult.base(), actualResult.base());
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

            for (int destinationBase = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

                parameters.add(new Object[] { createInfinity(base), destinationBase, createInfinity(destinationBase) });
                parameters.add(new Object[] { createNegativeInfinity(base), destinationBase,
                                              createNegativeInfinity(destinationBase) });

                parameters.add(new Object[] { createNumber(base, "0"), destinationBase,
                                              createNumber(destinationBase, "0") });
                parameters.add(new Object[] { createNumber(base, "-0"), destinationBase,
                                              createNumber(destinationBase, "0") });

                parameters.add(new Object[] { createNumber(base, "1"), destinationBase,
                                              createNumber(destinationBase, "1") });
                parameters.add(new Object[] { createNumber(base, "-1"), destinationBase,
                                              createNumber(destinationBase, "-1") });
            }
        }

        parameters.add(new Object[] { createNumber(10, "10"), 2, createNumber(2, "1010") });
        parameters.add(new Object[] { createNumber(10, "-10"), 2, createNumber(2, "-1010") });

        parameters.add(new Object[] { createNumber(10, "10.5"), 2, createNumber(2, "1010.1") });
        parameters.add(new Object[] { createNumber(10, "-10.5"), 2, createNumber(2, "-1010.1") });

        parameters.add(new Object[] { createNumber(10, "10.1"), 2, createNumber(2, "1010.000110011001") });
        parameters.add(new Object[] { createNumber(10, "-10.1"), 2, createNumber(2, "-1010.000110011001") });

        parameters.add(new Object[] { createNumber(2, "1010"), 10, createNumber(10, "10") });
        parameters.add(new Object[] { createNumber(2, "-1010"), 10, createNumber(10, "-10") });

        parameters.add(new Object[] { createNumber(2, "1010.1"), 10, createNumber(10, "10.5") });
        parameters.add(new Object[] { createNumber(2, "-1010.1"), 10, createNumber(10, "-10.5") });

        parameters.add(new Object[] { createNumber(2, "1010.000110011001"), 10, createNumber(10, "10.0998535156") });
        parameters.add(new Object[] { createNumber(2, "-1010.000110011001"), 10, createNumber(10, "-10.0998535156") });

        return parameters;
    }

}

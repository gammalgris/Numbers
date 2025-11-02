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
import static jmul.math.collections.CollectionsHelper.createNumberSet;
import jmul.math.collections.Set;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests determining divisors of a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DetermineDivisorsTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * The expected result.
     */
    private final Set<Number> expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param set
     *        all expected divisors
     */
    public DetermineDivisorsTest(Number number, Set<Number> set) {

        super();

        this.number = number;
        this.expectedResult = set;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s : %s", number.base(), number, expectedResult);
    }

    /**
     * Checks the divisors and the expected divisors.
     */
    @Test
    public void checkDivisors() {

        Set<Number> actualResult = number.divisors();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Checks the divisors and the expected divisors.
     */
    @Test
    public void checkDivisorsVariant2() {

        Set<Number> actualResult = Math.divisors(number);

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

        // 8 : { 1, 2, 4, 8 }
        parameters.add(new Object[] { createNumber(2, "1000"), createNumberSet(2, "1", "10", "100", "1000") });
        // 9 : { 1, 3, 9 }
        parameters.add(new Object[] { createNumber(2, "1001"), createNumberSet(2, "1", "11", "1001") });
        // 10 : { 1, 2, 5, 10 }
        parameters.add(new Object[] { createNumber(2, "1010"), createNumberSet(2, "1", "10", "101", "1010") });
        // 11 : { 1, 11 }
        parameters.add(new Object[] { createNumber(2, "1011"), createNumberSet(2, "1", "1011") });
        // 12 : { 1, 2, 3, 4, 6, 12 }
        parameters.add(new Object[] { createNumber(2, "1100"),
                                      createNumberSet(2, "1", "10", "11", "100", "110", "1100") });
        // 13 : { 1, 13 }
        parameters.add(new Object[] { createNumber(2, "1101"), createNumberSet(2, "1", "1101") });
        // 14 : { 1, 2, 7, 14 }
        parameters.add(new Object[] { createNumber(2, "1110"), createNumberSet(2, "1", "10", "111", "1110") });


        // 8 : { 1, 2, 4, 8 }
        parameters.add(new Object[] { createNumber(3, "22"), createNumberSet(3, "1", "2", "11", "22") });
        // 9 : { 1, 3, 9 }
        parameters.add(new Object[] { createNumber(3, "100"), createNumberSet(3, "1", "10", "100") });
        // 10 : { 1, 2, 5, 10 }
        parameters.add(new Object[] { createNumber(3, "101"), createNumberSet(3, "1", "2", "12", "101") });
        // 11 : { 1, 11 }
        parameters.add(new Object[] { createNumber(3, "102"), createNumberSet(3, "1", "102") });
        // 12 : { 1, 2, 3, 4, 6, 12 }
        parameters.add(new Object[] { createNumber(3, "110"), createNumberSet(3, "1", "2", "10", "11", "20", "110") });
        // 13 : { 1, 13 }
        parameters.add(new Object[] { createNumber(3, "111"), createNumberSet(3, "1", "111") });
        // 14 : { 1, 2, 7, 14 }
        parameters.add(new Object[] { createNumber(3, "112"), createNumberSet(3, "1", "2", "21", "112") });


        // 8 : { 1, 2, 4, 8 }
        parameters.add(new Object[] { createNumber(4, "20"), createNumberSet(4, "1", "2", "10", "20") });
        // 9 : { 1, 3, 9 }
        parameters.add(new Object[] { createNumber(4, "21"), createNumberSet(4, "1", "3", "21") });
        // 10 : { 1, 2, 5, 10 }
        parameters.add(new Object[] { createNumber(4, "22"), createNumberSet(4, "1", "2", "11", "22") });
        // 11 : { 1, 11 }
        parameters.add(new Object[] { createNumber(4, "23"), createNumberSet(4, "1", "23") });
        // 12 : { 1, 2, 3, 4, 6, 12 }
        parameters.add(new Object[] { createNumber(4, "30"), createNumberSet(4, "1", "2", "3", "10", "12", "30") });
        // 13 : { 1, 13 }
        parameters.add(new Object[] { createNumber(4, "31"), createNumberSet(4, "1", "31") });
        // 14 : { 1, 2, 7, 14 }
        parameters.add(new Object[] { createNumber(4, "32"), createNumberSet(4, "1", "2", "13", "32") });


        // 1 : { 1 }
        parameters.add(new Object[] { createNumber(10, "1"), createNumberSet(10, "1") });
        // 2 : { 1, 2 }
        parameters.add(new Object[] { createNumber(10, "2"), createNumberSet(10, "1", "2") });
        // 3 : { 1, 3 }
        parameters.add(new Object[] { createNumber(10, "3"), createNumberSet(10, "1", "3") });
        // 4 : { 1, 2, 4 }
        parameters.add(new Object[] { createNumber(10, "4"), createNumberSet(10, "1", "2", "4") });
        // 5 : { 1, 5 }
        parameters.add(new Object[] { createNumber(10, "5"), createNumberSet(10, "1", "5") });
        // 6 : { 1, 2, 3, 6 }
        parameters.add(new Object[] { createNumber(10, "6"), createNumberSet(10, "1", "2", "3", "6") });
        // 7 : { 1, 7 }
        parameters.add(new Object[] { createNumber(10, "7"), createNumberSet(10, "1", "7") });
        // 8 : { 1, 2, 4, 8 }
        parameters.add(new Object[] { createNumber(10, "8"), createNumberSet(10, "1", "2", "4", "8") });
        // 9 : { 1, 3, 9 }
        parameters.add(new Object[] { createNumber(10, "9"), createNumberSet(10, "1", "3", "9") });
        // 10 : { 1, 2, 5, 10 }
        parameters.add(new Object[] { createNumber(10, "10"), createNumberSet(10, "1", "2", "5", "10") });
        // 11 : { 1, 11 }
        parameters.add(new Object[] { createNumber(10, "11"), createNumberSet(10, "1", "11") });
        // 12 : { 1, 2, 3, 4, 6, 12 }
        parameters.add(new Object[] { createNumber(10, "12"), createNumberSet(10, "1", "2", "3", "4", "6", "12") });
        // 13 : { 1, 13 }
        parameters.add(new Object[] { createNumber(10, "13"), createNumberSet(10, "1", "13") });
        // 14 : { 1, 2, 7, 14 }
        parameters.add(new Object[] { createNumber(10, "14"), createNumberSet(10, "1", "2", "7", "14") });

        return parameters;
    }

}

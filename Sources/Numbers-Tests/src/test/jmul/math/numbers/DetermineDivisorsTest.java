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
import java.util.Arrays;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import jmul.math.Math;
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
    private final SortedSet<Number> expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param divisors
     *        all expected divisors
     */
    public DetermineDivisorsTest(Number number, Number... divisors) {

        super();

        this.number = number;
        this.expectedResult = new TreeSet<>(Arrays.asList(divisors));
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

        SortedSet<Number> actualResult = number.divisorSet();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Checks the divisors and the expected divisors.
     */
    @Test
    public void checkDivisorsVariant2() {

        SortedSet<Number> actualResult = Math.divisorSet(number);

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

        // 8 : { 2, 4 }
        parameters.add(new Object[] { createNumber(2, "1000"),
                                      new Number[] { createNumber(2, "10"), createNumber(2, "100") } });
        // 9 : { 3 }
        parameters.add(new Object[] { createNumber(2, "1001"), new Number[] { createNumber(2, "11") } });
        // 10 : { 2, 5 }
        parameters.add(new Object[] { createNumber(2, "1010"),
                                      new Number[] { createNumber(2, "10"), createNumber(2, "101") } });
        // 11 : { }
        parameters.add(new Object[] { createNumber(2, "1011"), new Number[] { } });
        // 12 : { 2, 3, 4, 6 }
        parameters.add(new Object[] { createNumber(2, "1100"),
                                      new Number[] { createNumber(2, "10"), createNumber(2, "11"),
                                                     createNumber(2, "100"), createNumber(2, "110") } });
        // 13 : { }
        parameters.add(new Object[] { createNumber(2, "1101"), new Number[] { } });
        // 14 : { 2, 7 }
        parameters.add(new Object[] { createNumber(2, "1110"),
                                      new Number[] { createNumber(2, "10"), createNumber(2, "111") } });

        // 8 : { 2, 4 }
        parameters.add(new Object[] { createNumber(3, "22"),
                                      new Number[] { createNumber(3, "2"), createNumber(3, "11") } });
        // 9 : { 3 }
        parameters.add(new Object[] { createNumber(3, "100"), new Number[] { createNumber(3, "10") } });
        // 10 : { 2, 5 }
        parameters.add(new Object[] { createNumber(3, "101"),
                                      new Number[] { createNumber(3, "2"), createNumber(3, "12") } });
        // 11 : { }
        parameters.add(new Object[] { createNumber(3, "102"), new Number[] { } });
        // 12 : { 2, 3, 4, 6 }
        parameters.add(new Object[] { createNumber(3, "110"),
                                      new Number[] { createNumber(3, "2"), createNumber(3, "10"), createNumber(3, "11"),
                                                     createNumber(3, "20") } });
        // 13 : { }
        parameters.add(new Object[] { createNumber(3, "111"), new Number[] { } });
        // 14 : { 2, 7 }
        parameters.add(new Object[] { createNumber(3, "112"),
                                      new Number[] { createNumber(3, "2"), createNumber(3, "21") } });

        // 8 : { 2, 4 }
        parameters.add(new Object[] { createNumber(4, "20"),
                                      new Number[] { createNumber(4, "2"), createNumber(4, "10") } });
        // 9 : { 3 }
        parameters.add(new Object[] { createNumber(4, "21"), new Number[] { createNumber(4, "3") } });
        // 10 : { 2, 5 }
        parameters.add(new Object[] { createNumber(4, "22"),
                                      new Number[] { createNumber(4, "2"), createNumber(4, "11") } });
        // 11 : { }
        parameters.add(new Object[] { createNumber(4, "23"), new Number[] { } });
        // 12 : { 2, 3, 4, 6 }
        parameters.add(new Object[] { createNumber(4, "30"),
                                      new Number[] { createNumber(4, "2"), createNumber(4, "3"), createNumber(4, "10"),
                                                     createNumber(4, "12") } });
        // 13 : { }
        parameters.add(new Object[] { createNumber(4, "31"), new Number[] { } });
        // 14 : { 2, 7 }
        parameters.add(new Object[] { createNumber(4, "32"),
                                      new Number[] { createNumber(4, "2"), createNumber(4, "13") } });

        // 8 : { 2, 4 }
        parameters.add(new Object[] { createNumber(10, "8"),
                                      new Number[] { createNumber(10, "2"), createNumber(10, "4") } });
        // 9 : { 3 }
        parameters.add(new Object[] { createNumber(10, "9"), new Number[] { createNumber(10, "3") } });
        // 10 : { 2, 5 }
        parameters.add(new Object[] { createNumber(10, "10"),
                                      new Number[] { createNumber(10, "2"), createNumber(10, "5") } });
        // 11 : { }
        parameters.add(new Object[] { createNumber(10, "11"), new Number[] { } });
        // 12 : { 2, 3, 4, 6 }
        parameters.add(new Object[] { createNumber(10, "12"),
                                      new Number[] { createNumber(10, "2"), createNumber(10, "3"),
                                                     createNumber(10, "4"), createNumber(10, "6") } });
        // 13 : { }
        parameters.add(new Object[] { createNumber(10, "13"), new Number[] { } });
        // 14 : { 2, 7 }
        parameters.add(new Object[] { createNumber(10, "14"),
                                      new Number[] { createNumber(10, "2"), createNumber(10, "7") } });

        return parameters;
    }

}

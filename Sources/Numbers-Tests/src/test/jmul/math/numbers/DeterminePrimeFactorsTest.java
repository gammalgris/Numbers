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
import static jmul.math.collections.CollectionsHelper.createNumberSequence;
import jmul.math.collections.Sequence;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests determining prime factors of a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DeterminePrimeFactorsTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * The expected result.
     */
    private final Sequence<Number> expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param sequence
     *        all expected divisors
     */
    public DeterminePrimeFactorsTest(Number number, Sequence<Number> sequence) {

        super();

        this.number = number;
        this.expectedResult = sequence;
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

        Sequence<Number> actualResult = number.primeFactors();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Checks the divisors and the expected divisors.
     */
    @Test
    public void checkDivisorsVariant2() {

        Sequence<Number> actualResult = Math.primeFactors(number);

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

        // 8 : { 2, 2, 2 }
        parameters.add(new Object[] { createNumber(2, "1000"), createNumberSequence(2, "10", "10", "10") });
        // 9 : { 3, 3 }
        parameters.add(new Object[] { createNumber(2, "1001"), createNumberSequence(2, "11", "11") });
        // 10 : { 2, 5 }
        parameters.add(new Object[] { createNumber(2, "1010"), createNumberSequence(2, "10", "101") });
        // 11 : { 11 }
        parameters.add(new Object[] { createNumber(2, "1011"), createNumberSequence(2, "1011") });
        // 12 : { 2, 2, 3 }
        parameters.add(new Object[] { createNumber(2, "1100"), createNumberSequence(2, "10", "10", "11") });
        // 13 : { 13 }
        parameters.add(new Object[] { createNumber(2, "1101"), createNumberSequence(2, "1101") });
        // 14 : { 2, 7 }
        parameters.add(new Object[] { createNumber(2, "1110"), createNumberSequence(2, "10", "111") });

        // 8 : { 2, 2, 2 }
        parameters.add(new Object[] { createNumber(3, "22"), createNumberSequence(3, "2", "2", "2") });
        // 9 : { 3, 3 }
        parameters.add(new Object[] { createNumber(3, "100"), createNumberSequence(3, "10", "10") });
        // 10 : { 2, 5 }
        parameters.add(new Object[] { createNumber(3, "101"), createNumberSequence(3, "2", "12") });
        // 11 : { 11 }
        parameters.add(new Object[] { createNumber(3, "102"), createNumberSequence(3, "102") });
        // 12 : { 2, 2, 3 }
        parameters.add(new Object[] { createNumber(3, "110"), createNumberSequence(3, "2", "2", "10") });
        // 13 : { 13 }
        parameters.add(new Object[] { createNumber(3, "111"), createNumberSequence(3, "111") });
        // 14 : { 2, 7 }
        parameters.add(new Object[] { createNumber(3, "112"), createNumberSequence(3, "2", "21") });

        // 8 : { 2, 2, 2 }
        parameters.add(new Object[] { createNumber(4, "20"), createNumberSequence(4, "2", "2", "2") });
        // 9 : { 3, 3 }
        parameters.add(new Object[] { createNumber(4, "21"), createNumberSequence(4, "3", "3") });
        // 10 : { 2, 5 }
        parameters.add(new Object[] { createNumber(4, "22"), createNumberSequence(4, "2", "11") });
        // 11 : { 11 }
        parameters.add(new Object[] { createNumber(4, "23"), createNumberSequence(4, "23") });
        // 12 : { 2, 2, 3 }
        parameters.add(new Object[] { createNumber(4, "30"), createNumberSequence(4, "2", "2", "3") });
        // 13 : { 13 }
        parameters.add(new Object[] { createNumber(4, "31"), createNumberSequence(4, "31") });
        // 14 : { 2, 7 }
        parameters.add(new Object[] { createNumber(4, "32"), createNumberSequence(4, "2", "13") });

        // 1 : { }
        parameters.add(new Object[] { createNumber(10, "1"), createNumberSequence(10) });
        // 2 : { 2 }
        parameters.add(new Object[] { createNumber(10, "2"), createNumberSequence(10, "2") });
        // 3 : { 3 }
        parameters.add(new Object[] { createNumber(10, "3"), createNumberSequence(10, "3") });
        // 4 : { 2, 2 }
        parameters.add(new Object[] { createNumber(10, "4"), createNumberSequence(10, "2", "2") });
        // 5 : { 5 }
        parameters.add(new Object[] { createNumber(10, "5"), createNumberSequence(10, "5") });
        // 6 : { 2, 3 }
        parameters.add(new Object[] { createNumber(10, "6"), createNumberSequence(10, "2", "3") });
        // 7 : { 7 }
        parameters.add(new Object[] { createNumber(10, "7"), createNumberSequence(10, "7") });
        // 8 : { 2, 2, 2 }
        parameters.add(new Object[] { createNumber(10, "8"), createNumberSequence(10, "2", "2", "2") });
        // 9 : { 3, 3 }
        parameters.add(new Object[] { createNumber(10, "9"), createNumberSequence(10, "3", "3") });
        // 10 : { 2, 5 }
        parameters.add(new Object[] { createNumber(10, "10"), createNumberSequence(10, "2", "5") });
        // 11 : { 11 }
        parameters.add(new Object[] { createNumber(10, "11"), createNumberSequence(10, "11") });
        // 12 : { 2, 2, 3 }
        parameters.add(new Object[] { createNumber(10, "12"), createNumberSequence(10, "2", "2", "3") });
        // 13 : { 13 }
        parameters.add(new Object[] { createNumber(10, "13"), createNumberSequence(10, "13") });
        // 14 : { 2, 7 }
        parameters.add(new Object[] { createNumber(10, "14"), createNumberSequence(10, "2", "7") });

        // 100 : { 2, 2, 5, 5 }
        parameters.add(new Object[] { createNumber(10, "100"), createNumberSequence(10, "2", "2", "5", "5") });

        // 1000 : { 2, 2, 2, 5, 5, 5 }
        parameters.add(new Object[] { createNumber(10, "1000"),
                                      createNumberSequence(10, "2", "2", "2", "5", "5", "5") });

        // 2137 : { 2137 }
        parameters.add(new Object[] { createNumber(10, "2137"), createNumberSequence(10, "2137") });
        // 2138 : { 2, 1069 }
        parameters.add(new Object[] { createNumber(10, "2138"), createNumberSequence(10, "2", "1069") });
        // 2139 : { 3, 23, 31 }
        parameters.add(new Object[] { createNumber(10, "2139"), createNumberSequence(10, "3", "23", "31") });

        return parameters;
    }

}

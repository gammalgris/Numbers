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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests determining prime factors of a number.
 *
 * @author Kristian Kutin
 */
@Ignore
@UnitTest
@RunWith(Parameterized.class)
public class DeterminePrimeFactorsInLargeNumbersTest {

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
    public DeterminePrimeFactorsInLargeNumbersTest(Number number, Sequence<Number> sequence) {

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
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        // 12 : { 2, 2, 3 }
        parameters.add(new Object[] { createNumber(10, "12"), createNumberSequence(10, "2", "2", "3") });

        // 123 : { 3, 41 }
        parameters.add(new Object[] { createNumber(10, "123"), createNumberSequence(10, "3", "41") });

        // 1234 : { 2, 617 }
        parameters.add(new Object[] { createNumber(10, "1234"), createNumberSequence(10, "2", "617") });

        // 12345 : { 3, 5, 823 }
        parameters.add(new Object[] { createNumber(10, "12345"), createNumberSequence(10, "3", "5", "823") });

        // 123456 : { 2, 2, 2, 2, 2, 2, 3, 643 }
        parameters.add(new Object[] { createNumber(10, "123456"),
                                      createNumberSequence(10, "2", "2", "2", "2", "2", "2", "3", "643") });

        // 1234567 : { 127, 9721 }
        parameters.add(new Object[] { createNumber(10, "1234567"),
                                      createNumberSequence(10, "2", "2", "2", "2", "2", "2", "3", "643") });

        return parameters;
    }

}

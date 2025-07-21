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
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests calculating the digit sum.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DigitSumTest {

    /**
     * A number (i.e. integer)
     */
    private Number number;

    /**
     * The expected result.
     */
    private Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param expectedResult
     *        the expected result
     */
    public DigitSumTest(Number number, Number expectedResult) {

        super();

        this.number = number;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s -> [base:%d] %s ", number.base(), number, expectedResult.base(),
                             expectedResult);
    }

    /**
     * Tests calculating the digit sum.
     */
    @Test
    public void testCalculatingDigitSum() {

        Number actualResult = number.digitSum();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests calculating the digit sum.
     */
    @Test
    public void testCalculatingDigitSumVariant2() {

        Number actualResult = Math.digitSum(number);

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

        parameters.add(new Object[] { createNumber(2, "0"), createNumber(2, "0") });
        parameters.add(new Object[] { createNumber(2, "1"), createNumber(2, "1") });
        parameters.add(new Object[] { createNumber(2, "10"), createNumber(2, "1") });
        parameters.add(new Object[] { createNumber(2, "11"), createNumber(2, "10") });

        parameters.add(new Object[] { createNumber(10, "0"), createNumber(10, "0") });
        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "2") });
        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "3") });
        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "4") });
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "5") });
        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "6") });
        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "7") });
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "8") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "9") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "11"), createNumber(10, "2") });
        parameters.add(new Object[] { createNumber(10, "111"), createNumber(10, "3") });
        parameters.add(new Object[] { createNumber(10, "197"), createNumber(10, "17") });

        return parameters;
    }

}

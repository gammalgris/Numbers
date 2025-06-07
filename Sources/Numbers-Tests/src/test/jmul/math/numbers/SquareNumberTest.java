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
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests calculating the square of a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SquareNumberTest {

    /**
     * A number.
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
    public SquareNumberTest(Number number, Number expectedResult) {

        super();

        this.number = number;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary for this test case
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s -> [base:%d] %s", number.base(), number, expectedResult.base(),
                             expectedResult);
    }

    /**
     * Tests calculating the square of a number and checks the result.
     */
    @Test
    public void testCalculation() {

        Number actualResult = number.square();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests calculating the square of a number and checks the result.
     */
    @Test
    public void testCalculationVariant2() {

        Number actualResult = Math.square(number);

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

            parameters.add(new Object[] { createNumber(10, "0"), createNumber(10, "0") });
            parameters.add(new Object[] { createNumber(10, "-0"), createNumber(10, "0") });

            parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "1") });
            parameters.add(new Object[] { createNumber(10, "-1"), createNumber(10, "1") });
        }

        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "4") });
        parameters.add(new Object[] { createNumber(10, "-2"), createNumber(10, "4") });

        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "9") });
        parameters.add(new Object[] { createNumber(10, "-3"), createNumber(10, "9") });

        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "16") });
        parameters.add(new Object[] { createNumber(10, "-4"), createNumber(10, "16") });

        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "25") });
        parameters.add(new Object[] { createNumber(10, "-5"), createNumber(10, "25") });

        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "36") });
        parameters.add(new Object[] { createNumber(10, "-6"), createNumber(10, "36") });

        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "49") });
        parameters.add(new Object[] { createNumber(10, "-7"), createNumber(10, "49") });

        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "64") });
        parameters.add(new Object[] { createNumber(10, "-8"), createNumber(10, "64") });

        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "81") });
        parameters.add(new Object[] { createNumber(10, "-9"), createNumber(10, "81") });

        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "100") });
        parameters.add(new Object[] { createNumber(10, "-10"), createNumber(10, "100") });

        parameters.add(new Object[] { createNumber(10, "11"), createNumber(10, "121") });
        parameters.add(new Object[] { createNumber(10, "-11"), createNumber(10, "121") });

        return parameters;
    }

}

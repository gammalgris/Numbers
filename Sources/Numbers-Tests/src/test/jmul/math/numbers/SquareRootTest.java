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
 * Tests calculating the square root.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SquareRootTest {

    /**
     * A number.
     */
    private Number number;

    /**
     * (Optional) The number of decimal places retained after cutting the fraction part.
     */
    private Number decimalPlaces;

    /**
     * The expected result.
     */
    private Number expectedResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param number
     *        A number
     * @param decimalPlaces
     *        The number of decimal places retained after cutting the fraction part. If no value is specified use
     *        <code>null</code>.
     * @param expectedResult
     *        the expected result
     */
    public SquareRootTest(Number number, Number decimalPlaces, Number expectedResult) {

        super();

        this.number = number;
        this.decimalPlaces = decimalPlaces;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary for this test case
     */
    @Override
    public String toString() {

        if (decimalPlaces == null) {

            return String.format("[base:%d] %s -> [base:%d] %s", number.base(), number, expectedResult.base(),
                                 expectedResult);
        } else {

            return String.format("[base:%d] %s (decimalPlaces: [base:%d] %s) -> [base:%d] %s", number.base(), number,
                                 decimalPlaces.base(), decimalPlaces, expectedResult.base(), expectedResult);
        }
    }

    /**
     * Tests calculating the square of a number and checks the result.
     */
    @Test
    public void testCalculation() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = number.squareRoot();

        } else {

            actualResult = number.squareRoot(decimalPlaces);
        }

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests calculating the square of a number and checks the result.
     */
    @Test
    public void testCalculationVariant2() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = Math.squareRoot(number);

        } else {

            actualResult = Math.squareRoot(number, decimalPlaces);
        }

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

        parameters.add(new Object[] { createNumber(10, "2"), null, createNumber(10, "1.4142135623") });

        // Note:
        // A result with more digits is 1,4142135623730950488016887242097. The test below imotted the zero (-> trim)
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "20"),
                                      createNumber(10, "1.4142135623730950488") });

        parameters.add(new Object[] { createNumber(10, "9"), null, createNumber(10, "3") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "20"), createNumber(10, "3") });

        return parameters;
    }

}

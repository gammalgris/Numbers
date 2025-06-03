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
import jmul.math.functions.repository.FunctionIdentifiers;
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
 * This test suit tests dividing numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RussianDivisionTest {

    /**
     * A number.
     */
    private final Number number1;

    /**
     * A number.
     */
    private final Number number2;

    /**
     * The number of decimal places retained after cutting the fraction part
     */
    private final Number decimalPlaces;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     * @param expectedResult
     *        the expected result
     */
    public RussianDivisionTest(Number number1, Number number2, Number decimalPlaces, Number expectedResult) {

        super();

        this.number1 = number1;
        this.number2 = number2;
        this.decimalPlaces = decimalPlaces;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("(%d) %s / (%d) %s -> [%s] (%d) %s", number1.base(), number1, number2.base(), number2,
                             decimalPlaces, expectedResult.base(), expectedResult);
    }

    /**
     * Performs the division and checks the result.
     */
    @Test
    public void testDivision() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = number1.divide(FunctionIdentifiers.RUSSIAN_DIVISION_FUNCTION, number2);

        } else {

            actualResult = number1.divide(FunctionIdentifiers.RUSSIAN_DIVISION_FUNCTION, number2, decimalPlaces);
        }

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Performs the division and checks the result.
     */
    @Test
    public void testDivisionVariant2() {

        Number actualResult;
        if (decimalPlaces == null) {

            actualResult = Math.divide(FunctionIdentifiers.RUSSIAN_DIVISION_FUNCTION, number1, number2);

        } else {

            actualResult = Math.divide(FunctionIdentifiers.RUSSIAN_DIVISION_FUNCTION, number1, number2, decimalPlaces);
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

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createNumber(base, "0"), createNumber(base, "10"), null,
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "-0"), createNumber(base, "10"), null,
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "0"), createNumber(base, "-10"), null,
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "-0"), createNumber(base, "-10"), null,
                                          createNumber(base, "0") });

            parameters.add(new Object[] { createNumber(base, "0"), createNumber(base, "10"), createNumber(base, "10"),
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "-0"), createNumber(base, "10"), createNumber(base, "10"),
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "0"), createNumber(base, "-10"), createNumber(base, "10"),
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "-0"), createNumber(base, "-10"), createNumber(base, "10"),
                                          createNumber(base, "0") });

            parameters.add(new Object[] { createNumber(base, "10"), createNumber(base, "1"), null,
                                          createNumber(base, "10") });
            parameters.add(new Object[] { createNumber(base, "-10"), createNumber(base, "1"), null,
                                          createNumber(base, "-10") });
            parameters.add(new Object[] { createNumber(base, "10"), createNumber(base, "-1"), null,
                                          createNumber(base, "-10") });
            parameters.add(new Object[] { createNumber(base, "-10"), createNumber(base, "-1"), null,
                                          createNumber(base, "10") });

            parameters.add(new Object[] { createNumber(base, "10"), createNumber(base, "1"), createNumber(base, "10"),
                                          createNumber(base, "10") });
            parameters.add(new Object[] { createNumber(base, "-10"), createNumber(base, "1"), createNumber(base, "10"),
                                          createNumber(base, "-10") });
            parameters.add(new Object[] { createNumber(base, "10"), createNumber(base, "-1"), createNumber(base, "10"),
                                          createNumber(base, "-10") });
            parameters.add(new Object[] { createNumber(base, "-10"), createNumber(base, "-1"), createNumber(base, "10"),
                                          createNumber(base, "10") });
        }

        parameters.add(new Object[] { createNumber(10, "70"), createNumber(10, "7"), null, createNumber(10, "10") });
        parameters.add(new Object[] { createNumber(10, "-70"), createNumber(10, "7"), null, createNumber(10, "-10") });
        parameters.add(new Object[] { createNumber(10, "70"), createNumber(10, "-7"), null, createNumber(10, "-10") });
        parameters.add(new Object[] { createNumber(10, "-70"), createNumber(10, "-7"), null, createNumber(10, "10") });

        parameters.add(new Object[] { createNumber(10, "70"), createNumber(10, "7"), createNumber(10, "2"),
                                      createNumber(10, "10") });
        parameters.add(new Object[] { createNumber(10, "-70"), createNumber(10, "7"), createNumber(10, "2"),
                                      createNumber(10, "-10") });
        parameters.add(new Object[] { createNumber(10, "70"), createNumber(10, "-7"), createNumber(10, "2"),
                                      createNumber(10, "-10") });
        parameters.add(new Object[] { createNumber(10, "-70"), createNumber(10, "-7"), createNumber(10, "2"),
                                      createNumber(10, "10") });

        parameters.add(new Object[] { createNumber(10, "70"), createNumber(10, "6"), null,
                                      createNumber(10, "11.6666666666") });
        parameters.add(new Object[] { createNumber(10, "-70"), createNumber(10, "6"), null,
                                      createNumber(10, "-11.6666666666") });
        parameters.add(new Object[] { createNumber(10, "70"), createNumber(10, "-6"), null,
                                      createNumber(10, "-11.6666666666") });
        parameters.add(new Object[] { createNumber(10, "-70"), createNumber(10, "-6"), null,
                                      createNumber(10, "11.6666666666") });

        parameters.add(new Object[] { createNumber(10, "70"), createNumber(10, "6"), createNumber(10, "2"),
                                      createNumber(10, "11.66") });
        parameters.add(new Object[] { createNumber(10, "-70"), createNumber(10, "6"), createNumber(10, "2"),
                                      createNumber(10, "-11.66") });
        parameters.add(new Object[] { createNumber(10, "70"), createNumber(10, "-6"), createNumber(10, "2"),
                                      createNumber(10, "-11.66") });
        parameters.add(new Object[] { createNumber(10, "-70"), createNumber(10, "-6"), createNumber(10, "2"),
                                      createNumber(10, "11.66") });

        parameters.add(new Object[] { createNumber(10, "70.5"), createNumber(10, "6.1"), null,
                                      createNumber(10, "11.5573770491") });
        parameters.add(new Object[] { createNumber(10, "-70.5"), createNumber(10, "6.1"), null,
                                      createNumber(10, "-11.5573770491") });
        parameters.add(new Object[] { createNumber(10, "70.5"), createNumber(10, "-6.1"), null,
                                      createNumber(10, "-11.5573770491") });
        parameters.add(new Object[] { createNumber(10, "-70.5"), createNumber(10, "-6.1"), null,
                                      createNumber(10, "11.5573770491") });

        parameters.add(new Object[] { createNumber(10, "70.5"), createNumber(10, "6.1"), createNumber(10, "2"),
                                      createNumber(10, "11.55") });
        parameters.add(new Object[] { createNumber(10, "-70.5"), createNumber(10, "6.1"), createNumber(10, "2"),
                                      createNumber(10, "-11.55") });
        parameters.add(new Object[] { createNumber(10, "70.5"), createNumber(10, "-6.1"), createNumber(10, "2"),
                                      createNumber(10, "-11.55") });
        parameters.add(new Object[] { createNumber(10, "-70.5"), createNumber(10, "-6.1"), createNumber(10, "2"),
                                      createNumber(10, "11.55") });

        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "3.1"), null,
                                      createNumber(5, "12.3240124012") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "3.1"), null,
                                      createNumber(5, "-12.3240124012") });
        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "-3.1"), null,
                                      createNumber(5, "-12.3240124012") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "-3.1"), null,
                                      createNumber(5, "12.3240124012") });

        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "3.1"), createNumber(10, "2"),
                                      createNumber(5, "12.32") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "3.1"), createNumber(10, "2"),
                                      createNumber(5, "-12.32") });
        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "-3.1"), createNumber(10, "2"),
                                      createNumber(5, "-12.32") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "-3.1"), createNumber(10, "2"),
                                      createNumber(5, "12.32") });

        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "3.1"), createNumber(5, "2"),
                                      createNumber(5, "12.32") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "3.1"), createNumber(5, "2"),
                                      createNumber(5, "-12.32") });
        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "-3.1"), createNumber(5, "2"),
                                      createNumber(5, "-12.32") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "-3.1"), createNumber(5, "2"),
                                      createNumber(5, "12.32") });

        return parameters;
    }

}

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
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifiers;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suit tests dividing numbers (i.e. division by subtraction).
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DivideNumbers2Test {

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
    public DivideNumbers2Test(Number number1, Number number2, Number decimalPlaces, Number expectedResult) {

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

        if (decimalPlaces == null) {

            return String.format("[%d] %s / [%d] %s -> [%d] %s default precision", number1.base(), number1,
                                 number2.base(), number2, expectedResult.base(), expectedResult);

        } else {

            return String.format("[%d] %s / [%d] %s -> [%d] %s precision [%d] %s", number1.base(), number1,
                                 number2.base(), number2, expectedResult.base(), expectedResult, decimalPlaces.base(),
                                 decimalPlaces);
        }
    }

    /**
     * Performs the division and checks the result.
     */
    @Test
    public void testDivision() {

        Number actualResult;
        if (decimalPlaces == null) {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION,
                                                       ProcessingDetails.DEFAULT_PRECISION,
                                                       ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = number1.divide(processingDetails, number2);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION,
                                                       decimalPlaces, ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = number1.divide(processingDetails, number2);
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

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION,
                                                       ProcessingDetails.DEFAULT_PRECISION,
                                                       ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = Math.divide(processingDetails, number1, number2);

        } else {

            ProcessingDetails processingDetails =
                ProcessingDetails.setProcessingDetails(OperationIdentifiers.DIVIDE_NUMBERS_BY_SUBTRACTION,
                                                       decimalPlaces, ProcessingDetails.DEFAULT_ITERATION_DEPTH);

            actualResult = Math.divide(processingDetails, number1, number2);
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

        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "3.1"), createNumber(5, "2"),
                                      createNumber(5, "12.32") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "3.1"), createNumber(5, "2"),
                                      createNumber(5, "-12.32") });
        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "-3.1"), createNumber(5, "2"),
                                      createNumber(5, "-12.32") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "-3.1"), createNumber(5, "2"),
                                      createNumber(5, "12.32") });

        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "3.1"), createNumber(5, "2"),
                                      createNumber(5, "12.32") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "3.1"), createNumber(5, "2"),
                                      createNumber(5, "-12.32") });
        parameters.add(new Object[] { createNumber(5, "44.32"), createNumber(5, "-3.1"), createNumber(5, "2"),
                                      createNumber(5, "-12.32") });
        parameters.add(new Object[] { createNumber(5, "-44.32"), createNumber(5, "-3.1"), createNumber(5, "2"),
                                      createNumber(5, "12.32") });

        parameters.add(new Object[] { createNumber(10, "0.4"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0.08") });
        parameters.add(new Object[] { createNumber(10, "0.04"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0.008") });
        parameters.add(new Object[] { createNumber(10, "0.004"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0.0008") });
        parameters.add(new Object[] { createNumber(10, "0.0004"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0.00008") });
        parameters.add(new Object[] { createNumber(10, "0.00004"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0.000008") });
        parameters.add(new Object[] { createNumber(10, "0.000004"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0.0000008") });
        parameters.add(new Object[] { createNumber(10, "0.0000004"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0.00000008") });
        parameters.add(new Object[] { createNumber(10, "0.00000004"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0.000000008") });
        parameters.add(new Object[] { createNumber(10, "0.000000004"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0.0000000008") });
        parameters.add(new Object[] { createNumber(10, "0.0000000004"), createNumber(10, "5"), createNumber(10, "10"),
                                      createNumber(10, "0") });


        // multiplication table in decimal

        Number precision = createNumber(10, "10");

        for (int a = -10; a <= 10; a++) {

            if (a == 0) {

                continue;
            }

            for (int b = -10; b <= 10; b++) {

                if (b == 0) {

                    continue;
                }

                int result = a * b;

                Number dividend = createNumber(10, "" + result);
                Number divisor = createNumber(10, "" + a);
                Number quotient = createNumber(10, "" + b);

                parameters.add(new Object[] { dividend, divisor, precision, quotient });
            }
        }


        // multiplication table in ternary

        parameters.add(createTestCase(3, "1", "1", "101", "1"));
        parameters.add(createTestCase(3, "2", "1", "101", "2"));
        parameters.add(createTestCase(3, "10", "1", "101", "10"));
        parameters.add(createTestCase(3, "11", "1", "101", "11"));
        parameters.add(createTestCase(3, "12", "1", "101", "12"));
        parameters.add(createTestCase(3, "20", "1", "101", "20"));
        parameters.add(createTestCase(3, "21", "1", "101", "21"));
        parameters.add(createTestCase(3, "22", "1", "101", "22"));
        parameters.add(createTestCase(3, "100", "1", "101", "100"));
        parameters.add(createTestCase(3, "101", "1", "101", "101"));

        parameters.add(createTestCase(3, "2", "2", "101", "1"));
        parameters.add(createTestCase(3, "11", "2", "101", "2"));
        parameters.add(createTestCase(3, "20", "2", "101", "10"));
        parameters.add(createTestCase(3, "22", "2", "101", "11"));
        parameters.add(createTestCase(3, "101", "2", "101", "12"));
        parameters.add(createTestCase(3, "110", "2", "101", "20"));
        parameters.add(createTestCase(3, "112", "2", "101", "21"));
        parameters.add(createTestCase(3, "121", "2", "101", "22"));
        parameters.add(createTestCase(3, "200", "2", "101", "100"));
        parameters.add(createTestCase(3, "202", "2", "101", "101"));

        parameters.add(createTestCase(3, "10", "10", "101", "1"));
        parameters.add(createTestCase(3, "20", "10", "101", "2"));
        parameters.add(createTestCase(3, "100", "10", "101", "10"));
        parameters.add(createTestCase(3, "110", "10", "101", "11"));
        parameters.add(createTestCase(3, "120", "10", "101", "12"));
        parameters.add(createTestCase(3, "200", "10", "101", "20"));
        parameters.add(createTestCase(3, "210", "10", "101", "21"));
        parameters.add(createTestCase(3, "220", "10", "101", "22"));
        parameters.add(createTestCase(3, "1000", "10", "101", "100"));
        parameters.add(createTestCase(3, "1010", "10", "101", "101"));

        parameters.add(createTestCase(3, "11", "11", "101", "1"));
        parameters.add(createTestCase(3, "22", "11", "101", "2"));
        parameters.add(createTestCase(3, "110", "11", "101", "10"));
        parameters.add(createTestCase(3, "121", "11", "101", "11"));
        parameters.add(createTestCase(3, "202", "11", "101", "12"));
        parameters.add(createTestCase(3, "220", "11", "101", "20"));
        parameters.add(createTestCase(3, "1001", "11", "101", "21"));
        parameters.add(createTestCase(3, "1012", "11", "101", "22"));
        parameters.add(createTestCase(3, "1100", "11", "101", "100"));
        parameters.add(createTestCase(3, "1111", "11", "101", "101"));

        parameters.add(createTestCase(3, "12", "12", "101", "1"));
        parameters.add(createTestCase(3, "101", "12", "101", "2"));
        parameters.add(createTestCase(3, "120", "12", "101", "10"));
        parameters.add(createTestCase(3, "202", "12", "101", "11"));
        parameters.add(createTestCase(3, "221", "12", "101", "12"));
        parameters.add(createTestCase(3, "1010", "12", "101", "20"));
        parameters.add(createTestCase(3, "1022", "12", "101", "21"));
        parameters.add(createTestCase(3, "1111", "12", "101", "22"));
        parameters.add(createTestCase(3, "1200", "12", "101", "100"));
        parameters.add(createTestCase(3, "1212", "12", "101", "101"));

        parameters.add(createTestCase(3, "20", "20", "101", "1"));
        parameters.add(createTestCase(3, "110", "20", "101", "2"));
        parameters.add(createTestCase(3, "200", "20", "101", "10"));
        parameters.add(createTestCase(3, "220", "20", "101", "11"));
        parameters.add(createTestCase(3, "1010", "20", "101", "12"));
        parameters.add(createTestCase(3, "1100", "20", "101", "20"));
        parameters.add(createTestCase(3, "1120", "20", "101", "21"));
        parameters.add(createTestCase(3, "1210", "20", "101", "22"));
        parameters.add(createTestCase(3, "2000", "20", "101", "100"));
        parameters.add(createTestCase(3, "2020", "20", "101", "101"));

        parameters.add(createTestCase(3, "21", "21", "101", "1"));
        parameters.add(createTestCase(3, "112", "21", "101", "2"));
        parameters.add(createTestCase(3, "210", "21", "101", "10"));
        parameters.add(createTestCase(3, "1001", "21", "101", "11"));
        parameters.add(createTestCase(3, "1022", "21", "101", "12"));
        parameters.add(createTestCase(3, "1120", "21", "101", "20"));
        parameters.add(createTestCase(3, "1211", "21", "101", "21"));
        parameters.add(createTestCase(3, "2002", "21", "101", "22"));
        parameters.add(createTestCase(3, "2100", "21", "101", "100"));
        parameters.add(createTestCase(3, "2121", "21", "101", "101"));

        parameters.add(createTestCase(3, "22", "22", "101", "1"));
        parameters.add(createTestCase(3, "121", "22", "101", "2"));
        parameters.add(createTestCase(3, "220", "22", "101", "10"));
        parameters.add(createTestCase(3, "1012", "22", "101", "11"));
        parameters.add(createTestCase(3, "1111", "22", "101", "12"));
        parameters.add(createTestCase(3, "1210", "22", "101", "20"));
        parameters.add(createTestCase(3, "2002", "22", "101", "21"));
        parameters.add(createTestCase(3, "2101", "22", "101", "22"));
        parameters.add(createTestCase(3, "2200", "22", "101", "100"));
        parameters.add(createTestCase(3, "2222", "22", "101", "101"));

        parameters.add(createTestCase(3, "100", "100", "101", "1"));
        parameters.add(createTestCase(3, "200", "100", "101", "2"));
        parameters.add(createTestCase(3, "1000", "100", "101", "10"));
        parameters.add(createTestCase(3, "1100", "100", "101", "11"));
        parameters.add(createTestCase(3, "1200", "100", "101", "12"));
        parameters.add(createTestCase(3, "2000", "100", "101", "20"));
        parameters.add(createTestCase(3, "2100", "100", "101", "21"));
        parameters.add(createTestCase(3, "2200", "100", "101", "22"));
        parameters.add(createTestCase(3, "10000", "100", "101", "100"));
        parameters.add(createTestCase(3, "10100", "100", "101", "101"));

        parameters.add(createTestCase(3, "101", "101", "101", "1"));
        parameters.add(createTestCase(3, "202", "101", "101", "2"));
        parameters.add(createTestCase(3, "1010", "101", "101", "10"));
        parameters.add(createTestCase(3, "1111", "101", "101", "11"));
        parameters.add(createTestCase(3, "1212", "101", "101", "12"));
        parameters.add(createTestCase(3, "2020", "101", "101", "20"));
        parameters.add(createTestCase(3, "2121", "101", "101", "21"));
        parameters.add(createTestCase(3, "2222", "101", "101", "22"));
        parameters.add(createTestCase(3, "10100", "101", "101", "100"));
        parameters.add(createTestCase(3, "10201", "101", "101", "101"));

        parameters.add(createTestCase(3, "-1", "-1", "101", "1"));
        parameters.add(createTestCase(3, "-2", "-1", "101", "2"));
        parameters.add(createTestCase(3, "-10", "-1", "101", "10"));
        parameters.add(createTestCase(3, "-11", "-1", "101", "11"));
        parameters.add(createTestCase(3, "-12", "-1", "101", "12"));
        parameters.add(createTestCase(3, "-20", "-1", "101", "20"));
        parameters.add(createTestCase(3, "-21", "-1", "101", "21"));
        parameters.add(createTestCase(3, "-22", "-1", "101", "22"));
        parameters.add(createTestCase(3, "-100", "-1", "101", "100"));
        parameters.add(createTestCase(3, "-101", "-1", "101", "101"));

        parameters.add(createTestCase(3, "-2", "-2", "101", "1"));
        parameters.add(createTestCase(3, "-11", "-2", "101", "2"));
        parameters.add(createTestCase(3, "-20", "-2", "101", "10"));
        parameters.add(createTestCase(3, "-22", "-2", "101", "11"));
        parameters.add(createTestCase(3, "-101", "-2", "101", "12"));
        parameters.add(createTestCase(3, "-110", "-2", "101", "20"));
        parameters.add(createTestCase(3, "-112", "-2", "101", "21"));
        parameters.add(createTestCase(3, "-121", "-2", "101", "22"));
        parameters.add(createTestCase(3, "-200", "-2", "101", "100"));
        parameters.add(createTestCase(3, "-202", "-2", "101", "101"));

        parameters.add(createTestCase(3, "-10", "-10", "101", "1"));
        parameters.add(createTestCase(3, "-20", "-10", "101", "2"));
        parameters.add(createTestCase(3, "-100", "-10", "101", "10"));
        parameters.add(createTestCase(3, "-110", "-10", "101", "11"));
        parameters.add(createTestCase(3, "-120", "-10", "101", "12"));
        parameters.add(createTestCase(3, "-200", "-10", "101", "20"));
        parameters.add(createTestCase(3, "-210", "-10", "101", "21"));
        parameters.add(createTestCase(3, "-220", "-10", "101", "22"));
        parameters.add(createTestCase(3, "-1000", "-10", "101", "100"));
        parameters.add(createTestCase(3, "-1010", "-10", "101", "101"));

        parameters.add(createTestCase(3, "-11", "-11", "101", "1"));
        parameters.add(createTestCase(3, "-22", "-11", "101", "2"));
        parameters.add(createTestCase(3, "-110", "-11", "101", "10"));
        parameters.add(createTestCase(3, "-121", "-11", "101", "11"));
        parameters.add(createTestCase(3, "-202", "-11", "101", "12"));
        parameters.add(createTestCase(3, "-220", "-11", "101", "20"));
        parameters.add(createTestCase(3, "-1001", "-11", "101", "21"));
        parameters.add(createTestCase(3, "-1012", "-11", "101", "22"));
        parameters.add(createTestCase(3, "-1100", "-11", "101", "100"));
        parameters.add(createTestCase(3, "-1111", "-11", "101", "101"));

        parameters.add(createTestCase(3, "-12", "-12", "101", "1"));
        parameters.add(createTestCase(3, "-101", "-12", "101", "2"));
        parameters.add(createTestCase(3, "-120", "-12", "101", "10"));
        parameters.add(createTestCase(3, "-202", "-12", "101", "11"));
        parameters.add(createTestCase(3, "-221", "-12", "101", "12"));
        parameters.add(createTestCase(3, "-1010", "-12", "101", "20"));
        parameters.add(createTestCase(3, "-1022", "-12", "101", "21"));
        parameters.add(createTestCase(3, "-1111", "-12", "101", "22"));
        parameters.add(createTestCase(3, "-1200", "-12", "101", "100"));
        parameters.add(createTestCase(3, "-1212", "-12", "101", "101"));

        parameters.add(createTestCase(3, "-20", "-20", "101", "1"));
        parameters.add(createTestCase(3, "-110", "-20", "101", "2"));
        parameters.add(createTestCase(3, "-200", "-20", "101", "10"));
        parameters.add(createTestCase(3, "-220", "-20", "101", "11"));
        parameters.add(createTestCase(3, "-1010", "-20", "101", "12"));
        parameters.add(createTestCase(3, "-1100", "-20", "101", "20"));
        parameters.add(createTestCase(3, "-1120", "-20", "101", "21"));
        parameters.add(createTestCase(3, "-1210", "-20", "101", "22"));
        parameters.add(createTestCase(3, "-2000", "-20", "101", "100"));
        parameters.add(createTestCase(3, "-2020", "-20", "101", "101"));

        parameters.add(createTestCase(3, "-21", "-21", "101", "1"));
        parameters.add(createTestCase(3, "-112", "-21", "101", "2"));
        parameters.add(createTestCase(3, "-210", "-21", "101", "10"));
        parameters.add(createTestCase(3, "-1001", "-21", "101", "11"));
        parameters.add(createTestCase(3, "-1022", "-21", "101", "12"));
        parameters.add(createTestCase(3, "-1120", "-21", "101", "20"));
        parameters.add(createTestCase(3, "-1211", "-21", "101", "21"));
        parameters.add(createTestCase(3, "-2002", "-21", "101", "22"));
        parameters.add(createTestCase(3, "-2100", "-21", "101", "100"));
        parameters.add(createTestCase(3, "-2121", "-21", "101", "101"));

        parameters.add(createTestCase(3, "-22", "-22", "101", "1"));
        parameters.add(createTestCase(3, "-121", "-22", "101", "2"));
        parameters.add(createTestCase(3, "-220", "-22", "101", "10"));
        parameters.add(createTestCase(3, "-1012", "-22", "101", "11"));
        parameters.add(createTestCase(3, "-1111", "-22", "101", "12"));
        parameters.add(createTestCase(3, "-1210", "-22", "101", "20"));
        parameters.add(createTestCase(3, "-2002", "-22", "101", "21"));
        parameters.add(createTestCase(3, "-2101", "-22", "101", "22"));
        parameters.add(createTestCase(3, "-2200", "-22", "101", "100"));
        parameters.add(createTestCase(3, "-2222", "-22", "101", "101"));

        parameters.add(createTestCase(3, "-100", "-100", "101", "1"));
        parameters.add(createTestCase(3, "-200", "-100", "101", "2"));
        parameters.add(createTestCase(3, "-1000", "-100", "101", "10"));
        parameters.add(createTestCase(3, "-1100", "-100", "101", "11"));
        parameters.add(createTestCase(3, "-1200", "-100", "101", "12"));
        parameters.add(createTestCase(3, "-2000", "-100", "101", "20"));
        parameters.add(createTestCase(3, "-2100", "-100", "101", "21"));
        parameters.add(createTestCase(3, "-2200", "-100", "101", "22"));
        parameters.add(createTestCase(3, "-10000", "-100", "101", "100"));
        parameters.add(createTestCase(3, "-10100", "-100", "101", "101"));

        parameters.add(createTestCase(3, "-101", "-101", "101", "1"));
        parameters.add(createTestCase(3, "-202", "-101", "101", "2"));
        parameters.add(createTestCase(3, "-1010", "-101", "101", "10"));
        parameters.add(createTestCase(3, "-1111", "-101", "101", "11"));
        parameters.add(createTestCase(3, "-1212", "-101", "101", "12"));
        parameters.add(createTestCase(3, "-2020", "-101", "101", "20"));
        parameters.add(createTestCase(3, "-2121", "-101", "101", "21"));
        parameters.add(createTestCase(3, "-2222", "-101", "101", "22"));
        parameters.add(createTestCase(3, "-10100", "-101", "101", "100"));
        parameters.add(createTestCase(3, "-10201", "-101", "101", "101"));

        parameters.add(createTestCase(3, "-1", "1", "101", "-1"));
        parameters.add(createTestCase(3, "-2", "1", "101", "-2"));
        parameters.add(createTestCase(3, "-10", "1", "101", "-10"));
        parameters.add(createTestCase(3, "-11", "1", "101", "-11"));
        parameters.add(createTestCase(3, "-12", "1", "101", "-12"));
        parameters.add(createTestCase(3, "-20", "1", "101", "-20"));
        parameters.add(createTestCase(3, "-21", "1", "101", "-21"));
        parameters.add(createTestCase(3, "-22", "1", "101", "-22"));
        parameters.add(createTestCase(3, "-100", "1", "101", "-100"));
        parameters.add(createTestCase(3, "-101", "1", "101", "-101"));

        parameters.add(createTestCase(3, "-2", "2", "101", "-1"));
        parameters.add(createTestCase(3, "-11", "2", "101", "-2"));
        parameters.add(createTestCase(3, "-20", "2", "101", "-10"));
        parameters.add(createTestCase(3, "-22", "2", "101", "-11"));
        parameters.add(createTestCase(3, "-101", "2", "101", "-12"));
        parameters.add(createTestCase(3, "-110", "2", "101", "-20"));
        parameters.add(createTestCase(3, "-112", "2", "101", "-21"));
        parameters.add(createTestCase(3, "-121", "2", "101", "-22"));
        parameters.add(createTestCase(3, "-200", "2", "101", "-100"));
        parameters.add(createTestCase(3, "-202", "2", "101", "-101"));

        parameters.add(createTestCase(3, "-10", "10", "101", "-1"));
        parameters.add(createTestCase(3, "-20", "10", "101", "-2"));
        parameters.add(createTestCase(3, "-100", "10", "101", "-10"));
        parameters.add(createTestCase(3, "-110", "10", "101", "-11"));
        parameters.add(createTestCase(3, "-120", "10", "101", "-12"));
        parameters.add(createTestCase(3, "-200", "10", "101", "-20"));
        parameters.add(createTestCase(3, "-210", "10", "101", "-21"));
        parameters.add(createTestCase(3, "-220", "10", "101", "-22"));
        parameters.add(createTestCase(3, "-1000", "10", "101", "-100"));
        parameters.add(createTestCase(3, "-1010", "10", "101", "-101"));

        parameters.add(createTestCase(3, "-11", "11", "101", "-1"));
        parameters.add(createTestCase(3, "-22", "11", "101", "-2"));
        parameters.add(createTestCase(3, "-110", "11", "101", "-10"));
        parameters.add(createTestCase(3, "-121", "11", "101", "-11"));
        parameters.add(createTestCase(3, "-202", "11", "101", "-12"));
        parameters.add(createTestCase(3, "-220", "11", "101", "-20"));
        parameters.add(createTestCase(3, "-1001", "11", "101", "-21"));
        parameters.add(createTestCase(3, "-1012", "11", "101", "-22"));
        parameters.add(createTestCase(3, "-1100", "11", "101", "-100"));
        parameters.add(createTestCase(3, "-1111", "11", "101", "-101"));

        parameters.add(createTestCase(3, "-12", "12", "101", "-1"));
        parameters.add(createTestCase(3, "-101", "12", "101", "-2"));
        parameters.add(createTestCase(3, "-120", "12", "101", "-10"));
        parameters.add(createTestCase(3, "-202", "12", "101", "-11"));
        parameters.add(createTestCase(3, "-221", "12", "101", "-12"));
        parameters.add(createTestCase(3, "-1010", "12", "101", "-20"));
        parameters.add(createTestCase(3, "-1022", "12", "101", "-21"));
        parameters.add(createTestCase(3, "-1111", "12", "101", "-22"));
        parameters.add(createTestCase(3, "-1200", "12", "101", "-100"));
        parameters.add(createTestCase(3, "-1212", "12", "101", "-101"));

        parameters.add(createTestCase(3, "-20", "20", "101", "-1"));
        parameters.add(createTestCase(3, "-110", "20", "101", "-2"));
        parameters.add(createTestCase(3, "-200", "20", "101", "-10"));
        parameters.add(createTestCase(3, "-220", "20", "101", "-11"));
        parameters.add(createTestCase(3, "-1010", "20", "101", "-12"));
        parameters.add(createTestCase(3, "-1100", "20", "101", "-20"));
        parameters.add(createTestCase(3, "-1120", "20", "101", "-21"));
        parameters.add(createTestCase(3, "-1210", "20", "101", "-22"));
        parameters.add(createTestCase(3, "-2000", "20", "101", "-100"));
        parameters.add(createTestCase(3, "-2020", "20", "101", "-101"));

        parameters.add(createTestCase(3, "-21", "21", "101", "-1"));
        parameters.add(createTestCase(3, "-112", "21", "101", "-2"));
        parameters.add(createTestCase(3, "-210", "21", "101", "-10"));
        parameters.add(createTestCase(3, "-1001", "21", "101", "-11"));
        parameters.add(createTestCase(3, "-1022", "21", "101", "-12"));
        parameters.add(createTestCase(3, "-1120", "21", "101", "-20"));
        parameters.add(createTestCase(3, "-1211", "21", "101", "-21"));
        parameters.add(createTestCase(3, "-2002", "21", "101", "-22"));
        parameters.add(createTestCase(3, "-2100", "21", "101", "-100"));
        parameters.add(createTestCase(3, "-2121", "21", "101", "-101"));

        parameters.add(createTestCase(3, "-22", "22", "101", "-1"));
        parameters.add(createTestCase(3, "-121", "22", "101", "-2"));
        parameters.add(createTestCase(3, "-220", "22", "101", "-10"));
        parameters.add(createTestCase(3, "-1012", "22", "101", "-11"));
        parameters.add(createTestCase(3, "-1111", "22", "101", "-12"));
        parameters.add(createTestCase(3, "-1210", "22", "101", "-20"));
        parameters.add(createTestCase(3, "-2002", "22", "101", "-21"));
        parameters.add(createTestCase(3, "-2101", "22", "101", "-22"));
        parameters.add(createTestCase(3, "-2200", "22", "101", "-100"));
        parameters.add(createTestCase(3, "-2222", "22", "101", "-101"));

        parameters.add(createTestCase(3, "-100", "100", "101", "-1"));
        parameters.add(createTestCase(3, "-200", "100", "101", "-2"));
        parameters.add(createTestCase(3, "-1000", "100", "101", "-10"));
        parameters.add(createTestCase(3, "-1100", "100", "101", "-11"));
        parameters.add(createTestCase(3, "-1200", "100", "101", "-12"));
        parameters.add(createTestCase(3, "-2000", "100", "101", "-20"));
        parameters.add(createTestCase(3, "-2100", "100", "101", "-21"));
        parameters.add(createTestCase(3, "-2200", "100", "101", "-22"));
        parameters.add(createTestCase(3, "-10000", "100", "101", "-100"));
        parameters.add(createTestCase(3, "-10100", "100", "101", "-101"));

        parameters.add(createTestCase(3, "-101", "101", "101", "-1"));
        parameters.add(createTestCase(3, "-202", "101", "101", "-2"));
        parameters.add(createTestCase(3, "-1010", "101", "101", "-10"));
        parameters.add(createTestCase(3, "-1111", "101", "101", "-11"));
        parameters.add(createTestCase(3, "-1212", "101", "101", "-12"));
        parameters.add(createTestCase(3, "-2020", "101", "101", "-20"));
        parameters.add(createTestCase(3, "-2121", "101", "101", "-21"));
        parameters.add(createTestCase(3, "-2222", "101", "101", "-22"));
        parameters.add(createTestCase(3, "-10100", "101", "101", "-100"));
        parameters.add(createTestCase(3, "-10201", "101", "101", "-101"));

        parameters.add(createTestCase(3, "1", "-1", "101", "-1"));
        parameters.add(createTestCase(3, "2", "-1", "101", "-2"));
        parameters.add(createTestCase(3, "10", "-1", "101", "-10"));
        parameters.add(createTestCase(3, "11", "-1", "101", "-11"));
        parameters.add(createTestCase(3, "12", "-1", "101", "-12"));
        parameters.add(createTestCase(3, "20", "-1", "101", "-20"));
        parameters.add(createTestCase(3, "21", "-1", "101", "-21"));
        parameters.add(createTestCase(3, "22", "-1", "101", "-22"));
        parameters.add(createTestCase(3, "100", "-1", "101", "-100"));
        parameters.add(createTestCase(3, "101", "-1", "101", "-101"));

        parameters.add(createTestCase(3, "2", "-2", "101", "-1"));
        parameters.add(createTestCase(3, "11", "-2", "101", "-2"));
        parameters.add(createTestCase(3, "20", "-2", "101", "-10"));
        parameters.add(createTestCase(3, "22", "-2", "101", "-11"));
        parameters.add(createTestCase(3, "101", "-2", "101", "-12"));
        parameters.add(createTestCase(3, "110", "-2", "101", "-20"));
        parameters.add(createTestCase(3, "112", "-2", "101", "-21"));
        parameters.add(createTestCase(3, "121", "-2", "101", "-22"));
        parameters.add(createTestCase(3, "200", "-2", "101", "-100"));
        parameters.add(createTestCase(3, "202", "-2", "101", "-101"));

        parameters.add(createTestCase(3, "10", "-10", "101", "-1"));
        parameters.add(createTestCase(3, "20", "-10", "101", "-2"));
        parameters.add(createTestCase(3, "100", "-10", "101", "-10"));
        parameters.add(createTestCase(3, "110", "-10", "101", "-11"));
        parameters.add(createTestCase(3, "120", "-10", "101", "-12"));
        parameters.add(createTestCase(3, "200", "-10", "101", "-20"));
        parameters.add(createTestCase(3, "210", "-10", "101", "-21"));
        parameters.add(createTestCase(3, "220", "-10", "101", "-22"));
        parameters.add(createTestCase(3, "1000", "-10", "101", "-100"));
        parameters.add(createTestCase(3, "1010", "-10", "101", "-101"));

        parameters.add(createTestCase(3, "11", "-11", "101", "-1"));
        parameters.add(createTestCase(3, "22", "-11", "101", "-2"));
        parameters.add(createTestCase(3, "110", "-11", "101", "-10"));
        parameters.add(createTestCase(3, "121", "-11", "101", "-11"));
        parameters.add(createTestCase(3, "202", "-11", "101", "-12"));
        parameters.add(createTestCase(3, "220", "-11", "101", "-20"));
        parameters.add(createTestCase(3, "1001", "-11", "101", "-21"));
        parameters.add(createTestCase(3, "1012", "-11", "101", "-22"));
        parameters.add(createTestCase(3, "1100", "-11", "101", "-100"));
        parameters.add(createTestCase(3, "1111", "-11", "101", "-101"));

        parameters.add(createTestCase(3, "12", "-12", "101", "-1"));
        parameters.add(createTestCase(3, "101", "-12", "101", "-2"));
        parameters.add(createTestCase(3, "120", "-12", "101", "-10"));
        parameters.add(createTestCase(3, "202", "-12", "101", "-11"));
        parameters.add(createTestCase(3, "221", "-12", "101", "-12"));
        parameters.add(createTestCase(3, "1010", "-12", "101", "-20"));
        parameters.add(createTestCase(3, "1022", "-12", "101", "-21"));
        parameters.add(createTestCase(3, "1111", "-12", "101", "-22"));
        parameters.add(createTestCase(3, "1200", "-12", "101", "-100"));
        parameters.add(createTestCase(3, "1212", "-12", "101", "-101"));

        parameters.add(createTestCase(3, "20", "-20", "101", "-1"));
        parameters.add(createTestCase(3, "110", "-20", "101", "-2"));
        parameters.add(createTestCase(3, "200", "-20", "101", "-10"));
        parameters.add(createTestCase(3, "220", "-20", "101", "-11"));
        parameters.add(createTestCase(3, "1010", "-20", "101", "-12"));
        parameters.add(createTestCase(3, "1100", "-20", "101", "-20"));
        parameters.add(createTestCase(3, "1120", "-20", "101", "-21"));
        parameters.add(createTestCase(3, "1210", "-20", "101", "-22"));
        parameters.add(createTestCase(3, "2000", "-20", "101", "-100"));
        parameters.add(createTestCase(3, "2020", "-20", "101", "-101"));

        parameters.add(createTestCase(3, "21", "-21", "101", "-1"));
        parameters.add(createTestCase(3, "112", "-21", "101", "-2"));
        parameters.add(createTestCase(3, "210", "-21", "101", "-10"));
        parameters.add(createTestCase(3, "1001", "-21", "101", "-11"));
        parameters.add(createTestCase(3, "1022", "-21", "101", "-12"));
        parameters.add(createTestCase(3, "1120", "-21", "101", "-20"));
        parameters.add(createTestCase(3, "1211", "-21", "101", "-21"));
        parameters.add(createTestCase(3, "2002", "-21", "101", "-22"));
        parameters.add(createTestCase(3, "2100", "-21", "101", "-100"));
        parameters.add(createTestCase(3, "2121", "-21", "101", "-101"));

        parameters.add(createTestCase(3, "22", "-22", "101", "-1"));
        parameters.add(createTestCase(3, "121", "-22", "101", "-2"));
        parameters.add(createTestCase(3, "220", "-22", "101", "-10"));
        parameters.add(createTestCase(3, "1012", "-22", "101", "-11"));
        parameters.add(createTestCase(3, "1111", "-22", "101", "-12"));
        parameters.add(createTestCase(3, "1210", "-22", "101", "-20"));
        parameters.add(createTestCase(3, "2002", "-22", "101", "-21"));
        parameters.add(createTestCase(3, "2101", "-22", "101", "-22"));
        parameters.add(createTestCase(3, "2200", "-22", "101", "-100"));
        parameters.add(createTestCase(3, "2222", "-22", "101", "-101"));

        parameters.add(createTestCase(3, "100", "-100", "101", "-1"));
        parameters.add(createTestCase(3, "200", "-100", "101", "-2"));
        parameters.add(createTestCase(3, "1000", "-100", "101", "-10"));
        parameters.add(createTestCase(3, "1100", "-100", "101", "-11"));
        parameters.add(createTestCase(3, "1200", "-100", "101", "-12"));
        parameters.add(createTestCase(3, "2000", "-100", "101", "-20"));
        parameters.add(createTestCase(3, "2100", "-100", "101", "-21"));
        parameters.add(createTestCase(3, "2200", "-100", "101", "-22"));
        parameters.add(createTestCase(3, "10000", "-100", "101", "-100"));
        parameters.add(createTestCase(3, "10100", "-100", "101", "-101"));

        parameters.add(createTestCase(3, "101", "-101", "101", "-1"));
        parameters.add(createTestCase(3, "202", "-101", "101", "-2"));
        parameters.add(createTestCase(3, "1010", "-101", "101", "-10"));
        parameters.add(createTestCase(3, "1111", "-101", "101", "-11"));
        parameters.add(createTestCase(3, "1212", "-101", "101", "-12"));
        parameters.add(createTestCase(3, "2020", "-101", "101", "-20"));
        parameters.add(createTestCase(3, "2121", "-101", "101", "-21"));
        parameters.add(createTestCase(3, "2222", "-101", "101", "-22"));
        parameters.add(createTestCase(3, "10100", "-101", "101", "-100"));
        parameters.add(createTestCase(3, "10201", "-101", "101", "-101"));


        return parameters;
    }

    /**
     * A helper function to provide test data in a diverging form.
     *
     * @param base
     *        a number base
     * @param numberString1
     *        the dividend (i.e. a number string)
     * @param numberString2
     *        the divisor (i.e. a number string)
     * @param numberString3
     *        the precision in digits (i.e. a number string)
     * @param numberString4
     *        the expected quotient (i.e. a number string)
     *
     * @return test data which conforms to the constructor signature.
     */
    private static Object[] createTestCase(int base, String numberString1, String numberString2, String numberString3,
                                           String numberString4) {

        return new Object[] {
               createNumber(base, numberString1), createNumber(base, numberString2), createNumber(base, numberString3),
               createNumber(base, numberString4)
        };
    }

}

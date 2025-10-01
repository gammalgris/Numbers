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
import jmul.math.operations.processing.ProcessingDetails;
import jmul.math.operations.repository.OperationIdentifiers;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suit tests rounding numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RoundToEvenTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * A the number of decimal places retained after cutting the fraction part.
     */
    private final Number decimalPlaces;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param decimalPlaces
     *        the number of decimal places retained after cutting the fraction part
     * @param expectedResult
     *        the expected result
     */
    public RoundToEvenTest(Number number, Number decimalPlaces, Number expectedResult) {

        super();

        this.number = number;
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

        return String.format("[%d] %s (precision [%d] %s) -> [%d] %s", number.base(), number, decimalPlaces.base(),
                             decimalPlaces, expectedResult.base(), expectedResult);
    }

    /**
     * Rounds a number and checks the result.
     */
    @Test
    public void testRounding() {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.ROUND_NUMBER_TO_EVEN_FUNCTION, decimalPlaces,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);
        Number actualResult = number.round(processingDetails);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Rounds a number and checks the result.
     */
    @Test
    public void testRoundingVariant2() {

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.ROUND_NUMBER_TO_EVEN_FUNCTION, decimalPlaces,
                                                   ProcessingDetails.DEFAULT_ITERATION_DEPTH);
        Number actualResult = Math.round(processingDetails, number);

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

            parameters.add(new Object[] { createInfinity(base), createNumber(base, "110"), createInfinity(base) });
            parameters.add(new Object[] { createNegativeInfinity(base), createNumber(base, "1"),
                                          createNegativeInfinity(base) });

            parameters.add(new Object[] { createNumber(base, "0"), createNumber(base, "1"), createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "-0"), createNumber(base, "1"), createNumber(base, "0") });

            parameters.add(new Object[] { createNumber(base, "0.1"), createNumber(10, "1"),
                                          createNumber(base, "0.1") });
            parameters.add(new Object[] { createNumber(base, "-0.1"), createNumber(10, "1"),
                                          createNumber(base, "-0.1") });

            parameters.add(new Object[] { createNumber(base, "0.1"), createNumber(10, "2"),
                                          createNumber(base, "0.1") });
            parameters.add(new Object[] { createNumber(base, "-0.1"), createNumber(10, "2"),
                                          createNumber(base, "-0.1") });

            parameters.add(new Object[] { createNumber(base, "0.1"), createNumber(10, "0"), createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "-0.1"), createNumber(10, "0"), createNumber(base, "0") });
        }

        parameters.add(new Object[] { createNumber(2, "1"), createNumber(10, "0"), createNumber(2, "1") });
        parameters.add(new Object[] { createNumber(2, "-1"), createNumber(10, "0"), createNumber(2, "-1") });

        parameters.add(new Object[] { createNumber(2, "1"), createNumber(10, "1"), createNumber(2, "1") });
        parameters.add(new Object[] { createNumber(2, "-1"), createNumber(10, "1"), createNumber(2, "-1") });

        parameters.add(new Object[] { createNumber(2, "1"), createNumber(10, "2"), createNumber(2, "1") });
        parameters.add(new Object[] { createNumber(2, "-1"), createNumber(10, "2"), createNumber(2, "-1") });

        parameters.add(new Object[] { createNumber(2, "1.1111"), createNumber(10, "1"), createNumber(2, "10") });
        parameters.add(new Object[] { createNumber(2, "-1.1111"), createNumber(10, "1"), createNumber(2, "-10") });

        parameters.add(new Object[] { createNumber(2, "1.1111"), createNumber(10, "2"), createNumber(2, "10") });
        parameters.add(new Object[] { createNumber(2, "-1.1111"), createNumber(10, "2"), createNumber(2, "-10") });

        parameters.add(new Object[] { createNumber(2, "1.1111"), createNumber(10, "3"), createNumber(2, "1.111") });
        parameters.add(new Object[] { createNumber(2, "-1.1111"), createNumber(10, "3"), createNumber(2, "-1.111") });

        parameters.add(new Object[] { createNumber(2, "1.1111"), createNumber(10, "4"), createNumber(2, "1.1111") });
        parameters.add(new Object[] { createNumber(2, "-1.1111"), createNumber(10, "4"), createNumber(2, "-1.1111") });

        parameters.add(new Object[] { createNumber(2, "1.1111"), createNumber(10, "5"), createNumber(2, "1.1111") });
        parameters.add(new Object[] { createNumber(2, "-1.1111"), createNumber(10, "5"), createNumber(2, "-1.1111") });

        parameters.add(new Object[] { createNumber(2, "1.0001"), createNumber(10, "1"), createNumber(2, "1") });
        parameters.add(new Object[] { createNumber(2, "-1.0001"), createNumber(10, "1"), createNumber(2, "-1") });

        parameters.add(new Object[] { createNumber(2, "1.0001"), createNumber(10, "2"), createNumber(2, "1") });
        parameters.add(new Object[] { createNumber(2, "-1.0001"), createNumber(10, "2"), createNumber(2, "-1") });

        parameters.add(new Object[] { createNumber(2, "1.0001"), createNumber(10, "3"), createNumber(2, "1") });
        parameters.add(new Object[] { createNumber(2, "-1.0001"), createNumber(10, "3"), createNumber(2, "-1") });

        parameters.add(new Object[] { createNumber(2, "1.0001"), createNumber(10, "4"), createNumber(2, "1.0001") });
        parameters.add(new Object[] { createNumber(2, "-1.0001"), createNumber(10, "4"), createNumber(2, "-1.0001") });

        parameters.add(new Object[] { createNumber(2, "1.0001"), createNumber(10, "5"), createNumber(2, "1.0001") });
        parameters.add(new Object[] { createNumber(2, "-1.0001"), createNumber(10, "5"), createNumber(2, "-1.0001") });

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createNumber(base, "1"), createNumber(base, "1"), createNumber(base, "1") });
            parameters.add(new Object[] { createNumber(base, "-1"), createNumber(base, "1"),
                                          createNumber(base, "-1") });

            parameters.add(new Object[] { createNumber(base, "1.1111"), createNumber(base, "1"),
                                          createNumber(base, "1.1") });
            parameters.add(new Object[] { createNumber(base, "-1.1111"), createNumber(base, "1"),
                                          createNumber(base, "-1.1") });

            parameters.add(new Object[] { createNumber(base, "1.0001"), createNumber(base, "1"),
                                          createNumber(base, "1") });
            parameters.add(new Object[] { createNumber(base, "-1.0001"), createNumber(base, "1"),
                                          createNumber(base, "-1") });
        }

        parameters.add(new Object[] { createNumber(10, "9.1"), createNumber(10, "1"), createNumber(10, "9.1") });
        parameters.add(new Object[] { createNumber(10, "-9.1"), createNumber(10, "1"), createNumber(10, "-9.1") });

        parameters.add(new Object[] { createNumber(10, "9.120"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.120"), createNumber(10, "2"), createNumber(10, "-9.12") });

        parameters.add(new Object[] { createNumber(10, "9.121"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.121"), createNumber(10, "2"), createNumber(10, "-9.12") });

        parameters.add(new Object[] { createNumber(10, "9.122"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.122"), createNumber(10, "2"), createNumber(10, "-9.12") });

        parameters.add(new Object[] { createNumber(10, "9.123"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.123"), createNumber(10, "2"), createNumber(10, "-9.12") });

        parameters.add(new Object[] { createNumber(10, "9.124"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.124"), createNumber(10, "2"), createNumber(10, "-9.12") });

        parameters.add(new Object[] { createNumber(10, "9.125"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.125"), createNumber(10, "2"), createNumber(10, "-9.12") });

        parameters.add(new Object[] { createNumber(10, "9.126"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.126"), createNumber(10, "2"), createNumber(10, "-9.12") });

        parameters.add(new Object[] { createNumber(10, "9.127"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.127"), createNumber(10, "2"), createNumber(10, "-9.12") });

        parameters.add(new Object[] { createNumber(10, "9.128"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.128"), createNumber(10, "2"), createNumber(10, "-9.12") });

        parameters.add(new Object[] { createNumber(10, "9.129"), createNumber(10, "2"), createNumber(10, "9.12") });
        parameters.add(new Object[] { createNumber(10, "-9.129"), createNumber(10, "2"), createNumber(10, "-9.12") });

        return parameters;
    }

}

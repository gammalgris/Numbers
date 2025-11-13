/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

import static test.jmul.math.numbers.NumberCheckHelper.checkNumbersAreUniqueInstances;


/**
 * This test suite tests adding two numbers with various combinations of operands.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class AddNumbersTest {

    /**
     * A number.
     */
    private final Number number1;

    /**
     * A number.
     */
    private final Number number2;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number1
     *        a number
     * @param number2
     *        a number
     * @param expectedResult
     *        the expected result
     */
    public AddNumbersTest(Number number1, Number number2, Number expectedResult) {

        super();

        this.number1 = number1;
        this.number2 = number2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a string retpresentation for this test case.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s + [base:%d] %s = [base:%d] %s", number1.base(), number1, number2.base(),
                             number2, expectedResult.base(), expectedResult);
    }

    /**
     * Tests adding the summands and checks the result.
     */
    @Test
    public void testAddition() {

        Number actualResult = number1.add(number2);

        checkNumbersAreUniqueInstances(number1, number2, actualResult);
        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests adding the summands and checks the result.
     */
    @Test
    public void testAdditionVariant2() {

        Number actualResult = Math.add(number1, number2);

        checkNumbersAreUniqueInstances(number1, number2, actualResult);
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

            parameters.add(createTestCase(base, "0", "0", "0"));

            parameters.add(createTestCase(base, "1", "0", "1"));
            parameters.add(createTestCase(base, "0", "1", "1"));

            parameters.add(createTestCase(base, "-1", "0", "-1"));
            parameters.add(createTestCase(base, "0", "-1", "-1"));

            parameters.add(createTestCase(base, "100", "0", "100"));
            parameters.add(createTestCase(base, "0", "100", "100"));

            parameters.add(createTestCase(base, "-100", "0", "-100"));
            parameters.add(createTestCase(base, "0", "-100", "-100"));
        }


        parameters.add(createTestCase(2, "1.1111", "0", "1.1111"));
        parameters.add(createTestCase(2, "0", "1.1111", "1.1111"));

        parameters.add(createTestCase(2, "-1.1111", "0", "-1.1111"));
        parameters.add(createTestCase(2, "0", "-1.1111", "-1.1111"));

        parameters.add(createTestCase(2, "100.1111", "0", "100.1111"));
        parameters.add(createTestCase(2, "0", "100.1111", "100.1111"));

        parameters.add(createTestCase(2, "-100.1111", "0", "-100.1111"));
        parameters.add(createTestCase(2, "0", "-100.1111", "-100.1111"));


        parameters.add(createTestCase(10, "1.2345", "0", "1.2345"));
        parameters.add(createTestCase(10, "0", "1.2345", "1.2345"));

        parameters.add(createTestCase(10, "-1.2345", "0", "-1.2345"));
        parameters.add(createTestCase(10, "0", "-1.2345", "-1.2345"));

        parameters.add(createTestCase(10, "100.2345", "0", "100.2345"));
        parameters.add(createTestCase(10, "0", "100.2345", "100.2345"));

        parameters.add(createTestCase(10, "-100.2345", "0", "-100.2345"));
        parameters.add(createTestCase(10, "0", "-100.2345", "-100.2345"));


        parameters.add(createTestCase(10, "1", "0.5", "1.5"));
        parameters.add(createTestCase(10, "0.5", "1", "1.5"));

        parameters.add(createTestCase(10, "-1", "0.5", "-0.5"));
        parameters.add(createTestCase(10, "0.5", "-1", "-0.5"));

        parameters.add(createTestCase(10, "100", "0.555", "100.555"));
        parameters.add(createTestCase(10, "0.555", "100", "100.555"));

        parameters.add(createTestCase(10, "899.999", "0.555", "900.554")); // check that the carry is added correctly
        parameters.add(createTestCase(10, "-100", "0.555", "-99.445"));
        parameters.add(createTestCase(10, "0.555", "-100", "-99.445"));


        parameters.add(createTestCase(10, "1.2345", "10.98765", "12.22215"));
        parameters.add(createTestCase(10, "10.98765", "1.2345", "12.22215"));

        parameters.add(createTestCase(10, "-1.2345", "10.98765", "9.75315"));
        parameters.add(createTestCase(10, "10.98765", "-1.2345", "9.75315"));

        parameters.add(createTestCase(10, "123.45", "1.098765", "124.548765"));
        parameters.add(createTestCase(10, "1.098765", "123.45", "124.548765"));

        parameters.add(createTestCase(10, "-123.45", "1.098765", "-122.351235"));
        parameters.add(createTestCase(10, "1.098765", "-123.45", "-122.351235"));


        parameters.add(createTestCase(10, "0.5", "0.5", "1"));
        parameters.add(createTestCase(10, "0.12345", "0.98765", "1.1111"));

        parameters.add(createTestCase(10, "1", "1", "2"));
        parameters.add(createTestCase(10, "2", "2", "4"));
        parameters.add(createTestCase(10, "10", "10", "20"));
        parameters.add(createTestCase(10, "123", "123", "246"));

        parameters.add(createTestCase(10, "123456789", "987654321", "1111111110"));
        parameters.add(createTestCase(10, "-123456789", "987654321", "864197532"));
        parameters.add(createTestCase(10, "123456789", "-987654321", "-864197532"));
        parameters.add(createTestCase(10, "-123456789", "-987654321", "-1111111110"));

        parameters.add(createTestCase(10, "11", "1", "12"));
        parameters.add(createTestCase(10, "1", "11", "12"));
        parameters.add(createTestCase(10, "-11", "1", "-10"));
        parameters.add(createTestCase(10, "1", "-11", "-10"));
        parameters.add(createTestCase(10, "11", "-1", "10"));
        parameters.add(createTestCase(10, "-1", "11", "10"));
        parameters.add(createTestCase(10, "-11", "-1", "-12"));
        parameters.add(createTestCase(10, "-1", "-11", "-12"));

        parameters.add(createTestCase(10, "1234567890.0123456789", "0.012345678987654321",
                                      "1234567890.024691357887654321"));
        parameters.add(createTestCase(10, "-1234567890.0123456789", "0.012345678987654321",
                                      "-1234567889.999999999912345679"));
        parameters.add(createTestCase(10, "1234567890.0123456789", "-0.012345678987654321",
                                      "1234567889.999999999912345679"));
        parameters.add(createTestCase(10, "-1234567890.0123456789", "-0.012345678987654321",
                                      "-1234567890.024691357887654321"));

        parameters.add(createTestCase(16, "FF", "11", "110"));

        parameters.add(createTestCase(2, "11", "1", "100"));
        parameters.add(createTestCase(8, "77", "1", "100"));
        parameters.add(createTestCase(10, "99", "1", "100"));
        parameters.add(createTestCase(10, "199", "1", "200"));
        parameters.add(createTestCase(10, "999", "1", "1000"));
        parameters.add(createTestCase(16, "FF", "1", "100"));

        parameters.add(createTestCase(2, "111", "1", "1000"));
        parameters.add(createTestCase(8, "777", "1", "1000"));
        parameters.add(createTestCase(10, "999", "1", "1000"));
        parameters.add(createTestCase(10, "1999", "1", "2000"));
        parameters.add(createTestCase(10, "9999", "1", "10000"));
        parameters.add(createTestCase(16, "FFF", "1", "1000"));

        return parameters;
    }

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param numberString1
     *        a number string
     * @param numberString2
     *        a number string
     * @param resultString
     *        a number string
     *
     * @return an object array
     */
    private static Object[] createTestCase(int base, String numberString1, String numberString2, String resultString) {

        return new Object[] {
               createNumber(base, numberString1), createNumber(base, numberString2), createNumber(base, resultString) };
    }

}

/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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
import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.FailedTestException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static test.jmul.math.numbers.NumberCheckHelper.checkNumberEqualsStringRepresentation;
import static test.jmul.math.numbers.NumberCheckHelper.checkNumbersAreUniqueInstances;


/**
 * This test suite tests halving numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class HalvingNumberTest {

    /**
     * The base for all summands.
     */
    private final int base;

    /**
     * The operand as number string.
     */
    private final String operandString;

    /**
     * The operand parsed from the specified number string.
     */
    private Number operand;

    /**
     * The expected sum as number string.
     */
    private final String resultString;

    /**
     * The expected sum parsed from the specified number string.
     */
    private Number result;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param base
     *        the base for all numbers
     * @param operandString
     *        the operand as number string
     * @param resultString
     *        the expected result as number string
     */
    public HalvingNumberTest(int base, String operandString, String resultString) {

        super();

        this.base = base;
        this.operandString = operandString;
        this.resultString = resultString;
    }

    /**
     * Parses the number strings before the actual test.
     */
    @Before
    public void setUp() {

        operand = createNumber(base, operandString);
        result = createNumber(base, resultString);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        operand = null;
        result = null;
    }

    /**
     * Tests halving a number and checks the result.
     */
    @Test
    public void testHalvingNumber() {

        try {

            // check that the operands and the expected result are built correctly
            if (operand.isZero()) {

                assertTrue("Zero cannot be negative!", operand.isPositive());

            } else {

                checkNumberEqualsStringRepresentation(operand, operandString);
            }
            checkNumberEqualsStringRepresentation(result, resultString);

            // check the operation
            Number actualResult = operand.halving();

            assertEquals(toString(), result, actualResult);

            // check the number instances
            checkNumbersAreUniqueInstances(operand, actualResult);

            // check that the operands didn't change
            if (operand.isZero()) {

                assertTrue("Zero cannot be negative!", operand.isPositive());

            } else {

                checkNumberEqualsStringRepresentation(operand, operandString);
            }

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Tests halving a number and checks the result.
     */
    @Test
    public void testHalvingNumberVariant2() {

        try {

            // check that the operands and the expected result are built correctly
            if (operand.isZero()) {

                assertTrue("Zero cannot be negative!", operand.isPositive());

            } else {

                checkNumberEqualsStringRepresentation(operand, operandString);
            }
            checkNumberEqualsStringRepresentation(result, resultString);

            // check the operation
            Number actualResult = Math.halving(operand);

            assertEquals(toString(), result, actualResult);

            // check the number instances
            checkNumbersAreUniqueInstances(operand, actualResult);

            // check that the operands didn't change
            if (operand.isZero()) {

                assertTrue("Zero cannot be negative!", operand.isPositive());

            } else {

                checkNumberEqualsStringRepresentation(operand, operandString);
            }

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Returns a summary of this test case (i.e. the operation with its operands)
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] halving %s = %s", base, operandString, resultString);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 2, "0", "0" });
        parameters.add(new Object[] { 2, "-0", "0" });

        parameters.add(new Object[] { 2, "1", "0.1" });
        parameters.add(new Object[] { 2, "-1", "-0.1" });

        parameters.add(new Object[] { 2, "100", "10" });
        parameters.add(new Object[] { 2, "-100", "-10" });

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base, "0", "0" });
            parameters.add(new Object[] { base, "-0", "0" });

            int half = base / 2;
            boolean odd = (base % 2) != 0;
            Digit digit = PositionalNumeralSystems.ordinalToDigit(base, half);

            parameters.add(new Object[] { base, "1", "0." + digit });
            parameters.add(new Object[] { base, "-1", "-0." + digit });

            if (odd) {

                parameters.add(new Object[] { base, "100", "" + digit + "" + digit + "." + digit });
                parameters.add(new Object[] { base, "-100", "-" + digit + "" + digit + "." + digit });

            } else {

                parameters.add(new Object[] { base, "100", "" + digit + "0" });
                parameters.add(new Object[] { base, "-100", "-" + digit + "0" });
            }
        }


        parameters.add(new Object[] { 2, "1.1111", "0.11111" });
        parameters.add(new Object[] { 2, "-1.1111", "-0.11111" });

        parameters.add(new Object[] { 2, "100.1111", "10.01111" });
        parameters.add(new Object[] { 2, "-100.1111", "-10.01111" });


        parameters.add(new Object[] { 10, "2.469", "1.2345" });
        parameters.add(new Object[] { 10, "-2.469", "-1.2345" });

        parameters.add(new Object[] { 10, "200.469", "100.2345" });
        parameters.add(new Object[] { 10, "-200.469", "-100.2345" });


        parameters.add(new Object[] { 10, "1", "0.5" });
        parameters.add(new Object[] { 10, "-1", "-0.5" });

        parameters.add(new Object[] { 10, "1.11", "0.555" });
        parameters.add(new Object[] { 10, "-1.11", "-0.555" });

        parameters.add(new Object[] { 10, "1799.998", "899.999" });
        parameters.add(new Object[] { 10, "-1799.998", "-899.999" });

        parameters.add(new Object[] { 10, "21.9753", "10.98765" });
        parameters.add(new Object[] { 10, "-21.9753", "-10.98765" });

        parameters.add(new Object[] { 10, "246.9", "123.45" });
        parameters.add(new Object[] { 10, "-246.9", "-123.45" });

        parameters.add(new Object[] { 10, "2.19753", "1.098765" });
        parameters.add(new Object[] { 10, "-2.19753", "-1.098765" });

        parameters.add(new Object[] { 10, "4", "2" });
        parameters.add(new Object[] { 10, "5", "2.5" });
        parameters.add(new Object[] { 10, "20", "10" });
        parameters.add(new Object[] { 10, "21", "10.5" });
        parameters.add(new Object[] { 10, "246", "123" });
        parameters.add(new Object[] { 10, "247", "123.5" });

        parameters.add(new Object[] { 10, "246913578", "123456789" });
        parameters.add(new Object[] { 10, "-246913578", "-123456789" });
        parameters.add(new Object[] { 10, "1975308642", "987654321" });
        parameters.add(new Object[] { 10, "-1975308642", "-987654321" });
        parameters.add(new Object[] { 10, "3950617285728395064", "1975308642864197532" });
        parameters.add(new Object[] { 10, "-3950617285728395064", "-1975308642864197532" });

        parameters.add(new Object[] { 10, "2469135780.0246913578", "1234567890.0123456789" });
        parameters.add(new Object[] { 10, "-2469135780.0246913578", "-1234567890.0123456789" });
        parameters.add(new Object[] { 10, "0.024691357975308642", "0.012345678987654321" });
        parameters.add(new Object[] { 10, "-0.024691357975308642", "-0.012345678987654321" });

        parameters.add(new Object[] { 16, "1FE", "FF" });
        parameters.add(new Object[] { 16, "22", "11" });

        parameters.add(new Object[] { 10, "122", "61" });

        parameters.add(new Object[] { 3, "0", "0" });
        parameters.add(new Object[] { 3, "1", "0.1" });
        parameters.add(new Object[] { 3, "2", "1" });
        parameters.add(new Object[] { 3, "10", "1.1" });
        parameters.add(new Object[] { 3, "11", "2" });
        parameters.add(new Object[] { 3, "12", "2.1" });
        parameters.add(new Object[] { 3, "20", "10" });
        parameters.add(new Object[] { 3, "21", "10.1" });
        parameters.add(new Object[] { 3, "22", "11" });
        parameters.add(new Object[] { 3, "100", "11.1" });
        parameters.add(new Object[] { 3, "101", "12" });
        parameters.add(new Object[] { 3, "102", "12.1" });
        parameters.add(new Object[] { 3, "110", "20" });
        parameters.add(new Object[] { 3, "111", "20.1" });
        parameters.add(new Object[] { 3, "112", "21" });
        parameters.add(new Object[] { 3, "120", "21.1" });
        parameters.add(new Object[] { 3, "121", "22" });
        parameters.add(new Object[] { 3, "122", "22.1" });
        parameters.add(new Object[] { 3, "200", "100" });
        parameters.add(new Object[] { 3, "201", "100.1" });
        parameters.add(new Object[] { 3, "202", "101" });
        parameters.add(new Object[] { 3, "210", "101.1" });
        parameters.add(new Object[] { 3, "211", "102" });
        parameters.add(new Object[] { 3, "212", "102.1" });
        parameters.add(new Object[] { 3, "220", "110" });
        parameters.add(new Object[] { 3, "221", "110.1" });
        parameters.add(new Object[] { 3, "222", "111" });
        parameters.add(new Object[] { 3, "1000", "111.1" });
        parameters.add(new Object[] { 3, "1001", "112" });
        parameters.add(new Object[] { 3, "1002", "112.1" });
        parameters.add(new Object[] { 3, "1010", "120" });
        parameters.add(new Object[] { 3, "1011", "120.1" });
        parameters.add(new Object[] { 3, "1012", "121" });
        parameters.add(new Object[] { 3, "1020", "121.1" });
        parameters.add(new Object[] { 3, "1021", "122" });

        parameters.add(new Object[] { 4, "12", "3" });
        parameters.add(new Object[] { 4, "13", "3.2" });

        parameters.add(new Object[] { 5, "0", "0" });
        parameters.add(new Object[] { 5, "1", "0.2" });
        parameters.add(new Object[] { 5, "2", "1" });
        parameters.add(new Object[] { 5, "3", "1.2" });
        parameters.add(new Object[] { 5, "4", "2" });
        parameters.add(new Object[] { 5, "10", "2.2" });
        parameters.add(new Object[] { 5, "11", "3" });
        parameters.add(new Object[] { 5, "12", "3.2" });
        parameters.add(new Object[] { 5, "13", "4" });
        parameters.add(new Object[] { 5, "14", "4.2" });
        parameters.add(new Object[] { 5, "20", "10" });
        parameters.add(new Object[] { 5, "21", "10.2" });
        parameters.add(new Object[] { 5, "22", "11" });
        parameters.add(new Object[] { 5, "23", "11.2" });
        parameters.add(new Object[] { 5, "24", "12" });
        parameters.add(new Object[] { 5, "30", "12.2" });
        parameters.add(new Object[] { 5, "31", "13" });
        parameters.add(new Object[] { 5, "32", "13.2" });
        parameters.add(new Object[] { 5, "33", "14" });
        parameters.add(new Object[] { 5, "34", "14.2" });
        parameters.add(new Object[] { 5, "40", "20" });
        parameters.add(new Object[] { 5, "41", "20.2" });
        parameters.add(new Object[] { 5, "42", "21" });
        parameters.add(new Object[] { 5, "43", "21.2" });
        parameters.add(new Object[] { 5, "44", "22" });
        parameters.add(new Object[] { 5, "100", "22.2" });
        parameters.add(new Object[] { 5, "101", "23" });
        parameters.add(new Object[] { 5, "102", "23.2" });
        parameters.add(new Object[] { 5, "103", "24" });
        parameters.add(new Object[] { 5, "104", "24.2" });
        parameters.add(new Object[] { 5, "110", "30" });

        return parameters;
    }

}

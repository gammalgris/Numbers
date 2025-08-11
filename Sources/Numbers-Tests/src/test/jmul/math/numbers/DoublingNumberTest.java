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

import static org.junit.Assert.assertTrue;

import jmul.math.Math;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.FailedTestException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static test.jmul.math.numbers.NumberCheckHelper.checkNumberEqualsStringRepresentation;
import static test.jmul.math.numbers.NumberCheckHelper.checkNumbersAreUniqueInstances;


/**
 * This test suite tests doubling numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DoublingNumberTest {

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
    public DoublingNumberTest(int base, String operandString, String resultString) {

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

        operand = new NumberImpl(base, operandString);
        result = new NumberImpl(base, resultString);
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
     * Tests doubling a number and checks the result.
     */
    @Test
    public void testDoublingNumber() {

        try {

            // check that the operands and the expected result are built correctly
            if (operand.isZero()) {

                assertTrue("Zero cannot be negative!", operand.isPositive());

            } else {

                checkNumberEqualsStringRepresentation(operand, operandString);
            }
            checkNumberEqualsStringRepresentation(result, resultString);

            // check the operation
            Number actualResult = operand.doubling();

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
     * Tests doubling a number and checks the result.
     */
    @Test
    public void testDoublingNumberVariant2() {

        try {

            // check that the operands and the expected result are built correctly
            if (operand.isZero()) {

                assertTrue("Zero cannot be negative!", operand.isPositive());

            } else {

                checkNumberEqualsStringRepresentation(operand, operandString);
            }
            checkNumberEqualsStringRepresentation(result, resultString);

            // check the operation
            Number actualResult = Math.doubling(operand);

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

        return String.format("[base:%d] doubling %s = %s", base, operandString, resultString);
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

        parameters.add(new Object[] { 2, "1", "10" });
        parameters.add(new Object[] { 2, "-1", "-10" });

        parameters.add(new Object[] { 2, "100", "1000" });
        parameters.add(new Object[] { 2, "-100", "-1000" });

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base, "0", "0" });
            parameters.add(new Object[] { base, "-0", "0" });

            parameters.add(new Object[] { base, "1", "2" });
            parameters.add(new Object[] { base, "-1", "-2" });

            parameters.add(new Object[] { base, "100", "200" });
            parameters.add(new Object[] { base, "-100", "-200" });
        }


        parameters.add(new Object[] { 2, "1.1111", "11.111" });
        parameters.add(new Object[] { 2, "-1.1111", "-11.111" });

        parameters.add(new Object[] { 2, "100.1111", "1001.111" });
        parameters.add(new Object[] { 2, "-100.1111", "-1001.111" });


        parameters.add(new Object[] { 10, "1.2345", "2.469" });
        parameters.add(new Object[] { 10, "-1.2345", "-2.469" });

        parameters.add(new Object[] { 10, "100.2345", "200.469" });
        parameters.add(new Object[] { 10, "-100.2345", "-200.469" });


        parameters.add(new Object[] { 10, "0.5", "1" });
        parameters.add(new Object[] { 10, "-0.5", "-1" });

        parameters.add(new Object[] { 10, "0.555", "1.11" });
        parameters.add(new Object[] { 10, "-0.555", "-1.11" });

        parameters.add(new Object[] { 10, "899.999", "1799.998" });
        parameters.add(new Object[] { 10, "-899.999", "-1799.998" });

        parameters.add(new Object[] { 10, "10.98765", "21.9753" });
        parameters.add(new Object[] { 10, "-10.98765", "-21.9753" });

        parameters.add(new Object[] { 10, "123.45", "246.9" });
        parameters.add(new Object[] { 10, "-123.45", "-246.9" });

        parameters.add(new Object[] { 10, "1.098765", "2.19753" });
        parameters.add(new Object[] { 10, "-1.098765", "-2.19753" });

        parameters.add(new Object[] { 10, "2", "4" });
        parameters.add(new Object[] { 10, "10", "20" });
        parameters.add(new Object[] { 10, "123", "246" });

        parameters.add(new Object[] { 10, "123456789", "246913578" });
        parameters.add(new Object[] { 10, "-123456789", "-246913578" });
        parameters.add(new Object[] { 10, "987654321", "1975308642" });
        parameters.add(new Object[] { 10, "-987654321", "-1975308642" });
        parameters.add(new Object[] { 10, "1975308642864197532", "3950617285728395064" });
        parameters.add(new Object[] { 10, "-1975308642864197532", "-3950617285728395064" });

        parameters.add(new Object[] { 10, "1234567890.0123456789", "2469135780.0246913578" });
        parameters.add(new Object[] { 10, "-1234567890.0123456789", "-2469135780.0246913578" });
        parameters.add(new Object[] { 10, "0.012345678987654321", "0.024691357975308642" });
        parameters.add(new Object[] { 10, "-0.012345678987654321", "-0.024691357975308642" });

        parameters.add(new Object[] { 16, "FF", "1FE" });
        parameters.add(new Object[] { 16, "11", "22" });

        parameters.add(new Object[] { 3, "22", "121" });
        parameters.add(new Object[] { 3, "11", "22" });
        parameters.add(new Object[] { 3, "2", "11" });
        parameters.add(new Object[] { 3, "1", "2" });

        parameters.add(new Object[] { 5, "13", "31" });
        parameters.add(new Object[] { 5, "4", "13" });
        parameters.add(new Object[] { 5, "2", "4" });
        parameters.add(new Object[] { 5, "1", "2" });

        return parameters;
    }

}

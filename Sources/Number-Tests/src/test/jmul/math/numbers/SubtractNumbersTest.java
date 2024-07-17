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
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.FailedTestException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests subtracting two numbers with various combinations of operands.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SubtractNumbersTest {

    /**
     * The base for all summands.
     */
    private final int base;

    /**
     * The minuend as number string.
     */
    private final String minuendString;

    /**
     * The minuend parsed from the specified number string.
     */
    private Number minuend;

    /**
     * The subtrahend as number string.
     */
    private final String subtrahendString;

    /**
     * The subtrahend parsed from the specified number string,
     */
    private Number subtrahend;

    /**
     * The expected difference as number string.
     */
    private final String differenceString;

    /**
     * The expected difference parsed from the specified number string.
     */
    private Number difference;

    /**
     * Creates a new test according to the specified parmaeters.
     *
     * @param base
     *        the base for all numbers
     * @param minuendString
     *        the first summand as number string
     * @param subtrahendString
     *        the second summand as number string
     * @param differenceString
     *        the sum as number string
     */
    public SubtractNumbersTest(int base, String minuendString, String subtrahendString, String differenceString) {

        super();

        this.base = base;
        this.minuendString = minuendString;
        this.subtrahendString = subtrahendString;
        this.differenceString = differenceString;
    }

    /**
     * Parses the specified number strings before the actual test.
     */
    @Before
    public void setUp() {

        minuend = new NumberImpl(base, minuendString);
        subtrahend = new NumberImpl(base, subtrahendString);
        difference = new NumberImpl(base, differenceString);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        minuend = null;
        subtrahend = null;
        difference = null;
    }

    /**
     * Tests adding the summands and checks the result.
     */
    @Test
    public void testSubtraction() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(minuend, minuendString);
            checkNumberEqualsStringRepresentation(subtrahend, subtrahendString);
            checkNumberEqualsStringRepresentation(difference, differenceString);

            // check the operation
            Number actualDifference = minuend.subtract(subtrahend);

            String message = String.format("%s - %s = %s", minuendString, subtrahendString, differenceString);
            assertEquals(message, difference, actualDifference);

            // check the number instances
            checkNumbersAreUniqueInstances(minuend, subtrahend, difference);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(minuend, minuendString);
            checkNumberEqualsStringRepresentation(subtrahend, subtrahendString);

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Tests adding the summands and checks the result.
     */
    @Test
    public void testSubtractionVariant2() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(minuend, minuendString);
            checkNumberEqualsStringRepresentation(subtrahend, subtrahendString);
            checkNumberEqualsStringRepresentation(difference, differenceString);

            // check the operation
            Number actualDifference = Math.subtract(minuend, subtrahend);

            String message = String.format("%s - %s = %s", minuendString, subtrahendString, differenceString);
            assertEquals(message, difference, actualDifference);

            // check the number instances
            checkNumbersAreUniqueInstances(minuend, subtrahend, difference);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(minuend, minuendString);
            checkNumberEqualsStringRepresentation(subtrahend, subtrahendString);

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Checks that the specified operands and the result are unique number instances (i.e. linked lists).
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     * @param result
     *        the operation result
     */
    private static void checkNumbersAreUniqueInstances(Number operand1, Number operand2, Number result) {

        assertFalse(operand1 == operand2);
        assertFalse(operand1 == result);
        assertFalse(operand2 == result);

        assertFalse(operand1.centerNode() == operand2.centerNode());
        assertFalse(operand1.centerNode() == result.centerNode());
        assertFalse(operand2.centerNode() == result.centerNode());
    }

    /**
     * Compare the specified number with the specified string representation of a number. If these
     * don't match then an assertion fails.
     *
     * @param number
     *        a number
     * @param stringRepresentation
     *        the string representation which should match the specified number
     */
    private static void checkNumberEqualsStringRepresentation(Number number, String stringRepresentation) {

        assertEquals(stringRepresentation, number.toString());
    }

    /**
     * Returns a summary of this test case (i.e. the operation with its operands)
     *
     * @return a summary
     */
    @Override
    public String toString() {

        String summary = String.format("[base:%d] %s - %s", base, minuendString, subtrahendString);
        return summary;
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = 2; base <= 64; base++) {

            parameters.add(new Object[] { base, "0", "0", "0" });

            parameters.add(new Object[] { base, "1", "0", "1" });
            parameters.add(new Object[] { base, "0", "1", "-1" });

            parameters.add(new Object[] { base, "-1", "0", "-1" });
            parameters.add(new Object[] { base, "0", "-1", "1" });

            parameters.add(new Object[] { base, "100", "0", "100" });
            parameters.add(new Object[] { base, "0", "100", "-100" });

            parameters.add(new Object[] { base, "-100", "0", "-100" });
            parameters.add(new Object[] { base, "0", "-100", "100" });
        }


        parameters.add(new Object[] { 2, "1.1111", "0", "1.1111" });
        parameters.add(new Object[] { 2, "0", "1.1111", "-1.1111" });

        parameters.add(new Object[] { 2, "-1.1111", "0", "-1.1111" });
        parameters.add(new Object[] { 2, "0", "-1.1111", "1.1111" });

        parameters.add(new Object[] { 2, "100.1111", "0", "100.1111" });
        parameters.add(new Object[] { 2, "0", "100.1111", "-100.1111" });

        parameters.add(new Object[] { 2, "-100.1111", "0", "-100.1111" });
        parameters.add(new Object[] { 2, "0", "-100.1111", "100.1111" });


        parameters.add(new Object[] { 10, "1.2345", "0", "1.2345" });
        parameters.add(new Object[] { 10, "0", "1.2345", "-1.2345" });

        parameters.add(new Object[] { 10, "-1.2345", "0", "-1.2345" });
        parameters.add(new Object[] { 10, "0", "-1.2345", "1.2345" });

        parameters.add(new Object[] { 10, "100.2345", "0", "100.2345" });
        parameters.add(new Object[] { 10, "0", "100.2345", "-100.2345" });

        parameters.add(new Object[] { 10, "-100.2345", "0", "-100.2345" });
        parameters.add(new Object[] { 10, "0", "-100.2345", "100.2345" });


        parameters.add(new Object[] { 10, "1", "0.5", "0.5" });
        parameters.add(new Object[] { 10, "0.5", "1", "-0.5" });

        parameters.add(new Object[] { 10, "-1", "0.5", "-1.5" });
        parameters.add(new Object[] { 10, "0.5", "-1", "1.5" });

        parameters.add(new Object[] { 10, "100", "0.555", "99.445" });
        parameters.add(new Object[] { 10, "0.555", "100", "-99.445" });

        parameters.add(new Object[] { 10, "899.999", "0.555", "899.444" });
        parameters.add(new Object[] { 10, "-100", "0.555", "-100.555" });
        parameters.add(new Object[] { 10, "0.555", "-100", "100.555" });


        parameters.add(new Object[] { 10, "1.2345", "10.98765", "-9.75315" });
        parameters.add(new Object[] { 10, "10.98765", "1.2345", "9.75315" });

        parameters.add(new Object[] { 10, "-1.2345", "10.98765", "-12.22215" });
        parameters.add(new Object[] { 10, "10.98765", "-1.2345", "12.22215" });

        parameters.add(new Object[] { 10, "123.45", "1.098765", "122.351235" });
        parameters.add(new Object[] { 10, "1.098765", "123.45", "-122.351235" });

        parameters.add(new Object[] { 10, "-123.45", "1.098765", "-124.548765" });
        parameters.add(new Object[] { 10, "1.098765", "-123.45", "124.548765" });


        parameters.add(new Object[] { 10, "0.5", "0.5", "0" });
        parameters.add(new Object[] { 10, "0.12345", "0.98765", "-0.8642" });

        parameters.add(new Object[] { 10, "1", "1", "0" });
        parameters.add(new Object[] { 10, "2", "2", "0" });
        parameters.add(new Object[] { 10, "10", "10", "0" });
        parameters.add(new Object[] { 10, "123", "123", "0" });

        parameters.add(new Object[] { 10, "123456789", "987654321", "-864197532" });
        parameters.add(new Object[] { 10, "-123456789", "987654321", "-1111111110" });
        parameters.add(new Object[] { 10, "123456789", "-987654321", "1111111110" });
        parameters.add(new Object[] { 10, "-123456789", "-987654321", "864197532" });

        parameters.add(new Object[] { 10, "11", "1", "10" });
        parameters.add(new Object[] { 10, "1", "11", "-10" });
        parameters.add(new Object[] { 10, "-11", "1", "-12" });
        parameters.add(new Object[] { 10, "1", "-11", "12" });
        parameters.add(new Object[] { 10, "11", "-1", "12" });
        parameters.add(new Object[] { 10, "-1", "11", "-12" });
        parameters.add(new Object[] { 10, "-11", "-1", "-10" });
        parameters.add(new Object[] { 10, "-1", "-11", "10" });

        parameters.add(new Object[] { 10, "1234567890.0123456789", "0.012345678987654321",
                                      "1234567889.99999999991234567" });
        parameters.add(new Object[] { 10, "-1234567890.0123456789", "0.012345678987654321",
                                      "-1234567890.024691357887654321" });
        parameters.add(new Object[] { 10, "1234567890.0123456789", "-0.012345678987654321",
                                      "1234567890.024691357887654321" });
        parameters.add(new Object[] { 10, "-1234567890.0123456789", "-0.012345678987654321",
                                      "-1234567889.99999999991234567" });

        parameters.add(new Object[] { 16, "FF", "11", "EE" });

        return parameters;
    }

}

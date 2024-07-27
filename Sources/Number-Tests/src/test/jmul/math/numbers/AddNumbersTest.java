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
 * This test suite tests adding two numbers with various combinations of operands.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class AddNumbersTest {

    /**
     * The base for all summands.
     */
    private final int base;

    /**
     * The first summand as number string.
     */
    private final String firstSummandString;

    /**
     * The first summand parsed from the specified number string.
     */
    private Number firstSummand;

    /**
     * The second summand as number string.
     */
    private final String secondSummandString;

    /**
     * The second summand parsed from the specified number string,
     */
    private Number secondSummand;

    /**
     * The expected sum as number string.
     */
    private final String sumString;

    /**
     * The expected sum parsed from the specified number string.
     */
    private Number sum;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param base
     *        the base for all numbers
     * @param firstSummandString
     *        the first summand as number string
     * @param secondSummandString
     *        the second summand as number string
     * @param sumString
     *        the sum as number string
     */
    public AddNumbersTest(int base, String firstSummandString, String secondSummandString, String sumString) {

        super();

        this.base = base;
        this.firstSummandString = firstSummandString;
        this.secondSummandString = secondSummandString;
        this.sumString = sumString;
    }

    /**
     * Parses the number strings before the actual test.
     */
    @Before
    public void setUp() {

        firstSummand = new NumberImpl(base, firstSummandString);
        secondSummand = new NumberImpl(base, secondSummandString);
        sum = new NumberImpl(base, sumString);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        firstSummand = null;
        secondSummand = null;
        sum = null;
    }

    /**
     * Tests adding the summands and checks the result.
     */
    @Test
    public void testAddition() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(firstSummand, firstSummandString);
            checkNumberEqualsStringRepresentation(secondSummand, secondSummandString);
            checkNumberEqualsStringRepresentation(sum, sumString);

            // check the operation
            Number actualResult = firstSummand.add(secondSummand);

            String message = String.format("%s + %s = %s", firstSummandString, secondSummandString, sumString);
            assertEquals(message, sum, actualResult);

            // check the number instances
            checkNumbersAreUniqueInstances(firstSummand, secondSummand, actualResult);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(firstSummand, firstSummandString);
            checkNumberEqualsStringRepresentation(secondSummand, secondSummandString);

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Tests adding the summands and checks the result.
     */
    @Test
    public void testAdditionVariant2() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(firstSummand, firstSummandString);
            checkNumberEqualsStringRepresentation(secondSummand, secondSummandString);
            checkNumberEqualsStringRepresentation(sum, sumString);

            // check the operation
            Number actualResult = Math.add(firstSummand, secondSummand);

            String message = String.format("%s + %s = %s", firstSummandString, secondSummandString, sumString);
            assertEquals(message, sum, actualResult);

            // check the number instances
            checkNumbersAreUniqueInstances(firstSummand, secondSummand, actualResult);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(firstSummand, firstSummandString);
            checkNumberEqualsStringRepresentation(secondSummand, secondSummandString);

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

        String summary = String.format("[base:%d] %s + %s", base, firstSummandString, secondSummandString);
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

        for (int base = 2; base <= 65; base++) {

            parameters.add(new Object[] { base, "0", "0", "0" });

            parameters.add(new Object[] { base, "1", "0", "1" });
            parameters.add(new Object[] { base, "0", "1", "1" });

            parameters.add(new Object[] { base, "-1", "0", "-1" });
            parameters.add(new Object[] { base, "0", "-1", "-1" });

            parameters.add(new Object[] { base, "100", "0", "100" });
            parameters.add(new Object[] { base, "0", "100", "100" });

            parameters.add(new Object[] { base, "-100", "0", "-100" });
            parameters.add(new Object[] { base, "0", "-100", "-100" });
        }


        parameters.add(new Object[] { 2, "1.1111", "0", "1.1111" });
        parameters.add(new Object[] { 2, "0", "1.1111", "1.1111" });

        parameters.add(new Object[] { 2, "-1.1111", "0", "-1.1111" });
        parameters.add(new Object[] { 2, "0", "-1.1111", "-1.1111" });

        parameters.add(new Object[] { 2, "100.1111", "0", "100.1111" });
        parameters.add(new Object[] { 2, "0", "100.1111", "100.1111" });

        parameters.add(new Object[] { 2, "-100.1111", "0", "-100.1111" });
        parameters.add(new Object[] { 2, "0", "-100.1111", "-100.1111" });


        parameters.add(new Object[] { 10, "1.2345", "0", "1.2345" });
        parameters.add(new Object[] { 10, "0", "1.2345", "1.2345" });

        parameters.add(new Object[] { 10, "-1.2345", "0", "-1.2345" });
        parameters.add(new Object[] { 10, "0", "-1.2345", "-1.2345" });

        parameters.add(new Object[] { 10, "100.2345", "0", "100.2345" });
        parameters.add(new Object[] { 10, "0", "100.2345", "100.2345" });

        parameters.add(new Object[] { 10, "-100.2345", "0", "-100.2345" });
        parameters.add(new Object[] { 10, "0", "-100.2345", "-100.2345" });


        parameters.add(new Object[] { 10, "1", "0.5", "1.5" });
        parameters.add(new Object[] { 10, "0.5", "1", "1.5" });

        parameters.add(new Object[] { 10, "-1", "0.5", "-0.5" });
        parameters.add(new Object[] { 10, "0.5", "-1", "-0.5" });

        parameters.add(new Object[] { 10, "100", "0.555", "100.555" });
        parameters.add(new Object[] { 10, "0.555", "100", "100.555" });

        parameters.add(new Object[] { 10, "899.999", "0.555", "900.554" }); // check that the carry is added correctly
        parameters.add(new Object[] { 10, "-100", "0.555", "-99.445" });
        parameters.add(new Object[] { 10, "0.555", "-100", "-99.445" });


        parameters.add(new Object[] { 10, "1.2345", "10.98765", "12.22215" });
        parameters.add(new Object[] { 10, "10.98765", "1.2345", "12.22215" });

        parameters.add(new Object[] { 10, "-1.2345", "10.98765", "9.75315" });
        parameters.add(new Object[] { 10, "10.98765", "-1.2345", "9.75315" });

        parameters.add(new Object[] { 10, "123.45", "1.098765", "124.548765" });
        parameters.add(new Object[] { 10, "1.098765", "123.45", "124.548765" });

        parameters.add(new Object[] { 10, "-123.45", "1.098765", "-122.351235" });
        parameters.add(new Object[] { 10, "1.098765", "-123.45", "-122.351235" });


        parameters.add(new Object[] { 10, "0.5", "0.5", "1" });
        parameters.add(new Object[] { 10, "0.12345", "0.98765", "1.1111" });

        parameters.add(new Object[] { 10, "1", "1", "2" });
        parameters.add(new Object[] { 10, "2", "2", "4" });
        parameters.add(new Object[] { 10, "10", "10", "20" });
        parameters.add(new Object[] { 10, "123", "123", "246" });

        parameters.add(new Object[] { 10, "123456789", "987654321", "1111111110" });
        parameters.add(new Object[] { 10, "-123456789", "987654321", "864197532" });
        parameters.add(new Object[] { 10, "123456789", "-987654321", "-864197532" });
        parameters.add(new Object[] { 10, "-123456789", "-987654321", "-1111111110" });

        parameters.add(new Object[] { 10, "11", "1", "12" });
        parameters.add(new Object[] { 10, "1", "11", "12" });
        parameters.add(new Object[] { 10, "-11", "1", "-10" });
        parameters.add(new Object[] { 10, "1", "-11", "-10" });
        parameters.add(new Object[] { 10, "11", "-1", "10" });
        parameters.add(new Object[] { 10, "-1", "11", "10" });
        parameters.add(new Object[] { 10, "-11", "-1", "-12" });
        parameters.add(new Object[] { 10, "-1", "-11", "-12" });

        parameters.add(new Object[] { 10, "1234567890.0123456789", "0.012345678987654321",
                                      "1234567890.024691357887654321" });
        parameters.add(new Object[] { 10, "-1234567890.0123456789", "0.012345678987654321",
                                      "-1234567889.999999999912345679" });
        parameters.add(new Object[] { 10, "1234567890.0123456789", "-0.012345678987654321",
                                      "1234567889.999999999912345679" });
        parameters.add(new Object[] { 10, "-1234567890.0123456789", "-0.012345678987654321",
                                      "-1234567890.024691357887654321" });

        parameters.add(new Object[] { 16, "FF", "11", "110" });

        return parameters;
    }

}

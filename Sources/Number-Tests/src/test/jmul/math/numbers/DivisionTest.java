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

import jmul.math.Constants;
import jmul.math.Math;
import jmul.math.fraction.Fraction;
import static jmul.math.fraction.FractionHelper.createFraction;
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


/**
 * This test suite tests the division.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DivisionTest {

    /**
     * The base for all operands.
     */
    private final int base;

    /**
     * The dividend as number string.
     */
    private final String dividendString;

    /**
     * The dividend parsed from the specified number string.
     */
    private Number dividend;

    /**
     * The divisor as number string.
     */
    private final String divisorString;

    /**
     * The divisor parsed from the specified number string,
     */
    private Number divisor;

    /**
     * The expected result.
     */
    private final Fraction expectedResult;

    /**
     * Creates a new test case accordign to the specified parameters.
     *
     * @param base
     *        a number base
     * @param dividendString
     *        the dividend as number string
     * @param divisorString
     *        the divisor as number string
     * @param expectedResult
     *        the expected result
     */
    public DivisionTest(int base, String dividendString, String divisorString, Fraction expectedResult) {

        super();

        this.base = base;
        this.dividendString = dividendString;
        this.divisorString = divisorString;
        this.expectedResult = expectedResult;
    }

    /**
     * Parses the number strings before the actual test.
     */
    @Before
    public void setUp() {

        dividend = new NumberImpl(base, dividendString);
        divisor = new NumberImpl(base, divisorString);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        dividend = null;
        divisor = null;
    }

    /**
     * Returns a summary of this test case (i.e. the operation with its operands)
     *
     * @return a summary
     */
    @Override
    public String toString() {

        String summary = String.format("[base:%d] %s / %s", base, dividend, divisor);
        return summary;
    }

    /**
     * Performs a division and checks the result.
     */
    @Test
    public void testDivision() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(dividend, dividendString);
            checkNumberEqualsStringRepresentation(divisor, divisorString);

            // check the operation
            Fraction actualResult = dividend.divide(divisor);

            String message = String.format("%s / %s = %s", dividendString, divisorString, expectedResult);
            assertEquals(message, expectedResult, actualResult);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(dividend, dividendString);
            checkNumberEqualsStringRepresentation(divisor, divisorString);

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Performs a division and checks the result.
     */
    @Test
    public void testDivisionVariant2() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(dividend, dividendString);
            checkNumberEqualsStringRepresentation(divisor, divisorString);

            // check the operation
            Fraction actualResult = Math.divide(dividend, divisor);

            String message = String.format("%s / %s = %s", dividendString, divisorString, expectedResult);
            assertEquals(message, expectedResult, actualResult);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(dividend, dividendString);
            checkNumberEqualsStringRepresentation(divisor, divisorString);

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 10, "10", "3", createFraction(10, "3", "1", "3") });

        for (int base = Constants.MIN_BASE; base <= Constants.MAX_BASE; base++) {

            //TODO
        }

        return parameters;
    }

}

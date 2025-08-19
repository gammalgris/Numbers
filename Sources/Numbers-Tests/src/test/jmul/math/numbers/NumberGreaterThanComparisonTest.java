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
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

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
 * This test suite compares two numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NumberGreaterThanComparisonTest {

    /**
     * The base for all summands.
     */
    private final int base;

    /**
     * The first operand as number string.
     */
    private final String firstOperandString;

    /**
     * The first operand parsed from the specified number string.
     */
    private Number firstOperand;

    /**
     * The second operand as number string.
     */
    private final String secondOperandString;

    /**
     * The second operand parsed from the specified number string,
     */
    private Number secondOperand;

    /**
     * The expected comparison result.
     */
    private final boolean expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param firstOperandString
     *        a number string
     * @param secondOperandString
     *        a number string
     * @param expectedResult
     *        the expected comparison result
     */
    public NumberGreaterThanComparisonTest(int base, String firstOperandString, String secondOperandString,
                                           boolean expectedResult) {

        super();

        this.base = base;
        this.firstOperandString = firstOperandString;
        this.secondOperandString = secondOperandString;
        this.expectedResult = expectedResult;
    }

    /**
     * Parses the number strings before the actual test.
     */
    @Before
    public void setUp() {

        firstOperand = createNumber(base, firstOperandString);
        secondOperand = createNumber(base, secondOperandString);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        firstOperand = null;
        secondOperand = null;
    }

    /**
     * Returns a summary of this test case (i.e. the operation with its operands)
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s > %s", base, firstOperandString, secondOperandString);
    }

    /**
     * Tests comparing two numbers and checks the result.
     */
    @Test
    public void testComparison() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(firstOperand, firstOperandString);
            checkNumberEqualsStringRepresentation(secondOperand, secondOperandString);

            // check the operation
            boolean actualResult = firstOperand.isGreater(secondOperand);

            String message = String.format("%s > %s => %s", firstOperandString, secondOperandString, expectedResult);
            assertEquals(message, expectedResult, actualResult);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(firstOperand, firstOperandString);
            checkNumberEqualsStringRepresentation(secondOperand, secondOperandString);

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Tests comparing two numbers and checks the result.
     */
    @Test
    public void testComparisonVariant2() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(firstOperand, firstOperandString);
            checkNumberEqualsStringRepresentation(secondOperand, secondOperandString);

            // check the operation
            boolean actualResult = Math.isGreater(firstOperand, secondOperand);

            String message = String.format("%s > %s => %s", firstOperandString, secondOperandString, expectedResult);
            assertEquals(message, expectedResult, actualResult);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(firstOperand, firstOperandString);
            checkNumberEqualsStringRepresentation(secondOperand, secondOperandString);

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

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base, "0", "0", false });
            parameters.add(new Object[] { base, "1", "0", true });
            parameters.add(new Object[] { base, "0", "1", false });

            parameters.add(new Object[] { base, "0.1111", "0.1101", true });
            parameters.add(new Object[] { base, "0.1101", "0.1111", false });

            parameters.add(new Object[] { base, "-0.1111", "-0.1101", false });
            parameters.add(new Object[] { base, "-0.1101", "-0.1111", true });
        }

        return parameters;
    }

}

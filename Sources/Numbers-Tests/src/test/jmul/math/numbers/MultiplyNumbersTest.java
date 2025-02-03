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
import static test.jmul.math.numbers.NumberCheckHelper.checkNumbersAreUniqueInstances;


/**
 * This test suite tests multiplying two numbers with various combinations of operands.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MultiplyNumbersTest {

    /**
     * The base for all summands.
     */
    private final int base;

    /**
     * The first operand as number string.
     */
    private final String firstOperandString;

    /**
     * The first summand parsed from the specified number string.
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
     * The expected product as number string.
     */
    private final String expectedProductString;

    /**
     * The expected product parsed from the specified number string.
     */
    private Number expectedProduct;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param base
     *        the base for all numbers
     * @param firstOperandString
     *        the first operand as number string
     * @param secondOperandString
     *        the second operand as number string
     * @param expectedProductString
     *        the product as number string
     */
    public MultiplyNumbersTest(int base, String firstOperandString, String secondOperandString,
                               String expectedProductString) {

        super();

        this.base = base;
        this.firstOperandString = firstOperandString;
        this.secondOperandString = secondOperandString;
        this.expectedProductString = expectedProductString;
    }

    /**
     * Parses the number strings before the actual test.
     */
    @Before
    public void setUp() {

        if (firstOperandString == null) {

            firstOperand = createNumber(base);

        } else {

            firstOperand = createNumber(base, firstOperandString);
        }

        if (secondOperandString == null) {

            secondOperand = createNumber(base);

        } else {

            secondOperand = createNumber(base, secondOperandString);
        }

        if (expectedProductString == null) {

            expectedProduct = createNumber(base);

        } else {

            expectedProduct = createNumber(base, expectedProductString);
        }
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        firstOperand = null;
        secondOperand = null;
        expectedProduct = null;
    }

    @Override
    public String toString() {

        String infinity = "infinity";

        String operand1 = firstOperandString;
        String operand2 = secondOperandString;
        String result = expectedProductString;

        if (firstOperandString == null) {

            operand1 = infinity;
        }
        if (secondOperandString == null) {

            operand2 = infinity;
        }
        if (expectedProductString == null) {

            result = infinity;
        }


        String representation = String.format("[base:%d]: %s * %s = %s", base, operand1, operand2, result);

        return representation;
    }

    /**
     * Tests adding the summands and checks the result.
     */
    @Test
    public void testAddition() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(firstOperand, firstOperandString);
            checkNumberEqualsStringRepresentation(secondOperand, secondOperandString);
            checkNumberEqualsStringRepresentation(expectedProduct, expectedProductString);

            // check the operation
            Number actualProduct = firstOperand.multiply(secondOperand);

            assertEquals(toString(), expectedProduct, actualProduct);

            // check the number instances
            checkNumbersAreUniqueInstances(firstOperand, secondOperand, actualProduct);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(firstOperand, firstOperandString);
            checkNumberEqualsStringRepresentation(secondOperand, secondOperandString);

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
            checkNumberEqualsStringRepresentation(firstOperand, firstOperandString);
            checkNumberEqualsStringRepresentation(secondOperand, secondOperandString);
            checkNumberEqualsStringRepresentation(expectedProduct, expectedProductString);

            // check the operation
            Number actualProduct = Math.multiply(firstOperand, secondOperand);

            assertEquals(toString(), expectedProduct, actualProduct);

            // check the number instances
            checkNumbersAreUniqueInstances(firstOperand, secondOperand, actualProduct);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(firstOperand, firstOperandString);
            checkNumberEqualsStringRepresentation(secondOperand, secondOperandString);

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Tests the number properties.
     */
    @Test
    public void testProperties() {

        // check that the operands and the expected result are built correctly
        checkNumberEqualsStringRepresentation(firstOperand, firstOperandString);
        checkNumberEqualsStringRepresentation(secondOperand, secondOperandString);
        checkNumberEqualsStringRepresentation(expectedProduct, expectedProductString);
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

            parameters.add(new Object[] { base, "1", null, null });
            parameters.add(new Object[] { base, null, "1", null });

            parameters.add(new Object[] { base, "1", "1", "1" });
            parameters.add(new Object[] { base, "1", "0", "0" });
            parameters.add(new Object[] { base, "0", "1", "0" });
            parameters.add(new Object[] { base, "1", "-1", "-1" });
            parameters.add(new Object[] { base, "-1", "1", "-1" });
            parameters.add(new Object[] { base, "-1", "-1", "1" });
        }

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base, "2", null, null });
            parameters.add(new Object[] { base, null, "1", null });

            parameters.add(new Object[] { base, "2", "1", "2" });
            parameters.add(new Object[] { base, "1", "2", "2" });
            parameters.add(new Object[] { base, "2", "0", "0" });
            parameters.add(new Object[] { base, "0", "2", "0" });
            parameters.add(new Object[] { base, "0", "1", "0" });
            parameters.add(new Object[] { base, "1", "0", "0" });
            parameters.add(new Object[] { base, "2", "-1", "-2" });
            parameters.add(new Object[] { base, "-1", "2", "-2" });
            parameters.add(new Object[] { base, "-2", "1", "-2" });
            parameters.add(new Object[] { base, "1", "-2", "-2" });
            parameters.add(new Object[] { base, "-2", "-1", "2" });
            parameters.add(new Object[] { base, "-1", "-2", "2" });
        }

        parameters.add(new Object[] { 10, "11", "0", "0" });
        parameters.add(new Object[] { 10, "0", "6", "0" });

        parameters.add(new Object[] { 10, "11", "6", "66" });
        parameters.add(new Object[] { 10, "11", "0.6", "6.6" });
        parameters.add(new Object[] { 10, "11", "0.06", "0.66" });
        parameters.add(new Object[] { 10, "11", "0.006", "0.066" });

        for (int a = -10; a <= 10; a++) {

            for (int b = -10; b <= 10; b++) {

                int result = a * b;
                parameters.add(new Object[] { 10, "" + a, "" + b, "" + result });
            }
        }

        return parameters;
    }

}

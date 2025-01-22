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
 * This test suite tests shifting the decimal point to the left in numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ShiftLeftXTest {

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
     * The number of shifts as number string.
     */
    private final String shiftsString;

    /**
     * The number of shifts parsed from the specified number string.
     */
    private Number shifts;

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
     * @param shiftsString
     *        the number of shifts as number string
     * @param resultString
     *        the expected result as number string
     */
    public ShiftLeftXTest(int base, String operandString, String shiftsString, String resultString) {

        super();

        this.base = base;
        this.operandString = operandString;
        this.shiftsString = shiftsString;
        this.resultString = resultString;
    }

    /**
     * Parses the number strings before the actual test.
     */
    @Before
    public void setUp() {

        operand = new NumberImpl(base, operandString);
        shifts = new NumberImpl(base, shiftsString);
        result = new NumberImpl(base, resultString);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        operand = null;
        shifts = null;
        result = null;
    }

    /**
     * Returns a summary of this test case (i.e. the operation with its operands)
     *
     * @return a summary
     */
    @Override
    public String toString() {

        String summary = String.format("[base:%d] shift %s left %s", base, shiftsString, operandString);
        return summary;
    }

    /**
     * Tests shifting a decimal point to the left.
     */
    @Test
    public void testShiftLeft() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(operand, operandString);
            checkNumberEqualsStringRepresentation(result, resultString);

            // check the operation
            Number actualResult = operand.shiftLeft(shifts);

            String message = String.format("shift %s left %s => %s", shiftsString, operandString, resultString);
            assertEquals(message, result, actualResult);

            // check the number instances
            checkNumbersAreUniqueInstances(operand, actualResult);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(operand, operandString);

        } catch (Exception e) {

            throw new FailedTestException(toString(), e);
        }
    }

    /**
     * Tests shifting a decimal point to the left.
     */
    @Test
    public void testShiftLeftVariant2() {

        try {

            // check that the operands and the expected result are built correctly
            checkNumberEqualsStringRepresentation(operand, operandString);
            checkNumberEqualsStringRepresentation(result, resultString);

            // check the operation
            Number actualResult = Math.shiftLeft(operand, shifts);

            String message = String.format("shift %s left %s => %s", shiftsString, operandString, resultString);
            assertEquals(message, result, actualResult);

            // check the number instances
            checkNumbersAreUniqueInstances(operand, actualResult);

            // check that the operands didn't change
            checkNumberEqualsStringRepresentation(operand, operandString);

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

            parameters.add(new Object[] { base, "0", "1", "0" });
            parameters.add(new Object[] { base, "-0", "1", "-0" });

            parameters.add(new Object[] { base, "1", "1", "0.1" });
            parameters.add(new Object[] { base, "-1", "1", "-0.1" });

            parameters.add(new Object[] { base, "1", "0", "1" });
            parameters.add(new Object[] { base, "-1", "0", "-1" });

            parameters.add(new Object[] { base, "1", "-1", "10" });
            parameters.add(new Object[] { base, "-1", "-1", "-10" });

            parameters.add(new Object[] { base, "1.1", "1", "0.11" });
            parameters.add(new Object[] { base, "-1.1", "1", "-0.11" });

            parameters.add(new Object[] { base, "11.1", "1", "1.11" });
            parameters.add(new Object[] { base, "-11.1", "1", "-1.11" });

            parameters.add(new Object[] { base, "1.1010101", "1", "0.11010101" });
            parameters.add(new Object[] { base, "-1.1010101", "1", "-0.11010101" });
        }

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base, "0", "2", "0" });
            parameters.add(new Object[] { base, "-0", "2", "-0" });

            parameters.add(new Object[] { base, "2", "1", "0.2" });
            parameters.add(new Object[] { base, "-2", "1", "-0.2" });

            parameters.add(new Object[] { base, "2", "2", "0.02" });
            parameters.add(new Object[] { base, "-2", "2", "-0.02" });

            parameters.add(new Object[] { base, "2.1212", "1", "0.21212" });
            parameters.add(new Object[] { base, "-2.1212", "1", "-0.21212" });

            parameters.add(new Object[] { base, "2.1212", "2", "0.021212" });
            parameters.add(new Object[] { base, "-2.1212", "2", "-0.021212" });
        }

        for (int base = BASE_MIN_LIMIT + 2; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base, "0", "3", "0" });
            parameters.add(new Object[] { base, "-0", "3", "-0" });

            parameters.add(new Object[] { base, "3", "1", "0.3" });
            parameters.add(new Object[] { base, "-3", "1", "-0.3" });

            parameters.add(new Object[] { base, "3", "3", "0.003" });
            parameters.add(new Object[] { base, "-3", "3", "-0.003" });

            parameters.add(new Object[] { base, "3.123123", "1", "0.3123123" });
            parameters.add(new Object[] { base, "-3.123123", "1", "-0.3123123" });

            parameters.add(new Object[] { base, "3.123123", "3", "0.003123123" });
            parameters.add(new Object[] { base, "-3.123123", "3", "-0.003123123" });
        }

        return parameters;
    }

}

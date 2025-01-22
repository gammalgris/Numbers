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
import jmul.math.numbers.NumberImpl;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests decrementing a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DecNumberTest {

    /**
     * The base for all operands.
     */
    private final int base;

    /**
     * The operand as number string.
     */
    private final String operandString;

    /**
     * The first operand parsed from the specified number string.
     */
    private Number operand;

    /**
     * The expected result as number string.
     */
    private final String expectedResultString;

    /**
     * The expected result parsed from the specified number string.
     */
    private Number expectedResult;

    /**
     * Creates a new test case according to the specified numbers.
     *
     * @param base
     *        a number base
     * @param operandString
     *        the operand as number string
     * @param expectedResultString
     *        the expected result as number string
     */
    public DecNumberTest(int base, String operandString, String expectedResultString) {

        super();

        this.base = base;
        this.operandString = operandString;
        this.expectedResultString = expectedResultString;
    }

    /**
     * Parses the number strings before the actual test.
     */
    @Before
    public void setUp() {

        operand = new NumberImpl(base, operandString);
        expectedResult = new NumberImpl(base, expectedResultString);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        operand = null;
        expectedResult = null;
    }

    /**
     * Tests decrementing a number.
     */
    @Test
    public void testDec() {

        Number actualResult = operand.dec();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests decrementing a number.
     */
    @Test
    public void testDecVariant2() {

        Number actualResult = Math.dec(operand);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 2, "10", "1" });

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            {
                Digit operand = PositionalNumeralSystems.ordinalToDigit(base, 1);
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, 0);

                parameters.add(new Object[] { base, "" + operand, "" + result });
            }

            {
                Digit operand = PositionalNumeralSystems.ordinalToDigit(base, base - 1);
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, base - 2);

                parameters.add(new Object[] { base, "" + operand, "" + result });
            }

            {
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, base - 1);

                parameters.add(new Object[] { base, "10", "" + result });
            }
        }

        return parameters;
    }

}

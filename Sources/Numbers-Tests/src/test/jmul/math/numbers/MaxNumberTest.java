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

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests comparing two numbers and getting the greater number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MaxNumberTest {

    /**
     * The base for all operands.
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
     * The expected result as number string.
     */
    private final String expectedResultString;

    /**
     * The expected result parsed from the specified number string.
     */
    private Number expectedResult;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param base
     *        the base for all numbers
     * @param firstOperandString
     *        the first operand as number string
     * @param secondOperandString
     *        the second operand as number string
     * @param expectedResultString
     *        the expected result as number string
     */
    public MaxNumberTest(int base, String firstOperandString, String secondOperandString, String expectedResultString) {

        super();

        this.base = base;
        this.firstOperandString = firstOperandString;
        this.secondOperandString = secondOperandString;
        this.expectedResultString = expectedResultString;
    }

    /**
     * Parses the number strings before the actual test.
     */
    @Before
    public void setUp() {

        firstOperand = createNumber(base, firstOperandString);
        secondOperand = createNumber(base, secondOperandString);
        expectedResult = createNumber(base, expectedResultString);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        firstOperand = null;
        secondOperand = null;
        expectedResult = null;
    }

    /**
     * Tests comparing two numbers and getting the bigger number.
     */
    @Test
    public void testGettingMax() {

        Number actualResult = firstOperand.max(secondOperand);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests comparing two numbers and getting the bigger number.
     */
    @Test
    public void testGettingMaxVariant2() {

        Number actualResult = Math.max(firstOperand, secondOperand);
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

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base, "0", "0", "0" });

            Digit digit = PositionalNumeralSystems.ordinalToDigit(base, base - 1);

            parameters.add(new Object[] { base, "" + digit.symbol(), "0", "" + digit.symbol() });
            parameters.add(new Object[] { base, "0", "" + digit.symbol(), "" + digit.symbol() });
        }

        return parameters;
    }

}

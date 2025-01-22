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
 * This test suite tests if numbers can be identified as odd.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class OddNumberTest {

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
     * The expected result.
     */
    private final boolean expectedResult;

    /**
     * Creates a new test case according to the specified numbers.
     *
     * @param base
     *        a number base
     * @param operandString
     *        the operand as number string
     * @param expectedResult
     *        the expected result
     */
    public OddNumberTest(int base, String operandString, boolean expectedResult) {

        super();

        this.base = base;
        this.operandString = operandString;
        this.expectedResult = expectedResult;
    }

    /**
     * Parses the number strings before the actual test.
     */
    @Before
    public void setUp() {

        operand = new NumberImpl(base, operandString);
    }

    /**
     * Cleans up after a test.
     */
    @After
    public void tearDown() {

        operand = null;
    }

    /**
     * Tests checking if a number is even.
     */
    @Test
    public void testOdd() {

        boolean actualResult = operand.isOdd();
        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Tests checking if a number is even.
     */
    @Test
    public void testOddVariant2() {

        boolean actualResult = Math.isOdd(operand);
        assertEquals(toString(), expectedResult, actualResult);
    }

    @Override
    public String toString() {

        return String.format("base=%d;number=%s", base, operandString);
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

            for (int ordinal = 0; ordinal < base; ordinal++) {

                boolean expectedResult = ordinal % 2 != 0;
                Digit digit = PositionalNumeralSystems.ordinalToDigit(base, ordinal);
                parameters.add(new Object[] { base, "" + digit, expectedResult });
            }
        }

        return parameters;
    }

}

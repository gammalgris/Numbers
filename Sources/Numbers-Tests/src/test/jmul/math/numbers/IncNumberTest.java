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
 * This test suite tests incrementing a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class IncNumberTest {

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
    public IncNumberTest(int base, String operandString, String expectedResultString) {

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
     * Returns a string representation for this test case.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("[base:%d]: (%s)++ -> %s", operand.base(), operand, expectedResult);
    }

    /**
     * Tests incrementing a number.
     */
    @Test
    public void testInc() {

        Number actualResult = operand.inc();
        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Tests incrementing a number.
     */
    @Test
    public void testIncVariant2() {

        Number actualResult = Math.inc(operand);
        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            {
                Digit operand = PositionalNumeralSystems.ordinalToDigit(base, 1);
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, 0);

                parameters.add(new Object[] { base, "-" + operand, "" + result });
            }

            {
                Digit operand = PositionalNumeralSystems.ordinalToDigit(base, 0);
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, 1);

                parameters.add(new Object[] { base, "" + operand, "" + result });
            }

            {
                Digit operand = PositionalNumeralSystems.ordinalToDigit(base, 1);
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, 2);

                parameters.add(new Object[] { base, "" + operand, "" + result });
            }

            {
                Digit operand = PositionalNumeralSystems.ordinalToDigit(base, base - 2);
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, base - 1);

                parameters.add(new Object[] { base, "" + operand, "" + result });
            }

            {
                Digit operand = PositionalNumeralSystems.ordinalToDigit(base, base - 1);
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, 1);

                parameters.add(new Object[] { base, "" + operand, "" + result + "0" });
            }

            {
                Digit highestDigit = PositionalNumeralSystems.ordinalToDigit(base, base - 1);
                Digit oneDigit = PositionalNumeralSystems.ordinalToDigit(base, 1);

                parameters.add(new Object[] { base, "" + highestDigit + highestDigit, "" + oneDigit + "00" });
            }
        }

        parameters.add(new Object[] { 2, "11", "100" });
        parameters.add(new Object[] { 2, "-1", "0" });
        parameters.add(new Object[] { 2, "0", "1" });
        parameters.add(new Object[] { 8, "77", "100" });
        parameters.add(new Object[] { 10, "99", "100" });
        parameters.add(new Object[] { 10, "199", "200" });
        parameters.add(new Object[] { 10, "999", "1000" });
        parameters.add(new Object[] { 16, "FF", "100" });

        parameters.add(new Object[] { 10, "0.15", "1.15" });
        parameters.add(new Object[] { 10, "0.14", "1.14" });
        parameters.add(new Object[] { 10, "0.13", "1.13" });
        parameters.add(new Object[] { 10, "0.12", "1.12" });
        parameters.add(new Object[] { 10, "0.11", "1.11" });
        parameters.add(new Object[] { 10, "0.10", "1.10" });
        parameters.add(new Object[] { 10, "0.09", "1.09" });
        parameters.add(new Object[] { 10, "0.08", "1.08" });
        parameters.add(new Object[] { 10, "0.07", "1.07" });
        parameters.add(new Object[] { 10, "0.06", "1.06" });
        parameters.add(new Object[] { 10, "0.05", "1.05" });
        parameters.add(new Object[] { 10, "0.04", "1.04" });
        parameters.add(new Object[] { 10, "0.03", "1.03" });
        parameters.add(new Object[] { 10, "0.02", "1.02" });
        parameters.add(new Object[] { 10, "0.01", "1.01" });
        parameters.add(new Object[] { 10, "0.0", "1" });
        parameters.add(new Object[] { 10, "-0.01", "0.99" });
        parameters.add(new Object[] { 10, "-0.02", "0.98" });
        parameters.add(new Object[] { 10, "-0.03", "0.97" });
        parameters.add(new Object[] { 10, "-0.04", "0.96" });
        parameters.add(new Object[] { 10, "-0.05", "0.95" });
        parameters.add(new Object[] { 10, "-0.06", "0.94" });
        parameters.add(new Object[] { 10, "-0.07", "0.93" });
        parameters.add(new Object[] { 10, "-0.08", "0.92" });
        parameters.add(new Object[] { 10, "-0.09", "0.91" });
        parameters.add(new Object[] { 10, "-0.10", "0.90" });
        parameters.add(new Object[] { 10, "-0.11", "0.89" });
    

        parameters.add(new Object[] { 2, "0", "1" });
        parameters.add(new Object[] { 2, "1", "10" });
        parameters.add(new Object[] { 2, "10", "11" });
        parameters.add(new Object[] { 2, "11", "100" });
        parameters.add(new Object[] { 2, "100", "101" });
        parameters.add(new Object[] { 2, "101", "110" });
        parameters.add(new Object[] { 2, "110", "111" });
        parameters.add(new Object[] { 2, "111", "1000" });
        parameters.add(new Object[] { 2, "1000", "1001" });
        parameters.add(new Object[] { 2, "1001", "1010" });
        parameters.add(new Object[] { 2, "1010", "1011" });
        parameters.add(new Object[] { 2, "1011", "1100" });
        parameters.add(new Object[] { 2, "1100", "1101" });
        parameters.add(new Object[] { 2, "1101", "1110" });
        parameters.add(new Object[] { 2, "1110", "1111" });
        parameters.add(new Object[] { 2, "1111", "10000" });
        parameters.add(new Object[] { 2, "10000", "10001" });

        parameters.add(new Object[] { 3, "0", "1" });
        parameters.add(new Object[] { 3, "1", "2" });
        parameters.add(new Object[] { 3, "2", "10" });
        parameters.add(new Object[] { 3, "10", "11" });
        parameters.add(new Object[] { 3, "11", "12" });
        parameters.add(new Object[] { 3, "12", "20" });
        parameters.add(new Object[] { 3, "20", "21" });
        parameters.add(new Object[] { 3, "21", "22" });
        parameters.add(new Object[] { 3, "22", "100" });
        parameters.add(new Object[] { 3, "100", "101" });
        parameters.add(new Object[] { 3, "101", "102" });
        parameters.add(new Object[] { 3, "102", "110" });
        parameters.add(new Object[] { 3, "110", "111" });
        parameters.add(new Object[] { 3, "111", "112" });
        parameters.add(new Object[] { 3, "112", "120" });
        parameters.add(new Object[] { 3, "120", "121" });
        parameters.add(new Object[] { 3, "121", "122" });
        parameters.add(new Object[] { 3, "122", "200" });
        parameters.add(new Object[] { 3, "200", "201" });
        parameters.add(new Object[] { 3, "201", "202" });
        parameters.add(new Object[] { 3, "202", "210" });
        parameters.add(new Object[] { 3, "210", "211" });
        parameters.add(new Object[] { 3, "211", "212" });
        parameters.add(new Object[] { 3, "212", "220" });
        parameters.add(new Object[] { 3, "220", "221" });
        parameters.add(new Object[] { 3, "221", "222" });
        parameters.add(new Object[] { 3, "222", "1000" });

        parameters.add(new Object[] { 4, "0", "1" });
        parameters.add(new Object[] { 4, "1", "2" });
        parameters.add(new Object[] { 4, "2", "3" });
        parameters.add(new Object[] { 4, "3", "10" });
        parameters.add(new Object[] { 4, "10", "11" });
        parameters.add(new Object[] { 4, "11", "12" });
        parameters.add(new Object[] { 4, "12", "13" });
        parameters.add(new Object[] { 4, "13", "20" });
        parameters.add(new Object[] { 4, "20", "21" });
        parameters.add(new Object[] { 4, "21", "22" });
        parameters.add(new Object[] { 4, "22", "23" });
        parameters.add(new Object[] { 4, "23", "30" });
        parameters.add(new Object[] { 4, "30", "31" });
        parameters.add(new Object[] { 4, "31", "32" });
        parameters.add(new Object[] { 4, "32", "33" });
        parameters.add(new Object[] { 4, "33", "100" });
        parameters.add(new Object[] { 4, "100", "101" });

        return parameters;
    }

}

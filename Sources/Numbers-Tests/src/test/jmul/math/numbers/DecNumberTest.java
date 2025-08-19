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

        operand = createNumber(base, operandString);
        expectedResult = createNumber(base, expectedResultString);
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
     * Tests decrementing a number.
     */
    @Test
    public void testDec() {

        Number actualResult = operand.dec();
        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests decrementing a number.
     */
    @Test
    public void testDecVariant2() {

        Number actualResult = Math.dec(operand);
        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
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

                parameters.add(new Object[] { base, "" + operand, "" + result });
            }

            {
                Digit operand = PositionalNumeralSystems.ordinalToDigit(base, 0);
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, 1);

                parameters.add(new Object[] { base, "" + operand, "-" + result });
            }

            {
                Digit operand = PositionalNumeralSystems.ordinalToDigit(base, 2);
                Digit result = PositionalNumeralSystems.ordinalToDigit(base, 1);

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

            {
                Digit highestDigit = PositionalNumeralSystems.ordinalToDigit(base, base - 1);
                Digit oneDigit = PositionalNumeralSystems.ordinalToDigit(base, 1);

                parameters.add(new Object[] { base, "" + oneDigit + "00", "" + highestDigit + highestDigit });
            }
        }

        parameters.add(new Object[] { 2, "100", "11" });
        parameters.add(new Object[] { 2, "0", "-1" });
        parameters.add(new Object[] { 2, "1", "0" });
        parameters.add(new Object[] { 8, "100", "77" });
        parameters.add(new Object[] { 10, "100", "99" });
        parameters.add(new Object[] { 10, "200", "199" });
        parameters.add(new Object[] { 10, "1000", "999" });
        parameters.add(new Object[] { 16, "100", "FF" });

        parameters.add(new Object[] { 10, "1.15", "0.15" });
        parameters.add(new Object[] { 10, "1.14", "0.14" });
        parameters.add(new Object[] { 10, "1.13", "0.13" });
        parameters.add(new Object[] { 10, "1.12", "0.12" });
        parameters.add(new Object[] { 10, "1.11", "0.11" });
        parameters.add(new Object[] { 10, "1.10", "0.10" });
        parameters.add(new Object[] { 10, "1.09", "0.09" });
        parameters.add(new Object[] { 10, "1.08", "0.08" });
        parameters.add(new Object[] { 10, "1.07", "0.07" });
        parameters.add(new Object[] { 10, "1.06", "0.06" });
        parameters.add(new Object[] { 10, "1.05", "0.05" });
        parameters.add(new Object[] { 10, "1.04", "0.04" });
        parameters.add(new Object[] { 10, "1.03", "0.03" });
        parameters.add(new Object[] { 10, "1.02", "0.02" });
        parameters.add(new Object[] { 10, "1.01", "0.01" });
        parameters.add(new Object[] { 10, "1.0", "0" });
        parameters.add(new Object[] { 10, "0.99", "-0.01" });
        parameters.add(new Object[] { 10, "0.98", "-0.02" });
        parameters.add(new Object[] { 10, "0.97", "-0.03" });
        parameters.add(new Object[] { 10, "0.96", "-0.04" });
        parameters.add(new Object[] { 10, "0.95", "-0.05" });
        parameters.add(new Object[] { 10, "0.94", "-0.06" });
        parameters.add(new Object[] { 10, "0.93", "-0.07" });
        parameters.add(new Object[] { 10, "0.92", "-0.08" });
        parameters.add(new Object[] { 10, "0.91", "-0.09" });
        parameters.add(new Object[] { 10, "0.90", "-0.10" });
        parameters.add(new Object[] { 10, "0.89", "-0.11" });

        parameters.add(new Object[] { 2, "0", "-1" });
        parameters.add(new Object[] { 2, "1", "0" });
        parameters.add(new Object[] { 2, "10", "1" });
        parameters.add(new Object[] { 2, "11", "10" });
        parameters.add(new Object[] { 2, "100", "11" });
        parameters.add(new Object[] { 2, "101", "100" });
        parameters.add(new Object[] { 2, "110", "101" });
        parameters.add(new Object[] { 2, "111", "110" });
        parameters.add(new Object[] { 2, "1000", "111" });
        parameters.add(new Object[] { 2, "1001", "1000" });
        parameters.add(new Object[] { 2, "1010", "1001" });
        parameters.add(new Object[] { 2, "1011", "1010" });
        parameters.add(new Object[] { 2, "1100", "1011" });
        parameters.add(new Object[] { 2, "1101", "1100" });
        parameters.add(new Object[] { 2, "1110", "1101" });
        parameters.add(new Object[] { 2, "1111", "1110" });
        parameters.add(new Object[] { 2, "10000", "1111" });

        parameters.add(new Object[] { 3, "0", "-1" });
        parameters.add(new Object[] { 3, "1", "0" });
        parameters.add(new Object[] { 3, "2", "1" });
        parameters.add(new Object[] { 3, "10", "2" });
        parameters.add(new Object[] { 3, "11", "10" });
        parameters.add(new Object[] { 3, "12", "11" });
        parameters.add(new Object[] { 3, "20", "12" });
        parameters.add(new Object[] { 3, "21", "20" });
        parameters.add(new Object[] { 3, "22", "21" });
        parameters.add(new Object[] { 3, "100", "22" });
        parameters.add(new Object[] { 3, "101", "100" });
        parameters.add(new Object[] { 3, "102", "101" });
        parameters.add(new Object[] { 3, "110", "102" });
        parameters.add(new Object[] { 3, "111", "110" });
        parameters.add(new Object[] { 3, "112", "111" });
        parameters.add(new Object[] { 3, "120", "112" });
        parameters.add(new Object[] { 3, "121", "120" });
        parameters.add(new Object[] { 3, "122", "121" });
        parameters.add(new Object[] { 3, "200", "122" });
        parameters.add(new Object[] { 3, "201", "200" });
        parameters.add(new Object[] { 3, "202", "201" });
        parameters.add(new Object[] { 3, "210", "202" });
        parameters.add(new Object[] { 3, "211", "210" });
        parameters.add(new Object[] { 3, "212", "211" });
        parameters.add(new Object[] { 3, "220", "212" });
        parameters.add(new Object[] { 3, "221", "220" });
        parameters.add(new Object[] { 3, "222", "221" });
        parameters.add(new Object[] { 3, "1000", "222" });

        parameters.add(new Object[] { 4, "0", "-1" });
        parameters.add(new Object[] { 4, "1", "0" });
        parameters.add(new Object[] { 4, "2", "1" });
        parameters.add(new Object[] { 4, "3", "2" });
        parameters.add(new Object[] { 4, "10", "3" });
        parameters.add(new Object[] { 4, "11", "10" });
        parameters.add(new Object[] { 4, "12", "11" });
        parameters.add(new Object[] { 4, "13", "12" });
        parameters.add(new Object[] { 4, "20", "13" });
        parameters.add(new Object[] { 4, "21", "20" });
        parameters.add(new Object[] { 4, "22", "21" });
        parameters.add(new Object[] { 4, "23", "22" });
        parameters.add(new Object[] { 4, "30", "23" });
        parameters.add(new Object[] { 4, "31", "30" });
        parameters.add(new Object[] { 4, "32", "31" });
        parameters.add(new Object[] { 4, "33", "32" });
        parameters.add(new Object[] { 4, "100", "33" });

        return parameters;
    }

}

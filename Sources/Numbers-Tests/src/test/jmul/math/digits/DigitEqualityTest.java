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

package test.jmul.math.digits;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite checks the digits comparison regarding equality.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DigitEqualityTest {

    /**
     * A digit.
     */
    private final Digit digit1;

    /**
     * A digit.
     */
    private final Digit digit2;

    /**
     * The expected result of the comparison.
     */
    private final boolean expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param d1
     *        a digit
     * @param d2
     *        a digit
     * @param result
     *        the expected result
     */
    public DigitEqualityTest(Digit d1, Digit d2, boolean result) {

        super();

        digit1 = d1;
        digit2 = d2;
        expectedResult = result;
    }

    /**
     * Compares two digits regarding equality and compares the actual result with the expected result.
     */
    @Test
    public void compareDigits() {

        boolean actualResult = digit1.equals(digit2);

        String message =
            String.format("The comparison failed (digit1=%s; digit2=%s; expected result=%b; actual result=%b)!",
                          digit1.toString(), digit2.toString(), expectedResult, actualResult);
        assertEquals(message, expectedResult, actualResult);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int a = 0; a < 9; a++) {

            Digit d1 = PositionalNumeralSystems.ordinalToDigit(10, a);
            Digit d2 = PositionalNumeralSystems.ordinalToDigit(10, a + 1);
            parameters.add(new Object[] { d1, d2, false });
        }

        for (int a = 0; a < 9; a++) {

            Digit d1 = PositionalNumeralSystems.ordinalToDigit(10, a);
            Digit d2 = PositionalNumeralSystems.ordinalToDigit(10, a + 1);
            parameters.add(new Object[] { d2, d1, false });
        }

        for (int a = 0; a < 10; a++) {

            Digit d1 = PositionalNumeralSystems.ordinalToDigit(10, a);
            Digit d2 = PositionalNumeralSystems.ordinalToDigit(10, a);
            parameters.add(new Object[] { d1, d2, true });
        }

        return parameters;
    }

}

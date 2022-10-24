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

package test.jmul.math.numbers.digits;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.NumeralSystems;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite checks the digits comparison regarding their natural ordering.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DigitComparisonTest {

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
    private final int expectedResult;

    /**
     * Creates a new test case accordign to the specified parameters.
     *
     * @param d1
     *        a digit
     * @param d2
     *        a digit
     * @param result
     *        the expected result
     */
    public DigitComparisonTest(Digit d1, Digit d2, int result) {

        super();

        digit1 = d1;
        digit2 = d2;
        expectedResult = result;
    }

    /**
     * Compares two digits regarding their natural ordering and compares the actual result with the expected result.
     */
    @Test
    public void compareDigits() {

        int actualResult = digit1.compareTo(digit2);

        String message =
            String.format("The comparison failed (digit1=%s; digit2=%s; expected result=%d; actual result=%d)!", digit1,
                          digit2, expectedResult, actualResult);
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

            Digit d1 = NumeralSystems.ordinalToDigit(10, a);
            Digit d2 = NumeralSystems.ordinalToDigit(10, a + 1);
            parameters.add(new Object[] { d1, d2, -1 });
        }

        for (int a = 0; a < 9; a++) {

            Digit d1 = NumeralSystems.ordinalToDigit(10, a);
            Digit d2 = NumeralSystems.ordinalToDigit(10, a + 1);
            parameters.add(new Object[] { d2, d1, 1 });
        }

        return parameters;
    }

}

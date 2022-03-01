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

package test.jmul.math.numbers;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.numbers.RealDecimalNumber;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the comparable function.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberComparableTest {

    /**
     * A number.
     */
    private final RealDecimalNumber number1;

    /**
     * A number.
     */
    private final RealDecimalNumber number2;

    /**
     * The expected result for comparing equality.
     */
    private final int expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number1
     * @param number2
     * @param expectedResult
     */
    public RealNumberComparableTest(RealDecimalNumber number1, RealDecimalNumber number2, int expectedResult) {

        super();

        this.number1 = number1;
        this.number2 = number2;
        this.expectedResult = expectedResult;
    }

    /**
     * Tests comparing the numbers in the given order and reversed order.
     */
    @Test
    public void testComparable() {

        String message =
            String.format("Comparing the numbers %s and %s has failed!", number1.toString(), number2.toString());

        int r1 = number1.compareTo(number2);
        assertEquals(message, expectedResult, r1);

        int r2 = number2.compareTo(number1);
        assertEquals(message, -expectedResult, r2);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new RealDecimalNumber(), new RealDecimalNumber(0), 0 });

        parameters.add(new Object[] { new RealDecimalNumber(), RealDecimalNumber.POSITIVE_INFINITY, -1 });
        parameters.add(new Object[] { RealDecimalNumber.POSITIVE_INFINITY, new RealDecimalNumber(), 1 });

        parameters.add(new Object[] { RealDecimalNumber.NEGATIVE_INFINITY, RealDecimalNumber.POSITIVE_INFINITY, -1 });
        parameters.add(new Object[] { RealDecimalNumber.POSITIVE_INFINITY, RealDecimalNumber.NEGATIVE_INFINITY, 1 });

        parameters.add(new Object[] { RealDecimalNumber.NEGATIVE_INFINITY, RealDecimalNumber.NEGATIVE_INFINITY, 0 });
        parameters.add(new Object[] { RealDecimalNumber.POSITIVE_INFINITY, RealDecimalNumber.POSITIVE_INFINITY, 0 });

        parameters.add(new Object[] { new RealDecimalNumber(-1), new RealDecimalNumber(1), -1 });
        parameters.add(new Object[] { new RealDecimalNumber(1), new RealDecimalNumber(-1), 1 });

        parameters.add(new Object[] { new RealDecimalNumber("0.00000001"), new RealDecimalNumber(1), -1 });
        parameters.add(new Object[] { new RealDecimalNumber(1), new RealDecimalNumber("0.00000001"), 1 });

        return parameters;
    }

}

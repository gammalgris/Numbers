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

import jmul.math.numbers.RealNumber;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the equality function.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberEqualsTest {

    /**
     * A number.
     */
    private final RealNumber number1;

    /**
     * A number.
     */
    private final RealNumber number2;

    /**
     * The expected result for comparing equality.
     */
    private final boolean expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number1
     * @param number2
     * @param expectedResult
     */
    public RealNumberEqualsTest(RealNumber number1, RealNumber number2, boolean expectedResult) {

        super();

        this.number1 = number1;
        this.number2 = number2;
        this.expectedResult = expectedResult;
    }

    /**
     * Tests comparing the numbers in the given order and reversed order.
     */
    @Test
    public void testEquality() {

        String message =
            String.format("Comparing the numbers %s and %s has failed!", number1.toString(), number2.toString());

        boolean r1 = number1.equals(number2);
        assertEquals(message, expectedResult, r1);

        boolean r2 = number2.equals(number1);
        assertEquals(message, expectedResult, r2);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new RealNumber(), new RealNumber(0), true });

        parameters.add(new Object[] { RealNumber.POSITIVE_INFINITY, new RealNumber(0), false });
        parameters.add(new Object[] { new RealNumber(0), RealNumber.POSITIVE_INFINITY, false });
        parameters.add(new Object[] { RealNumber.NEGATIVE_INFINITY, new RealNumber(0), false });
        parameters.add(new Object[] { new RealNumber(0), RealNumber.NEGATIVE_INFINITY, false });

        parameters.add(new Object[] { RealNumber.POSITIVE_INFINITY, new RealNumber(10), false });
        parameters.add(new Object[] { new RealNumber(10), RealNumber.POSITIVE_INFINITY, false });
        parameters.add(new Object[] { RealNumber.NEGATIVE_INFINITY, new RealNumber(10), false });
        parameters.add(new Object[] { new RealNumber(10), RealNumber.NEGATIVE_INFINITY, false });

        parameters.add(new Object[] { RealNumber.POSITIVE_INFINITY, new RealNumber(-10), false });
        parameters.add(new Object[] { new RealNumber(-10), RealNumber.POSITIVE_INFINITY, false });
        parameters.add(new Object[] { RealNumber.NEGATIVE_INFINITY, new RealNumber(-10), false });
        parameters.add(new Object[] { new RealNumber(-10), RealNumber.NEGATIVE_INFINITY, false });

        parameters.add(new Object[] { RealNumber.POSITIVE_INFINITY, RealNumber.POSITIVE_INFINITY, true });
        parameters.add(new Object[] { RealNumber.POSITIVE_INFINITY, RealNumber.NEGATIVE_INFINITY, false });
        parameters.add(new Object[] { RealNumber.NEGATIVE_INFINITY, RealNumber.POSITIVE_INFINITY, false });
        parameters.add(new Object[] { RealNumber.NEGATIVE_INFINITY, RealNumber.NEGATIVE_INFINITY, true });

        parameters.add(new Object[] { new RealNumber(1000), new RealNumber(1000), true });
        parameters.add(new Object[] { new RealNumber(1000), new RealNumber(1001), false });
        parameters.add(new Object[] { new RealNumber(1001), new RealNumber(1000), false });

        parameters.add(new Object[] { new RealNumber(1000.00001F), new RealNumber(1000.00001F), true });
        parameters.add(new Object[] { new RealNumber(1000.00001F), new RealNumber(1001.00001F), false });
        parameters.add(new Object[] { new RealNumber(1001.00001F), new RealNumber(1000.00001F), false });

        return parameters;
    }

}

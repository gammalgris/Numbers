/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
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
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite checks determining if a number consists of a single digit.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class IsSingleDigitTest {

    /**
     * A number (i.e. integer)
     */
    private Number number;

    /**
     * The expected result.
     */
    private Boolean expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param expectedResult
     *        the expected result
     */
    public IsSingleDigitTest(Number number, boolean expectedResult) {

        super();

        this.number = number;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s -> %s ", number.base(), number, expectedResult);
    }

    /**
     * Tests checking if the number consists of a single digit.
     */
    @Test
    public void testIfSingleDigit() {

        Boolean actualResult = number.isSingleDigit();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests checking if the number consists of a single digit.
     */
    @Test
    public void testIfSingleDigitVariant2() {

        Boolean actualResult = Math.isSingleDigit(number);

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

        parameters.add(new Object[] { createNumber(2, "0"), true });
        parameters.add(new Object[] { createNumber(2, "1"), true });
        parameters.add(new Object[] { createNumber(2, "10"), false });
        parameters.add(new Object[] { createNumber(2, "11"), false });

        parameters.add(new Object[] { createNumber(3, "0"), true });
        parameters.add(new Object[] { createNumber(3, "1"), true });
        parameters.add(new Object[] { createNumber(3, "2"), true });
        parameters.add(new Object[] { createNumber(3, "10"), false });
        parameters.add(new Object[] { createNumber(3, "11"), false });
        parameters.add(new Object[] { createNumber(3, "12"), false });

        return parameters;
    }

}

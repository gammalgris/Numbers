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
 * This test suite tests calculating the factorial.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FactorialTest {

    /**
     * A positive integer.
     */
    private final Number operand;

    /**
     * The expected result of the factorial function.
     */
    private final Number expectedResult;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param operand
     *        a positive integer
     * @param expectedResult
     *        the expected result of the factorial function
     */
    public FactorialTest(Number operand, Number expectedResult) {

        super();

        this.operand = operand;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a string representation for this test case.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("[base:%d]: %s! -> %s", operand.base(), operand, expectedResult);
    }

    /**
     * Tests calculating the factorial.
     */
    @Test
    public void testFactorial() {

        Number actualResult = operand.factorial();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests calculating the factorial.
     */
    @Test
    public void testFactorialVariant2() {

        Number actualResult = Math.factorial(operand);

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

        parameters.add(new Object[] { createNumber(10, "0"), createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "1"), createNumber(10, "1") });
        parameters.add(new Object[] { createNumber(10, "2"), createNumber(10, "2") });
        parameters.add(new Object[] { createNumber(10, "3"), createNumber(10, "6") });
        parameters.add(new Object[] { createNumber(10, "4"), createNumber(10, "24") });
        parameters.add(new Object[] { createNumber(10, "5"), createNumber(10, "120") });
        parameters.add(new Object[] { createNumber(10, "6"), createNumber(10, "720") });
        parameters.add(new Object[] { createNumber(10, "7"), createNumber(10, "5040") });
        parameters.add(new Object[] { createNumber(10, "8"), createNumber(10, "40320") });
        parameters.add(new Object[] { createNumber(10, "9"), createNumber(10, "362880") });
        parameters.add(new Object[] { createNumber(10, "10"), createNumber(10, "3628800") });

        return parameters;
    }

}

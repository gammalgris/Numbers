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

import static jmul.math.Constants.MAX_BASE;
import static jmul.math.Constants.MIN_BASE;
import jmul.math.Math;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests negating a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NegateNumberTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * The result of the equals comparison.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param expectedResult
     *        the expected result
     */
    public NegateNumberTest(Number number, Number expectedResult) {

        super();

        this.number = number;
        this.expectedResult = expectedResult;
    }

    /**
     * Tests negating a number and checks the result.
     */
    @Test
    public void testNegateNumber() {

        Number actualResult = number.negate();

        assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests negating a number and checks the result.
     */
    @Test
    public void testNegateNumberVariant2() {

        Number actualResult = Math.negate(number);

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

        for (int base = MIN_BASE; base <= MAX_BASE; base++) {

            parameters.add(new Object[] { new NumberImpl(base), new NumberImpl(base, Signs.NEGATIVE) });
            parameters.add(new Object[] { new NumberImpl(base, Signs.NEGATIVE), new NumberImpl(base) });

            parameters.add(new Object[] { new NumberImpl(base, "0"), new NumberImpl(base, "-0") });
            parameters.add(new Object[] { new NumberImpl(base, "-0"), new NumberImpl(base, "0") });

            parameters.add(new Object[] { new NumberImpl(base, "1"), new NumberImpl(base, "-1") });
            parameters.add(new Object[] { new NumberImpl(base, "-1"), new NumberImpl(base, "1") });
        }

        return parameters;
    }

}

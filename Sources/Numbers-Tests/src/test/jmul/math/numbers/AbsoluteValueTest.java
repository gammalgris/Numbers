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

import jmul.math.Math;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This tests suite checks the calculation of absolute number values.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class AbsoluteValueTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * The expected result.
     */
    private final Number expectedAbsoluteValue;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param number
     *        a number.
     * @param expectedAbsoluteValue
     *        the expected result.
     */
    public AbsoluteValueTest(Number number, Number expectedAbsoluteValue) {

        super();

        this.number = number;
        this.expectedAbsoluteValue = expectedAbsoluteValue;
    }

    /**
     * Returns a summary of the test case.
     *
     * @return a summary of the test case
     */
    @Override
    public String toString() {

        return String.format("(%d) %s -> (%d) %s", number.base(), number, expectedAbsoluteValue.base(),
                             expectedAbsoluteValue);
    }

    /**
     * Calculates the abslute value and checks the correctness of the result.
     */
    @Test
    public void testAbsoluteValue() {

        Number absoluteValue = number.absoluteValue();

        assertEquals(toString(), expectedAbsoluteValue, absoluteValue);
        assertEquals(toString(), expectedAbsoluteValue.toString(), absoluteValue.toString());
    }

    /**
     * Calculates the abslute value and checks the correctness of the result.
     */
    @Test
    public void testAbsoluteValueVariant2() {

        Number absoluteValue = Math.absoluteValue(number);

        assertEquals(toString(), expectedAbsoluteValue, absoluteValue);
        assertEquals(toString(), expectedAbsoluteValue.toString(), absoluteValue.toString());
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

            parameters.add(new Object[] { createInfinity(base), createInfinity(base) });
            parameters.add(new Object[] { createNegativeInfinity(base), createInfinity(base) });

            parameters.add(new Object[] { createNumber(base, "0"), createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "-0"), createNumber(base, "0") });

            parameters.add(new Object[] { createNumber(base, "1"), createNumber(base, "1") });
            parameters.add(new Object[] { createNumber(base, "-1"), createNumber(base, "1") });
        }

        parameters.add(new Object[] { createNumber("1234567890"), createNumber("1234567890") });
        parameters.add(new Object[] { createNumber("-1234567890"), createNumber("1234567890") });

        parameters.add(new Object[] { createNumber("0.123456789"), createNumber("0.123456789") });
        parameters.add(new Object[] { createNumber("-0.123456789"), createNumber("0.123456789") });

        parameters.add(new Object[] { createNumber("1234567890.123456789"), createNumber("1234567890.123456789") });
        parameters.add(new Object[] { createNumber("-1234567890.123456789"), createNumber("1234567890.123456789") });

        return parameters;
    }

}

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
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import static jmul.math.numbers.Signs.NEGATIVE;

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
     * Calculates the abslute value and checks the correctness of the result.
     */
    @Test
    public void testAbsoluteValue() {

        Number absoluteValue = number.absoluteValue();

        String message =
            String.format("The absolute value doesn't match (number=%s; expected absolute value=%s; actual absolute value=%s)!",
                          number.toString(), expectedAbsoluteValue.toString(), absoluteValue.toString());
        assertEquals(message, expectedAbsoluteValue, absoluteValue);
        assertEquals(message, expectedAbsoluteValue.toString(), absoluteValue.toString());
    }

    /**
     * Calculates the abslute value and checks the correctness of the result.
     */
    @Test
    public void testAbsoluteValueVariant2() {

        Number absoluteValue = Math.absoluteValue(number);

        String message =
            String.format("The absolute value doesn't match (number=%s; expected absolute value=%s; actual absolute value=%s)!",
                          number.toString(), expectedAbsoluteValue.toString(), absoluteValue.toString());
        assertEquals(message, expectedAbsoluteValue, absoluteValue);
        assertEquals(message, expectedAbsoluteValue.toString(), absoluteValue.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { new NumberImpl(), new NumberImpl() });
        parameters.add(new Object[] { new NumberImpl(NEGATIVE), new NumberImpl() });

        parameters.add(new Object[] { new NumberImpl("0"), new NumberImpl("0") });
        parameters.add(new Object[] { new NumberImpl("-0"), new NumberImpl("0") });

        parameters.add(new Object[] { new NumberImpl("1"), new NumberImpl("1") });
        parameters.add(new Object[] { new NumberImpl("-1"), new NumberImpl("1") });

        parameters.add(new Object[] { new NumberImpl("1"), new NumberImpl("1") });
        parameters.add(new Object[] { new NumberImpl("-1"), new NumberImpl("1") });

        parameters.add(new Object[] { new NumberImpl("1234567890"), new NumberImpl("1234567890") });
        parameters.add(new Object[] { new NumberImpl("-1234567890"), new NumberImpl("1234567890") });

        parameters.add(new Object[] { new NumberImpl("0.123456789"), new NumberImpl("0.123456789") });
        parameters.add(new Object[] { new NumberImpl("-0.123456789"), new NumberImpl("0.123456789") });

        parameters.add(new Object[] { new NumberImpl("1234567890.123456789"), new NumberImpl("1234567890.123456789") });
        parameters.add(new Object[] { new NumberImpl("-1234567890.123456789"),
                                      new NumberImpl("1234567890.123456789") });

        return parameters;
    }

}

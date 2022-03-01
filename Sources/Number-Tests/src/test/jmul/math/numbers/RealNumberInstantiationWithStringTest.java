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
 * This test suite tests instantiation of a real number with string values.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberInstantiationWithStringTest {

    /**
     * The actual input.
     */
    private final String input;

    /**
     * The expected string representation.
     */
    private final String expectedOutput;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param anInput
     * @param theExpectedOutput
     */
    public RealNumberInstantiationWithStringTest(String anInput, String theExpectedOutput) {

        input = anInput;
        expectedOutput = theExpectedOutput;
    }

    /**
     * Tests the instantiation and the generation of a string representation (i.e. standard notation).
     */
    @Test
    public void testInstantiationAndStringRepresentation() {

        String message = String.format("Test failed for %s!", input);

        //RealNumber number = RealNumber.parseString(input);
        RealDecimalNumber number = new RealDecimalNumber(input);
        //assertEquals(message, expectedOutput, number.toStandardNotation());
        //assertEquals(message, expectedOutput, number.toString());

        assertEquals(message, false, number.isInfinity());

        if (StringHelper.isNegative(input)) {

            assertEquals(message, false, number.isPositive());
            assertEquals(message, true, number.isNegative());

        } else {

            assertEquals(message, true, number.isPositive());
            assertEquals(message, false, number.isNegative());
        }

        int expectedDigitCount = StringHelper.digits(input);
        int expectedLeftDigitCount = StringHelper.digitsLeft(input);
        int expectedRightDigitCount = StringHelper.digitsRight(input);
        int actualDigitCount = number.digits();
        int actualLeftDigitCount = number.leftDigits();
        int actualRightDigitCount = number.rightDigits();
        assertEquals(message, expectedDigitCount, actualDigitCount);
        assertEquals(message, expectedLeftDigitCount, actualLeftDigitCount);
        assertEquals(message, expectedRightDigitCount, actualRightDigitCount);

        assertEquals(message, expectedOutput, number.toStandardNotation());
        assertEquals(message, expectedOutput, number.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "0", "0" });
        parameters.add(new Object[] { "1", "1" });
        parameters.add(new Object[] { "+0", "0" });
        parameters.add(new Object[] { "+1", "1" });
        parameters.add(new Object[] { "-0", "-0" });
        parameters.add(new Object[] { "-1", "-1" });
        parameters.add(new Object[] { "12", "12" });
        parameters.add(new Object[] { "-12", "-12" });

        parameters.add(new Object[] { "" + Integer.MAX_VALUE, "2147483647" });
        parameters.add(new Object[] { "" + Integer.MIN_VALUE, "-2147483648" });
        parameters.add(new Object[] { "" + Long.MAX_VALUE, "9223372036854775807" });
        parameters.add(new Object[] { "" + Long.MIN_VALUE, "-9223372036854775808" });

        parameters.add(new Object[] { "0.1", "0.1" });
        parameters.add(new Object[] { "0.12", "0.12" });
        parameters.add(new Object[] { "0.123", "0.123" });
        parameters.add(new Object[] { "-0.1", "-0.1" });
        parameters.add(new Object[] { "-0.12", "-0.12" });
        parameters.add(new Object[] { "-0.123", "-0.123" });

        parameters.add(new Object[] { "0,1", "0.1" });
        parameters.add(new Object[] { "0,12", "0.12" });
        parameters.add(new Object[] { "0,123", "0.123" });
        parameters.add(new Object[] { "-0,1", "-0.1" });
        parameters.add(new Object[] { "-0,12", "-0.12" });
        parameters.add(new Object[] { "-0,123", "-0.123" });

        parameters.add(new Object[] { "00,12300", "0.123" });
        parameters.add(new Object[] { "-00,12300", "-0.123" });
        parameters.add(new Object[] { "011", "11" });
        parameters.add(new Object[] { "-011", "-11" });

        parameters.add(new Object[] { "1.10E2", "110" });
        parameters.add(new Object[] { "1.10E1", "11" });

        parameters.add(new Object[] { "1.10E-1", "0.11" });
        parameters.add(new Object[] { "1.2304563211E-20", "0.000000000000000000012304563211" });
        parameters.add(new Object[] { "1.230456321100000001E-80", "0.00000000000000000000000000000000000000000000000000000000000000000000000000000001230456321100000001" });
        parameters.add(new Object[] { "1.2304563211E6", "1230456.3211" });

        return parameters;
    }

}

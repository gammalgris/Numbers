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
 * This test suite tests instantiation of a real number with double values.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberInstantiationWithDoubleTest {

    /**
     * The actual input.
     */
    private final double input;

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
    public RealNumberInstantiationWithDoubleTest(double anInput, String theExpectedOutput) {

        input = anInput;
        expectedOutput = theExpectedOutput;
    }

    /**
     * Tests the instantiation and the generation of a string representation (i.e. standard notation).
     */
    @Test
    public void testInstantiationAndStringRepresentation() {

        String message = String.format("Test failed for %f!", input);

        RealDecimalNumber number = new RealDecimalNumber(input);
        assertEquals(message, expectedOutput, number.toStandardNotation());
        assertEquals(message, expectedOutput, number.toString());

        assertEquals(message, false, number.isInfinity());

        if (input < 0) {

            assertEquals(message, false, number.isPositive());
            assertEquals(message, true, number.isNegative());

        } else {

            assertEquals(message, true, number.isPositive());
            assertEquals(message, false, number.isNegative());
        }

        String s = String.valueOf(input);
        int expectedDigitCount = StringHelper.digits(s);
        int expectedLeftDigitCount = StringHelper.digitsLeft(s);
        int expectedRightDigitCount = StringHelper.digitsRight(s);
        assertEquals(message, expectedDigitCount, number.digits());
        assertEquals(message, expectedLeftDigitCount, number.leftDigits());
        assertEquals(message, expectedRightDigitCount, number.rightDigits());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        double step = 0.91001D;
        for (double a = -100D; a <= 100D; a = a + step) {

            parameters.add(new Object[] { a, "" + a });
        }

        parameters.add(new Object[] { Double.MAX_VALUE, "" + Double.MAX_VALUE });
        parameters.add(new Object[] { Double.MIN_VALUE, "" + Double.MIN_VALUE });

        return parameters;
    }

}

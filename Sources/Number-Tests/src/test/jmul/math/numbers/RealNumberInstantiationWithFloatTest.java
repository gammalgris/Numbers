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
 * This test suite tests instantiation of a real number with float values.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberInstantiationWithFloatTest {

    /**
     * The actual input.
     */
    private final float input;

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
    public RealNumberInstantiationWithFloatTest(float anInput, String theExpectedOutput) {

        input = anInput;
        expectedOutput = theExpectedOutput;
    }

    /**
     * Tests the instantiation and the generation of a string representation (i.e. standard notation).
     */
    @Test
    public void testInstantiationAndStringRepresentation() {

        String message = String.format("Test failed for %f!", input);

        RealNumber number = new RealNumber(input);

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

        parameters.add(new Object[] { 123.456F, "123.456" });
        parameters.add(new Object[] { 123F, "123.0" });

        float step = 0.91001F;
        for (float a = -100F; a <= 100F; a = a + step) {

            parameters.add(new Object[] { a, "" + a });
        }

        parameters.add(new Object[] { Float.MAX_VALUE, "" + Float.MAX_VALUE });
        parameters.add(new Object[] { Float.MIN_VALUE, "" + Float.MIN_VALUE });

        return parameters;
    }

}

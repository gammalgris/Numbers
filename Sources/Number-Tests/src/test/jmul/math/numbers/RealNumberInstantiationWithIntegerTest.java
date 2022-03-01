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

import jmul.math.numbers.digits.DecimalDigits;
import jmul.math.numbers.RealDecimalNumber;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;


/**
 * This test suite tests instantiation of a real number with int values.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberInstantiationWithIntegerTest {

    /**
     * The actual input.
     */
    private final int input;

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
    public RealNumberInstantiationWithIntegerTest(int anInput, String theExpectedOutput) {

        input = anInput;
        expectedOutput = theExpectedOutput;
    }

    /**
     * Tests the instantiation and the generation of a string representation (i.e. standard notation).
     */
    @Test
    public void testInstantiationAndStringRepresentation() {

        String message = String.format("Test failed for %d!", input);

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

        int expectedDigitCount = digits(input);
        int actualDigitCount = number.digits();
        int actualLeftDigitCount = number.leftDigits();
        int actualRightDigitCount = number.rightDigits();
        assertEquals(message, expectedDigitCount, actualDigitCount);
        assertEquals(message, expectedDigitCount, actualLeftDigitCount);
        assertEquals(message, 0, actualRightDigitCount);
    }

    /**
     * Returns the number of digits for the specified integer.
     *
     * @param i
     *        an integer
     *
     * @return a digit count
     */
    private int digits(int i) {

        if (Integer.MIN_VALUE == i) {

            return 10; // see -2147483648 <= Math.abs() doesn't work with this value and you don't get an error!
        }

        int digits = 0;

        int normalized = Math.abs(i);

        while (normalized > 0) {

            normalized = normalized / DecimalDigits.ZERO.base();
            digits++;
        }

        if (digits == 0) {

            digits++;
        }

        return digits;
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int a = -100; a <= 100; a++) {

            parameters.add(new Object[] { a, "" + a });
        }

        parameters.add(new Object[] { Integer.MAX_VALUE, "" + Integer.MAX_VALUE });
        parameters.add(new Object[] { Integer.MIN_VALUE - 1, "" + (Integer.MIN_VALUE - 1) });
        parameters.add(new Object[] { Integer.MIN_VALUE, "" + (Integer.MIN_VALUE) });

        return parameters;
    }

}

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
import jmul.math.numbers.RealNumber;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests instantiation of a real number with long values.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberInstantiationWithLongTest {

    /**
     * The actual input.
     */
    private final long input;

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
    public RealNumberInstantiationWithLongTest(long anInput, String theExpectedOutput) {

        input = anInput;
        expectedOutput = theExpectedOutput;
    }

    /**
     * Tests the instantiation and the generation of a string representation (i.e. standard notation).
     */
    @Test
    public void testInstantiationAndStringRepresentation() {

        String message = String.format("Test failed for %d!", input);

        RealNumber number = new RealNumber(input);
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
        assertEquals(message, expectedDigitCount, number.digits());
        assertEquals(message, expectedDigitCount, number.leftDigits());
        assertEquals(message, 0, number.rightDigits());
    }

    /**
     * Returns the number of digits for the specified long value.
     *
     * @param l
     *        a long value
     *
     * @return a digit count
     */
    private int digits(long l) {

        if (Long.MIN_VALUE == l) {

            return 19; // see  -9223372036854775808 <= Math.abs() doesn't work with this value and you don't get an error!
        }

        int digits = 0;

        long normalized = Math.abs(l);

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

        for (long a = -100L; a <= 100L; a++) {

            parameters.add(new Object[] { a, "" + a });
        }

        parameters.add(new Object[] { Long.MAX_VALUE, "" + Long.MAX_VALUE });
        parameters.add(new Object[] { Long.MIN_VALUE - 1, "" + (Long.MIN_VALUE - 1) });
        parameters.add(new Object[] { Long.MIN_VALUE, "" + (Long.MIN_VALUE) });

        return parameters;
    }

}

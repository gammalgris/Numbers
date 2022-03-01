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
import jmul.math.numbers.digits.Digit;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests instantiation of a real number with int strings and inspecting
 * individual digits.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberDigitAtTest {

    /**
     * The actual input.
     */
    private final String input;

    /**
     * An index.
     */
    private final int index;

    /**
     * The expected character at the specified index position.
     */
    private final char expectedOutput;

    /**
     * Creates a new test case according to the specified numbers.
     *
     * @param anInput
     * @param anIndex
     * @param theExpectedOutput
     */
    public RealNumberDigitAtTest(String anInput, int anIndex, char theExpectedOutput) {

        input = anInput;
        index = anIndex;
        expectedOutput = theExpectedOutput;
    }

    /**
     * Tests instantiation of a real number with int strings and inspecting
     * individual digits.
     */
    @Test
    public void testDigitAt() {

        String message = String.format("Test failed (%s; index=%d; expected digit=%s)", input, index, expectedOutput);

        RealDecimalNumber number = new RealDecimalNumber(input);
        Digit digit = number.digitAt(index);
        char symbol = digit.symbol();

        assertEquals(message, expectedOutput, symbol);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { "0", 1, '0' });
        parameters.add(new Object[] { "1", 1, '1' });
        parameters.add(new Object[] { "-1", 1, '1' });

        parameters.add(new Object[] { "1", 2, '0' });
        parameters.add(new Object[] { "1", 3, '0' });
        parameters.add(new Object[] { "1", 4, '0' });
        parameters.add(new Object[] { "1", 5, '0' });
        parameters.add(new Object[] { "1", 6, '0' });
        parameters.add(new Object[] { "1", 7, '0' });
        parameters.add(new Object[] { "1", 8, '0' });
        parameters.add(new Object[] { "1", 9, '0' });
        parameters.add(new Object[] { "1", 10, '0' });

        parameters.add(new Object[] { "1", -1, '0' });
        parameters.add(new Object[] { "1", -2, '0' });
        parameters.add(new Object[] { "1", -3, '0' });
        parameters.add(new Object[] { "1", -4, '0' });
        parameters.add(new Object[] { "1", -5, '0' });
        parameters.add(new Object[] { "1", -6, '0' });
        parameters.add(new Object[] { "1", -7, '0' });
        parameters.add(new Object[] { "1", -8, '0' });
        parameters.add(new Object[] { "1", -9, '0' });

        parameters.add(new Object[] { "12", 1, '2' });
        parameters.add(new Object[] { "12", 2, '1' });
        parameters.add(new Object[] { "-12", 1, '2' });
        parameters.add(new Object[] { "-12", 2, '1' });

        parameters.add(new Object[] { "123", 1, '3' });
        parameters.add(new Object[] { "123", 2, '2' });
        parameters.add(new Object[] { "123", 3, '1' });
        parameters.add(new Object[] { "-123", 1, '3' });
        parameters.add(new Object[] { "-123", 2, '2' });
        parameters.add(new Object[] { "-123", 3, '1' });

        parameters.add(new Object[] { "1.2", 1, '1' });
        parameters.add(new Object[] { "1.2", -1, '2' });

        parameters.add(new Object[] { "12.34", 2, '1' });
        parameters.add(new Object[] { "12.34", 1, '2' });
        parameters.add(new Object[] { "12.34", -1, '3' });
        parameters.add(new Object[] { "12.34", -2, '4' });

        parameters.add(new Object[] { "123.456", 3, '1' });
        parameters.add(new Object[] { "123.456", 2, '2' });
        parameters.add(new Object[] { "123.456", 1, '3' });
        parameters.add(new Object[] { "123.456", -1, '4' });
        parameters.add(new Object[] { "123.456", -2, '5' });
        parameters.add(new Object[] { "123.456", -3, '6' });

        parameters.add(new Object[] { "0.123456", 3, '0' });
        parameters.add(new Object[] { "0.123456", 2, '0' });
        parameters.add(new Object[] { "0.123456", 1, '0' });
        parameters.add(new Object[] { "0.123456", -1, '1' });
        parameters.add(new Object[] { "0.123456", -2, '2' });
        parameters.add(new Object[] { "0.123456", -3, '3' });
        parameters.add(new Object[] { "0.123456", -4, '4' });
        parameters.add(new Object[] { "0.123456", -5, '5' });
        parameters.add(new Object[] { "0.123456", -6, '6' });
        parameters.add(new Object[] { "0.123456", -7, '0' });
        parameters.add(new Object[] { "0.123456", -8, '0' });

        parameters.add(new Object[] { "0.0123456", 2, '0' });
        parameters.add(new Object[] { "0.0123456", 1, '0' });
        parameters.add(new Object[] { "0.0123456", -1, '0' });
        parameters.add(new Object[] { "0.0123456", -2, '1' });
        parameters.add(new Object[] { "0.0123456", -3, '2' });
        parameters.add(new Object[] { "0.0123456", -4, '3' });
        parameters.add(new Object[] { "0.0123456", -5, '4' });
        parameters.add(new Object[] { "0.0123456", -6, '5' });
        parameters.add(new Object[] { "0.0123456", -7, '6' });
        parameters.add(new Object[] { "0.0123456", -8, '0' });
        parameters.add(new Object[] { "0.0123456", -9, '0' });

        parameters.add(new Object[] { "0.00123456", 1, '0' });
        parameters.add(new Object[] { "0.00123456", -1, '0' });
        parameters.add(new Object[] { "0.00123456", -2, '0' });
        parameters.add(new Object[] { "0.00123456", -3, '1' });
        parameters.add(new Object[] { "0.00123456", -4, '2' });
        parameters.add(new Object[] { "0.00123456", -5, '3' });
        parameters.add(new Object[] { "0.00123456", -6, '4' });
        parameters.add(new Object[] { "0.00123456", -7, '5' });
        parameters.add(new Object[] { "0.00123456", -8, '6' });
        parameters.add(new Object[] { "0.00123456", -9, '0' });
        parameters.add(new Object[] { "0.00123456", -10, '0' });

        return parameters;
    }

}

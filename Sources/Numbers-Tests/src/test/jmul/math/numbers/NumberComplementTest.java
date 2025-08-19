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

import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests calculating the number complement.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NumberComplementTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * The expected result.
     */
    private final Number expectedComplementValue;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param number
     *        a number.
     * @param expectedComplementValue
     *        the expected result.
     */
    public NumberComplementTest(Number number, Number expectedComplementValue) {

        super();

        this.number = number;
        this.expectedComplementValue = expectedComplementValue;
    }

    /**
     * Calculates the abslute value and checks the correctness of the result.
     */
    @Test
    public void testAbsoluteValue() {

        Number complement = number.complement();

        String message =
            String.format("The complement value doesn't match (number=%s; expected complement value=%s; actual complement value=%s)!",
                          number.toString(), expectedComplementValue.toString(), complement.toString());
        assertEquals(message, expectedComplementValue, complement);
        assertEquals(message, expectedComplementValue.toString(), complement.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { createNumber(9, "0"), createNumber(9, "8") });
        parameters.add(new Object[] { createNumber(9, "1"), createNumber(9, "7") });
        parameters.add(new Object[] { createNumber(9, "2"), createNumber(9, "6") });
        parameters.add(new Object[] { createNumber(9, "3"), createNumber(9, "5") });
        parameters.add(new Object[] { createNumber(9, "4"), createNumber(9, "4") });
        parameters.add(new Object[] { createNumber(9, "5"), createNumber(9, "3") });
        parameters.add(new Object[] { createNumber(9, "6"), createNumber(9, "2") });
        parameters.add(new Object[] { createNumber(9, "7"), createNumber(9, "1") });

        parameters.add(new Object[] { createNumber(9, "1234567801"), createNumber(9, "7654321087") });

        parameters.add(new Object[] { createNumber(9, "0.123456781"), createNumber(9, "8.765432107") });


        parameters.add(new Object[] { createNumber("0"), createNumber("9") });
        parameters.add(new Object[] { createNumber("1"), createNumber("8") });
        parameters.add(new Object[] { createNumber("2"), createNumber("7") });
        parameters.add(new Object[] { createNumber("3"), createNumber("6") });
        parameters.add(new Object[] { createNumber("4"), createNumber("5") });
        parameters.add(new Object[] { createNumber("5"), createNumber("4") });
        parameters.add(new Object[] { createNumber("6"), createNumber("3") });
        parameters.add(new Object[] { createNumber("7"), createNumber("2") });
        parameters.add(new Object[] { createNumber("8"), createNumber("1") });
        parameters.add(new Object[] { createNumber("9"), createNumber("0") });

        parameters.add(new Object[] { createNumber("12345678901"), createNumber("87654321098") });

        parameters.add(new Object[] { createNumber("0.1234567891"), createNumber("9.8765432108") });

        return parameters;
    }

}

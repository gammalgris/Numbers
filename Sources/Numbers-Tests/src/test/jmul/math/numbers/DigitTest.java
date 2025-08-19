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

import jmul.math.numbers.Constants;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.numbers.NumberHelper.parseByte;
import static jmul.math.numbers.NumberHelper.parseDouble;
import static jmul.math.numbers.NumberHelper.parseFloat;
import static jmul.math.numbers.NumberHelper.parseInteger;
import static jmul.math.numbers.NumberHelper.parseLong;
import static jmul.math.numbers.NumberHelper.parseShort;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the digit count of a number (i.e. digits left and right of the decimal separator).
 * Numbers are base 10.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DigitTest {

    /**
     * The base for the number.
     */
    private final int base;

    /**
     *A number.
     */
    private final Number number;

    /**
     * The expected digit count left of a decimal separator.
     */
    private final Number digitsLeft;

    /**
     * The expected digit count right of a decimal separator.
     */
    private final Number digitsRight;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param input
     *        an input
     * @param digitsLeft
     *        digits left of a decimal separator
     * @param digitsRight
     *        digits right of a decimal separator
     */
    public DigitTest(Integer base, Object input, Object digitsLeft, Object digitsRight) {

        super();

        if (base == null) {

            this.base = Constants.DEFAULT_NUMBER_BASE;

        } else {

            this.base = base;
        }

        this.digitsLeft = newNumber(this.base, digitsLeft);
        this.digitsRight = newNumber(this.base, digitsRight);
        this.number = newNumber(this.base, input);
    }

    /**
     * Creates a number according to the specified parameters.
     *
     * @param base
     *        a number base
     * @param input
     *        some input
     *
     * @return a number
     */
    private static Number newNumber(int base, Object input) {

        if (input == null) {

            return createInfinity(base);

        } else if (input instanceof String) {

            String inputString = (String) input;
            return createNumber(base, inputString);

        } else if (input instanceof Byte) {

            byte b = ((Byte) input);
            return parseByte(b);

        } else if (input instanceof Short) {

            short s = ((Short) input);
            return parseShort(s);

        } else if (input instanceof Integer) {

            int i = ((Integer) input);
            return parseInteger(i);

        } else if (input instanceof Long) {

            long l = ((Long) input);
            return parseLong(l);

        } else if (input instanceof Float) {

            float f = ((Float) input);
            return parseFloat(f);

        } else if (input instanceof Double) {

            double d = ((Double) input);
            return parseDouble(d);

        } else {

            throw new RuntimeException("Oops!");
        }
    }

    /**
     * Checks the digits of a number.
     */
    @Test
    public void testDigits() {

        Number actualLeftDigits = number.digitsLeft();
        Number actualRightDigits = number.digitsRight();

        {
            String message = String.format("The left digits don't match (%s)!", number);
            assertEquals(message, digitsLeft, actualLeftDigits);
        }

        {
            String message = String.format("The right digits don't match (%s)!", number);
            assertEquals(message, digitsRight, actualRightDigits);
        }
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { null, "1", 1, 0 });
        parameters.add(new Object[] { null, "0", 1, 0 });
        parameters.add(new Object[] { null, "-1", 1, 0 });

        parameters.add(new Object[] { null, "1.00", 1, 0 });
        parameters.add(new Object[] { null, "0.00", 1, 0 });
        parameters.add(new Object[] { null, "-1.00", 1, 0 });

        parameters.add(new Object[] { null, "1.1E0", 1, 1 });
        parameters.add(new Object[] { null, "-1.1E0", 1, 1 });
        parameters.add(new Object[] { null, "1.1E-0", 1, 1 });
        parameters.add(new Object[] { null, "-1.1E-0", 1, 1 });

        parameters.add(new Object[] { null, "1.1E1", 2, 0 });
        parameters.add(new Object[] { null, "-1.1E1", 2, 0 });
        parameters.add(new Object[] { null, "1.1E-1", 1, 2 });
        parameters.add(new Object[] { null, "-1.1E-1", 1, 2 });

        parameters.add(new Object[] { null, "1.1E2", 3, 0 });
        parameters.add(new Object[] { null, "-1.1E2", 3, 0 });
        parameters.add(new Object[] { null, "1.1E-2", 1, 3 });
        parameters.add(new Object[] { null, "-1.1E-2", 1, 3 });

        parameters.add(new Object[] { null, "1.1E3", 4, 0 });
        parameters.add(new Object[] { null, "-1.1E3", 4, 0 });
        parameters.add(new Object[] { null, "1.1E-3", 1, 4 });
        parameters.add(new Object[] { null, "-1.1E-3", 1, 4 });

        parameters.add(new Object[] { null, "1.010", 1, 2 });
        parameters.add(new Object[] { null, "0.010", 1, 2 });
        parameters.add(new Object[] { null, "-1.010", 1, 2 });

        parameters.add(new Object[] { null, "1.1", 1, 1 });
        parameters.add(new Object[] { null, "+1.1", 1, 1 });
        parameters.add(new Object[] { null, "-1.1", 1, 1 });

        parameters.add(new Object[] { null, "21.12", 2, 2 });
        parameters.add(new Object[] { null, "+21.12", 2, 2 });
        parameters.add(new Object[] { null, "-21.12", 2, 2 });

        parameters.add(new Object[] { null, "321.123", 3, 3 });
        parameters.add(new Object[] { null, "+321.123", 3, 3 });
        parameters.add(new Object[] { null, "-321.123", 3, 3 });

        parameters.add(new Object[] { null, new Byte((byte) 0), 1, 0 });
        parameters.add(new Object[] { null, new Byte((byte) 1), 1, 0 });
        parameters.add(new Object[] { null, new Byte((byte) 2), 1, 0 });
        parameters.add(new Object[] { null, Byte.MIN_VALUE, 3, 0 });
        parameters.add(new Object[] { null, Byte.MAX_VALUE, 3, 0 });

        parameters.add(new Object[] { null, new Short((short) 0), 1, 0 });
        parameters.add(new Object[] { null, new Short((short) 1), 1, 0 });
        parameters.add(new Object[] { null, new Short((short) 2), 1, 0 });
        parameters.add(new Object[] { null, Short.MIN_VALUE, 5, 0 });
        parameters.add(new Object[] { null, Short.MAX_VALUE, 5, 0 });

        parameters.add(new Object[] { null, new Integer((int) 0), 1, 0 });
        parameters.add(new Object[] { null, new Integer((int) 1), 1, 0 });
        parameters.add(new Object[] { null, new Integer((int) 2), 1, 0 });
        parameters.add(new Object[] { null, Integer.MIN_VALUE, 10, 0 });
        parameters.add(new Object[] { null, Integer.MAX_VALUE, 10, 0 });

        parameters.add(new Object[] { null, new Long(0L), 1, 0 });
        parameters.add(new Object[] { null, new Long(1L), 1, 0 });
        parameters.add(new Object[] { null, new Long(2L), 1, 0 });
        parameters.add(new Object[] { null, Long.MIN_VALUE, 19, 0 });
        parameters.add(new Object[] { null, Long.MAX_VALUE, 19, 0 });

        parameters.add(new Object[] { null, new Float(0F), 1, 0 });
        parameters.add(new Object[] { null, new Float(1F), 1, 0 });
        parameters.add(new Object[] { null, new Float(2F), 1, 0 });
        parameters.add(new Object[] { null, Float.MIN_VALUE, 1, 46 });
        parameters.add(new Object[] { null, Float.MAX_VALUE, 39, 0 });

        parameters.add(new Object[] { null, new Double(0D), 1, 0 });
        parameters.add(new Object[] { null, new Double(1D), 1, 0 });
        parameters.add(new Object[] { null, new Double(2D), 1, 0 });
        parameters.add(new Object[] { null, Double.MIN_VALUE, 1, 325 });
        parameters.add(new Object[] { null, Double.MAX_VALUE, 309, 0 });

        parameters.add(new Object[] { 2, "10.11111000001", "10", "1011" });

        return parameters;
    }

}

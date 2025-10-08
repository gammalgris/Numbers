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


import java.lang.reflect.InvocationTargetException;

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

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests creating numbers with various input parameters.
 *
 * TODO add tests for numbers with bases besides 16, 10, 8 and 2
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class CreateNumberTest {

    /**
     * The base for the number.
     */
    private final int base;

    /**
     * The input (i.e. a number string in supported notations, a number wrapper or primitive number)
     */
    private final Number number;

    /**
     * The expected standard notation.
     */
    private final String expectedStandardNotation;

    /**
     * The expected scientific notation.
     */
    private final String expectedScientificNotation;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param base
     *        a number base (optional parameter, may be <code>null</code> for the default number base)
     * @param input
     *        an input
     * @param expectedStandardNotation
     *        the expected standard notation
     * @param expectedScientificNotation
     *        the expected scientific notation
     */
    public CreateNumberTest(Integer base, Object input, String expectedStandardNotation,
                            String expectedScientificNotation) {

        super();

        if (base == null) {

            this.base = Constants.DEFAULT_NUMBER_BASE;

        } else {

            this.base = base;
        }

        this.expectedStandardNotation = expectedStandardNotation;
        this.expectedScientificNotation = expectedScientificNotation;

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
     * Creates an error message accroding to the specified parameters.
     *
     * @param base
     *        a number base (optional parameter, can be <code>null</code> the default number base is used)
     * @param input
     *        the actual impout for creating a number (may be a string, a number wrapper or primitive type)
     * @param actualNotation
     *        the actual notation
     * @param expectedNotation
     *        the expected notation
     *
     * @return an error message
     */
    private static String createErrorMessage(Integer base, Object input, String actualNotation,
                                             String expectedNotation) {

        return String.format("The number (base='%s'; input='%s'; actual notation='%s'; expected notation='%s') was not correctly created!",
                             base, input, actualNotation, expectedNotation);
    }

    /**
     * Creates a number and compares the default notation with the expected default notation.
     *
     * @throws NoSuchMethodException
     *         is thrown if there exists no suitable constructor. If the exception is thrown something went wrong.
     * @throws InstantiationException
     *         is thrown if an error occurs within the constructor. If the exception is thrown something went wrong.
     * @throws IllegalAccessException
     *         is thrown if the constructor cannot be accessed. If the exception is thrown something went wrong.
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the constructor. If the exception is thrown something went wrong.
     */
    @Test
    public void testNumberCreationAndDefaultNotation() throws NoSuchMethodException, InstantiationException,
                                                              IllegalAccessException, InvocationTargetException {

        String actualDefaultNotation = number.toString();

        String message = createErrorMessage(base, number, actualDefaultNotation, expectedStandardNotation);
        assertEquals(message, expectedStandardNotation, actualDefaultNotation);
    }

    /**
     * Creates a number and compares the standard notation with the expected standard notation.
     *
     * @throws NoSuchMethodException
     *         is thrown if there exists no suitable constructor. If the exception is thrown something went wrong.
     * @throws InstantiationException
     *         is thrown if an error occurs within the constructor. If the exception is thrown something went wrong.
     * @throws IllegalAccessException
     *         is thrown if the constructor cannot be accessed. If the exception is thrown something went wrong.
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the constructor. If the exception is thrown something went wrong.
     */
    @Test
    public void testNumberCreationAndStandardNotation() throws NoSuchMethodException, InstantiationException,
                                                               IllegalAccessException, InvocationTargetException {

        String actualStandardNotation = number.toStandardNotation();

        String message = createErrorMessage(base, number, actualStandardNotation, expectedStandardNotation);
        assertEquals(message, expectedStandardNotation, actualStandardNotation);
    }

    /**
     * Creates a number and compares the scientific notation with the expected scientific notation.
     *
     * @throws NoSuchMethodException
     *         is thrown if there exists no suitable constructor. If the exception is thrown something went wrong.
     * @throws InstantiationException
     *         is thrown if an error occurs within the constructor. If the exception is thrown something went wrong.
     * @throws IllegalAccessException
     *         is thrown if the constructor cannot be accessed. If the exception is thrown something went wrong.
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the constructor. If the exception is thrown something went wrong.
     */
    @Test
    public void testNumberCreationAndScientificNotation() throws NoSuchMethodException, InstantiationException,
                                                                 IllegalAccessException, InvocationTargetException {

        String actualScientificNotation = number.toScientificNotation();

        String message = createErrorMessage(base, number, actualScientificNotation, expectedScientificNotation);
        assertEquals(message, expectedScientificNotation, actualScientificNotation);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = Constants.BASE_MIN_LIMIT; base <= 14; base++) {

            parameters.add(new Object[] { base, "0", "0", "0" });
            parameters.add(new Object[] { base, "-0", "0", "0" });

            parameters.add(new Object[] { base, "0.0", "0", "0" });
            parameters.add(new Object[] { base, "-0.0", "0", "0" });

            parameters.add(new Object[] { base, "1", "1", "1E0" });
            parameters.add(new Object[] { base, "-1", "-1", "-1E0" });

            parameters.add(new Object[] { base, "1.1", "1.1", "1.1E0" });
            parameters.add(new Object[] { base, "+1.1", "1.1", "1.1E0" });
            parameters.add(new Object[] { base, "-1.1", "-1.1", "-1.1E0" });
        }

        for (int base = 15; base <= Constants.BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base, "0", "0", "0" });
            parameters.add(new Object[] { base, "-0", "0", "0" });

            parameters.add(new Object[] { base, "0.0", "0", "0" });
            parameters.add(new Object[] { base, "-0.0", "0", "0" });

            parameters.add(new Object[] { base, "1", "1", "1E+0" });
            parameters.add(new Object[] { base, "-1", "-1", "-1E+0" });

            parameters.add(new Object[] { base, "1.1", "1.1", "1.1E+0" });
            parameters.add(new Object[] { base, "+1.1", "1.1", "1.1E+0" });
            parameters.add(new Object[] { base, "-1.1", "-1.1", "-1.1E+0" });
        }

        parameters.add(new Object[] { 10, "1.1E0", "1.1", "1.1E0" });
        parameters.add(new Object[] { 10, "-1.1E0", "-1.1", "-1.1E0" });

        parameters.add(new Object[] { 10, "1.1E1", "11", "1.1E1" });
        parameters.add(new Object[] { 10, "-1.1E1", "-11", "-1.1E1" });
        parameters.add(new Object[] { 10, "1.1E-1", "0.11", "1.1E-1" });
        parameters.add(new Object[] { 10, "-1.1E-1", "-0.11", "-1.1E-1" });

        parameters.add(new Object[] { 10, "1.1E2", "110", "1.1E2" });
        parameters.add(new Object[] { 10, "-1.1E2", "-110", "-1.1E2" });
        parameters.add(new Object[] { 10, "1.1E-2", "0.011", "1.1E-2" });
        parameters.add(new Object[] { 10, "-1.1E-2", "-0.011", "-1.1E-2" });

        parameters.add(new Object[] { 10, "1.1E3", "1100", "1.1E3" });
        parameters.add(new Object[] { 10, "-1.1E3", "-1100", "-1.1E3" });
        parameters.add(new Object[] { 10, "1.1E-3", "0.0011", "1.1E-3" });
        parameters.add(new Object[] { 10, "-1.1E-3", "-0.0011", "-1.1E-3" });

        parameters.add(new Object[] { 10, "1.010", "1.01", "1.01E0" });

        parameters.add(new Object[] { 10, "21.12", "21.12", "2.112E1" });
        parameters.add(new Object[] { 10, "+21.12", "21.12", "2.112E1" });
        parameters.add(new Object[] { 10, "-21.12", "-21.12", "-2.112E1" });

        parameters.add(new Object[] { 10, "321.123", "321.123", "3.21123E2" });
        parameters.add(new Object[] { 10, "+321.123", "321.123", "3.21123E2" });
        parameters.add(new Object[] { 10, "-321.123", "-321.123", "-3.21123E2" });


        parameters.add(new Object[] { null, new Byte((byte) 1), "1", "1E0" });
        parameters.add(new Object[] { null, new Byte((byte) 2), "2", "2E0" });
        parameters.add(new Object[] { null, Byte.MIN_VALUE, "-128", "-1.28E2" });
        parameters.add(new Object[] { null, Byte.MAX_VALUE, "127", "1.27E2" });

        parameters.add(new Object[] { null, new Short((short) 1), "1", "1E0" });
        parameters.add(new Object[] { null, new Short((short) 2), "2", "2E0" });
        parameters.add(new Object[] { null, Short.MIN_VALUE, "-32768", "-3.2768E4" });
        parameters.add(new Object[] { null, Short.MAX_VALUE, "32767", "3.2767E4" });

        parameters.add(new Object[] { null, new Integer((int) 1), "1", "1E0" });
        parameters.add(new Object[] { null, new Integer((int) 2), "2", "2E0" });
        parameters.add(new Object[] { null, Integer.MIN_VALUE, "-2147483648", "-2.147483648E9" });
        parameters.add(new Object[] { null, Integer.MAX_VALUE, "2147483647", "2.147483647E9" });

        parameters.add(new Object[] { null, new Long(1L), "1", "1E0" });
        parameters.add(new Object[] { null, new Long(2L), "2", "2E0" });
        parameters.add(new Object[] { null, Long.MIN_VALUE, "-9223372036854775808", "-9.223372036854775808E18" });
        parameters.add(new Object[] { null, Long.MAX_VALUE, "9223372036854775807", "9.223372036854775807E18" });

        parameters.add(new Object[] { null, new Float(1F), "1", "1E0" });
        parameters.add(new Object[] { null, new Float(2F), "2", "2E0" });
        parameters.add(new Object[] { null, Float.MIN_VALUE, "0.0000000000000000000000000000000000000000000014",
                                      "1.4E-45" });
        parameters.add(new Object[] { null, Float.MAX_VALUE, "340282350000000000000000000000000000000",
                                      "3.4028235E38" });

        parameters.add(new Object[] { null, new Double(1D), "1", "1E0" });
        parameters.add(new Object[] { null, new Double(2D), "2", "2E0" });
        parameters.add(new Object[] { null, Double.MIN_VALUE,
                                      "0.0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000049",
                                      "4.9E-324" });
        parameters.add(new Object[] { null, Double.MAX_VALUE,
                                      "179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                                      "1.7976931348623157E308" });


        parameters.add(new Object[] { 16, "1", "1", "1E+0" });
        parameters.add(new Object[] { 16, "-1", "-1", "-1E+0" });

        parameters.add(new Object[] { 16, "1.1", "1.1", "1.1E+0" });
        parameters.add(new Object[] { 16, "+1.1", "1.1", "1.1E+0" });
        parameters.add(new Object[] { 16, "-1.1", "-1.1", "-1.1E+0" });

        parameters.add(new Object[] { 16, "1.1E+0", "1.1", "1.1E+0" });
        parameters.add(new Object[] { 16, "-1.1E+0", "-1.1", "-1.1E+0" });

        parameters.add(new Object[] { 16, "1.1E+1", "11", "1.1E+1" });
        parameters.add(new Object[] { 16, "-1.1E+1", "-11", "-1.1E+1" });
        parameters.add(new Object[] { 16, "1.1E-1", "0.11", "1.1E-1" });
        parameters.add(new Object[] { 16, "-1.1E-1", "-0.11", "-1.1E-1" });

        parameters.add(new Object[] { 16, "1.1E+2", "110", "1.1E+2" });
        parameters.add(new Object[] { 16, "-1.1E+2", "-110", "-1.1E+2" });
        parameters.add(new Object[] { 16, "1.1E-2", "0.011", "1.1E-2" });
        parameters.add(new Object[] { 16, "-1.1E-2", "-0.011", "-1.1E-2" });

        parameters.add(new Object[] { 16, "1.1E+3", "1100", "1.1E+3" });
        parameters.add(new Object[] { 16, "-1.1E+3", "-1100", "-1.1E+3" });
        parameters.add(new Object[] { 16, "1.1E-3", "0.0011", "1.1E-3" });
        parameters.add(new Object[] { 16, "-1.1E-3", "-0.0011", "-1.1E-3" });

        parameters.add(new Object[] { 16, "1.010", "1.01", "1.01E+0" });

        parameters.add(new Object[] { 16, "21.12", "21.12", "2.112E+1" });
        parameters.add(new Object[] { 16, "+21.12", "21.12", "2.112E+1" });
        parameters.add(new Object[] { 16, "-21.12", "-21.12", "-2.112E+1" });

        parameters.add(new Object[] { 16, "321.123", "321.123", "3.21123E+2" });
        parameters.add(new Object[] { 16, "+321.123", "321.123", "3.21123E+2" });
        parameters.add(new Object[] { 16, "-321.123", "-321.123", "-3.21123E+2" });


        parameters.add(new Object[] { 8, "1", "1", "1E0" });
        parameters.add(new Object[] { 8, "-1", "-1", "-1E0" });

        parameters.add(new Object[] { 8, "1.1", "1.1", "1.1E0" });
        parameters.add(new Object[] { 8, "+1.1", "1.1", "1.1E0" });
        parameters.add(new Object[] { 8, "-1.1", "-1.1", "-1.1E0" });

        parameters.add(new Object[] { 8, "1.1E0", "1.1", "1.1E0" });
        parameters.add(new Object[] { 8, "-1.1E0", "-1.1", "-1.1E0" });

        parameters.add(new Object[] { 8, "1.1E1", "11", "1.1E1" });
        parameters.add(new Object[] { 8, "-1.1E1", "-11", "-1.1E1" });
        parameters.add(new Object[] { 8, "1.1E-1", "0.11", "1.1E-1" });
        parameters.add(new Object[] { 8, "-1.1E-1", "-0.11", "-1.1E-1" });

        parameters.add(new Object[] { 8, "1.1E2", "110", "1.1E2" });
        parameters.add(new Object[] { 8, "-1.1E2", "-110", "-1.1E2" });
        parameters.add(new Object[] { 8, "1.1E-2", "0.011", "1.1E-2" });
        parameters.add(new Object[] { 8, "-1.1E-2", "-0.011", "-1.1E-2" });

        parameters.add(new Object[] { 8, "1.1E3", "1100", "1.1E3" });
        parameters.add(new Object[] { 8, "-1.1E3", "-1100", "-1.1E3" });
        parameters.add(new Object[] { 8, "1.1E-3", "0.0011", "1.1E-3" });
        parameters.add(new Object[] { 8, "-1.1E-3", "-0.0011", "-1.1E-3" });

        parameters.add(new Object[] { 8, "1.010", "1.01", "1.01E0" });

        parameters.add(new Object[] { 8, "21.12", "21.12", "2.112E1" });
        parameters.add(new Object[] { 8, "+21.12", "21.12", "2.112E1" });
        parameters.add(new Object[] { 8, "-21.12", "-21.12", "-2.112E1" });

        parameters.add(new Object[] { 8, "321.123", "321.123", "3.21123E2" });
        parameters.add(new Object[] { 8, "+321.123", "321.123", "3.21123E2" });
        parameters.add(new Object[] { 8, "-321.123", "-321.123", "-3.21123E2" });


        parameters.add(new Object[] { 2, "1", "1", "1E0" });
        parameters.add(new Object[] { 2, "-1", "-1", "-1E0" });

        parameters.add(new Object[] { 2, "1.1", "1.1", "1.1E0" });
        parameters.add(new Object[] { 2, "+1.1", "1.1", "1.1E0" });
        parameters.add(new Object[] { 2, "-1.1", "-1.1", "-1.1E0" });

        parameters.add(new Object[] { 2, "1.1E0", "1.1", "1.1E0" });
        parameters.add(new Object[] { 2, "-1.1E0", "-1.1", "-1.1E0" });

        parameters.add(new Object[] { 2, "1.1E1", "11", "1.1E1" });
        parameters.add(new Object[] { 2, "-1.1E1", "-11", "-1.1E1" });
        parameters.add(new Object[] { 2, "1.1E-1", "0.11", "1.1E-1" });
        parameters.add(new Object[] { 2, "-1.1E-1", "-0.11", "-1.1E-1" });

        parameters.add(new Object[] { 2, "1.1E10", "11000000000", "1.1E10" });
        parameters.add(new Object[] { 2, "-1.1E10", "-11000000000", "-1.1E10" });
        parameters.add(new Object[] { 2, "1.1E-10", "0.00000000011", "1.1E-10" });
        parameters.add(new Object[] { 2, "-1.1E-10", "-0.00000000011", "-1.1E-10" });

        parameters.add(new Object[] { 2, "1.1E11", "110000000000", "1.1E11" });
        parameters.add(new Object[] { 2, "-1.1E11", "-110000000000", "-1.1E11" });
        parameters.add(new Object[] { 2, "1.1E-11", "0.000000000011", "1.1E-11" });
        parameters.add(new Object[] { 2, "-1.1E-11", "-0.000000000011", "-1.1E-11" });

        parameters.add(new Object[] { 2, "1.010", "1.01", "1.01E0" });

        parameters.add(new Object[] { 2, "11.11", "11.11", "1.111E1" });
        parameters.add(new Object[] { 2, "+11.11", "11.11", "1.111E1" });
        parameters.add(new Object[] { 2, "-11.11", "-11.11", "-1.111E1" });

        parameters.add(new Object[] { 2, "111.111", "111.111", "1.11111E2" });
        parameters.add(new Object[] { 2, "+111.111", "111.111", "1.11111E2" });
        parameters.add(new Object[] { 2, "-111.111", "-111.111", "-1.11111E2" });

        parameters.add(new Object[] { 16, "5.A827999FD2", "5.A827999FD2", "5.A827999FD2E+0" });
        parameters.add(new Object[] { 16, "A.646E17212", "A.646E17212", "A.646E17212E+0" });
        parameters.add(new Object[] { 16, "2.D413CCCFE8", "2.D413CCCFE8", "2.D413CCCFE8E+0" });

        return parameters;
    }

}

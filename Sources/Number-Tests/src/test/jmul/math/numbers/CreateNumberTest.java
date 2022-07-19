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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jmul.math.bool.BooleanHelper;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static test.jmul.math.numbers.NumberCreationHelper.createNumber;
import static test.jmul.math.numbers.StringHelper.removeSign;


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
    private final Integer base;

    /**
     * The implementation class for numbers.
     */
    private final Class type;

    /**
     * The input (i.e. a number string in supported notations)
     */
    private final Object input;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param type
     *        a number class
     * @param input
     *        an input
     */
    public CreateNumberTest(Integer base, Class type, Object input) {

        this.base = base;
        this.type = type;
        this.input = input;
    }

    /**
     * Trims a number string. The string is traversed from from right to left and a
     * copy of the specified string is returned with the superfluous zeroes removed.
     *
     * @param string
     *        a number string
     *
     * @return a trimmed number string
     */
    private static String trimRightSide(String string) {

        String TRIMMED_GROUP = "TRIMMED";
        String REGEX_1 = "^[0]*(?<TRIMMED>[1-9A-Za-z]{1}[0-9A-Za-z]*[.,]{1}[0-9A-Za-z]*[1-9A-Za-z]{1})[0]*$";
        String REGEX_2 = "^[0]*(?<TRIMMED>[1-9A-Za-z][0-9A-Za-z]*)[.,][0]*$";
        String[] REGEX_ARRAY = new String[] { REGEX_1, REGEX_2 };

        for (String regex : REGEX_ARRAY) {

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(string);

            if (matcher.matches()) {

                String trimmed = matcher.group(TRIMMED_GROUP);
                return trimmed;
            }
        }

        return string;
    }

    /**
     * Tests creating a number and compares the number with the input.
     *
     * @throws NoSuchMethodException
     *         is thrown if there exists no suitable constructor.
     * @throws InstantiationException
     *         is thrown if an error occurs within the constructor
     * @throws IllegalAccessException
     *         is thrown if the constructor cannot be accessed
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the constructo
     */
    @Test
    public void testNumberCreation() throws NoSuchMethodException, InstantiationException, IllegalAccessException,
                                            InvocationTargetException {

        Number number = createNumber(base, type, input);

        String normalizedInput = input.toString();
        String trimmedNormalizedInput = trimRightSide(normalizedInput);
        String unsignedTrimmedNormalizedInput = removeSign(trimmedNormalizedInput);
        String standardNotation = number.toStandardNotation();
        String scientificNotation = number.toScientificNotation();

        boolean equalsStandardNotation = unsignedTrimmedNormalizedInput.equals(standardNotation);
        boolean equalsScientificNotation = normalizedInput.equals(scientificNotation);

        String message;
        if (base == null) {

            message =
                String.format("The created number (%s/%s) doesn't match the input (%s)!", standardNotation,
                              scientificNotation, input);

        } else {

            message =
                String.format("The created number (%s/%s/base %s) doesn't match the input (%s)!", standardNotation,
                              scientificNotation, base, input);
        }

        assertTrue(message, BooleanHelper.xor(equalsStandardNotation, equalsScientificNotation));
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 10, NumberImpl.class, "1" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-1" });

        parameters.add(new Object[] { 10, NumberImpl.class, "1.1" });
        parameters.add(new Object[] { 10, NumberImpl.class, "+1.1" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-1.1" });

        parameters.add(new Object[] { 10, NumberImpl.class, "1.1E0" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-1.1E0" });

        parameters.add(new Object[] { 10, NumberImpl.class, "1.1E1" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-1.1E1" });
        parameters.add(new Object[] { 10, NumberImpl.class, "1.1E-1" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-1.1E-1" });

        parameters.add(new Object[] { 10, NumberImpl.class, "1.1E2" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-1.1E2" });
        parameters.add(new Object[] { 10, NumberImpl.class, "1.1E-2" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-1.1E-2" });

        parameters.add(new Object[] { 10, NumberImpl.class, "1.1E3" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-1.1E3" });
        parameters.add(new Object[] { 10, NumberImpl.class, "1.1E-3" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-1.1E-3" });

        parameters.add(new Object[] { 10, NumberImpl.class, "1.010" });

        parameters.add(new Object[] { 10, NumberImpl.class, "21.12" });
        parameters.add(new Object[] { 10, NumberImpl.class, "+21.12" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-21.12" });

        parameters.add(new Object[] { 10, NumberImpl.class, "321.123" });
        parameters.add(new Object[] { 10, NumberImpl.class, "+321.123" });
        parameters.add(new Object[] { 10, NumberImpl.class, "-321.123" });


        parameters.add(new Object[] { null, NumberImpl.class, new Byte((byte) 1) });
        parameters.add(new Object[] { null, NumberImpl.class, new Byte((byte) 2) });
        parameters.add(new Object[] { null, NumberImpl.class, Byte.MIN_VALUE });
        parameters.add(new Object[] { null, NumberImpl.class, Byte.MAX_VALUE });

        parameters.add(new Object[] { null, NumberImpl.class, new Short((short) 1) });
        parameters.add(new Object[] { null, NumberImpl.class, new Short((short) 2) });
        parameters.add(new Object[] { null, NumberImpl.class, Short.MIN_VALUE });
        parameters.add(new Object[] { null, NumberImpl.class, Short.MAX_VALUE });

        parameters.add(new Object[] { null, NumberImpl.class, new Integer((int) 1) });
        parameters.add(new Object[] { null, NumberImpl.class, new Integer((int) 2) });
        parameters.add(new Object[] { null, NumberImpl.class, Integer.MIN_VALUE });
        parameters.add(new Object[] { null, NumberImpl.class, Integer.MAX_VALUE });

        parameters.add(new Object[] { null, NumberImpl.class, new Long(1L) });
        parameters.add(new Object[] { null, NumberImpl.class, new Long(2L) });
        parameters.add(new Object[] { null, NumberImpl.class, Long.MIN_VALUE });
        parameters.add(new Object[] { null, NumberImpl.class, Long.MAX_VALUE });

        parameters.add(new Object[] { null, NumberImpl.class, new Float(1F) });
        parameters.add(new Object[] { null, NumberImpl.class, new Float(2F) });
        parameters.add(new Object[] { null, NumberImpl.class, Float.MIN_VALUE });
        parameters.add(new Object[] { null, NumberImpl.class, Float.MAX_VALUE });

        parameters.add(new Object[] { null, NumberImpl.class, new Double(1D) });
        parameters.add(new Object[] { null, NumberImpl.class, new Double(2D) });
        parameters.add(new Object[] { null, NumberImpl.class, Double.MIN_VALUE });
        parameters.add(new Object[] { null, NumberImpl.class, Double.MAX_VALUE });


        parameters.add(new Object[] { 16, NumberImpl.class, "1" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-1" });

        parameters.add(new Object[] { 16, NumberImpl.class, "1.1" });
        parameters.add(new Object[] { 16, NumberImpl.class, "+1.1" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-1.1" });

        parameters.add(new Object[] { 16, NumberImpl.class, "1.1E0" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-1.1E0" });

        parameters.add(new Object[] { 16, NumberImpl.class, "1.1E1" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-1.1E1" });
        parameters.add(new Object[] { 16, NumberImpl.class, "1.1E-1" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-1.1E-1" });

        parameters.add(new Object[] { 16, NumberImpl.class, "1.1E2" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-1.1E2" });
        parameters.add(new Object[] { 16, NumberImpl.class, "1.1E-2" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-1.1E-2" });

        parameters.add(new Object[] { 16, NumberImpl.class, "1.1E3" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-1.1E3" });
        parameters.add(new Object[] { 16, NumberImpl.class, "1.1E-3" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-1.1E-3" });

        parameters.add(new Object[] { 16, NumberImpl.class, "1.010" });

        parameters.add(new Object[] { 16, NumberImpl.class, "21.12" });
        parameters.add(new Object[] { 16, NumberImpl.class, "+21.12" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-21.12" });

        parameters.add(new Object[] { 16, NumberImpl.class, "321.123" });
        parameters.add(new Object[] { 16, NumberImpl.class, "+321.123" });
        parameters.add(new Object[] { 16, NumberImpl.class, "-321.123" });


        parameters.add(new Object[] { 8, NumberImpl.class, "1" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-1" });

        parameters.add(new Object[] { 8, NumberImpl.class, "1.1" });
        parameters.add(new Object[] { 8, NumberImpl.class, "+1.1" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-1.1" });

        parameters.add(new Object[] { 8, NumberImpl.class, "1.1E0" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-1.1E0" });

        parameters.add(new Object[] { 8, NumberImpl.class, "1.1E1" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-1.1E1" });
        parameters.add(new Object[] { 8, NumberImpl.class, "1.1E-1" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-1.1E-1" });

        parameters.add(new Object[] { 8, NumberImpl.class, "1.1E2" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-1.1E2" });
        parameters.add(new Object[] { 8, NumberImpl.class, "1.1E-2" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-1.1E-2" });

        parameters.add(new Object[] { 8, NumberImpl.class, "1.1E3" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-1.1E3" });
        parameters.add(new Object[] { 8, NumberImpl.class, "1.1E-3" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-1.1E-3" });

        parameters.add(new Object[] { 8, NumberImpl.class, "1.010" });

        parameters.add(new Object[] { 8, NumberImpl.class, "21.12" });
        parameters.add(new Object[] { 8, NumberImpl.class, "+21.12" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-21.12" });

        parameters.add(new Object[] { 8, NumberImpl.class, "321.123" });
        parameters.add(new Object[] { 8, NumberImpl.class, "+321.123" });
        parameters.add(new Object[] { 8, NumberImpl.class, "-321.123" });


        parameters.add(new Object[] { 2, NumberImpl.class, "1" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-1" });

        parameters.add(new Object[] { 2, NumberImpl.class, "1.1" });
        parameters.add(new Object[] { 2, NumberImpl.class, "+1.1" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-1.1" });

        parameters.add(new Object[] { 2, NumberImpl.class, "1.1E0" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-1.1E0" });

        parameters.add(new Object[] { 2, NumberImpl.class, "1.1E1" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-1.1E1" });
        parameters.add(new Object[] { 2, NumberImpl.class, "1.1E-1" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-1.1E-1" });

        parameters.add(new Object[] { 2, NumberImpl.class, "1.1E2" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-1.1E2" });
        parameters.add(new Object[] { 2, NumberImpl.class, "1.1E-2" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-1.1E-2" });

        parameters.add(new Object[] { 2, NumberImpl.class, "1.1E3" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-1.1E3" });
        parameters.add(new Object[] { 2, NumberImpl.class, "1.1E-3" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-1.1E-3" });

        parameters.add(new Object[] { 2, NumberImpl.class, "1.010" });

        parameters.add(new Object[] { 2, NumberImpl.class, "11.11" });
        parameters.add(new Object[] { 2, NumberImpl.class, "+11.11" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-11.11" });

        parameters.add(new Object[] { 2, NumberImpl.class, "111.111" });
        parameters.add(new Object[] { 2, NumberImpl.class, "+111.111" });
        parameters.add(new Object[] { 2, NumberImpl.class, "-111.111" });

        return parameters;
    }

}

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

import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.NumberParsingException;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests creating numbers with invalid parameters in order to check the error handling.
 *
 * TODO look for more edge cases
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class CreateNumberWithIllegalDigitsTest {

    /**
     * The expected exception type.
     */
    private final Class expectedExceptionType;

    /**
     * The number base.
     */
    private final int base;

    /**
     * A number string.
     */
    private final String input;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param expectedExceptionType
     *        the expected exception type
     * @param base
     *        the number base
     * @param input
     *        a number string
     */
    public CreateNumberWithIllegalDigitsTest(Class expectedExceptionType, int base, String input) {

        super();

        this.expectedExceptionType = expectedExceptionType;
        this.base = base;
        this.input = input;
    }

    /**
     * Tests vreating a new number.
     */
    @Test
    public void testCreation() {

        try {

            createNumber(base, input);

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();

            if (expectedExceptionType.isAssignableFrom(actualExceptionType)) {

                return;

            } else {

                String message =
                    String.format("An exception is expected (%s) but a different exception was thrown (%s)!",
                                  expectedExceptionType.getCanonicalName(), actualExceptionType.getCanonicalName());
                fail(message);
            }
        }

        String message =
            String.format("An exception is expected (%s) but no exception was thrown (base=%s; input=%s)!",
                          expectedExceptionType.getCanonicalName(), base, input);
        fail(message);
    }

    /**
     * Builds a number string.
     *
     * @param base
     *        a number base
     *
     * @return a number string depending on the specified base
     */
    private static String buildNumberString(int base) {

        if (base == 1) {

            return "";
        }

        Digit first;
        {
            int ordinal = base - 1;
            first = PositionalNumeralSystems.ordinalToDigit(base, ordinal);
        }

        return "" + buildNumberString(base - 1) + first;
    }

    /**
     * Returns a matrix of test data.
     *
     * @return a matrix of test data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        // Test boundaries
        parameters.add(new Object[] { IllegalArgumentException.class, 1, "1" });
        parameters.add(new Object[] { IllegalArgumentException.class, BASE_MAX_LIMIT + 1, "1" });

        // Test text input
        parameters.add(new Object[] { NumberParsingException.class, BASE_MAX_LIMIT, "Hello World!" });

        // Test mixed input (i.e. number and text)
        parameters.add(new Object[] { NumberParsingException.class, 10, "Hello 10" });
        parameters.add(new Object[] { NumberParsingException.class, 10, "10 World" });

        String numberString = buildNumberString(BASE_MAX_LIMIT);
        numberString = numberString + "!";
        parameters.add(new Object[] { NumberParsingException.class, BASE_MAX_LIMIT, numberString });

        // Test input with digits from a higher base
        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT - 1; base++) {

            parameters.add(new Object[] { NumberParsingException.class, base, buildNumberString(base + 1) });
        }

        return parameters;
    }

}

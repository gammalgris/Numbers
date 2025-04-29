/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

package test.jmul.math.fractions;


import java.util.ArrayList;
import java.util.Collection;

import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Constants;
import jmul.math.numbers.exceptions.NumberParsingException;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.FailedTestException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the creation of fractions.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class CreateFractionWithInvalidParameters1Test {

    /**
     * The number base.
     */
    private final int base;

    /**
     * A string representign the integer part.
     */
    private final String integerPartString;

    /**
     * The expected exception type.
     */
    private final Class expectedExceptionType;

    /**
     * Creates a new test case according to the specified numbers.
     *
     * @param base
     *        a number base
     * @param integerPartString
     *        a string representing the integer part
     * @param expectedExceptionType
     *        the expected exception type
     */
    public CreateFractionWithInvalidParameters1Test(int base, String integerPartString, Class expectedExceptionType) {

        super();

        this.base = base;
        this.integerPartString = integerPartString;
        this.expectedExceptionType = expectedExceptionType;
    }

    /**
     * Creates a fraction and checks wif an exception is thrown.
     */
    @Test
    public void testCreation() {

        try {

            createFraction(base, integerPartString);

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();
            if (expectedExceptionType.isAssignableFrom(actualExceptionType)) {

                return;
            }

            String message =
                String.format("A different exception was thrown (%s) but %s is expected!",
                              expectedExceptionType.getName(), actualExceptionType.getName());
            throw new FailedTestException(message);
        }

        String message = String.format("No expection was thrown but %s is expected!", expectedExceptionType.getName());
        throw new FailedTestException(message);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = Constants.BASE_MIN_LIMIT; base <= Constants.BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base, "--1", NumberParsingException.class });
            parameters.add(new Object[] { base, "1.1", IllegalArgumentException.class });
        }

        return parameters;
    }

}

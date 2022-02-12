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

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite contains test cases where instantiation will fail.
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class RealNumberInstantiationFailureWithStringTest {

    /**
     * The actual input.
     */
    private final String input;

    /**
     * The expected expection type.
     */
    private final Class expectedExceptionType;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param anInput
     * @param theExpectedExceptionType
     */
    public RealNumberInstantiationFailureWithStringTest(String anInput, Class theExpectedExceptionType) {

        super();

        input = anInput;
        expectedExceptionType = theExpectedExceptionType;
    }

    /**
     * Tests the instantiation and evaluates the resulting failure.
     */
    @Test
    public void testInstantiationAndStringRepresentation() {

        try {

            //RealNumber.parseString(input);
            new RealNumber(input);

            fail("An exception is epxected but none was thrown!");

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();

            if (!expectedExceptionType.isAssignableFrom(actualExceptionType)) {

                String message =
                    String.format("A different exception is expected (expected=%s; actual=%s)!",
                                  expectedExceptionType.getName(), actualExceptionType.getName());
                fail(message);
            }
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

        parameters.add(new Object[] { "a", IllegalArgumentException.class });
        parameters.add(new Object[] { "1.", IllegalArgumentException.class });

        return parameters;
    }

}

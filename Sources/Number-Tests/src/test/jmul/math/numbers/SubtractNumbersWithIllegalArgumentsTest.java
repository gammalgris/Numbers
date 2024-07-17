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

package test.jmul.math.numbers;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.Math;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests subtracting two numbers with illegal parmaeters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SubtractNumbersWithIllegalArgumentsTest {

    /**
     * The minuend.
     */
    private Number minuend;

    /**
     * The subtrahend.
     */
    private Number subtrahend;

    /**
     * The expected exception.
     */
    private final Class expectedException;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param minuend
     *        the minuend
     * @param subtrahend
     *        the subtrahend
     * @param expectedException
     *        the expected exception
     */
    public SubtractNumbersWithIllegalArgumentsTest(Number minuend, Number subtrahend, Class expectedException) {

        super();

        this.minuend = minuend;
        this.subtrahend = subtrahend;
        this.expectedException = expectedException;
    }

    /**
     * Subtracts two numbers and checks if an exception is thrown.
     */
    @Test
    public void testSubtractingNumber() {

        try {

            minuend.subtract(subtrahend);
            fail("An exception is expected but none was thrown!");

        } catch (Exception e) {

            Class actualException = e.getClass();
            if (!expectedException.isAssignableFrom(actualException)) {

                String message =
                    String.format("A %s was thrown but %s is expected!", actualException, expectedException);
                fail(message);
            }
        }
    }

    /**
     * Subtracts two numbers and checks if an exception is thrown.
     */
    @Test
    public void testSubtractingNumberVariant2() {

        try {

            Math.add(minuend, subtrahend);
            fail("An exception is expected but none was thrown!");

        } catch (Exception e) {

            Class actualException = e.getClass();
            if (!expectedException.isAssignableFrom(actualException)) {

                String message =
                    String.format("A %s was thrown but %s is expected!", actualException, expectedException);
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

        parameters.add(new Object[] { new NumberImpl("1"), null, IllegalArgumentException.class });
        parameters.add(new Object[] { new NumberImpl(2, "1"), new NumberImpl(10, "1"),
                                      IllegalArgumentException.class });

        return parameters;
    }

}

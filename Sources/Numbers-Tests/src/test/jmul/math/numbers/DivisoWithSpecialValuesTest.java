/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2025  Kristian Kutin
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
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.NoResultButLimitException;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the diviso function with special input values (i.e. division of integers with an integer result).
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DivisoWithSpecialValuesTest {

    /**
     * The dividend.
     */
    private final Number dividend;

    /**
     * The divisor,
     */
    private final Number divisor;

    /**
     * The expected exception.
     */
    private final Class expectedException;

    /**
     * The expected limit.
     */
    private final Number expectedLimit;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param dividend
     *        a number
     * @param divisor
     *        a number
     * @param expectedException
     *        the expected exception class
     */
    public DivisoWithSpecialValuesTest(Number dividend, Number divisor, Class expectedException, Number expectedLimit) {

        super();

        this.dividend = dividend;
        this.divisor = divisor;
        this.expectedException = expectedException;
        this.expectedLimit = expectedLimit;
    }

    /**
     * Returns a summary of this test case (i.e. the operation with its operands)
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s / %s => %s", dividend.base(), dividend, divisor, expectedException);
    }

    private static void failWrongException(Class expectedException, Class actualException) {

        String message =
            String.format("Wrong exception was thrown (expected exception: %s; actual exception: %s)!",
                          expectedException, actualException);
        fail(message);
    }

    private static void failNoException(Class expectedException) {

        String message = String.format("No exception was thrown (expected exception: %s)!", expectedException);
        fail(message);
    }

    private static void checkLimit(NoResultButLimitException e, Number expectedLimit) {

        Number actualLimit = e.limit();
        assertEquals("Checking limits!", expectedLimit, actualLimit);
    }

    /**
     * Calls the diviso function and checks the result.
     */
    @Test
    public void testDiviso() {

        Number actualResult;
        try {

            actualResult = dividend.diviso(divisor);
            failNoException(expectedException);

        } catch (Exception e) {

            Class actualException = e.getClass();
            if (expectedException.isAssignableFrom(actualException)) {

                if (e instanceof NoResultButLimitException) {

                    checkLimit((NoResultButLimitException) e, expectedLimit);
                }

            } else {

                failWrongException(expectedException, actualException);
            }
        }
    }

    /**
     * Calls the diviso function and checks the result.
     */
    @Test
    public void testDivisoVariant2() {

        Number actualResult;
        try {

            actualResult = Math.diviso(dividend, divisor);
            failNoException(expectedException);

        } catch (Exception e) {

            Class actualException = e.getClass();
            if (expectedException.isAssignableFrom(actualException)) {

                if (e instanceof NoResultButLimitException) {

                    checkLimit((NoResultButLimitException) e, expectedLimit);
                }

            } else {

                failWrongException(expectedException, actualException);
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

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createNumber(base, "1"), createNumber(base), NoResultButLimitException.class,
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "1"), createNumber(Signs.NEGATIVE, base),
                                          NoResultButLimitException.class, createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "-1"), createNumber(base), NoResultButLimitException.class,
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createNumber(base, "1"), createNumber(base, "0"),
                                          UndefinedOperationException.class, null });
        }

        return parameters;
    }

}

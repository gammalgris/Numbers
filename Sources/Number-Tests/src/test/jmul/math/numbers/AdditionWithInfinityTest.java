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

import static jmul.math.Constants.MAX_BASE;
import static jmul.math.Constants.MIN_BASE;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Signs;
import jmul.math.numbers.exceptions.UndefinedOperationException;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.SetUpException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests calculating with infinity.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class AdditionWithInfinityTest {

    /**
     * A constant value.
     */
    private static final Class NO_EXCEPTION;

    /**
     * A constant value.
     */
    private static final Number NO_RESULT;

    /*
     * The static initializer.
     */
    static {

        NO_EXCEPTION = null;
        NO_RESULT = null;
    }

    /**
     * The first operand.
     */
    private final Number firstOperand;

    /**
     * The second operand.
     */
    private final Number secondOperand;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * The expected exception.
     */
    private final Class expectedException;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param firstOperand
     *        an oeprand for the calculation
     * @param secondOperand
     *        an operand for the calculation
     * @param expectedResult
     *        the expected result of the calculation
     * @param expectedException
     *        the expected exception
     */
    public AdditionWithInfinityTest(Number firstOperand, Number secondOperand, Number expectedResult,
                                    Class expectedException) {

        super();

        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.expectedResult = expectedResult;
        this.expectedException = expectedException;
    }

    /**
     * Performs an addition and checks the result.
     */
    @Test
    public void testAddition() {

        if ((expectedException == null) && (expectedResult == null)) {

            throw new SetUpException();
        }

        if ((expectedException != null) && (expectedResult != null)) {

            throw new SetUpException();
        }

        Number actualResult;
        try {

            actualResult = firstOperand.add(secondOperand);

        } catch (Exception e) {

            if (expectedException != null) {

                Class actualException = e.getClass();
                String message =
                    String.format("A %s is expected but %s was thrown!", expectedException, actualException);
                assertTrue(message, expectedException.isAssignableFrom(actualException));

            } else {

                throw e;
            }

            return;
        }

        if (expectedResult != NO_RESULT) {

            String message = String.format("%s + %s", firstOperand.toString(), secondOperand.toString());
            assertEquals(message, expectedResult, actualResult);

        } else {

            String message = String.format("A %s is expected but none was thrown!", expectedException);
            fail(message);
        }

    }

    /**
     * Creates a number which represents infinity.
     *
     * @param base
     *        the number base
     *
     * @return a number
     */
    private static Number createInfinity(int base) {

        return new NumberImpl(base);
    }

    /**
     * Creates a number which represents negative infinity.
     *
     * @param base
     *        the number base
     *
     * @return a number
     */
    private static Number createNegativeInfinity(int base) {

        return new NumberImpl(base, Signs.NEGATIVE);
    }

    /**
     * Creates a number according to the specified number base and number string.
     *
     * @param base
     *        the number base
     * @param numberString
     *        a number string
     *
     * @return a number
     */
    private static Number createNumber(int base, String numberString) {

        return new NumberImpl(base, numberString);
    }

    /**
     * Returns a matrix of test data.
     *
     * @return a matrix of test data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = MIN_BASE; base < MAX_BASE; base++) {

            parameters.add(new Object[] { createNumber(base, "1"), createInfinity(base), createInfinity(base),
                                          NO_EXCEPTION });
            parameters.add(new Object[] { createNumber(base, "-1"), createInfinity(base), createInfinity(base),
                                          NO_EXCEPTION });
            parameters.add(new Object[] { createNumber(base, "1"), createNegativeInfinity(base),
                                          createNegativeInfinity(base), NO_EXCEPTION });
            parameters.add(new Object[] { createNumber(base, "-1"), createNegativeInfinity(base),
                                          createNegativeInfinity(base), NO_EXCEPTION });

            parameters.add(new Object[] { createInfinity(base), createInfinity(base), createInfinity(base),
                                          NO_EXCEPTION });
            parameters.add(new Object[] { createNegativeInfinity(base), createNegativeInfinity(base),
                                          createNegativeInfinity(base), NO_EXCEPTION });

            parameters.add(new Object[] { createInfinity(base), createNegativeInfinity(base), NO_RESULT,
                                          UndefinedOperationException.class });
            parameters.add(new Object[] { createNegativeInfinity(base), createInfinity(base), NO_RESULT,
                                          UndefinedOperationException.class });
        }

        return parameters;
    }

}

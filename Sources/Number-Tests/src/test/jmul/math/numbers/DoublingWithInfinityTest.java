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
public class DoublingWithInfinityTest {

    /**
     * A constant value.
     */
    private static final Number NO_RESULT;

    /*
     * The static initializer.
     */
    static {

        NO_RESULT = null;
    }

    /**
     * The operand.
     */
    private final Number operand;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param operand
     *        an operand for the calculation
     * @param expectedResult
     *        the expected result of the calculation
     */
    public DoublingWithInfinityTest(Number operand, Number expectedResult) {

        super();

        this.operand = operand;
        this.expectedResult = expectedResult;
    }

    /**
     * Performs an addition and checks the result.
     */
    @Test
    public void testAddition() {

        if (expectedResult == null) {

            throw new SetUpException();
        }

        Number actualResult = operand.doubling();

        String message = String.format("doubling %s", operand.toString());
        assertEquals(message, expectedResult, actualResult);
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

        for (int base = 2; base < 65; base++) {

            parameters.add(new Object[] { createInfinity(base), createInfinity(base) });
            parameters.add(new Object[] { createNegativeInfinity(base), createNegativeInfinity(base) });
        }

        return parameters;
    }

}

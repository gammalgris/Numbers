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

import jmul.math.operations.OperationSingletons;
import jmul.math.operations.repository.OperationIdentifiers;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests translating a number base to a number of a different number base.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class BaseToNumberTest {

    /**
     * A number base (as decimal number).
     */
    private final int base;

    /**
     * A different number base (as decimal number).
     */
    private final int destinationBase;

    /**
     * The specified number base as number of the specified destination number base.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param base
     *        a number base (as decimal number)
     * @param destinationBase
     *        a number base (as decimal number)
     * @param expectedResult
     *        the expected result
     */
    public BaseToNumberTest(int base, int destinationBase, Number expectedResult) {

        super();

        this.base = base;
        this.destinationBase = destinationBase;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a string representation of this test case.
     *
     * @return string representation
     */
    @Override
    public String toString() {

        return String.format("base: %d; expected base: %d -> %s ", base, destinationBase, expectedResult);
    }

    /**
     * Tests translating the number base.
     */
    @Test
    public void testTranslation() {

        BinaryOperation<Integer, Result<Number>> function =
            (BinaryOperation<Integer, Result<Number>>) OperationSingletons.getFunction(OperationIdentifiers.BASE_TO_NUMBER_FUNCTION);

        Result<Number> result = function.calculate(base, destinationBase);
        Number actualResult = result.result();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { 2, 2, createNumber(2, "10") });

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { 2, base, createNumber(base, "2") });
        }

        parameters.add(new Object[] { 3, 2, createNumber(2, "11") });
        parameters.add(new Object[] { 4, 2, createNumber(2, "100") });
        parameters.add(new Object[] { 5, 2, createNumber(2, "101") });
        parameters.add(new Object[] { 6, 2, createNumber(2, "110") });
        parameters.add(new Object[] { 7, 2, createNumber(2, "111") });
        parameters.add(new Object[] { 8, 2, createNumber(2, "1000") });
        parameters.add(new Object[] { 9, 2, createNumber(2, "1001") });
        parameters.add(new Object[] { 10, 2, createNumber(2, "1010") });
        parameters.add(new Object[] { 11, 2, createNumber(2, "1011") });
        parameters.add(new Object[] { 12, 2, createNumber(2, "1100") });
        parameters.add(new Object[] { 13, 2, createNumber(2, "1101") });
        parameters.add(new Object[] { 14, 2, createNumber(2, "1110") });
        parameters.add(new Object[] { 15, 2, createNumber(2, "1111") });
        parameters.add(new Object[] { 16, 2, createNumber(2, "10000") });
        parameters.add(new Object[] { 17, 2, createNumber(2, "10001") });
        parameters.add(new Object[] { 18, 2, createNumber(2, "10010") });
        parameters.add(new Object[] { 19, 2, createNumber(2, "10011") });
        parameters.add(new Object[] { 20, 2, createNumber(2, "10100") });
        parameters.add(new Object[] { 21, 2, createNumber(2, "10101") });
        parameters.add(new Object[] { 22, 2, createNumber(2, "10110") });
        parameters.add(new Object[] { 23, 2, createNumber(2, "10111") });
        parameters.add(new Object[] { 24, 2, createNumber(2, "11000") });
        parameters.add(new Object[] { 25, 2, createNumber(2, "11001") });
        parameters.add(new Object[] { 26, 2, createNumber(2, "11010") });
        parameters.add(new Object[] { 27, 2, createNumber(2, "11011") });
        parameters.add(new Object[] { 28, 2, createNumber(2, "11100") });
        parameters.add(new Object[] { 29, 2, createNumber(2, "11101") });
        parameters.add(new Object[] { 30, 2, createNumber(2, "11110") });
        parameters.add(new Object[] { 31, 2, createNumber(2, "11111") });
        parameters.add(new Object[] { 32, 2, createNumber(2, "100000") });
        parameters.add(new Object[] { 33, 2, createNumber(2, "100001") });
        parameters.add(new Object[] { 34, 2, createNumber(2, "100010") });
        parameters.add(new Object[] { 35, 2, createNumber(2, "100011") });
        parameters.add(new Object[] { 36, 2, createNumber(2, "100100") });
        parameters.add(new Object[] { 37, 2, createNumber(2, "100101") });
        parameters.add(new Object[] { 38, 2, createNumber(2, "100110") });
        parameters.add(new Object[] { 39, 2, createNumber(2, "100111") });
        parameters.add(new Object[] { 40, 2, createNumber(2, "101000") });
        parameters.add(new Object[] { 41, 2, createNumber(2, "101001") });
        parameters.add(new Object[] { 42, 2, createNumber(2, "101010") });
        parameters.add(new Object[] { 43, 2, createNumber(2, "101011") });
        parameters.add(new Object[] { 44, 2, createNumber(2, "101100") });
        parameters.add(new Object[] { 45, 2, createNumber(2, "101101") });
        parameters.add(new Object[] { 46, 2, createNumber(2, "101110") });
        parameters.add(new Object[] { 47, 2, createNumber(2, "101111") });
        parameters.add(new Object[] { 48, 2, createNumber(2, "110000") });
        parameters.add(new Object[] { 49, 2, createNumber(2, "110001") });
        parameters.add(new Object[] { 50, 2, createNumber(2, "110010") });
        parameters.add(new Object[] { 51, 2, createNumber(2, "110011") });
        parameters.add(new Object[] { 52, 2, createNumber(2, "110100") });
        parameters.add(new Object[] { 53, 2, createNumber(2, "110101") });
        parameters.add(new Object[] { 54, 2, createNumber(2, "110110") });
        parameters.add(new Object[] { 55, 2, createNumber(2, "110111") });
        parameters.add(new Object[] { 56, 2, createNumber(2, "111000") });
        parameters.add(new Object[] { 57, 2, createNumber(2, "111001") });
        parameters.add(new Object[] { 58, 2, createNumber(2, "111010") });
        parameters.add(new Object[] { 59, 2, createNumber(2, "111011") });
        parameters.add(new Object[] { 60, 2, createNumber(2, "111100") });
        parameters.add(new Object[] { 61, 2, createNumber(2, "111101") });
        parameters.add(new Object[] { 62, 2, createNumber(2, "111110") });
        parameters.add(new Object[] { 63, 2, createNumber(2, "111111") });
        parameters.add(new Object[] { 64, 2, createNumber(2, "1000000") });

        return parameters;
    }

}

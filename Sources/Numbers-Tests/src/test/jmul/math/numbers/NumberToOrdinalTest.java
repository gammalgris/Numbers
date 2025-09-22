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
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests translating a number to an ordinal number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NumberToOrdinalTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * A number base.
     */
    private final int destinationBase;

    /**
     * The expected ordinal number.
     */
    private final int expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param destinationBase
     *        a number base
     * @param expectedResult
     *        the expected ordinal number for the specified number base
     */
    public NumberToOrdinalTest(Number number, int destinationBase, int expectedResult) {

        super();

        this.number = number;
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

        return String.format("%s -> ordinal %d (base: %d)", number, expectedResult, destinationBase);
    }

    /**
     * Tests translating a number to an ordinal.
     */
    @Test
    public void testTranslation() {

        MixedBinaryOperation<Number, Integer, Result<Integer>> function =
            (MixedBinaryOperation<Number, Integer, Result<Integer>>) OperationSingletons.getFunction(OperationIdentifiers.NUMBER_TO_ORDINAL_FUNCTION);

        Result<Integer> result = function.calculate(number, destinationBase);
        int actualResult = result.result();

        assertEquals(toString(), expectedResult, actualResult);
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

            for (int ordinal = 0; ordinal < base; ordinal++) {

                parameters.add(new Object[] { createNumber(10, "" + ordinal), base, ordinal });
            }
        }

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            for (int ordinal = 0; ordinal < base; ordinal++) {

                parameters.add(new Object[] { createNumber(10, "-" + ordinal), base, ordinal });
            }
        }

        return parameters;
    }

}

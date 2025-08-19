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

import jmul.math.functions.FunctionSingletons;
import jmul.math.functions.repository.FunctionIdentifiers;
import jmul.math.numbers.Constants;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNegativeInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.MixedBinaryOperation;
import jmul.math.operations.Result;

import jmul.test.classification.UnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests translating a number to an ordinal number with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NumberToOrdinalWithInvalidArgumentsTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * A number base.
     */
    private final int destinationBase;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param destinationBase
     *        a number base
     */
    public NumberToOrdinalWithInvalidArgumentsTest(Number number, int destinationBase) {

        super();

        this.number = number;
        this.destinationBase = destinationBase;
    }

    /**
     * Tests translating the number base.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTranslation() {

        MixedBinaryOperation<Number, Integer, Result<Integer>> function =
            (MixedBinaryOperation<Number, Integer, Result<Integer>>) FunctionSingletons.getFunction(FunctionIdentifiers.NUMBER_TO_ORDINAL_FUNCTION);

        function.calculate(number, destinationBase);
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

            parameters.add(new Object[] { createInfinity(Constants.DEFAULT_NUMBER_BASE), base });
            parameters.add(new Object[] { createNegativeInfinity(Constants.DEFAULT_NUMBER_BASE), base });
        }

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            for (int ordinal = BASE_MAX_LIMIT; ordinal < BASE_MAX_LIMIT + 2; ordinal++) {

                parameters.add(new Object[] { createNumber(10, "" + ordinal), base });
            }
        }

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            for (int ordinal = BASE_MAX_LIMIT; ordinal < BASE_MAX_LIMIT + 2; ordinal++) {

                parameters.add(new Object[] { createNumber(10, "-" + ordinal), base });
            }
        }

        return parameters;
    }

}

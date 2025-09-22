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

package test.jmul.math.fractions;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.Math;
import jmul.math.fractions.Fraction;
import jmul.math.fractions.FractionHelper;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.operations.implementations.NumberToFraction;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests converting a number to a fraction.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NumberToFractionTest {

    /**
     * The first operand.
     */
    private final Number operand;

    /**
     * The expected result.
     */
    private final Fraction expectedResult;

    /**
     * The conversion function.
     */
    private final UnaryOperation<Number, Result<Fraction>> function;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param operand
     *        a number
     * @param expectedResult
     *        the expected fraction
     */
    public NumberToFractionTest(Number operand, Fraction expectedResult) {

        super();

        this.operand = operand;
        this.expectedResult = expectedResult;
        this.function = new NumberToFraction();
    }

    /**
     * Returns a string representation for this test case.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("[base:%d]: (%s) -> %s", operand.base(), operand, expectedResult);
    }

    /**
     * Tests converting a number to a fraction.
     */
    @Test
    public void testConversion() {

        Result<Fraction> wrappedResult = function.calculate(operand);
        Fraction actualResult = wrappedResult.result();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests converting a number to a fraction.
     */
    @Test
    public void testConversionVariant2() {

        Fraction actualResult = operand.toFraction();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests converting a number to a fraction.
     */
    @Test
    public void testConversionVariant3() {

        Fraction actualResult = Math.toFraction(operand);

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

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { NumberHelper.createInfinity(base),
                                          FractionHelper.createInfinity(base, Signs.POSITIVE) });
            parameters.add(new Object[] { NumberHelper.createNegativeInfinity(base),
                                          FractionHelper.createInfinity(base, Signs.NEGATIVE) });

            parameters.add(new Object[] { createNumber(base, "0"), createFraction(base, "0") });

            parameters.add(new Object[] { createNumber(base, "0.1"), createFraction(base, "1", "10") });
            parameters.add(new Object[] { createNumber(base, "-0.1"), createFraction(base, "-1", "10") });

            parameters.add(new Object[] { createNumber(base, "1"), createFraction(base, "1") });
            parameters.add(new Object[] { createNumber(base, "-1"), createFraction(base, "-1") });

            parameters.add(new Object[] { createNumber(base, "1.1"), createFraction(base, "11", "10") });
            parameters.add(new Object[] { createNumber(base, "-1.1"), createFraction(base, "-11", "10") });
        }

        return parameters;
    }

}

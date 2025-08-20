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
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests evaluating a fraction (i.e. dividing it's numerator by the denominator).
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class EvaluateFractionTest {

    /**
     * A fraction.
     */
    private Fraction fraction;

    /**
     * The expected result.
     */
    private Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param expectedResult
     *        the expected result
     */
    public EvaluateFractionTest(Fraction fraction, Number expectedResult) {

        super();

        this.fraction = fraction;
        this.expectedResult = expectedResult;
    }

    /**
     * Return the precision of teh expected result.
     *
     * @return a precision
     */
    public Number decimalPlaces() {

        return Math.getDefaultMaximumFractionLength(fraction.base());
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    public String toString() {

        return String.format("evaluate (base %d) %s with precision (base %d) %s -> (base %d) %s", fraction.base(),
                             fraction, decimalPlaces().base(), decimalPlaces(), expectedResult.base(), expectedResult);
    }

    /**
     * Tests evaluationg a fraction and checks the result.
     */
    @Test
    public void testEvaluation() {

        Number actualResult = fraction.evaluate();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests evaluationg a fraction and checks the result.
     */
    @Test
    public void testEvaluationVariant2() {

        Number actualResult = fraction.evaluate(decimalPlaces());

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests evaluationg a fraction and checks the result.
     */
    @Test
    public void testEvaluationVariant3() {

        Number actualResult = Math.evaluate(fraction);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests evaluationg a fraction and checks the result.
     */
    @Test
    public void testEvaluationVariant4() {

        Number actualResult = Math.evaluate(fraction, decimalPlaces());

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

            parameters.add(new Object[] { FractionHelper.createInfinity(base), NumberHelper.createInfinity(base) });
            parameters.add(new Object[] { FractionHelper.createInfinity(base, Signs.NEGATIVE),
                                          NumberHelper.createNegativeInfinity(base) });

            parameters.add(new Object[] { createFraction(base, "1"), createNumber(base, "1") });
            parameters.add(new Object[] { createFraction(base, "-1"), createNumber(base, "-1") });

            parameters.add(new Object[] { createFraction(base, "10", "1"), createNumber(base, "10") });
            parameters.add(new Object[] { createFraction(base, "-10", "1"), createNumber(base, "-10") });
        }

        parameters.add(new Object[] { createFraction(2, "10", "1", "10"), createNumber(2, "10.1") });
        parameters.add(new Object[] { createFraction(2, "-10", "1", "10"), createNumber(2, "-10.1") });

        parameters.add(new Object[] { createFraction(2, "10", "1", "11"), createNumber(2, "10.0101010101") });
        parameters.add(new Object[] { createFraction(2, "-10", "1", "11"), createNumber(2, "-10.0101010101") });

        parameters.add(new Object[] { createFraction(10, "10", "1", "10"), createNumber(10, "10.1") });
        parameters.add(new Object[] { createFraction(10, "-10", "1", "10"), createNumber(10, "-10.1") });

        parameters.add(new Object[] { createFraction(10, "10", "1", "11"), createNumber(10, "10.0909090909") });
        parameters.add(new Object[] { createFraction(10, "-10", "1", "11"), createNumber(10, "-10.0909090909") });

        parameters.add(new Object[] { createFraction(8, "10", "1", "10"), createNumber(8, "10.1") });
        parameters.add(new Object[] { createFraction(8, "-10", "1", "10"), createNumber(8, "-10.1") });

        parameters.add(new Object[] { createFraction(8, "10", "1", "11"), createNumber(8, "10.0707070707") });
        parameters.add(new Object[] { createFraction(8, "-10", "1", "11"), createNumber(8, "-10.0707070707") });

        parameters.add(new Object[] { createFraction(16, "10", "1", "10"), createNumber(16, "10.1") });
        parameters.add(new Object[] { createFraction(16, "-10", "1", "10"), createNumber(16, "-10.1") });

        parameters.add(new Object[] { createFraction(16, "10", "1", "11"), createNumber(16, "10.0F0F0F0F0F") });
        parameters.add(new Object[] { createFraction(16, "-10", "1", "11"), createNumber(16, "-10.0F0F0F0F0F") });

        return parameters;
    }

}

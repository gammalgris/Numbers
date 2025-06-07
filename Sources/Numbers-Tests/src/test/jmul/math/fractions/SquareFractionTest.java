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
import static jmul.math.fractions.FractionHelper.createFraction;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests calculating the square of a fraction.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SquareFractionTest {

    /**
     * A number.
     */
    private Fraction fraction;

    /**
     * The expected result.
     */
    private Fraction expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param expectedResult
     *        the expected result
     */
    public SquareFractionTest(Fraction fraction, Fraction expectedResult) {

        super();

        this.fraction = fraction;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary for this test case
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s -> [base:%d] %s", fraction.base(), fraction, expectedResult.base(),
                             expectedResult);
    }

    /**
     * Tests calculating the square of a number and checks the result.
     */
    @Test
    public void testCalculation() {

        Fraction actualResult = fraction.square();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests calculating the square of a number and checks the result.
     */
    @Test
    public void testCalculationVariant2() {

        Fraction actualResult = Math.square(fraction);

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

            parameters.add(new Object[] { createFraction(10, "0"), createFraction(10, "0") });
            parameters.add(new Object[] { createFraction(10, "-0"), createFraction(10, "0") });

            parameters.add(new Object[] { createFraction(10, "0", "0"), createFraction(10, "0", "0") }); // Check this, the equals and toString method may not be trusted
            parameters.add(new Object[] { createFraction(10, "-0", "0"), createFraction(10, "0", "0") }); // Check this, the equals and toString method may not be trusted
            parameters.add(new Object[] { createFraction(10, "0", "-0"), createFraction(10, "0", "0") }); // Check this, the equals and toString method may not be trusted

            parameters.add(new Object[] { createFraction(10, "1"), createFraction(10, "1", "1") });
            parameters.add(new Object[] { createFraction(10, "-1"), createFraction(10, "1", "1") });

            parameters.add(new Object[] { createFraction(10, "1", "1"), createFraction(10, "1", "1") });
            parameters.add(new Object[] { createFraction(10, "-1", "1"), createFraction(10, "1", "1") });
            parameters.add(new Object[] { createFraction(10, "1", "-1"), createFraction(10, "1", "1") });
        }

        parameters.add(new Object[] { createFraction(10, "2"), createFraction(10, "4", "1") });
        parameters.add(new Object[] { createFraction(10, "-2"), createFraction(10, "4", "1") });

        parameters.add(new Object[] { createFraction(10, "3"), createFraction(10, "9", "1") });
        parameters.add(new Object[] { createFraction(10, "-3"), createFraction(10, "9", "1") });

        parameters.add(new Object[] { createFraction(10, "4"), createFraction(10, "16", "1") });
        parameters.add(new Object[] { createFraction(10, "-4"), createFraction(10, "16", "1") });

        parameters.add(new Object[] { createFraction(10, "5"), createFraction(10, "25", "1") });
        parameters.add(new Object[] { createFraction(10, "-5"), createFraction(10, "25", "1") });

        parameters.add(new Object[] { createFraction(10, "6"), createFraction(10, "36", "1") });
        parameters.add(new Object[] { createFraction(10, "-6"), createFraction(10, "36", "1") });

        parameters.add(new Object[] { createFraction(10, "7"), createFraction(10, "49", "1") });
        parameters.add(new Object[] { createFraction(10, "-7"), createFraction(10, "49", "1") });

        parameters.add(new Object[] { createFraction(10, "8"), createFraction(10, "64", "1") });
        parameters.add(new Object[] { createFraction(10, "-8"), createFraction(10, "64", "1") });

        parameters.add(new Object[] { createFraction(10, "9"), createFraction(10, "81", "1") });
        parameters.add(new Object[] { createFraction(10, "-9"), createFraction(10, "81", "1") });

        parameters.add(new Object[] { createFraction(10, "10"), createFraction(10, "100", "1") });
        parameters.add(new Object[] { createFraction(10, "-10"), createFraction(10, "100", "1") });

        parameters.add(new Object[] { createFraction(10, "11"), createFraction(10, "121", "1") });
        parameters.add(new Object[] { createFraction(10, "-11"), createFraction(10, "121", "1") });

        parameters.add(new Object[] { createFraction(10, "11", "2", "3"), createFraction(10, "1225", "9") });
        parameters.add(new Object[] { createFraction(10, "-11", "2", "3"), createFraction(10, "1225", "9") });
        parameters.add(new Object[] { createFraction(10, "11", "-2", "3"), createFraction(10, "1225", "9") });
        parameters.add(new Object[] { createFraction(10, "11", "2", "-3"), createFraction(10, "1225", "9") });

        return parameters;
    }

}

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
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests doubling a fraction.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DoublingFractionTest {

    /**
     * A fraction.
     */
    private final Fraction fraction;

    /**
     * The expected result.
     */
    private final Fraction expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param expectedResult
     *        the expected result
     */
    public DoublingFractionTest(Fraction fraction, Fraction expectedResult) {

        super();

        this.fraction = fraction;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a description for this test case.
     *
     * @return a description
     */
    public String toString() {

        return String.format("double (%d) %s -> (%d) %s", fraction.base(), fraction, expectedResult.base(),
                             expectedResult);
    }

    /**
     * Tests doubling a fraction.
     */
    @Test
    public void testHalving() {

        Fraction actualResult = fraction.doubling();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests doubling a fraction.
     */
    @Test
    public void testHalvingVariant2() {

        Fraction actualResult = Math.doubling(fraction);

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

            parameters.add(new Object[] { createFraction(base), createFraction(base) });
            parameters.add(new Object[] { createFraction(Signs.NEGATIVE, base), createFraction(Signs.NEGATIVE, base) });

            parameters.add(new Object[] { createFraction(base, "0"), createFraction(base, "0") });
            parameters.add(new Object[] { createFraction(base, "-0"), createFraction(base, "0") });
        }

        parameters.add(new Object[] { createFraction(2, "1"), createFraction(2, "10", "1") });
        parameters.add(new Object[] { createFraction(2, "-1"), createFraction(2, "-10", "1") });

        parameters.add(new Object[] { createFraction(2, "1", "1"), createFraction(2, "10", "1") });
        parameters.add(new Object[] { createFraction(2, "-1", "1"), createFraction(2, "-10", "1") });

        parameters.add(new Object[] { createFraction(2, "1", "1", "1"), createFraction(2, "100", "1") });
        parameters.add(new Object[] { createFraction(2, "-1", "1", "1"), createFraction(2, "-100", "1") });

        parameters.add(new Object[] { createFraction(3, "1", "1", "1"), createFraction(3, "11", "1") });
        parameters.add(new Object[] { createFraction(3, "-1", "1", "1"), createFraction(3, "-11", "1") });

        for (int base = BASE_MIN_LIMIT + 2; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "2", "1") });
            parameters.add(new Object[] { createFraction(base, "-1"), createFraction(base, "-2", "1") });

            parameters.add(new Object[] { createFraction(base, "1", "1"), createFraction(base, "2", "1") });
            parameters.add(new Object[] { createFraction(base, "-1", "1"), createFraction(base, "-2", "1") });

        }

        parameters.add(new Object[] { createFraction(4, "1", "1", "1"), createFraction(4, "10", "1") });
        parameters.add(new Object[] { createFraction(4, "-1", "1", "1"), createFraction(4, "-10", "1") });

        for (int base = BASE_MIN_LIMIT + 3; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createFraction(base, "1", "1", "1"), createFraction(base, "4", "1") });
            parameters.add(new Object[] { createFraction(base, "-1", "1", "1"), createFraction(base, "-4", "1") });
        }

        return parameters;
    }

}

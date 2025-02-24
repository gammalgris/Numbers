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
 * This test suite tests normalizing a fraction.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class NormalizeFractionTest {

    /**
     * The first operand parsed from the specified number string.
     */
    private final Fraction operand;

    /**
     * The expected result parsed from the specified number string.
     */
    private final Fraction expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param operand
     *        a fraction
     * @param expectedResult
     *        a normalized fraction
     */
    public NormalizeFractionTest(Fraction operand, Fraction expectedResult) {

        super();

        this.operand = operand;
        this.expectedResult = expectedResult;
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
     * Tests normalizing a fraction.
     */
    @Test
    public void testNormalization() {

        Fraction actualResult = operand.normalizedFraction();
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

        parameters.add(new Object[] { createFraction(2, "0"), createFraction(2, "0", "1") });
        parameters.add(new Object[] { createFraction(2, "1"), createFraction(2, "1", "1") });
        parameters.add(new Object[] { createFraction(2, "-1"), createFraction(2, "-1", "1") });

        parameters.add(new Object[] { createFraction(2, "0", "1"), createFraction(2, "0") });
        parameters.add(new Object[] { createFraction(2, "1", "1"), createFraction(2, "1", "1") });
        parameters.add(new Object[] { createFraction(2, "-1", "1"), createFraction(2, "-1", "1") });

        parameters.add(new Object[] { createFraction(2, "1", "1", "1"), createFraction(2, "10", "1") });
        parameters.add(new Object[] { createFraction(2, "-1", "1", "1"), createFraction(2, "-10", "1") });

        parameters.add(new Object[] { createFraction(3, "2", "2", "2"), createFraction(3, "20", "2") });
        parameters.add(new Object[] { createFraction(3, "-2", "2", "2"), createFraction(3, "-20", "2") });

        parameters.add(new Object[] { createFraction(4, "2", "2", "2"), createFraction(4, "12", "2") });
        parameters.add(new Object[] { createFraction(4, "-2", "2", "2"), createFraction(4, "-12", "2") });

        parameters.add(new Object[] { createFraction(5, "2", "2", "2"), createFraction(5, "11", "2") });
        parameters.add(new Object[] { createFraction(5, "-2", "2", "2"), createFraction(5, "-11", "2") });

        parameters.add(new Object[] { createFraction(6, "2", "2", "2"), createFraction(6, "10", "2") });
        parameters.add(new Object[] { createFraction(6, "-2", "2", "2"), createFraction(6, "-10", "2") });

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createFraction(base, "0"), createFraction(base, "0", "1") });
            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "1", "1") });
            parameters.add(new Object[] { createFraction(base, "-1"), createFraction(base, "-1", "1") });

            parameters.add(new Object[] { createFraction(base, "0", "1"), createFraction(base, "0") });
            parameters.add(new Object[] { createFraction(base, "1", "1"), createFraction(base, "1", "1") });
            parameters.add(new Object[] { createFraction(base, "-1", "1"), createFraction(base, "-1", "1") });

            parameters.add(new Object[] { createFraction(base, "1", "1", "1"), createFraction(base, "2", "1") });
            parameters.add(new Object[] { createFraction(base, "-1", "1", "1"), createFraction(base, "-2", "1") });
        }

        for (int base = BASE_MIN_LIMIT + 5; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createFraction(base, "2", "2", "2"), createFraction(base, "6", "2") });
            parameters.add(new Object[] { createFraction(base, "-2", "2", "2"), createFraction(base, "-6", "2") });
        }

        return parameters;
    }

}

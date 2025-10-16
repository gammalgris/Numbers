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

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests reducing a fraction.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ReduceFractionTest {

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
    public ReduceFractionTest(Fraction fraction, Fraction expectedResult) {

        super();

        this.fraction = fraction;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s : %s", fraction.base(), fraction, expectedResult);
    }

    /**
     * Reduces a fraction and checks the result.
     */
    @Test
    public void testReduceFraction() {

        Fraction actualResult = fraction.reduce();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Reduces a fraction and checks the result.
     */
    @Test
    public void checkDivisorsVariant2() {

        Fraction actualResult = Math.reduce(fraction);

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

        parameters.add(new Object[] { createFraction(10, "14"), createFraction(10, "14", "1") });

        parameters.add(new Object[] { createFraction(10, "14", "12"), createFraction(10, "7", "6") });

        parameters.add(new Object[] { createFraction(10, "15", "51"), createFraction(10, "5", "17") });

        parameters.add(new Object[] { createFraction(10, "100", "200"), createFraction(10, "1", "2") });

        parameters.add(new Object[] { createFraction(10, "4", "9"), createFraction(10, "4", "9") });
        parameters.add(new Object[] { createFraction(10, "6", "11"), createFraction(10, "6", "11") });
        parameters.add(new Object[] { createFraction(10, "10", "20"), createFraction(10, "1", "2") });
        parameters.add(new Object[] { createFraction(10, "15", "30"), createFraction(10, "1", "2") });

        parameters.add(new Object[] { createFraction(2, "100", "1001"), createFraction(2, "100", "1001") });
        parameters.add(new Object[] { createFraction(2, "110", "1011"), createFraction(2, "110", "1011") });
        parameters.add(new Object[] { createFraction(2, "1010", "10100"), createFraction(2, "1", "10") });
        parameters.add(new Object[] { createFraction(2, "1111", "11110"), createFraction(2, "1", "10") });

        parameters.add(new Object[] { createFraction(16, "4", "9"), createFraction(16, "4", "9") });
        parameters.add(new Object[] { createFraction(16, "6", "B"), createFraction(16, "6", "B") });
        parameters.add(new Object[] { createFraction(16, "A", "14"), createFraction(16, "1", "2") });
        parameters.add(new Object[] { createFraction(16, "F", "1E"), createFraction(16, "1", "2") });

        parameters.add(new Object[] { createFraction(10, "5", "10"), createFraction(10, "1", "2") });
        parameters.add(new Object[] { createFraction(10, "20", "40"), createFraction(10, "1", "2") });
        parameters.add(new Object[] { createFraction(10, "25", "50"), createFraction(10, "1", "2") });

        parameters.add(new Object[] { createFraction(2, "101", "1010"), createFraction(2, "1", "10") });
        parameters.add(new Object[] { createFraction(2, "10100", "101000"), createFraction(2, "1", "10") });
        parameters.add(new Object[] { createFraction(2, "11001", "110010"), createFraction(2, "1", "10") });

        parameters.add(new Object[] { createFraction(16, "5", "A"), createFraction(16, "1", "2") });
        parameters.add(new Object[] { createFraction(16, "14", "28"), createFraction(16, "1", "2") });
        parameters.add(new Object[] { createFraction(16, "19", "32"), createFraction(16, "1", "2") });

        return parameters;
    }

}

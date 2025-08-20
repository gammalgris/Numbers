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
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests rebasing a fraction.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RebaseFractionTest {

    /**
     * A fraction.
     */
    private final Fraction fraction;

    /**
     * A destination number base.
     */
    private final int destinationBase;

    /**
     * The expected result.
     */
    private final Fraction expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param destinationBase
     *        a destination number base
     * @param expectedResult
     *        the expected result
     */
    public RebaseFractionTest(Fraction fraction, int destinationBase, Fraction expectedResult) {

        super();

        this.fraction = fraction;
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

        return String.format("(%d) %s rebased to (%d)%s", fraction.base(), fraction, destinationBase, expectedResult);
    }

    /**
     * Tests translating the number base.
     */
    @Test
    public void testRebase() {

        Fraction actualResult = fraction.rebase(destinationBase);

        assertEquals(toString(), expectedResult.base(), actualResult.base());
        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests translating the number base.
     */
    @Test
    public void testRebaseVariant2() {

        Fraction actualResult = Math.rebase(fraction, destinationBase);

        assertEquals(toString(), expectedResult.base(), actualResult.base());
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

            for (int destinationBase = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

                parameters.add(new Object[] { FractionHelper.createInfinity(base), destinationBase,
                                              FractionHelper.createInfinity(destinationBase) });
                parameters.add(new Object[] { FractionHelper.createInfinity(base, Signs.NEGATIVE), destinationBase,
                                              FractionHelper.createInfinity(destinationBase, Signs.NEGATIVE) });

                parameters.add(new Object[] { createFraction(base, "0"), destinationBase,
                                              createFraction(destinationBase, "0") });
                parameters.add(new Object[] { createFraction(base, "-0"), destinationBase,
                                              createFraction(destinationBase, "0") });

                parameters.add(new Object[] { createFraction(base, "1"), destinationBase,
                                              createFraction(destinationBase, "1") });
                parameters.add(new Object[] { createFraction(base, "-1"), destinationBase,
                                              createFraction(destinationBase, "-1") });

                parameters.add(new Object[] { createFraction(base, "1", "1"), destinationBase,
                                              createFraction(destinationBase, "1", "1") });
                parameters.add(new Object[] { createFraction(base, "-1", "1"), destinationBase,
                                              createFraction(destinationBase, "-1", "1") });

                parameters.add(new Object[] { createFraction(base, "1", "1", "1"), destinationBase,
                                              createFraction(destinationBase, "1", "1", "1") });
                parameters.add(new Object[] { createFraction(base, "-1", "1", "1"), destinationBase,
                                              createFraction(destinationBase, "-1", "1", "1") });
            }
        }

        parameters.add(new Object[] { createFraction(10, "10"), 2, createFraction(2, "1010") });
        parameters.add(new Object[] { createFraction(10, "-10"), 2, createFraction(2, "-1010") });

        parameters.add(new Object[] { createFraction(2, "1010"), 10, createFraction(10, "10") });
        parameters.add(new Object[] { createFraction(2, "-1010"), 10, createFraction(10, "-10") });

        parameters.add(new Object[] { createFraction(10, "10", "11"), 2, createFraction(2, "1010", "1011") });
        parameters.add(new Object[] { createFraction(10, "-10", "11"), 2, createFraction(2, "-1010", "1011") });

        parameters.add(new Object[] { createFraction(2, "1010", "1011"), 10, createFraction(10, "10", "11") });
        parameters.add(new Object[] { createFraction(2, "-1010", "1011"), 10, createFraction(10, "-10", "11") });

        parameters.add(new Object[] { createFraction(2, "1", "1010", "1011"), 10,
                                      createFraction(10, "1", "10", "11") });
        parameters.add(new Object[] { createFraction(2, "-1", "1010", "1011"), 10,
                                      createFraction(10, "-1", "10", "11") });

        return parameters;
    }

}

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
import java.util.Arrays;
import java.util.Collection;
import java.util.SortedSet;

import java.util.TreeSet;

import jmul.math.Math;
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Number;

import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests determining common prime factors of a fraction.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DetermineCommonPrimeFactorsTest {

    /**
     * A fraction.
     */
    private final Fraction fraction;

    /**
     * The expected result.
     */
    private final SortedSet<Number> expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param primeFactors
     *        all expected prime factors
     */
    public DetermineCommonPrimeFactorsTest(Fraction fraction, Number... primeFactors) {

        super();

        this.fraction = fraction;
        this.expectedResult = new TreeSet<>(Arrays.asList(primeFactors));
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
     * Checks the prime factors and compares the results.
     */
    @Test
    public void checkPrimeFactors() {

        SortedSet<Number> actualResult = fraction.commonPrimeFactors();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Checks the prime factors and compares the results.
     */
    @Test
    public void checkPrimeFactors2() {

        SortedSet<Number> actualResult = Math.commonPrimeFactors(fraction);

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

        parameters.add(new Object[] { createFraction(10, "14"), new Number[] { } });

        parameters.add(new Object[] { createFraction(10, "14", "12"), new Number[] { createNumber(10, "2") } });

        parameters.add(new Object[] { createFraction(10, "15", "51"), new Number[] { createNumber(10, "3") } });

        parameters.add(new Object[] { createFraction(10, "100", "200"),
                                      new Number[] { createNumber(10, "2"), createNumber(10, "5") } });

        parameters.add(new Object[] { createFraction(10, "5", "10"), new Number[] { createNumber(10, "5") } });
        parameters.add(new Object[] { createFraction(10, "20", "40"),
                                      new Number[] { createNumber(10, "2"), createNumber(10, "5") } });
        parameters.add(new Object[] { createFraction(10, "25", "50"), new Number[] { createNumber(10, "5") } });

        return parameters;
    }

}

/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

import static jmul.math.Math.compare;
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.exceptions.DigitBaseMismatchException;

import jmul.test.classification.UnitTest;
import jmul.test.exceptions.FailedTestException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Test comparing two fractions but with invalid parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class FractionComparisonInvalidParametersTest {

    /**
     * A fraction.
     */
    private final Fraction fraction1;

    /**
     * A fraction.
     */
    private final Fraction fraction2;

    /**
     * The expected exception type.
     */
    private final Class expectedExceptionType;

    /**
     * Creates a test case accprding to the specified parameters.
     *
     * @param fraction1
     *        a fraction
     * @param fraction2
     *        a fraction
     * @param expectedExceptionType
     *
     */
    public FractionComparisonInvalidParametersTest(Fraction fraction1, Fraction fraction2,
                                                   Class expectedExceptionType) {

        super();

        this.fraction1 = fraction1;
        this.fraction2 = fraction2;
        this.expectedExceptionType = expectedExceptionType;
    }

    /**
     * Creates a fraction and checks wif an exception is thrown.
     */
    @Test
    public void testComparison() {

        try {

            fraction1.compareTo(fraction2);

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();
            if (expectedExceptionType.isAssignableFrom(actualExceptionType)) {

                return;
            }

            String message =
                String.format("A different exception was thrown (%s) but %s is expected!",
                              expectedExceptionType.getName(), actualExceptionType.getName());
            throw new FailedTestException(message);
        }

        String message = String.format("No expection was thrown but %s is expected!", expectedExceptionType.getName());
        throw new FailedTestException(message);
    }

    /**
     * Creates a fraction and checks wif an exception is thrown.
     */
    @Test
    public void testComparison2() {

        try {

            compare(fraction1, fraction2);

        } catch (Exception e) {

            Class actualExceptionType = e.getClass();
            if (expectedExceptionType.isAssignableFrom(actualExceptionType)) {

                return;
            }

            String message =
                String.format("A different exception was thrown (%s) but %s is expected!",
                              expectedExceptionType.getName(), actualExceptionType.getName());
            throw new FailedTestException(message);
        }

        String message = String.format("No expection was thrown but %s is expected!", expectedExceptionType.getName());
        throw new FailedTestException(message);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { createFraction(2, "1"), createFraction(3, "1"),
                                      DigitBaseMismatchException.class });
        parameters.add(new Object[] { createFraction(2, "1", "101", "11"), createFraction(10, "10", "10", "11"),
                                      DigitBaseMismatchException.class });

        return parameters;
    }

}

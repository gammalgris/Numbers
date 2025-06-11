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

package test.jmul.math.vectors;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.Math;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.vectors.Vector;
import static jmul.math.vectors.VectorHelper.createVector;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests multiplying a vector with a number.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MultiplyVectorWithNumberTest {

    /**
     * A vector.
     */
    private final Vector vector;

    /**
     * A number.
     */
    private final Number number;

    /**
     * The expected result.
     */
    private final Vector expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param vector
     *        a vector
     * @param number
     *        a number
     * @param expectedResult
     *        the expected result
     */
    public MultiplyVectorWithNumberTest(Vector vector, Number number, Vector expectedResult) {

        super();

        this.vector = vector;
        this.number = number;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s * [base:%d] %s -> [base:%d] %s", vector.base(), vector, number.base(),
                             number, expectedResult.base(), expectedResult);
    }

    /**
     * Tests multiplying the vector with a number.
     */
    @Test
    public void testMultiplication() {

        Vector actualResult = vector.multiply(number);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests multiplying the vector with a number.
     */
    @Test
    public void testMultiplicationVariant2() {

        Vector actualResult = Math.multiply(vector, number);

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

            parameters.add(new Object[] { createVector(base, "0"), createNumber(base, "0"), createVector(base, "0") });

            parameters.add(new Object[] { createVector(base, "0"), createNumber(base, "1"), createVector(base, "0") });
            parameters.add(new Object[] { createVector(base, "1"), createNumber(base, "0"), createVector(base, "0") });
            parameters.add(new Object[] { createVector(base, "0"), createNumber(base, "-1"), createVector(base, "0") });

            parameters.add(new Object[] { createVector(base, "0", "0", "0"), createNumber(base, "0"),
                                          createVector(base, "0", "0", "0") });

            parameters.add(new Object[] { createVector(base, "0", "0", "1"), createNumber(base, "1"),
                                          createVector(base, "0", "0", "1") });
            parameters.add(new Object[] { createVector(base, "0", "1", "0"), createNumber(base, "1"),
                                          createVector(base, "0", "1", "0") });
            parameters.add(new Object[] { createVector(base, "1", "0", "0"), createNumber(base, "1"),
                                          createVector(base, "1", "0", "0") });
        }

        parameters.add(new Object[] { createVector(10, "0"), createNumber(10, "-1000"), createVector(10, "0") });
        parameters.add(new Object[] { createVector(10, "0", "1", "2", "11"), createNumber(10, "-1000"),
                                      createVector(10, "0", "-1000", "-2000", "-11000") });
        parameters.add(new Object[] { createVector(10, "1.1", "1.2", "-2.1", "1.9"), createNumber(10, "-1000"),
                                      createVector(10, "-1100", "-1200", "2100", "-1900") });

        return parameters;
    }

}

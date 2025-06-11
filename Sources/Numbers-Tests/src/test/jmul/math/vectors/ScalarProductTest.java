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
 * This test suite tests calculating the scalar product of two vectors.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class ScalarProductTest {

    /**
     * A vector.
     */
    private final Vector vector1;

    /**
     * A vector.
     */
    private final Vector vector2;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param vector1
     *        a vector
     * @param vector2
     *        a vector
     * @param expectedResult
     *        the expected result
     */
    public ScalarProductTest(Vector vector1, Vector vector2, Number expectedResult) {

        super();

        this.vector1 = vector1;
        this.vector2 = vector2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s * [base:%d] %s -> [base:%d] %s", vector1.base(), vector1, vector2.base(),
                             vector2, expectedResult.base(), expectedResult);
    }

    /**
     * Tests calculating the scalar product of two vectors.
     */
    @Test
    public void testCalculatingTheScalarProduct() {

        Number actualResult = vector1.scalarProduct(vector2);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Tests calculating the scalar product of two vectors.
     */
    @Test
    public void testCalculatingTheScalarProductVariant2() {

        Number actualResult = Math.scalarProduct(vector1, vector2);

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

            parameters.add(new Object[] { createVector(base, "0"), createVector(base, "0"), createNumber(base, "0") });

            parameters.add(new Object[] { createVector(base, "0"), createVector(base, "1"), createNumber(base, "0") });
            parameters.add(new Object[] { createVector(base, "1"), createVector(base, "0"), createNumber(base, "0") });
            parameters.add(new Object[] { createVector(base, "0"), createVector(base, "-1"), createNumber(base, "0") });

            parameters.add(new Object[] { createVector(base, "0", "0", "0"), createVector(base, "0", "0", "0"),
                                          createNumber(base, "0") });

            parameters.add(new Object[] { createVector(base, "0", "0", "1"), createVector(base, "1", "0", "0"),
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createVector(base, "0", "1", "0"), createVector(base, "0", "0", "1"),
                                          createNumber(base, "0") });
            parameters.add(new Object[] { createVector(base, "1", "0", "0"), createVector(base, "0", "1", "0"),
                                          createNumber(base, "0") });
        }

        parameters.add(new Object[] { createVector(10, "0"), createVector(10, "-1000"), createNumber(10, "0") });
        parameters.add(new Object[] { createVector(10, "0", "1", "2", "11"), createVector(10, "-1000", "0", "2", "1"),
                                      createNumber(10, "15") });
        parameters.add(new Object[] { createVector(10, "1.1", "1.2", "-2.1", "1.9"),
                                      createVector(10, "-1000", "0", "2", "1"), createNumber(10, "-1102.3") });

        return parameters;
    }

}

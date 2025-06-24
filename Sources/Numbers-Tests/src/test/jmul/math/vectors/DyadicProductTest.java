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
import jmul.math.matrices.Matrix;
import static jmul.math.matrices.MatrixHelper.createMatrix;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.vectors.Vector;
import static jmul.math.vectors.VectorHelper.createVector;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests calculating the dydic prdocut of two vectors.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DyadicProductTest {

    /**
     * A vector (i.e. column vector).
     */
    private final Vector vector1;

    /**
     * A vector (i.e. row vector).
     */
    private final Vector vector2;

    /**
     * The expected result.
     */
    private final Matrix expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param vector1
     *        a vector (i.e. column vector)
     * @param vector2
     *        a vector (i.e. row vector)
     * @param expectedResult
     *        the expected result
     */
    public DyadicProductTest(Vector vector1, Vector vector2, Matrix expectedResult) {

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

        return String.format("[base:%d] %s + [base:%d] %s -> [base:%d] %s", vector1.base(), vector1, vector2.base(),
                             vector2, expectedResult.base(), expectedResult);
    }

    /**
     * Tests calculating the dyadic product.
     */
    @Test
    public void testDyadicProduct() {

        Matrix actualResult = vector1.dyadicProduct(vector2);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
        assertEquals(toString(), vector1.dimensions(), actualResult.rows());
        assertEquals(toString(), vector2.dimensions(), actualResult.columns());
    }

    /**
     * Tests calculating the dyadic product.
     */
    @Test
    public void testDyadicProductVariant2() {

        Matrix actualResult = Math.dyadicProduct(vector1, vector2);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
        assertEquals(toString(), vector1.dimensions(), actualResult.rows());
        assertEquals(toString(), vector2.dimensions(), actualResult.columns());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        /*
         * Vector 1
         *
         * |1|
         * |3|
         * |2|
         *
         * Vector 2
         *
         * |2 1 0 3|
         *
         * Result Matrix
         *
         * |2 1 0 3|
         * |6 3 0 9|
         * |4 2 0 6|
         *
         */
        parameters.add(new Object[] { createVector(10, "1", "3", "2"), createVector(10, "2", "1", "0", "3"),
                                      createMatrix(10, createNumber(10, "4"), createNumber(10, "3"), "2", "6", "4", "1",
                                                   "3", "2", "0", "0", "0", "3", "9", "6") });

        return parameters;
    }

}

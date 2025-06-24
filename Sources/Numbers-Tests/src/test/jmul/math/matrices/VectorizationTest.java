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

package test.jmul.math.matrices;


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
 * This test suite tests the vectorization of matrices.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class VectorizationTest {

    /**
     * A matrix.
     */
    private final Matrix matrix;

    /**
     * The expected result (i.e. a vector).
     */
    private final Vector expectedResult;

    /**
     * Creates a new test case according to the specified paramters.
     *
     * @param matrix
     *        a matrix
     * @param expectedResult
     *        the expected result (i.e. a vector)
     */
    public VectorizationTest(Matrix matrix, Vector expectedResult) {

        super();

        this.matrix = matrix;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s -> [base:%d] %s", matrix.base(), matrix, expectedResult.base(),
                             expectedResult);
    }

    /**
     * Performs a vectorization and cehcks the result.
     */
    @Test
    public void addMatrices() {

        Vector actualResult = matrix.toVector();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Performs a vectorization and cehcks the result.
     */
    @Test
    public void addMatricesVariant2() {

        Vector actualResult = Math.toVector(matrix);

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

        parameters.add(new Object[] { createMatrix(10, createNumber(10, "2"), createNumber(10, "2"),
                                                   new String[] { "1", "2", "3", "4" }),
                                      createVector(10, new String[] { "1", "2", "3", "4" }) });

        parameters.add(new Object[] { createMatrix(10, createNumber(10, "3"), createNumber(10, "2"),
                                                   new String[] { "1", "2", "3", "4", "5", "6" }),
                                      createVector(10, new String[] { "1", "2", "3", "4", "5", "6" }) });

        return parameters;
    }

}

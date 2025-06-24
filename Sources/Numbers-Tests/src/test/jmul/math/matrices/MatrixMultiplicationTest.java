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
import jmul.math.functions.implementations.MatrixMultiplicationFunction;
import jmul.math.matrices.Matrix;
import static jmul.math.matrices.MatrixHelper.createMatrix;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests multiplying matrices.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MatrixMultiplicationTest {

    /**
     * A matrix.
     */
    private final Matrix matrix1;

    /**
     * A matrix.
     */
    private final Matrix matrix2;

    /**
     * The expected result (i.e. a matrix).
     */
    private final Matrix expectedResult;

    /**
     * Creates a new test case accordig to the specified parameters.
     *
     * @param matrix1
     *        a matrix
     * @param matrix2
     *        a matrix
     * @param expectedResult
     *        the expected result
     */
    public MatrixMultiplicationTest(Matrix matrix1, Matrix matrix2, Matrix expectedResult) {

        super();

        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s * [base:%d] %s = [base:%d] %s", matrix1.base(), matrix1, matrix2.base(),
                             matrix2, expectedResult.base(), expectedResult);
    }

    /**
     * Adds two matrices and checks the result.
     */
    @Test
    public void addMatrices() {

        Matrix actualResult = matrix1.multiply(matrix2);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Adds two matrices and checks the result.
     */
    @Test
    public void addMatricesVariant2() {

        Matrix actualResult = Math.multiply(matrix1, matrix2);

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

        /*
         * Matrix 1
         * 
         * |1 3 5|
         * |2 4 6|
         * 
         * Matrix 2
         * 
         * |1 4|
         * |2 5|
         * |3 6|
         * 
         * Result Matrix
         * 
         * |22 49|
         * |28 64|
         */
        parameters.add(new Object[] { createMatrix(10, createNumber(10, "3"), createNumber(10, "2"),
                                                   new String[] { "1", "2", "3", "4", "5", "6" }),
                                      createMatrix(10, createNumber(10, "2"), createNumber(10, "3"),
                                                   new String[] { "1", "2", "3", "4", "5", "6" }),
                                      createMatrix(10, createNumber(10, "2"), createNumber(10, "2"),
                                                   new String[] { "22", "28", "49", "64" }) });

        /*
         * Matrix 1
         * 
         * |3 2 1|
         * |1 0 2|
         *
         * Matrix 2
         * 
         * |1 2|
         * |0 1|
         * |4 0|
         *
         * Result Matrix
         * 
         * |7 8|
         * |9 2|
         */

        parameters.add(new Object[] { createMatrix(10, createNumber(10, "3"), createNumber(10, "2"),
                                                   new String[] { "3", "1", "2", "0", "1", "2" }),
                                      createMatrix(10, createNumber(10, "2"), createNumber(10, "3"),
                                                   new String[] { "1", "0", "4", "2", "1", "0" }),
                                      createMatrix(10, createNumber(10, "2"), createNumber(10, "2"),
                                                   new String[] { "7", "9", "8", "2" }) });

        /*
         * Matrix 1
         * 
         * |3 2 1|
         * |1 0 2|
         * |0 1 0|
         *
         * Matrix 2
         * 
         * |1 2|
         * |0 1|
         * |4 0|
         *
         * Result Matrix
         * 
         * |7 8|
         * |9 2|
         * |0 1|
         */

        parameters.add(new Object[] { createMatrix(10, createNumber(10, "3"), createNumber(10, "3"),
                                                   new String[] { "3", "1", "0", "2", "0", "1", "1", "2", "0" }),
                                      createMatrix(10, createNumber(10, "2"), createNumber(10, "3"),
                                                   new String[] { "1", "0", "4", "2", "1", "0" }),
                                      createMatrix(10, createNumber(10, "2"), createNumber(10, "3"),
                                                   new String[] { "7", "9", "0", "8", "2", "1" }) });

        /*
         * Matrix 1
         * 
         * |3 2 1|
         * |1 0 2|
         *
         * Matrix 2
         * 
         * |1 2 0|
         * |0 1 0|
         * |4 0 1|
         *
         * Result Matrix
         * 
         * |7 8 1|
         * |9 2 2|
         */

        parameters.add(new Object[] { createMatrix(10, createNumber(10, "3"), createNumber(10, "2"),
                                                   new String[] { "3", "1", "2", "0", "1", "2" }),
                                      createMatrix(10, createNumber(10, "3"), createNumber(10, "3"),
                                                   new String[] { "1", "0", "4", "2", "1", "0", "0", "0", "1" }),
                                      createMatrix(10, createNumber(10, "3"), createNumber(10, "2"),
                                                   new String[] { "7", "9", "8", "2", "1", "2" }) });

        return parameters;
    }

}

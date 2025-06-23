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

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests calculating the ranspose of a matrix.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class TransposeMatrixTest {

    /**
     * A matrix.
     */
    private final Matrix matrix;

    /**
     * The expected result (i.e. the transpose of a matrix).
     */
    private final Matrix expectedResult;

    /**
     * Creates a new test case accordign to the specified parameters.
     *
     * @param matrix
     *        a matrix
     * @param expectedResult
     *        the expected result (i.e. the transpose of a matrix)
     */
    public TransposeMatrixTest(Matrix matrix, Matrix expectedResult) {

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
     * Calculates the transpose of a matrix and checks the result.
     */
    @Test
    public void calculateTranspose() {

        Matrix actualResult = matrix.transpose();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Calculates the transpose of a matrix and checks the result.
     */
    @Test
    public void calculateTransposeVariant2() {

        Matrix actualResult = Math.transpose(matrix);

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

        parameters.add(new Object[] { createMatrix(10, createNumber(10, "2"), createNumber(10, "3"),
                                                   new String[] { "1", "3", "5", "2", "4", "6" }),
                                      createMatrix(10, createNumber(10, "3"), createNumber(10, "2"),
                                                   new String[] { "1", "2", "3", "4", "5", "6" }) });
        parameters.add(new Object[] { createMatrix(10, createNumber(10, "3"), createNumber(10, "2"),
                                                   new String[] { "1", "2", "3", "4", "5", "6" }),
                                      createMatrix(10, createNumber(10, "2"), createNumber(10, "3"),
                                                   new String[] { "1", "3", "5", "2", "4", "6" }) });

        return parameters;
    }

}

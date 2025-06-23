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
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests adding matrices.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class AddMatricesTest {

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
     * Creates a new test case according to the specified parameters.
     *
     * @param matrix1
     *        a matrix
     * @param matrix2
     *        a matrix
     * @param expectedResult
     *        the expected result (i.e.e a matrix)
     */
    public AddMatricesTest(Matrix matrix1, Matrix matrix2, Matrix expectedResult) {

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

        return String.format("[base:%d] %s + [base:%d] %s = [base:%d] %s", matrix1.base(), matrix1, matrix2.base(),
                             matrix2, expectedResult.base(), expectedResult);
    }

    /**
     * Adds two matrices and checks the result.
     */
    @Test
    public void addMatrices() {

        Matrix actualResult = matrix1.add(matrix2);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Adds two matrices and checks the result.
     */
    @Test
    public void addMatricesVariant2() {

        Matrix actualResult = Math.add(matrix1, matrix2);

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

        parameters.add(new Object[] { createMatrix(2, createNumber(2, "10"), createNumber(2, "10"),
                                                   new String[] { "0", "0", "0", "0" }),
                                      createMatrix(2, createNumber(2, "10"), createNumber(2, "10"),
                                                   new String[] { "1", "1", "1", "1" }),
                                      createMatrix(2, createNumber(2, "10"), createNumber(2, "10"),
                                                   new String[] { "1", "1", "1", "1" }) });

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createMatrix(base, createNumber(base, "2"), createNumber(base, "2"),
                                                       new String[] { "0", "0", "0", "0" }),
                                          createMatrix(base, createNumber(base, "2"), createNumber(base, "2"),
                                                       new String[] { "1", "1", "1", "1" }),
                                          createMatrix(base, createNumber(base, "2"), createNumber(base, "2"),
                                                       new String[] { "1", "1", "1", "1" }) });
        }

        parameters.add(new Object[] { createMatrix(10, createNumber(10, "2"), createNumber(10, "2"),
                                                   new String[] { "1", "2", "3", "4" }),
                                      createMatrix(10, createNumber(10, "2"), createNumber(10, "2"),
                                                   new String[] { "1", "2", "3", "4" }),
                                      createMatrix(10, createNumber(10, "2"), createNumber(10, "2"),
                                                   new String[] { "2", "4", "6", "8" }) });

        return parameters;
    }

}

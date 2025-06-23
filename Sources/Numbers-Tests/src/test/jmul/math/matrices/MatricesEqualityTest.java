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
 * This test suite checks the equality of matrices.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MatricesEqualityTest {

    /**
     * A matrix.
     */
    private final Matrix matrix1;

    /**
     * A matrix.
     */
    private final Matrix matrix2;

    /**
     * The expected result.
     */
    private final boolean expectedResult;

    /**
     * Creates a test case according to the specified prameters.
     *
     * @param matrix1
     *        a matrix
     * @param matrix2
     *        a matrix
     * @param expectedResult
     *        the expected result
     */
    public MatricesEqualityTest(Matrix matrix1, Matrix matrix2, boolean expectedResult) {

        super();

        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s ?= [base:%d] %s -> %b", matrix1.base(), matrix1, matrix2.base(), matrix2,
                             expectedResult);
    }

    /**
     * Tests the equality.
     */
    @Test
    public void testEquality() {

        boolean actualResult = matrix1.equals(matrix2);

        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Tests the equality (i.e. reciprocal test).
     */
    @Test
    public void testEquality2() {

        boolean actualResult = matrix2.equals(matrix1);

        assertEquals(toString(), expectedResult, actualResult);
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

            parameters.add(new Object[] { createMatrix(base, createNumber(base, "1"), createNumber(base, "1"), "0"),
                                          createMatrix(base, createNumber(base, "1"), createNumber(base, "1"), "1"),
                                          false });
            parameters.add(new Object[] { createMatrix(base, createNumber(base, "1"), createNumber(base, "1"), "0"),
                                          createMatrix(base, createNumber(base, "1"), createNumber(base, "1"), "0"),
                                          true });
        }

        parameters.add(new Object[] { createMatrix(10, createNumber(10, "1"), createNumber(10, "1"), "0"),
                                      createMatrix(10, createNumber(10, "1"), createNumber(10, "1"), "-1000"), false });

        return parameters;
    }

}

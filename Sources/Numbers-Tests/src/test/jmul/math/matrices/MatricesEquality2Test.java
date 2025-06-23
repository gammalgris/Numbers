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


import jmul.math.matrices.Matrix;
import static jmul.math.matrices.MatrixHelper.createMatrix;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests some edge cases regarding equality of vectors.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class MatricesEquality2Test {

    /**
     * Tests if the reference to the same vector returns the expected result.
     */
    @Test
    public void testSameReference() {

        Matrix matrix1 = createMatrix(10, createNumber(10, "1"), createNumber(10, "1"), "0");
        Matrix matrix2 = matrix1;

        boolean actualResult = matrix1.equals(matrix2);
        boolean expectedResult = true;

        assertEquals("Vector equality", expectedResult, actualResult);
    }

    /**
     * Tests if a null reference returns the expected result.
     */
    @Test
    public void testNullReference() {

        Matrix matrix1 = createMatrix(10, createNumber(10, "1"), createNumber(10, "1"), "0");
        Matrix matrix2 = null;

        boolean actualResult = matrix1.equals(matrix2);
        boolean expectedResult = false;

        assertEquals("Vector equality", expectedResult, actualResult);
    }

    /**
     * Tests if the vectors of different number bases are equal.
     */
    @Test
    public void testDifferentNumberBases() {

        Matrix matrix1 = createMatrix(10, createNumber(10, "1"), createNumber(10, "1"), "0");
        Matrix matrix2 = createMatrix(11, createNumber(11, "1"), createNumber(11, "1"), "0");

        boolean actualResult = matrix1.equals(matrix2);
        boolean expectedResult = false;

        assertEquals("Vector equality", expectedResult, actualResult);
    }

}

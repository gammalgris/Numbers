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


import jmul.math.indices.IndexSingletons;
import jmul.math.matrices.Matrix;
import static jmul.math.numbers.NumberHelper.createNumber;
import static jmul.math.matrices.MatrixHelper.createMatrix;
import jmul.math.matrices.MatrixImpl;
import jmul.math.numbers.Number;
import static org.junit.Assert.assertEquals;
import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * This test suite checks if the components of a matrix are arranged correctly.
 * 
 * @author Kristian Kutin
 */
@UnitTest
public class MatrixToStringTest {

    /**
     * Tests if the matrix components are arranged correctly.
     */
    @Test
    public void testToString() {

        int base = 10;
        Matrix matrix = createMatrix(base, "3", "2", "1", "2", "3", "4", "5", "6");
        
        Number firstIndex = IndexSingletons.firstIndex(base);

        assertEquals("Matrix size (columns)", "3", matrix.columns().toString());
        assertEquals("Matrix size (rows)", "2", matrix.rows().toString());

        Number columnIndex = firstIndex;
        Number rowIndex = firstIndex;
        assertEquals("Check components c1/r1", "1", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = IndexSingletons.nextIndex(columnIndex);
        assertEquals("Check components c2/r1", "3", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = IndexSingletons.nextIndex(columnIndex);
        assertEquals("Check components c3/r1", "5", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = firstIndex;
        rowIndex = IndexSingletons.nextIndex(rowIndex);
        assertEquals("Check components c1/r2", "2", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = IndexSingletons.nextIndex(columnIndex);
        assertEquals("Check components c2/r2", "4", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = IndexSingletons.nextIndex(columnIndex);
        assertEquals("Check components c3/r2", "6", matrix.component(columnIndex, rowIndex).toString());

        assertEquals("Check toString", "{{1, 3, 5}{2, 4, 6}}", matrix.toString());
    }

    /**
     * Tests if the matrix components are arranged correctly.
     */
    @Test
    public void testToStringMatrix2() {

        int base = 10;
        Matrix matrix = createMatrix(base, "2", "3", "1", "2", "3", "4", "5", "6");
        
        Number firstIndex = IndexSingletons.firstIndex(base);

        assertEquals("Matrix size (columns)", "2", matrix.columns().toString());
        assertEquals("Matrix size (rows)", "3", matrix.rows().toString());

        Number columnIndex = firstIndex;
        Number rowIndex = firstIndex;
        assertEquals("Check components c1/r1", "1", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = IndexSingletons.nextIndex(columnIndex);
        assertEquals("Check components c2/r1", "4", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = firstIndex;
        rowIndex = IndexSingletons.nextIndex(rowIndex);
        assertEquals("Check components c1/r2", "2", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = IndexSingletons.nextIndex(columnIndex);
        assertEquals("Check components c2/r2", "5", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = firstIndex;
        rowIndex = IndexSingletons.nextIndex(rowIndex);
        assertEquals("Check components c1/r3", "3", matrix.component(columnIndex, rowIndex).toString());

        columnIndex = IndexSingletons.nextIndex(columnIndex);
        assertEquals("Check components c2/r3", "6", matrix.component(columnIndex, rowIndex).toString());

        assertEquals("Check toString", "{{1, 4}{2, 5}{3, 6}}", matrix.toString());
    }

}

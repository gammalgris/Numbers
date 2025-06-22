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
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.indices.IndexSingletons;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests creating a matrix.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateMatrixTest {

    /**
     * Creates a matrix with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMatrixWithInvalidIndexes() {

        createMatrix(10, createNumber(11, "2"), createNumber(11, "2"), new String[] { "1", "2", "3", "4" });
    }

    /**
     * Creates a matrix with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMatrixWithTooFewComponents() {

        createMatrix(10, createNumber(11, "2"), createNumber(11, "2"), new String[] { "1", "2", "3" });
    }

    /**
     * Creates a matrix with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMatrixWithTooManyComponents() {

        createMatrix(10, createNumber(11, "2"), createNumber(11, "2"), new String[] { "1", "2", "3", "4", "5" });
    }

    /**
     * Creates a matrix with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMatrixWithNullComponents() {

        createMatrix(10, createNumber(11, "2"), createNumber(11, "2"), (String[]) null);
    }

    /**
     * Creates a matrix with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMatrixWithNullColumnbs() {

        createMatrix(10, null, createNumber(10, "2"), new String[] { "1", "2", "3", "4" });
    }

    /**
     * Creates a matrix with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMatrixWithNullRows() {

        createMatrix(10, createNumber(10, "2"), null, new String[] { "1", "2", "3", "4" });
    }

    /**
     * Creates a matrix with an valid parameters.
     */
    @Test
    public void testCreateMatrix() {

        Matrix matrix =
            createMatrix(10, createNumber(10, "2"), createNumber(10, "2"), new String[] { "1", "2", "3", "4" });

        Number firstIndex = IndexSingletons.firstIndex();

        Number columnIndex = firstIndex;
        Number rowIndex = firstIndex;
        Number actualComponent;
        Number expectedComponent;

        actualComponent = matrix.component(columnIndex, rowIndex);
        expectedComponent = createNumber(10, "1");
        assertEquals("Failed component check!", expectedComponent, actualComponent);

        columnIndex = firstIndex;
        rowIndex = IndexSingletons.nextIndex(rowIndex);

        actualComponent = matrix.component(columnIndex, rowIndex);
        expectedComponent = createNumber(10, "2");
        assertEquals("Failed component check!", expectedComponent, actualComponent);

        columnIndex = IndexSingletons.nextIndex(columnIndex);
        rowIndex = firstIndex;

        actualComponent = matrix.component(columnIndex, rowIndex);
        expectedComponent = createNumber(10, "3");
        assertEquals("Failed component check!", expectedComponent, actualComponent);

        rowIndex = IndexSingletons.nextIndex(rowIndex);

        actualComponent = matrix.component(columnIndex, rowIndex);
        expectedComponent = createNumber(10, "4");
        assertEquals("Failed component check!", expectedComponent, actualComponent);
    }

}

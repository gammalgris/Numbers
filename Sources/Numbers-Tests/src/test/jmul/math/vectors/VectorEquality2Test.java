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


import jmul.math.vectors.Vector;
import static jmul.math.vectors.VectorHelper.createVector;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests some edge cases regarding equality of vectors.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class VectorEquality2Test {

    /**
     * Tests if the reference to the same vector returns the expected result.
     */
    @Test
    public void testSameReference() {

        Vector vector1 = createVector(10, "0");
        Vector vector2 = vector1;

        boolean actualResult = vector1.equals(vector2);
        boolean expectedResult = true;

        assertEquals("Vector equality", expectedResult, actualResult);
    }

    /**
     * Tests if a null reference returns the expected result.
     */
    @Test
    public void testNullReference() {

        Vector vector1 = createVector(10, "0");
        Vector vector2 = null;

        boolean actualResult = vector1.equals(vector2);
        boolean expectedResult = false;

        assertEquals("Vector equality", expectedResult, actualResult);
    }

    /**
     * Tests if the vectors of different number bases are equal.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDifferentNumberBases() {

        Vector vector1 = createVector(10, "0");
        Vector vector2 = createVector(11, "0");

        vector1.equals(vector2);
    }

}

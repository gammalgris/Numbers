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


import jmul.math.indices.IndexSingletons;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.vectors.Vector;
import jmul.math.vectors.VectorImpl;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests creating a vector.
 *
 * TODO not all constructors are yet tested.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateVectorTest {

    /**
     * Checks creating a zero dimensional vector.
     */
    @Test
    public void testCreateZeroDimensionalVector() {

        Vector vector = new VectorImpl();

        assertEquals(10, vector.base());
        assertTrue(vector.dimensions().isZero());
    }

    /**
     * Checks creating a zero dimensional vector with an invalid number base.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateZeroDimensionalVectorWithInvalidNumberBase() {

        new VectorImpl(1);
    }

    /**
     * Checks creating a one dimensional vector.
     */
    @Test
    public void testCreateOneDimensionalVector() {

        int base = 10;
        final Number ONE = createNumber(base, "1");
        final Number INDEX = IndexSingletons.firstIndex(base);

        Vector vector = new VectorImpl(base, createNumber(base, "1"));

        assertEquals(base, vector.base());
        assertTrue(vector.dimensions().isOne());
        assertEquals(ONE, vector.dimensions());
        assertEquals(ONE, vector.component(INDEX));
    }

    /**
     * Checks creating a one dimensional vector with an empty array.
     */
    @Test
    public void testCreateOneDimensionalVectorWithEmotyArray() {

        int base = 10;
        Vector vector = new VectorImpl(base, new Number[] { });

        assertEquals(base, vector.base());
        assertTrue(vector.dimensions().isZero());
    }

    /**
     * Checks creating a two dimensional vector.
     */
    @Test
    public void testCreateTwoDimensionalVector() {

        int base = 10;
        final Number ONE = createNumber(base, "1");
        final Number TWO = createNumber(base, "2");
        final Number FIRST_INDEX = IndexSingletons.firstIndex(base);
        final Number LAST_INDEX = IndexSingletons.nextIndex(FIRST_INDEX);

        Vector vector = new VectorImpl(base, createNumber(base, "1"), createNumber(base, "2"));

        assertEquals(base, vector.base());
        assertEquals(TWO, vector.dimensions());
        assertEquals(ONE, vector.component(FIRST_INDEX));
        assertEquals(TWO, vector.component(LAST_INDEX));
    }

}

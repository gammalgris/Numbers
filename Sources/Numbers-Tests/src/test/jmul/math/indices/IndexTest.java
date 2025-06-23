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

package test.jmul.math.indices;


import jmul.math.indices.IndexSingletons;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * A test suite for testing index numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class IndexTest {

    /**
     * Test an illegal index number.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIndexZero() {

        Number index = createNumber(Signs.POSITIVE, 10, 0);

        IndexSingletons.nextIndex(index);
    }

    /**
     * Test retrieving the first index (initial state).
     */
    @Test
    public void testFirstIndex() {

        int base = 10;
        Number ONE = createNumber(Signs.POSITIVE, base, 1);

        Number newIndex = IndexSingletons.firstIndex(base);

        assertEquals(ONE, newIndex);
        assertFalse(ONE == newIndex);

        Number newIndex2 = IndexSingletons.firstIndex(base);

        assertEquals(ONE, newIndex2);
        assertFalse(ONE == newIndex2);
        assertTrue(newIndex == newIndex2);
    }

    /**
     * Test retrieving the index next ot one.
     */
    @Test
    public void testOne() {

        Number ONE = createNumber(Signs.POSITIVE, 10, 1);
        Number TWO = ONE.inc();

        Number newIndex = IndexSingletons.nextIndex(ONE);

        assertEquals(TWO, newIndex);
        assertFalse(TWO == newIndex);

        Number newIndex2 = IndexSingletons.nextIndex(ONE);

        assertEquals(TWO, newIndex2);
        assertFalse(TWO == newIndex2);
        assertTrue(newIndex == newIndex2);
    }

    /**
     * Test retrieving the index next to two.
     */
    @Test
    public void testTwo() {

        Number TWO = createNumber(Signs.POSITIVE, 10, 2);
        Number THREE = TWO.inc();

        Number newIndex = IndexSingletons.nextIndex(TWO);

        assertEquals(THREE, newIndex);
        assertFalse(THREE == newIndex);

        Number newIndex2 = IndexSingletons.nextIndex(TWO);

        assertEquals(THREE, newIndex2);
        assertFalse(THREE == newIndex2);
        assertTrue(newIndex == newIndex2);
    }

    /**
     * Test retrieving the index next to ten.
     */
    @Test
    public void testTen() {

        Number TEN = createNumber(10, "10");
        Number ELEVEN = TEN.inc();

        Number newIndex = IndexSingletons.nextIndex(TEN);

        assertEquals(ELEVEN, newIndex);
        assertFalse(ELEVEN == newIndex);

        Number newIndex2 = IndexSingletons.nextIndex(TEN);

        assertEquals(ELEVEN, newIndex2);
        assertFalse(ELEVEN == newIndex2);
        assertTrue(newIndex == newIndex2);
    }

}

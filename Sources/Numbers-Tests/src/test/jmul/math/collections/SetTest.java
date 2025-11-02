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

package test.jmul.math.collections;


import jmul.math.collections.Set;
import jmul.math.collections.SetImpl;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests a set.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SetTest {

    /**
     * Creates a set with no elements.
     */
    @Test
    public void testCreateSetWithNoElements() {

        int base = 10;
        Number noElement1 = createNumber(base, "1");
        Number noElement2 = createNumber(base, "1");
        Number noElement3 = createNumber(base, "2");

        Set<Number> set = new SetImpl<>(base);

        assertEquals("#size", createNumber(base, "0"), set.elements());
        assertEquals("#string", "{}", set.toString());
        assertTrue("#empty", set.isEmpty());

        assertFalse("#element", set.isElement(noElement1));
        assertFalse("#element", set.isElement(noElement2));
        assertFalse("#element", set.isElement(noElement3));
    }

    /**
     * Creates a set with two elements.
     */
    @Test
    public void testCreateSetWithTwoElements() {

        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "2");
        Number noElement = createNumber(base, "3");

        Set<Number> set = new SetImpl<>(base, element1, element2);

        assertEquals("#size", createNumber(base, "2"), set.elements());
        assertEquals("#string", "{1, 2}", set.toString());
        assertFalse("#empty", set.isEmpty());

        assertTrue("#element", set.isElement(element1));
        assertTrue("#element", set.isElement(element2));
        assertFalse("#element", set.isElement(noElement));
    }

    /**
     * Tests creating a set with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createTestSequenceWithNullElementArray() {

        int base = 10;
        Number[] elements = null;

        new SetImpl<Number>(base, elements);
    }

    /**
     * Tests creating a set with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createTestSequenceWithNullElement() {

        int base = 10;
        Number elements = null;

        new SetImpl<Number>(base, elements);
    }

    /**
     * Compares two different sets.
     */
    @Test
    public void testCompareTwoDifferentSets() {

        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "2");
        Number element3 = createNumber(base, "3");

        Set<Number> set1 = new SetImpl<>(base, element1, element2);
        Set<Number> set2 = new SetImpl<>(base, element1, element3);

        assertFalse("equals", set1.equals(set2));
        assertFalse("equals", set2.equals(set1));
    }

    /**
     * Compares two equal sequences.
     */
    @Test
    public void testCompareTwoEqualSets() {

        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "2");

        Set<Number> set1 = new SetImpl<>(base, element1, element2);
        Set<Number> set2 = new SetImpl<>(base, element1, element2);

        assertTrue("equals", set1.equals(set2));
        assertTrue("equals", set2.equals(set1));
    }

    /**
     * Tests the itnersection of two sets.
     */
    @Test
    public void testIntersection() {

        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "2");
        Number element3 = createNumber(base, "3");

        Set<Number> set1 = new SetImpl<>(base, element1, element2);
        Set<Number> set2 = new SetImpl<>(base, element2, element3);

        Set<Number> expectedResult = new SetImpl<>(base, element2);
        Set<Number> actualResult = set1.intersection(set2);

        assertEquals("result", expectedResult, actualResult);
    }

    /**
     * Tests the union of two sets.
     */
    @Test
    public void testUnion() {
        
        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "2");
        Number element3 = createNumber(base, "3");

        Set<Number> set1 = new SetImpl<>(base, element1, element2);
        Set<Number> set2 = new SetImpl<>(base, element2, element3);

        Set<Number> expectedResult = new SetImpl<>(base, element1, element2, element3);
        Set<Number> actualResult = set1.union(set2);

        assertEquals("result", expectedResult, actualResult);
    }

    /**
     * Tests the difference of two sets.
     */
    @Test
    public void testDifference() {
        
        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "2");
        Number element3 = createNumber(base, "3");

        Set<Number> set1 = new SetImpl<>(base, element1, element2);
        Set<Number> set2 = new SetImpl<>(base, element2, element3);

        Set<Number> expectedResult = new SetImpl<>(base, element1);
        Set<Number> actualResult = set1.difference(set2);

        assertEquals("result", expectedResult, actualResult);
    }

    /**
     * Tests the symmetric difference of two sets.
     */
    @Test
    public void testSymmetricDifference() {
        
        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "2");
        Number element3 = createNumber(base, "3");

        Set<Number> set1 = new SetImpl<>(base, element1, element2);
        Set<Number> set2 = new SetImpl<>(base, element2, element3);

        Set<Number> expectedResult = new SetImpl<>(base, element1, element3);
        Set<Number> actualResult = set1.symmetricDifference(set2);

        assertEquals("result", expectedResult, actualResult);
    }

}

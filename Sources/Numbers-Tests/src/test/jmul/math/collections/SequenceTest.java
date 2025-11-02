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


import jmul.math.collections.Sequence;
import jmul.math.collections.SequenceImpl;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests a sequence.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SequenceTest {

    /**
     * Creates a sequence with no elements.
     */
    @Test
    public void testCreateSequenceWithNoElements() {

        int base = 10;
        Number noElement1 = createNumber(base, "1");
        Number noElement2 = createNumber(base, "1");
        Number noElement3 = createNumber(base, "2");

        Sequence<Number> sequence = new SequenceImpl<>(base);

        assertEquals("#size", createNumber(base, "0"), sequence.elements());
        assertEquals("#string", "{}", sequence.toString());
        assertTrue("#empty", sequence.isEmpty());

        assertFalse("#element", sequence.isElement(noElement1));
        assertFalse("#element", sequence.isElement(noElement2));
        assertFalse("#element", sequence.isElement(noElement3));
    }

    /**
     * Creates a sequence with two elements.
     */
    @Test
    public void testCreateSequenceWithTwoElements() {

        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "1");
        Number noElement = createNumber(base, "2");

        Sequence<Number> sequence = new SequenceImpl<>(base, element1, element2);

        assertEquals("#size", createNumber(base, "2"), sequence.elements());
        assertEquals("#string", "{1, 1}", sequence.toString());
        assertFalse("#empty", sequence.isEmpty());

        assertTrue("#element", sequence.isElement(element1));
        assertTrue("#element", sequence.isElement(element2));
        assertFalse("#element", sequence.isElement(noElement));
    }

    /**
     * Tests creating a sequence with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createTestSequenceWithNullElementArray() {

        int base = 10;
        Number[] elements = null;

        new SequenceImpl<Number>(base, elements);
    }

    /**
     * Tests creating a sequence with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createTestSequenceWithNullElement() {

        int base = 10;
        Number elements = null;

        new SequenceImpl<Number>(base, elements);
    }

    /**
     * Compares two different sequences.
     */
    @Test
    public void testCompareTwoDifferentSequences() {

        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "1");
        Number element3 = createNumber(base, "2");

        Sequence<Number> sequence1 = new SequenceImpl<>(base, element1, element2);
        Sequence<Number> sequence2 = new SequenceImpl<>(base, element1, element3);

        assertFalse("equals", sequence1.equals(sequence2));
        assertFalse("equals", sequence2.equals(sequence1));
    }

    /**
     * Compares two equal sequences.
     */
    @Test
    public void testCompareTwoEqualSequences() {

        int base = 10;
        Number element1 = createNumber(base, "1");
        Number element2 = createNumber(base, "2");

        Sequence<Number> sequence1 = new SequenceImpl<>(base, element1, element2);
        Sequence<Number> sequence2 = new SequenceImpl<>(base, element1, element2);

        assertTrue("equals", sequence1.equals(sequence2));
        assertTrue("equals", sequence2.equals(sequence1));
    }

}

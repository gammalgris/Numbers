/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

package test.jmul.math.numbers;


import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Signs;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite checks the properties of various numbers.
 *
 * @author Kristian Kutin
 */
public class NumberPropertyTest {

    /**
     * Tests the properties of an unsigned zero.
     */
    @Test
    public void testUnsignedZero() {

        Number number = new NumberImpl("0");

        assertEquals(false, number.isFraction());
        assertEquals(false, number.isInfinity());
        assertEquals(true, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(true, number.isNaturalNumberIncludingZero());
        assertEquals(false, number.isNegative());
        assertEquals(true, number.isPositive());
        assertEquals(true, number.isZero());
    }

    /**
     * Tests the properties of a signed (positive) zero.
     */
    @Test
    public void testPositiveZero() {

        Number number = new NumberImpl("+0");

        assertEquals(false, number.isFraction());
        assertEquals(false, number.isInfinity());
        assertEquals(true, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(true, number.isNaturalNumberIncludingZero());
        assertEquals(false, number.isNegative());
        assertEquals(true, number.isPositive());
        assertEquals(true, number.isZero());
    }

    /**
     * Tests the properties of an signed (negative) zero.
     */
    @Test
    public void testNegativeZero() {

        Number number = new NumberImpl("-0");

        assertEquals(false, number.isFraction());
        assertEquals(false, number.isInfinity());
        assertEquals(true, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(false, number.isNaturalNumberIncludingZero());
        assertEquals(true, number.isNegative());
        assertEquals(false, number.isPositive());
        assertEquals(true, number.isZero());
    }

    /**
     * Tests the properties of unsigned infinity.
     */
    @Test
    public void testUnsignednfinity() {

        Number number = new NumberImpl();

        assertEquals(false, number.isFraction());
        assertEquals(true, number.isInfinity());
        assertEquals(false, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(false, number.isNaturalNumberIncludingZero());
        assertEquals(false, number.isNegative());
        assertEquals(true, number.isPositive());
        assertEquals(false, number.isZero());
    }

    /**
     * Tests the properties of signed (positive) infinity.
     */
    @Test
    public void testPositiveInfinity() {

        Number number = new NumberImpl(Signs.POSITIVE);

        assertEquals(false, number.isFraction());
        assertEquals(true, number.isInfinity());
        assertEquals(false, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(false, number.isNaturalNumberIncludingZero());
        assertEquals(false, number.isNegative());
        assertEquals(true, number.isPositive());
        assertEquals(false, number.isZero());
    }

    /**
     * Tests the properties of signed (negative) infinity.
     */
    @Test
    public void testNegativeInfinity() {

        Number number = new NumberImpl(Signs.NEGATIVE);

        assertEquals(false, number.isFraction());
        assertEquals(true, number.isInfinity());
        assertEquals(false, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(false, number.isNaturalNumberIncludingZero());
        assertEquals(true, number.isNegative());
        assertEquals(false, number.isPositive());
        assertEquals(false, number.isZero());
    }

    /**
     * Tests the properties of an unsigned one.
     */
    @Test
    public void testUnsignedOne() {

        Number number = new NumberImpl("1");

        assertEquals(false, number.isFraction());
        assertEquals(false, number.isInfinity());
        assertEquals(true, number.isInteger());
        assertEquals(true, number.isNaturalNumber());
        assertEquals(true, number.isNaturalNumberIncludingZero());
        assertEquals(false, number.isNegative());
        assertEquals(true, number.isPositive());
        assertEquals(false, number.isZero());
    }

    /**
     * Tests the properties of a signed (positive) one.
     */
    @Test
    public void testPositiveOne() {

        Number number = new NumberImpl("+1");

        assertEquals(false, number.isFraction());
        assertEquals(false, number.isInfinity());
        assertEquals(true, number.isInteger());
        assertEquals(true, number.isNaturalNumber());
        assertEquals(true, number.isNaturalNumberIncludingZero());
        assertEquals(false, number.isNegative());
        assertEquals(true, number.isPositive());
        assertEquals(false, number.isZero());
    }

    /**
     * Tests the properties of an unsigned (negative) one.
     */
    @Test
    public void testNegativeOne() {

        Number number = new NumberImpl("-1");

        assertEquals(false, number.isFraction());
        assertEquals(false, number.isInfinity());
        assertEquals(true, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(false, number.isNaturalNumberIncludingZero());
        assertEquals(true, number.isNegative());
        assertEquals(false, number.isPositive());
        assertEquals(false, number.isZero());
    }

    /**
     * Tests the properties of an unsigned decimal number.
     */
    @Test
    public void testUnsignedDecimal() {

        Number number = new NumberImpl("1.1");

        assertEquals(true, number.isFraction());
        assertEquals(false, number.isInfinity());
        assertEquals(false, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(false, number.isNaturalNumberIncludingZero());
        assertEquals(false, number.isNegative());
        assertEquals(true, number.isPositive());
        assertEquals(false, number.isZero());
    }

    /**
     * Tests the properties of a signed (positive) decimal number.
     */
    @Test
    public void testPositiveDecimal() {

        Number number = new NumberImpl("+1.1");

        assertEquals(true, number.isFraction());
        assertEquals(false, number.isInfinity());
        assertEquals(false, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(false, number.isNaturalNumberIncludingZero());
        assertEquals(false, number.isNegative());
        assertEquals(true, number.isPositive());
        assertEquals(false, number.isZero());
    }

    /**
     * Tests the properties of a signed (negative) decimal number.
     */
    @Test
    public void testNegativeDecimal() {

        Number number = new NumberImpl("-1.1");

        assertEquals(true, number.isFraction());
        assertEquals(false, number.isInfinity());
        assertEquals(false, number.isInteger());
        assertEquals(false, number.isNaturalNumber());
        assertEquals(false, number.isNaturalNumberIncludingZero());
        assertEquals(true, number.isNegative());
        assertEquals(false, number.isPositive());
        assertEquals(false, number.isZero());
    }

}

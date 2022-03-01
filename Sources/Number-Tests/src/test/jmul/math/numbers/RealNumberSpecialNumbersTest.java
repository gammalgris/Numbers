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


import jmul.math.numbers.RealDecimalNumber;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite contains tests regarding some special numbers. Currently htere exist only two speical numbers
 * (positive infinity and negative infinity).
 *
 * @author Kristian Kutin
 */
public class RealNumberSpecialNumbersTest {

    /**
     * Tests positive infinity.
     */
    @Test
    public void testPositiveInfinity() {

        RealDecimalNumber n = RealDecimalNumber.POSITIVE_INFINITY;
        assertEquals("Infinity", n.toStandardNotation());
        assertEquals("Infinity", n.toString());

        assertEquals(true, n.isInfinity());

        assertEquals(true, n.isPositive());
        assertEquals(false, n.isNegative());

        assertEquals(0, n.digits());
        assertEquals(0, n.leftDigits());
        assertEquals(0, n.rightDigits());
    }

    /**
     * Tests negative infinity.
     */
    @Test
    public void testNegativeInfinity() {

        RealDecimalNumber n = RealDecimalNumber.NEGATIVE_INFINITY;
        assertEquals("-Infinity", n.toStandardNotation());
        assertEquals("-Infinity", n.toString());

        assertEquals(true, n.isInfinity());

        assertEquals(false, n.isPositive());
        assertEquals(true, n.isNegative());

        assertEquals(0, n.digits());
        assertEquals(0, n.leftDigits());
        assertEquals(0, n.rightDigits());
    }

}

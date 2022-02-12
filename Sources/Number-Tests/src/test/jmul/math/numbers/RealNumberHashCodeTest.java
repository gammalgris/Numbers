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


import jmul.math.numbers.RealNumber;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests the hash code calulation.
 *
 * @author Kristian Kutin
 */
public class RealNumberHashCodeTest {

    /**
     * Hash code test for zero.
     */
    @Test
    public void testZero() {

        RealNumber n1 = new RealNumber(0);
        int h1 = n1.hashCode();

        RealNumber n2 = new RealNumber(0);
        int h2 = n2.hashCode();

        String message = String.format("Comparing the numbers %s and %s has failed!", n1.toString(), n2.toString());

        assertEquals(message, n1, n2);
        assertEquals(message, h1, h2);
    }

    /**
     * Hash code test for positive infinity.
     */
    @Test
    public void testPositiveInfinity() {

        RealNumber n1 = RealNumber.POSITIVE_INFINITY;
        int h1 = n1.hashCode();

        RealNumber n2 = RealNumber.POSITIVE_INFINITY;
        int h2 = n2.hashCode();

        String message = String.format("Comparing the numbers %s and %s has failed!", n1.toString(), n2.toString());

        assertEquals(message, n1, n2);
        assertEquals(message, h1, h2);
    }

    /**
     * Hash code test for negative infinity.
     */
    @Test
    public void testNegativeInfinity() {

        RealNumber n1 = RealNumber.NEGATIVE_INFINITY;
        int h1 = n1.hashCode();

        RealNumber n2 = RealNumber.NEGATIVE_INFINITY;
        int h2 = n2.hashCode();

        String message = String.format("Comparing the numbers %s and %s has failed!", n1.toString(), n2.toString());

        assertEquals(message, n1, n2);
        assertEquals(message, h1, h2);
    }

}

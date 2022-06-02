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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests the equality comparison of nummbers.
 *
 * @author Kristian Kutin
 */
public class NumberEqualityTest {

    @Test
    public void testZeroAndOne() {

        Number zero = new NumberImpl("0");
        Number one = new NumberImpl("1");

        String message =
            String.format("The equality test failed for the numbers \"%s\" and \"%s\")!", zero.toString(),
                          one.toString());
        assertFalse(message, zero.equals(one));
        assertFalse(message, one.equals(zero));
    }

    @Test
    public void testNull() {

        Number zero = new NumberImpl("0");

        String message = String.format("The equality test failed for the number \"%s\")!", zero.toString());
        assertFalse(message, zero.equals(null));
    }

    @Test
    public void testZeroAndReference() {

        Number zero = new NumberImpl("0");
        Number reference = zero;

        String message = String.format("The equality test failed for the number \"%s\"!", zero.toString());
        assertTrue(message, zero.equals(reference));
        assertTrue(message, reference.equals(zero));
    }

    @Test
    public void testZeroAndClone() {

        Number zero = new NumberImpl("0");
        Number clone = new NumberImpl("0");

        String message = String.format("The equality test failed for the number \"%s\"!", zero.toString());
        assertTrue(message, zero.equals(clone));
        assertTrue(message, clone.equals(zero));
    }

    @Test
    public void testInfinityAndNumber() {

        Number pi = new NumberImpl();
        Number ni = new NumberImpl("11");

        String message =
            String.format("The equality test failed for the numbers \"%s\" and \"%s\")!", pi.toString(), ni.toString());
        assertFalse(message, pi.equals(ni));
        assertFalse(message, ni.equals(pi));

    }

    @Test
    public void testPositiveInfinityAndNegativeInfinity() {

        Number pi = new NumberImpl(Signs.POSITIVE);
        Number ni = new NumberImpl(Signs.NEGATIVE);

        String message =
            String.format("The equality test failed for the numbers \"%s\" and \"%s\")!", pi.toString(), ni.toString());
        assertFalse(message, pi.equals(ni));
        assertFalse(message, ni.equals(pi));
    }

    @Test
    public void testSimilarBigIntegers() {

        Number n1 = new NumberImpl("9876543210987654321");
        Number n2 = new NumberImpl("8876543210987654321");

        String message =
            String.format("The equality test failed for the numbers \"%s\" and \"%s\")!", n1.toString(), n2.toString());
        assertFalse(message, n1.equals(n2));
        assertFalse(message, n2.equals(n1));
    }

    @Test
    public void testEqualBigIntegers() {

        Number n1 = new NumberImpl("9876543210987654321");
        Number n2 = new NumberImpl("9876543210987654321");

        String message =
            String.format("The equality test failed for the numbers \"%s\" and \"%s\")!", n1.toString(), n2.toString());
        assertTrue(message, n1.equals(n2));
        assertTrue(message, n2.equals(n1));
    }

    @Test
    public void testDistinctBigDecimalNumbers() {

        Number n1 = new NumberImpl("9876543210987654321.0123456789123456789");
        Number n2 = new NumberImpl("9876543210987654321.0123456789123456788");

        String message =
            String.format("The equality test failed for the numbers \"%s\" and \"%s\")!", n1.toString(), n2.toString());
        assertFalse(message, n1.equals(n2));
        assertFalse(message, n2.equals(n1));
    }

    @Test
    public void testEqualBigDecimalNumbers() {


        Number n1 = new NumberImpl("9876543210987654321.0123456789123456789");
        Number n2 = new NumberImpl("9876543210987654321.0123456789123456789");

        String message =
            String.format("The equality test failed for the numbers \"%s\" and \"%s\")!", n1.toString(), n2.toString());
        assertTrue(message, n1.equals(n2));
        assertTrue(message, n2.equals(n1));
    }

}

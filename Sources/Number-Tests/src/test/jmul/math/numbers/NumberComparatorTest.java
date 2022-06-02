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


import org.junit.Test;

import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;

import static org.junit.Assert.assertEquals;


/**
 * This test suite tests the currently implemented comparator for numbers.
 *
 * @author Kristian Kutin
 */
public class NumberComparatorTest {

    @Test
    public void testZeroAndOneComparison() {

        Number zero = new NumberImpl("0");
        Number one = new NumberImpl("1");

        String message =
            String.format("The equality test failed for the numbers \"%s\" and \"%s\")!", zero.toString(),
                          one.toString());
        assertEquals(message, -1, zero.compareTo(one));
        assertEquals(message, 1, one.equals(zero));
    }

}

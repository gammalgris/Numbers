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

package test.jmul.math.numbers.digits;


import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.PositionalNumeralSystems;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * The test suite contains some special cases regarding digit comparison and equality.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SpecialCasesTest {

    /**
     * The default constructor.
     */
    public SpecialCasesTest() {

        super();
    }

    /**
     * Tests the comparison with <code>null</code>.
     */
    @Test
    public void testEqualsNull() {

        Digit d = PositionalNumeralSystems.ordinalToDigit(10, 1);
        boolean actualResult = d.equals(null);

        assertEquals(false, actualResult);
    }

    /**
     * Tests the comparison with <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCompareNull() {

        Digit d = PositionalNumeralSystems.ordinalToDigit(10, 1);
        d.compareTo(null);
    }

}

/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests shifting the decimal point.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ShiftWithInfinityTest {

    /**
     * Tests shifting the decimal point by infinity.
     */
    @Test
    public void shiftOneLeftByInfinity() {

        Number n = new NumberImpl("1");
        Number shifts = new NumberImpl();

        Number result = n.shiftLeft(shifts);

        assertTrue(result.isZero());
        assertTrue(result.isPositive());
    }

    /**
     * Tests shifting the decimal point by infinity.
     */
    @Test
    public void shiftOneRightByInfinity() {

        Number n = new NumberImpl("1");
        Number shifts = new NumberImpl();

        Number result = n.shiftRight(shifts);

        assertTrue(result.isInfinity());
        assertTrue(result.isPositive());
    }

    /**
     * Tests shifting the decimal point by infinity.
     */
    @Test
    public void shiftOneLeftByNegativeInfinity() {

        Number n = new NumberImpl("1");
        Number shifts = new NumberImpl();

        Number result = n.shiftLeft(shifts);

        assertTrue(result.isZero());
        assertTrue(result.isPositive());
    }

    /**
     * Tests shifting the decimal point by infinity.
     */
    @Test
    public void shiftOneRightByNegativeInfinity() {

        Number n = new NumberImpl("1");
        Number shifts = new NumberImpl();

        Number result = n.shiftRight(shifts);

        assertTrue(result.isInfinity());
        assertTrue(result.isPositive());
    }

}

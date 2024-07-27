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


import jmul.math.Math;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.exceptions.UndefinedOperationException;

import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * This test suite tests
 */
@UnitTest
public class ShiftWithIllegalArgumentsTest {

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = UndefinedOperationException.class)
    public void shiftOneLeftByFraction() {

        Number n = new NumberImpl("1");
        Number shifts = new NumberImpl("1.1");

        n.shiftLeft(shifts);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shiftNullLeftByOne() {

        Number n = null;
        Number shifts = new NumberImpl("1");

        Math.shiftLeft(n, shifts);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shiftOneLeftByNull() {

        Number n = new NumberImpl("1");
        Number shifts = null;

        n.shiftLeft(shifts);
    }

}

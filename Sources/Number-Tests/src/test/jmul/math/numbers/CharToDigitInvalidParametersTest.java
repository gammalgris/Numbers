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


import jmul.math.numbers.system.NumeralSystems;

import org.junit.Test;


/**
 * This test suite contains some tests with invalid parameters to test the error handling.
 *
 * @author Kristian Kutin
 */
public class CharToDigitInvalidParametersTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBase() {

        NumeralSystems.charToDigit(3, '0');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOrdinal() {

        NumeralSystems.charToDigit(2, '2');
    }

}
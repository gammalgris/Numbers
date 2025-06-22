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

package test.jmul.math.constants;


import jmul.math.constants.Constant;
import static jmul.math.constants.ConstantHelper.createConstant;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests constants for various number bases.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class ConstantTest {

    /**
     * Tests creating a constant and checking the default value.
     */
    @Test
    public void testCreate() {

        Number initialValue = createNumber(10, "10");

        Constant constant = createConstant(initialValue);

        Number actualValue = constant.value(10);
        assertEquals(initialValue, actualValue);
        assertEquals(initialValue.toString(), actualValue.toString());
    }

    /**
     * Tests retrieving a value for a different number base.
     */
    @Test
    public void testGetValueForDifferentNumberBase() {

        Number initialValue = createNumber(10, "10");

        Constant constant = createConstant(initialValue);

        Number actualValue = constant.value(16);
        assertEquals(16, actualValue.base());
        assertEquals("A", actualValue.toString());
    }

}

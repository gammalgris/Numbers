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

package test.jmul.math.logarithms;


import jmul.math.logarithms.Logarithm;
import static jmul.math.logarithms.LogarithmHelper.createLogarithm;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * A tests uite testing creating logarithm expressions.
 *
 * @author Kristian Kutin
 */
public class CreateLogarithmTest {

    /**
     * Create a logarithm expressions.
     */
    @Test
    public void testCreateLogarithm() {

        Logarithm logarithm = createLogarithm(10, "2", "3");

        assertEquals("base", 10, logarithm.base());
        assertEquals("logarithm base", "2", logarithm.logarithmBase().toString());
        assertEquals("numerus base", "3", logarithm.numerus().toString());
    }

    /**
     * Create a logarithm expressions with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateLogarithmWithLogarithmBase1() {

        createLogarithm(10, "1", "3");
    }

    /**
     * Create a logarithm expressions.
     */
    @Test
    public void testCreateLogarithmWithLogarithmBase0() {

        Logarithm logarithm = createLogarithm(10, "0", "3");

        assertEquals("base", 10, logarithm.base());
        assertEquals("logarithm base", "0", logarithm.logarithmBase().toString());
        assertEquals("numerus base", "3", logarithm.numerus().toString());
    }

    /**
     * Create a logarithm expressions with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateLogarithmWithLogarithmBaseMinus2() {

        createLogarithm(10, "-2", "3");
    }

    /**
     * Create a logarithm expressions with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateLogarithmWithZeroNumerus() {

        createLogarithm(10, "2", "0");
    }

    /**
     * Create a logarithm expressions with invalid parameters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateLogarithmWithNegativeNumerus() {

        createLogarithm(10, "2", "-1");
    }

}

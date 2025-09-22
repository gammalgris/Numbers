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


import jmul.math.numbers.Number;
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

    @Test
    public void testCreateLogarithm() {

        Logarithm logarithm = createLogarithm(10, "2", "3");
        
        assertEquals("base", 10, logarithm.base());
        assertEquals("logarithm base", "2", logarithm.logarithmBase().toString());
        assertEquals("numerus base", "3", logarithm.numerus().toString());
        
    }

}

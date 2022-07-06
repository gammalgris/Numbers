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

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


@UnitTest
public class CalculateWithInfinityTest {

    private static final int BASE;

    static {

        BASE = 10;
    }

    @Test
    public void addPositiveNumberAndPositiveInfinity() {

        Number n1 = new NumberImpl(BASE, "1");
        Number n2 = new NumberImpl(BASE);

        Number sum = n1.add(n2);
        String message = String.format("%s + %s", n1.toString(), n2.toString());
        assertEquals(message, sum, n2);
    }

    @Test
    public void addNegativeNumberAndPositiveInfinity() {

        Number n1 = new NumberImpl(BASE, "-1");
        Number n2 = new NumberImpl(BASE);

        Number sum = n1.add(n2);
        String message = String.format("%s + %s", n1.toString(), n2.toString());
        assertEquals(message, sum, n2);
    }

    @Test
    public void addPositiveNumberAndNegativeInfinity() {

        Number n1 = new NumberImpl(BASE, "1");
        Number n2 = new NumberImpl(BASE, Signs.NEGATIVE);

        Number sum = n1.add(n2);
        String message = String.format("%s + %s", n1.toString(), n2.toString());
        assertEquals(message, sum, n2);
    }

    @Test
    public void addNegativeNumberAndNegativeInfinity() {

        Number n1 = new NumberImpl(BASE, "-1");
        Number n2 = new NumberImpl(BASE, Signs.NEGATIVE);

        Number sum = n1.add(n2);
        String message = String.format("%s + %s", n1.toString(), n2.toString());
        assertEquals(message, sum, n2);
    }

}

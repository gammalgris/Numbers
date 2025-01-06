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
import jmul.math.numbers.nodes.NodesHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite test filling up leading and trailing zeroes in operands.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class FillUpWithZeroesTest {


    /**
     * Test filling up leading and trailing zeroes in the first operand.
     */
    @Test
    public void testFillFirstOperand() {

        String expected1 = "0001.2000";
        String expected2 = "1234.5678";

        Number number1 = new NumberImpl("1.2");
        Number number2 = new NumberImpl("1234.5678");

        NodesHelper.fillUpWithZeroes(number1.centerNode(), number2.centerNode());

        String actual1 = number1.toString();
        String actual2 = number2.toString();

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    /**
     * Test filling up leading zeroes in the first operand and trailing zeroes in the
     * second operand.
     */
    @Test
    public void testFillLeadingZeroesFirstOperandAndTrailingZeroesSecondOperand() {

        String expected1 = "0001.2345";
        String expected2 = "1234.5";

        Number number1 = new NumberImpl("1.2345");
        Number number2 = new NumberImpl("1234.5");

        NodesHelper.fillUpWithZeroes(number1.centerNode(), number2.centerNode());

        String actual1 = number1.toString();
        String actual2 = number2.toString();

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    /**
     * Test fillung up trailing zeroes in the first operand and leading zeroes in the
     * second operand.
     */
    @Test
    public void testFillTrailingZeroesFirstOperandAndLeadingZeroesSecondOperand() {

        String expected1 = "1234.5000";
        String expected2 = "1.2345";

        Number number1 = new NumberImpl("1234.5");
        Number number2 = new NumberImpl("1.2345");

        NodesHelper.fillUpWithZeroes(number1.centerNode(), number2.centerNode());

        String actual1 = number1.toString();
        String actual2 = number2.toString();

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    /**
     * Test filling up leading and trailing zeroes in the second operand.
     */
    @Test
    public void testFillSecondOperand() {

        String expected1 = "1234.5678";
        String expected2 = "1.2";

        Number number1 = new NumberImpl("1234.5678");
        Number number2 = new NumberImpl("1.2");

        NodesHelper.fillUpWithZeroes(number1.centerNode(), number2.centerNode());

        String actual1 = number1.toString();
        String actual2 = number2.toString();

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

}

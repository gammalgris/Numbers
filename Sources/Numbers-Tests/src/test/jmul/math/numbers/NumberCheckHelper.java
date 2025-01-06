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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * A utility class which provides various tests.
 *
 * @author Kristian Kutin
 */
public final class NumberCheckHelper {

    /**
     * The default constructor.
     */
    private NumberCheckHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks that the specified operand and the result are unique number instances (i.e. linked lists).
     *
     * @param operand
     *        an operand
     * @param result
     *        the operation result
     */
    public static void checkNumbersAreUniqueInstances(Number operand, Number result) {

        assertFalse(operand == result);

        assertFalse(operand.centerNode() == result.centerNode());
    }

    /**
     * Checks that the specified operands and the result are unique number instances (i.e. linked lists).
     *
     * @param operand1
     *        an operand
     * @param operand2
     *        an operand
     * @param result
     *        the operation result
     */
    public static void checkNumbersAreUniqueInstances(Number operand1, Number operand2, Number result) {

        assertFalse(operand1 == operand2);
        assertFalse(operand1 == result);
        assertFalse(operand2 == result);

        assertFalse(operand1.centerNode() == operand2.centerNode());
        assertFalse(operand1.centerNode() == result.centerNode());
        assertFalse(operand2.centerNode() == result.centerNode());
    }

    /**
     * Compare the specified number with the specified string representation of a number. If these
     * don't match then an assertion fails.
     *
     * @param number
     *        a number
     * @param stringRepresentation
     *        the string representation which should match the specified number
     */
    public static void checkNumberEqualsStringRepresentation(Number number, String stringRepresentation) {

        assertEquals(stringRepresentation, number.toString());
    }

}

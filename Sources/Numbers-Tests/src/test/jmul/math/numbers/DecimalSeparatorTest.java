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


import jmul.math.numbers.Constants;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suit tests the decimal separator in string representations of numbers.
 */
@UnitTest
public class DecimalSeparatorTest {

    /**
     * Tests the standard notation with a specified decimal separator.
     */
    @Test
    public void testStandardNotation() {

        char decimalSeparator = '.';
        String input = String.format("1%s1", decimalSeparator);

        Number n = createNumber(input);

        String s = n.toStandardNotation(decimalSeparator);
        assertEquals(input, s);
    }

    /**
     * Tests the standard notation with the default decimal separator.
     */
    @Test
    public void testStandardNotationWithDefaultDecimalSeparator() {

        char decimalSeparator = Constants.DECIMAL_SEPARATOR;
        String input = String.format("1%s1", decimalSeparator);

        Number n = createNumber(input);

        String s = n.toStandardNotation();
        assertEquals(input, s);
    }

    /**
     * Tests the toString method with a specified decimal separator.
     */
    @Test
    public void testToString() {

        char decimalSeparator = '.';
        String input = String.format("1%s1", decimalSeparator);

        Number n = createNumber(input);

        String s = n.toString(decimalSeparator);
        assertEquals(input, s);
    }

    /**
     * Tests the toString method with the default decimal separator.
     */
    @Test
    public void testToStringWithDefaultDecimalSeparator() {

        char decimalSeparator = Constants.DECIMAL_SEPARATOR;
        String input = String.format("1%s1", decimalSeparator);

        Number n = createNumber(input);

        String s = n.toString();
        assertEquals(input, s);
    }

    /**
     * Tests the scientific notation with a specified decimal separator.
     */
    @Test
    public void testScientificNotation() {

        char decimalSeparator = '.';
        String input = String.format("1%s1E111", decimalSeparator);

        Number n = createNumber(input);

        String s = n.toScientificNotation(decimalSeparator);
        assertEquals(input, s);
    }

    /**
     * Tests the scientific notation with the default decimal separator.
     */
    @Test
    public void testScientificNotationWithDefaultDecimalSeparator() {

        char decimalSeparator = Constants.DECIMAL_SEPARATOR;
        String input = String.format("1%s1E111", decimalSeparator);

        Number n = createNumber(input);

        String s = n.toScientificNotation();
        assertEquals(input, s);
    }

}

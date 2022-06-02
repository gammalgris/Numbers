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

package test.jmul.math.numbers.digits;


import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.digits.NumeralSystems;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests a small sample of available numeral systems (i.e. if the digits are created correctly).
 * 
 * @author Kristian Kutin
 */
public class NumeralSystemsTest {

    /**
     * Tests the properties of a digit.
     *
     * @param digit
     *        a digit
     * @param base
     *        the expected base
     * @param ordinal
     *        the expected ordinal value
     * @param symbol
     *        the expected symbol
     */
    private void testDigit(Digit digit, int base, int ordinal, char symbol) {

        assertEquals(digit.base(), base);
        assertEquals(digit.ordinal(), ordinal);
        assertEquals(digit.symbol(), symbol);
    }

    /**
     * Tests all digits within the decimal numeral system.
     */
    @Test
    public void testDecimalSystem() {

        Digit digit;

        digit = NumeralSystems.ordinalToDigit(10, 0);
        testDigit(digit, 10, 0, '0');

        digit = NumeralSystems.ordinalToDigit(10, 1);
        testDigit(digit, 10, 1, '1');

        digit = NumeralSystems.ordinalToDigit(10, 2);
        testDigit(digit, 10, 2, '2');

        digit = NumeralSystems.ordinalToDigit(10, 3);
        testDigit(digit, 10, 3, '3');

        digit = NumeralSystems.ordinalToDigit(10, 4);
        testDigit(digit, 10, 4, '4');

        digit = NumeralSystems.ordinalToDigit(10, 5);
        testDigit(digit, 10, 5, '5');

        digit = NumeralSystems.ordinalToDigit(10, 6);
        testDigit(digit, 10, 6, '6');

        digit = NumeralSystems.ordinalToDigit(10, 7);
        testDigit(digit, 10, 7, '7');

        digit = NumeralSystems.ordinalToDigit(10, 8);
        testDigit(digit, 10, 8, '8');

        digit = NumeralSystems.ordinalToDigit(10, 9);
        testDigit(digit, 10, 9, '9');
    }

    /**
     * Tests the lower bound of a decimal numeral system.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecimalSystemLowerBound() {

        NumeralSystems.ordinalToDigit(10, -1);
    }

    /**
     * Tests the upper bound of a decimal numeral system.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecimalSystemUpperBound() {

        NumeralSystems.ordinalToDigit(10, 10);
    }

    /**
     * Tests all digits within the binary numeral system.
     */
    @Test
    public void testBinarySystem() {

        Digit digit;

        digit = NumeralSystems.ordinalToDigit(2, 0);
        testDigit(digit, 2, 0, '0');

        digit = NumeralSystems.ordinalToDigit(2, 1);
        testDigit(digit, 2, 1, '1');
    }

    /**
     * Tests the lower bound of a binary numeral system.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBinarySystemLowerBound() {

        NumeralSystems.ordinalToDigit(2, -1);
    }

    /**
     * Tests the upper bound of a binary numeral system.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBinarySystemUpperBound() {

        NumeralSystems.ordinalToDigit(2, 2);
    }

}

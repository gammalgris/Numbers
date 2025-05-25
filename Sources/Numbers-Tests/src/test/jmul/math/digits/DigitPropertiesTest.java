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

package test.jmul.math.digits;


import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * Tests the properties of digits for selected number bases.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class DigitPropertiesTest {

    /**
     * Tests all digit properties for digits of number base 2.
     */
    @Test
    public void testBase2() {

        int base = 2;
        Digit digit;

        digit = PositionalNumeralSystems.ordinalToDigit(base, 0);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", true, digit.isEven());
        assertEquals("odd test", false, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(0));
        assertEquals("ordinal test #2", 0, digit.ordinal());
        assertEquals("zero test", true, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 1);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", false, digit.isEven());
        assertEquals("odd test", true, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(1));
        assertEquals("ordinal test #2", 1, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());
    }

    /**
     * Tests all digit properties for digits of number base 3.
     */
    @Test
    public void testBase3() {

        int base = 3;
        Digit digit;

        digit = PositionalNumeralSystems.ordinalToDigit(base, 0);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", true, digit.isEven());
        assertEquals("odd test", false, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(0));
        assertEquals("ordinal test #2", 0, digit.ordinal());
        assertEquals("zero test", true, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 1);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", false, digit.isEven());
        assertEquals("odd test", true, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(1));
        assertEquals("ordinal test #2", 1, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 2);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", true, digit.isEven());
        assertEquals("odd test", false, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(2));
        assertEquals("ordinal test #2", 2, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());
    }

    /**
     * Tests all digit properties for digits of number base 2.
     */
    @Test
    public void testBase4() {

        int base = 4;
        Digit digit;

        digit = PositionalNumeralSystems.ordinalToDigit(base, 0);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", true, digit.isEven());
        assertEquals("odd test", false, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(0));
        assertEquals("ordinal test #2", 0, digit.ordinal());
        assertEquals("zero test", true, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 1);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", false, digit.isEven());
        assertEquals("odd test", true, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(1));
        assertEquals("ordinal test #2", 1, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 2);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", true, digit.isEven());
        assertEquals("odd test", false, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(2));
        assertEquals("ordinal test #2", 2, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 3);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", false, digit.isEven());
        assertEquals("odd test", true, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(3));
        assertEquals("ordinal test #2", 3, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());
    }

    /**
     * Tests all digit properties for digits of number base 5.
     */
    @Test
    public void testBase5() {

        int base = 5;
        Digit digit;

        digit = PositionalNumeralSystems.ordinalToDigit(base, 0);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", true, digit.isEven());
        assertEquals("odd test", false, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(0));
        assertEquals("ordinal test #2", 0, digit.ordinal());
        assertEquals("zero test", true, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 1);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", false, digit.isEven());
        assertEquals("odd test", true, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(1));
        assertEquals("ordinal test #2", 1, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 2);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", true, digit.isEven());
        assertEquals("odd test", false, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(2));
        assertEquals("ordinal test #2", 2, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 3);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", false, digit.isEven());
        assertEquals("odd test", true, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(3));
        assertEquals("ordinal test #2", 3, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());

        digit = PositionalNumeralSystems.ordinalToDigit(base, 4);

        assertEquals("base mismatch", base, digit.base());
        assertEquals("even test", true, digit.isEven());
        assertEquals("odd test", false, digit.isOdd());
        assertEquals("ordinal test #1", true, digit.isOrdinal(4));
        assertEquals("ordinal test #2", 4, digit.ordinal());
        assertEquals("zero test", false, digit.isZero());
    }

}

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
 * This test suite tests the creation of digits.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CreateDigitTest {

    /**
     * Tests creating digits and checks the properties.
     */
    @Test
    public void testCreateDigits() {

        char[] symbols = { '0', '1' };
        PositionalNumeralSystems.addPositionalNumeralSystemWithCustomSymbols(symbols);

        int base = symbols.length;

        for (int ordinal = 0; ordinal < base; ordinal++) {

            Digit digit = PositionalNumeralSystems.ordinalToDigit(base, ordinal);

            char expectedSymbol = symbols[ordinal];

            assertEquals("base mismatch", base, digit.base());
            assertEquals("ordinal mismatch", ordinal, digit.ordinal());
            assertEquals("symbol mismatch", expectedSymbol, digit.symbol());
        }
    }

}

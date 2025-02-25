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

package test.jmul.math.digits;


import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;

import jmul.test.classification.ManualTest;

import org.junit.Ignore;


/**
 * This test suite contains manual tests for looking up digits.
 *
 * @author Kristian Kutin
 */
@Ignore
@ManualTest
public class ShowDigitsTest {

    /**
     * Prints digits for a specific base to the console.
     *
     * @param args
     *        command line parameters. These are ignored.
     */
    public static void main(String... args) {

        int base = 62;

        System.out.println("base=" + base);

        for (int ordinal = 0; ordinal < base; ordinal++) {

            Digit digit = PositionalNumeralSystems.ordinalToDigit(base, ordinal);
            String message = String.format("ordinal=%d; digit='%s'", ordinal, digit);
            System.out.println(message);
        }
    }

}

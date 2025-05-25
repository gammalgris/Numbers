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


import java.util.Set;

import jmul.math.digits.Digit;
import jmul.math.digits.DigitHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests determining digits for rounding by base.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class IdentifyRoundingDigitsTest {

    /**
     * Determines the digits in number base 2 which are rounded down, rounded up and which represents the middle.
     */
    @Test
    public void testBase2() {

        int base = 2;

        Set<Digit> roundDown = DigitHelper.determineRoundDownDigits(base);
        Set<Digit> middle = DigitHelper.determineMiddleDigit(base);
        Set<Digit> roundUp = DigitHelper.determineRoundUpDigits(base);

        // check set sizes

        assertEquals("digits which are rounded down", 0, roundDown.size());
        assertEquals("the middle digit", 1, middle.size());
        assertEquals("digits which are rounded up", 0, roundUp.size());
        assertEquals("sum check", base - 1, roundDown.size() + middle.size() + roundUp.size());
    }

    /**
     * Determines the digits in number base 3 which are rounded down, rounded up and which represents the middle.
     */
    @Test
    public void testBase3() {

        int base = 3;

        Set<Digit> roundDown = DigitHelper.determineRoundDownDigits(base);
        Set<Digit> middle = DigitHelper.determineMiddleDigit(base);
        Set<Digit> roundUp = DigitHelper.determineRoundUpDigits(base);

        // check set sizes

        assertEquals("digits which are rounded down", 1, roundDown.size());
        assertEquals("the middle digit", 0, middle.size());
        assertEquals("digits which are rounded up", 1, roundUp.size());
        assertEquals("sum check", base - 1, roundDown.size() + middle.size() + roundUp.size());

        // cross check sets (i.e. sets should have no common digits)

        assertEquals("check set#1", false, roundDown.containsAll(roundUp));

        assertEquals("check set#2", false, middle.containsAll(roundDown));
        assertEquals("check set#2", false, middle.containsAll(roundUp));

        assertEquals("check set#3", false, roundUp.containsAll(roundDown));
    }

    /**
     * Determines the digits in number base 3 which are rounded down, rounded up and which represents the middle.
     */
    @Test
    public void testBase4() {

        int base = 4;

        Set<Digit> roundDown = DigitHelper.determineRoundDownDigits(base);
        Set<Digit> middle = DigitHelper.determineMiddleDigit(base);
        Set<Digit> roundUp = DigitHelper.determineRoundUpDigits(base);

        // check set sizes

        assertEquals("digits which are rounded down", 1, roundDown.size());
        assertEquals("the middle digit", 1, middle.size());
        assertEquals("digits which are rounded up", 1, roundUp.size());
        assertEquals("sum check", base - 1, roundDown.size() + middle.size() + roundUp.size());

        // cross check sets (i.e. sets should have no common digits)

        assertEquals("check set#1", false, roundDown.containsAll(roundUp));
        assertEquals("check set#1", false, roundDown.containsAll(middle));

        assertEquals("check set#2", false, middle.containsAll(roundDown));
        assertEquals("check set#2", false, middle.containsAll(roundUp));

        assertEquals("check set#3", false, roundUp.containsAll(roundDown));
        assertEquals("check set#3", false, roundUp.containsAll(middle));
    }

    /**
     * Determines the digits in number base 5 which are rounded down, rounded up and which represents the middle.
     */
    @Test
    public void testBase5() {

        int base = 5;

        Set<Digit> roundDown = DigitHelper.determineRoundDownDigits(base);
        Set<Digit> middle = DigitHelper.determineMiddleDigit(base);
        Set<Digit> roundUp = DigitHelper.determineRoundUpDigits(base);

        // check set sizes

        assertEquals("digits which are rounded down", 2, roundDown.size());
        assertEquals("the middle digit", 0, middle.size());
        assertEquals("digits which are rounded up", 2, roundUp.size());
        assertEquals("sum check", base - 1, roundDown.size() + middle.size() + roundUp.size());

        // cross check sets (i.e. sets should have no common digits)

        assertEquals("check set#1", false, roundDown.containsAll(roundUp));

        assertEquals("check set#2", false, middle.containsAll(roundDown));
        assertEquals("check set#2", false, middle.containsAll(roundUp));

        assertEquals("check set#3", false, roundUp.containsAll(roundDown));
    }

    /**
     * Determines the digits in number base 6 which are rounded down, rounded up and which represents the middle.
     */
    @Test
    public void testBase6() {

        int base = 6;

        Set<Digit> roundDown = DigitHelper.determineRoundDownDigits(base);
        Set<Digit> middle = DigitHelper.determineMiddleDigit(base);
        Set<Digit> roundUp = DigitHelper.determineRoundUpDigits(base);

        // check set sizes

        assertEquals("digits which are rounded down", 2, roundDown.size());
        assertEquals("the middle digit", 1, middle.size());
        assertEquals("digits which are rounded up", 2, roundUp.size());
        assertEquals("sum check", base - 1, roundDown.size() + middle.size() + roundUp.size());

        // cross check sets (i.e. sets should have no common digits)

        assertEquals("check set#1", false, roundDown.containsAll(roundUp));
        assertEquals("check set#1", false, roundDown.containsAll(middle));

        assertEquals("check set#2", false, middle.containsAll(roundDown));
        assertEquals("check set#2", false, middle.containsAll(roundUp));

        assertEquals("check set#3", false, roundUp.containsAll(roundDown));
        assertEquals("check set#3", false, roundUp.containsAll(middle));
    }

    /**
     * Determines the digits in number base 7 which are rounded down, rounded up and which represents the middle.
     */
    @Test
    public void testBase7() {

        int base = 7;

        Set<Digit> roundDown = DigitHelper.determineRoundDownDigits(base);
        Set<Digit> middle = DigitHelper.determineMiddleDigit(base);
        Set<Digit> roundUp = DigitHelper.determineRoundUpDigits(base);

        // check set sizes

        assertEquals("digits which are rounded down", 3, roundDown.size());
        assertEquals("the middle digit", 0, middle.size());
        assertEquals("digits which are rounded up", 3, roundUp.size());
        assertEquals("sum check", base - 1, roundDown.size() + middle.size() + roundUp.size());

        // cross check sets (i.e. sets should have no common digits)

        assertEquals("check set#1", false, roundDown.containsAll(roundUp));

        assertEquals("check set#2", false, middle.containsAll(roundDown));
        assertEquals("check set#2", false, middle.containsAll(roundUp));

        assertEquals("check set#3", false, roundUp.containsAll(roundDown));
    }

}

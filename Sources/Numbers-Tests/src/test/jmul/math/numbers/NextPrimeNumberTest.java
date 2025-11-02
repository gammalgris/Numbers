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

package test.jmul.math.numbers;


import jmul.math.Math;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * Tests calculating the next prime number.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class NextPrimeNumberTest {

    /**
     * Checks calculating the 1st prime number (i.e. ordinal 0).
     */
    @Test
    public void testFirstPrimeNumber() {

        Number ordinal = createNumber(10, "0");
        Number expectedPrimeNumber = createNumber(10, "2");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 2nd prime number (i.e. ordinal 1).
     */
    @Test
    public void testSecondPrimeNumber() {

        Number ordinal = createNumber(10, "1");
        Number expectedPrimeNumber = createNumber(10, "3");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 3rd prime number (i.e. ordinal 2).
     */
    @Test
    public void testThirdPrimeNumber() {

        Number ordinal = createNumber(10, "2");
        Number expectedPrimeNumber = createNumber(10, "5");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 4th prime number (i.e. ordinal 3).
     */
    @Test
    public void testFourthPrimeNumber() {

        Number ordinal = createNumber(10, "3");
        Number expectedPrimeNumber = createNumber(10, "7");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 5th prime number (i.e. ordinal 4).
     */
    @Test
    public void testFifthPrimeNumber() {

        Number ordinal = createNumber(10, "4");
        Number expectedPrimeNumber = createNumber(10, "11");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 6th prime number (i.e. ordinal 5).
     */
    @Test
    public void testSixthPrimeNumber() {

        Number ordinal = createNumber(10, "5");
        Number expectedPrimeNumber = createNumber(10, "13");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 7th prime number (i.e. ordinal 6).
     */
    @Test
    public void testSeventhPrimeNumber() {

        Number ordinal = createNumber(10, "6");
        Number expectedPrimeNumber = createNumber(10, "17");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 8th prime number (i.e. ordinal 7).
     */
    @Test
    public void testEightPrimeNumber() {

        Number ordinal = createNumber(10, "7");
        Number expectedPrimeNumber = createNumber(10, "19");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 9th prime number (i.e. ordinal 8).
     */
    @Test
    public void testNinthPrimeNumber() {

        Number ordinal = createNumber(10, "8");
        Number expectedPrimeNumber = createNumber(10, "23");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 10th prime number (i.e. ordinal 9).
     */
    @Test
    public void testTenthPrimeNumber() {

        Number ordinal = createNumber(10, "9");
        Number expectedPrimeNumber = createNumber(10, "29");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 11th prime number (i.e. ordinal 10).
     */
    @Test
    public void testEleventhPrimeNumber() {

        Number ordinal = createNumber(10, "10");
        Number expectedPrimeNumber = createNumber(10, "31");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 12th prime number (i.e. ordinal 11).
     */
    @Test
    public void testTwelfthPrimeNumber() {

        Number ordinal = createNumber(10, "11");
        Number expectedPrimeNumber = createNumber(10, "37");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 13th prime number (i.e. ordinal 12).
     */
    @Test
    public void testThirteenthPrimeNumber() {

        Number ordinal = createNumber(10, "12");
        Number expectedPrimeNumber = createNumber(10, "41");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 14th prime number (i.e. ordinal 13).
     */
    @Test
    public void testFourteenthPrimeNumber() {

        Number ordinal = createNumber(10, "13");
        Number expectedPrimeNumber = createNumber(10, "43");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 15th prime number (i.e. ordinal 14).
     */
    @Test
    public void testFifteenthPrimeNumber() {

        Number ordinal = createNumber(10, "14");
        Number expectedPrimeNumber = createNumber(10, "47");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 25th prime number (i.e. ordinal 24).
     */
    @Test
    public void testTwentyfifthPrimeNumber() {

        Number ordinal = createNumber(10, "25");
        Number expectedPrimeNumber = createNumber(10, "101");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 50th prime number (i.e. ordinal 49).
     */
    @Test
    public void testFiftiethPrimeNumber() {

        Number ordinal = createNumber(10, "49");
        Number expectedPrimeNumber = createNumber(10, "229");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

    /**
     * Checks calculating the 200th prime number (i.e. ordinal 199).
     */
    @Test
    public void testTwohundrethPrimeNumber() {

        Number ordinal = createNumber(10, "199");
        Number expectedPrimeNumber = createNumber(10, "1223");
        Number actualPrimeNumber = Math.nextPrimeNumber(ordinal);

        assertEquals("prime", expectedPrimeNumber, actualPrimeNumber);
    }

}

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


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.Math;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests determining prime numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class IsPrimeTest {

    /**
     * A number.
     */
    private final Number number;

    /**
     * The expected result.
     */
    private final boolean expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param number
     *        a number
     * @param expectedResult
     *        the expected result
     */
    public IsPrimeTest(Number number, Boolean expectedResult) {

        super();

        this.number = number;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s : %s", number.base(), number, expectedResult);
    }

    /**
     * Checks if a number can be identified as prime number.
     */
    @Test
    public void checkPrimeNumber() {

        boolean actualResult = number.isPrime();

        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Checks if a number can be identified as prime number.
     */
    @Test
    public void checkPrimeNumberVariant2() {

        boolean actualResult = Math.isPrime(number);

        assertEquals(toString(), expectedResult, actualResult);
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { createNumber(10, "1"), false });
        parameters.add(new Object[] { createNumber(10, "2"), true });
        parameters.add(new Object[] { createNumber(10, "3"), true });
        parameters.add(new Object[] { createNumber(10, "4"), false });
        parameters.add(new Object[] { createNumber(10, "5"), true });
        parameters.add(new Object[] { createNumber(10, "6"), false });
        parameters.add(new Object[] { createNumber(10, "7"), true });
        parameters.add(new Object[] { createNumber(10, "8"), false });
        parameters.add(new Object[] { createNumber(10, "9"), false });
        parameters.add(new Object[] { createNumber(10, "10"), false });
        parameters.add(new Object[] { createNumber(10, "11"), true });
        parameters.add(new Object[] { createNumber(10, "12"), false });
        parameters.add(new Object[] { createNumber(10, "13"), true });
        parameters.add(new Object[] { createNumber(10, "14"), false });
        parameters.add(new Object[] { createNumber(10, "15"), false });
        parameters.add(new Object[] { createNumber(10, "16"), false });
        parameters.add(new Object[] { createNumber(10, "17"), true });
        parameters.add(new Object[] { createNumber(10, "18"), false });
        parameters.add(new Object[] { createNumber(10, "19"), true });
        parameters.add(new Object[] { createNumber(10, "20"), false });
        parameters.add(new Object[] { createNumber(10, "21"), false });
        parameters.add(new Object[] { createNumber(10, "22"), false });
        parameters.add(new Object[] { createNumber(10, "23"), true });
        parameters.add(new Object[] { createNumber(10, "24"), false });
        parameters.add(new Object[] { createNumber(10, "25"), false });
        parameters.add(new Object[] { createNumber(10, "26"), false });
        parameters.add(new Object[] { createNumber(10, "27"), false });
        parameters.add(new Object[] { createNumber(10, "28"), false });
        parameters.add(new Object[] { createNumber(10, "29"), true });
        parameters.add(new Object[] { createNumber(10, "30"), false });
        parameters.add(new Object[] { createNumber(10, "31"), true });
        parameters.add(new Object[] { createNumber(10, "32"), false });
        parameters.add(new Object[] { createNumber(10, "33"), false });
        parameters.add(new Object[] { createNumber(10, "34"), false });
        parameters.add(new Object[] { createNumber(10, "35"), false });
        parameters.add(new Object[] { createNumber(10, "36"), false });
        parameters.add(new Object[] { createNumber(10, "37"), true });
        parameters.add(new Object[] { createNumber(10, "38"), false });
        parameters.add(new Object[] { createNumber(10, "39"), false });
        parameters.add(new Object[] { createNumber(10, "40"), false });
        parameters.add(new Object[] { createNumber(10, "41"), true });
        parameters.add(new Object[] { createNumber(10, "42"), false });
        parameters.add(new Object[] { createNumber(10, "43"), true });
        parameters.add(new Object[] { createNumber(10, "44"), false });
        parameters.add(new Object[] { createNumber(10, "45"), false });
        parameters.add(new Object[] { createNumber(10, "46"), false });
        parameters.add(new Object[] { createNumber(10, "47"), true });
        parameters.add(new Object[] { createNumber(10, "48"), false });
        parameters.add(new Object[] { createNumber(10, "49"), false });
        parameters.add(new Object[] { createNumber(10, "50"), false });
        parameters.add(new Object[] { createNumber(10, "51"), false });
        parameters.add(new Object[] { createNumber(10, "52"), false });
        parameters.add(new Object[] { createNumber(10, "53"), true });
        parameters.add(new Object[] { createNumber(10, "54"), false });
        parameters.add(new Object[] { createNumber(10, "55"), false });
        parameters.add(new Object[] { createNumber(10, "56"), false });
        parameters.add(new Object[] { createNumber(10, "57"), false });
        parameters.add(new Object[] { createNumber(10, "58"), false });
        parameters.add(new Object[] { createNumber(10, "59"), true });
        parameters.add(new Object[] { createNumber(10, "60"), false });
        parameters.add(new Object[] { createNumber(10, "61"), true });
        parameters.add(new Object[] { createNumber(10, "62"), false });
        parameters.add(new Object[] { createNumber(10, "63"), false });
        parameters.add(new Object[] { createNumber(10, "64"), false });
        parameters.add(new Object[] { createNumber(10, "65"), false });
        parameters.add(new Object[] { createNumber(10, "66"), false });
        parameters.add(new Object[] { createNumber(10, "67"), true });
        parameters.add(new Object[] { createNumber(10, "68"), false });
        parameters.add(new Object[] { createNumber(10, "69"), false });
        parameters.add(new Object[] { createNumber(10, "70"), false });
        parameters.add(new Object[] { createNumber(10, "71"), true });
        parameters.add(new Object[] { createNumber(10, "72"), false });
        parameters.add(new Object[] { createNumber(10, "73"), true });
        parameters.add(new Object[] { createNumber(10, "74"), false });
        parameters.add(new Object[] { createNumber(10, "75"), false });
        parameters.add(new Object[] { createNumber(10, "76"), false });
        parameters.add(new Object[] { createNumber(10, "77"), false });
        parameters.add(new Object[] { createNumber(10, "78"), false });
        parameters.add(new Object[] { createNumber(10, "79"), true });
        parameters.add(new Object[] { createNumber(10, "80"), false });
        parameters.add(new Object[] { createNumber(10, "81"), false });
        parameters.add(new Object[] { createNumber(10, "82"), false });
        parameters.add(new Object[] { createNumber(10, "83"), true });
        parameters.add(new Object[] { createNumber(10, "84"), false });
        parameters.add(new Object[] { createNumber(10, "85"), false });
        parameters.add(new Object[] { createNumber(10, "86"), false });
        parameters.add(new Object[] { createNumber(10, "87"), false });
        parameters.add(new Object[] { createNumber(10, "88"), false });
        parameters.add(new Object[] { createNumber(10, "89"), true });
        parameters.add(new Object[] { createNumber(10, "90"), false });
        parameters.add(new Object[] { createNumber(10, "91"), false });
        parameters.add(new Object[] { createNumber(10, "92"), false });
        parameters.add(new Object[] { createNumber(10, "93"), false });
        parameters.add(new Object[] { createNumber(10, "94"), false });
        parameters.add(new Object[] { createNumber(10, "95"), false });
        parameters.add(new Object[] { createNumber(10, "96"), false });
        parameters.add(new Object[] { createNumber(10, "97"), true });
        parameters.add(new Object[] { createNumber(10, "98"), false });
        parameters.add(new Object[] { createNumber(10, "99"), false });
        parameters.add(new Object[] { createNumber(10, "100"), false });

        return parameters;
    }

}

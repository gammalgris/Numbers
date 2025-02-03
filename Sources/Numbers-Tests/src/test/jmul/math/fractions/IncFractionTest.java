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

package test.jmul.math.fractions;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.Math;
import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests incrementing a fraction.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class IncFractionTest {

    /**
     * The first operand parsed from the specified number string.
     */
    private final Fraction operand;

    /**
     * The expected result parsed from the specified number string.
     */
    private final Fraction expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param operand
     *        a fraction
     * @param expectedResult
     *        the expected result
     */
    public IncFractionTest(Fraction operand, Fraction expectedResult) {

        super();

        this.operand = operand;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a string representation for this test case.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("[base:%d]: (%s)-- -> %s", operand.base(), operand, expectedResult);
    }

    /**
     * Tests incrementing a number.
     */
    @Test
    public void testInc() {

        Fraction actualResult = operand.inc();
        assertEquals(toString(), expectedResult, actualResult); // check by equals
        assertEquals(toString(), expectedResult.toString(), actualResult.toString()); // check string representation
    }

    /**
     * Tests incrementing a number.
     */
    @Test
    public void testIncVariant2() {

        Fraction actualResult = Math.inc(operand);
        assertEquals(toString(), expectedResult, actualResult); // check by equals
        assertEquals(toString(), expectedResult.toString(), actualResult.toString()); // check string representation
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createFraction(base, "-1"), createFraction(base, "0") });
            parameters.add(new Object[] { createFraction(base, "0"), createFraction(base, "1") });

            Digit highestDigit = PositionalNumeralSystems.ordinalToDigit(base, base - 1);
            Digit oneDigit = PositionalNumeralSystems.ordinalToDigit(base, 1);
            Digit zeroDigit = PositionalNumeralSystems.ordinalToDigit(base, 0);
            parameters.add(new Object[] { createFraction(base, "" + highestDigit),
                                          createFraction(base, "" + oneDigit + zeroDigit) });
            parameters.add(new Object[] { createFraction(base, "" + highestDigit + highestDigit),
                                          createFraction(base, "" + oneDigit + zeroDigit + zeroDigit) });
            parameters.add(new Object[] { createFraction(base, "" + highestDigit + highestDigit + highestDigit),
                                          createFraction(base, "" + oneDigit + zeroDigit + zeroDigit + zeroDigit) });

            parameters.add(new Object[] { createFraction(base, "-1", "1"), createFraction(base, "0") });
        }

        for (int base = BASE_MIN_LIMIT + 1; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "2") });
        }

        parameters.add(new Object[] { createFraction(2, "0"), createFraction(2, "1") });
        parameters.add(new Object[] { createFraction(2, "1"), createFraction(2, "10") });
        parameters.add(new Object[] { createFraction(2, "10"), createFraction(2, "11") });
        parameters.add(new Object[] { createFraction(2, "11"), createFraction(2, "100") });
        parameters.add(new Object[] { createFraction(2, "100"), createFraction(2, "101") });
        parameters.add(new Object[] { createFraction(2, "101"), createFraction(2, "110") });
        parameters.add(new Object[] { createFraction(2, "110"), createFraction(2, "111") });
        parameters.add(new Object[] { createFraction(2, "111"), createFraction(2, "1000") });
        parameters.add(new Object[] { createFraction(2, "1000"), createFraction(2, "1001") });
        parameters.add(new Object[] { createFraction(2, "1001"), createFraction(2, "1010") });
        parameters.add(new Object[] { createFraction(2, "1010"), createFraction(2, "1011") });
        parameters.add(new Object[] { createFraction(2, "1011"), createFraction(2, "1100") });
        parameters.add(new Object[] { createFraction(2, "1100"), createFraction(2, "1101") });
        parameters.add(new Object[] { createFraction(2, "1101"), createFraction(2, "1110") });
        parameters.add(new Object[] { createFraction(2, "1110"), createFraction(2, "1111") });
        parameters.add(new Object[] { createFraction(2, "1111"), createFraction(2, "10000") });
        parameters.add(new Object[] { createFraction(2, "10000"), createFraction(2, "10001") });

        parameters.add(new Object[] { createFraction(3, "0"), createFraction(3, "1") });
        parameters.add(new Object[] { createFraction(3, "1"), createFraction(3, "2") });
        parameters.add(new Object[] { createFraction(3, "2"), createFraction(3, "10") });
        parameters.add(new Object[] { createFraction(3, "10"), createFraction(3, "11") });
        parameters.add(new Object[] { createFraction(3, "11"), createFraction(3, "12") });
        parameters.add(new Object[] { createFraction(3, "12"), createFraction(3, "20") });
        parameters.add(new Object[] { createFraction(3, "20"), createFraction(3, "21") });
        parameters.add(new Object[] { createFraction(3, "21"), createFraction(3, "22") });
        parameters.add(new Object[] { createFraction(3, "22"), createFraction(3, "100") });
        parameters.add(new Object[] { createFraction(3, "100"), createFraction(3, "101") });
        parameters.add(new Object[] { createFraction(3, "101"), createFraction(3, "102") });
        parameters.add(new Object[] { createFraction(3, "102"), createFraction(3, "110") });
        parameters.add(new Object[] { createFraction(3, "110"), createFraction(3, "111") });
        parameters.add(new Object[] { createFraction(3, "111"), createFraction(3, "112") });
        parameters.add(new Object[] { createFraction(3, "112"), createFraction(3, "120") });
        parameters.add(new Object[] { createFraction(3, "120"), createFraction(3, "121") });
        parameters.add(new Object[] { createFraction(3, "121"), createFraction(3, "122") });
        parameters.add(new Object[] { createFraction(3, "122"), createFraction(3, "200") });
        parameters.add(new Object[] { createFraction(3, "200"), createFraction(3, "201") });
        parameters.add(new Object[] { createFraction(3, "201"), createFraction(3, "202") });
        parameters.add(new Object[] { createFraction(3, "202"), createFraction(3, "210") });
        parameters.add(new Object[] { createFraction(3, "210"), createFraction(3, "211") });
        parameters.add(new Object[] { createFraction(3, "211"), createFraction(3, "212") });
        parameters.add(new Object[] { createFraction(3, "212"), createFraction(3, "220") });
        parameters.add(new Object[] { createFraction(3, "220"), createFraction(3, "221") });
        parameters.add(new Object[] { createFraction(3, "221"), createFraction(3, "222") });
        parameters.add(new Object[] { createFraction(3, "222"), createFraction(3, "1000") });

        parameters.add(new Object[] { createFraction(4, "0"), createFraction(4, "1") });
        parameters.add(new Object[] { createFraction(4, "1"), createFraction(4, "2") });
        parameters.add(new Object[] { createFraction(4, "2"), createFraction(4, "3") });
        parameters.add(new Object[] { createFraction(4, "3"), createFraction(4, "10") });
        parameters.add(new Object[] { createFraction(4, "10"), createFraction(4, "11") });
        parameters.add(new Object[] { createFraction(4, "11"), createFraction(4, "12") });
        parameters.add(new Object[] { createFraction(4, "12"), createFraction(4, "13") });
        parameters.add(new Object[] { createFraction(4, "13"), createFraction(4, "20") });
        parameters.add(new Object[] { createFraction(4, "20"), createFraction(4, "21") });
        parameters.add(new Object[] { createFraction(4, "21"), createFraction(4, "22") });
        parameters.add(new Object[] { createFraction(4, "22"), createFraction(4, "23") });
        parameters.add(new Object[] { createFraction(4, "23"), createFraction(4, "30") });
        parameters.add(new Object[] { createFraction(4, "30"), createFraction(4, "31") });
        parameters.add(new Object[] { createFraction(4, "31"), createFraction(4, "32") });
        parameters.add(new Object[] { createFraction(4, "32"), createFraction(4, "33") });
        parameters.add(new Object[] { createFraction(4, "33"), createFraction(4, "100") });
        parameters.add(new Object[] { createFraction(4, "100"), createFraction(4, "101") });

        parameters.add(new Object[] { createFraction(2, "10", "10"), createFraction(2, "100", "10") });
        parameters.add(new Object[] { createFraction(2, "1", "10"), createFraction(2, "11", "10") });
        parameters.add(new Object[] { createFraction(2, "-1", "10"), createFraction(2, "1", "10") });
        parameters.add(new Object[] { createFraction(2, "-1", "1", "10"), createFraction(2, "-1", "10") });
        parameters.add(new Object[] { createFraction(2, "-1", "10", "10"), createFraction(2, "-10", "10") });
        parameters.add(new Object[] { createFraction(2, "-100", "10"), createFraction(2, "-10", "10") });

        parameters.add(new Object[] { createFraction(10, "1", "3"), createFraction(10, "4", "3") });
        parameters.add(new Object[] { createFraction(10, "0", "3"), createFraction(10, "1") });
        parameters.add(new Object[] { createFraction(10, "-1", "3"), createFraction(10, "2", "3") });
        parameters.add(new Object[] { createFraction(10, "-2", "3"), createFraction(10, "1", "3") });
        parameters.add(new Object[] { createFraction(10, "-3", "3"), createFraction(10, "0", "3") });
        parameters.add(new Object[] { createFraction(10, "-4", "3"), createFraction(10, "-1", "3") });
        parameters.add(new Object[] { createFraction(10, "-5", "3"), createFraction(10, "-2", "3") });
        parameters.add(new Object[] { createFraction(10, "-6", "3"), createFraction(10, "-3", "3") });
        parameters.add(new Object[] { createFraction(10, "-7", "3"), createFraction(10, "-4", "3") });

        parameters.add(new Object[] { createFraction(10, "-2", "1", "3"), createFraction(10, "-1", "1", "3") });
        parameters.add(new Object[] { createFraction(10, "2", "1", "3"), createFraction(10, "3", "1", "3") });

        return parameters;
    }

}

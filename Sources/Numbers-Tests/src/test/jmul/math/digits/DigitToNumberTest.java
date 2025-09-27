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


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.operations.implementations.DigitToNumberConversion;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;

import jmul.test.classification.UnitTest;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests translating digits to numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class DigitToNumberTest {

    /**
     * A digit.
     */
    private Digit digit;

    /**
     * The expected result.
     */
    private Number expectedResult;

    /**
     * The conversion function.
     */
    private UnaryOperation<Digit, Result<Number>> function;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param digit
     *        a digit
     * @param expectedResult
     *        the expected result (i.e. an integer)
     */
    public DigitToNumberTest(Digit digit, Number expectedResult) {

        super();

        this.digit = digit;
        this.expectedResult = expectedResult;
    }

    /**
     * Prepares the test setup.
     */
    @Before
    public void setUp() {

        function = new DigitToNumberConversion();
    }

    /**
     * Cleans up after a tests.
     */
    @After
    public void tearDown() {

        function = null;
    }

    /**
     * Returns a summary for this test case.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s -> [base:%d] %s ", digit.base(), digit, expectedResult.base(),
                             expectedResult);
    }

    /**
     * Tests the conversion.
     */
    @Test
    public void testConversion() {

        Result<Number> result = function.calculate(digit);
        Number actualResult = result.result();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(2, 0), createNumber(2, "0") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(2, 1), createNumber(2, "1") });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(3, 0), createNumber(3, "0") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(3, 1), createNumber(3, "1") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(3, 2), createNumber(3, "2") });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(4, 0), createNumber(4, "0") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(4, 1), createNumber(4, "1") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(4, 2), createNumber(4, "2") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(4, 3), createNumber(4, "3") });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 0), createNumber(5, "0") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 1), createNumber(5, "1") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 2), createNumber(5, "2") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 3), createNumber(5, "3") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 4), createNumber(5, "4") });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 0), createNumber(6, "0") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 1), createNumber(6, "1") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 2), createNumber(6, "2") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 3), createNumber(6, "3") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 4), createNumber(6, "4") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 5), createNumber(6, "5") });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(7, 0), createNumber(7, "0") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(7, 1), createNumber(7, "1") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(7, 2), createNumber(7, "2") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(7, 3), createNumber(7, "3") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(7, 4), createNumber(7, "4") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(7, 5), createNumber(7, "5") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(7, 6), createNumber(7, "6") });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(8, 0), createNumber(8, "0") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(8, 1), createNumber(8, "1") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(8, 2), createNumber(8, "2") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(8, 3), createNumber(8, "3") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(8, 4), createNumber(8, "4") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(8, 5), createNumber(8, "5") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(8, 6), createNumber(8, "6") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(8, 7), createNumber(8, "7") });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(9, 0), createNumber(9, "0") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(9, 1), createNumber(9, "1") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(9, 2), createNumber(9, "2") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(9, 3), createNumber(9, "3") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(9, 4), createNumber(9, "4") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(9, 5), createNumber(9, "5") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(9, 6), createNumber(9, "6") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(9, 7), createNumber(9, "7") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(9, 8), createNumber(9, "8") });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 0), createNumber(10, "0") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 1), createNumber(10, "1") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 2), createNumber(10, "2") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 3), createNumber(10, "3") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 4), createNumber(10, "4") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 5), createNumber(10, "5") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 6), createNumber(10, "6") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 7), createNumber(10, "7") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 8), createNumber(10, "8") });
        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 9), createNumber(10, "9") });

        return parameters;
    }

}

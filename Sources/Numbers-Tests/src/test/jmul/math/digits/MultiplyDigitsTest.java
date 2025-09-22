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
import jmul.math.operations.implementations.MultiplyDigits;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.ResultWithCarry;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests multiplying digits.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class MultiplyDigitsTest {

    /**
     * A digit.
     */
    private final Digit digit1;

    /**
     * A digit.
     */
    private final Digit digit2;

    /**
     * The expected result.
     */
    private final Digit expectedResult;

    /**
     * The expected carry.
     */
    private final Digit expectedCarry;

    /**
     * The addition function.
     */
    private final BinaryOperation<Digit, ResultWithCarry<Digit>> function;

    /**
     * Creates a test case according to the specified parameters.
     *
     * @param digit1
     *        a digit
     * @param digit2
     *        a digit
     * @param expectedResult
     *        the expected result
     * @param expectedCarry
     *        the expected carry
     */
    public MultiplyDigitsTest(Digit digit1, Digit digit2, Digit expectedResult, Digit expectedCarry) {

        super();

        this.digit1 = digit1;
        this.digit2 = digit2;
        this.expectedResult = expectedResult;
        this.expectedCarry = expectedCarry;
        this.function = new MultiplyDigits();
    }

    /**
     * Returns a summary for this test.
     *
     * @return a summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s * %s -> %s with carry %s", digit1.base(), digit1, digit2, expectedResult,
                             expectedCarry);
    }

    /**
     * Tests multiplying two digits and verifies the result.
     */
    @Test
    public void testAddingDigits() {

        ResultWithCarry<Digit> result = function.calculate(digit1, digit2);
        Digit sum = result.result();
        Digit carry = result.carry();

        assertEquals(toString() + " check result", expectedResult, sum);
        assertEquals(toString() + " check carry", expectedCarry, carry);
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

            for (int a = 0; a < base; a++) {
                for (int b = 0; b < base; b++) {

                    int product = a * b;
                    int result = product % base;
                    int carry = product / base;

                    parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(base, a),
                                                  PositionalNumeralSystems.ordinalToDigit(base, b),
                                                  PositionalNumeralSystems.ordinalToDigit(base, result),
                                                  PositionalNumeralSystems.ordinalToDigit(base, carry) });
                }
            }
        }

        return parameters;
    }

}

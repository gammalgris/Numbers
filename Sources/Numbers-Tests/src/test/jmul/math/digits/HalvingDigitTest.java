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
import jmul.math.functions.implementations.HalvingDigit;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.ResultWithRemainder;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests halving digits.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class HalvingDigitTest {

    /**
     * A digit.
     */
    private final Digit digit;

    /**
     * A carry.
     */
    private final Digit carry;

    /**
     * The expected result.
     */
    private final Digit expectedResult;

    /**
     * The expected remainder.
     */
    private final Digit expectedRemainder;

    /**
     * The function to halve digits.
     */
    private BinaryOperation<Digit, ResultWithRemainder<Digit>> halveDigitFunction;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param digit
     *        a digit
     * @param carry
     *        a digit
     * @param expectedResult
     *        the expected result
     * @param expectedRemainder
     *        the expected remainder
     */
    public HalvingDigitTest(Digit digit, Digit carry, Digit expectedResult, Digit expectedRemainder) {

        super();

        this.digit = digit;
        this.carry = carry;
        this.expectedResult = expectedResult;
        this.expectedRemainder = expectedRemainder;
        halveDigitFunction = new HalvingDigit();
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("[base:%d] %s halved with carry %s -> result %s with remainder %s", digit.base(), digit,
                             carry, expectedResult, expectedRemainder);
    }

    /**
     * Halves a digit and checks the result.
     */
    @Test
    public void testHalvingDigit() {

        ResultWithRemainder<Digit> result = halveDigitFunction.calculate(digit, carry);

        assertEquals(toString(), expectedResult, result.result());
        assertEquals(toString(), expectedRemainder, result.remainder());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 4),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 2),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 3),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 2),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0) });


        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 4),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 4),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 3),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 4),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 2),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 3),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 3),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 2),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1) });


        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 9),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 4),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 8),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 4),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 7),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 3),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 6),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 3),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 5),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 2),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 4),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 2),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 3),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 2),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });


        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 9),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 9),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 8),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 9),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 7),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 8),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 6),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 8),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 5),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 7),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 4),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 7),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 3),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 6),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 2),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 6),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 5),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(10, 0),
                                      PositionalNumeralSystems.ordinalToDigit(10, 1),
                                      PositionalNumeralSystems.ordinalToDigit(10, 5),
                                      PositionalNumeralSystems.ordinalToDigit(10, 0) });

        return parameters;
    }

}

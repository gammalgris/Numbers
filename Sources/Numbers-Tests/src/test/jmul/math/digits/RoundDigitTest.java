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
import jmul.math.operations.implementations.RoundDigitHalfUpFunction;
import jmul.math.operations.ResultWithCarry;
import jmul.math.operations.UnaryOperation;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests rounding digits.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RoundDigitTest {

    /**
     * A digit.
     */
    private final Digit digit;

    /**
     * The expected result.
     */
    private final Digit expectedResult;

    /**
     * The expected result.
     */
    private final Digit expectedCarry;

    /**
     * A eference to a rounding function.
     */
    private final UnaryOperation<Digit, ResultWithCarry<Digit>> roundingFunction;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param digit
     *        a digit
     * @param expectedResult
     *        the expected result
     */
    public RoundDigitTest(Digit digit, Digit expectedResult, Digit expectedCarry) {

        super();

        this.digit = digit;
        this.expectedResult = expectedResult;
        this.expectedCarry = expectedCarry;
        this.roundingFunction = new RoundDigitHalfUpFunction();
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("(%d) %s -> (%d) %s", digit.base(), digit, expectedResult.base(), expectedResult);
    }

    /**
     * Rounds a digit and checks the result.
     */
    @Test
    public void testRoundingDigit() {

        ResultWithCarry<Digit> result = roundingFunction.calculate(digit);
        Digit actualResult = result.result();
        Digit actualCarry = result.carry();

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
        assertEquals(toString(), expectedCarry, actualCarry);
        assertEquals(toString(), expectedCarry.toString(), actualCarry.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(2, 0),
                                      PositionalNumeralSystems.ordinalToDigit(2, 0),
                                      PositionalNumeralSystems.ordinalToDigit(2, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(2, 1),
                                      PositionalNumeralSystems.ordinalToDigit(2, 0),
                                      PositionalNumeralSystems.ordinalToDigit(2, 1) });


        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(3, 0),
                                      PositionalNumeralSystems.ordinalToDigit(3, 0),
                                      PositionalNumeralSystems.ordinalToDigit(3, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(3, 1),
                                      PositionalNumeralSystems.ordinalToDigit(3, 0),
                                      PositionalNumeralSystems.ordinalToDigit(3, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(3, 2),
                                      PositionalNumeralSystems.ordinalToDigit(3, 0),
                                      PositionalNumeralSystems.ordinalToDigit(3, 1) });


        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(4, 0),
                                      PositionalNumeralSystems.ordinalToDigit(4, 0),
                                      PositionalNumeralSystems.ordinalToDigit(4, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(4, 1),
                                      PositionalNumeralSystems.ordinalToDigit(4, 0),
                                      PositionalNumeralSystems.ordinalToDigit(4, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(4, 2),
                                      PositionalNumeralSystems.ordinalToDigit(4, 0),
                                      PositionalNumeralSystems.ordinalToDigit(4, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(4, 3),
                                      PositionalNumeralSystems.ordinalToDigit(4, 0),
                                      PositionalNumeralSystems.ordinalToDigit(4, 1) });


        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 1),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 2),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 3),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(5, 4),
                                      PositionalNumeralSystems.ordinalToDigit(5, 0),
                                      PositionalNumeralSystems.ordinalToDigit(5, 1) });


        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 0),
                                      PositionalNumeralSystems.ordinalToDigit(6, 0),
                                      PositionalNumeralSystems.ordinalToDigit(6, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 1),
                                      PositionalNumeralSystems.ordinalToDigit(6, 0),
                                      PositionalNumeralSystems.ordinalToDigit(6, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 2),
                                      PositionalNumeralSystems.ordinalToDigit(6, 0),
                                      PositionalNumeralSystems.ordinalToDigit(6, 0) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 3),
                                      PositionalNumeralSystems.ordinalToDigit(6, 0),
                                      PositionalNumeralSystems.ordinalToDigit(6, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 4),
                                      PositionalNumeralSystems.ordinalToDigit(6, 0),
                                      PositionalNumeralSystems.ordinalToDigit(6, 1) });

        parameters.add(new Object[] { PositionalNumeralSystems.ordinalToDigit(6, 5),
                                      PositionalNumeralSystems.ordinalToDigit(6, 0),
                                      PositionalNumeralSystems.ordinalToDigit(6, 1) });

        return parameters;
    }

}

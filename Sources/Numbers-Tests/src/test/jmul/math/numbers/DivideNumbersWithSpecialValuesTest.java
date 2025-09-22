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


import jmul.math.operations.implementations.DivisionOfNumbersBySubtractionReturnResultAndRemainder;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.NoResultButLimitException;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.ResultWithRemainder;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * Tests the division with special parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class DivideNumbersWithSpecialValuesTest {

    /**
     * Tests division by infinity.
     */
    @Test(expected = NoResultButLimitException.class)
    public void divideByInfinity() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createNumber(10, "10");
        Number operand2 = createInfinity(10);

        try {

            function.calculate(operand1, operand2);

        } catch (NoResultButLimitException e) {

            Number expectedResult = createNumber(10, "0");
            Number actualResult = e.limit();

            assertEquals(expectedResult, actualResult);

            throw e;
        }
    }

    /**
     * Tests dividing infinity by infinity.
     */
    @Test(expected = NoResultButLimitException.class)
    public void divideInfinityByInfinity() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createInfinity(10);
        Number operand2 = createInfinity(10);

        try {

            function.calculate(operand1, operand2);

        } catch (NoResultButLimitException e) {

            Number expectedResult = createNumber(10, "1");
            Number actualResult = e.limit();

            assertEquals(expectedResult, actualResult);

            throw e;
        }
    }

    /**
     * Tests dividing by zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void divideByZero() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createNumber(10, "10");
        Number operand2 = createNumber(10, "0");

        function.calculate(operand1, operand2);
    }

    /**
     * Tests dividing infinity by a number.
     */
    @Test
    public void divideInfinityByNumber() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createInfinity(10);
        Number operand2 = createNumber(10, "10");

        ResultWithRemainder<Number> result = function.calculate(operand1, operand2);

        Number expectedResult = createInfinity(10);
        Number actualResult = result.result();
        assertEquals(expectedResult, actualResult);

        Number expectedRemainder = createNumber(10, "0");
        Number actualRemainder = result.remainder();
        assertEquals(expectedRemainder, actualRemainder);
    }

    /**
     * Tests dividing zero by a number.
     */
    @Test
    public void divideZeroyByNumber() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createNumber(10, "0");
        Number operand2 = createNumber(10, "10");

        ResultWithRemainder<Number> result = function.calculate(operand1, operand2);

        Number expectedResult = createNumber(10, "0");
        Number actualResult = result.result();
        assertEquals(expectedResult, actualResult);

        Number expectedRemainder = createNumber(10, "0");
        Number actualRemainder = result.remainder();
        assertEquals(expectedRemainder, actualRemainder);
    }

    /**
     * Tests division by infinity.
     */
    @Test(expected = NoResultButLimitException.class)
    public void divideZeroByZero() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createNumber(10, "0");
        Number operand2 = createNumber(10, "0");

        try {

            function.calculate(operand1, operand2);

        } catch (NoResultButLimitException e) {

            Number expectedResult = createNumber(10, "1");
            Number actualResult = e.limit();

            assertEquals(expectedResult, actualResult);

            throw e;
        }
    }


}

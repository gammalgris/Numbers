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
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.numbers.exceptions.UndefinedOperationException;
import jmul.math.operations.BinaryOperation;
import jmul.math.operations.Result;
import jmul.math.operations.ResultWithRemainder;

import jmul.math.operations.TernaryOperation;
import jmul.math.operations.implementations.DivisionBySubtraction;

import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * Tests the division with illegal parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class DivideNumbersWithIllegalArgumentsTest {

    /**
     * Tests division where the first parameter is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void divideNullByNumber() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = null;
        Number operand2 = createNumber(10, "1");

        function.calculate(operand1, operand2);
    }

    /**
     * Tests division where the second parameter is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void divideByNull() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createNumber(10, "1");
        Number operand2 = null;

        function.calculate(operand1, operand2);
    }

    /**
     * Tests division where the first parameter is not an integer.
     */
    @Test(expected = IllegalArgumentException.class)
    public void divideNonInteger() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createNumber(10, "1.1");
        Number operand2 = createNumber(10, "1");

        function.calculate(operand1, operand2);
    }

    /**
     * Tests division where the second parameter is not an integer.
     */
    @Test(expected = IllegalArgumentException.class)
    public void divideByNonInteger() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createNumber(10, "1");
        Number operand2 = createNumber(10, "1.1");

        function.calculate(operand1, operand2);
    }

    /**
     * Tests division where the second parameter is zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void divideByZero() {

        BinaryOperation<Number, ResultWithRemainder<Number>> function =
            new DivisionOfNumbersBySubtractionReturnResultAndRemainder();

        Number operand1 = createNumber(10, "1");
        Number operand2 = createNumber(10, "0");

        function.calculate(operand1, operand2);
    }

    /**
     * Tests division where the second parameter is zero.
     */
    @Test(expected = UndefinedOperationException.class)
    public void divideByZeroVarian2() {

        TernaryOperation<Number, Result<Number>> function = new DivisionBySubtraction();

        Number precision = createNumber(10, "10");
        Number operand1 = createNumber(10, "1");
        Number operand2 = createNumber(10, "0");

        function.calculate(operand1, operand2, precision);
    }

}

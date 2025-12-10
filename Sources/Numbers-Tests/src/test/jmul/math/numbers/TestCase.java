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


import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.parseInteger;


/**
 * This class represents a test case.
 *
 * @author Kristian Kutin
 */
class TestCase {

    /**
     * An operand.
     */
    public final Number operand1;

    /**
     * An operand.
     */
    public final Number operand2;

    /**
     * The expected result.
     */
    public final Number expectedResult;

    /**
     * Creates a test case accordign to the specified parameters.
     *
     * @param operand1
     * an operand (i.e. an int value)
     * @param operand2
     * an operand (i.e. an int value)
     * @param expectedResult
     * an operand (i.e. an int value)
     */
    public TestCase(int operand1, int operand2, int expectedResult) {

        this(parseInteger(operand1), parseInteger(operand2), parseInteger(expectedResult));
    }

    /**
     * Creates a test case accordign to the specified parameters.
     *
     * @param operand1
     * an operand (i.e. number)
     * @param operand2
     * an operand (i.e. number)
     * @param expectedResult
     * an operand (i.e. number)
     */
    public TestCase(Number operand1, Number operand2, Number expectedResult) {

        super();

        this.operand1 = operand1;
        this.operand2 = operand2;
        this.expectedResult = expectedResult;
    }

}

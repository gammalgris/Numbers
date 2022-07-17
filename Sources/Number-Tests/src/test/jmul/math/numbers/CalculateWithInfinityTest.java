/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2022  Kristian Kutin
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

import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests calculating with infinity.
 *
 * //TODO add tests for different binary functions (i.e. subtraction, etc.)
 * //TODO add tests for different unary functions (i.e. faculty, etc.)
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class CalculateWithInfinityTest {

    /**
     * The first operand.
     */
    private final Number firstOperand;

    /**
     * The second operand.
     */
    private final Number secondOperand;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param firstOperand
     *        an oeprand for the calculation
     * @param secondOperand
     *        an operand for the calculation
     * @param expectedResult
     *        the expected result of the calculation
     */
    public CalculateWithInfinityTest(Number firstOperand, Number secondOperand, Number expectedResult) {

        super();

        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.expectedResult = expectedResult;
    }

    @Test
    public void testCalculation() {

        Number actualResult = firstOperand.add(secondOperand);

        String message = String.format("%s + %s", firstOperand.toString(), secondOperand.toString());
        assertEquals(message, expectedResult, actualResult);
    }

    /**
     * Returns a matrix of test data.
     *
     * @return a matrix of test data
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = 2; base < 64; base++) {

            parameters.add(new Object[] { new NumberImpl(base, "1"), new NumberImpl(base), new NumberImpl(base) });
            parameters.add(new Object[] { new NumberImpl(base, "-1"), new NumberImpl(base), new NumberImpl(base) });
            parameters.add(new Object[] { new NumberImpl(base, "1"), new NumberImpl(base, Signs.NEGATIVE),
                                          new NumberImpl(base, Signs.NEGATIVE) });
            parameters.add(new Object[] { new NumberImpl(base, "-1"), new NumberImpl(base, Signs.NEGATIVE),
                                          new NumberImpl(base, Signs.NEGATIVE) });
        }

        return parameters;
    }

}

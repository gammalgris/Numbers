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
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite checks the results of equivalent additions and multiplications.<br>
 * <br>
 * <i>Note:<br>
 * This cross check is a bit tricky as it will sometimes fail with numbers of an odd number base. This is due to not
 * </i>
 *
 * @author Kristian Kutin
 */
@UnitTest
public class CrossVerificationTest {

    /**
     * Tests an addition and the corresponding multiplication and compares the results.
     */
    @Test
    public void testAdditionAndMultiplication() {

        Number operand1 = createNumber(3, "0.220110201");
        Number operand2 = createNumber(3, "22");
        Number expectedResult = createNumber(3, "21.020202122");

        Number actualResult1 = createNumber(3, "0");
        Number counter = operand2;
        while (!counter.isZero()) {

            actualResult1 = actualResult1.add(operand1);
            counter = counter.dec();
        }

        Number actualResult2 = operand1.multiply(operand2);

        assertEquals("check #1 (addition)", expectedResult, actualResult1);
        assertEquals("check #2 (multiplication)", expectedResult, actualResult2);
    }

    /**
     * Tests an addition and the corresponding multiplication and compares the results.
     */
    @Test
    public void testAdditionAndMultiplication2() {

        Number operand1 = createNumber(3, "2");
        Number operand2 = createNumber(3, "22");
        Number expectedResult = createNumber(3, "121");

        Number actualResult1 = createNumber(3, "0");
        Number counter = operand2;
        while (!counter.isZero()) {

            actualResult1 = actualResult1.add(operand1);
            counter = counter.dec();
        }

        Number actualResult2 = operand1.multiply(operand2);

        assertEquals("check #1 (addition)", expectedResult, actualResult1);
        assertEquals("check #2 (multiplication)", expectedResult, actualResult2);
    }

    /**
     * Tests an addition and the corresponding multiplication and compares the results.
     */
    @Test
    public void testAdditionAndMultiplication3() {

        Number operand1 = createNumber(3, "2");
        Number operand2 = createNumber(3, "101");
        Number expectedResult = createNumber(3, "202");

        Number actualResult1 = createNumber(3, "0");
        Number counter = operand2;
        int loops = 0;
        do {

            actualResult1 = actualResult1.add(operand1);
            counter = counter.dec();
            loops++;

        } while (!counter.isZero());

        Number actualResult2 = operand1.multiply(operand2);

        assertEquals("check #1 (addition)", expectedResult, actualResult1);
        assertEquals("check #2 (multiplication)", expectedResult, actualResult2);
    }

    /**
     * Tests an addition and the corresponding multiplication and compares the results.
     */
    @Test
    public void testAdditionAndMultiplication4() {

        Number operand1 = createNumber(3, "2");
        Number operand2 = createNumber(3, "101");
        Number expectedResult = createNumber(3, "202");

        Number actualResult1 = createNumber(3, "0");
        Number counter = operand2;
        while (!counter.isZero()) {

            actualResult1 = actualResult1.add(operand1);
            counter = counter.dec();
        }

        Number actualResult2 = operand1.multiply(operand2);

        assertEquals("check #1 (addition)", expectedResult, actualResult1);
        assertEquals("check #2 (multiplication)", expectedResult, actualResult2);
    }

    /**
     * Tests an addition and the corresponding multiplication and compares the results.
     */
    @Test
    public void testAdditionAndMultiplication5() {

        Number operand1 = createNumber(10, "2");
        Number operand2 = createNumber(10, "100");
        Number expectedResult = createNumber(10, "200");

        Number actualResult1 = createNumber(10, "0");
        Number counter = operand2;
        while (!counter.isZero()) {

            actualResult1 = actualResult1.add(operand1);
            counter = counter.dec();
        }

        Number actualResult2 = operand1.multiply(operand2);

        assertEquals("check #1 (addition)", expectedResult, actualResult1);
        assertEquals("check #2 (multiplication)", expectedResult, actualResult2);
    }

    /**
     * Tests an addition and the corresponding multiplication and compares the results.
     */
    @Test
    public void testAdditionAndMultiplication6() {

        Number operand1 = createNumber(10, "0.02");
        Number operand2 = createNumber(10, "100");
        Number expectedResult = createNumber(10, "2");

        Number actualResult1 = createNumber(10, "0");
        Number counter = operand2;
        while (!counter.isZero()) {

            actualResult1 = actualResult1.add(operand1);
            counter = counter.dec();
        }

        Number actualResult2 = operand1.multiply(operand2);

        assertEquals("check #1 (addition)", expectedResult, actualResult1);
        assertEquals("check #2 (multiplication)", expectedResult, actualResult2);
    }

}

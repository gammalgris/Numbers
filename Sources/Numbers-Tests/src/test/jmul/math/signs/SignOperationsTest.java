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

package test.jmul.math.signs;


import java.util.ArrayList;
import java.util.List;

import jmul.math.signs.Sign;
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * Checks the operations for signs.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SignOperationsTest {

    /**
     * Tests the negate operation for signs.
     */
    @Test
    public void testNegate() {

        Sign[] operands = { Signs.POSITIVE, Signs.NEGATIVE };
        Sign[] expectedResults = { Signs.NEGATIVE, Signs.POSITIVE };

        List<String> errors = new ArrayList<>();

        int maxIndex = operands.length - 1;
        for (int a = 0; a < maxIndex; a++) {

            Sign operand = operands[a];
            Sign expectedResult = expectedResults[a];
            Sign actualResult = Signs.negate(operand);

            String message = String.format("negate(%s)=%s but is %s", operand, expectedResult, actualResult);
            if (expectedResult != actualResult) {

                errors.add(message);
            }
        }

        assertTrue(errors.toString(), errors.isEmpty());
    }

    /**
     * Tests the logical and operation for signs (i.e. POSITIVE represents true and NEGATIVE represents
     * false).
     */
    @Test
    public void testAnd() {

        Sign[] operands1 = { Signs.POSITIVE, Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE };
        Sign[] operands2 = { Signs.POSITIVE, Signs.NEGATIVE, Signs.POSITIVE, Signs.NEGATIVE };
        Sign[] expectedResults = { Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE, Signs.NEGATIVE };

        List<String> errors = new ArrayList<>();

        int maxIndex = operands1.length - 1;
        for (int a = 0; a < maxIndex; a++) {

            Sign operand1 = operands1[a];
            Sign operand2 = operands2[a];
            Sign expectedResult = expectedResults[a];
            Sign actualResult = Signs.and(operand1, operand2);

            String message =
                String.format("and(%s, %s)=%s but is %s", operand1, operand2, expectedResult, actualResult);
            if (expectedResult != actualResult) {

                errors.add(message);
            }
        }

        assertTrue(errors.toString(), errors.isEmpty());
    }

    /**
     * Tests the logical or operation for signs (i.e. POSITIVE represents true and NEGATIVE represents
     * false).
     */
    @Test
    public void testOr() {

        Sign[] operands1 = { Signs.POSITIVE, Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE };
        Sign[] operands2 = { Signs.POSITIVE, Signs.NEGATIVE, Signs.POSITIVE, Signs.NEGATIVE };
        Sign[] expectedResults = { Signs.POSITIVE, Signs.POSITIVE, Signs.POSITIVE, Signs.NEGATIVE };

        List<String> errors = new ArrayList<>();

        int maxIndex = operands1.length - 1;
        for (int a = 0; a < maxIndex; a++) {

            Sign operand1 = operands1[a];
            Sign operand2 = operands2[a];
            Sign expectedResult = expectedResults[a];
            Sign actualResult = Signs.or(operand1, operand2);

            String message = String.format("or(%s, %s)=%s but is %s", operand1, operand2, expectedResult, actualResult);
            if (expectedResult != actualResult) {

                errors.add(message);
            }
        }

        assertTrue(errors.toString(), errors.isEmpty());
    }

    /**
     * Tests the logical xor operation for signs (i.e. POSITIVE represents true and NEGATIVE represents
     * false).
     */
    @Test
    public void testXor() {

        Sign[] operands1 = { Signs.POSITIVE, Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE };
        Sign[] operands2 = { Signs.POSITIVE, Signs.NEGATIVE, Signs.POSITIVE, Signs.NEGATIVE };
        Sign[] expectedResults = { Signs.NEGATIVE, Signs.POSITIVE, Signs.POSITIVE, Signs.NEGATIVE };

        List<String> errors = new ArrayList<>();

        int maxIndex = operands1.length - 1;
        for (int a = 0; a < maxIndex; a++) {

            Sign operand1 = operands1[a];
            Sign operand2 = operands2[a];
            Sign expectedResult = expectedResults[a];
            Sign actualResult = Signs.xor(operand1, operand2);

            String message =
                String.format("xor(%s, %s)=%s but is %s", operand1, operand2, expectedResult, actualResult);
            if (expectedResult != actualResult) {

                errors.add(message);
            }
        }

        assertTrue(errors.toString(), errors.isEmpty());
    }

    /**
     * Tests the multiplication operation for signs.
     */
    @Test
    public void testMultiplication() {

        Sign[] operands1 = { Signs.POSITIVE, Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE };
        Sign[] operands2 = { Signs.POSITIVE, Signs.NEGATIVE, Signs.POSITIVE, Signs.NEGATIVE };
        Sign[] expectedResults = { Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE, Signs.POSITIVE };

        List<String> errors = new ArrayList<>();

        int maxIndex = operands1.length - 1;
        for (int a = 0; a < maxIndex; a++) {

            Sign operand1 = operands1[a];
            Sign operand2 = operands2[a];
            Sign expectedResult = expectedResults[a];
            Sign actualResult = Signs.multiply(operand1, operand2);

            String message =
                String.format("multiply(%s, %s)=%s but is %s", operand1, operand2, expectedResult, actualResult);
            if (expectedResult != actualResult) {

                errors.add(message);
            }
        }

        assertTrue(errors.toString(), errors.isEmpty());
    }

    /**
     * Tests the division operation for signs (i.e. result).
     */
    @Test
    public void testDivisionAndResultSign() {

        Sign[] operands1 = { Signs.POSITIVE, Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE };
        Sign[] operands2 = { Signs.POSITIVE, Signs.NEGATIVE, Signs.POSITIVE, Signs.NEGATIVE };
        Sign[] expectedResults = { Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE, Signs.POSITIVE };

        List<String> errors = new ArrayList<>();

        int maxIndex = operands1.length - 1;
        for (int a = 0; a < maxIndex; a++) {

            Sign operand1 = operands1[a];
            Sign operand2 = operands2[a];
            Sign expectedResult = expectedResults[a];
            Sign actualResult = Signs.divideAndDetermineResultSign(operand1, operand2);

            String message =
                String.format("divide(%s, %s)=%s but is %s", operand1, operand2, expectedResult, actualResult);
            if (expectedResult != actualResult) {

                errors.add(message);
            }
        }

        assertTrue(errors.toString(), errors.isEmpty());
    }

    /**
     * Tests the division operation for signs (i.e. remainder).
     */
    @Test
    public void testDivisionAndRemainderSign() {

        Sign[] operands1 = { Signs.POSITIVE, Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE };
        Sign[] operands2 = { Signs.POSITIVE, Signs.NEGATIVE, Signs.POSITIVE, Signs.NEGATIVE };
        Sign[] expectedResults = { Signs.POSITIVE, Signs.POSITIVE, Signs.NEGATIVE, Signs.NEGATIVE };

        List<String> errors = new ArrayList<>();

        int maxIndex = operands1.length - 1;
        for (int a = 0; a < maxIndex; a++) {

            Sign operand1 = operands1[a];
            Sign operand2 = operands2[a];
            Sign expectedResult = expectedResults[a];
            Sign actualResult = Signs.divideAndDetermineRemainderSign(operand1, operand2);

            String message =
                String.format("divide(%s, %s)=%s but is %s", operand1, operand2, expectedResult, actualResult);
            if (expectedResult != actualResult) {

                errors.add(message);
            }
        }

        assertTrue(errors.toString(), errors.isEmpty());
    }

}

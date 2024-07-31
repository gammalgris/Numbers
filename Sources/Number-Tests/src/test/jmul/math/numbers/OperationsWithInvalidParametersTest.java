/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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


import jmul.math.Math;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;
import jmul.math.numbers.exceptions.UndefinedOperationException;

import jmul.test.classification.UnitTest;

import org.junit.Test;


/**
 * This test suite tests various operations with illegal arguments.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class OperationsWithInvalidParametersTest {

    /**
     * Tests adding two operands where one operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNumberAndNull() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        n1.add(n2);
    }

    /**
     * Tests adding two operands where one operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNumberAndNullVariant2() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        Math.add(n1, n2);
    }

    /**
     * Tests adding two operands where one operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullAndNumber() {

        Number n1 = null;
        Number n2 = new NumberImpl("1");

        Math.add(n1, n2);
    }

    /**
     * Tests negating an operand where the operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegateNull() {

        Number n1 = null;

        Math.negate(n1);
    }

    /**
     * Tests calculating the complement of an operand where the operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testComplementNull() {

        Number n1 = null;

        Math.complement(n1);
    }

    /**
     * Tests halving an operand where the operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testHalvingNull() {

        Number n1 = null;

        Math.halving(n1);
    }

    /**
     * Tests doubling an operand where the operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDoublingNull() {

        Number n1 = null;

        Math.doubling(n1);
    }

    /**
     * Tests subtracting two operands where one operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSubtractNumberAndNull() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        n1.subtract(n2);
    }

    /**
     * Tests subtracting two operands where one operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSubtractAddNumberAndNullVariant2() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        Math.subtract(n1, n2);
    }

    /**
     * Tests subtracting two operands where one operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSubtractNullAndNumber() {

        Number n1 = null;
        Number n2 = new NumberImpl("1");

        Math.subtract(n1, n2);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = UndefinedOperationException.class)
    public void shiftOneLeftByFraction() {

        Number n = new NumberImpl("1");
        Number shifts = new NumberImpl("1.1");

        n.shiftLeft(shifts);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shiftNullLeftByOne() {

        Number n = null;
        Number shifts = new NumberImpl("1");

        Math.shiftLeft(n, shifts);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shiftOneLeftByNull() {

        Number n = new NumberImpl("1");
        Number shifts = null;

        n.shiftLeft(shifts);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shiftOneLeftByNullVariant2() {

        Number n = new NumberImpl("1");
        Number shifts = null;

        Math.shiftLeft(n, shifts);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = UndefinedOperationException.class)
    public void shiftOneRightByFraction() {

        Number n = new NumberImpl("1");
        Number shifts = new NumberImpl("1.1");

        n.shiftRight(shifts);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shiftNullRightByOne() {

        Number n = null;
        Number shifts = new NumberImpl("1");

        Math.shiftRight(n, shifts);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shiftOneRightByNull() {

        Number n = new NumberImpl("1");
        Number shifts = null;

        n.shiftRight(shifts);
    }

    /**
     * Tests shifting the decimal point by providing illegal arguments.
     */
    @Test(expected = IllegalArgumentException.class)
    public void shiftOneRightByNullVariant2() {

        Number n = new NumberImpl("1");
        Number shifts = null;

        Math.shiftRight(n, shifts);
    }

    /**
     * Tests trunkating an operand where the operand is <code>null</code>
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTruncateNull() {

        Number n = null;

        Math.truncate(n);
    }

    /**
     * Tests comapring two operands where one operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCompareNumberAndNull() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        n1.compareTo(n2);
    }

    /**
     * Tests comapring two operands where one operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCompareNumberAndNullVariant2() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        Math.compare(n1, n2);
    }

    /**
     * Tests comapring two operands where one operand is <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCompareNullAndNumber() {

        Number n1 = null;
        Number n2 = new NumberImpl("1");

        Math.compare(n1, n2);
    }

    /**
     * Tests comparing two operands where one operand is zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMaxNumberAndNull() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        n1.max(n2);
    }

    /**
     * Tests comparing two operands where one operand is zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMaxNumberAndNullVariant2() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        Math.max(n1, n2);
    }

    /**
     * Tests comparing two operands where one operand is zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMaxNullAndNumber() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        Math.max(n1, n2);
    }

    /**
     * Tests comparing two operands where one operand is zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMinNumberAndNull() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        n1.min(n2);
    }

    /**
     * Tests comparing two operands where one operand is zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMinNumberAndNullVariant2() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        Math.min(n1, n2);
    }

    /**
     * Tests comparing two operands where one operand is zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMinNullAndNumber() {

        Number n1 = new NumberImpl("1");
        Number n2 = null;

        Math.min(n1, n2);
    }

    /**
     * Tests incrementing <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIncNull() {

        Number n = null;

        Math.inc(n);
    }

    /**
     * Tests decrementing <code>null</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDecNull() {

        Number n = null;

        Math.dec(n);
    }

    /**
     * Tests checking if <code>null</code> is even.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEvenNull() {

        Number n = null;

        Math.isEven(n);
    }

    /**
     * Tests checking if <code>null</code> is odd.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOddNull() {

        Number n = null;

        Math.isOdd(n);
    }

    /**
     * Tests checking if a fraction is even.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEvenFraction() {

        Number n = new NumberImpl(10, "1.1");

        Math.isEven(n);
    }

    /**
     * Tests checking if a fraction is odd.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOddFraction() {

        Number n = new NumberImpl(10, "1.1");

        Math.isOdd(n);
    }

}

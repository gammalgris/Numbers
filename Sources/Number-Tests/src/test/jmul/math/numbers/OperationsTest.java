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


import jmul.math.numbers.RealDecimalNumber;
import jmul.math.numbers.builders.NegationOperation;
import jmul.math.numbers.operations.NegationOperationImpl;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * Thois test suite tests various operations.
 *
 * @author Kristian Kutin
 */
public class OperationsTest {

    /**
     * Tests the negation of a number (negative to positive).
     */
    @Test
    public void testNegationNegativeToPositive() {

        NegationOperation<RealDecimalNumber> negator = new NegationOperationImpl<>();

        RealDecimalNumber n = new RealDecimalNumber(-1);
        RealDecimalNumber m = new RealDecimalNumber(1);
        RealDecimalNumber o = negator.negate(n);

        String message = String.format("The negation failed (%s -> %s)!", n.toString(), m.toString());
        assertEquals(message, m, o);
    }

    /**
     * Tests the negation of zero (negative to positive).
     */
    @Test
    public void testNegationNegativeToPositiveZero() {

        NegationOperation<RealDecimalNumber> negator = new NegationOperationImpl<>();

        RealDecimalNumber n = new RealDecimalNumber(-0);
        RealDecimalNumber m = new RealDecimalNumber(0);
        RealDecimalNumber o = negator.negate(n);

        String message = String.format("The negation failed (%s -> %s)!", n.toString(), m.toString());
        assertEquals(message, m, o);
    }

    /**
     * Tests the negation of infinity (negative to positive).
     */
    @Test
    public void testNegationNegativeToPositiveInfinity() {

        NegationOperation<RealDecimalNumber> negator = new NegationOperationImpl<>();

        RealDecimalNumber n = RealDecimalNumber.NEGATIVE_INFINITY;
        RealDecimalNumber m = RealDecimalNumber.POSITIVE_INFINITY;
        RealDecimalNumber o = negator.negate(n);

        String message = String.format("The negation failed (%s -> %s)!", n.toString(), m.toString());
        assertEquals(message, m, o);
    }

    /**
     * Tests the negation of a number (positive to negative).
     */
    @Test
    public void testNegationPositiveToNegative() {

        NegationOperation<RealDecimalNumber> negator = new NegationOperationImpl<>();

        RealDecimalNumber n = new RealDecimalNumber(1);
        RealDecimalNumber m = new RealDecimalNumber(-1);
        RealDecimalNumber o = negator.negate(n);

        String message = String.format("The negation failed (%s -> %s)!", n.toString(), m.toString());
        assertEquals(message, m, o);
    }

    /**
     * Tests the negation of zero (positive to negative).
     */
    @Test
    public void testNegationPositiveToNegativeZero() {

        NegationOperation<RealDecimalNumber> negator = new NegationOperationImpl<>();

        RealDecimalNumber n = new RealDecimalNumber(0);
        RealDecimalNumber m = new RealDecimalNumber(-0);
        RealDecimalNumber o = negator.negate(n);

        String message = String.format("The negation failed (%s -> %s)!", n.toString(), m.toString());
        assertEquals(message, m, o);
    }

    /**
     * Tests the negation of infinity (positive to negative).
     */
    @Test
    public void testNegationPositiveToNegativeInfinity() {

        NegationOperation<RealDecimalNumber> negator = new NegationOperationImpl<>();

        RealDecimalNumber n = RealDecimalNumber.POSITIVE_INFINITY;
        RealDecimalNumber m = RealDecimalNumber.NEGATIVE_INFINITY;
        RealDecimalNumber o = negator.negate(n);

        String message = String.format("The negation failed (%s -> %s)!", n.toString(), m.toString());
        assertEquals(message, m, o);
    }

}

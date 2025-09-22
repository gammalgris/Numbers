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

package test.jmul.math.functions;


import jmul.math.functions.Function;
import jmul.math.functions.FunctionHelper;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;


/**
 * This test suite tests random function cration.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class RandomFunctionTest {

    /**
     * A number base.
     */
    private static final int DEFAULT_NUMBER_BASE;

    /**
     * ONE
     */
    private static final Number ONE;

    /*
     * The static initializer.
     */
    static {

        DEFAULT_NUMBER_BASE = 10;

        ONE = createNumber(DEFAULT_NUMBER_BASE, "1");
    }

    /**
     * Tests creating a random monomial function.
     */
    @Test
    public void testCreateRandomMonomialFunction() {

        Function f = FunctionHelper.createRandomMonomialFunction(DEFAULT_NUMBER_BASE);
        assertNotNull("formula", f.toString());
        assertNotNull("formula", f.toFunctionNotation());

        Number result = f.calculate(ONE);

        assertNotNull("function values", result);
    }

    /**
     * Tests creating a random polynomial function.
     */
    @Test
    public void testCreateRandomPolynomialFunction() {

        Function f = FunctionHelper.createRandomPolynomialFunction(DEFAULT_NUMBER_BASE);
        assertNotNull("formula", f.toString());
        assertNotNull("formula", f.toFunctionNotation());

        Number result = f.calculate(ONE);

        assertNotNull("function values", result);
    }

    /**
     * Tests creating a random partial function.
     */
    @Test
    public void testCreateRandomPartialFunction() {

        Function f = FunctionHelper.createRandomPartialFunction(DEFAULT_NUMBER_BASE);
        assertNotNull("formula", f.toString());
        assertNotNull("formula", f.toFunctionNotation());

        Number result = f.calculate(ONE);

        assertNotNull("function values", result);
    }

    /**
     * Tests creating a random monomial function.
     */
    @Test
    public void testCreateRandomFunction() {

        Function f = FunctionHelper.createRandomFunction(DEFAULT_NUMBER_BASE);
        assertNotNull("formula", f.toString());
        assertNotNull("formula", f.toFunctionNotation());

        Number result = f.calculate(ONE);

        assertNotNull("function values", result);
    }

}

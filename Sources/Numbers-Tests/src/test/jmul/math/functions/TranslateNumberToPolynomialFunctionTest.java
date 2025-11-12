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


import jmul.math.functions.FunctionHelper;
import jmul.math.functions.PolynomialFunction;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createInfinity;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests translating numbers to polynomial functions.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TranslateNumberToPolynomialFunctionTest {

    /**
     * Tests the translation with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTranslationWithNull() {

        Number number = null;

        FunctionHelper.fromNumber(number);
    }

    /**
     * Tests the translation with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTranslationWithInfinity() {

        int base = 10;
        Number number = createInfinity(base);

        FunctionHelper.fromNumber(number);
    }

    /**
     * Tests the translation with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTranslationWithZero() {

        int base = 10;
        Number number = createNumber(base, "0");

        FunctionHelper.fromNumber(number);
    }

    /**
     * Tests the translation with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTranslationWithFraction() {

        int base = 10;
        Number number = createNumber(base, "1.2345");

        FunctionHelper.fromNumber(number);
    }

    /**
     * Tests translating a number to a polynomial function.
     */
    @Test
    public void testTranslatation() {

        int base = 10;
        Number number = createNumber(base, "12345");

        PolynomialFunction function = FunctionHelper.fromNumber(number);

        assertEquals("#base", base, function.base());
        assertEquals("#string", "x^4 + 2 * x^3 + 3 * x^2 + 4 * x + 5", function.toString());
    }

}

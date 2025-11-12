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


import static jmul.math.functions.FunctionHelper.createPolynomialFunction;
import jmul.math.functions.PolynomialFunction;
import jmul.math.numbers.Number;
import jmul.math.numbers.NumberHelper;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests translating polynomial functions to numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TranslatePolynomialFunctionToNumberTest {

    /**
     * Tests the translation with an invalid parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTranslationWithNull() {

        PolynomialFunction polynimialFunction = null;

        NumberHelper.fromPolynomialFunction(polynimialFunction);
    }

    /**
     * Tests the translation.
     */
    @Test
    public void testTranslationWithZero() {

        int base = 10;

        PolynomialFunction polynimialFunction = createPolynomialFunction(base, "0");

        Number number = NumberHelper.fromPolynomialFunction(polynimialFunction);

        assertEquals("#base", base, number.base());
        assertEquals("#string", "0", number.toString());
    }

    /**
     * Tests the translation.
     */
    @Test
    public void testTranslation() {

        int base = 10;

        PolynomialFunction polynimialFunction = createPolynomialFunction(base, "5", "4", "3", "2", "1");

        Number number = NumberHelper.fromPolynomialFunction(polynimialFunction);

        assertEquals("#base", base, number.base());
        assertEquals("#string", "12345", number.toString());
    }

}

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

package test.jmul.math.intervals;


import jmul.math.intervals.IntervalHelper;
import jmul.math.intervals.Translator;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests translating numbers from one itnerval to another interval.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class TranslatorTest {

    /**
     * Tests translating a number from one interval to another interval.
     */
    @Test
    public void testTranslation1() {

        int base = 10;
        Number newMin = createNumber(base, "-1");
        Number newMax = createNumber(base, "1");
        Number oldMin = createNumber(base, "0");
        Number oldMax = createNumber(base, "5");

        Translator translator = IntervalHelper.createTranslator(oldMin, oldMax, newMin, newMax);

        Number input = createNumber(base, "0");
        Number expectedResult = createNumber(base, "-1");

        Number actualResult = translator.translate(input);
        assertEquals("#translation", expectedResult, actualResult);
    }

    /**
     * Tests translating a number from one interval to another interval.
     */
    @Test
    public void testTranslation2() {

        int base = 10;
        Number newMin = createNumber(base, "-1");
        Number newMax = createNumber(base, "1");
        Number oldMin = createNumber(base, "0");
        Number oldMax = createNumber(base, "5");

        Translator translator = IntervalHelper.createTranslator(oldMin, oldMax, newMin, newMax);

        Number input = createNumber(base, "5");
        Number expectedResult = createNumber(base, "1");

        Number actualResult = translator.translate(input);
        assertEquals("#translation", expectedResult, actualResult);
    }

    /**
     * Tests translating a number from one interval to another interval.
     */
    @Test
    public void testTranslation3() {

        int base = 10;
        Number newMin = createNumber(base, "-1");
        Number newMax = createNumber(base, "1");
        Number oldMin = createNumber(base, "0");
        Number oldMax = createNumber(base, "5");

        Translator translator = IntervalHelper.createTranslator(oldMin, oldMax, newMin, newMax);

        Number input = createNumber(base, "2.5");
        Number expectedResult = createNumber(base, "0");

        Number actualResult = translator.translate(input);
        assertEquals("#translation", expectedResult, actualResult);
    }

    /**
     * Tests translating a number from outside one interval to another interval.
     */
    @Test
    public void testTranslation4() {

        int base = 10;
        Number newMin = createNumber(base, "-1");
        Number newMax = createNumber(base, "1");
        Number oldMin = createNumber(base, "0");
        Number oldMax = createNumber(base, "5");

        Translator translator = IntervalHelper.createTranslator(oldMin, oldMax, newMin, newMax);

        Number input = createNumber(base, "6");
        Number expectedResult = createNumber(base, "1.4");

        Number actualResult = translator.translate(input);
        assertEquals("#translation", expectedResult, actualResult);
    }

}

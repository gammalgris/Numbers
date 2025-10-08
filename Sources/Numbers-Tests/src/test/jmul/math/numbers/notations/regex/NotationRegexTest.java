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

package test.jmul.math.numbers.notations.regex;


import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.numbers.notations.regex.NotationRegex;
import jmul.math.numbers.notations.regex.ScientificNotationRegex;
import jmul.math.numbers.notations.regex.StandardNotationRegex;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 * This test suite tests creating regex entities.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class NotationRegexTest {

    /**
     * Test creating a regex entity for number base 10.
     */
    @Test
    public void testCreateStandardNotationForBase10() {

        int base = 10;

        String allowedDigits = PositionalNumeralSystems.allowedDigitsRegex(base);
        String allowedDigitsWithoutZero = PositionalNumeralSystems.allowedDigitsWithoutZeroRegex(base);

        NotationRegex regex = new StandardNotationRegex(base, allowedDigits, allowedDigitsWithoutZero);
        String regexString = regex.regexString();

        assertNotNull("#regex", regexString);
        assertEquals("#capturing groups", 3, regex.namedCapturingGroups().size());
        assertFalse("#placeholders", regexString.contains("%DIGITS%"));
        assertFalse("#placeholders", regexString.contains("%DIGITS_WITHOUT_ZERO%"));
    }

    /**
     * Test creating a regex entity for number base 2.
     */
    @Test
    public void testCreateStandardNotationForBase2() {

        int base = 2;

        String allowedDigits = PositionalNumeralSystems.allowedDigitsRegex(base);
        String allowedDigitsWithoutZero = PositionalNumeralSystems.allowedDigitsWithoutZeroRegex(base);

        NotationRegex regex = new StandardNotationRegex(base, allowedDigits, allowedDigitsWithoutZero);
        String regexString = regex.regexString();

        assertNotNull("#regex", regexString);
        assertEquals("#capturing groups", 3, regex.namedCapturingGroups().size());
        assertFalse("#placeholders", regexString.contains("%DIGITS%"));
        assertFalse("#placeholders", regexString.contains("%DIGITS_WITHOUT_ZERO%"));
    }

    /**
     * Test creating a regex entity for number base 30.
     */
    @Test
    public void testCreateStandardNotationForBase30() {

        int base = 30;

        String allowedDigits = PositionalNumeralSystems.allowedDigitsRegex(base);
        String allowedDigitsWithoutZero = PositionalNumeralSystems.allowedDigitsWithoutZeroRegex(base);

        NotationRegex regex = new StandardNotationRegex(base, allowedDigits, allowedDigitsWithoutZero);
        String regexString = regex.regexString();

        assertNotNull("#regex", regexString);
        assertEquals("#capturing groups", 3, regex.namedCapturingGroups().size());
        assertFalse("#placeholders", regexString.contains("%DIGITS%"));
        assertFalse("#placeholders", regexString.contains("%DIGITS_WITHOUT_ZERO%"));
    }

    /**
     * Test creating a regex entity for number base 10.
     */
    @Test
    public void testCreateScienificNotationNotationForBase10() {

        int base = 10;

        String allowedDigits = PositionalNumeralSystems.allowedDigitsRegex(base);
        String allowedDigitsWithoutZero = PositionalNumeralSystems.allowedDigitsWithoutZeroRegex(base);

        NotationRegex regex = new ScientificNotationRegex(base, allowedDigits, allowedDigitsWithoutZero);
        String regexString = regex.regexString();

        assertNotNull("#regex", regexString);
        assertEquals("#capturing groups", 4, regex.namedCapturingGroups().size());
        assertFalse("#placeholders", regexString.contains("%DIGITS%"));
        assertFalse("#placeholders", regexString.contains("%DIGITS_WITHOUT_ZERO%"));
        assertTrue("#optional sign", regexString.contains("<SIGNEXPONENT>[+-]){0,1}"));
    }

    /**
     * Test creating a regex entity for number base 2.
     */
    @Test
    public void testCreateScienificNotationNotationForBase2() {

        int base = 2;

        String allowedDigits = PositionalNumeralSystems.allowedDigitsRegex(base);
        String allowedDigitsWithoutZero = PositionalNumeralSystems.allowedDigitsWithoutZeroRegex(base);

        NotationRegex regex = new ScientificNotationRegex(base, allowedDigits, allowedDigitsWithoutZero);
        String regexString = regex.regexString();

        assertNotNull("#regex", regexString);
        assertEquals("#capturing groups", 4, regex.namedCapturingGroups().size());
        assertFalse("#placeholders", regexString.contains("%DIGITS%"));
        assertFalse("#placeholders", regexString.contains("%DIGITS_WITHOUT_ZERO%"));
        assertTrue("#optional sign", regexString.contains("<SIGNEXPONENT>[+-]){0,1}"));
    }

    /**
     * Test creating a regex entity for number base 30.
     */
    @Test
    public void testCreateScienificNotationNotationForBase30() {

        int base = 30;

        String allowedDigits = PositionalNumeralSystems.allowedDigitsRegex(base);
        String allowedDigitsWithoutZero = PositionalNumeralSystems.allowedDigitsWithoutZeroRegex(base);

        NotationRegex regex = new ScientificNotationRegex(base, allowedDigits, allowedDigitsWithoutZero);
        String regexString = regex.regexString();

        assertNotNull("#regex", regexString);
        assertEquals("#capturing groups", 4, regex.namedCapturingGroups().size());
        assertFalse("#placeholders", regexString.contains("%DIGITS%"));
        assertFalse("#placeholders", regexString.contains("%DIGITS_WITHOUT_ZERO%"));
        assertTrue("#mandatory sign", regexString.contains("<SIGNEXPONENT>[+-]){1}"));
    }

}

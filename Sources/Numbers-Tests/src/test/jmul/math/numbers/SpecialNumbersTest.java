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
import jmul.math.Math;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.operations.processing.ProcessingDetails;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * This test suite tests special numbers.
 *
 * @author Kristian Kutin
 */
@UnitTest
public class SpecialNumbersTest {

    /**
     * Tests Euler's number with base 10 and default processing details.
     */
    @Test
    public void testEulersNumberBase10() {

        int base = 10;

        Number e = Math.e(base);

        assertEquals("2.7182818282", e.toString());
    }

    /**
     * Tests Euler's number with base 10 and different processing details.
     */
    @Test
    public void testEulersNumberBase10Variant2() {

        int base = 10;

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(ProcessingDetails.DEFAULT_ALGORITHM, createNumber(base, "20"),
                                                   createNumber(base, "20"));

        Number e = Math.e(processingDetails, base);

        assertEquals("2.71828182845904523533", e.toString());
    }

    /**
     * Tests Euler's number with base 2 and default processing details.
     */
    @Test
    public void testEulersNumberBase2() {

        int base = 2;

        Number e = Math.e(base);

        assertEquals("10.1011011111", e.toString());
        assertEquals("2.7177734375", e.rebase(10).toString());
    }

    /**
     * Tests Euler's number with base 30 and default processing details.
     */
    @Test
    public void testEulersNumberBase30() {

        int base = 30;

        Number e = Math.e(base);

        assertEquals("2.LGDI8COIGJ", e.toString());
        assertEquals("2.7182818282", e.rebase(10).toString());
    }

}

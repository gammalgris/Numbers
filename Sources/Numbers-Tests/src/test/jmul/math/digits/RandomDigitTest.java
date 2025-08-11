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

package test.jmul.math.digits;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jmul.math.digits.Digit;
import jmul.math.digits.PositionalNumeralSystems;
import jmul.math.functions.implementations.RandomDigitFunction;
import jmul.math.numbers.Constants;
import jmul.math.operations.Result;
import jmul.math.operations.UnaryOperation;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests generating random digits.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RandomDigitTest {

    /**
     * A number base.
     */
    private final int base;

    /**
     * A function.
     */
    private UnaryOperation<Integer, Result<Digit>> function;

    /**
     * Creates a new test case according to the specified parameter.
     *
     * @param base
     *        a number base
     */
    public RandomDigitTest(int base) {

        super();

        this.base = base;
        this.function = new RandomDigitFunction();
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("number base %d -> random digit", base);
    }

    /**
     * Generates random digits and checks the occurrences.
     */
    @Test
    public void testGeneratingRandomDigit() {

        Map<Digit, Integer> occurrences = new HashMap<>();

        for (int a = 0; a < base * 20; a++) {

            Result<Digit> result = function.calculate(base);
            Digit randomDigit = result.result();

            Integer occurrence = occurrences.get(randomDigit);
            if (occurrence == null) {

                occurrence = 0;
            }
            occurrence++;
            occurrences.put(randomDigit, occurrence);
        }

        for (int ordinal = 0; ordinal < base; ordinal++) {

            Digit digit = PositionalNumeralSystems.ordinalToDigit(base, ordinal);
            Integer occurrence = occurrences.get(digit);
            assertNotNull(toString(), occurrence);
            assertTrue(toString(), occurrence > 0);
        }
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = Constants.BASE_MIN_LIMIT; base <= Constants.BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { base });
        }

        return parameters;
    }

}

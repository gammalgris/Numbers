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


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jmul.math.Math;
import jmul.math.numbers.Constants;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests generating random numbers (with custom precision).
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RandomNumber2Test {

    /**
     * A number base.
     */
    private final int base;

    /**
     * The number of digits to the right of the decimal separator.
     */
    private final Number digits;

    /**
     * Creates a new test case according to the specified parameter.
     *
     * @param base
     *        a number base
     * @param digits
     *        the number of digits to the right of the decimal separator
     */
    public RandomNumber2Test(int base, Number digits) {

        super();

        this.base = base;
        this.digits = digits;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("number base %d -- digits %s --> random digit", base, digits);
    }

    /**
     * Generates a random number and checks the random numbers.
     */
    @Test
    public void testGeneratingRandomNumber() {

        Map<Number, Integer> occurrences = new HashMap<>();

        int max = base * 100;
        for (int a = 0; a < max; a++) {

            Number randomNumber = Math.random(base, digits);

            Integer occurrence = occurrences.get(randomNumber);
            if (occurrence == null) {

                occurrence = 0;
            }
            occurrence++;
            occurrences.put(randomNumber, occurrence);
        }

        System.out.println();

        final Number ZERO = createNumber(base, Signs.POSITIVE, 0);
        final Number ONE = createNumber(base, Signs.POSITIVE, 1);

        for (Number number : occurrences.keySet()) {

            assertTrue(toString(), number.isGreaterOrEqual(ZERO));
            assertTrue(toString(), number.isLesser(ONE));
        }

        System.out.println();
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

            Number number = createNumber(base, "0");
            number = number.inc();
            number = number.inc();

            parameters.add(new Object[] { base, number });
        }

        return parameters;
    }

}

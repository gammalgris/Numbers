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

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests generating random numbers with an interval (with default precision).
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class RandomNumber3Test {

    /**
     * The lower bound of an interval.
     */
    private final Number min;

    /**
     * The upper bound of an interval.
     */
    private final Number max;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param min
     *        the lower bound of an interval
     * @param max
     *        the upper bound of an interval
     */
    public RandomNumber3Test(Number min, Number max) {

        super();

        this.min = min;
        this.max = max;
    }

    /**
     * Returns a test summary.
     *
     * @return a test summary
     */
    @Override
    public String toString() {

        return String.format("random ([base:%d]; min %s; max %s) --> random digit", min.base(), min, max);
    }

    /**
     * Generates a random number and checks the random numbers.
     */
    @Test
    public void testGeneratingRandomNumber() {

        Map<Number, Integer> occurrences = new HashMap<>();

        int base = min.base();
        int max2 = base * 100;
        for (int a = 0; a < max2; a++) {

            Number randomNumber = Math.random(min, max);

            Integer occurrence = occurrences.get(randomNumber);
            if (occurrence == null) {

                occurrence = 0;
            }
            occurrence++;
            occurrences.put(randomNumber, occurrence);
        }

        System.out.println();

        Number number = min;
        while (number.isLesserOrEqual(max)) {

            Integer occurrence = occurrences.get(number);
            assertNotNull(toString(), occurrence);
            assertTrue(toString(), occurrence > 0);

            number = number.inc();
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

            Number min = createNumber(base, "0");
            min = min.inc();

            Number max = min;
            max = max.doubling();
            max = max.doubling();
            max = max.doubling();

            parameters.add(new Object[] { min, max });
        }

        return parameters;
    }

}

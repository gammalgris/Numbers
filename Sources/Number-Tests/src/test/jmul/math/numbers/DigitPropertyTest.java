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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jmul.math.numbers.digits.Digit;
import jmul.math.numbers.RealDecimalNumber;

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite test the properties of each digit of a number (i.e. digit sequence).
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class DigitPropertyTest {

    /**
     * The actual input.
     */
    private final String input;

    /**
     * The expected base for each digit.
     */
    private final int expectedBase;

    /**
     * Creates a new test case according to the specified numbers.
     *
     * @param anInput
     * @param theExpectedBase
     */
    public DigitPropertyTest(String anInput, int theExpectedBase) {

        super();

        input = anInput;
        expectedBase = theExpectedBase;
    }

    /**
     * Tests the instantiation and the generation of a string representation (i.e. standard notation).
     */
    @Test
    public void testDigitProperties() {

        String message = String.format("Test failed for %s!", input);

        //RealNumber number = RealNumber.parseString(input);
        RealDecimalNumber number = new RealDecimalNumber(input);

        List<Integer> baseSequence = new ArrayList<>();

        for (int a = number.leftDigits(); a >= -number.rightDigits(); a--) {

            if (a == 0) {

                continue;
            }

            Digit digit = number.digitAt(a);
            int base = digit.base();

            baseSequence.add(base);
        }

        Set<Integer> actualBase = new TreeSet<>(baseSequence);

        if (actualBase.size() != 1) {

            fail(message);
        }

        if (!actualBase.contains(expectedBase)) {

            fail(message);
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

        parameters.add(new Object[] { "0", 10 });
        parameters.add(new Object[] { "10", 10 });

        return parameters;
    }

}

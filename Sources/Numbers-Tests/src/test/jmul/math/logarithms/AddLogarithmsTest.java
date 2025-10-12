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

package test.jmul.math.logarithms;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.logarithms.Logarithm;
import static jmul.math.logarithms.LogarithmHelper.createLogarithm;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests adding logarithms.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class AddLogarithmsTest {

    /**
     * A logarithm expression.
     */
    private final Logarithm logarithm1;

    /**
     * A logarithm expression.
     */
    private final Logarithm logarithm2;

    /**
     * The expected result.
     */
    private final Logarithm expectedResult;

    /**
     * Create a test case according to the specified parameters.
     * 
     * @param logarithm1
     *        a logarithm expression
     * @param logarithm2
     *        a logarithm expression
     * @param expectedResult
     *        a logarithm expression
     */
    public AddLogarithmsTest(Logarithm logarithm1, Logarithm logarithm2, Logarithm expectedResult) {

        super();

        this.logarithm1 = logarithm1;
        this.logarithm2 = logarithm2;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a string representation for this test case.
     * 
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("%s + %s = %s", logarithm1, logarithm2, expectedResult);
    }

    @Test
    public void testAddition() {

        Logarithm actualResult = logarithm1.add(logarithm2);

        assertEquals(toString(), expectedResult, actualResult);
        assertEquals(toString(), expectedResult.toString(), actualResult.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { createLogarithm(10, "5", "2"), createLogarithm(10, "5", "2"),
                                      createLogarithm(10, "5", "4") });

        return parameters;
    }

}

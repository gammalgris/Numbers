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

import jmul.math.Math;
import jmul.math.intervals.Interval;
import jmul.math.intervals.IntervalHelper;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;

import jmul.math.operations.processing.ProcessingDetails;

import jmul.math.operations.repository.OperationIdentifiers;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests calculating the sine.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class SineTest {

    /**
     * The input for calculating the sine.
     */
    private final Number input;

    /**
     * The expected result.
     */
    private final Number expectedResult;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param input
     *        the input
     * @param expectedResult
     *        the expected result
     */
    public SineTest(Number input, Number expectedResult) {

        super();

        this.input = input;
        this.expectedResult = expectedResult;
    }

    /**
     * Returns a string representation for this test case.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return String.format("sine([%d] %s) = [%d] %s", input.base(), input, expectedResult.base(), expectedResult);
    }

    /**
     * Tests calculating the sine.
     */
    @Test
    public void calculateSine() {

        int base = input.base();

        Interval interval = IntervalHelper.createInterval(expectedResult, createNumber(base, "5"));

        Number actualResult = input.sine();

        //assertEquals(toString(), expectedResult, actualResult);
        //assertEquals(toString(), expectedResult.toString(), actualResult.toString());
        assertTrue(toString() + "?=" + actualResult, interval.isWithinInterval(actualResult));
    }

    /**
     * Tests calculating the sine.
     */
    @Test
    public void calculateSineVariant2() {

        int base = input.base();

        Interval interval = IntervalHelper.createInterval(expectedResult, createNumber(base, "3"));

        ProcessingDetails processingDetails =
            ProcessingDetails.setProcessingDetails(OperationIdentifiers.SINE_APPROXIMATION_2_FUNCTION,
                                                   createNumber(base, "20"), createNumber(base, "1"));

        Number actualResult = input.sine(processingDetails);

        //assertEquals(toString(), expectedResult, actualResult);
        //assertEquals(toString(), expectedResult.toString(), actualResult.toString());
        assertTrue(toString() + "?=" + actualResult, interval.isWithinInterval(actualResult));
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        int base = 10;

        final Number pi = Math.PI.value(10);

        final Number degree0 = Math.ZERO.value(base);
        final Number degree90 = pi.halving();
        final Number degree180 = pi;
        final Number degree270 = degree90.add(degree180);
        final Number degree360 = pi.doubling();
        final Number degree450 = degree360.add(degree90);

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { degree0, createNumber(base, "0") });
        parameters.add(new Object[] { degree90, createNumber(base, "1") });
        parameters.add(new Object[] { degree180, createNumber(base, "0") });
        parameters.add(new Object[] { degree270, createNumber(base, "-1") });
        parameters.add(new Object[] { degree360, createNumber(base, "0") });
        parameters.add(new Object[] { degree450, createNumber(base, "0") });

        /*parameters.add(new Object[] { createNumber(base, "90"), createNumber(base, "1") });
        parameters.add(new Object[] { createNumber(base, "180"), createNumber(base, "0") });
        parameters.add(new Object[] { createNumber(base, "270"), createNumber(base, "-1") });
        parameters.add(new Object[] { createNumber(base, "360"), createNumber(base, "0") });
        parameters.add(new Object[] { createNumber(base, "450"), createNumber(base, "0") });*/

        return parameters;
    }

}

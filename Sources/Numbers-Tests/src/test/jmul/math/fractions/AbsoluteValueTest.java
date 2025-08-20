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

package test.jmul.math.fractions;


import java.util.ArrayList;
import java.util.Collection;

import jmul.math.Math;
import jmul.math.fractions.Fraction;
import jmul.math.fractions.FractionHelper;
import static jmul.math.fractions.FractionHelper.createFraction;
import static jmul.math.numbers.Constants.BASE_MAX_LIMIT;
import static jmul.math.numbers.Constants.BASE_MIN_LIMIT;
import static jmul.math.signs.Signs.NEGATIVE;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This tests suite checks the calculation of absolute fraction values.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class AbsoluteValueTest {

    /**
     * A number.
     */
    private final Fraction fraction;

    /**
     * The expected result.
     */
    private final Fraction expectedAbsoluteValue;

    /**
     * Creates a test according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param expectedAbsoluteValue
     *        the expected result.
     */
    public AbsoluteValueTest(Fraction fraction, Fraction expectedAbsoluteValue) {

        super();

        this.fraction = fraction;
        this.expectedAbsoluteValue = expectedAbsoluteValue;
    }

    /**
     * Calculates the abslute value and checks the correctness of the result.
     */
    @Test
    public void testAbsoluteValue() {

        Fraction absoluteValue = fraction.absoluteValue();

        assertEquals(toString(), expectedAbsoluteValue, absoluteValue);
        assertEquals(toString(), expectedAbsoluteValue.toString(), absoluteValue.toString());
    }

    /**
     * Calculates the abslute value and checks the correctness of the result.
     */
    @Test
    public void testAbsoluteValueVariant2() {

        Fraction absoluteValue = Math.absoluteValue(fraction);

        assertEquals(toString(), expectedAbsoluteValue, absoluteValue);
        assertEquals(toString(), expectedAbsoluteValue.toString(), absoluteValue.toString());
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        for (int base = BASE_MIN_LIMIT; base <= BASE_MAX_LIMIT; base++) {

            parameters.add(new Object[] { FractionHelper.createInfinity(base), FractionHelper.createInfinity(base) });
            parameters.add(new Object[] { FractionHelper.createInfinity(base, NEGATIVE),
                                          FractionHelper.createInfinity(base) });

            parameters.add(new Object[] { createFraction(base, "0"), createFraction(base, "0") });
            parameters.add(new Object[] { createFraction(base, "-0"), createFraction(base, "0") });

            parameters.add(new Object[] { createFraction(base, "1"), createFraction(base, "1") });
            parameters.add(new Object[] { createFraction(base, "-1"), createFraction(base, "1") });

            parameters.add(new Object[] { createFraction(base, "1", "1"), createFraction(base, "1", "1") });
            parameters.add(new Object[] { createFraction(base, "-1", "1"), createFraction(base, "1", "1") });

            parameters.add(new Object[] { createFraction(base, "1", "1", "1"), createFraction(base, "1", "1", "1") });
            parameters.add(new Object[] { createFraction(base, "-1", "1", "1"), createFraction(base, "1", "1", "1") });
        }

        return parameters;
    }

}

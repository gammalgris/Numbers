/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2024  Kristian Kutin
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

import jmul.math.fractions.Fraction;
import static jmul.math.fractions.FractionHelper.createFraction;
import jmul.math.numbers.Constants;
import jmul.math.signs.Sign;
import jmul.math.signs.Signs;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the creation of various fractions and tests various fraction properties.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class CreateFractionWithValidParametersTest {

    /**
     * A fraction.
     */
    private final Fraction fraction;

    /**
     * The expected string representation.
     */
    private final String expectedString;

    /**
     * The expected sign.
     */
    private final Sign expectedSign;

    /**
     * Creates a new test case according to the specified parameters.
     *
     * @param fraction
     *        a fraction
     * @param expectedString
     *        the expected string representation
     * @param expectedSign
     *        the expected sign
     */
    public CreateFractionWithValidParametersTest(Fraction fraction, String expectedString, Sign expectedSign) {

        super();

        this.fraction = fraction;
        this.expectedString = expectedString;
        this.expectedSign = expectedSign;
    }

    /**
     * Tests the string representation.
     */
    @Test
    public void testToString() {

        String message = String.format("%s -> %s", fraction, expectedString);

        String string = fraction.toString();
        assertEquals(message, expectedString, string);
    }

    /**
     * Tests the sign and all associated methods.
     */
    @Test
    public void testSign() {

        String message = String.format("%s -> %s", fraction, expectedString);

        Sign actualSign = fraction.sign();
        assertEquals(message, expectedSign, actualSign);

        if (Signs.isPositive(actualSign)) {

            assertTrue(message, fraction.isPositive());
            assertFalse(message, fraction.isNegative());

        } else {

            assertFalse(message, fraction.isPositive());
            assertTrue(message, fraction.isNegative());
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

            parameters.add(new Object[] { createFraction(base), "infinity", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(Signs.NEGATIVE, base), "-infinity", Signs.NEGATIVE });

            parameters.add(new Object[] { createFraction(base, "0"), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-0"), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "1"), "1", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-1"), "-1", Signs.NEGATIVE });

            parameters.add(new Object[] { createFraction(base, "0", "0"), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, null, "0"), "infinity/0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "0", null), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-0", "0"), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "0", "-0"), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "1", "10"), "1/10", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-1", "10"), "-1/10", Signs.NEGATIVE });
            parameters.add(new Object[] { createFraction(base, "1", "-10"), "-1/10", Signs.NEGATIVE });

            parameters.add(new Object[] { createFraction(base, "0", "0", "0"), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-0", "0", "0"), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "0", "-0", "0"), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "0", "0", "-0"), "0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, null, "1", "0"), "infinity 1/0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "1", null, "0"), "1 infinity/0", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "1", "1", "10"), "1 1/10", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "1", "1", null), "1 1/infinity", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-1", "1", null), "-1 1/infinity", Signs.NEGATIVE });

            parameters.add(new Object[] { createFraction(base, "1", "1", "1"), "1 1/1", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-1", "1", "1"), "-1 1/1", Signs.NEGATIVE });
            parameters.add(new Object[] { createFraction(base, "1", "-1", "1"), "-1 1/1", Signs.NEGATIVE });
            parameters.add(new Object[] { createFraction(base, "1", "1", "-1"), "-1 1/1", Signs.NEGATIVE });
            parameters.add(new Object[] { createFraction(base, "1", "-1", "-1"), "1 1/1", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-1", "-1", "1"), "1 1/1", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-1", "1", "-1"), "1 1/1", Signs.POSITIVE });
            parameters.add(new Object[] { createFraction(base, "-1", "-1", "-1"), "-1 1/1", Signs.NEGATIVE });
        }

        return parameters;
    }

}

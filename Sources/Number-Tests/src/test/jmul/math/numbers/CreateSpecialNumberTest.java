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


import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;

import jmul.math.numbers.Number;
import jmul.math.numbers.NumberImpl;

import jmul.test.classification.UnitTest;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static test.jmul.math.numbers.NumberCreationHelper.createNumber;


/**
 * This test suite tests creating special numbers with various input parameters.
 *
 * @author Kristian Kutin
 */
@UnitTest
@RunWith(Parameterized.class)
public class CreateSpecialNumberTest {

    /**
     * The implementation class for numbers.
     */
    private final Class type;

    /**
     * The input (i.e. a number string in supported notations)
     */
    private final Object input;

    /**
     * The expected standard notation which might deviate from the input.
     */
    private final String expectedStandardNotation;

    /**
     * The expected scientific notation which might deviate from the input.
     */
    private final String expectedScientificNotation;

    /**
     * Creates a new text case according to the specified parameters.
     *
     * @param type
     *        an imklementation class
     * @param input
     *        an input
     * @param expectedStandardNotation
     *        output deviating from the input
     * @param expectedScientificNotation
     *        output deviating from the input
     */
    public CreateSpecialNumberTest(Class type, Object input, String expectedStandardNotation,
                                   String expectedScientificNotation) {

        super();

        this.type = type;
        this.input = input;
        this.expectedStandardNotation = expectedStandardNotation;
        this.expectedScientificNotation = expectedScientificNotation;
    }

    /**
     * Tests creating a number and compares the number with the input.
     *
     * @throws NoSuchMethodException
     *         is thrown if there exists no suitable constructor.
     * @throws InstantiationException
     *         is thrown if an error occurs within the constructor
     * @throws IllegalAccessException
     *         is thrown if the constructor cannot be accessed
     * @throws InvocationTargetException
     *         is thrown if an error occurs within the constructo
     */
    @Test
    public void testNumberCreation() throws NoSuchMethodException, InstantiationException, IllegalAccessException,
                                            InvocationTargetException {

        Number number = createNumber(type, input);

        String actualStandardNotation = number.toStandardNotation();
        String actualScientificNotation = number.toScientificNotation();

        String message =
            String.format("The created number (%s/%s) doesn't match the expected output (%s/%s)!",
                          actualStandardNotation, actualScientificNotation, expectedStandardNotation,
                          expectedScientificNotation);
        assertTrue(message, expectedStandardNotation.equals(actualStandardNotation));
        assertTrue(message, expectedScientificNotation.equals(actualScientificNotation));
    }

    /**
     * Returns a matrix of test data and expected results.
     *
     * @return a matrix of test data and expected results
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Collection<Object[]> parameters = new ArrayList<Object[]>();

        parameters.add(new Object[] { NumberImpl.class, "1.1E-0", "1.1", "1.1E0" });
        parameters.add(new Object[] { NumberImpl.class, "-1.1E-0", "-1.1", "-1.1E0" });

        parameters.add(new Object[] { NumberImpl.class, "0", "0", "0" });
        parameters.add(new Object[] { NumberImpl.class, new Byte((byte) 0), "0", "0" });
        parameters.add(new Object[] { NumberImpl.class, new Short((short) 0), "0", "0" });
        parameters.add(new Object[] { NumberImpl.class, new Integer((int) 0), "0", "0" });
        parameters.add(new Object[] { NumberImpl.class, new Long(0L), "0", "0" });
        parameters.add(new Object[] { NumberImpl.class, new Float(0F), "0", "0" });
        parameters.add(new Object[] { NumberImpl.class, new Double(0D), "0", "0" });

        parameters.add(new Object[] { NumberImpl.class, "0.010", "0.01", "1E-2" });
        parameters.add(new Object[] { NumberImpl.class, "-1.010", "-1.01", "-1.01E0" });

        return parameters;
    }

}

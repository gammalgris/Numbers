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
import jmul.math.numbers.NumberHelper;
import jmul.math.numbers.NumberImpl;

import jmul.reflection.constructors.ConstructorInvoker;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * This test suite tests the digit count of a number (i.e. digits left and right of the decimal separator).
 * Numbers are base 10.
 *
 * TODO missing test for bases besides 10
 *
 * @author Kristian Kutin
 */
@RunWith(Parameterized.class)
public class DigitTest {

    /**
     * The implementation class for numbers.
     */
    private final Class type;

    /**
     * The input
     */
    private final Object input;

    /**
     * The expected digit count left of a decimal separator.
     */
    private final int digitsLeft;

    /**
     * The expected digit count right of a decimal separator.
     */
    private final int digitsRight;

    /**
     * Creates a new test according to the specified parameters.
     *
     * @param type
     *        a number class
     * @param input
     *        an input
     * @param digitsLeft
     *        digits left of a decimal separator
     * @param digitsRight
     *        digits right of a decimal separator
     */
    public DigitTest(Class type, Object input, int digitsLeft, int digitsRight) {

        super();

        this.type = type;
        this.input = input;
        this.digitsLeft = digitsLeft;
        this.digitsRight = digitsRight;
    }

    /**
     * Instantiates a number according to the specified parameters.
     *
     * @param type
     *        a number class
     * @param input
     *        an input
     *
     * @return a new number instance
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
    private static Number createNumber(Class type, Object input) throws NoSuchMethodException, InstantiationException,
                                                                        IllegalAccessException,
                                                                        InvocationTargetException {

        Class[] signature = { input.getClass() };
        ConstructorInvoker invoker = new ConstructorInvoker(type, signature);

        Object[] parameters = { input };
        return (Number) invoker.invoke(parameters);
    }

    /**
     * Creates a number and checks if the digit count left and right of the decimal separator
     * is as expected.
     *
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

        int actualLeftDigits = NumberHelper.countLeftDigits(number);
        int actualRightDigits = NumberHelper.countRightDigit(number);

        {
            String message = String.format("The left digits don't match (%s)!", input);
            assertEquals(message, digitsLeft, actualLeftDigits);
        }

        {
            String message = String.format("The right digits don't match (%s)!", input);
            assertEquals(message, digitsRight, actualRightDigits);
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

        parameters.add(new Object[] { NumberImpl.class, "1", 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, "0", 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, "-1", 1, 0 });

        parameters.add(new Object[] { NumberImpl.class, "1.00", 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, "0.00", 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, "-1.00", 1, 0 });

        parameters.add(new Object[] { NumberImpl.class, "1.1E0", 1, 1 });
        parameters.add(new Object[] { NumberImpl.class, "-1.1E0", 1, 1 });
        parameters.add(new Object[] { NumberImpl.class, "1.1E-0", 1, 1 });
        parameters.add(new Object[] { NumberImpl.class, "-1.1E-0", 1, 1 });

        parameters.add(new Object[] { NumberImpl.class, "1.1E1", 2, 0 });
        parameters.add(new Object[] { NumberImpl.class, "-1.1E1", 2, 0 });
        parameters.add(new Object[] { NumberImpl.class, "1.1E-1", 1, 2 });
        parameters.add(new Object[] { NumberImpl.class, "-1.1E-1", 1, 2 });

        parameters.add(new Object[] { NumberImpl.class, "1.1E2", 3, 0 });
        parameters.add(new Object[] { NumberImpl.class, "-1.1E2", 3, 0 });
        parameters.add(new Object[] { NumberImpl.class, "1.1E-2", 1, 3 });
        parameters.add(new Object[] { NumberImpl.class, "-1.1E-2", 1, 3 });

        parameters.add(new Object[] { NumberImpl.class, "1.1E3", 4, 0 });
        parameters.add(new Object[] { NumberImpl.class, "-1.1E3", 4, 0 });
        parameters.add(new Object[] { NumberImpl.class, "1.1E-3", 1, 4 });
        parameters.add(new Object[] { NumberImpl.class, "-1.1E-3", 1, 4 });

        parameters.add(new Object[] { NumberImpl.class, "1.010", 1, 2 });
        parameters.add(new Object[] { NumberImpl.class, "0.010", 1, 2 });
        parameters.add(new Object[] { NumberImpl.class, "-1.010", 1, 2 });

        parameters.add(new Object[] { NumberImpl.class, "1.1", 1, 1 });
        parameters.add(new Object[] { NumberImpl.class, "+1.1", 1, 1 });
        parameters.add(new Object[] { NumberImpl.class, "-1.1", 1, 1 });

        parameters.add(new Object[] { NumberImpl.class, "21.12", 2, 2 });
        parameters.add(new Object[] { NumberImpl.class, "+21.12", 2, 2 });
        parameters.add(new Object[] { NumberImpl.class, "-21.12", 2, 2 });

        parameters.add(new Object[] { NumberImpl.class, "321.123", 3, 3 });
        parameters.add(new Object[] { NumberImpl.class, "+321.123", 3, 3 });
        parameters.add(new Object[] { NumberImpl.class, "-321.123", 3, 3 });

        parameters.add(new Object[] { NumberImpl.class, new Byte((byte) 0), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Byte((byte) 1), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Byte((byte) 2), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, Byte.MIN_VALUE, 3, 0 });
        parameters.add(new Object[] { NumberImpl.class, Byte.MAX_VALUE, 3, 0 });

        parameters.add(new Object[] { NumberImpl.class, new Short((short) 0), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Short((short) 1), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Short((short) 2), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, Short.MIN_VALUE, 5, 0 });
        parameters.add(new Object[] { NumberImpl.class, Short.MAX_VALUE, 5, 0 });

        parameters.add(new Object[] { NumberImpl.class, new Integer((int) 0), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Integer((int) 1), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Integer((int) 2), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, Integer.MIN_VALUE, 10, 0 });
        parameters.add(new Object[] { NumberImpl.class, Integer.MAX_VALUE, 10, 0 });

        parameters.add(new Object[] { NumberImpl.class, new Long(0L), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Long(1L), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Long(2L), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, Long.MIN_VALUE, 19, 0 });
        parameters.add(new Object[] { NumberImpl.class, Long.MAX_VALUE, 19, 0 });

        parameters.add(new Object[] { NumberImpl.class, new Float(0F), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Float(1F), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Float(2F), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, Float.MIN_VALUE, 1, 46 });
        parameters.add(new Object[] { NumberImpl.class, Float.MAX_VALUE, 39, 0 });

        parameters.add(new Object[] { NumberImpl.class, new Double(0D), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Double(1D), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, new Double(2D), 1, 0 });
        parameters.add(new Object[] { NumberImpl.class, Double.MIN_VALUE, 1, 325 });
        parameters.add(new Object[] { NumberImpl.class, Double.MAX_VALUE, 309, 0 });

        return parameters;
    }

}

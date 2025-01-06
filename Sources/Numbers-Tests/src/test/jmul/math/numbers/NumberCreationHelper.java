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

import jmul.math.numbers.Number;

import jmul.reflection.constructors.ConstructorInvoker;


/**
 * A utility class for creating numbers.
 *
 * @author Kristian Kutin
 */
public final class NumberCreationHelper {

    /**
     * The default constructor.
     */
    private NumberCreationHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Instantiates a number according to the specified parameters.
     *
     * @param base
     *       the base for the number
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
    public static Number createNumber(Integer base, Class type, Object input) throws NoSuchMethodException,
                                                                                     InstantiationException,
                                                                                     IllegalAccessException,
                                                                                     InvocationTargetException {

        Class[] signature;
        if (base == null) {

            signature = new Class[] { input.getClass() };

        } else {

            signature = new Class[] { Integer.TYPE, input.getClass() };
        }
        ConstructorInvoker invoker = new ConstructorInvoker(type, signature);

        Object[] parameters;
        if (base == null) {

            parameters = new Object[] { input };

        } else {

            parameters = new Object[] { base, input };
        }

        return (Number) invoker.invoke(parameters);
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
    public static Number createNumber(Class type, Object input) throws NoSuchMethodException, InstantiationException,
                                                                       IllegalAccessException,
                                                                       InvocationTargetException {

        Class[] signature = { input.getClass() };
        ConstructorInvoker invoker = new ConstructorInvoker(type, signature);

        Object[] parameters = { input };
        return (Number) invoker.invoke(parameters);
    }

}

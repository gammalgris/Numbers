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

package jmul.singletons;


import java.util.HashMap;
import java.util.Map;


/**
 * This class manages function singletons (i.e. instances of classes that implement a strategy pattern) and shouldn't
 * exist multiple times.
 *
 * @author Kristian Kutin
 */
public final class FunctionSingletons {

    /**
     * A map containing all singletons.
     */
    private static Map<CharSequence, Object> SINGLETON_MAP;

    /*
     * The static initializer.
     */
    static {

        SINGLETON_MAP = new HashMap<>();
    }

    /**
     * The default constructor.
     */
    private FunctionSingletons() {

        throw new UnsupportedOperationException();
    }

    /**
     * Checks if a specific function singleton exists.
     *
     * @param name
     *        the name of a function singleton
     *
     * @return <code>true</code> if the function singleton exists, else <code>false</code>
     */
    public static boolean existsFunction(CharSequence name) {

        return SINGLETON_MAP.containsKey(name);
    }

    /**
     * Adds or replaces the
     *
     * @param name
     *        the name for this function singleton
     * @param function
     *        a function singleton
     */
    public static void putFunction(CharSequence name, Object function) {

        if (existsFunction(name)) {

            throw new FunctionExistsException(name);
        }

        SINGLETON_MAP.put(name, function);
    }

    /**
     * Returns the function singelton with the speficied name.
     *
     * @param name
     *        the name of a function singleton
     *
     * @return
     */
    public static Object getFunction(CharSequence name) {

        return SINGLETON_MAP.get(name);
    }

}



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

package jmul.math.vectors;


import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;


/**
 * A utility class for vector.
 *
 * @author Kristian Kutin
 */
public class VectorHelper {

    /**
     * The default constructor.
     */
    private VectorHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new Vector.
     *
     * @param base
     *        a number base
     * @param components
     *        all components of the new vector
     *
     * @return a new vector
     */
    protected static Vector createVector(int base, Number... components) {

        return new VectorImpl(base, components);
    }

    /**
     * Creates a new vector.
     *
     * @param base
     *        a number base
     * @param componentStrings
     *        all components as strings
     *
     * @return a new vector
     */
    public static Vector createVector(int base, String... componentStrings) {

        int length = componentStrings.length;
        Number[] components = new Number[length];

        for (int index = 0; index < length; index++) {

            String componentString = componentStrings[index];
            Number number = createNumber(base, componentString);
            components[index] = number;
        }

        return createVector(base, components);
    }

}

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

package jmul.math.matrices;


import jmul.math.functions.implementations.ParameterCheckHelper;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.createNumber;


/**
 * A utility class for matrices.
 *
 * @author Kristian Kutin
 */
public final class MatrixHelper {

    /**
     * The default constructor.
     */
    private MatrixHelper() {

        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new matrix.
     *
     * @param base
     *        a number base
     * @param columns
     *        a number of columns
     * @param rows
     *        a number of rows
     * @param components
     *        all components of the new Matrix
     *
     * @return a new matrix
     */
    protected static Matrix createMatrix(int base, Number columns, Number rows, Number... components) {

        return new MatrixImpl(base, columns, rows, components);
    }

    /**
     * Creates a new matrix.
     *
     * @param base
     *        a number base
     * @param columns
     *        a number of columns
     * @param rows
     *        a number of rows
     * @param componentStrings
     *        all components as strings
     *
     * @return a new matrix
     */
    public static Matrix createMatrix(int base, Number columns, Number rows, String... componentStrings) {

        ParameterCheckHelper.checkParameter(componentStrings);

        int length = componentStrings.length;
        Number[] components = new Number[length];

        for (int index = 0; index < length; index++) {

            String componentString = componentStrings[index];
            Number number = createNumber(base, componentString);
            components[index] = number;
        }

        return createMatrix(base, columns, rows, components);
    }

}

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

package jmul.reflection;


import java.lang.reflect.Array;


/**
 * A utility class for instantiating generic arrays.
 *
 * @param <T>
 *        the actual element type
 *
 * TODO Move to reflection package.
 *
 * @author Kristian Kutin
 */
@Deprecated
public class ArrayInstantiator<T> {

    /**
     * Instantiate a new empty array with the specified length.
     *
     * @param anElementType
     *        the element type (needed due to generic type erasure)
     * @param aLength
     *        the legnth of the new array
     *
     * @return a new instance
     */
    @Deprecated
    public T[] instantiate(Class<T> anElementType, int aLength) {

        return (T[]) Array.newInstance(anElementType, aLength);
    }

    @Deprecated
    public T[] appendLeft(Class<T> anElementType, T[] anArray, T aDigit) {

        int oldLength = anArray.length;
        int newLength = oldLength + 1;
        int indexLeft = 0;

        T[] copy = instantiate(anElementType, newLength);

        copy[indexLeft] = aDigit;

        for (int a = 0; a < oldLength; a++) {

            copy[a + 1] = anArray[a];
        }

        return copy;
    }

    /**
     *
     *
     * @param anElementType
     * @param anArray
     * @param aDigit
     * @return
     */
    @Deprecated
    public T[] appendRight(Class<T> anElementType, T[] anArray, T aDigit) {

        int oldLength = anArray.length;
        int newLength = oldLength + 1;
        int indexRight = newLength - 1;

        T[] copy = instantiate(anElementType, newLength);

        for (int a = 0; a < anArray.length; a++) {

            copy[a] = anArray[a];
        }

        copy[indexRight] = aDigit;

        return copy;
    }

}

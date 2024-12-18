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

package jmul.math.operations;


/**
 * A custom interface for comparators with mixed parameter signatures.
 *
 * @author Kristian Kutin
 *
 * @param <T>
 *        the type of objects that may be compared by this comparator
 * @param <S>
 *        the type of objects that may be compared by this comparator
 */
public interface MixedComparator<T, S> {

    /**
     * Compares the specified objects.
     *
     * @param o1
     *        the first object
     * @param o2
     *        the second object
     *
     * @return <code>1</code> if the first object is greater than the second object, <code>0</code> if both
     *         objects are considered equal or <code>-1</code> if the first object is lesser than the second
     *         object
     */
    int compare(T o1, S o2);

}

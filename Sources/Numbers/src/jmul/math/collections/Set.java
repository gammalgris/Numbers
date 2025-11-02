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

package jmul.math.collections;


import jmul.math.numbers.Number;


/**
 * This interface describes a set (see <a href="https://en.wikipedia.org/wiki/Set_theory">set theory</a>).
 *
 * @param <T>
 *        the element type
 *
 * @author Kristian Kutin
 */
public interface Set<T> extends Iterable<T> {

    /**
     * The number base for ordinal numbers.
     *
     * @return a number base
     */
    int base();

    /**
     * Checks if this set is empty.
     *
     * @return <code>true</code> if this set is empty, else <code>false</code>
     */
    boolean isEmpty();

    /**
     * Returns the total number of elements of this set.
     *
     * @return the total number of elements of this set
     */
    Number elements();

    /**
     * Returns the element associated with the specified ordinal number.
     *
     * @param ordinal
     *        an ordinal number
     *
     * @return an element which is member of this set
     */
    T ordinal(Number ordinal);

    /**
     * Checks if the specified element is a member of this set.
     *
     * @param e
     *        an element
     *
     * @return <code>true</code> if the specified element is a member of this set, else <code>false</code>
     */
    boolean isElement(T e);

    /**
     * Returns the intersection of this set with the specified set.
     *
     * @param s
     *        a set
     *
     * @return the return set contains all elements which are members of both sets
     */
    Set<T> intersection(Set<T> s);

    /**
     * Returns the union of this set with the specified set.
     *
     * @param s
     *        a set
     *
     * @return the return set contains all elements of both sets
     */
    Set<T> union(Set<T> s);

    /**
     * Returns the difference of this set with the specified set.
     *
     * @param s
     *        a set
     *
     * @return the return set contains elements which are members of this set but not members of the specified set
     */
    Set<T> difference(Set<T> s);

    /**
     * Returns the symmetric difference of this set with the specified set.
     *
     * @param s
     *        a set
     *
     * @return the return set contains elements that are members of either this set or the specified set
     */
    Set<T> symmetricDifference(Set<T> s);

}

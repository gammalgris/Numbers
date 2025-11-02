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


import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import jmul.math.Math;
import jmul.math.hash.HashHelper;
import jmul.math.numbers.Number;
import static jmul.math.numbers.NumberHelper.parseInteger;
import jmul.math.operations.implementations.ParameterCheckHelper;


/**
 * An implementation of a sequence.
 *
 * @param <T>
 *        the element type
 *
 * @author Kristian Kutin
 */
public class SequenceImpl<T> implements Sequence<T> {

    /**
     * A number base.
     */
    final int base;

    /**
     * The actual element container.
     */
    final SortedMap<Number, T> members;

    /**
     * Creates a new sequence according to the specified elements.
     *
     * @param base
     *        a number base
     * @param elements
     *        an array with elements
     */
    public SequenceImpl(int base, T... elements) {

        super();

        ParameterCheckHelper.checkNumberBase(base);

        if (elements == null) {

            throw new IllegalArgumentException("No elements (null) were specified!");
        }

        this.base = base;
        this.members = new TreeMap<>();

        final Number ZERO = Math.ZERO.value(base);
        Number ordinal = ZERO;

        for (T element : elements) {

            addElement(ordinal, element);
            ordinal = ordinal.inc();
        }
    }

    /**
     * Creates a new sequence according to the specified elements.
     *
     * @param base
     *        a number base
     * @param elements
     *        a list with elements
     */
    public SequenceImpl(int base, List<T> elements) {

        super();

        ParameterCheckHelper.checkNumberBase(base);

        if (elements == null) {

            throw new IllegalArgumentException("No elements (null) were specified!");
        }

        this.base = base;
        this.members = new TreeMap<>();

        final Number ZERO = Math.ZERO.value(base);
        Number ordinal = ZERO;

        for (T element : elements) {

            addElement(ordinal, element);
            ordinal = ordinal.inc();
        }
    }

    /**
     * Adds an element to this set.<br>
     * <br>
     * <i>Note:<br>
     * This method is only used during initialization.
     * </i>
     *
     * @param ordinal
     *        an ordinal number
     * @param element
     *        an element
     */
    private void addElement(Number ordinal, T element) {

        if (element == null) {

            throw new IllegalArgumentException("No element (null) was specified!");
        }

        this.members.put(ordinal, element);
    }

    /**
     * The number base for ordinal numbers.
     *
     * @return a number base
     */
    @Override
    public int base() {

        return base;
    }

    /**
     * Checks if this sequence is empty.
     *
     * @return <code>true</code> if this sequence is empty, else <code>false</code>
     */
    @Override
    public boolean isEmpty() {

        return members.isEmpty();
    }

    /**
     * Returns the total number of elements of this sequence.
     *
     * @return the total number of elements of this sequence
     */
    @Override
    public Number elements() {

        int size = members.size();
        Number s = parseInteger(size);

        return s.rebase(base);
    }

    /**
     * Returns the element associated with the specified ordinal number.
     *
     * @param ordinal
     *        an ordinal number
     *
     * @return an element which is member of this sequence
     */
    @Override
    public T ordinal(Number ordinal) {

        T value = members.get(ordinal);

        if (value == null) {

            throw new IllegalArgumentException("An invalid ordinal number was specified!");
        }

        return value;
    }

    /**
     * Checks if the specified element is a member of this sequence.
     *
     * @param e
     *        an element
     *
     * @return <code>true</code> if the specified element is a member of this sequence, else <code>false</code>
     */
    @Override
    public boolean isElement(T e) {

        return members.containsValue(e);
    }

    /**
     * Returns an iterator for this set.
     *
     * @return an iterator
     */
    @Override
    public Iterator<T> iterator() {

        return members.values().iterator();
    }

    /**
     * Returns a string representation of this set.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("{");

        boolean first = true;
        for (T element : this) {

            if (first) {

                first = false;

            } else {

                buffer.append(", ");
            }

            buffer.append(element);
        }

        buffer.append("}");

        return buffer.toString();
    }

    /**
     * Calculates a hash code for this sequence.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        Object[] a = members.values().toArray();

        return HashHelper.calculateHashCode(Sequence.class, a);
    }

    /**
     * Compares this sequence with the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if this sequence is considered equal to the specified object, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof Sequence) {

            Sequence other = (Sequence) o;

            if (!this.elements().equals(other.elements())) {

                return false;
            }

            for (Number ordinal = Math.ZERO.value(base); ordinal.isLesser(this.elements()); ordinal = ordinal.inc()) {

                Object element1 = this.ordinal(ordinal);
                Object element2 = other.ordinal(ordinal);

                if (!element1.equals(element2)) {

                    return false;
                }
            }

            return true;
        }

        return false;
    }

}

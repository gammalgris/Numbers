/*
 * SPDX-License-Identifier: GPL-3.0
 *
 *
 * (J)ava (M)iscellaneous (U)tilities (L)ibrary
 *
 * JMUL is a central repository for utilities which are used in my
 * other public and private repositories.
 *
 * Copyright (C) 2026  Kristian Kutin
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

package jmul.math.equations.components;


import jmul.math.hash.HashHelper;
import jmul.math.numbers.Number;


/**
 * An implementation of a constant.
 *
 * @author Kristian Kutin
 */
public class ConstantImpl implements Constant {

    /**
     * The value of this constant.
     */
    private final Number value;

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param value
     *        the value for this constant
     */
    public ConstantImpl(Number value) {

        super();

        if (value == null) {

            throw new IllegalArgumentException("No constant value (null) was specified!");
        }

        this.value = value;
    }

    /**
     * Returns the valueof this constant.
     *
     * @return a value
     */
    @Override
    public Number value() {

        return value;
    }

    /**
     * Returns a string representation for this constant.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return value.toString();
    }

    /**
     * Calculates and returns a hash code for this constant.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(Constant.class, value);
    }

    /**
     * Compares this constant with the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if this constant and the specified object are considered equal, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (null == o) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof Constant) {

            Constant other = (Constant) o;

            return this.value().equals(other.value());
        }

        return false;
    }

}

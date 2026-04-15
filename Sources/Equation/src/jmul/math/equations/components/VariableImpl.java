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


/**
 * An implementation of a variable.
 *
 * @author Kristian Kutin
 */
class VariableImpl implements Variable {

    /**
     * The name of this variable.
     */
    private final String name;

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param name
     *        the name for this variable
     */
    public VariableImpl(String name) {

        super();

        if (name == null) {

            throw new IllegalArgumentException("No variable name (null) was specified!");
        }

        if (name.trim().isEmpty()) {

            throw new IllegalArgumentException("No variable name (empty string) was specified!");
        }

        this.name = name;
    }

    /**
     * Returns the name of this variable.
     *
     * @return the name of this variable
     */
    @Override
    public String name() {

        return name;
    }

    /**
     * Returns a string representation for this variable.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return name;
    }

    /**
     * Calculates and returns a hash code for this variable.
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {

        return HashHelper.calculateHashCode(Variable.class, name);
    }

    /**
     * Compares this variable with the specified object.
     *
     * @param o
     *        an object
     *
     * @return <code>true</code> if this variable and the specified object are considered equal, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {

        if (null == o) {

            return false;
        }

        if (this == o) {

            return true;
        }

        if (o instanceof Variable) {

            Variable other = (Variable) o;

            return this.name().equals(other.name());
        }

        return false;
    }

}

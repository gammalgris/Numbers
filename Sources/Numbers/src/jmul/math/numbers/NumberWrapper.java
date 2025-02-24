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

package jmul.math.numbers;


import jmul.math.fractions.Fraction;
import jmul.math.numbers.exceptions.NumberTypeMismatchException;


/**
 * A wrapper class for a number or a fraction.
 *
 * @author Kristian Kutin
 *
 * @deprecated Not sure if this is really needed.
 */
@Deprecated
public final class NumberWrapper {

    /**
     * A number.
     */
    private final AbstractNumber number;

    /**
     * Creates a new instance according to the specified parameter.
     *
     * @param number
     *        a number
     */
    public NumberWrapper(AbstractNumber number) {

        super();

        this.number = number;
    }

    /**
     * Checks if this wrapper contains a number.
     *
     * @return <code>true</code> if this wrapper contains a number, else <code>false</code>
     */
    public boolean isNumber() {

        return number instanceof Number;
    }

    /**
     * Returns the wrapped number.
     *
     * @return a number
     */
    public Number number() {

        if (!isNumber()) {

            throw new NumberTypeMismatchException();
        }

        return (Number) number;
    }

    /**
     * Checks if this wrapper contains a fraction.
     *
     * @return <code>true</code> if this wrapper contains a fraction, else <code>false</code>
     */
    public boolean isFraction() {

        return number instanceof Fraction;
    }

    /**
     * Returns the wrapped fraction.
     *
     * @return a fraction
     */
    public Fraction fraction() {

        if (!isFraction()) {

            throw new NumberTypeMismatchException();
        }

        return (Fraction) number;
    }

    /**
     * Returns a string representation for this wrapper.
     *
     * @return a string representation
     */
    @Override
    public String toString() {

        return number.toString();
    }

}

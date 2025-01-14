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

package jmul.math.functions.implementations;


import jmul.math.digits.Digit;
import jmul.math.functions.Function;
import jmul.math.operations.EqualityFunction;


/**
 * An implementation of an equality comparator for digits.
 *
 * @author Kristian Kutin
 */
public class DigitEquality implements Function, EqualityFunction<Digit> {

    /**
     * The defailt constructor.
     */
    public DigitEquality() {

        super();
    }

    /**
     * Compares the two specified digits regarding equality.
     *
     * @param d1
     *        a digit
     * @param d2
     *        a digit
     *
     * @return <code>true</code> if both digits are considered equal, else <code>false</code>
     */
    @Override
    public boolean equals(Digit d1, Digit d2) {

        // Check the references

        if ((d1 == null) && (d2 == null)) {

            return true;
        }

        if ((d1 == null) || (d2 == null)) {

            return false;
        }

        if (d1 == d2) {

            return true;
        }

        // Check the digit bases

        if (d1.base() != d2.base()) {

            return false;
        }

        // Check the ordinal values

        return d1.ordinal() == d2.ordinal();
    }

}

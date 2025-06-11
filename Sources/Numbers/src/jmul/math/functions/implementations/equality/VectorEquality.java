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

package jmul.math.functions.implementations.equality;


import java.util.Iterator;

import jmul.math.functions.Function;
import jmul.math.numbers.Number;
import jmul.math.numbers.exceptions.DigitBaseMismatchException;
import jmul.math.operations.EqualityFunction;
import jmul.math.vectors.Vector;


/**
 * An implementation of an equality comparator for vectors.
 *
 * @author Kristian Kutin
 */
public class VectorEquality implements Function, EqualityFunction<Vector> {

    /**
     * The default constructor.
     */
    public VectorEquality() {

        super();
    }

    /**
     * Compares the two specified vectors regarding equality.
     *
     * @param v1
     *        a vector
     * @param v2
     *        a vector
     *
     * @return <code>true</code> if both vectors are considered equal, else <code>false</code>
     */
    @Override
    public boolean equals(Vector v1, Vector v2) {

        // Check the references

        if ((v1 == null) && (v2 == null)) {

            return true;
        }

        if ((v1 == null) || (v2 == null)) {

            return false;
        }

        if (v1 == v2) {

            return true;
        }

        // Check the number bases

        if (v1.base() != v2.base()) {

            throw new DigitBaseMismatchException(v1, v2);
        }

        // check the components

        Iterator<Number> iterator1 = v1.iterator();
        Iterator<Number> iterator2 = v2.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {

            Number component1 = iterator1.next();
            Number component2 = iterator2.next();

            if (!component1.equals(component2)) {

                return false;
            }
        }

        return true;
    }

}

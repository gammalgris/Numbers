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

package jmul.math.fraction.functions;


import java.util.Comparator;

import jmul.math.fraction.Fraction;
import jmul.math.functions.Function;
import jmul.math.functions.FunctionSingletons;
import jmul.math.numbers.FunctionIdentifiers;
import jmul.math.numbers.functions.EqualityFunction;


/**
 * An implementation of an equality comparator for expressions.
 *
 * @author Kristian Kutin
 */
public class FractionEquality implements Function, EqualityFunction<Fraction> {

    /**
     * The default constructor.
     */
    public FractionEquality() {

        super();
    }

    /**
     * Compares the two specified expressions regarding equality.
     *
     * @param e1
     *        a number
     * @param e2
     *        a number
     *
     * @return <code>true</code> if both numbers are considered equal, else <code>false</code>
     */
    @Override
    public boolean equals(Fraction e1, Fraction e2) {

        // Check the references

        if ((e1 == null) && (e2 == null)) {

            return true;
        }

        if ((e1 == null) || (e2 == null)) {

            return false;
        }

        if (e1 == e2) {

            return true;
        }

        Comparator<Fraction> function =
            (Comparator<Fraction>) FunctionSingletons.getFunction(FunctionIdentifiers.FRACTION_COMPARATOR_FUNCTION);
        int result = function.compare(e1, e2);

        return result == 0;
    }

}

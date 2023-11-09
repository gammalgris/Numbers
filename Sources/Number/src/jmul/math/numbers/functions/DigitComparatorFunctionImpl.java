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

package jmul.math.numbers.functions;


import java.util.Comparator;

import jmul.math.numbers.digits.Digit;
import static jmul.math.numbers.functions.ParameterCheckHelper.checkParameter;
import static jmul.math.numbers.functions.ParameterCheckHelper.checkParameterBase;

import jmul.math.functions.Function;


/**
 * An implementation of an natural ordering comparator for digits.
 *
 * @author Kristian Kutin
 */
public class DigitComparatorFunctionImpl extends ComparatorBase implements Function, Comparator<Digit> {

    /**
     * The default constructor.
     */
    public DigitComparatorFunctionImpl() {

        super();
    }

    /**
     * Compares the two specified digits regarding their natural order.
     *
     * @param d1
     *        a number
     * @param d2
     *        a number
     *
     * @return <code>1</code>, <code>0</code> or <code>-1</code> if the first digit is greater than,
     *         equals or lesser than the second digit.
     */
    @Override
    public int compare(Digit d1, Digit d2) {

        // Check the references

        checkParameter(d1);
        checkParameter(d2);
        checkParameterBase(d1, d2);

        if (d1 == d2) {

            return EQUALS;
        }

        // compare the ordinal values

        if (d1.ordinal() > d2.ordinal()) {

            return GREATER_THAN;
        }

        if (d1.ordinal() < d2.ordinal()) {

            return LESSER_THAN;
        }

        // return a default value

        return EQUALS;
    }

}

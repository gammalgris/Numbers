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

package jmul.math.units;


import jmul.math.Math;
import jmul.math.numbers.Number;
import jmul.math.operations.implementations.ParameterCheckHelper;


public final class Conversions {

    /**
     * The default constructor.
     */
    private Conversions() {

        throw new UnsupportedOperationException();
    }

    public static Number convert(Number number, Angle unitFrom, Angle unitTo) {

        ParameterCheckHelper.checkParameter(number);

        if (unitFrom == null) {

            throw new IllegalArgumentException("No unit (null) was specified!");
        }

        if (unitTo == null) {

            throw new IllegalArgumentException("No unit (null) was specified!");
        }

        if (unitFrom == unitTo) {

            return number;
        }

        int base = number.base();

        final Number pi = Math.pi(base);

        //TODO

        return null;
    }

}
